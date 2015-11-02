package MULE.Model;

import javafx.scene.image.Image;

/**
 * Created by SeYeon on 9/7/2015.
 */
public class Race {
    private String name;
    private Image image;

    public Race (String name, String filename) {
        this.name = name;
        this.image = new Image(filename);
    }

    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public String toString() {
        return name;
    }
}
