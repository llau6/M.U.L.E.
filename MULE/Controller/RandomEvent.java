package MULE.Controller;

//import com.sun.jdi.connect.Connector;
import MULE.Model.GameManager;
import MULE.Model.Player;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by SeYeon on 10/23/2015.
 */
public class RandomEvent {
    @FXML
    private ImageView treasureImage;
    @FXML
    javafx.scene.control.Button okay;
    @FXML
    javafx.scene.control.Label label;
    private ArrayList<String> randomEvents;
    private int m;
    private static javafx.scene.control.Label randomLabel;
    private boolean lowestScorePerson;
    private int randomNum;

    public void initialize() {
        randomLabel = label;
        randomEvents = new ArrayList<>();
        if (GameManager.currentTurnNumber == 1) {
            lowestScorePerson = true;
        }
        if (GameManager.currentRoundNumber < 4) {
            m = 25;
        } else if (GameManager.currentRoundNumber < 8) {
            m = 50;
        } else if (GameManager.currentRoundNumber < 12) {
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
    }

    public void initiateEvent(int index) {
        Player p = GameManager.currentPlayer;
        if (index == 0) {
            p.setFoodCount(p.getFoodCount() + 3);
            p.setEnergyCount(p.getEnergyCount() + 2);
        } else if (index == 1) {
            p.setOreCount(p.getOreCount() + 2);
        } else if (index == 2) {
            p.setMoney(p.getMoney() + (8*m));
        } else if (index == 3) {
            p.setMoney(p.getMoney() + (2*m));
        } else if (index == 4) {
            p.setMoney(p.getMoney() - 4*m);
        } else if (index == 5) {
            p.setFoodCount(p.getFoodCount()/2);
        } else {
            p.setFoodCount(p.getFoodCount() - 6*m);
        }
    }

    @FXML
    public void okayButtonHandle(ActionEvent event) {
        ((Stage) okay.getScene().getWindow()).close();
        GameManager.updateCurrentScore();
        MapScreen.updateResources();
    }

    @FXML
    private void handleTreasure(MouseEvent event) {
        treasureImage.setImage(new Image("MULE/View/Images/openingChest.gif"));
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1.5), treasureImage);
        translateTransition.setCycleCount(1);
        translateTransition.play();
        File file = new File("M4/music/walkingCatMule.gif");
        if (!file.exists()) {
            System.out.println("File does not exist");
        }
//        URL resource = getClass().getResource("M4/music/openChest.wav");
//        Paths.get("M4/music/openChest.wav").toUri().toString();
//        AudioClip openingChest = new AudioClip(resource.toString());
//        openingChest.play();
        translateTransition.setOnFinished(event2 -> {
            treasureImage.setImage(new Image("MULE/View/Images/openedChest.gif"));
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../View/randomPopUp.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Random Event For You!");

                stage.initModality(Modality.APPLICATION_MODAL);

                if (lowestScorePerson) {
                    randomNum = (int) (Math.random() * 3);
                } else {
                    randomNum = (int) (Math.random() * 6);
                }
                randomLabel.setText(randomEvents.get(randomNum));

                initiateEvent(randomNum);
                stage.show();
                ((Stage) treasureImage.getScene().getWindow()).close();
            } catch(IOException e) {
                System.out.println("hi!!!!");
            }
        });
        //NEED TO FIGURE OUT A WAY TO DISALLOW THE PLAYER TO RE-DO RANDOM EVENT AGAIN
//        ((Stage)(treasureImage.getScene().getWindow())).close();
    }
}
