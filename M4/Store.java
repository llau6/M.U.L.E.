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
        //initialize labels
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
        //reference for timer to close store
        sCompleteButton = completeButton;

        if (StoreManager.boughtMule) {
            muleCombo.setDisable(true);
        } else {
            muleCombo.setDisable(false);
        }
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
                String plusSign = "";
                boolean validEntry = false;
                int multiplier = 1;
                int newNet = 0;
                int oldNet = Integer.parseInt(netGainLabel.getText());
                int storeNum = Integer.parseInt(storeItem.getText());
                if (StoreManager.buy) {
                    multiplier = -1;
                }
                //checks to see if sufficient funds/quantities in store/player inventory
                //sets money label, item quantity label, and player inventory label
                if (storeItem.getId().equals("enterFood") && ((StoreManager.buy && Integer.parseInt(foodQuantityLabel.getText()) - 1 >= 0)
                        || (StoreManager.sell && Integer.parseInt(newFood.getText()) - 1 >= 0))
                        && Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.foodPrice >= 0) {
                    foodQuantityLabel.setText(String.valueOf(Integer.parseInt(foodQuantityLabel.getText()) + multiplier));
                    newFood.setText(String.valueOf((Integer.parseInt(newFood.getText())) - multiplier));
                    newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.foodPrice));
                    newNet = oldNet + multiplier * StoreManager.foodPrice;
                    validEntry = true;
                } else if (storeItem.getId().equals("enterEnergy") && ((StoreManager.buy && Integer.parseInt(energyQuantityLabel.getText()) - 1 >= 0)
                        ||  (StoreManager.sell && Integer.parseInt(newEnergy.getText()) - 1 >= 0))
                        && Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.foodPrice >= 0) {
                    energyQuantityLabel.setText(String.valueOf(Integer.parseInt(energyQuantityLabel.getText()) + multiplier));
                    newEnergy.setText(String.valueOf((Integer.parseInt(newEnergy.getText())) - multiplier));
                    newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.energyPrice));
                    newNet = oldNet + multiplier * StoreManager.energyPrice;
                    validEntry = true;
                } else if (storeItem.getId().equals("enterOre") && ((StoreManager.buy && Integer.parseInt(oreQuantityLabel.getText()) - 1 >= 0)
                        ||  (StoreManager.sell && Integer.parseInt(newOre.getText()) - 1 >= 0))
                        && Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.foodPrice >= 0) {
                    oreQuantityLabel.setText(String.valueOf(Integer.parseInt(oreQuantityLabel.getText()) + multiplier));
                    newOre.setText(String.valueOf((Integer.parseInt(newOre.getText())) - multiplier));
                    newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.orePrice));
                    newNet = oldNet + multiplier * StoreManager.orePrice;
                    validEntry = true;
                }
                //sets netGain label if there are sufficient funds/quantities in store/player inventory
                if (validEntry) {
                    if (newNet > 0) {
                        plusSign = "+";
                        netGainLabel.setTextFill(Color.GREEN);
                    } else if (newNet == 0) {
                        netGainLabel.setTextFill(Color.BLACK);
                    } else {
                        netGainLabel.setTextFill(Color.RED);
                    }
                    netGainLabel.setText(plusSign + newNet);
                    storeItem.setText("" + (storeNum + 1));
                }
            });

            minusButton.setOnAction((event) -> {
                String plusSign = "";
                int multiplier = -1;
                int newNet = 0;
                int oldNet = Integer.parseInt(netGainLabel.getText());
                int storeNum = Integer.parseInt(storeItem.getText());
                if (StoreManager.buy) {
                    multiplier = 1;
                }
                //executes if store quantity is greater than 0
                //sets money label, netGain label, store quantity label, and player inventory quantity label
                if (storeNum > 0) {
                    if (storeItem.getId().equals("enterFood")) {
                        foodQuantityLabel.setText(String.valueOf(Integer.parseInt(foodQuantityLabel.getText()) + multiplier));
                        newFood.setText(String.valueOf((Integer.parseInt(newFood.getText())) - multiplier));
                        newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.foodPrice));
                        newNet = oldNet + multiplier * StoreManager.foodPrice;
                    } else if (storeItem.getId().equals("enterEnergy")) {
                        energyQuantityLabel.setText(String.valueOf(Integer.parseInt(energyQuantityLabel.getText()) + multiplier));
                        newEnergy.setText(String.valueOf((Integer.parseInt(newEnergy.getText())) - multiplier));
                        newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.energyPrice));
                        newNet = oldNet + multiplier * StoreManager.energyPrice;
                    } else if (storeItem.getId().equals("enterOre")) {
                        oreQuantityLabel.setText(String.valueOf(Integer.parseInt(oreQuantityLabel.getText()) + multiplier));
                        newOre.setText(String.valueOf((Integer.parseInt(newOre.getText())) - multiplier));
                        newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.orePrice));
                        newNet = oldNet + multiplier * StoreManager.orePrice;
                    }
                    if (newNet > 0) {
                        plusSign = "+";
                        netGainLabel.setTextFill(Color.GREEN);
                    } else if (newNet == 0) {
                        netGainLabel.setTextFill(Color.BLACK);
                    } else {
                        netGainLabel.setTextFill(Color.RED);
                    }
                    netGainLabel.setText(plusSign + newNet);
                    storeItem.setText("" + (storeNum - 1));
                }
            });
        }

        muleCombo.setOnAction((event) -> {
            String plusSign = "";
            int netGain;
            String selected = (String) muleCombo.getSelectionModel().getSelectedItem();
            int previous = StoreManager.isPrevMule();
            int mulePrice = StoreManager.calculateMulePrice(selected);
            mulePriceLabel.setText(String.valueOf(mulePrice));
            //selecting the first mule does not require subtracting out previous mule prices
            if (StoreManager.isFirstMule()) {
                previous = 0;
                StoreManager.setFirstMule(false);
            }
            //setting netGain after choosing a Mule
            if (!StoreManager.buy && !StoreManager.sell) {
                netGain = mulePrice;
                StoreManager.setPrevMule(mulePrice);
            } else {
                //netGain has to subtract out the previous Mule price
                netGain = Integer.parseInt(netGainLabel.getText()) - previous + mulePrice;
            }
            //setting color and sign of the netGain depending if neg/pos
            if (netGain > 0) {
                netGainLabel.setTextFill(Color.GREEN);
                plusSign = "+";
            } else if (netGain == 0) {
                netGainLabel.setTextFill(Color.BLACK);
            } else {
                netGainLabel.setTextFill(Color.RED);
            }
            netGainLabel.setText(plusSign + netGain);
            newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) - previous + mulePrice));
            //if there is not enough funds for a mule
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
            StoreManager.almostBought = !selected.equals("(No Mule)");
        });

        buyButton.setOnAction((event) -> {
            StoreManager.buy = true;
            StoreManager.sell = false;
            if (netGainLabel.getText().equals("")) {
                netGainLabel.setTextFill(Color.RED);
                netGainLabel.setText("-0");
            }
            buySellStack.setVisible(false);
        });

        sellButton.setOnAction((event) -> {
            StoreManager.sell = true;
            StoreManager.buy = false;
            if (netGainLabel.getText().equals("")) {
                netGainLabel.setTextFill(Color.GREEN);
                netGainLabel.setText("+0");
            }
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
