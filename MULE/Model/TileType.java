package MULE.Model;

/**
 * Created by jatin1 on 9/20/15.
 */
public enum TileType {
    RIVER(4, 2, 0, 0, "River", "MULE/View/Images/River.png"),
    PLAIN(2, 3, 1, 0, "Plains", "MULE/View/Images/Plains.png"),
    MOUNTAIN1(1, 1, 2, 0, "Brown Mountains", "MULE/View/Images/BrownMtn.png"),
    MOUNTAIN2(1, 1, 3, 0, "Blue Mountains", "MULE/View/Images/BlueMtn.png"),
    MOUNTAIN3(1, 1, 4, 0, "Small Mountains", "MULE/View/Images/SmallMtn.png"),
    SHROOMS(1, 0, 0, 10, "Mushrooms", "MULE/View/Images/Shrooms.png");

    private int foodCount;
    private int energyCount;
    private int oreCount;
    private int happinessCount;
    private String name;
    private String src;

    TileType(int foodCount, int energyCount, int oreCount, int shroomCount, String name, String src) {
        this.foodCount = foodCount;
        this.energyCount = energyCount;
        this.oreCount = oreCount;
        this.happinessCount = shroomCount;
        this.name = name;
        this.src = src;
    }

    public String getName() { return name; }

    public int getFoodCount() { return foodCount; }

    public int getEnergyCount() {
        return energyCount;
    }

    public int getOreCount() { return oreCount; }

    public int getHappinessCount() { return happinessCount; }

    public String getSrc() {
        return src;
    }
}