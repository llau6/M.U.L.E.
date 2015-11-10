package MULE.Model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Lily on 11/8/2015.
 */
public class PubManager {
    // Money Bonus = Round Bonus + random(0, Time Bonus)
    public static int calculateBonus() {
        int moneyBonus;
        int roundBonus = 50;
        int timeBonus = 50;
        // Calculate Round Bonus
        //1	   2	3	 4	 5	 6	 7	 8	 9	 10	 11	 12
        //50   50	50	100	100	100	100	150	150	150	150	200
        for (int i = 0; i < GameManager.getCurrentRoundNumber() + 1; i++) {
            if ((i / 4) != (i - 1) / 4) {
                roundBonus += 50;
            }
        }
        // Calculate Time Bonus
        //39-50 seconds left : 200
        //26-38 seconds left : 150
        //13-25 seconds left : 100
        //0-12 seconds left : 50
        for (int i = 0; i < GameManager.getTimerLeft(); i++) {
            if ((i / 13) != (i - 1) / 13) {
                timeBonus += 50;
            }
        }
        // Randomize Time Bonus
        timeBonus = ThreadLocalRandom.current().nextInt(0, timeBonus + 1);
        moneyBonus = roundBonus + timeBonus;
        if (moneyBonus > 250) {
            moneyBonus = 250;
        }
        GameManager.getCurrentPlayer().setMoney(GameManager.getCurrentPlayer().getMoney() + moneyBonus);
        return moneyBonus;
    }

}
