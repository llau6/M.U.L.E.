package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by jatin1 on 9/20/15.
 */
public enum TileType {
    RIVER(4, 2, 0), PLAIN(2, 3, 1), MOUNTAIN1(1, 1, 2), MOUNTAIN2(1, 1, 3), MOUNTAIN3(1, 1, 4);

    private int foodCount;
    private int energyCount;
    private int oreCount;

    TileType(int foodCount, int energyCount, int oreCount) {
        this.foodCount = foodCount;
        this.energyCount = energyCount;
        this.oreCount = oreCount;
    }
}
