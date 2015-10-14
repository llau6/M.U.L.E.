package M4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Lily on 10/1/2015.
 */
public class Store implements Initializable {
    @FXML
    public Button completeButton;

    @FXML
    public Button backButton;

    @FXML
    public Label enterFood;

    @FXML
    public Label enterEnergy;

    @FXML
    public Label enterOre;

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
    public ComboBox muleCombo;

    @FXML
    public Label foodPrice;

    @FXML
    public Label foodQuantityLabel;

    @FXML
    public Label energyPrice;

    @FXML
    public Label energyQuantityLabel;

    @FXML
    public Label orePrice;

    @FXML
    public Label oreQuantityLabel;

    @FXML
    public Label mulePriceLabel;

    @FXML
    public Label muleQuantityLabel;

    @FXML
    public Label netGainLabel;

    @FXML
    private ImageView purchaseMule;

    @FXML
    private Label newMoney;

    @FXML
    private Label newFood;

    @FXML
    private Label newEnergy;

    @FXML
    private Label newOre;

    @FXML
    private StackPane buySellStack;

    @FXML
    private Button buyButton;

    @FXML
    private Button sellButton;

    @FXML
    private Label muleRestriction;

    public static Button sCompleteButton;

    public void initialize(URL location, ResourceBundle resources) {
        if (StoreManager.boughtMule) {
            muleCombo.setDisable(true);
        } else {
            muleCombo.setDisable(false);
        }

        sCompleteButton = completeButton;

        newMoney.setText(String.valueOf(GameManager.currentPlayer.getMoney()));
        newFood.setText(String.valueOf(GameManager.currentPlayer.getFoodCount()));
        newEnergy.setText(String.valueOf(GameManager.currentPlayer.getEnergyCount()));
        newOre.setText(String.valueOf(GameManager.currentPlayer.getOreCount()));
        foodQuantityLabel.setText(String.valueOf(StoreManager.getFoodQuantity()));
        energyQuantityLabel.setText(String.valueOf(StoreManager.getEnergyQuantity()));
        oreQuantityLabel.setText(String.valueOf(StoreManager.getOreQuantity()));
        muleQuantityLabel.setText(String.valueOf(StoreManager.getMuleQuantity()));
        foodPrice.setText(String.valueOf(StoreManager.getFoodPrice()));
        energyPrice.setText(String.valueOf(StoreManager.getEnergyPrice()));
        orePrice.setText(String.valueOf(StoreManager.getOrePrice()));

        //creating and implementing "+" and "-" buttons
        ArrayList<ButtonEntry<ButtPackage, Label>> buttons =  new ArrayList<>();
        buttons.add(new ButtonEntry<>(new ButtPackage(foodUp, foodDown), enterFood));
        buttons.add(new ButtonEntry<>(new ButtPackage(energyUp, energyDown), enterEnergy));
        buttons.add(new ButtonEntry<>(new ButtPackage(oreUp, oreDown), enterOre));
        for (ButtonEntry<ButtPackage, Label> x : buttons) {
            Button plusButton = x.getKey().getPlus();
            Button minusButton = x.getKey().getMinus();
            Label storeItem = x.getValue();
            plusButton.setOnAction((event) -> {
                int multiplier = 1;
                String plusSign = "+";
                boolean restricted = true;
                if (StoreManager.buy) {
                    multiplier = -1;
                    plusSign = "";
                }
                int netCost = Integer.parseInt(netGainLabel.getText());
                if (storeItem.getId().equals("enterFood") && ((StoreManager.buy && Integer.parseInt(foodQuantityLabel.getText()) - 1 >= 0)
                        || (StoreManager.sell && Integer.parseInt(newFood.getText()) - 1 >= 0))
                        && Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.foodPrice >= 0) {
                    restricted = false;
                    newFood.setText(String.valueOf((Integer.parseInt(newFood.getText())) - multiplier));
                    foodQuantityLabel.setText(String.valueOf(Integer.parseInt(foodQuantityLabel.getText()) + multiplier));
                    netGainLabel.setText(plusSign + (netCost + multiplier * StoreManager.foodPrice));
                    newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.foodPrice));
                }
                if (storeItem.getId().equals("enterEnergy") && ((StoreManager.buy && Integer.parseInt(energyQuantityLabel.getText()) - 1 >= 0)
                        ||  (StoreManager.sell && Integer.parseInt(newEnergy.getText()) - 1 >= 0))
                        && Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.foodPrice >= 0) {
                    restricted = false;
                    newEnergy.setText(String.valueOf((Integer.parseInt(newEnergy.getText())) - multiplier));
                    energyQuantityLabel.setText(String.valueOf(Integer.parseInt(energyQuantityLabel.getText()) + multiplier));
                    netGainLabel.setText(plusSign + (netCost + multiplier * StoreManager.energyPrice));
                    newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.energyPrice));
                }
                if (storeItem.getId().equals("enterOre") && ((StoreManager.buy && Integer.parseInt(oreQuantityLabel.getText()) - 1 >= 0)
                        ||  (StoreManager.sell && Integer.parseInt(newOre.getText()) - 1 >= 0))
                        && Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.foodPrice >= 0) {
                    restricted = false;
                    newOre.setText(String.valueOf((Integer.parseInt(newOre.getText())) - multiplier));
                    oreQuantityLabel.setText(String.valueOf(Integer.parseInt(oreQuantityLabel.getText()) + multiplier));
                    netGainLabel.setText(plusSign + (netCost + multiplier * StoreManager.orePrice));
                    newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.orePrice));
                }
                if (!restricted) {
                    int storeNum = Integer.parseInt(storeItem.getText());
                    storeItem.setText("" + (storeNum + 1));
                }
            });

            minusButton.setOnAction((event) -> {
                int multiplier = -1;
                String plusSign = "+";
                if (StoreManager.buy) {
                    multiplier = 1;
                    plusSign = "";
                }
                boolean restricted = true;
                int storeNum = Integer.parseInt(storeItem.getText());
                int netCost = Integer.parseInt(netGainLabel.getText());
                if (storeNum > 0) {
                    if (storeItem.getId().equals("enterFood")) {
                        restricted = false;
                        newFood.setText(String.valueOf((Integer.parseInt(newFood.getText())) - multiplier));
                        foodQuantityLabel.setText(String.valueOf(Integer.parseInt(foodQuantityLabel.getText()) + multiplier));
                        netGainLabel.setText(plusSign + (netCost + multiplier * StoreManager.foodPrice));
                        newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.foodPrice));
                    } else if (storeItem.getId().equals("enterEnergy")) {
                        restricted = false;
                        newEnergy.setText(String.valueOf((Integer.parseInt(newEnergy.getText())) - multiplier));
                        energyQuantityLabel.setText(String.valueOf(Integer.parseInt(energyQuantityLabel.getText()) + multiplier));
                        netGainLabel.setText(plusSign + (netCost + multiplier * StoreManager.energyPrice));
                        newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.energyPrice));
                    } else if (storeItem.getId().equals("enterOre")) {
                        restricted = false;
                        newOre.setText(String.valueOf((Integer.parseInt(newOre.getText())) - multiplier));
                        oreQuantityLabel.setText(String.valueOf(Integer.parseInt(oreQuantityLabel.getText()) + multiplier));
                        netGainLabel.setText(plusSign + (netCost + multiplier * StoreManager.orePrice));
                        newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.orePrice));
                    }
                    if (!restricted) {
                        storeItem.setText("" + (storeNum - 1));
                    }
                }
            });
        }

        muleCombo.setOnAction((event) -> {
            String selected = (String) muleCombo.getSelectionModel().getSelectedItem();
            int previous = StoreManager.prevMule;
            if (StoreManager.firstMule) {
                previous = 0;
            }
            StoreManager.firstMule = false;
            int price = StoreManager.getMulePrice(selected);
            mulePriceLabel.setText(String.valueOf(price));

                //in case no resources are being purchased
                if (!StoreManager.buy) {
                    netGainLabel.setTextFill(Color.RED);
                    netGainLabel.setText("" + price);
                    StoreManager.prevMule = price;
                    System.out.println(StoreManager.prevMule);
                    //if resources are also being purchased
                } else {
                    netGainLabel.setText(String.valueOf(Integer.parseInt(netGainLabel.getText()) - previous + price));

                }
                newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) - previous + price));
                if (Integer.parseInt(newMoney.getText()) < 0) {
                    muleRestriction.setOpacity(1.0);
                    completeButton.setDisable(true);
                } else {
                    muleRestriction.setOpacity(0.0);
                    completeButton.setDisable(false);
                }
            if (selected.equals("Energy")) {
                purchaseMule.setImage(new Image("M4/images/muleEnergy.gif"));
            }
            if (!selected.equals("(No Mule)")) {
                StoreManager.almostBought = true;
            } else {
                StoreManager.almostBought = false;
            }
        });

        buyButton.setOnAction((event) -> {
            StoreManager.buy = true;
            StoreManager.sell = false;
            netGainLabel.setTextFill(Color.RED);
            if (netGainLabel.getText().equals("")) {
                netGainLabel.setText("-0");
            }
            buySellStack.setVisible(false);
        });

        sellButton.setOnAction((event) -> {
            StoreManager.sell = true;
            StoreManager.buy = false;
            netGainLabel.setTextFill(Color.GREEN);
            netGainLabel.setText("+0");
            buySellStack.setVisible(false);
        });

        completeButton.setOnAction((event) -> {
            StoreManager.buy = false;
            StoreManager.sell = false;
            StoreManager.firstMule = true;
            StoreManager.prevMule = 0;
            GameManager.currentPlayer.setMoney(Integer.parseInt(newMoney.getText()));
            GameManager.currentPlayer.setFoodCount(Integer.parseInt(newFood.getText()));
            GameManager.currentPlayer.setEnergyCount(Integer.parseInt(newEnergy.getText()));
            GameManager.currentPlayer.setOreCount(Integer.parseInt(newOre.getText()));
            StoreManager.setFoodQuantity(Integer.parseInt(foodQuantityLabel.getText()));
            StoreManager.setEnergyQuantity(Integer.parseInt(energyQuantityLabel.getText()));
            StoreManager.setOreQuantity(Integer.parseInt(oreQuantityLabel.getText()));
            MapScreen.updateResources();
            if (StoreManager.almostBought) {
                StoreManager.boughtMule = true;
            }
            if (StoreManager.boughtMule) {
                     StoreManager.setMuleQuantity(Integer.parseInt(muleQuantityLabel.getText()) - 1);
                    Image image = new Image("M4/images/walkingCatMuleCursor.gif");
                    GameManager.mapGrid.setCursor(new ImageCursor(image));
                    GameManager.currentPlayer.getMules().add(new Mule((String) muleCombo.getSelectionModel().getSelectedItem()));
                    MapScreen.clickCount = 0;
//                        MapScreen.placeMule(new Mule((String) muleCombo.getSelectionModel().getSelectedItem()));
//                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MapScreen.fxml"));
//                        Parent root = (Parent) fxmlLoader.load();
//                        MapScreen mapScreen = (MapScreen) fxmlLoader.getController();
//                        mapScreen.placeMule(new Mule((String) muleCombo.getSelectionModel().getSelectedItem()));
//                    } catch(IOException e){}
            }
            Stage stage = (Stage) completeButton.getScene().getWindow();
            stage.close();
        });

        backButton.setOnAction((event) -> {
            StoreManager.buy = false;
            StoreManager.sell = false;
            StoreManager.firstMule = true;
            StoreManager.prevMule = 0;
            try {
                Stage stage = (Stage) backButton.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("TownOptions.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.out.println("Oops, something went wrong!");
            }
        });
    }

    private class ButtonEntry<ButtPackage, Label> {
        private ButtPackage b;
        private Label l;

        public ButtonEntry(ButtPackage b, Label l) {
            this.b = b;
            this.l = l;
        }

        public ButtPackage getKey() {
            return b;
        }

        public Label getValue() {
            return l;
        }
    }

    private class ButtPackage {
        private Button plus;
        private Button minus;
        public ButtPackage(Button plus, Button minus) {
            this.plus = plus;
            this.minus = minus;
        }

        public Button getPlus() {
            return plus;
        }

        public Button getMinus() {
            return minus;
        }
    }
}
