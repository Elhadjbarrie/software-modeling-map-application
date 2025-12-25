package model;

public class PasteParamsCommand extends Command {

    private double ancienZoom;
    private double ancienX;
    private double ancienY;

    private double nouveauZoom;
    private double nouveauX;
    private double nouveauY;

    public PasteParamsCommand(Perspective perspective, double zoom, double offsetX, double offsetY) {
        super(perspective, "Coller paramètres");
        this.nouveauZoom = zoom;
        this.nouveauX = offsetX;
        this.nouveauY = offsetY;
    }

    @Override
    public void execute() {
        ancienZoom = perspective.getZoom();
        ancienX = perspective.getOffsetX();
        ancienY = perspective.getOffsetY();

        perspective.setZoom(nouveauZoom);
        perspective.setOffsetX(nouveauX);
        perspective.setOffsetY(nouveauY);
    }

    @Override
    public void undo() {
        perspective.setZoom(ancienZoom);
        perspective.setOffsetX(ancienX);
        perspective.setOffsetY(ancienY);
    }
}



