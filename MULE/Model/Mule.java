package MULE.Model;

import javafx.scene.control.Button;

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
    public Button getLand() { return onLand;}
    public void setLand(Button land) {
        onLand = land;
    }

//    public boolean isInOwnedLand_noMuleExist(Button selected, Player currPlayer) {
//        ArrayList<Button> ownedLands = currPlayer.getLands();
//        boolean ownedLand_noMuleExist = false;
//        for (Button land : ownedLands) {
//            if (land.equals(selected)) {
//                ownedLand_noMuleExist =  true;
//            }
//        }
//        for (int i = 0; i < currPlayer.getMules().size(); i++) {
//            if (currPlayer.getMules().get(i).getLand() != null && currPlayer.getMules().get(i).getLand().equals(selected)) {
//                ownedLand_noMuleExist = false;
//            }
//        }
//        return ownedLand_noMuleExist;
//    }

    public Button getOnLand() {
        return onLand;
    }
}
