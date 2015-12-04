package MULE.Controller;

import MULE.Model.GameManager;
import MULE.Model.Player;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by SeYeon on 12/4/2015.
 */

public class RRoundPopUp {
    @FXML
    ImageView pBall1;
    @FXML
    ImageView pBall2;
    @FXML
    ImageView pBall3;
    @FXML
    Label playerLabel;
    private boolean alreadyClicked;
    private static Player curPlayer;
    private static Player scurPlayer;
    private static Queue<Player> players;
    private static int numCalled = 0;

    public void initialize() {
        if (numCalled == 0) {
            players = new LinkedList<>();
            for (Player player : GameManager.getPlayersQueue()) {
                players.add(player);
            }
        }
        alreadyClicked = false;
        curPlayer = players.remove();

        playerLabel.setText("Player " + curPlayer.getName());
        numCalled++;
    }
    public void mouseOver(MouseEvent mouseEvent) {
        ((ImageView)mouseEvent.getSource()).setImage(new Image("MULE/View/Images/wigglyBall.gif"));
    }
    public void mouseExited(MouseEvent mouseEvent) {
        if (!alreadyClicked) {
            ((ImageView) mouseEvent.getSource()).setImage(new Image("MULE/View/Images/sitBall.png"));
        }
    }
    public void handleClick(MouseEvent mouseEvent) {
        if (!alreadyClicked) {
            ImageView pBall = (ImageView) mouseEvent.getSource();
            scurPlayer = curPlayer;
            pBall.setImage(new Image("MULE/View/Images/openPBall.gif"));
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1.3), pBall);
            translateTransition.setCycleCount(1);
            translateTransition.play();
            translateTransition.setOnFinished(event -> {
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("../View/RRoundMessagePop.fxml"));
                    stage.setScene(new Scene(root));
                    stage.setTitle("Fun Tax!");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("Could not open round random events message.");
                    e.printStackTrace();
                }
            });
        }
        alreadyClicked = true;
        if (numCalled >= GameManager.getPlayerNum()) {
            numCalled = 0;
            ((Stage)pBall1.getScene().getWindow()).close();
        } else {
            initialize();
        }
    }

    public static Player getScurPlayer() {
        return scurPlayer;
    }
}
