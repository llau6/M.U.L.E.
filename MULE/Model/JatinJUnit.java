package MULE.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jatin1 on 11/11/15.
 */
public class JatinJUnit {
    private static final int TIMEOUT = 200;
    private final int foodPrice = -130;
    private final int energyPrice = -125;
    private final int orePrice = -150;
    private String[] types = {"Ore", "Energy", "Food"};
    private int[] expectedValues = {orePrice, energyPrice, foodPrice};

    @Test (timeout = TIMEOUT)
    public void testSingleMuleAll() throws Exception {
        for (int i = 0; i < types.length; i++) {
            assertEquals(expectedValues[i], StoreManager.calculateMulePrice(types[i]));
        }
    }

    @Test (timeout = TIMEOUT)
    public void testMultipleMules() throws Exception {
        int expectedSum = 0;
        int sum = 0;
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < types.length; i++) {
                expectedSum += expectedValues[i];
                 sum += StoreManager.calculateMulePrice(types[i]);
            }
        }
        assertEquals(expectedSum, sum);
    }

    @Test (timeout = TIMEOUT)
    public void testNullMule() throws Exception {
        assertEquals(0, StoreManager.calculateMulePrice(null));
    }

    @Test (timeout = TIMEOUT)
    public void testInvalidMule() throws Exception {
        assertEquals(0, StoreManager.calculateMulePrice("Bob Waters"));
    }
}