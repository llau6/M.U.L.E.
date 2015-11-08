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
import javafx.scene.image.ImageView;
import javafx.scene.paint.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
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
    private Label requireRace;
    @FXML
    private Label requireColor;
    @FXML
    private Label sameName;
    @FXML
    private Label sameColor;
    @FXML
    private TextField nameText;

    private static int iteration = 1;
    private static String playerName;
    private static Color color;
    public static Race raceChosen;
    private ObservableList<Race> comboData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberLabel.setText("Player " + iteration);
        comboData.add(new Race("Human", "MULE/View/Images/MULE_Humanoid.png"));
        comboData.add(new Race("Flapper", "MULE/View/Images/MULE_Flapper.png"));
        comboData.add(new Race("Bonzoid", "MULE/View/Images/MULE_Bonzoid.png"));
        comboData.add(new Race("Ugaite", "MULE/View/Images/MULE_Mechtron.png"));
        comboData.add(new Race("Buzzite", "MULE/View/Images/MULE_Gollumer.png"));
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
            playerImg.setImage(selected.getImage());
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
            requireRace.setOpacity(1.0);
            requireColor.setOpacity(1.0);
        }
    }
}
