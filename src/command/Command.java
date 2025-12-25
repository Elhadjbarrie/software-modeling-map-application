package command;
public abstract class Command {

    protected Perspective perspective;
    protected String nom;

    public Command(Perspective perspective, String nom) {
        this.perspective = perspective;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public abstract void execute();
    public abstract void undo();
}