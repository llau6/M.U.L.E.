package M4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by SeYeon on 9/12/2015.
 */
public class MapScreen implements Initializable{

    @FXML
    public static Button townButton;

    @FXML
    public Button nextPlayer;

    @FXML
    public static GridPane map;

    @FXML
    public Label currPlayer;

    @FXML
    public Label money;

    @FXML
    public Label ore;

    @FXML
    public Label energy;

    @FXML
    public Label food;

    @FXML
    public Label score;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GameManager.initializeMap();
        GameManager.initLandSelection(currPlayer, energy, money, ore, food, score);
        nextPlayer.setOnAction((event) -> {
            if (GameManager.currentTurn < GameManager.totalTurnsInitial - 1) {
                GameManager.currentTurn++;
                GameManager.initLandSelection(currPlayer, energy, money, ore, food, score);
            } else {
                try {
                    Stage stage;
                    Parent root;
                    FXMLLoader loader = new FXMLLoader();
                    //get reference to the button's stage
                    stage = (Stage) nextPlayer.getScene().getWindow();
                    //load up other FXML document
                    root = loader.load(getClass().getResource("Map2.fxml"));
                    //create a new scene with root and set the stage
                    Scene scene = new Scene(root, 800, 700);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("Pls");
                }
            }
        });
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("TownOptions.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Town Actions");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
