package controller;

public class MenuController {

    private final AppController appController;

    public MenuController(AppController appController) {
        this.appController = appController;
    }

    // Image
    public void chargerImage(String chemin) {
        appController.chargerImage(chemin);
    }

    // Undo / Redo
    public void undo() {
        appController.undo();
    }

    public void redo() {
        appController.redo();
    }

    // Copier / Coller paramètres
    public void copierParams() {
        appController.copierParams();
    }

    public void collerParams() {
        appController.collerParams();
    }

    // Sauver / Charger perspective (JSON)
    public void savePerspective(String chemin) {
        appController.savePerspective(chemin);
    }

    public void loadPerspective(String chemin) {
        appController.loadPerspective(chemin);
    }

    // Quitter
    public void quitter() {
        System.exit(0);
    }
}