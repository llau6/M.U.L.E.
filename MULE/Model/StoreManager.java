package MULE.Model;

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
    private static int mulePrice;
    private static boolean firstMule = true;

    public static boolean isBuy() {
        return buy;
    }

    public static void setBuy(boolean buy) {
        StoreManager.buy = buy;
    }

    private static boolean buy;

    public static boolean isSell() {
        return sell;
    }

    public static void setSell(boolean sell) {
        StoreManager.sell = sell;
    }

    private static boolean sell;

    public static boolean isAlmostBought() {
        return almostBought;
    }

    public static void setAlmostBought(boolean almostBought) {
        StoreManager.almostBought = almostBought;
    }

    private static boolean almostBought;

    public static boolean isBoughtMule() {
        return boughtMule;
    }

    public static void setBoughtMule(boolean boughtMule) {
        StoreManager.boughtMule = boughtMule;
    }

    private static boolean boughtMule;

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

    public static int calculateMulePrice(String type) {
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

    public static int isPrevMule() {
        return prevMule;
    }

    public static void setPrevMule(int prevMule) {
        StoreManager.prevMule = prevMule;
    }

    protected static int prevMule;

    public static boolean isFirstMule() {
        return firstMule;
    }

    public static void setFirstMule(boolean firstMule) {
        StoreManager.firstMule = firstMule;
    }

}
