package M4;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayerBuilder;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by SeYeon on 10/23/2015.
 */
public class RandomEvent {
    @FXML
    private ImageView treasureImage;
    private ArrayList<String> randomEvents;

    public void initialize() {
        randomEvents = new ArrayList<>();
        randomEvents.add("YOU JUST RECEIVED A PACKAGE FROM THE GT ALUMNI CONTAINING 3 FOOD AND 2 ENERGY UNITS.");
        randomEvents.add("A WANDERING TECH STUDENT REPAID YOUR HOSPITALITY BY LEAVING TWO BARS OF ORE.");
        randomEvents.add("THE MUSEUM BOUGHT YOUR ANTIQUE PERSONAL COMPUTER FOR $ 8*m.");
        randomEvents.add("YOU FOUND A DEAD MOOSE RAT AND SOLD THE HIDE FOR $2*m.");
        randomEvents.add("FLYING CAT-BUGS ATE THE ROOF OFF YOUR HOUSE. REPAIRS COST $4*m.");
        randomEvents.add("MISCHIEVOUS UGA STUDENTS BROKE INTO YOUR STORAGE SHED AND STOLE HALF YOUR FOOD.");
        randomEvents.add("YOUR SPACE GYPSY INLAWS MADE A MESS OF THE TOWN. IT COST YOU $6*m TO CLEAN IT UP");
    }

    @FXML
    private void handleTreasure(MouseEvent event) {
        treasureImage.setImage(new Image("M4/images/openingChest.gif"));
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
            treasureImage.setImage(new Image("M4/images/openedChest.gif"));
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("muleDestroyed.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Random Event For You!");
                //TO DO!!! change the label text to whatever random event
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch(IOException e) {
                System.out.println("hi!!!!");
            }
        });
        //NEED TO FIGURE OUT A WAY TO DISALLOW THE PLAYER TO RE-DO RANDOM EVENT AGAIN
//        ((Stage)(treasureImage.getScene().getWindow())).close();
    }
}
