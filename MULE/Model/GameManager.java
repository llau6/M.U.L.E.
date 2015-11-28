package MULE.Model;

import MULE.Controller.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by jatin1 on 9/18/15.
 */
public final class GameManager {
    private GameManager() {}
    private static boolean isFree = true;
    private static boolean newRound;
    private static boolean townOpen;
    private static int playerNum;
    private static int timerLeft;
    private static int totalTurnsInitial;
    private static int currentTurnNumber = 1;
    private static int currentRoundNumber = 1;
    private static GridPane mapGrid;
    private static String difficulty;
    private static String mapType;
    private static Player currentPlayer;
    public static  Queue<Player> players = new LinkedList<>();
    private static PriorityQueue<Player> orderedPlayers = new PriorityQueue<>();
    private static List<Player> visitedPlayers = new LinkedList<>();
    private static Timer timer;

    /**
     * Free land grant
     * First 2 turns for every player
     * @param currPlayer Current Player Label
     * @param countDownText Count Down Label
     */
    public static void initLandSelection(Label currPlayer, Text countDownText) {
        townOpen = false;
        totalTurnsInitial--;
        final int[] countDown = {50};
        countDownText.setText("Time left:" + countDown[0]);
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
        MapScreen.sMapColor.setFill(currentPlayer.getColor());
        currPlayer.setText(currentPlayer.getName());
        MapScreen.updateResources();
        players.add(currentPlayer);
    }

    //initial land selection phase after first two turn
    public static void buyLandSelection(Label currPlayer, Text countDownText, Label round, Label roundLabel, Label turnType, Button claimLand, Button skipButton) {
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
        MapScreen.sMapColor.setFill(currentPlayer.getColor());
        currPlayer.setText(currentPlayer.getName());
        MapScreen.updateResources();
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

    public static void gamePlay(Label currPlayer, Text countDownText, Label turnType, Label round, Button skipButton) {
        townOpen = true;
        MapScreen.updateTown();
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
                            townOpen = false;
                            MapScreen.updateTown();
                            skipButton.setText("You're Done");
                            closeAll();
                        }
                    }
                });
            }
        }, 1000, 1000); //Every 1 second
        currentPlayer = orderedPlayers.remove();
        MapScreen.sMapColor.setFill(currentPlayer.getColor());
        currPlayer.setText(currentPlayer.getName());
        MapScreen.updateResources();
        visitedPlayers.add(currentPlayer);
        if (currentTurnNumber == players.size()) {
            currentRoundNumber++;
            currentTurnNumber = 1;
            newRound = true;
        } else {
            currentTurnNumber++;
        }
    }

    public static void updateCurrentScore() {
        currentPlayer.setScore(currentPlayer.getMoney() + (currentPlayer.getLandCount() * 500)
                + currentPlayer.getEnergyCount() + currentPlayer.getOreCount() + currentPlayer.getFoodCount());
    }

    // For mule production method
    public static void updateCurrentScore(Player currentPlayer) {
        currentPlayer.setScore(currentPlayer.getMoney() + (currentPlayer.getLandCount() * 500)
                + currentPlayer.getEnergyCount() + currentPlayer.getOreCount() + currentPlayer.getFoodCount());
    }

    /**
     * Chances of a player getting a random event
     */
    public static void initiateRandom() {
        int chance = (int) (Math.random() * 100 + 1);
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

    /**
     * Updates resources gained from mules for every player every round
     */
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

    /**
     * Closes all the windows that are not the map screen
     * Used when time runs out
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

    public static boolean isFree() {
        return isFree;
    }

    public static boolean isNewRound() {
        return newRound;
    }

    public static boolean isTownOpen() {
        return townOpen;
    }

    public static int getPlayerNum() {
        return playerNum;
    }

    public static int getTotalTurnsInitial() {
        return totalTurnsInitial;
    }

    public static int getCurrentTurnNumber() {
        return currentTurnNumber;
    }

    public static int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public static int getTimerLeft() {
        return timerLeft;
    }

    public static GridPane getMapGrid() {
        return mapGrid;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static String getMapType() { return mapType; }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static PriorityQueue<Player> getOrderedPlayers() {
        return orderedPlayers;
    }

    public static Queue<Player> getPlayersQueue() {
        return players;
    }

    public static Timer getTimer() {
        return timer;
    }

    public static void setDifficulty(String difficulty) {
        GameManager.difficulty = difficulty;
    }

    public static void setMapType(String mapType) {
        GameManager.mapType = mapType;
    }

    public static void setTotalTurnsInitial(int totalTurnsInitial) {
        GameManager.totalTurnsInitial = totalTurnsInitial;
    }

    public static void setCurrentRoundNumber(int currentRoundNumber) {
        GameManager.currentRoundNumber = currentRoundNumber;
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        GameManager.currentPlayer = currentPlayer;
    }

    public static void setMapGrid(GridPane mapGrid) {
        GameManager.mapGrid = mapGrid;
    }

    public static void setPlayerNum(int playerNum) {
        GameManager.playerNum = playerNum;
    }

    public static void setTownOpen(boolean townOpen) {
        GameManager.townOpen = townOpen;
    }
}