package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageModel {

    private BufferedImage image;
    private int largeur;
    private int hauteur;

    private String currentPath;

    public ImageModel(String cheminImage) throws IOException {
        chargerImage(cheminImage);
    }

    public void chargerImage(String cheminImage) {
        try {
            File file = new File(cheminImage);

            if (!file.exists()) {
                throw new IOException("Fichier introuvable : " + file.getAbsolutePath());
            }

            // Lecture simple
            image = ImageIO.read(file);

            if (image == null) {
                throw new IOException("Format d'image non supporté : " + cheminImage);
            }

            largeur = image.getWidth();
            hauteur = image.getHeight();
            currentPath = cheminImage;

        } catch (Exception e) {
            System.err.println("Erreur chargement image : " + e.getMessage());
            image = null;
            largeur = 0;
            hauteur = 0;
            currentPath = null;
        }
    }

    public BufferedImage getImage() { return image; }
    public int getLargeur() { return largeur; }
    public int getHauteur() { return hauteur; }
    public String getCurrentPath() { return currentPath; }
}