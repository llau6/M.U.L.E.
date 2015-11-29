package MULE.Controller;

import MULE.Model.SoundManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
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

    @FXML
    public Button backButton;

    public static Button getsPubButton() {
        return sPubButton;
    }

    private static Button sPubButton;

    private SoundManager soundManager;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            soundManager = new SoundManager(10, 3);
            //soundManager.playSound("town");
            soundManager.playMusic();

            sPubButton = pub_button;
            pub_button.setOnAction((event) -> {
                //load up other FXML document
                try {
                    soundManager.shutdown();
                    Stage stage = (Stage) pub_button.getScene().getWindow();
                    Parent root;
                    //get reference to the button's stage
                    root = FXMLLoader.load(getClass().getResource("../View/Pub.fxml"));
                    //create a new scene with root and set the stage
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("oops");
                }
            });

            store_button.setOnAction((event) -> {
                soundManager.shutdown();
                //load up other FXML document
                try {
                    Stage stage = (Stage) store_button.getScene().getWindow();
                    Parent root;
                    //get reference to the button's stage
                    root = FXMLLoader.load(getClass().getResource("../View/Store.fxml"));
                    //create a new scene with root and set the stage
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("oops");
                }
            });

            backButton.setOnAction((event) -> {
                soundManager.shutdown();
                MapScreen.soundManager.playMusic();
                //load up other FXML document
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            });

        } catch (MalformedURLException ex) {
            System.out.println("sound error");
        }



    }

    @FXML
    private void handleWampusAction(ActionEvent event) throws IOException {
        soundManager.shutdown();
        Stage stage = (Stage) wampusButton.getScene().getWindow();
        Parent root;
        //get reference to the button's stage
        root = FXMLLoader.load(getClass().getResource("../View/WampusGrounds.fxml"));
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}