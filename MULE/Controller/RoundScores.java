package MULE.Controller;

import MULE.Model.Database;
import MULE.Model.GameManager;
import MULE.Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label roundLabel;
    @FXML
    private Label firstLabel;
    @FXML
    private Label secondLabel;
    @FXML
    private Label thirdLabel;
    @FXML
    private Label fourthLabel;
    @FXML
    private Label firstPlayer;
    @FXML
    private Label secondPlayer;
    @FXML
    private Label thirdPlayer;
    @FXML
    private Label fourthPlayer;
    @FXML
    private Label firstScore;
    @FXML
    private Label secondScore;
    @FXML
    private Label thirdScore;
    @FXML
    private Label fourthScore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roundLabel.setText("End of Round " + (GameManager.getCurrentRoundNumber() - 1));
        int i = GameManager.getPlayerNum();
        for (Player p: GameManager.getOrderedPlayers()) {
            if (i == 1) {
                firstPlayer.setText("Player: " + p.getName());
                firstScore.setText("Score: " + p.getScore());
            } else if (i == 2) {
                secondLabel.setOpacity(1.0);
                secondPlayer.setOpacity(1.0);
                secondScore.setOpacity(1.0);
                secondPlayer.setText("Player: " + p.getName());
                secondScore.setText("Score: " + p.getScore());
            } else if (i == 3) {
                thirdLabel.setOpacity(1.0);
                thirdPlayer.setOpacity(1.0);
                thirdScore.setOpacity(1.0);
                thirdPlayer.setText("Player: " + p.getName());
                thirdScore.setText("Score: " + p.getScore());
            } else if (i == 4) {
                fourthLabel.setOpacity(1.0);
                fourthPlayer.setOpacity(1.0);
                fourthScore.setOpacity(1.0);
                fourthPlayer.setText("Player: " + p.getName());
                fourthScore.setText("Score: " + p.getScore());
            }
            i--;
        }
        saveButton.setDisable(false);
        nextRoundButton.setOnAction((event) -> {
            GameManager.initiateRandom();
            Stage stage = (Stage) nextRoundButton.getScene().getWindow();
            stage.close();
        });

        saveButton.setOnAction((event) -> {
            try {
                Database.saveGameInfo();
            } catch (Exception e) {
                System.out.println(e);
            }
            GameManager.initiateRandom();
            Stage stage = (Stage) nextRoundButton.getScene().getWindow();
            stage.close();
        });
    }
}
