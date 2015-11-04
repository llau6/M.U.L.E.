package MULE.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Load Screen to start the settings
 */
public class LoadScreen implements Initializable {
    @FXML
    private Button newGameButton;

    @FXML
    private Button loadSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newGameButton.setOnAction((event) -> {
            try {
                Stage stage = (Stage) newGameButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../View/config_settings.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println("Oops, something went wrong!");
            }
        });
    }
}
