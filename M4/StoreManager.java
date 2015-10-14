package M4;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
    protected static int foodPrice = 30;
    protected static int energyPrice = 25;
    protected static int orePrice = 50;
    private static int mulePrice;
    protected static int prevMule;
    protected static boolean firstMule = true;
    protected static boolean buy;
    protected static boolean sell;
    protected static boolean almostBought;
    protected static boolean boughtMule;

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

    public static int getMulePrice(String type) {
        mulePrice = 0;
        int base = -100;
        if (type.equals("Food")) {
            mulePrice = base - foodPrice;
        } else if (type.equals("Energy")) {
            mulePrice = base - energyPrice;
        } else if (type.equals("Ore")) {
            mulePrice = base - orePrice;
        } else {
            mulePrice = 0;
        }
        prevMule = mulePrice;
        return mulePrice;
    }

    public static int getFoodQuantity() { return foodQty; }

    public static int getEnergyQuantity() { return energyQty; }

    public static int getOreQuantity() { return oreQty; }

    public static void setFoodQuantity(int f) { foodQty = f; }

    public static void setEnergyQuantity(int e) { energyQty = e; }

    public static void setOreQuantity(int o) { oreQty = o; }

    public static void setMuleQuantity(int m) { muleQty = m; }

    public static int getMuleQuantity() { return muleQty; }

    public static int getFoodPrice() { return foodPrice; }

    public static int getEnergyPrice() { return energyPrice; }

    public static int getOrePrice() { return orePrice; }

    public static int getMulePrice() { return mulePrice; }

}
