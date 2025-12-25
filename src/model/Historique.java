package model;

import java.util.Stack;

public class Historique {

    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    // Ajouter une commande après exécution
    public void add(Command cmd) {
        undoStack.push(cmd);
        redoStack.clear();    // Toute nouvelle action efface le redo (logique standard)
    }

    // Annuler
    public void undo() {
        if (undoStack.isEmpty()) return;

        Command cmd = undoStack.pop();
        cmd.undo();
        redoStack.push(cmd);
    }

    // Refaire
    public void redo() {
        if (redoStack.isEmpty()) return;

        Command cmd = redoStack.pop();
        cmd.execute();
        undoStack.push(cmd);
    }

    // (Optionnel) pour savoir si on peut undo/redo
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }
}