package view;

import controller.AppController;
import controller.MenuController;
import model.Perspective;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private AppController appController;
    private MenuController menuController;

    private PerspectiveView viewPrincipale;
    private PerspectiveView viewSecondaire;

    public MainWindow() {

        this.appController = AppController.getInstance();
        this.menuController = new MenuController(appController);

        setTitle("Projet Session - Visualisation d'image");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setJMenuBar(creerMenuBar());

        // Toolbar
        add(new ToolbarView(), BorderLayout.NORTH);

        // Perspectives
        Perspective p1 = appController.getPerspectivePrincipale();
        Perspective p2 = appController.getPerspectiveSecondaire();

        viewPrincipale = new PerspectiveView(p1);
        viewSecondaire = new PerspectiveView(p2);

        JSplitPane split = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                viewPrincipale,
                viewSecondaire
        );
        split.setResizeWeight(0.7);
        add(split, BorderLayout.CENTER);

        // >>> ICI : création du thumbnail SUR la vue principale <<<
        ThumbnailView thumbnail = new ThumbnailView();
        viewPrincipale.addThumbnail(thumbnail);   // nouvelle méthode qu'on ajoute ci-dessous
    }
    private JMenuBar creerMenuBar() {

        JMenuBar menuBar = new JMenuBar();

        JMenu menuFichier = new JMenu("Fichier");

        // Ouvrir image
        JMenuItem itemCharger = new JMenuItem("Ouvrir une image");
        itemCharger.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String chemin = chooser.getSelectedFile().getAbsolutePath();
                menuController.chargerImage(chemin);
                repaint();
            }
        });

        // ----------------------
        // SAUVEGARDE / CHARGEMENT DE PERSPECTIVE
        // ----------------------

        JMenuItem itemSavePerspective = new JMenuItem("Sauvegarder perspective...");
        itemSavePerspective.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                String chemin = chooser.getSelectedFile().getAbsolutePath();
                menuController.savePerspective(chemin);
            }
        });

        JMenuItem itemLoadPerspective = new JMenuItem("Charger perspective...");
        itemLoadPerspective.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String chemin = chooser.getSelectedFile().getAbsolutePath();
                menuController.loadPerspective(chemin);
            }
        });

        // Quitter
        JMenuItem itemQuitter = new JMenuItem("Quitter");
        itemQuitter.addActionListener(e -> menuController.quitter());

        // Ajout des items dans le menu Fichier
        menuFichier.add(itemCharger);
        menuFichier.addSeparator();
        menuFichier.add(itemSavePerspective);
        menuFichier.add(itemLoadPerspective);
        menuFichier.addSeparator();
        menuFichier.add(itemQuitter);

        // Ajouter dans la barre
        menuBar.add(menuFichier);

        return menuBar;
    }
}

