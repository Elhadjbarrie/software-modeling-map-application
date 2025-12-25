package controller;

import model.*;

import java.io.IOException;

public class AppController {

    private EtatPerspective etatCopie = null;

    // --- Singleton ---
    private static AppController instance = null;

    public static AppController getInstance() {
        if (instance == null)
            instance = new AppController();
        return instance;
    }

    // --- Modèle ---
    private ImageModel imageModel;

    // Deux perspectives (grande + petite)
    private Perspective perspectivePrincipale;
    private Perspective perspectiveSecondaire;

    // La perspective actuellement manipulée
    private Perspective perspectiveActive;

    // Historique des commandes
    private Historique historique;

    // Mouse controller
    private MouseController mouseController;

    // --- Constructeur privé ---
    private AppController() {
        historique = new Historique();

        // On crée UNE FOIS les perspectives
        perspectivePrincipale = new Perspective();
        perspectiveSecondaire = new Perspective();
        perspectiveActive = perspectivePrincipale;

        mouseController = new MouseController(this);
    }

    public MouseController getMouseController() {
        return mouseController;
    }

    // --- Chargement de l'image ---
    public void chargerImage(String chemin) {
        try {
            this.imageModel = new ImageModel(chemin);

            // on ne recrée pas les perspectives ici
            // On force juste un rafraîchissement des vues
            if (perspectivePrincipale != null) {
                perspectivePrincipale.forceNotify();
            }
            if (perspectiveSecondaire != null) {
                perspectiveSecondaire.forceNotify();
            }

        } catch (IOException e) {
            System.err.println("Erreur chargement image : " + e.getMessage());
        }
    }

    // --- Perspectives actives ---
    public void setPerspectiveActive(Perspective p) {
        this.perspectiveActive = p;
    }

    public Perspective getPerspectiveActive() {
        return perspectiveActive;
    }

    public Perspective getPerspectivePrincipale() {
        return perspectivePrincipale;
    }

    public Perspective getPerspectiveSecondaire() {
        return perspectiveSecondaire;
    }

    public ImageModel getImageModel() {
        return imageModel;
    }

    // --- Exécution des commandes ---
    public void execute(Command cmd) {
        if (cmd == null) return;
        cmd.execute();
        historique.add(cmd);
    }

    public void undo() {
        historique.undo();
    }

    public void redo() {
        historique.redo();
    }

    // --- Copier / Coller les paramètres ---
    public void copierParams() {
        Perspective p = getPerspectiveActive();
        if (p == null) return;

        CopyParamsCommand cmd = new CopyParamsCommand(p);
        execute(cmd);

        etatCopie = new EtatPerspective(
                cmd.getZoom(),
                cmd.getOffsetX(),
                cmd.getOffsetY()
        );
    }

    public void collerParams() {
        if (etatCopie == null) return;

        Perspective p = getPerspectiveActive();
        if (p == null) return;

        PasteParamsCommand cmd = new PasteParamsCommand(
                p,
                etatCopie.zoom,
                etatCopie.offsetX,
                etatCopie.offsetY
        );

        execute(cmd);
    }

    // --- Sauvegarde / Chargement de perspective ---
    public void savePerspective(String chemin) {
        Perspective p = getPerspectiveActive();
        if (p == null) return;

        SavePerspectiveCommand cmd = new SavePerspectiveCommand(p, chemin);
        execute(cmd);
    }

    public void loadPerspective(String chemin) {
        Perspective p = getPerspectiveActive();
        if (p == null) return;

        LoadPerspectiveCommand cmd = new LoadPerspectiveCommand(p, chemin);
        execute(cmd);
    }

    // --- Structure interne pour copier/coller ---
    private static class EtatPerspective {
        double zoom;
        double offsetX;
        double offsetY;

        public EtatPerspective(double zoom, double offsetX, double offsetY) {
            this.zoom = zoom;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }
    }
}