package MULE.Controller;

import MULE.Model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


import javafx.scene.image.ImageView;
import javafx.scene.Node;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Queue;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Created by SeYeon on 9/12/2015.
 */
public class MapScreen implements Initializable {
    private static boolean isLoadingFromDB = false;
    @FXML
    public GridPane map;
    @FXML
    public Button claimLand;
    @FXML
    private Button skipButt;
    @FXML
    private ImageView selectedImage;
    @FXML
    private ImageView mapImg;
    @FXML
    private ImageView playerImg;
    @FXML
    public Label turnType;
    @FXML
    public Label currPlayer;
    @FXML
    public Label money;
    @FXML
    public Label ore;
    @FXML
    public Label happiness;
    @FXML
    public Label energy;
    @FXML
    public Label food;
    @FXML
    public Label score;
    @FXML
    public Label round;
    @FXML
    public Label roundLabel;
    @FXML
    public Label tileType;
    @FXML
    private Label selectedFood;
    @FXML
    private Label selectedOre;
    @FXML
    private Label selectedHappiness;
    @FXML
    private Label selectedEnergy;
    @FXML
    private Label selectedCost;
    @FXML
    private Rectangle mapColor;
    @FXML
    public Text countDownText;

    public static GridPane sMap;
    public static Label sCurrPlayer;
    private static Label sEnergy;
    private static Label sMoney;
    private static Label sOre;
    private static Label sHappiness;
    private static Label sFood;
    private static Label sScore;
    public static Text sCountDownText;
    public static Label sTurnType;
    public static Label sRound;
    public static ImageView sSelectedImage;
    public static Button sSkipButton;
    public static Label sSelectedText;
    public static ImageView sMapImg;
    public static ImageView sPlayerImg;
    public static Rectangle sMapColor;
    private static int clickCount = 0;
    private int playerCount = 0;
    private int skipCount = 0;
    public static SoundManager soundManager;

    public static void setClickCount(int clickCount) {
        MapScreen.clickCount = clickCount;
    }
    public static int getClickCount() {
        return clickCount;
    }
    public static void setIsLoadingFromDB(boolean val) {
        isLoadingFromDB = val;
    }
    public static boolean isLoadingFromDB() {
        return isLoadingFromDB;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sMap = map;
        sCurrPlayer = currPlayer;
        sEnergy = energy;
        sMoney = money;
        sOre = ore;
        sHappiness = happiness;
        sFood = food;
        sScore = score;
        sCountDownText = countDownText;
        sTurnType = turnType;
        sRound = round;
        sSkipButton = skipButt;
        sMapImg = mapImg;
        sSelectedImage = selectedImage;
        sSelectedText = tileType;
        sMapColor = mapColor;
        sPlayerImg = playerImg;

        if (isLoadingFromDB) {
            MapScreen.updateResources();
            loadMap(GameManager.getPlayersQueue());
            claimLand.setDisable(true);
            GameManager.gamePlay(countDownText, turnType, round, skipButt);
        } else {
            skipButt.setDisable(true);
            GameManager.setTotalTurnsInitial(GameManager.getPlayersQueue().size() * 2);
            GameManager.initLandSelection(countDownText);
            StoreManager.initializeStore();
        }
        initializeGridPane();
        initializeButtons();
        initializeMap();
        GameManager.setMapGrid(map);
    }

    /**
     * Initializes standard map or randomized map
     */
    public void initializeMap() {
        Random ran = new Random();
        String src;
        ObservableList<Node> children = map.getChildren();
        for (Node node : children) {
            int i = GridPane.getRowIndex(node);
            int j = GridPane.getColumnIndex(node);
            if (node instanceof ImageView) {
                // Standard game map
                if (GameManager.getMapType().equals("Standard")) {
                    if ((i == 0 && j == 2)
                            || (i == 1 && j == 1)
                            || (i == 2 && j == 8)) {
                        TileManager.setTileTypes(i, j, TileType.MOUNTAIN1);
                        src = TileType.MOUNTAIN1.getSrc();
                    } else if ((i == 3 && j == 1)
                            || (i == 3 && j == 6)
                            || (i == 4 && j == 2)
                            || (i == 4 && j == 8)) {
                        TileManager.setTileTypes(i, j, TileType.MOUNTAIN2);
                        src = TileType.MOUNTAIN2.getSrc();
                    } else if ((i == 0 && j == 6)
                            || (i == 1 && j == 8)
                            || (i == 2 && j == 0)) {
                        TileManager.setTileTypes(i, j, TileType.MOUNTAIN3);
                        src = TileType.MOUNTAIN3.getSrc();
                    } else {
                        TileManager.setTileTypes(i, j, TileType.PLAIN);
                        src = TileType.PLAIN.getSrc();
                    }
                    // Random game map
                } else {
                    int x = ran.nextInt(10);
                    if (x == 0) {
                        TileManager.setTileTypes(i, j, TileType.MOUNTAIN1);
                        src = TileType.MOUNTAIN1.getSrc();
                    } else if (x == 1) {
                        TileManager.setTileTypes(i, j, TileType.MOUNTAIN2);
                        src = TileType.MOUNTAIN2.getSrc();
                    } else if (x == 2) {
                        TileManager.setTileTypes(i, j, TileType.MOUNTAIN3);
                        src = TileType.MOUNTAIN3.getSrc();
                    } else if (x == 3) {
                        TileManager.setTileTypes(i, j, TileType.SHROOMS);
                        src = TileType.SHROOMS.getSrc();
                    } else {
                        TileManager.setTileTypes(i, j, TileType.PLAIN);
                        src = TileType.PLAIN.getSrc();
                    }
                }
                ((ImageView) node).setImage(new Image(src));
            }
        }
    }

    /**
     * Initializes the Skip Button and Claim Land Button
     */
    public void initializeButtons() {
        claimLand.setOnAction((event) -> {
            GameManager.getTimer().cancel();
            playerCount++;
            if (playerCount + skipCount >= GameManager.getPlayersQueue().size()) {
                playerCount = 0;
                skipCount = 0;
            }
            // Purchasing property
            if (GameManager.getCurrentPlayer().getMoney() >= 300) {
                if (!TileManager.isTaken()) {
                    TileManager.setTaken(true);
                    GameManager.getCurrentPlayer().setLandCount();
                    TileManager.setPlayerLand();
                    if (!GameManager.isFree()) {
                        GameManager.getCurrentPlayer().setMoney(GameManager.getCurrentPlayer().getMoney() - 300);
                    }
                    // So that player can select property again next turn
                    GameManager.getCurrentPlayer().setClicked(false);
                }
                if (GameManager.getTotalTurnsInitial() != 0) {
                    GameManager.initLandSelection(countDownText);
                } else {
                    skipButt.setDisable(false);
                    GameManager.buyLandSelection(countDownText, round, roundLabel, turnType, claimLand, skipButt);
                }
            } else {
                claimLand.setDisable(true);
                claimLand.setText("Insufficient Funds!");
            }
        });

        skipButt.setOnAction((event) -> {
            GameManager.getTimer().cancel();
            if (claimLand.isDisable()) {
                claimLand.setText("Claim Land!");
                claimLand.setDisable(false);
            }
            skipCount++;
            if (skipCount >= GameManager.getPlayersQueue().size() || isLoadingFromDB) {
                claimLand.setDisable(true);
                if (GameManager.isNewRound()) {
                    GameManager.updateProduction();
                    //IFFFY
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("../View/saveScreen.fxml"));
                        stage.setScene(new Scene(root));
                        stage.setTitle("Save Screen");
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    GameManager.gamePlay(countDownText, turnType, round, skipButt);
                    map.setCursor(Cursor.DEFAULT);
                }
            } else {
                if (playerCount + skipCount == GameManager.getPlayersQueue().size()) {
                    playerCount = 0;
                    skipCount = 0;
                }
                GameManager.buyLandSelection(countDownText, round, roundLabel, turnType, claimLand, skipButt);
            }
            // So next player can purchase mule
            StoreManager.setBoughtMule(false);
        });
    }

    /**
     * Changes town image/label to open or closed
     */
    public static void updateTown() {
        if (GameManager.isTownOpen()) {
            sMapImg.setImage(new Image("MULE/View/Images/gameOpen.gif"));
        } else {
            sSelectedText.setText("TOWN IS CLOSED");
            sSelectedImage.setImage(new Image("MULE/View/Images/townClosed.png"));
            sMapImg.setImage(new Image("MULE/View/Images/gameMap.gif"));
        }
    }

    /**
     * Updates player's resource labels
     */
    public static void updateResources() {
        sMoney.setText("" + GameManager.getCurrentPlayer().getMoney());
        sFood.setText("" + GameManager.getCurrentPlayer().getFoodCount());
        sEnergy.setText("" + GameManager.getCurrentPlayer().getEnergyCount());
        sOre.setText("" + GameManager.getCurrentPlayer().getOreCount());
        sHappiness.setText("" + GameManager.getCurrentPlayer().getHappinessCount());
        GameManager.updateCurrentScore();
        sScore.setText("" + GameManager.getCurrentPlayer().getScore());
    }

    /**
     * Reloads game state from database
     * @param q Current player
     */
    public void loadMap(Queue<Player> q) {
        ObservableList<Node> children = map.getChildren();
        for (int z = 0; z < children.size(); z++) {
            int i = GridPane.getRowIndex(children.get(z));
            int j = GridPane.getColumnIndex(children.get(z));
            if ((children.get(z) instanceof Rectangle)) {
                Rectangle currNode = (Rectangle) children.get(z);
                for (Player player : q) {
                    Image muleImage = null;
                    int landVal = player.getLands()[i][j];
                    if (landVal == 2) {
                        muleImage = new Image("MULE/View/Images/muleFood.gif");
                    } else if (landVal == 3) {
                        muleImage = new Image("MULE/View/Images/muleEnergy.gif");
                    } else if (landVal == 4) {
                        muleImage = new Image("MULE/View/Images/muleOre.gif");
                    }
                    if (landVal != 0) {
                        TileManager.setTaken(i, j);
                        currNode.setStrokeWidth(4.4);
                        currNode.setFill(Color.TRANSPARENT);
                        currNode.setStroke(player.getColor());
                        currNode.setOpacity(1);
                        if (landVal != 1) {
                            ImageView mImageView = new ImageView(muleImage);
                            mImageView.setPreserveRatio(true);
                            mImageView.setFitWidth(50);
                            map.add(mImageView, j, i);
                        }
                    }
                }
            }
        }
    }

    /**
     * Selects/deselects land and places mule on the tiles
     */
    public void initializeGridPane() {
        try {
            soundManager = new SoundManager(20, 5);
            //soundManager.playSound("map");
            soundManager.playMusic();
            ObservableList<Node> children = map.getChildren();
            for (Node node : children) {
                int i = GridPane.getRowIndex(node);
                int j = GridPane.getColumnIndex(node);
                if (node instanceof Rectangle) {
                    Rectangle currNode = (Rectangle) node;

                    // If statement to get rid of stupid yellow squiggly lines.
                    if (currNode != null) {
                        // When the mouse hovers over a tile
                        currNode.setOnMouseEntered(event -> {
                            // Town
                            if (i == 2 && j == 4) {
                                currNode.setFill(Color.WHITE);
                                currNode.setOpacity(.34);
                                selectedFood.setText("--");
                                selectedEnergy.setText("--");
                                selectedOre.setText("--");
                                selectedHappiness.setText("--");
                                selectedCost.setText("--");
                                if (GameManager.isTownOpen()) {
                                    tileType.setText("TOWN IS OPEN");
                                    Image townImg = new Image("MULE/View/Images/townOpen.png");
                                    selectedImage.setImage(townImg);
                                } else {
                                    tileType.setText("TOWN IS CLOSED");
                                    Image townImg = new Image("MULE/View/Images/townClosed.png");
                                    selectedImage.setImage(townImg);
                                }
                            }
                            // Highlights if
                            // 1) No property is currently selected
                            // 2) Property is not owned by anyone
                            // 3) It is still the land selection phase
                            else if ((!(GameManager.getCurrentPlayer().isClicked()) && !TileManager.isTaken(i, j) && !claimLand.isDisabled()
                                    || (StoreManager.isAlmostBought() && GameManager.getCurrentPlayer().getLands()[i][j] == 1))) {
                                currNode.setFill(Color.WHITE);
                                currNode.setOpacity(.34);
                                // Set labels so players can see what is being purchased
                                TileType selectedTileType = TileManager.getTileType(i, j);
                                selectedImage.setImage(new Image(selectedTileType.getSrc()));
                                tileType.setText(String.valueOf(selectedTileType.getName()));
                                selectedFood.setText(String.valueOf(selectedTileType.getFoodCount()));
                                selectedOre.setText(String.valueOf(selectedTileType.getOreCount()));
                                selectedHappiness.setText(String.valueOf(selectedTileType.getHappinessCount()));
                                selectedEnergy.setText(String.valueOf(selectedTileType.getEnergyCount()));
                                if (GameManager.getTotalTurnsInitial() > 0) {
                                    selectedCost.setText("FREE");
                                } else {
                                    selectedCost.setText("300");
                                }
                            }
                        });
                        // When the mouse moves off of a tile
                        currNode.setOnMouseExited(event -> {
                            // Same conditions from when a square is being highlighted ^^
                            // OR if the Town
                            if ((i == 2 && j == 4) || (!(GameManager.getCurrentPlayer().isClicked()) && !TileManager.isTaken(i, j) && !claimLand.isDisabled())) {
                                currNode.setFill(Color.TRANSPARENT);
                                currNode.setOpacity(0);
                            } else if (StoreManager.isAlmostBought() && GameManager.getCurrentPlayer().getLands()[i][j] == 1) {
                                currNode.setStrokeWidth(4.4);
                                currNode.setFill(Color.TRANSPARENT);
                                currNode.setStroke(GameManager.getCurrentPlayer().getColor());
                                currNode.setOpacity(1);
                            }
                        });
                        currNode.setOnMouseClicked(event -> {
                            // If Town is clicked
                            if (i == 2 && j == 4) {
                                if (GameManager.isTownOpen()) {
                                    try {
                                        soundManager.shutdown();
                                        Stage stage = new Stage();
                                        Parent root = FXMLLoader.load(getClass().getResource("../View/townScreen.fxml"));
                                        stage.setScene(new Scene(root));
                                        stage.setTitle("Town Actions");
                                        stage.initModality(Modality.APPLICATION_MODAL);
                                        stage.show();
                                    } catch (IOException e) {
                                        System.out.println("op");
                                    }
                                }
                            }
                            // Selects if
                            // 1) No property is selected
                            // 2) Property is not owned by anyone
                            // 3) It is still the land selection phase
                            else if (!(GameManager.getCurrentPlayer().isClicked()) && !TileManager.isTaken(i, j) && !claimLand.isDisabled()) {
                                currNode.setStrokeWidth(4.4);
                                currNode.setFill(Color.TRANSPARENT);
                                currNode.setStroke(GameManager.getCurrentPlayer().getColor());
                                currNode.setOpacity(1);
                                GameManager.getCurrentPlayer().setClicked(true);
                                //Tile Manager keeps track of selected tile
                                TileManager.setCurrRow(i);
                                TileManager.setCurrCol(j);
                                // Deselects if
                                // 1) Property is currently selected
                                // 2) Property is not taken
                                // 3) It is still the land selection phase
                                // 4) It is your color
                            } else if (GameManager.getCurrentPlayer().isClicked()
                                    && !TileManager.isTaken(i, j)
                                    && !claimLand.isDisabled()
                                    && currNode.getStroke() != null
                                    && currNode.getStroke().equals(GameManager.getCurrentPlayer().getColor())) {
                                currNode.setStrokeWidth(0);
                                currNode.setFill(Color.TRANSPARENT);
                                currNode.setStroke(Color.WHITE);
                                currNode.setOpacity(0);
                                TileManager.setCurrRow(i);
                                TileManager.setCurrCol(j);
                                // Needed so players can select land again next round
                                GameManager.getCurrentPlayer().setClicked(false);
                            }
                            // If you need to place a mule
                            if (StoreManager.isAlmostBought()) {
                                StoreManager.setAlmostBought(false);
                                String mule = GameManager.getCurrentPlayer().getCurMule();
                                if (!map.getCursor().equals(Cursor.DEFAULT)) {
                                    // If player owns land and there's no mule on it
                                    if (GameManager.getCurrentPlayer().getLands()[i][j] == 1 && MapScreen.getClickCount() == 0) {
                                        Image muleImage = new Image("MULE/View/Images/mule" + mule + ".gif");
                                        ImageView mImageView = new ImageView(muleImage);
                                        mImageView.setPreserveRatio(true);
                                        mImageView.setFitWidth(50);
                                        map.add(mImageView, j, i);
                                        map.setCursor(Cursor.DEFAULT);
                                        // Takes highlight off of tile
                                        currNode.setStrokeWidth(4.4);
                                        currNode.setFill(Color.TRANSPARENT);
                                        currNode.setStroke(GameManager.getCurrentPlayer().getColor());
                                        currNode.setOpacity(1);
                                        // Set player's land array to the type of mule they own
                                        GameManager.getCurrentPlayer().setLands(mule, i, j);
                                        clickCount++;
                                        // If mule needs to be destroyed
                                    } else if (MapScreen.getClickCount() == 0) {
                                        map.setCursor(new ImageCursor(new Image("MULE/View/Images/catMuleDestroyedCursor.gif")));
                                        clickCount++;
                                        try {
                                            Stage stage = new Stage();
                                            Parent root = FXMLLoader.load(getClass().getResource("../View/muleDestroyed.fxml"));
                                            stage.setScene(new Scene(root));
                                            root.setCursor(new ImageCursor(new Image("MULE/View/Images/catMuleDestroyedCursor.gif")));
                                            stage.setTitle("You Suck!!");
                                            stage.initModality(Modality.APPLICATION_MODAL);
                                            stage.show();
                                        } catch (IOException e) {
                                            System.out.println("hi!!!!");
                                        }
                                    }
                                }
                            }
                        });
                    }
                } // If current node is not null
            }
        } catch (MalformedURLException ex) {
            System.out.println("sound error");
        }
    } // Init GridPane
}