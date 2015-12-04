package MULE.Controller;

//import com.sun.jdi.connect.Connector;
import MULE.Model.GameManager;
import MULE.Model.Player;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Created by SeYeon on 10/23/2015.
 */
public class RRandomEvent implements Initializable {
    @FXML
    private ImageView fancyTreasureImage;
    @FXML
    private ImageView eventImg;
    @FXML
    private Rectangle chestRect;
    private static boolean alreadyClicked;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alreadyClicked = false;

        chestRect.setOnMouseEntered((event) -> {
            eventImg.setImage(new Image("MULE/View/Images/diamondGlow.png"));
        });

        chestRect.setOnMouseExited((event) -> {
            eventImg.setImage(new Image("MULE/View/Images/diamondBG.png"));
        });

        chestRect.setOnMouseClicked((event) -> {
            if (!alreadyClicked) {
                fancyTreasureImage.setImage(new Image("MULE/View/Images/openingFancyTreasure.gif"));
                TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(3), fancyTreasureImage);
                translateTransition.setCycleCount(1);
                translateTransition.play();
                translateTransition.setOnFinished(event2 -> {
                    try {
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("../View/RRoundPopUp.fxml"));
                        stage.setScene(new Scene(root));
                        stage.setTitle("Round Event!");
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                        ((Stage) fancyTreasureImage.getScene().getWindow()).close();
                    } catch (IOException e) {
                        System.out.println("Could not open random events message.");
                        e.printStackTrace();
                    }
                });
                alreadyClicked = true;
            }
        });
    }
}
