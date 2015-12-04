package MULE.Controller;

import MULE.Model.GameManager;
import MULE.Model.SoundManager;
import MULE.Model.StoreManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Lily on 12/3/2015.
 */
public class StoreScreen implements Initializable {
    public Button getCompleteButton() {
        return backButton;
    }

    @FXML
    public Button backButton;

    @FXML
    public Button mulePurchase;

    @FXML
    public Button resourcePurchase;

    @FXML
    public Button resourceSell;

    @FXML
    public Button foodUp;

    @FXML
    public Button foodDown;

    @FXML
    public Button energyUp;

    @FXML
    public Button energyDown;

    @FXML
    public Button oreUp;

    @FXML
    public Button oreDown;

    @FXML
    public Label foodQty;

    @FXML
    public Label energyQty;

    @FXML
    public Label oreQty;

    @FXML
    public Label muleQty;

    @FXML
    public Label enterFood;

    @FXML
    public Label enterEnergy;

    @FXML
    public Label enterOre;

    @FXML
    public Label lowMuleFunds;

    @FXML
    public Label lowResourcesFunds;

    @FXML
    public Label lowPlayerResources;

    @FXML
    public Label lowStoreResources;

    @FXML
    public Label playerLabel;

    @FXML
    public Label foodLabel;

    @FXML
    public Label energyLabel;

    @FXML
    public Label oreLabel;

    @FXML
    public Label happinessLabel;

    @FXML
    public Label moneyLabel;

    @FXML
    private ImageView playerImg;

    @FXML
    private Rectangle foodMule;

    @FXML
    private Rectangle energyMule;

    @FXML
    private Rectangle oreMule;

    @FXML
    private Rectangle happinessMule;

    @FXML
    private Rectangle foodMuleHover;

    @FXML
    private Rectangle energyMuleHover;

    @FXML
    private Rectangle oreMuleHover;

    @FXML
    private Rectangle happinessMuleHover;


    public static Button sCompleteButton;
    public static SoundManager soundManager;
    private static String colorName;
    private static String chosenMule = "";

    public void initialize(URL location, ResourceBundle resources) {
        try {
            soundManager = new SoundManager(5, 7);
            soundManager.playMusic();
            //reference for timer to close store
            sCompleteButton = backButton;
            playerLabel.setText(GameManager.getCurrentPlayer().getName());
            if (("" +  GameManager.getCurrentPlayer().getColor()).equals("0xffa500ff")) {
                colorName = "Orange";
            } else if (("" +  GameManager.getCurrentPlayer().getColor()).equals("0xff0000ff")) {
                colorName = "Red";
            } else if (("" +  GameManager.getCurrentPlayer().getColor()).equals("0x008000ff")) {
                colorName = "Green";
            } else if (("" +  GameManager.getCurrentPlayer().getColor()).equals("0x0000ffff")) {
                colorName = "Blue";
            }
            playerImg.setImage(new Image("MULE/View/Images/" + GameManager.getCurrentPlayer().getRace() + colorName + ".png"));
            moneyLabel.setText(String.valueOf(GameManager.getCurrentPlayer().getMoney()));
            foodLabel.setText(String.valueOf(GameManager.getCurrentPlayer().getFoodCount()));
            energyLabel.setText(String.valueOf(GameManager.getCurrentPlayer().getEnergyCount()));
            oreLabel.setText(String.valueOf(GameManager.getCurrentPlayer().getOreCount()));
            happinessLabel.setText(String.valueOf(GameManager.getCurrentPlayer().getHappinessCount()));
            foodQty.setText(String.valueOf(StoreManager.getFoodQuantity()));
            energyQty.setText(String.valueOf(StoreManager.getEnergyQuantity()));
            oreQty.setText(String.valueOf(StoreManager.getOreQuantity()));
            muleQty.setText(String.valueOf(StoreManager.getMuleQuantity()));
            if (StoreManager.isBoughtMule()) {
                mulePurchase.setDisable(true);
            } else {
                mulePurchase.setDisable(false);
            }

            foodUp.setOnAction((event) -> {
                lowResourcesFunds.setOpacity(0.0);
                lowStoreResources.setOpacity(0.0);
                lowPlayerResources.setOpacity(0.0);

                enterFood.setText(String.valueOf(Integer.parseInt(enterFood.getText()) + 1));
            });

            energyUp.setOnAction((event) -> {
                lowResourcesFunds.setOpacity(0.0);
                lowStoreResources.setOpacity(0.0);
                lowPlayerResources.setOpacity(0.0);

                enterEnergy.setText(String.valueOf(Integer.parseInt(enterEnergy.getText()) + 1));
            });

            oreUp.setOnAction((event) -> {
                lowResourcesFunds.setOpacity(0.0);
                lowStoreResources.setOpacity(0.0);
                lowPlayerResources.setOpacity(0.0);

                enterOre.setText(String.valueOf(Integer.parseInt(enterOre.getText()) + 1));
            });

            foodDown.setOnAction((event) -> {
                lowResourcesFunds.setOpacity(0.0);
                lowStoreResources.setOpacity(0.0);
                lowPlayerResources.setOpacity(0.0);

                int enter;
                if (Integer.parseInt(enterFood.getText()) == 0) {
                    enter = 0;
                } else {
                    enter = Integer.parseInt(enterFood.getText()) - 1;
                }
                enterFood.setText(String.valueOf(enter));
            });

            energyDown.setOnAction((event) -> {
                lowResourcesFunds.setOpacity(0.0);
                lowStoreResources.setOpacity(0.0);
                lowPlayerResources.setOpacity(0.0);

                int enter;
                if (Integer.parseInt(enterEnergy.getText()) == 0) {
                    enter = 0;
                } else {
                    enter = Integer.parseInt(enterEnergy.getText()) - 1;
                }
                enterEnergy.setText(String.valueOf(enter));
            });

            oreDown.setOnAction((event) -> {
                lowResourcesFunds.setOpacity(0.0);
                lowStoreResources.setOpacity(0.0);
                lowPlayerResources.setOpacity(0.0);

                int enter;
                if (Integer.parseInt(enterOre.getText()) == 0) {
                    enter = 0;
                } else {
                    enter = Integer.parseInt(enterOre.getText()) - 1;
                }
                enterOre.setText(String.valueOf(enter));
            });

            resourcePurchase.setOnAction((event) -> {
                int foodNum = Integer.parseInt(enterFood.getText());
                int energyNum = Integer.parseInt(enterEnergy.getText());
                int oreNum = Integer.parseInt(enterOre.getText());
                int foodPrice = foodNum * 30;
                int energyPrice = energyNum * 25;
                int orePrice = oreNum * 50;
                int totalPrice = foodPrice + energyPrice + orePrice;
                if (totalPrice > GameManager.getCurrentPlayer().getMoney()) {
                    lowResourcesFunds.setOpacity(1.0);
                } else if (foodNum > StoreManager.getFoodQuantity()
                        || energyNum > StoreManager.getEnergyQuantity()
                        || oreNum > StoreManager.getOreQuantity()) {
                    lowStoreResources.setOpacity(1.0);
                } else {
                    StoreManager.setFoodQuantity(StoreManager.getFoodQuantity() - foodNum);
                    StoreManager.setEnergyQuantity(StoreManager.getEnergyQuantity() - energyNum);
                    StoreManager.setOreQuantity(StoreManager.getOreQuantity() - oreNum);
                    GameManager.getCurrentPlayer().setFoodCount(GameManager.getCurrentPlayer().getFoodCount() + foodNum);
                    GameManager.getCurrentPlayer().setEnergyCount(GameManager.getCurrentPlayer().getEnergyCount() + energyNum);
                    GameManager.getCurrentPlayer().setOreCount(GameManager.getCurrentPlayer().getOreCount() + oreNum);
                    GameManager.getCurrentPlayer().setMoney(GameManager.getCurrentPlayer().getMoney() - totalPrice);
                    moneyLabel.setText(String.valueOf(GameManager.getCurrentPlayer().getMoney()));
                    foodLabel.setText(String.valueOf(GameManager.getCurrentPlayer().getFoodCount()));
                    energyLabel.setText(String.valueOf(GameManager.getCurrentPlayer().getEnergyCount()));
                    oreLabel.setText(String.valueOf(GameManager.getCurrentPlayer().getOreCount()));
                    foodQty.setText(String.valueOf(StoreManager.getFoodQuantity()));
                    energyQty.setText(String.valueOf(StoreManager.getEnergyQuantity()));
                    oreQty.setText(String.valueOf(StoreManager.getOreQuantity()));
                    enterFood.setText("0");
                    enterEnergy.setText("0");
                    enterOre.setText("0");
                }
            });

            resourceSell.setOnAction((event) -> {
                int foodNum = Integer.parseInt(enterFood.getText());
                int energyNum = Integer.parseInt(enterEnergy.getText());
                int oreNum = Integer.parseInt(enterOre.getText());
                int foodPrice = foodNum * 30;
                int energyPrice = energyNum * 25;
                int orePrice = oreNum * 50;
                int totalPrice = foodPrice + energyPrice + orePrice;
                if (foodNum > GameManager.getCurrentPlayer().getFoodCount()
                        || energyNum > GameManager.getCurrentPlayer().getEnergyCount()
                        || oreNum > GameManager.getCurrentPlayer().getOreCount()) {
                    lowPlayerResources.setOpacity(1.0);
                } else {
                    StoreManager.setFoodQuantity(StoreManager.getFoodQuantity() + foodNum);
                    StoreManager.setEnergyQuantity(StoreManager.getEnergyQuantity() + energyNum);
                    StoreManager.setOreQuantity(StoreManager.getOreQuantity() + oreNum);
                    GameManager.getCurrentPlayer().setFoodCount(GameManager.getCurrentPlayer().getFoodCount() - foodNum);
                    GameManager.getCurrentPlayer().setEnergyCount(GameManager.getCurrentPlayer().getEnergyCount() - energyNum);
                    GameManager.getCurrentPlayer().setOreCount(GameManager.getCurrentPlayer().getOreCount() - oreNum);
                    GameManager.getCurrentPlayer().setMoney(GameManager.getCurrentPlayer().getMoney() + totalPrice);
                    moneyLabel.setText(String.valueOf(GameManager.getCurrentPlayer().getMoney()));
                    foodLabel.setText(String.valueOf(GameManager.getCurrentPlayer().getFoodCount()));
                    energyLabel.setText(String.valueOf(GameManager.getCurrentPlayer().getEnergyCount()));
                    oreLabel.setText(String.valueOf(GameManager.getCurrentPlayer().getOreCount()));
                    foodQty.setText(String.valueOf(StoreManager.getFoodQuantity()));
                    energyQty.setText(String.valueOf(StoreManager.getEnergyQuantity()));
                    oreQty.setText(String.valueOf(StoreManager.getOreQuantity()));
                    enterFood.setText("0");
                    enterEnergy.setText("0");
                    enterOre.setText("0");
                }
            });

            foodMuleHover.setOnMouseEntered((event) -> {
                if (chosenMule.equals("")) {
                    foodMule.setFill(Color.BLACK);
                    foodMule.setStrokeWidth(0.0);
                    foodMule.setOpacity(.2);
                }
            });

            foodMuleHover.setOnMouseExited((event) -> {
                if (chosenMule.equals("")) {
                    foodMule.setOpacity(0.0);
                }
            });

            foodMuleHover.setOnMouseClicked((event) -> {
                if (chosenMule.equals("")) {
                    foodMule.setFill(Color.TRANSPARENT);
                    foodMule.setStroke(Color.BLACK);
                    foodMule.setStrokeWidth(5);
                    foodMule.setOpacity(.2);
                    chosenMule = "Food";
                }
            });

            energyMuleHover.setOnMouseEntered((event) -> {
                if (chosenMule.equals("")) {
                    energyMule.setFill(Color.BLACK);
                    energyMule.setStrokeWidth(0.0);
                    energyMule.setOpacity(.2);
                }
            });

            energyMuleHover.setOnMouseExited((event) -> {
                if (chosenMule.equals("")) {
                    energyMule.setOpacity(0.0);
                }
            });

            energyMuleHover.setOnMouseClicked((event) -> {
                if (chosenMule.equals("")) {
                    energyMule.setFill(Color.TRANSPARENT);
                    energyMule.setStroke(Color.BLACK);
                    energyMule.setStrokeWidth(5);
                    energyMule.setOpacity(.2);
                    chosenMule = "Energy";
                }
            });

            oreMuleHover.setOnMouseEntered((event) -> {
                if (chosenMule.equals("")) {
                    oreMule.setFill(Color.BLACK);
                    oreMule.setStrokeWidth(0.0);
                    oreMule.setOpacity(.2);
                }
            });

            oreMuleHover.setOnMouseExited((event) -> {
                if (chosenMule.equals("")) {
                    oreMule.setOpacity(0.0);
                }
            });

            oreMuleHover.setOnMouseClicked((event) -> {
                if (chosenMule.equals("")) {
                    oreMule.setFill(Color.TRANSPARENT);
                    oreMule.setStroke(Color.BLACK);
                    oreMule.setStrokeWidth(5);
                    oreMule.setOpacity(.2);
                    chosenMule = "Ore";
                }
            });

            happinessMuleHover.setOnMouseEntered((event) -> {
                if (chosenMule.equals("")) {
                    happinessMule.setFill(Color.BLACK);
                    happinessMule.setStrokeWidth(0.0);
                    happinessMule.setOpacity(.2);
                }
            });

            happinessMuleHover.setOnMouseExited((event) -> {
                if (chosenMule.equals("")) {
                    happinessMule.setOpacity(0.0);
                }
            });

            happinessMuleHover.setOnMouseClicked((event) -> {
                if (chosenMule.equals("")) {
                    happinessMule.setFill(Color.TRANSPARENT);
                    happinessMule.setStroke(Color.BLACK);
                    happinessMule.setStrokeWidth(5);
                    happinessMule.setOpacity(.2);
                    chosenMule = "Happiness";
                }
            });

            mulePurchase.setOnAction((event) -> {
                mulePurchase.setDisable(true);
                StoreManager.setAlmostBought(true);
                GameManager.getCurrentPlayer().setCurMule(chosenMule);
                MapScreen.updateResources();
                if (StoreManager.isAlmostBought()) {
                    StoreManager.setBoughtMule(true);
                }
                StoreManager.setMuleQuantity(Integer.parseInt(muleQty.getText()) - 1);
                Image image = new Image("MULE/View/Images/walkingCatMuleCursor.gif");
                GameManager.getMapGrid().setCursor(new ImageCursor(image));
                MapScreen.setClickCount(0);
            });

            backButton.setOnAction((event) -> {
                soundManager.shutdown();
                Town.soundManager.playMusic();
                soundManager.shutdown();
                MapScreen.soundManager.playMusic();
                Stage stage = (Stage) mulePurchase.getScene().getWindow();
                MapScreen.updateResources();
                stage.close();
                chosenMule = "";
            });
        } catch (MalformedURLException ex) {
            System.out.println("Music error in the Store.");
        }
    }
}
