package command;
public class ZoomCommand extends Command{

    private double facteurZoom;
    private double ancienZoom;

    public ZoomCommand(Perspective perspective, double facteurZoom) {
        super(perspective, "Zoom");
        this.facteurZoom = facteurZoom;
    }

    @Override
    public void execute() {
        ancienZoom = perspective.getZoom();                   // on garde l'ancien
        double nouveauZoom = ancienZoom * facteurZoom;        // multiplicatif
        perspective.setZoom(nouveauZoom);                     // déclenche notifyObservers()
    }

    @Override
    public void undo() {
        perspective.setZoom(ancienZoom);                      // on revient à l’ancien niveau
    }
}