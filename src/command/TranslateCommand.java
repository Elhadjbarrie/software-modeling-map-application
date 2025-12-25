package command;
public class TranslateCommand extends Command{

    private double dx;
    private double dy;
    private double ancienX;
    private double ancienY;

    public TranslateCommand(Perspective perspective, double dx, double dy) {
        super(perspective, "Translation");
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void execute() {
        ancienX = perspective.getOffsetX();
        ancienY = perspective.getOffsetY();
        perspective.move(dx, dy);
    }

    @Override
    public void undo() {
        double newDx = ancienX - perspective.getOffsetX();
        double newDy = ancienY - perspective.getOffsetY();
        perspective.move(newDx, newDy);
    }
}