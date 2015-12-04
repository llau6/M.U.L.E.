package MULE.Model;

import javafx.scene.paint.Color;

/**
 * Created by jatin1 on 9/18/15.
 */
public class Player implements Comparable<Player> {
    private String name;
    private Color color;
    private Race race;
    private int money;
    private int foodCount;
    private int energyCount;
    private int oreCount;
    private int happinessCount;
    private int landCount;
    private int score;
    private String curMule;
    private int[][] lands = new int[5][9];
    private boolean clicked = false;

    public Player(String name, Race race, Color color) {
        this.name = name;
        this.color = color;
        this.race = race;
        this.score = 0;
        this.landCount = 0;
        this.oreCount = 0;
        this.happinessCount = 0;
        if (GameManager.getDifficulty().equals("Beginner")) {
            foodCount = 8;
            energyCount = 4;
        } else {
            foodCount = 4;
            energyCount = 2;
        }
        if (race.toString().equals("Human")) {
            this.money = 600;
        } else if (race.toString().equals("Flapper")) {
            this.money = 1600;
        } else if (race.toString().equals("Leggite")) {
            this.money = 1700;
        } else if (race.toString().equals("Packer")) {
            this.money = 900;
        } else if (race.toString().equals("Spheroid")) {
            this.money = 300;
        } else {
            this.money = 1000;
        }
    }
    public final String getName() { return name; }

    public final Color getColor() { return color; }

    public final int getMoney() { return money; }

    public final int getFoodCount() { return foodCount; }

    public final int getEnergyCount() { return energyCount; }

    public final int getOreCount() { return oreCount; }

    public final int getHappinessCount() { return happinessCount; }

    public final int getLandCount() { return landCount; }

    public final Race getRace() {
        return this.race;
    }

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

    public final void setHappinessCount(int shroomCount) {
        this.happinessCount = shroomCount;
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

    public final void setLands(int[][] lands) {
        this.lands = lands;
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
        } else if (s.equals("Happiness")) {
            set = 5;
        }
        this.lands[i][j] = set;
    }

    // ASK JATIN
    public final int compareTo(Player other) {
        return this.score - other.getScore();
    }
}
