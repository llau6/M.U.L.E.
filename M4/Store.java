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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
    public TextField enterFood;

    @FXML
    public TextField enterEnergy;

    @FXML
    public TextField enterOre;

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
    public Label netCostLabel;

    @FXML
    public Label netGainLabel;

    @FXML
    public RadioButton buyRadio;

    @FXML
    public RadioButton sellRadio;
    @FXML
    private ImageView purchaseMule;

    private boolean boughtMule;

//    @FXML
//    public Button tryAgainButton;

    public void initialize(URL location, ResourceBundle resources) {

        foodQuantityLabel.setText(String.valueOf(StoreManager.getFoodQuantity()));
        energyQuantityLabel.setText(String.valueOf(StoreManager.getEnergyQuantity()));
        oreQuantityLabel.setText(String.valueOf(StoreManager.getOreQuantity()));
        muleQuantityLabel.setText(String.valueOf(StoreManager.getMuleQuantity()));
        foodPrice.setText(String.valueOf(StoreManager.getFoodPrice()));
        energyPrice.setText(String.valueOf(StoreManager.getEnergyPrice()));
        orePrice.setText(String.valueOf(StoreManager.getOrePrice()));
        netCostLabel.setText("-0");
        netGainLabel.setText("+0");


        muleCombo.setOnAction((event) -> {
            StoreManager.setMulePrice(muleCombo.getSelectionModel().getSelectedItem());
            mulePriceLabel.setText(String.valueOf(StoreManager.getMulePrice()));
            if (buyRadio.isDisabled() || !sellRadio.isDisabled()) {
                netCostLabel.setText("-" + String.valueOf(StoreManager.getMulePrice()));
            } else {
                netCostLabel.setText("-" + String.valueOf(StoreManager.getMulePrice() + StoreManager.getResourcesPrice()));
            }
            String selected = (String) muleCombo.getSelectionModel().getSelectedItem();
            if (!selected.equals("(No Mule)")) {
                purchaseMule.setImage(new Image("M4/images/mule"+ muleCombo.getSelectionModel().getSelectedItem() +".gif"));
                boughtMule = true;
            } else {
                purchaseMule.setImage(null);
            }


        });

        sellRadio.setOnAction((event) -> {
            buyRadio.setDisable(!buyRadio.isDisabled());
            if (buyRadio.isDisabled()) {
                StoreManager.setDeltaFood(Integer.parseInt(enterFood.getText()));
                StoreManager.setDeltaEnergy(Integer.parseInt(enterEnergy.getText()));
                StoreManager.setDeltaOre(Integer.parseInt(enterOre.getText()));
                netGainLabel.setText("+" + StoreManager.calculateResources());
            } else {
                netGainLabel.setText("+0");
            }
        });

        buyRadio.setOnAction((event) -> {
            sellRadio.setDisable(!sellRadio.isDisabled());
            if (sellRadio.isDisabled()) {
                StoreManager.setDeltaFood(Integer.parseInt(enterFood.getText()));
                StoreManager.setDeltaEnergy(Integer.parseInt(enterEnergy.getText()));
                StoreManager.setDeltaOre(Integer.parseInt(enterOre.getText()));
                netCostLabel.setText("-" + (StoreManager.calculateResources() + StoreManager.getMulePrice()));
            } else {
                netCostLabel.setText("-" + String.valueOf(StoreManager.getMulePrice()));
            }
        });

        completeButton.setOnAction((event) -> {
            if (StoreManager.checkSufficiency(!buyRadio.isDisabled(), !sellRadio.isDisabled())) {
                StoreManager.completeTransaction(!buyRadio.isDisabled(), !sellRadio.isDisabled());
                Stage stage = (Stage) completeButton.getScene().getWindow();
                stage.close();
                if (boughtMule) {
//                    try {
                        System.out.println(boughtMule);
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
            } else {
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("StoreError.fxml"));
                    stage.setScene(new Scene(root));
                    stage.setTitle("Town Actions");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("oopsZ");
                }
            }
        });

        backButton.setOnAction((event) -> {
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
//
//        tryAgainButton.setOnAction((event) -> {
//            try {
//                Stage stage = (Stage) backButton.getScene().getWindow();
//                Parent root = FXMLLoader.load(getClass().getResource("Store.fxml"));
//                Scene scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//            } catch (IOException e) {
//                System.out.println("Oops, something went wrong!");
//            }
//        });
    }
}
