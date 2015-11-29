package MULE.Controller;

import MULE.Model.GameManager;
import MULE.Model.Player;
import MULE.Model.Race;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Lily on 11/8/2015.
 */
public class PlayerScreen implements Initializable {
    @FXML
    private Button submitButton;
    @FXML
    private ComboBox<Race> raceCombo;
    @FXML
    private ComboBox<String> colorCombo;
    @FXML
    private ImageView playerImg;
    @FXML
    private Label numberLabel;
    @FXML
    private Label requireName;
    @FXML
    private Label sameName;
    @FXML
    private Label sameColor;
    @FXML
    private TextField nameText;

    private static String tempRace;
    private static String tempColor;
    private static int iteration = 1;
    private static String playerName;
    private static Color color;
    private static String colorName;
    public static Race raceChosen;
    private ObservableList<Race> comboData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submitButton.setDisable(true);
        numberLabel.setText("Player " + iteration);
        comboData.add(new Race("Human", "MULE/View/Images/HumanGrey.png"));
        comboData.add(new Race("Flapper", "MULE/View/Images/FlapperGrey.png"));
        comboData.add(new Race("Bonzoid", "MULE/View/Images/BonzoidGrey.png"));
        comboData.add(new Race("Ugaite", "MULE/View/Images/UgaiteGrey.png"));
        comboData.add(new Race("Buzzite", "MULE/View/Images/BuzziteGrey.png"));
        comboData.add(new Race("Leggite", "MULE/View/Images/LeggiteGrey.png"));
        comboData.add(new Race("Packer", "MULE/View/Images/PackerGrey.png"));
        comboData.add(new Race("Spheroid", "MULE/View/Images/SpheroidGrey.png"));
        raceCombo.setItems(comboData);

        raceCombo.setConverter(new StringConverter<Race>() {
            @Override
            public String toString(Race object) {
                if (object == null) {
                    return null;
                } else {
                    return object.getName();
                }
            }
            @Override
            public Race fromString(String string) {
                return null;
            }
        });

        raceCombo.setOnAction((event) -> {
            Race selected = raceCombo.getSelectionModel().getSelectedItem();
            tempRace = selected.getName();
            if (tempColor != null) {
                playerImg.setImage(new Image("MULE/View/Images/" + tempRace + tempColor + ".png"));
            } else {
                playerImg.setImage(selected.getImage());
            }
            if (!nameText.getText().equals("")
                    && colorCombo.getSelectionModel().getSelectedItem() != null) {
                submitButton.setDisable(false);
            }
        });

        colorCombo.setOnAction((event) -> {
            String color = colorCombo.getSelectionModel().getSelectedItem();
            tempColor = color;
            if (tempRace != null) {
                playerImg.setImage(new Image("MULE/View/Images/" + tempRace + tempColor + ".png"));

            }
            if (!nameText.getText().equals("")
                    && raceCombo.getSelectionModel().getSelectedItem() != null) {
                submitButton.setDisable(false);
            }
        });
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        Object[] playersArray = GameManager.players.toArray();
        boolean isSameName = false;
        boolean isSameColor = false;
        if (nameText.getText() != null && !nameText.getText().isEmpty()
                && !colorCombo.getSelectionModel().isEmpty() && !raceCombo.getSelectionModel().isEmpty()) {
            for (int i = 0; i < playersArray.length; i++) {
                if (((Player) playersArray[i]).getName().equals(nameText.getText())) {
                    isSameName = true;
                } else if (((Player) playersArray[i]).getColor().equals(javafx.scene.paint.Color.valueOf(colorCombo.getSelectionModel().getSelectedItem()))) {
                    isSameColor = true;
                }
            }
            // Save name, color, and race
            if (iteration == 1 || (!isSameName && !isSameColor)) {
                playerName = nameText.getText();
                color = javafx.scene.paint.Color.valueOf(colorCombo.getSelectionModel().getSelectedItem());
                colorName = colorCombo.getSelectionModel().getSelectedItem();
                tempColor = null;
                tempRace = null;
                raceChosen = raceCombo.getSelectionModel().getSelectedItem();
                Player player = new Player(playerName, raceChosen, color);
                GameManager.players.add(player);
                GameManager.getOrderedPlayers().add(player);
                if (iteration < GameManager.getPlayerNum()) {
                    iteration++;
                    FXMLLoader loader = new FXMLLoader();
                    stage = (Stage) submitButton.getScene().getWindow();
                    root = loader.load(getClass().getResource("../View/playerScreen.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    FXMLLoader loader = new FXMLLoader();
                    stage = (Stage) submitButton.getScene().getWindow();
                    root = loader.load(getClass().getResource("../View/MapScreen.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } else {
                if (isSameName) {
                    sameName.setOpacity(1.0);
                    sameColor.setOpacity(0);
                } else if (isSameColor) {
                    sameName.setOpacity(0.0);
                    sameColor.setOpacity(1.0);
                }
            }
        } else {
            requireName.setOpacity(1.0);
        }
    }
}
