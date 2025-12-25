package model;
import java.util.ArrayList;
import java.util.List;

public class Perspective {

    private double zoom = 1.0;
    private double offsetX = 0;
    private double offsetY = 0;

    private List<PerspectiveObserver> observers = new ArrayList<>();

    public double getZoom() { return zoom; }
    public double getOffsetX() { return offsetX; }
    public double getOffsetY() { return offsetY; }

    public void setZoom(double newZoom) {
        this.zoom = newZoom;
        notifyObservers();
    }

    public void setOffsetX(double x) {
        this.offsetX = x;
        notifyObservers();
    }

    public void setOffsetY(double y) {
        this.offsetY = y;
        notifyObservers();
    }

    public void move(double dx, double dy) {
        this.offsetX += dx;
        this.offsetY += dy;
        notifyObservers();
    }

    public void addObserver(PerspectiveObserver obs) {
        observers.add(obs);
    }

    public void removeObserver(PerspectiveObserver obs) {
        observers.remove(obs);
    }

    private void notifyObservers() {
        for (PerspectiveObserver obs : observers) {
            obs.update();
        }
    }

    public void forceNotify() {
        notifyObservers();
    }
}