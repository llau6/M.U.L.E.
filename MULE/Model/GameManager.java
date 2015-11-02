package MULE.Model;
import MULE.Controller.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Created by jatin1 on 9/18/15.
 */
public class GameManager {
    public static String difficulty;
    public static Queue<Player> players = new LinkedList<>();
    public static PriorityQueue<Player> orderedPlayers = new PriorityQueue<>();
    public static List<Player> visitedPlayers = new LinkedList<>();
    public static TileType[][] gameMap = new TileType[5][9];
    public static Player currentPlayer;
    public static int totalTurnsInitial;
    public static Timer timer;
    public static int currentTurnNumber = 1;
    public static int currentRoundNumber = 1;
    public static int timerLeft;
    public static boolean isFree = true;
    public static boolean newRound;

    public static GridPane mapGrid;
    public static void initializeMap() {
        StoreManager.initializeStore();

    }

    //initial land selection phase
    public static void initLandSelection(Label currPlayer, Label energy, Label money, Label ore, Label food, Label score, Text countDownText, Button townButton) {
        townButton.setDisable(true);
        totalTurnsInitial--;
        final int[] countDown = {50};
        countDownText.setText("Time left: " + countDown[0]);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        countDown[0]--;
                        countDownText.setText("Time left: " + countDown[0]);
                        timerLeft = countDown[0];

                        if (countDown[0] <= 0) {
                            timer.cancel();
                            countDownText.setText("Out of time!");
                        }
                    }
                });
            }
        }, 1000, 1000); //Every 1 second
        currentPlayer = players.remove();
        //currentPlayer.setScore(currentPlayer.getMoney() + (currentPlayer.getLandCount()*500) +
         //           currentPlayer.getEnergyCount() + currentPlayer.getOreCount() + currentPlayer.getFoodCount());

        currPlayer.setText(currentPlayer.getName());
        energy.setText("" + currentPlayer.getEnergyCount());
        money.setText("" + currentPlayer.getMoney());
        ore.setText("" + currentPlayer.getOreCount());
        food.setText("" + currentPlayer.getFoodCount());
        score.setText("" + currentPlayer.getScore());
        players.add(currentPlayer);
    }
    public static void updateCurrentScore() {
        currentPlayer.setScore(currentPlayer.getMoney() + (currentPlayer.getLandCount()*500)
                + currentPlayer.getEnergyCount() + currentPlayer.getOreCount() + currentPlayer.getFoodCount());
    }

    public static void updateCurrentScore(Player currentPlayer) {
        currentPlayer.setScore(currentPlayer.getMoney() + (currentPlayer.getLandCount()*500)
                + currentPlayer.getEnergyCount() + currentPlayer.getOreCount() + currentPlayer.getFoodCount());
    }
    //initial land selection phase after first two turn
    public static void buyLandSelection(Player prevPlayer, Label currPlayer, Label energy, Label money, Label ore, Label food, Label score, Boolean bought, Text countDownText, Label round, Label roundLabel, Label turnType, Button claimLand, Button skipButton, Button townButton) {
        isFree = false;
        turnType.setText("INITIAL LAND SELECTION");
        claimLand.setDisable(false);
        claimLand.setText("Claim Land!");
        skipButton.setText("Skip Turn");
        roundLabel.setText("Round:");
        round.setText("" + currentRoundNumber);
        final int[] countDown = {50};
        countDownText.setText("Time left: " + countDown[0]);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        countDownText.setText("Time left: " + countDown[0]);
                        countDown[0]--;
                        countDownText.setText("Time left: " + countDown[0]);
                        timerLeft = countDown[0];

                        if (countDown[0] <= 0) {
                            timer.cancel();
                            countDownText.setText("Out of time!");
                            claimLand.setText("Too late!");
                            claimLand.setDisable(true);
                            skipButton.setText("You're done");
                        }
                    }
                });
            }
        }, 1000, 1000); //Every 1 second
        currentPlayer = orderedPlayers.remove();
        //currentPlayer.setScore(currentPlayer.getMoney() + (currentPlayer.getLandCount() * 500) + currentPlayer.getEnergyCount() + currentPlayer.getOreCount() + currentPlayer.getFoodCount());
        currPlayer.setText(currentPlayer.getName());
        energy.setText("" + currentPlayer.getEnergyCount());
        money.setText("" + currentPlayer.getMoney());
        ore.setText("" + currentPlayer.getOreCount());
        food.setText("" + currentPlayer.getFoodCount());
        score.setText("" + currentPlayer.getScore());
        visitedPlayers.add(currentPlayer);
        if (currentTurnNumber == players.size()) {
            currentRoundNumber++;
            //new round
            for (Player p : visitedPlayers) {
                orderedPlayers.add(p);
            }
            visitedPlayers = new LinkedList<>();
            currentTurnNumber = 1;
        } else {
            currentTurnNumber++;
        }
    }

    public static void initiateRandom() {
        int chance = (int) (Math.random() * 100 + 1);
        System.out.println(chance);
        //int chance = 27;
        if (chance <= 27) {
            RandomEvent randomEvent = new RandomEvent();
            randomEvent.initialize();
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(GameManager.class.getResource("../View/randomEvent.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Random Event!!");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch(IOException e) {
                System.out.println("hi!!!!");
            }
        }
    }
    public static void gamePlay(Label currPlayer, Label energy, Label money, Label ore, Label food, Label score, Text countDownText, Label turnType, Label round, Button townButton, Button skipButton) {
        townButton.setDisable(false);
        newRound = false;
        round.setDisable(false);
        skipButton.setText("End Turn");
        turnType.setText("TURN-BASED GAMEPLAY");
        round.setText("" + currentRoundNumber);
        final int[] countDown = {50};
        countDownText.setText("Time left: " + countDown[0]);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        countDownText.setText("Time left: " + countDown[0]);
                        countDown[0]--;
                        countDownText.setText("Time left: " + countDown[0]);
                        timerLeft = countDown[0];

                        if (countDown[0] <= 0) {
                            timer.cancel();
                            countDownText.setText("Out of time!");
                            townButton.setDisable(true);
                            skipButton.setText("You're Done");
                            closeAll();
                        }
                    }
                });
            }
        }, 1000, 1000); //Every 1 second

        initiateRandom();

        currentPlayer = orderedPlayers.remove();
        currPlayer.setText(currentPlayer.getName());
        energy.setText("" + currentPlayer.getEnergyCount());
        money.setText("" + currentPlayer.getMoney());
        ore.setText("" + currentPlayer.getOreCount());
        food.setText("" + currentPlayer.getFoodCount());
        score.setText("" + currentPlayer.getScore());
        visitedPlayers.add(currentPlayer);
        if (currentTurnNumber == players.size()) {
            currentRoundNumber++;
            currentTurnNumber = 1;
            newRound = true;
        } else {
            currentTurnNumber++;
        }
    }

    private static void closeAll() {
        ArrayList<Button> staticButts = new ArrayList<>();
        staticButts.add(Store.sCompleteButton);
        staticButts.add(Pub.sGambleButton);
        staticButts.add(ControllerWampusGrounds.sClaimButton);
        staticButts.add(Town.sPubButton);

        for (Button b: staticButts) {
            try {
                if (b != null) {
                    Stage stage = (Stage) b.getScene().getWindow();
                    stage.close();
                }
            } catch (Exception e) {;
            }
        }

    }
    //For Pub
    //Money Bonus = Round Bonus + random(0, Time Bonus)
    public static int calculateBonus() {
        int moneyBonus = 0;
        int roundBonus = 50;
        int timeBonus = 50;
        //calculate Round Bonus
        //1	   2	3	 4	 5	 6	 7	 8	 9	 10	 11	 12
        //50   50	50	100	100	100	100	150	150	150	150	200
        for (int i = 0; i < currentRoundNumber + 1; i++) {
            if ((i / 4) != (i - 1) / 4) {
                roundBonus += 50;
            }
        }
        //calculate Time Bonus
        //39-50 seconds left : 200
        //26-38 seconds left : 150
        //13-25 seconds left : 100
        //0-12 seconds left : 50
        for (int i = 0; i < timerLeft; i++) {
            if ((i / 13) != (i - 1) / 13) {
                timeBonus += 50;
            }
        }
        //randomize Time Bonus
        timeBonus = ThreadLocalRandom.current().nextInt(0, timeBonus + 1);
        moneyBonus = roundBonus + timeBonus;
        if (moneyBonus > 250) {
            moneyBonus = 250;
        }
        currentPlayer.setMoney(currentPlayer.getMoney() + moneyBonus);
        return moneyBonus;
    }

    public static void updateProduction() {
        orderedPlayers = new PriorityQueue<>();
        for (Player player : visitedPlayers) {
            int[][] lands = player.getLands();
            // Loop through player's land array to see if they have a mule on land
            // [2] = Owns Food Mule
            // [3] = Owns Energy Mule
            // [4] = Owns Ore Mule
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    int landNum = lands[i][j];
                    TileType tileType = TileManager.getTileType(i, j);
                    if (player.getEnergyCount() >= 1) {
                        System.out.println(player.getEnergyCount());
                        if (landNum == 2) {
                            player.setFoodCount(player.getFoodCount() + tileType.getFoodCount());
                            player.setEnergyCount(player.getEnergyCount() - 1);
                        } else if (landNum == 3) {
                            player.setEnergyCount(player.getEnergyCount() + tileType.getEnergyCount());
                            player.setEnergyCount(player.getEnergyCount() - 1);
                        } else if (landNum == 4) {
                            player.setOreCount(player.getOreCount() + tileType.getOreCount());
                            player.setEnergyCount(player.getEnergyCount() - 1);
                        }
                    }
                }
            }
            GameManager.updateCurrentScore(player);
            orderedPlayers.add(player);
        }
        visitedPlayers = new LinkedList<>();
    }
}