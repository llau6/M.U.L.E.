package MULE.Model;

import javafx.scene.control.Button;

/**
 * Mule Condtions
 */
public class Mule {
    String type;
    Button onLand;

    /**
     * Mule constructor
     * @param type Mule Type
     */
    public Mule(String type) {
        this.type = type;
    }

    /**
     * Mule constructor
     * @param type Mule Type
     * @param onLand Land Button the mule is on
     */
    public Mule(String type, Button onLand) {
        this.type = type;
        this.onLand = onLand;
    }

    /**
     * Retrieves the type of mule
     * @return type of mule
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the land of mule
     * @return land of mule
     */
    public Button getLand() {
        return onLand;
    }

    /**
     * Sets the land of which mule is placed on
     * @param land Land Button Pressed
     */
    public void setLand(Button land) {
        onLand = land;
    }

//    public boolean isInOwnedLand_noMuleExist(
// Button selected, Player currPlayer) {
//        ArrayList<Button> ownedLands = currPlayer.getLands();
//        boolean ownedLand_noMuleExist = false;
//        for (Button land : ownedLands) {
//            if (land.equals(selected)) {
//                ownedLand_noMuleExist =  true;
//            }
//        }
//        for (int i = 0; i < currPlayer.getMules().size(); i++) {
//            if (currPlayer.getMules().get(i).getLand() != null
// && currPlayer.getMules().get(i).getLand().equals(selected)) {
//                ownedLand_noMuleExist = false;
//            }
//        }
//        return ownedLand_noMuleExist;
//    }

    /**
     * Retrieves what land mule is on
     * @return Land Button placed
     */
    public Button getOnLand() {
        return onLand;
    }
}
