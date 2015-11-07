package MULE.Model;

/**
 * Type of Tiles in grid
 */
public enum TileType {
    RIVER(4, 2, 0, "River"
            , "MULE/View/Images/River.png"),
    PLAIN(2, 3, 1, "Plains"
            , "MULE/View/Images/Plain.png"),
    MOUNTAIN1(1, 1, 2, "Brown Mountains"
            , "MULE/View/Images/BrownMtn.png"),
    MOUNTAIN2(1, 1, 3, "Blue Mountains"
            , "MULE/View/Images/BlueMtn.png"),
    MOUNTAIN3(1, 1, 4, "Small Mountains"
            , "MULE/View/Images/SmallMtn.png");

    private int foodCount;
    private int energyCount;
    private int oreCount;
    private String name;
    private String src;

    /**
     * TileType constructor
     * @param name of tile
     * @param foodCount food count of tile
     * @param energyCount energy count of tile
     * @param oreCount ore count of tile
     * @param src source of tile
     */
    TileType(int foodCount
            , int energyCount
            , int oreCount
            , String name
            , String src) {
        this.foodCount = foodCount;
        this.energyCount = energyCount;
        this.oreCount = oreCount;
        this.name = name;
        this.src = src;
    }

    /**
     * Retrieves the name of tile
     * @return Name of Tile
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the food count of tile
     * @return Food count of Tile
     */
    public int getFoodCount() {
        return foodCount;
    }

    /**
     * Retrieves the energy count of tile
     * @return Energy Count of Tile
     */
    public int getEnergyCount() {
        return energyCount;
    }

    /**
     * Retrieves the ore of tile
     * @return Ore Count of Tile
     */
    public int getOreCount() {
        return oreCount;
    }

    /**
     * Retrieves the src of tile
     * @return Src of Tile
     */
    public String getSrc() {
        return src;
    }
}