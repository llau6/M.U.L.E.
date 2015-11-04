package MULE.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ControllerWampusGrounds implements Initializable {
    @FXML
    public GridPane map;

    private final static boolean[][] minigameMap = new boolean[5][9];
    private int row;
    private int col;

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

    public static Button getsClaimButton() {
        return sClaimButton;
    }

    private static Button sClaimButton;

    public void initialize(URL url, ResourceBundle rb) {
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
                    Button wampusNode = (Button)getNodeFromGridPane(map,i,j);
                    wampusNode.setOnAction(event -> {
                        wampusNode.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("MULE/View/Images/wampus.png"))));
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
                    Button notNode = (Button)getNodeFromGridPane(map,i,j);
                    notNode.setOnAction(event -> {
                        notNode.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("MULE/View/Images/empty.png"))));
                        rowsLabel.setText("Number of Rows Away: " + Math.abs(row - checkI));
                        colsLabel.setText("Number of Columns Away: " + Math.abs(col - checkJ));
                        numTurns++;
                        guessLabel.setText("Number of Shots Left: " + (4 - numTurns));
                        notNode.setDisable(true);
                        if (numTurns >= 4) {
                            conditionLabel.setText("You've failed to catch the Wampus!");
                        }
                    });
                }
            }
        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, final int row, final int col) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button && GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }

    @FXML
    private void handleTownAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/townScreen.fxml"));
        Stage stage = (Stage) townButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void handleClaimAction(ActionEvent event) throws IOException {
        //add code here to give currentPlayer some amount of money    --James

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("townScreen.fxml"));
        AnchorPane nextScreen = (AnchorPane) loader.load();

        Stage stage = (Stage) claimButton.getScene().getWindow();
        Scene scene = new Scene(nextScreen, 840, 640);
        stage.setScene(scene);
        stage.show();
    }
}