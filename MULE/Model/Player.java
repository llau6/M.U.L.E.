package MULE.Model;

import javafx.scene.paint.Color;

/**
 * Player conditions
 */
public class Player implements Comparable<Player> {
    private String name;
    private Race race;
    private Color color;
    private int money = 1000;
    private int foodCount;
    private int energyCount;
    private int oreCount;
    private int landCount;
    private int score;
    private String curMule;
    private int[][] lands = new int[5][9];
    private boolean clicked = false;

    /**
     * Player constructor
     * @param name Name of the player
     * @param race Race of the player
     * @param color Color of the player
     */
    public Player(String name, Race race, Color color) {
        this.name = name;
        this.race = race;
        this.color = color;
        this.score = 0;
        this.landCount = 0;
        this.oreCount = 0;
        if (GameManager.difficulty.equals("Beginner")) {
            foodCount = 8;
            energyCount = 4;
        } else {
            foodCount = 4;
            energyCount = 2;
        }
    }

    /**
     * Player constructor
     */
    public Player() {
        this.name = null;
        this.race = null;
        this.color = null;
        this.score = 0;
        this.landCount = 0;
        this.oreCount = 0;
        foodCount = 8;
        energyCount = 4;
    }

    /**
     * Retrieves the name of the player
     * @return Name of player
     */
    public String getName() { return name; }

    /**
     * Retrieves the color of the player
     * @return Color of player
     */
    public Color getColor() { return color; }

    /**
     * Retrieves the money of the player
     * @return Money of player
     */
    public int getMoney() { return money; }

    /**
     * Retrieves the food count of the player
     * @return Food count of player
     */
    public int getFoodCount() { return foodCount; }

    /**
     * Retrieves the energy count of the player
     * @return Energy count of player
     */
    public int getEnergyCount() { return energyCount; }

    /**
     * Retrieves the ore of the player
     * @return Ore of player
     */
    public int getOreCount() { return oreCount; }

    /**
     * Retrieves the Land Count of the player
     * @return Land count of player
     */
    public int getLandCount() { return landCount; }

    /**
     * Retrieves the Score of the player
     * @return Score of player
     */
    public int getScore() { return score; }

    /**
     * Retrieves the lands of the player
     * @return Lands of player
     */
    public int[][] getLands() { return lands; }

    /**
     * Retrieves the current mule of the player
     * @return Current mule of player
     */
    public String getCurMule() { return curMule; }

    /**
     * Retrieves whether is clicked
     * @return boolean condition of clicked
     */
    public boolean isClicked() { return clicked; }

    /**
     * Sets the name of the player
     * @param name of player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the money of the player
     * @param money of player
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Sets the food count of the player
     * @param foodCount of player
     */
    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    /**
     * Sets the energy of the player
     * @param energyCount of player
     */
    public void setEnergyCount(int energyCount) {
        this.energyCount = energyCount;
    }

    /**
     * Sets the ore of the player
     * @param oreCount of player
     */
    public void setOreCount(int oreCount) {
        this.oreCount = oreCount;
    }

    /**
     * Sets the land count of the player
     */
    public void setLandCount() {
        this.landCount = landCount + 1;
    }

    /**
     * Sets the score of the player
     * @param score of player
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets the current mule of the player
     * @param curMule of player
     */
    public void setCurMule(String curMule) {
        this.curMule = curMule;
    }

    /**
     * Sets whether player is clicked
     * @param clicked condition of player
     */
    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    /**
     * Sets the lands of the player
     * @param s set condition
     * @param i row
     * @param j column
     */
    public void setLands(String s, int i, int j) {
        int set = 0;
        if (s.equals("No mule")) {
            set = 1;
        } else if (s.equals("Food")) {
            set = 2;
        } else if (s.equals("Energy")) {
            set = 3;
        } else if (s.equals("Ore")) {
            set = 4;
        }
        this.lands[i][j] = set;
    }

    /**
     * Compares each players' scores
     * @param other Other Player
     */
    public int compareTo(Player other) {
        return this.score - other.getScore();
    }
}
