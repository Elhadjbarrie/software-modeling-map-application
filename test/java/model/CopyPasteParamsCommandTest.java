package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CopyPasteParamsCommandTest {

    @Test
    void copyFromOnePerspectiveAndPasteToAnother() {
        Perspective source = new Perspective();
        source.setZoom(2.0);
        source.setOffsetX(100);
        source.setOffsetY(-50);

        Perspective target = new Perspective();

        CopyParamsCommand copy = new CopyParamsCommand(source);
        copy.execute();

        // ✅ PasteParamsCommand attend des doubles, pas l’objet CopyParamsCommand
        PasteParamsCommand paste = new PasteParamsCommand(
                target,
                source.getZoom(),
                source.getOffsetX(),
                source.getOffsetY()
        );
        paste.execute();

        assertEquals(2.0, target.getZoom(), 0.0001);
        assertEquals(100, target.getOffsetX(), 0.0001);
        assertEquals(-50, target.getOffsetY(), 0.0001);
    }
}
