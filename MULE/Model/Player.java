package MULE.Model;

import javafx.scene.paint.Color;

/**
 * Created by jatin1 on 9/18/15.
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
    public String getName() { return name; }

    public Color getColor() { return color; }

    public int getMoney() { return money; }

    public int getFoodCount() { return foodCount; }

    public int getEnergyCount() { return energyCount; }

    public int getOreCount() { return oreCount; }

    public int getLandCount() { return landCount; }

    public int getScore() { return score; }

    public int[][] getLands() { return lands; }

    public String getCurMule() { return curMule; }

    public boolean isClicked() { return clicked; }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public void setEnergyCount(int energyCount) {
        this.energyCount = energyCount;
    }

    public void setOreCount(int oreCount) {
        this.oreCount = oreCount;
    }

    public void setLandCount() {
        this.landCount = landCount + 1;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setCurMule(String curMule) {
        this.curMule = curMule;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

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

    public int compareTo(Player other) {
        return this.score - other.getScore();
    }
}
