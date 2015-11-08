package MULE.Controller;

import MULE.Model.Database;
import MULE.Model.GameManager;
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
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Lily on 11/2/2015.
 */
public class LoadScreen implements Initializable {
    @FXML
    private Rectangle startRect;
    @FXML
    private Rectangle loadRect;
    @FXML
    private ImageView loadImg;
    @FXML
    private Button newGameButton;

    @FXML
    private Button loadSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startRect.setOnMouseEntered(event -> {
            Image screen = new Image("MULE/View/Images/loadHover1.png");
            loadImg.setImage(screen);
            loadImg.setPreserveRatio(true);
        });
        startRect.setOnMouseExited(event -> {
            Image screen = new Image("MULE/View/Images/loadScreen.png");
            loadImg.setImage(screen);
            loadImg.setPreserveRatio(true);
        });
        startRect.setOnMouseClicked((event) -> {
            try {
                Stage stage = (Stage) startRect.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../View/configScreen.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println("Oops, something went wrong!");
            }
        });
        loadRect.setOnMouseEntered(event -> {
            Image screen = new Image("MULE/View/Images/loadHover2.png");
            loadImg.setImage(screen);
            loadImg.setPreserveRatio(true);
        });
        loadRect.setOnMouseExited(event -> {
            Image screen = new Image("MULE/View/Images/loadScreen.png");
            loadImg.setImage(screen);
            loadImg.setPreserveRatio(true);
        });
        loadRect.setOnMouseClicked((event) -> {
            try {
                Database.loadGameFromDB();
                System.out.println(GameManager.players);
                System.out.println(GameManager.getCurrentPlayer());
                MapScreen.setIsLoadingFromDB(true);
                Stage stage = (Stage) loadRect.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../View/MapScreen.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
