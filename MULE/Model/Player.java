package MULE.Model;

import javafx.scene.paint.Color;

/**
 * Created by jatin1 on 9/18/15.
 */
public class Player implements Comparable<Player> {
    private String name;
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

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.score = 0;
        this.landCount = 0;
        this.oreCount = 0;
        if (GameManager.getDifficulty().equals("Beginner")) {
            foodCount = 8;
            energyCount = 4;
        } else {
            foodCount = 4;
            energyCount = 2;
        }
    }
    public final String getName() { return name; }

    public final Color getColor() { return color; }

    public final int getMoney() { return money; }

    public final int getFoodCount() { return foodCount; }

    public final int getEnergyCount() { return energyCount; }

    public final int getOreCount() { return oreCount; }

    public final int getLandCount() { return landCount; }

    public final int getScore() { return score; }

    public final int[][] getLands() { return lands; }

    public final String getCurMule() { return curMule; }

    public final boolean isClicked() { return clicked; }

    public final void setName(String name) {
        this.name = name;
    }

    public final void setMoney(int money) {
        this.money = money;
    }

    public final void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public final void setEnergyCount(int energyCount) {
        this.energyCount = energyCount;
    }

    public final void setOreCount(int oreCount) {
        this.oreCount = oreCount;
    }

    public final void setLandCount() {
        this.landCount = landCount + 1;
    }

    public final void setScore(int score) {
        this.score = score;
    }

    public final void setCurMule(String curMule) {
        this.curMule = curMule;
    }

    public final void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public final void setLands(String s, int i, int j) {
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

    // ASK JATIN
    public final int compareTo(Player other) {
        return this.score - other.getScore();
    }
}
