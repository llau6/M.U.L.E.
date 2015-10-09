package M4;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Lily on 10/7/2015.
 */
public class StoreManager {
    private static int foodQty;
    private static int energyQty;
    private static int oreQty;
    private static int muleQty;
    private static int foodPrice = 30;
    private static int energyPrice = 25;
    private static int orePrice = 50;
    private static int resourcesPrice;
    private static int mulePrice;
    private static int deltaFood;
    private static int deltaEnergy;
    private static int deltaOre;
    private static int deltaMule;


    public static void initializeStore() {
        if (GameManager.difficulty.equals("Beginner")) {
            foodQty = 16;
            energyQty = 16;
            oreQty = 0;
            muleQty = 25;
        } else {
            foodQty = 8;
            energyQty = 8;
            oreQty = 8;
            muleQty = 14;
        }
    }

    public static int setMulePrice(Object type) {
        mulePrice = 0;
        int base = 100;
        deltaMule++;
        if (type.equals("Food")) {
            mulePrice = base + foodPrice;
        } else if (type.equals("Energy")) {
            mulePrice = base + energyPrice;
        } else if (type.equals("Ore")) {
            mulePrice = base + orePrice;
        } else {
            deltaMule--;
        }
        return mulePrice;
    }

    public static boolean checkSufficiency(boolean buy, boolean sell) {
        if (sell && !buy) {
            if (GameManager.currentPlayer.getFoodCount() - deltaFood < 0
                    || GameManager.currentPlayer.getEnergyCount() - deltaEnergy < 0
                    || GameManager.currentPlayer.getOreCount() - deltaOre < 0) {
                return false;
            }
        } else if (buy && !sell){
            if (foodQty - deltaFood < 0
                    || energyQty - deltaEnergy < 0
                    || oreQty - deltaOre < 0
                    || GameManager.currentPlayer.getMoney() - (mulePrice + resourcesPrice) < 0) {
                return false;
            }
        }
        return true;
    }

    public static int calculateResources() {
        int calcFood = deltaFood * foodPrice;
        int calcEnergy = deltaEnergy * energyPrice;
        int calcOre = deltaOre * orePrice;
        resourcesPrice = calcFood + calcEnergy + calcOre;
        return resourcesPrice;
    }

    public static void completeTransaction(boolean buy, boolean sell) {
        if (sell && !buy) {
            deltaFood *= -1;
            deltaEnergy *= -1;
            deltaOre *= -1;
            mulePrice *= -1;
            resourcesPrice *= -1;
        }
        foodQty -= deltaFood;
        energyQty -= deltaEnergy;
        oreQty -= deltaOre;
        muleQty -= deltaMule;
        GameManager.currentPlayer.setMoney(GameManager.currentPlayer.getMoney() - mulePrice - resourcesPrice);
        GameManager.currentPlayer.setFoodCount(GameManager.currentPlayer.getFoodCount() + deltaFood);
        GameManager.currentPlayer.setEnergyCount(GameManager.currentPlayer.getEnergyCount() + deltaEnergy);
        GameManager.currentPlayer.setOreCount(GameManager.currentPlayer.getOreCount() + deltaOre);
    }

    public static int getFoodQuantity() { return foodQty; }

    public static int getEnergyQuantity() { return energyQty; }

    public static int getOreQuantity() { return oreQty; }

    public static int getMuleQuantity() { return muleQty; }

    public static int getFoodPrice() { return foodPrice; }

    public static int getEnergyPrice() { return energyPrice; }

    public static int getOrePrice() { return orePrice; }

    public static int getResourcesPrice() { return resourcesPrice; }

    public static int getMulePrice() { return mulePrice; }

    public static void setDeltaFood(int f) { deltaFood = f; }

    public static void setDeltaEnergy(int e) { deltaEnergy = e; }

    public static void setDeltaOre(int o) { deltaOre = o; }


}
