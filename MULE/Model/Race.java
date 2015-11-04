package MULE.Model;

import javafx.scene.image.Image;

/**
 * Race of Mules
 */
public class Race {
    private String name;
    private Image image;

    /**
     * Race Constructor
     * @param name of race
     * @param filename Image of race
     */
    public Race (String name, String filename) {
        this.name = name;
        this.image = new Image(filename);
    }

    /**
     * Gets the name of the race
     * @return  name of race
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the image of the race
     * @return  image of race
     */
    public Image getImage() {
        return image;
    }

    /**
     * Convert race name to string
     * @return name of race as a string
     */
    public String toString() {
        return name;
    }
}
