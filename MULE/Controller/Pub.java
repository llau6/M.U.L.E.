package MULE.Controller;

import MULE.Model.GameManager;
import MULE.Model.PubManager;
import MULE.Model.StoreManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Lily on 10/1/2015.
 */
public class Pub implements Initializable {
    @FXML
    private Button back_button;
    @FXML
    private Label youWin;
    @FXML
    private Label bonus;
    @FXML
    private Rectangle gambleRect;
    @FXML
    private ImageView pubImg;

    private boolean hasGambled;
    private static Button sGambleButton;
    public static Button getsGambleButton() {
        return sGambleButton;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sGambleButton = back_button;
        gambleRect.setOnMouseEntered(event -> {
            if (!hasGambled) {
                pubImg.setImage(new Image("MULE/View/Images/pubClick.png"));
            }
        });
        gambleRect.setOnMouseExited(event -> {
            pubImg.setImage(new Image("MULE/View/Images/pubBG.png"));
        });
        gambleRect.setOnMouseClicked((event) -> {
            if (!hasGambled) {
                pubImg.setImage(new Image("MULE/View/Images/pubBG.png"));
                youWin.setOpacity(1.0);
                bonus.setOpacity(1.0);
                bonus.setText("$" + PubManager.calculateBonus());
                back_button.setText("End Turn");
                //So next player can purchase mule
                StoreManager.setBoughtMule(false);
                hasGambled = true;
            }
        });
        back_button.setOnAction((event) -> {
            if (hasGambled) {
                MapScreen.updateResources();
                GameManager.setTownOpen(false);
                MapScreen.updateTown();
                GameManager.getTimer().cancel();
                Stage stage = (Stage) back_button.getScene().getWindow();
                stage.close();
            } else {
                try {
                    Stage stage = (Stage) back_button.getScene().getWindow();
                    if (hasGambled) {
                        stage.close();
                    } else {
                        Parent root = FXMLLoader.load(getClass().getResource("../View/townScreen.fxml"));
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
