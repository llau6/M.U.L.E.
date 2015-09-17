package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by SeYeon on 9/12/2015.
 */
public class Controller2 implements Initializable{
    @FXML
    Label playerName;

    @FXML
    Label colorLabel;

    @FXML
    Label raceLabel;

    @FXML
    ImageView raceView;

    @FXML
    GridPane gridPane2;
    @FXML
    Pane pane3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        gridPane2.getStylesheets().add(getClass().getResource("muleStyle.css").toExternalForm());
        pane3.getStyleClass().add("pane3");

        playerName.setText(Controller.playerName);
        colorLabel.setText(Controller.color);
        raceLabel.setText(Controller.raceChosen.getName());
        raceView.setImage(Controller.raceChosen.getImage());

    }

    @Override
    public String toString() {
        return "I exist";
    }
}
