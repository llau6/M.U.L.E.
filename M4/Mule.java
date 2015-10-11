package M4;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

/**
 * Created by SeYeon on 10/10/2015.
 */
public class Mule {
    String type;
    Button onLand;

    public Mule(String type) {
        this.type = type;
    }

    public Mule(String type, Button onLand) {
        this.type = type;
        this.onLand = onLand;
    }
    public String getType() {
        return type;
    }
    public void setLand(Button land) {
        onLand = land;
    }

    public boolean isInOwnedLand(Button selected, Player currPlayer) {
        ArrayList<Button> ownedLands = currPlayer.getLands();
        System.out.println(ownedLands.size());
        for (Button land : ownedLands) {
            if (land.equals(selected)) {
                return true;
            }
        }
        return false;
    }
}
