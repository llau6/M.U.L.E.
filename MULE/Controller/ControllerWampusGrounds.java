package MULE.Controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import MULE.Model.GameManager;
import MULE.Model.SoundManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerWampusGrounds implements Initializable {
    @FXML
    public GridPane map;

    public static boolean[][] minigameMap = new boolean[5][9];
    public static int row;
    public static int col;

    @FXML
    private Label rowsLabel;
    @FXML
    private Label colsLabel;
    @FXML
    private Label guessLabel;
    @FXML
    private Label conditionLabel;
    @FXML
    private Button townButton;
    @FXML
    private Button claimButton;

    private int numTurns;

    private static Button sClaimButton;
    private SoundManager soundManager;

    public void initialize(URL url, ResourceBundle rb) {
        try {
            soundManager = new SoundManager(3, 6);
            //soundManager.playSound("hunting");
            soundManager.playMusic();
        } catch (MalformedURLException ex) {
            System.out.println("sound error");
        }
        sClaimButton = claimButton;
        claimButton.setDisable(true);

        Random rand = new Random();

        row = rand.nextInt(5);
        col = rand.nextInt(9);

        minigameMap[row][col] = true;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                int checkI = i;
                int checkJ = j;

                if (i == row && j == col){
                    Button wampusNode = (Button) getButtonFromGridPane(map, i, j);
                    wampusNode.setOnAction(event -> {
                        wampusNode.setGraphic(new ImageView(new Image("MULE/View/Images/wampus.png")));
                        rowsLabel.setText("Number of Rows Away: " + Math.abs(row - checkI));
                        colsLabel.setText("Number of Columns Away: " + Math.abs(col - checkJ));
                        numTurns++;
                        guessLabel.setText("Number of Shots Left: " + (4 - numTurns));
                        if (numTurns <= 4) {
                            conditionLabel.setText("You've caught the Wampus! He gives you money to go away.");
                            claimButton.setDisable(false);
                        }

                        wampusNode.setDisable(true);
                    });
                } else {
                    Button notNode = (Button) getButtonFromGridPane(map, i, j);
                    notNode.setOnAction(event -> {
                        notNode.setGraphic(new ImageView(new Image("MULE/View/Images/empty.png")));
                        rowsLabel.setText("Number of Rows Away: " + Math.abs(row - checkI));
                        colsLabel.setText("Number of Columns Away: " + Math.abs(col - checkJ));
                        numTurns++;
                        guessLabel.setText("Number of Shots Left: " + (4 - numTurns));
                        notNode.setDisable(true);
                        if (numTurns >= 4) {
                            conditionLabel.setText("You've failed to catch the Wampus!");
                            map.setDisable(true);
                        }
                    });
                }
            }
        }
    }

    public static Node getButtonFromGridPane(GridPane gridPane, final int row, final int col) {
        if (gridPane == null) {
            throw new IllegalArgumentException("GridPane is null!");
        }
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("Either row or column is a negative number!");
        }
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button && GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    @FXML
    private void handleTownAction(ActionEvent event) throws IOException {
        MapScreen.updateResources();
        soundManager.shutdown();
        Stage stage = (Stage) claimButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../View/townScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleClaimAction(ActionEvent event) throws IOException {
        //add code here to give currentPlayer some amount of money    --James
        GameManager.getCurrentPlayer().setMoney(GameManager.getCurrentPlayer().getMoney() + 50);
        MapScreen.updateResources();
        soundManager.shutdown();
        Stage stage = (Stage) claimButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../View/townScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static Button getsClaimButton() {
        return sClaimButton;
    }
}