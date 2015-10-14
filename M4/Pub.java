package M4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Lily on 10/1/2015.
 */
public class Pub implements Initializable {
    @FXML
    public Button gamble_button;

    @FXML
    public Button back_button;

    @FXML
    public Label youWin;

    @FXML
    public Label bonus;

    private boolean hasGambled;

    public static Button sGambleButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sGambleButton = gamble_button;

        gamble_button.setOnAction((event) -> {
            youWin.setOpacity(1.0);
            bonus.setText("$" + GameManager.calculateBonus());
            gamble_button.setDisable(true);
            hasGambled = true;
            back_button.setText("End Turn");
            //so next player can purchase mule
            StoreManager.boughtMule = false;
        });

        back_button.setOnAction((event) -> {
            if (hasGambled) {
                GameManager.timer.cancel();
                Stage stage = (Stage) back_button.getScene().getWindow();
                // do what you have to do
                stage.close();
                MapScreen.gamePlayBuffer();
            } else {
                try {
                    Stage stage = (Stage) back_button.getScene().getWindow();
                    if (gamble_button.isDisabled()) {
                        stage.close();
                    } else {
                        Parent root = FXMLLoader.load(getClass().getResource("TownOptions.fxml"));
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
