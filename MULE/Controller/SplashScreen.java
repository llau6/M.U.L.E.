package MULE.Controller;

import MULE.Model.SoundManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Lily on 11/2/2015.
 */
public class SplashScreen implements Initializable {
    @FXML
    private Rectangle startRect;

    @FXML
    private ImageView splashScreen;

    public static SoundManager soundManager;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //soundManager.playMusic();
        try {
            soundManager = new SoundManager(10, 2);
            //soundManager.playSound("intro");
            soundManager.playMusic();

            startRect.setOnMouseEntered(event -> {
                Image screen = new Image("MULE/View/Images/muleHover.png");
                splashScreen.setImage(screen);
                splashScreen.setPreserveRatio(true);
            });
            startRect.setOnMouseExited(event -> {
                Image screen = new Image("MULE/View/Images/muleStart.png");
                splashScreen.setImage(screen);
                splashScreen.setPreserveRatio(true);
            });
            startRect.setOnMouseClicked((event) -> {
                try {
                    Stage stage = (Stage) startRect.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("../View/LoadScreen.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("Oops, something went wrong!");
                }
            });

        } catch (MalformedURLException ex) {
            System.out.println("sound 1 error");
        }

    }

}