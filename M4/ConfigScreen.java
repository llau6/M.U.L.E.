package M4;

import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by SeYeon on 9/14/2015.
 */
public class ConfigScreen implements Initializable {

    @FXML
    public ComboBox difficultyBox;

    @FXML
    private Button done;

    @FXML
    public ComboBox numPlayers;

    public static int playernum;

    @FXML
    private Label requiredNum;
    @FXML
    private Label requiredDifficulty;
    @FXML
    private Label requiredMap;

    @FXML
    private ComboBox mapCombo;

    @FXML
    private Label mule;
    @FXML
    private ImageView muleCat;
//    @FXML
//    private ImageView staticMule;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        done.getStyleClass().add("button");
        animateLabel();
    }

    public void animateLabel() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(5), mule);
        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(3), muleCat);
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(950), mule);

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
        parallelTransition.getChildren().addAll(translateTransition, translateTransition2, rotateTransition);
        parallelTransition.play();

        parallelTransition.setOnFinished(event -> {
            muleCat.setImage(new Image("M4/images/catMule.gif"));
        });
    }
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        GameManager.difficulty = (String) difficultyBox.getSelectionModel().getSelectedItem();
        Object o;
        if (!numPlayers.getSelectionModel().isEmpty() && !difficultyBox.getSelectionModel().isEmpty()
                && !mapCombo.getSelectionModel().isEmpty()) {
            o = numPlayers.getSelectionModel().getSelectedItem();
            playernum = Integer.parseInt((String) o);
            Stage stage;
            Parent root;
            FXMLLoader loader = new FXMLLoader();
            //get reference to the button's stage
            stage = (Stage) done.getScene().getWindow();
            //load up other FXML document
            root = loader.load(getClass().getResource("playerInitScreen.fxml"));
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



    // checking random event; TO DO get rid of this or put this somewhere where you handle randomly pop up thing
    @FXML
    Button randomButton;
    public void randomHandle(ActionEvent event) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("randomEvent.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Random Event!!");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch(IOException e) {
            System.out.println("hi!!!!");
        }
    }
}
