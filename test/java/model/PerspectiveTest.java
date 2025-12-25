package model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PerspectiveTest {

    @Test
    void zoomMustAlwaysBePositive() {
        Perspective p = new Perspective();
        p.setZoom(1.0);
        assertTrue(p.getZoom() > 0);
    }
}

