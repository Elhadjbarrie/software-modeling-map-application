package view;

import controller.AppController;
import controller.MenuController;

import javax.swing.*;
import java.awt.*;

public class ToolbarView extends JToolBar {

    private AppController appController;
    private MenuController menuController;

    public ToolbarView() {

        this.appController = AppController.getInstance();
        this.menuController = new MenuController(appController);

        setFloatable(false);  // la toolbar est fixe
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Bouton : Charger image
        JButton btnCharger = new JButton("Ouvrir");
        btnCharger.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                String chemin = chooser.getSelectedFile().getAbsolutePath();
                menuController.chargerImage(chemin);
            }
        });
        add(btnCharger);

        // Bouton : Undo
        JButton btnUndo = new JButton("Undo");
        btnUndo.addActionListener(e -> menuController.undo());
        add(btnUndo);

        // Bouton : Redo
        JButton btnRedo = new JButton("Redo");
        btnRedo.addActionListener(e -> menuController.redo());
        add(btnRedo);

        // Bouton : Copier paramètres
        JButton btnCopier = new JButton("Copier");
        btnCopier.addActionListener(e -> menuController.copierParams());
        add(btnCopier);

        // Bouton : Coller paramètres
        JButton btnColler = new JButton("Coller");
        btnColler.addActionListener(e -> menuController.collerParams());
        add(btnColler);

    }

}
