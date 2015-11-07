package MULE.Controller;

import MULE.Model.GameManager;
import MULE.Model.StoreManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Pub Conditions
 */
public class Pub implements Initializable {
    private static Button sGambleButton;
    @FXML
    public Button gambleButton;
    @FXML
    public Button backButton;
    @FXML
    public Label youWin;
    @FXML
    public Label bonus;
    private boolean hasGambled;

    /**
     * Gets the gamble button
     * @return gamble button
     */
    public static Button getsGambleButton() {
        return sGambleButton;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sGambleButton = gambleButton;

        gambleButton.setOnAction((event) -> {
                youWin.setOpacity(1.0);
                bonus.setText("$"
                        + GameManager.calculateBonus());
                gambleButton.setDisable(true);
                hasGambled = true;
                backButton.setText("End Turn");
                //So next player can purchase mule
                StoreManager.setBoughtMule(false);
            });

        backButton.setOnAction((event) -> {
                if (hasGambled) {
                    GameManager.getTimer().cancel();
                    Stage stage = (Stage) backButton
                            .getScene().getWindow();
                    stage.close();
                    MapScreen.updateResources();
                } else {
                    try {
                        Stage stage = (Stage) backButton.
                                getScene().getWindow();
                        if (gambleButton.isDisabled()) {
                            stage.close();
                        } else {
                            Parent root = FXMLLoader.load(
                                    getClass().getResource(
                                            "../View/townScreen.fxml"));
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }
                    } catch (IOException e) {
                        System.out.println("Oops, something went wrong!");
                    }
                }
            });
    }
}
