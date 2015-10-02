package M4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Lily on 10/1/2015.
 */
public class Store implements Initializable {
    @FXML
    public Button completeButton;

    @FXML
    public Button backButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        backButton.setOnAction((event) -> {
            try {
                Stage stage = (Stage) backButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("TownOptions.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println("Oops, something went wrong!");
            }
        });
    }
}
