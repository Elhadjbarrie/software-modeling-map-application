
package view;

import controller.AppController;
import controller.MouseController;
import model.ImageModel;
import model.Perspective;
import model.PerspectiveObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PerspectiveView extends JPanel implements PerspectiveObserver {

    private Perspective perspective;
    private AppController appController;
    private MouseController mouseController;

    public PerspectiveView(Perspective perspective) {

        this.perspective = perspective;
        this.appController = AppController.getInstance();
        this.mouseController = appController.getMouseController();

        // Important : layout nul pour pouvoir placer un thumbnail par-dessus
        setLayout(null);

        perspective.addObserver(this);
        setupMouseEvents();
        setDoubleBuffered(true);
    }

    // Méthode pour ajouter le thumbnail sur cette vue
    public void addThumbnail(JPanel thumbnail) {
        int w = 150;
        int h = 150;

        // On le place en bas-droite de la vue principale
        // (les coordonnées exactes sont ajustées lors du resize)
        thumbnail.setSize(w, h);
        add(thumbnail);

        // Écouteur pour recaler le thumbnail si la fenêtre est redimensionnée
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int margin = 15;
                thumbnail.setLocation(
                        getWidth() - w - margin,
                        getHeight() - h - margin
                );
                repaint();
            }
        });
    }

    private void setupMouseEvents() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                appController.setPerspectiveActive(perspective);
                mouseController.setCurrentView(perspective);
                mouseController.onMousePressed(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseController.onMouseReleased();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mouseController.onMouseDragged(e.getX(), e.getY());
            }
        });

        addMouseWheelListener(e -> {
            double rotation = e.getPreciseWheelRotation();
            mouseController.onScroll(rotation);
        });
    }

    @Override
    public void update() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        ImageModel imageModel = appController.getImageModel();
        if (imageModel == null || imageModel.getImage() == null) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        double zoom = perspective.getZoom();
        double dx = perspective.getOffsetX();
        double dy = perspective.getOffsetY();

        int newW = (int) (imageModel.getLargeur() * zoom);
        int newH = (int) (imageModel.getHauteur() * zoom);

        g2.drawImage(
                imageModel.getImage(),
                (int) dx,
                (int) dy,
                newW,
                newH,
                null
        );
    }
}