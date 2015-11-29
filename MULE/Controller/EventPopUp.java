package MULE.Controller;

import MULE.Model.GameManager;
import MULE.Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Lily on 11/29/2015.
 */
public class EventPopUp implements Initializable {
    @FXML
    private Button okayButton;
    @FXML
    private Label eventMessage;
    private ArrayList<String> randomEvents;
    private int m;
    private boolean lowestScorePerson;
    private int randomNum;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        randomEvents = new ArrayList<>();
        if (GameManager.getCurrentTurnNumber() == 1) {
            lowestScorePerson = true;
        }
        if (GameManager.getCurrentRoundNumber() < 4) {
            m = 25;
        } else if (GameManager.getCurrentRoundNumber() < 8) {
            m = 50;
        } else if (GameManager.getCurrentRoundNumber() < 12) {
            m = 75;
        } else {
            m = 100;
        }
        randomEvents.add("YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.");
        randomEvents.add("A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.");
        randomEvents.add("THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $" + 8*m);
        randomEvents.add("YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $"+2*m);
        randomEvents.add("FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $"+4*m);
        randomEvents.add("MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.");
        randomEvents.add("YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU $"+ 6*m + " TO CLEAN IT UP");
        randomEvents.add("YOUR LITTLE SISTER ACCIDENTALLY BREAKS YOUR MULE'S BACK. LOST HALF ORE.");
        randomEvents.add("YOUR MOM CALLS AND SENDS YOU $" + 2*m + ".");
        randomEvents.add("HUNGRY BAND OF SQUIRRELS STEAL 3 FOODS FROM YOU");

        if (lowestScorePerson) {
            randomNum = (int) (Math.random() * 3);
        } else {
            randomNum = (int) (Math.random() * 6);
        }
        eventMessage.setText(randomEvents.get(randomNum));
        initiateEvent(randomNum);
         okayButton.setOnMouseClicked((event) -> {
             ((Stage) okayButton.getScene().getWindow()).close();
             MapScreen.updateResources();
         });
    }
    public void initiateEvent(int index) {
        Player p = GameManager.getCurrentPlayer();
        if (index == 0) {
            p.setFoodCount(p.getFoodCount() + 3);
            p.setEnergyCount(p.getEnergyCount() + 2);
        } else if (index == 1) {
            p.setOreCount(p.getOreCount() + 2);
        } else if (index == 2) {
            p.setMoney(p.getMoney() + (8 * m));
        } else if (index == 3) {
            p.setMoney(p.getMoney() + (2 * m));
        } else if (index == 4) {
            p.setMoney(p.getMoney() - 4 * m);
        } else if (index == 5) {
            p.setFoodCount(p.getFoodCount()/2);
        } else if (index == 6) {
            p.setFoodCount(p.getFoodCount() - 6 * m);
        } else if (index == 7) {
            p.setOreCount(p.getOreCount()/2);
        } else if (index == 8) {
            p.setMoney(p.getMoney() + (2 * m));
        } else if (index == 9) {
            p.setFoodCount(p.getFoodCount() - 3);
        }
    }

}
