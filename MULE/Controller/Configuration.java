package MULE.Controller;

import MULE.Model.GameManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Lily on 11/8/2015.
 */
public class Configuration implements Initializable {
    @FXML
    private Button submitButton;
    @FXML
    private RadioButton rad1;
    @FXML
    private RadioButton rad2;
    @FXML
    private RadioButton rad3;
    @FXML
    private RadioButton rad4;
    @FXML
    private RadioButton radBeginner;
    @FXML
    private RadioButton radStandard;
    @FXML
    private RadioButton radTournament;
    @FXML
    private RadioButton radSomething1;
    @FXML
    private RadioButton radSomething2;
    @FXML
    private RadioButton radSomething3;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup numGroup = new ToggleGroup();
        rad1.setToggleGroup(numGroup);
        rad2.setToggleGroup(numGroup);
        rad3.setToggleGroup(numGroup);
        rad4.setToggleGroup(numGroup);
        ToggleGroup difficultyGroup = new ToggleGroup();
        radBeginner.setToggleGroup(difficultyGroup);
        radStandard.setToggleGroup(difficultyGroup);
        radTournament.setToggleGroup(difficultyGroup);
        ToggleGroup mapGroup = new ToggleGroup();
        radSomething1.setToggleGroup(mapGroup);
        radSomething2.setToggleGroup(mapGroup);
        radSomething3.setToggleGroup(mapGroup);
        rad1.setSelected(true);
        radBeginner.setSelected(true);
        radSomething1.setSelected(true);
    // Click submit button
        submitButton.setOnMouseClicked((event) -> {
            GameManager.setDifficulty(((RadioButton) difficultyGroup.getSelectedToggle()).getText());
            GameManager.setPlayerNum(Integer.parseInt(((RadioButton) numGroup.getSelectedToggle()).getText()));
            try {
                Stage stage = (Stage) submitButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../View/playerScreen.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println("Oops, something went wrong!");
            }
        });
    }
}
