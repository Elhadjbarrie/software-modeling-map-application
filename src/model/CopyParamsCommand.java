package model;

public class CopyParamsCommand extends Command {

    private double zoom;
    private double offsetX;
    private double offsetY;

    public CopyParamsCommand(Perspective perspective) {
        super(perspective, "Copier paramètres");
    }

    @Override
    public void execute() {
        zoom = perspective.getZoom();
        offsetX = perspective.getOffsetX();
        offsetY = perspective.getOffsetY();
    }

    @Override
    public void undo() {
        // Rien à annuler : copier ne modifie pas l'état
    }

    // Permet d'être récupéré par PasteParamsCommand
    public double getZoom() { return zoom; }
    public double getOffsetX() { return offsetX; }
    public double getOffsetY() { return offsetY; }
}