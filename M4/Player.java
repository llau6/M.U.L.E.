package M4;

import javafx.scene.paint.Color;

/**
 * Created by jatin1 on 9/18/15.
 */
public class Player implements Comparable<Player> {
    private String name;
    private int foodCount;
    private int energyCount;
    private int money = 1000;
    private int landCount;
    private int oreCount;
    private Race race;
    private Color color;
    private int score;

    public Player(String name, Race race, Color color) {
        this.name = name;
        this.race = race;
        this.color = color;
        if (GameManager.difficulty.equals("Beginner")) {
            foodCount = 8;
            energyCount = 4;
        } else {
            foodCount = 4;
            energyCount = 2;
        }
        this.score = energyCount + foodCount + 500 * landCount + money;
    }

    public String getName() {
        return name;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public int getEnergyCount() {
        return energyCount;
    }

    public int getOreCount() {
        return oreCount;
    }

    public Race getRace() {
        return race;
    }

    public Color getColor() {
        return color;
    }

    public int getMoney() {
        return money;
    }

    public int getLandCount() {
        return landCount;
    }

    public int getScore() {
        return score;
    }

    public void setEnergyCount(int energyCount) {
        this.energyCount = energyCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLandCount(int landCount) {
        this.landCount = landCount;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int compareTo(Player other) {
        return this.score - other.getScore();
    }
}
