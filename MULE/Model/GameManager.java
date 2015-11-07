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
 * Intializes the map
 */
public final class GameManager {
    private static int landPoints = 500;
    private static String difficulty;
    public static  Queue<Player> players = new LinkedList<>();
    private static PriorityQueue<Player> orderedPlayers = new PriorityQueue<>();
    private static List<Player> visitedPlayers = new LinkedList<>();
    private static Player currentPlayer;
    private static int totalTurnsInitial;
    private static Timer timer;
    private static int currentTurnNumber = 1;
    private static int currentRoundNumber = 1;
    private static int timerLeft;
    private static boolean isFree = true;
    private static boolean newRound;
    private static GridPane mapGrid;

    /**
     * Private constuctor for Game Manger
     */
    private GameManager() { }

    /**
     * Initialize map
     */
    public static void initializeMap() {
        StoreManager.initializeStore();
    }

    /**
     * Gets the difficulty
     * @return difficulty Difficulty
     */
    public static String getDifficulty() {
        return difficulty;
    }


    /**
     * Sets the difficulty
     * @param difficulty Difficulty
     */
    public static void setDifficulty(String difficulty) {
        GameManager.difficulty = difficulty;
    }

    /**
     * Gets the total turns initial
     * @return Initial Total Turns
     */
    public static int getTotalTurnsInitial() {
        return totalTurnsInitial;
    }

    /**
     * Sets the total turns initial
     * @param totalTurnsInitial Initial Total Turns
     */
    public static void setTotalTurnsInitial(int totalTurnsInitial) {
        GameManager.totalTurnsInitial = totalTurnsInitial;
    }

    /**
     * Gets the timer
     * @return timer
     */
    public static Timer getTimer() {
        return timer;
    }

    /**
     * Sets the timer
     * @param timer Timer of game
     */
    public static void setTimer(Timer timer) {
        GameManager.timer = timer;
    }

    /**
     * Gets the current player
     * @return the current player
     */
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the current player
     * @param currentPlayer current player
     */
    public static void setCurrentPlayer(Player currentPlayer) {
        GameManager.currentPlayer = currentPlayer;
    }

    /**
     * Gets the free condition boolean
     * @return free boolean
     */
    public static boolean isFree() {
        return isFree;
    }

    /**
     * Sets the free condition boolean
     * @param isFree free boolean
     */
    public static void setIsFree(boolean isFree) {
        GameManager.isFree = isFree;
    }

    /**
     * Gets the boolean value new round
     * @return new round boolean value
     */
    public static boolean isNewRound() {
        return newRound;
    }

    /**
     * Sets the new round
     * @param newRound New Round
     */
    public static void setNewRound(boolean newRound) {
        GameManager.newRound = newRound;
    }

    /**
     *
     * Gets the map grid
     * @return Map Grid
     */
    public static GridPane getMapGrid() {
        return mapGrid;
    }

    /**
     * Sets the map grid
     * @param mapGrid Map Grid
     */
    public static void setMapGrid(GridPane mapGrid) {
        GameManager.mapGrid = mapGrid;
    }

    /**
     * Gets the ordered players
     * @return Ordered Players Queue
     */
    public static PriorityQueue<Player> getOrderedPlayers() {
        return orderedPlayers;
    }

    /**
     * Gets the players
     * @return Players Queue
     */
    public static Queue<Player> getPlayersQueue() {
        return players;
    }

    /**
     * Sets the players
     * @param playersQ Players Queue
     */
    public static void setPlayersQueue(Queue<Player> playersQ) {
        players = playersQ;
    }

    /**
     * Sets the ordered players
     * @param orderedPlayers Ordered Players Queue
     */
    public static void setOrderedPlayers(PriorityQueue<Player> orderedPlayers) {
        GameManager.orderedPlayers = orderedPlayers;
    }

    /**
     * Gets the current turn number
     * @return  Current turn Number
     */
    public static int getCurrentTurnNumber() {
        return currentTurnNumber;
    }

    /**
     * Sets the current turn number
     * @param currentTurnNumber Current turn Number
     */
    public static void setCurrentTurnNumber(int currentTurnNumber) {
        GameManager.currentTurnNumber = currentTurnNumber;
    }

    /**
     * Gets the round number
     * @return currentRoundNumber Current Round Number
     */
    public static int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    /**
     * Sets the round number
     * @param currentRoundNumber Current Round Number
     */
    public static void setCurrentRoundNumber(int currentRoundNumber) {
        GameManager.currentRoundNumber = currentRoundNumber;
    }
    /**
     * Intializes the lands with timers
     * @param currPlayer Current Player
     * @param energy Energy Label
     * @param money Money Label
     * @param ore Ore Label
     * @param food Food Label
     * @param score Score Label
     * @param countDownText timer Text
     * @param townButton Button to go town
     */
    //initial land selection phase
    public static void initLandSelection(Label currPlayer
            , Label energy, Label money
            , Label ore, Label food
            , Label score, Text countDownText
            , Button townButton) {
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
        //currentPlayer.setScore(currentPlayer.getMoney()
        // + (currentPlayer.getLandCount()*500) +
        //           currentPlayer.getEnergyCount()
        // + currentPlayer.getOreCount() + currentPlayer.getFoodCount());

        currPlayer.setText(currentPlayer.getName());
        energy.setText("" + currentPlayer.getEnergyCount());
        money.setText("" + currentPlayer.getMoney());
        ore.setText("" + currentPlayer.getOreCount());
        food.setText("" + currentPlayer.getFoodCount());
        score.setText("" + currentPlayer.getScore());
        players.add(currentPlayer);
    }

    /**
     * Updates current score of current player
     */
    public static void updateCurrentScore() {
        currentPlayer.setScore(currentPlayer.getMoney()
                + (currentPlayer.getLandCount()
                * GameManager.landPoints)
                + currentPlayer.getEnergyCount()
                + currentPlayer.getOreCount()
                + currentPlayer.getFoodCount());
    }

    /**
     * Updates current score of the player
     * @param currentPlayer Current Player
     */
    public static void updateCurrentScore(Player currentPlayer) {
        currentPlayer.setScore(currentPlayer.getMoney()
                + (currentPlayer.getLandCount() * landPoints)
                + currentPlayer.getEnergyCount()
                + currentPlayer.getOreCount()
                + currentPlayer.getFoodCount());
    }

    /**
     * Intializes land selection
     * @param prevPlayer Previous Player
     * @param currPlayer Current Player
     * @param energy Energy Label
     * @param money Money Label
     * @param ore Ore Label
     * @param food Food Label
     * @param score Score Label
     * @param bought indicate whether land is already bought
     * @param countDownText timer Text
     * @param round Label indicating what round it is
     * @param roundLabel Round Text Label
     * @param turnType Type of the turn
     * @param claimLand Claim Button
     * @param skipButton Button to skip turn
     * @param townButton Button to go town
     */
    //initial land selection phase after first two turn
    public static void buyLandSelection(Player prevPlayer,
            Label currPlayer, Label energy
            , Label money, Label ore, Label food
            , Label score, Boolean bought, Text countDownText
            , Label round, Label roundLabel, Label turnType
            , Button claimLand, Button skipButton, Button townButton) {
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

    /**
     * Intializes the random events
     */
    public static void initiateRandom() {
        int chance = (int) (Math.random() * 100 + 1);
        //int chance = 27;
        if (chance <= 27) {
            RandomEvent randomEvent = new RandomEvent();
            randomEvent.initialize();
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(
                        GameManager.class.getResource(
                                "../View/randomEvent.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Random Event!!");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch (IOException e) {
                System.out.println("hi!!!!");
            }
        }
    }

    /**
     * Starts the game play
     * @param currPlayer Current Player
     * @param energy Energy Label
     * @param money Money Label
     * @param ore Ore Label
     * @param food Food Label
     * @param score Score Label
     * @param countDownText timer Text
     * @param round Label indicating what round it is
     * @param turnType Type of the turn
     * @param skipButton Button to skip turn
     * @param townButton Button to go town
     */
    public static void gamePlay(Label currPlayer
            , Label energy, Label money, Label ore
            , Label food, Label score, Text countDownText
            , Label turnType, Label round, Button townButton
            , Button skipButton) {
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

    /**
     * Closes the screen upon button press
     */
    private static void closeAll() {
        ArrayList<Button> staticButts = new ArrayList<>();
        staticButts.add(Store.sCompleteButton);
        staticButts.add(Pub.getsGambleButton());
        staticButts.add(ControllerWampusGrounds.getsClaimButton());
        staticButts.add(Town.getsPubButton());

        for (Button b: staticButts) {
            try {
                if (b != null) {
                    Stage stage = (Stage) b.getScene().getWindow();
                    stage.close();
                }
            } catch (Exception e) {
                System.out.println("oops");
            }
        }

    }


    //For Pub
    //Money Bonus = Round Bonus + random(0, Time Bonus)
    /**
     * Calculates the bonus on each round
     * @return bonus
     */
    public static int calculateBonus() {
        int moneyBonus = 0;
        int roundBonus = 50;
        int timeBonus = 50;
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

    /**
     * Updates the production of each visited players
     */
    public static void updateProduction() {
        orderedPlayers = new PriorityQueue<>();
        for (Player player : visitedPlayers) {
            int[][] lands = player.getLands();
            // Loop through player's land array to see
            // if they have a mule on land
            // [2] = Owns Food Mule
            // [3] = Owns Energy Mule
            // [4] = Owns Ore Mule
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    int landNum = lands[i][j];
                    TileType tileType = TileManager.getTileType(i, j);
                    if (player.getEnergyCount() >= 1) {
                        if (landNum == 2) {
                            player.setFoodCount(player.getFoodCount()
                                    + tileType.getFoodCount());
                            player.setEnergyCount(player.getEnergyCount() - 1);
                        } else if (landNum == 3) {
                            player.setEnergyCount(player.getEnergyCount()
                                    + tileType.getEnergyCount());
                            player.setEnergyCount(player.getEnergyCount() - 1);
                        } else if (landNum == 4) {
                            player.setOreCount(player.getOreCount()
                                    + tileType.getOreCount());
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