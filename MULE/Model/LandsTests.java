package MULE.Model;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Junit test for setting land condtions correctly
 */
public class LandsTests {
    public static final int TIMEOUT = 200;
    private Player player;
    private int[][] lands;

    @Before
    public void setUp() {
        player = new Player();
        lands = new int[5][9];
    }

    @Test(timeout = TIMEOUT)
    public void testNoMuleLands() {
        player.setLands("No mule", 0, 0);
        lands = player.getLands();
        assertEquals(1, lands[0][0]);

    }

    @Test(timeout = TIMEOUT)
    public void testFoodLands() {
        player.setLands("Food", 0, 0);
        lands = player.getLands();
        assertEquals(lands[0][0], 2);
    }

    @Test(timeout = TIMEOUT)
    public void testEnergyLands() {
        player.setLands("Energy", 0, 0);
        lands = player.getLands();
        assertEquals(lands[0][0], 3);
    }

    @Test(timeout = TIMEOUT)
    public void testOreLands() {
        player.setLands("Ore", 0, 0);
        lands = player.getLands();
        assertEquals(lands[0][0], 4);
    }
}
