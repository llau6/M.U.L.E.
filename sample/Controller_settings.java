package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by SeYeon on 9/14/2015.
 */
public class Controller_settings {
    @FXML
    private Button done;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        //get reference to the button's stage
        stage = (Stage) done.getScene().getWindow();
        //load up other FXML document
        root = loader.load(getClass().getResource("sample.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root, 400, 275);
        stage.setScene(scene);
        stage.show();
}

}
