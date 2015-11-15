package MULE.Model;

import javafx.scene.image.Image;

/**
 * Created by SeYeon on 9/7/2015.
 */
public class Race {
    private String name;
    private Image image;

    public  Race (String nameInput, String filenameInput) {
        this.name = nameInput;
        this.image = new Image(filenameInput);
    }

    public final String getName() {
        return name;
    }

    public final Image getImage() {
        return image;
    }

    public final String toString() {
        return name;
    }
}
