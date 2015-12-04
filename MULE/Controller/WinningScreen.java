package MULE.Controller;

import MULE.Model.GameManager;
import MULE.Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.PriorityQueue;
import java.util.ResourceBundle;

/**
 * Created by Lily on 12/4/2015.
 */
public class WinningScreen implements Initializable {
    @FXML
    private Button continueButton;
    @FXML
    private Label playerLabel;
    @FXML
    private Label scoreLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int i = GameManager.getPlayerNum();
        System.out.println(GameManager.getOrderedPlayers().size());
        PriorityQueue<Player> sortedPlayers = new PriorityQueue<>();
        for (Player p: GameManager.getPlayersQueue()) {
            sortedPlayers.add(p);
        }
        Player winner = null;
        while (!sortedPlayers.isEmpty()) {
            winner = sortedPlayers.remove();
        }
        playerLabel.setText(winner.getName());
        scoreLabel.setText(String.valueOf(winner.getScore()));

        continueButton.setOnAction((event) -> {
            Stage stage = (Stage) continueButton.getScene().getWindow();
            stage.close();
            stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../View/endingScreen.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Save Screen");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
}
