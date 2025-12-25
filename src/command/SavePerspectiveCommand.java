package command;
import controller.AppController;
import java.io.FileWriter;
import java.io.IOException;

public class SavePerspectiveCommand extends Command {

    private final String filePath;

    public SavePerspectiveCommand(Perspective perspective, String filePath) {
        super(perspective, "SavePerspective");
        this.filePath = filePath;
    }

    @Override
    public void execute() {

        AppController app = AppController.getInstance();

        if (perspective == null || app.getImageModel() == null) return;

        String imagePath = app.getImageModel().getCurrentPath();
        if (imagePath == null) return;

        // JSON simple, sans suppression d'espaces
        String json =
                "{\n" +
                        "  \"imagePath\": \"" + imagePath + "\",\n" +
                        "  \"zoom\": " + perspective.getZoom() + ",\n" +
                        "  \"offsetX\": " + perspective.getOffsetX() + ",\n" +
                        "  \"offsetY\": " + perspective.getOffsetY() + "\n" +
                        "}";

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
            System.out.println("Perspective sauvegardée dans " + filePath);
        } catch (IOException e) {
            System.err.println("Erreur sauvegarde perspective : " + e.getMessage());
        }
    }

    @Override
    public void undo() {
        // Rien à annuler : sauvegarde = opération externe
    }
}