package view;

import controller.AppController;

import javax.swing.*;
import java.awt.*;

public class ThumbnailView extends JPanel {

    private AppController app = AppController.getInstance();

    public ThumbnailView() {
        setPreferredSize(new Dimension(150, 150));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (app.getImageModel() == null || app.getImageModel().getImage() == null)
            return;

        Image img = app.getImageModel().getImage();
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
    }
}
