package MULE.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Lily on 12/4/2015.
 */
public class EndingScreen implements Initializable {
    @FXML
    private ImageView endingBG;

    @FXML
    private Rectangle playRect;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playRect.setOnMouseEntered((event) -> {
            endingBG.setImage(new Image("MULE/View/Images/endingHover.png"));
        });

        playRect.setOnMouseExited((event) -> {
            endingBG.setImage(new Image("MULE/View/Images/ending.png"));
        });

        playRect.setOnMouseClicked((event) -> {
            Stage stage = (Stage) playRect.getScene().getWindow();
            stage.close();
            stage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../View/SplashScreen.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Save Screen");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }
}
