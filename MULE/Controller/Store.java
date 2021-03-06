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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Lily on 10/1/2015.
 */
public class Store implements Initializable {
    public Button getCompleteButton() {
        return completeButton;
    }

    @FXML
    private Button completeButton;

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
    public static SoundManager soundManager;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            soundManager = new SoundManager(5, 7);
            soundManager.playMusic();

            newMoney.setText(String.valueOf(GameManager.getCurrentPlayer().getMoney()));
            newFood.setText(String.valueOf(GameManager.getCurrentPlayer().getFoodCount()));
            newEnergy.setText(String.valueOf(GameManager.getCurrentPlayer().getEnergyCount()));
            newOre.setText(String.valueOf(GameManager.getCurrentPlayer().getOreCount()));
            foodQuantityLabel.setText(String.valueOf(StoreManager.getFoodQuantity()));
            energyQuantityLabel.setText(String.valueOf(StoreManager.getEnergyQuantity()));
            oreQuantityLabel.setText(String.valueOf(StoreManager.getOreQuantity()));
            muleQuantityLabel.setText(String.valueOf(StoreManager.getMuleQuantity()));
            foodPrice.setText(String.valueOf(StoreManager.getFoodPrice()));
            energyPrice.setText(String.valueOf(StoreManager.getEnergyPrice()));
            orePrice.setText(String.valueOf(StoreManager.getOrePrice()));
            //reference for timer to close store
            sCompleteButton = completeButton;
            if (StoreManager.isBoughtMule()) {
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
                    if (StoreManager.isBuy()) {
                        multiplier = -1;
                    }
                    //checks to see if sufficient funds/quantities in store/player inventory
                    //sets money label, item quantity label, and player inventory label
                    if (storeItem.getId().equals("enterFood") && ((StoreManager.isBuy() && Integer.parseInt(foodQuantityLabel.getText()) - 1 >= 0)
                            || (StoreManager.isSell() && Integer.parseInt(newFood.getText()) - 1 >= 0))
                            && Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.getFoodPrice() >= 0) {
                        foodQuantityLabel.setText(String.valueOf(Integer.parseInt(foodQuantityLabel.getText()) + multiplier));
                        newFood.setText(String.valueOf((Integer.parseInt(newFood.getText())) - multiplier));
                        newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.getFoodPrice()));
                        newNet = oldNet + multiplier * StoreManager.getFoodPrice();
                        validEntry = true;
                    } else if (storeItem.getId().equals("enterEnergy") && ((StoreManager.isBuy() && Integer.parseInt(energyQuantityLabel.getText()) - 1 >= 0)
                            ||  (StoreManager.isSell() && Integer.parseInt(newEnergy.getText()) - 1 >= 0))
                            && Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.getFoodPrice() >= 0) {
                        energyQuantityLabel.setText(String.valueOf(Integer.parseInt(energyQuantityLabel.getText()) + multiplier));
                        newEnergy.setText(String.valueOf((Integer.parseInt(newEnergy.getText())) - multiplier));
                        newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.getEnergyPrice()));
                        newNet = oldNet + multiplier * StoreManager.getEnergyPrice();
                        validEntry = true;
                    } else if (storeItem.getId().equals("enterOre") && ((StoreManager.isBuy() && Integer.parseInt(oreQuantityLabel.getText()) - 1 >= 0)
                            ||  (StoreManager.isSell() && Integer.parseInt(newOre.getText()) - 1 >= 0))
                            && Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.getFoodPrice() >= 0) {
                        oreQuantityLabel.setText(String.valueOf(Integer.parseInt(oreQuantityLabel.getText()) + multiplier));
                        newOre.setText(String.valueOf((Integer.parseInt(newOre.getText())) - multiplier));
                        newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.getOrePrice()));
                        newNet = oldNet + multiplier * StoreManager.getOrePrice();
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
                    if (StoreManager.isBuy()) {
                        multiplier = 1;
                    }
                    //executes if store quantity is greater than 0
                    //sets money label, netGain label, store quantity label, and player inventory quantity label
                    if (storeNum > 0) {
                        if (storeItem.getId().equals("enterFood")) {
                            foodQuantityLabel.setText(String.valueOf(Integer.parseInt(foodQuantityLabel.getText()) + multiplier));
                            newFood.setText(String.valueOf((Integer.parseInt(newFood.getText())) - multiplier));
                            newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.getFoodPrice()));
                            newNet = oldNet + multiplier * StoreManager.getFoodPrice();
                        } else if (storeItem.getId().equals("enterEnergy")) {
                            energyQuantityLabel.setText(String.valueOf(Integer.parseInt(energyQuantityLabel.getText()) + multiplier));
                            newEnergy.setText(String.valueOf((Integer.parseInt(newEnergy.getText())) - multiplier));
                            newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.getEnergyPrice()));
                            newNet = oldNet + multiplier * StoreManager.getEnergyPrice();
                        } else if (storeItem.getId().equals("enterOre")) {
                            oreQuantityLabel.setText(String.valueOf(Integer.parseInt(oreQuantityLabel.getText()) + multiplier));
                            newOre.setText(String.valueOf((Integer.parseInt(newOre.getText())) - multiplier));
                            newMoney.setText(String.valueOf(Integer.parseInt(newMoney.getText()) + multiplier * StoreManager.getOrePrice()));
                            newNet = oldNet + multiplier * StoreManager.getOrePrice();
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
                if (!StoreManager.isBuy() && !StoreManager.isSell()) {
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
                if (!selected.equals("(No Mule)")) {
                    purchaseMule.setImage(new Image("MULE/View/Images/mule" + muleCombo.getSelectionModel().getSelectedItem() + ".gif"));
                    StoreManager.setAlmostBought(true);
                } else {
                    purchaseMule.setImage(null);
                    StoreManager.setAlmostBought(false);
                }
            });

            buyButton.setOnAction((event) -> {
                StoreManager.setBuy(true);
                StoreManager.setSell(false);
                if (netGainLabel.getText().equals("")) {
                    netGainLabel.setTextFill(Color.RED);
                    netGainLabel.setText("-0");
                }
                buySellStack.setVisible(false);
            });

            sellButton.setOnAction((event) -> {
                StoreManager.setSell(true);
                StoreManager.setBuy(false);
                if (netGainLabel.getText().equals("")) {
                    netGainLabel.setTextFill(Color.GREEN);
                    netGainLabel.setText("+0");
                }
                buySellStack.setVisible(false);
            });

            completeButton.setOnAction((event) -> {
                StoreManager.setBuy(false);
                StoreManager.setSell(false);
                StoreManager.setFirstMule(true);
                StoreManager.setPrevMule(0);
                GameManager.getCurrentPlayer().setMoney(Integer.parseInt(newMoney.getText()));
                GameManager.getCurrentPlayer().setFoodCount(Integer.parseInt(newFood.getText()));
                GameManager.getCurrentPlayer().setEnergyCount(Integer.parseInt(newEnergy.getText()));
                GameManager.getCurrentPlayer().setOreCount(Integer.parseInt(newOre.getText()));
                StoreManager.setFoodQuantity(Integer.parseInt(foodQuantityLabel.getText()));
                StoreManager.setEnergyQuantity(Integer.parseInt(energyQuantityLabel.getText()));
                StoreManager.setOreQuantity(Integer.parseInt(oreQuantityLabel.getText()));
                MapScreen.updateResources();
                if (StoreManager.isAlmostBought()) {
                    StoreManager.setBoughtMule(true);
                }
                if (!muleCombo.isDisabled() && StoreManager.isBoughtMule()) {
                    StoreManager.setMuleQuantity(Integer.parseInt(muleQuantityLabel.getText()) - 1);
                    Image image = new Image("MULE/View/Images/walkingCatMuleCursor.gif");
                    GameManager.getMapGrid().setCursor(new ImageCursor(image));
                    //GameManager.currentPlayer.getMules().add(new Mule((String) muleCombo.getSelectionModel().getSelectedItem()));
                    GameManager.getCurrentPlayer().setCurMule((String) muleCombo.getSelectionModel().getSelectedItem());
                    MapScreen.setClickCount(0);
                }
                soundManager.shutdown();
                MapScreen.soundManager.playMusic();
                Stage stage = (Stage) completeButton.getScene().getWindow();
                stage.close();
            });

            backButton.setOnAction((event) -> {
                soundManager.shutdown();
                Town.soundManager.playMusic();
                StoreManager.setBuy(false);
                StoreManager.setSell(false);
                StoreManager.setFirstMule(true);
                StoreManager.setPrevMule(0);
                try {
                    Stage stage = (Stage) backButton.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("../View/townScreen.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("Oops, something went wrong!");
                }
            });
        } catch (MalformedURLException ex) {
            System.out.println("sound error");
        }
    }

    private static class ButtonEntry<ButtPackage, Label> {
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

    private static class ButtPackage {
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
