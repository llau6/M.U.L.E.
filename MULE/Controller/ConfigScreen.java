package MULE.Controller;

import MULE.Model.GameManager;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Manages the configuration and selection of players and difficulty screen
 */
public class ConfigScreen implements Initializable {
    @FXML
    private Button done;
    @FXML
    public ComboBox difficultyBox;
    @FXML
    public ComboBox numPlayers;
    @FXML
    private ComboBox mapCombo;
    @FXML
    private ImageView muleCat;
    @FXML
    private Label requiredNum;
    @FXML
    private Label requiredDifficulty;
    @FXML
    private Label requiredMap;
    @FXML
    private Label mule;
    private static int playernum;

    /**
     * Gets the number of players
     * @return number of players
     */
    public static int getPlayernum() {
        return playernum;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        done.getStyleClass().add("button");
        animateLabel();
    }

    /**
     * Animates Map Label
     */
    public void animateLabel() {
        TranslateTransition translateTransition
                = new TranslateTransition(
                Duration.seconds(5), mule);
        TranslateTransition translateTransition2
                = new TranslateTransition(
                Duration.seconds(3), muleCat);
        RotateTransition rotateTransition
                = new RotateTransition(
                Duration.millis(950), mule);

        rotateTransition.setToAngle(40);
        rotateTransition.setFromAngle(-40);
        rotateTransition.setCycleCount(14);
        rotateTransition.setAutoReverse(true);

        // move the label
        translateTransition.setFromX(10);
        translateTransition.setToX(-240);
        translateTransition.setAutoReverse(true);
        translateTransition.setCycleCount(2);

        //move the image
        translateTransition2.setFromX(10);
        translateTransition2.setToX(-360);
        translateTransition2.setAutoReverse(true);
        translateTransition2.setCycleCount(4);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                translateTransition
                , translateTransition2
                , rotateTransition);
        parallelTransition.play();

        parallelTransition.setOnFinished(event -> {
                muleCat.setImage(new Image("MULE/View/Images/catMule.gif"));
    //            muleCat.setOpacity(0);
    //            staticMule.setOpacity(1);
            });
    }

    /**
     * Updates the combo box with the value input by user
     * @param event Button Press Event
     * @throws IOException Exception
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        GameManager.setDifficulty((String) difficultyBox
                .getSelectionModel().getSelectedItem());
        Object o;
        if (!numPlayers.getSelectionModel().isEmpty()
                && !difficultyBox.getSelectionModel().isEmpty()
                && !mapCombo.getSelectionModel().isEmpty()) {
            o = numPlayers.getSelectionModel().getSelectedItem();
            playernum = Integer.parseInt((String) o);
            Stage stage;
            Parent root;
            FXMLLoader loader = new FXMLLoader();
            //get reference to the button's stage
            stage = (Stage) done.getScene().getWindow();
            //load up other FXML document
            root = FXMLLoader.load(getClass().getResource(
                    "../View/playerInitScreen.fxml"));
            //create a new scene with root and set the stage
            Scene scene = new Scene(root, 400, 275);
            stage.setScene(scene);
            stage.show();
        } else {
            requiredNum.setOpacity(1.0);
            requiredDifficulty.setOpacity(1.0);
            requiredMap.setOpacity(1.0);
        }
    }
}
