package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class HistoriqueTest {

    @Test
    void undoAndRedoRestoreStateCorrectly() {
        Perspective p = new Perspective();
        p.setZoom(1.0);

        Historique h = new Historique();
        ZoomCommand cmd = new ZoomCommand(p, 2.0);

        // Exécution normale de la commande
        cmd.execute();
        h.add(cmd);
        assertEquals(2.0, p.getZoom(), 0.0001);

        // Undo
        h.undo();
        assertEquals(1.0, p.getZoom(), 0.0001);

        // Redo
        h.redo();
        assertEquals(2.0, p.getZoom(), 0.0001);
    }
}
