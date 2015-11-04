package MULE.Model;

/**
 * Manages Store and transactions
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

    /**
     * Retrieves the buy mule condition
     * @return boolean condition of buy mule
     */
    public static boolean isBuy() {
        return buy;
    }

    /**
     * Sets the buy mule condition
     * @param buy boolean condition of buy mule
     */
    public static void setBuy(boolean buy) {
        StoreManager.buy = buy;
    }

    private static boolean buy;

    /**
     * Retrieves the sell mule condition
     * @return boolean condition of sell mule
     */
    public static boolean isSell() {
        return sell;
    }

    /**
     * Sets the sell mule condition
     * @param sell boolean condition of sell mule
     */
    public static void setSell(boolean sell) {
        StoreManager.sell = sell;
    }

    private static boolean sell;

    /**
     * Retrieves the almost bought mule condition
     * @return boolean condition of bought mule
     */
    public static boolean isAlmostBought() {
        return almostBought;
    }

    /**
     * Sets the almost bought mule condition
     * @param almostBought boolean condition of bought mule
     */
    public static void setAlmostBought(boolean almostBought) {
        StoreManager.almostBought = almostBought;
    }

    private static boolean almostBought;

    /**
     * Retrieves the bought mule condition
     * @return boolean condition of bought mule
     */
    public static boolean isBoughtMule() {
        return boughtMule;
    }

    /**
     * Sets the bought mule condition
     * @param boughtMule boolean condition of bought mule
     */
    public static void setBoughtMule(boolean boughtMule) {
        StoreManager.boughtMule = boughtMule;
    }

    private static boolean boughtMule;

    /**
     * Intializes the store based on default quantity
     */
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

    /**
     * Calculates mule price
     * @param type Type of mule
     * @return mule price
     */
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

    /**
     * Gets the food quantity
     * @return food quantity
     */
    public static int getFoodQuantity() { return foodQty; }

    /**
     * Gets the energy quantity
     * @return energy quantity
     */
    public static int getEnergyQuantity() { return energyQty; }

    /**
     * Gets the ore quantity
     * @return ore quantity
     */
    public static int getOreQuantity() { return oreQty; }

    /**
     * Sets the food quantity
     * @param f Food Quantity
     */
    public static void setFoodQuantity(int f) { foodQty = f; }

    /**
     * Sets the energy quantity
     * @param e Ore Quantity
     */
    public static void setEnergyQuantity(int e) { energyQty = e; }

    /**
     * Sets the ore quantity
     * @param o Ore Quantity
     */
    public static void setOreQuantity(int o) { oreQty = o; }

    /**
     * Sets the mule quantity
     * @param m Mule Quantity
     */
    public static void setMuleQuantity(int m) { muleQty = m; }

    /**
     * Gets the mule quantity
     * @return Mule Quantity
     */
    public static int getMuleQuantity() { return muleQty; }

    /**
     * Gets the food price
     * @return food price
     */
    public static int getFoodPrice() { return foodPrice; }

    /**
     * Gets the energy price
     * @return energy price
     */
    public static int getEnergyPrice() { return energyPrice; }

    /**
     * Gets the ore price
     * @return ore price
     */
    public static int getOrePrice() { return orePrice; }

    /**
     * Gets the previous mule condition
     * @return boolean condition of previous mule
     */
    public static int isPrevMule() {
        return prevMule;
    }

    /**
     * Sets the previous mule
     * @param prevMule Previous Mule
     */
    public static void setPrevMule(int prevMule) {
        StoreManager.prevMule = prevMule;
    }

    protected static int prevMule;

    /**
     * Gets the first mule condition
     * @return boolean condition of first mule
     */
    public static boolean isFirstMule() {
        return firstMule;
    }

    /**
     * Sets the first mule
     * @param firstMule boolean condition of first mule
     */
    public static void setFirstMule(boolean firstMule) {
        StoreManager.firstMule = firstMule;
    }

}
