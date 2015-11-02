package MULE.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Lily on 11/2/2015.
 */
public class RoundScores implements Initializable {
    @FXML
    private Button saveButton;

    @FXML
    private Button nextRoundButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nextRoundButton.setOnAction((event) -> {
            Stage stage = (Stage) nextRoundButton.getScene().getWindow();
            stage.close();
        });
    }
}
