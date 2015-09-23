package M4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        done.getStyleClass().add("button");
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
}
