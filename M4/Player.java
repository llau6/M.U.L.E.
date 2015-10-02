package M4;

import javafx.scene.paint.Color;

import java.util.ArrayList;

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
    private ArrayList<TileType> lands;
    private int round = 4;
    private int time = 36;

    public Player(String name, Race race, Color color) {
        this.name = name;
        this.race = race;
        this.color = color;
        this.lands = new ArrayList<>();
        if (GameManager.difficulty.equals("Beginner")) {
            foodCount = 8;
            energyCount = 4;
        } else {
            foodCount = 4;
            energyCount = 2;
        }
        this.score = 0;
        this.landCount = 0;
        this.oreCount = 0;
    }

    public String getName() { return name; }

    public int getFoodCount() { return foodCount; }

    public int getEnergyCount() { return energyCount; }

    public int getOreCount() {
        return oreCount;
    }

    public Race getRace() {
        return race;
    }

    public Color getColor() { return color; }

    public int getMoney() { return money; }

    public int getLandCount() { return landCount; }

    public int getScore() {return score; }

    public int getRound() { return round; }

    public int getTime() { return time; }

    public ArrayList<TileType> getLands() {
        return lands;
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

    public void setScore(int score) {
        this.score = score;
    }

    public void setLands(ArrayList<TileType> lands) {
        this.lands = lands;
    }

    public void setRound() {
        this.round = round; }

    public void setTime() {
        this.time = time; }

    public int compareTo(Player other) {
        return this.score - other.getScore();
    }
}
