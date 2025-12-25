package model;

import controller.AppController;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LoadPerspectiveCommand extends Command {

    private final String filePath;

    private double ancienZoom;
    private double ancienX;
    private double ancienY;

    public LoadPerspectiveCommand(Perspective perspective, String filePath) {
        super(perspective, "LoadPerspective");
        this.filePath = filePath;
    }

    @Override
    public void execute() {
        try {
            // Sauvegarder l'état avant chargement (pour Undo)
            ancienZoom = perspective.getZoom();
            ancienX = perspective.getOffsetX();
            ancienY = perspective.getOffsetY();

            String content = Files.readString(Paths.get(filePath));

            // Extraction propre des champs JSON
            String imagePath = extract(content, "imagePath");
            String zoomStr   = extract(content, "zoom");
            String offXStr   = extract(content, "offsetX");
            String offYStr   = extract(content, "offsetY");

            double newZoom = (zoomStr  != null ? Double.parseDouble(zoomStr) : ancienZoom);
            double newX    = (offXStr  != null ? Double.parseDouble(offXStr) : ancienX);
            double newY    = (offYStr  != null ? Double.parseDouble(offYStr) : ancienY);

            AppController app = AppController.getInstance();

            // 1. Charger l'image si nécessaire
            if (imagePath != null && !imagePath.isBlank()) {
                app.chargerImage(imagePath);
            }

            // 2. Appliquer exactement l'état sauvegardé à la perspective active
            Perspective p = app.getPerspectiveActive();
            if (p == null) return;

            p.setZoom(newZoom);
            p.setOffsetX(newX);
            p.setOffsetY(newY);

        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de la perspective : " + e.getMessage());
        }
    }

    private String extract(String json, String key) {
        // Cherche "key":
        String k = "\"" + key + "\":";
        int start = json.indexOf(k);
        if (start < 0) return null;
        start += k.length();

        int endLine = json.indexOf(",", start);
        int endBrace = json.indexOf("}", start);
        int end;

        if (endLine < 0 && endBrace < 0) {
            end = json.length();
        } else if (endLine < 0) {
            end = endBrace;
        } else if (endBrace < 0) {
            end = endLine;
        } else {
            end = Math.min(endLine, endBrace);
        }

        return json.substring(start, end)
                .replace("\"", "")
                .trim();
    }

    @Override
    public void undo() {
        perspective.setZoom(ancienZoom);
        perspective.setOffsetX(ancienX);
        perspective.setOffsetY(ancienY);
    }
}