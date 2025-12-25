package controller;

import model.Perspective;
import model.TranslateCommand;
import model.ZoomCommand;

public class MouseController {

    private AppController app;
    private Perspective p;

    private double startX, startY;
    private double initialX, initialY;
    private boolean dragging;

    public MouseController(AppController app) {
        this.app = app;
    }

    public void setCurrentView(Perspective perspective) {
        this.p = perspective;
    }

    public void onMousePressed(double x, double y) {
        this.p = app.getPerspectiveActive();
        startX = x;
        startY = y;

        initialX = p.getOffsetX();
        initialY = p.getOffsetY();

        dragging = true;
    }

    public void onMouseDragged(double x, double y) {
        if (!dragging) return;

        double dx = x - startX;
        double dy = y - startY;

        // Déplacement visuel immédiat
        p.move(dx, dy);

        startX = x;
        startY = y;
    }

    public void onMouseReleased() {
        if (!dragging) return;
        dragging = false;

        double dxTotal = p.getOffsetX() - initialX;
        double dyTotal = p.getOffsetY() - initialY;

        app.execute(new TranslateCommand(p, dxTotal, dyTotal));
    }

    public void onScroll(double wheelRotation) {

        double facteurZoom = (wheelRotation < 0) ? 1.1 : 0.9;

        app.execute(new ZoomCommand(app.getPerspectiveActive(), facteurZoom));
    }
}