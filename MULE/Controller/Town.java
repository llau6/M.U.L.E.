package MULE.Controller;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    @FXML
    public Button backButton;

    public static Button getsPubButton() {
        return sPubButton;
    }

    private static Button sPubButton;
    private boolean invoked = false;

    @FXML
    private ImageView character;
    private int up = 0;
    private int left = 0;
    private int down = 0;
    private int right = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sPubButton = pub_button;
        pub_button.setOnAction((event) -> {
        });
    }

    @FXML
    private void onKeyPressedTown (KeyEvent k) {
        int offset = 5;
        System.out.println("x: " + character.getX());
        System.out.println("y: " + character.getY());
        if(k.getCode()== KeyCode.DOWN && isOnRoad()[0]) {
            character.setImage(new Image("MULE/View/Images/walking/frontWalking/frontWalking_" + (down%4+1) + ".png"));
            character.setY(character.getY() + offset);
            down++;
        }
        if (k.getCode() == KeyCode.UP && isOnRoad()[1]) {
            character.setImage(new Image("MULE/View/Images/walking/backWalking/backWalking_" + (up%4+1) + ".png"));
            character.setY(character.getY() - offset);
            up++;
        }
        if (k.getCode() == KeyCode.LEFT && isOnRoad()[2]) {
            character.setImage(new Image("MULE/View/Images/walking/leftWalking/leftWalking_" + (left%4+1) + ".png"));
            character.setX(character.getX() - offset);
            left++;
        }
        if (k.getCode() == KeyCode.RIGHT && isOnRoad()[3]) {
            character.setImage(new Image("MULE/View/Images/walking/rightWalking/rightWalking_" + (right%4+1) + ".png"));
            character.setX(character.getX() + offset);
            right++;
        }
        //back to map screen
        if (character.getY() > 0) {
//            character.setImage(new Image("MULE/View/Images/lightExplode.gif"));
//            RotateTransition transition = new RotateTransition(Duration.millis(1500), character);
//            transition.setCycleCount(1);
//            transition.play();
//            transition.setOnFinished(e -> {
//                closeTown();
//            });
            transitionToOtherPlaces("close");
        }
    }

    private void transitionToOtherPlaces(String toWhere) {
        if (!invoked) {
            invoked = true;
            character.setImage(new Image("MULE/View/Images/lightExplode.gif"));
            RotateTransition transition = new RotateTransition(Duration.millis(1500), character);
            transition.setCycleCount(1);
            transition.play();
            transition.setOnFinished(e -> {
                if (toWhere.equals("close")) {
                    closeTown();
                } else {
                    goToPlaces(toWhere);
                }
            });
        }
    }

    private boolean[] isOnRoad() {
        boolean[] onRoad = new boolean[4];
        //if character is on the straight road with bridge
        if ((character.getY() <= 0 && character.getY() >= -85)) {
            //right side:
            if (character.getX() < 10) {
                onRoad[3] = true;
            }
            //left side:
            if (character.getX() > -10) {
                onRoad[2] = true;
            }
            if (onRoad[2] && onRoad[3]
                    || character.getX() == 10 || character.getX() == -10) {
                onRoad[0] = true;
                onRoad[1] = true;
            }
        } else if (character.getY() > -110 && character.getY() <= -85 ) {
            //above bridge - horizontal road
            if (character.getY() < -85 || (character.getX() < 10 && character.getX() > -10)) {
                onRoad[0] = true;
            }
            if (character.getY() > -105 || character.getX() > 105) {
                onRoad[1] = true;
            }
            //right side:
            if (character.getX() < 125) {
                onRoad[3] = true;
            }
            //left side:
            if (character.getX() > -280) {
                onRoad[2] = true;
            } else {
                transitionToOtherPlaces("WampusGrounds");
            }
        } else if (character.getY() <= -110 && character.getY() > -410) {
            //above bridge - vertical road
            onRoad[0] = true;
            onRoad[1] = true;
            //right side:
            if ((character.getY() < -120 && character.getY() > -150)
                    || (character.getY() < -320 && character.getY() > -350)) {
                //sideways
                if (character.getX() > 130) {
                    if (character.getY() >= -125 || (character.getY() >= -325 && character.getY() < -150)) {
                        onRoad[0] = false;
                    }
                    if (character.getY() <= -345 || (character.getY() <= -145 && character.getY() > -320)) {
                        onRoad[1] = false;
                        System.out.println(onRoad[0]);
                    }
                }
                if (character.getX() < 200) {
                    onRoad[3] = true;
                } else {
                    if (character.getY() < -120 && character.getY() > -150) {
                        transitionToOtherPlaces("Store");
                    } else {
                        transitionToOtherPlaces("Pub");
                    }
                }
            } else {
                if (character.getX() < 125) {
                    onRoad[3] = true;
                }
            }
            //left side:
            if (character.getX() > 115) {
                onRoad[2] = true;
            }
        }

        return onRoad;
    }

    private void closeTown() {
        Stage stage = (Stage) character.getScene().getWindow();
        stage.close();
    }

    private void goToPlaces(String toWhere) {
        //load up other FXML document
        try {
            Stage stage = (Stage) character.getScene().getWindow();
            Parent root;
            //get reference to the button's stage
            root = FXMLLoader.load(getClass().getResource("../View/" + toWhere + ".fxml"));
            //create a new scene with root and set the stage
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("oops");
        }
    }
}