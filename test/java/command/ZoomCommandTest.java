package command;

import static org.junit.jupiter.api.Assertions.*;

import command.ZoomCommand;
import org.junit.jupiter.api.Test;

import model.Perspective;

class ZoomCommandTest {

    @Test
    void executeAndUndoRestorePreviousZoom() {
        Perspective p = new Perspective();
        p.setZoom(1.0);

        ZoomCommand cmd = new ZoomCommand(p, 2.0);

        cmd.execute();
        assertEquals(2.0, p.getZoom(), 0.0001);

        cmd.undo();
        assertEquals(1.0, p.getZoom(), 0.0001);
    }
}
