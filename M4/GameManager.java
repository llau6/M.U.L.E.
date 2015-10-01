package M4;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.*;

/**
 * Created by jatin1 on 9/18/15.
 */
public class GameManager {
    public static String difficulty;
    public static Queue<Player> players = new LinkedList<>();
    public static TileType[][] gameMap = new TileType[5][9];
    public static Player currentPlayer;
    public static int currentTurn;
    public static int totalTurnsInitial;
    public static Timer timer;


    //creates the 2D array with the appropriate tiles for the default map
    public static void initializeMap() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 2 && j == 4) {
                } else if (j == 4) {
                    gameMap[i][j] = TileType.RIVER;
                } else if ((i == 0 && j == 2) || (i == 1 && j == 1) && (i == 2 || j == 8)) {
                    gameMap[i][j] = TileType.MOUNTAIN1;
                } else if ((i == 3 && j == 1) || (i == 3 && j == 6) && (i == 4 || j == 2) && (i == 4 || j == 8)) {
                    gameMap[i][j] = TileType.MOUNTAIN2;
                } else if ((i == 0 && j == 6) || (i == 1 && j == 8) && (i == 2 || j == 0)) {
                    gameMap[i][j] = TileType.MOUNTAIN3;
                } else {
                    gameMap[i][j] = TileType.PLAIN;
                }
            }
        }
    }


    //initial land selection phase
    public static void initLandSelection(Label currPlayer, Label energy, Label money, Label ore, Label food, Label score, Text countDownText) {
        totalTurnsInitial = players.size() * 2;
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

                        if (countDown[0] <= 0) {
                            timer.cancel();
                            countDownText.setText("Out of time!");
                        }
                    }
                });
            }
        }, 1000, 1000); //Every 1 second
        currentPlayer = players.remove();
        currentTurn++;

        currPlayer.setText(currentPlayer.getName());
        energy.setText("" + currentPlayer.getEnergyCount());
        money.setText("" + currentPlayer.getMoney());
        ore.setText("" + currentPlayer.getOreCount());
        food.setText("" + currentPlayer.getFoodCount());
        score.setText("" + currentPlayer.getScore());
        players.add(currentPlayer);
    }

    //initial land selection phase after first two turn
    public static void buyLandSelection(Player prevPlayer, Label currPlayer, Label energy, Label money, Label ore, Label food, Label score, Boolean bought, Text countDownText) {
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

                        if (countDown[0] <= 0) {
                            timer.cancel();
                            countDownText.setText("Out of time!");
                        }
                    }
                });
            }
        }, 1000, 1000); //Every 1 second
        currentPlayer = players.remove();
        currentTurn++;

        int curMoney = prevPlayer.getMoney();
        if (bought) {
            prevPlayer.setMoney(curMoney - 300);
        }
        currPlayer.setText(currentPlayer.getName());
        energy.setText("" + currentPlayer.getEnergyCount());
        money.setText("" + currentPlayer.getMoney());
        ore.setText("" + currentPlayer.getOreCount());
        food.setText("" + currentPlayer.getFoodCount());
        score.setText("" + currentPlayer.getScore());
        players.add(currentPlayer);
    }
}