package MULE.Controller;

import javafx.event.ActionEvent;
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
 * Town conditions
 */
public class Town implements Initializable {
    @FXML
    public Button pubButton;

    @FXML
    public Button storeButton;

    @FXML
    public Button wampusButton;

    @FXML
    public Button backButton;

    private static Button sPubButton;

    /**
     * Gets the Pub Button
     * @return pub buttom
     */
    public static Button getsPubButton() {
        return sPubButton;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sPubButton = pubButton;
        pubButton.setOnAction((event) -> {
            //load up other FXML document
                try {
                    Stage stage = (Stage) pubButton.getScene().getWindow();
                    Parent root;
                    //get reference to the button's stage
                    root = FXMLLoader.load(getClass().getResource(
                            "../View/Pub.fxml"));
                    //create a new scene with root and set the stage
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("oops");
                }
            });

        storeButton.setOnAction((event) -> {
            //load up other FXML document
                try {
                    Stage stage = (Stage) storeButton.getScene().getWindow();
                    Parent root;
                    //get reference to the button's stage
                    root = FXMLLoader.load(getClass().getResource(
                            "../View/Store.fxml"));
                    //create a new scene with root and set the stage
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("oops");
                }
            });

        backButton.setOnAction((event) -> {
                //load up other FXML document
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            });
    }

    /**
     * Loads the Wampus Ground FXML
     * @param event Intiate upon action
     * @throws IOException Might throw IOException
     */
    @FXML
    private void handleWampusAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) wampusButton.getScene().getWindow();
        Parent root;
        //get reference to the button's stage
        root = FXMLLoader.load(getClass().getResource(
                "../View/WampusGrounds.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}