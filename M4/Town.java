package M4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Lily on 9/30/2015.
 */
public class Town implements Initializable {
    @FXML
    public Button pub_button;

    @FXML
    public Button store_button;

    @FXML
    public Button wampusButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pub_button.setOnAction((event) -> {
            //load up other FXML document
            try {
                Stage stage = (Stage) pub_button.getScene().getWindow();
                Parent root;
                //get reference to the button's stage
                root = FXMLLoader.load(getClass().getResource("Pub.fxml"));
                //create a new scene with root and set the stage
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println("oops");
            }
        });

        store_button.setOnAction((event) -> {
            //load up other FXML document
            try {
                Stage stage = (Stage) store_button.getScene().getWindow();
                Parent root;
                //get reference to the button's stage
                root = FXMLLoader.load(getClass().getResource("Store.fxml"));
                //create a new scene with root and set the stage
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println("oops");
            }
        });
    }

    @FXML
    private void handleWampusAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) wampusButton.getScene().getWindow();
        Parent root;
        //get reference to the button's stage
        root = FXMLLoader.load(getClass().getResource("WampusGrounds.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}