package MULE.Model;

/**
 * Created by jatin1 on 9/20/15.
 */
public enum TileType {
    RIVER(4, 2, 0, "River", "MULE/View/Images/River.png"),
    PLAIN(2, 3, 1, "Plains", "MULE/View/Images/Plain.png"),
    MOUNTAIN1(1, 1, 2, "Brown Mountains", "MULE/View/Images/BrownMtn.png"),
    MOUNTAIN2(1, 1, 3, "Blue Mountains", "MULE/View/Images/BlueMtn.png"),
    MOUNTAIN3(1, 1, 4, "Small Mountains", "MULE/View/Images/SmallMtn.png");

    private int foodCount;
    private int energyCount;
    private int oreCount;
    private String name;
    private String src;

    TileType(int foodCount, int energyCount, int oreCount, String name, String src) {
        this.foodCount = foodCount;
        this.energyCount = energyCount;
        this.oreCount = oreCount;
        this.name = name;
        this.src = src;
    }

    public String getName() { return name; }

    public int getFoodCount() { return foodCount; }

    public int getEnergyCount() {
        return energyCount;
    }

    public int getOreCount() { return oreCount; }

    public String getSrc() {
        return src;
    }
}