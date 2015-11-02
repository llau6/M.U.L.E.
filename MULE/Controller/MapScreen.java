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
import java.net.URL;
import java.util.Queue;
import java.util.ResourceBundle;

/**
 * Created by SeYeon on 9/12/2015.
 */
public class MapScreen implements Initializable {
    @FXML
    public GridPane map;
    @FXML
    public Button claimLand;
    @FXML
    private Button skipButt;
    @FXML
    private Button townButton;
    @FXML
    private ImageView selectedImage;
    @FXML
    public Label turnType;
    @FXML
    public Label currPlayer;
    @FXML
    public Label money;
    @FXML
    public Label ore;
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
    private Label selectedEnergy;
    @FXML
    private Label selectedCost;
    @FXML
    public Text countDownText;

    public static Label sCurrPlayer;
    public static Label sEnergy;
    public static Label sMoney;
    public static Label sOre;
    public static Label sFood;
    public static Label sScore;
    public static Text sCountDownText;
    public static Label sTurnType;
    public static Label sRound;
    public static Button sTownButton;
    public static Button sSkipButton;
    private int playerCount = 0;
    private int skipCount = 0;
    public static int clickCount = 0;
    private final SoundManager soundManager = new SoundManager(3);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sCurrPlayer = currPlayer;
        sEnergy = energy;
        sMoney = money;
        sOre = ore;
        sFood = food;
        sScore = score;
        sCountDownText = countDownText;
        sTurnType = turnType;
        sRound = round;
        sTownButton = townButton;
        sSkipButton = skipButt;

        skipButt.setDisable(true);
        GameManager.totalTurnsInitial = GameManager.players.size() * 2;
        GameManager.initLandSelection(currPlayer, energy, money, ore, food, score, countDownText, townButton);
        GameManager.initializeMap();
        initializeGridPane();
//        soundManager.playMusic();

        claimLand.setOnAction((event) -> {
            GameManager.timer.cancel();
            playerCount++;
            if (playerCount + skipCount >= GameManager.players.size()) {
                playerCount = 0;
                skipCount = 0;
                System.out.println("RESET");
            }
            // Purchasing property
            if (GameManager.currentPlayer.getMoney() >= 300) {
                // If property is not owned by anyone
                if (!TileManager.isTaken()) {
                    // Set property to owned
                    TileManager.setTaken(true);
                    // Player's land count++
                    GameManager.currentPlayer.setLandCount();
                    // Add current property tile to player's array of owned land
                    TileManager.setPlayerLand();
                    // If not during Free Land Grant phase, deduct money
                    if (!GameManager.isFree) {
                        GameManager.currentPlayer.setMoney(GameManager.currentPlayer.getMoney() - 300);
                    }
                    // So that player can select property again next turn
                    GameManager.currentPlayer.setClicked(false);
                }
                if (GameManager.totalTurnsInitial != 0) {
                    GameManager.initLandSelection(currPlayer, energy, money, ore, food, score, countDownText, townButton);
                } else {
                    skipButt.setDisable(false);
                    GameManager.buyLandSelection(GameManager.currentPlayer, currPlayer, energy, money, ore, food, score, true, countDownText, round, roundLabel, turnType, claimLand, skipButt, townButton);
                }
            } else {
                claimLand.setDisable(true);
                claimLand.setText("Insufficient Funds!");
            }
        });

        skipButt.setOnAction((event) -> {
            GameManager.timer.cancel();
            if (claimLand.isDisable()) {
                claimLand.setText("Claim Land!");
                claimLand.setDisable(false);
            }
            skipCount++;
            if (skipCount >= GameManager.players.size()) {
                claimLand.setDisable(true);
                if (GameManager.newRound) {
                    GameManager.updateProduction();
                    //IFFFY
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("../View/saveScreen.fxml"));
                        stage.setScene(new Scene(root));
                        stage.setTitle("Town Actions");
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                GameManager.gamePlay(currPlayer, energy, money, ore, food, score, countDownText, turnType, round, townButton, skipButt);
                map.setCursor(Cursor.DEFAULT);
            } else {
                if (playerCount + skipCount == GameManager.players.size()) {
                    playerCount = 0;
                    skipCount = 0;
                }
                GameManager.buyLandSelection(GameManager.currentPlayer, currPlayer, energy, money, ore, food, score, false, countDownText, round, roundLabel, turnType, claimLand, skipButt, townButton);
            }
            // So next player can purchase mule
            StoreManager.setBoughtMule(false);
        });
        GameManager.mapGrid = map;
    } // End of initializing

    public static void updateResources() {
        sMoney.setText("" + GameManager.currentPlayer.getMoney());
        sFood.setText("" + GameManager.currentPlayer.getFoodCount());
        sEnergy.setText("" + GameManager.currentPlayer.getEnergyCount());
        sOre.setText("" + GameManager.currentPlayer.getOreCount());
        GameManager.updateCurrentScore();
        sScore.setText("" + GameManager.currentPlayer.getScore());
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("../View/townScreen.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Town Actions");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void loadMap(Queue<Player> q) {
        ObservableList<Node> children = map.getChildren();
        for (Node node : children) {
            int i = GridPane.getRowIndex(node);
            int j = GridPane.getColumnIndex(node);
            Rectangle currNode = (Rectangle) node;
            for (Player player: q) {
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
                    currNode.setStroke(GameManager.currentPlayer.getColor());
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

    // Selecting/deselecting land & placing mules on the tiles
    public void initializeGridPane() {
        ObservableList<Node> children = map.getChildren();
        for (Node node : children) {
            int i = GridPane.getRowIndex(node);
            int j = GridPane.getColumnIndex(node);
            //If not the Town
            if (!(i == 2 && j == 4)) {
                Rectangle currNode = (Rectangle) node;
                // If statement to get rid of stupid yellow squiggly lines.
                if (currNode != null) {
                    // When the mouse hovers over a tile
                    currNode.setOnMouseEntered(event -> {
                        // Highlights if
                        // 1) No property is currently selected
                        // 2) Property is not owned by anyone
                        // 3) It is still the land selection phase
                        if ((!(GameManager.currentPlayer.isClicked()) && !TileManager.isTaken(i, j) && !claimLand.isDisabled()
                                || (StoreManager.isAlmostBought() && GameManager.currentPlayer.getLands()[i][j] == 1))) {
                            currNode.setFill(Color.WHITE);
                            currNode.setOpacity(.34);
                            // Set labels so players can see what is being purchased
                            TileType selectedTileType = TileManager.getTileType(i, j);
                            selectedImage.setImage(new Image(selectedTileType.getSrc()));
                            tileType.setText(String.valueOf(selectedTileType.getName()));
                            selectedFood.setText(String.valueOf(selectedTileType.getFoodCount()));
                            selectedOre.setText(String.valueOf(selectedTileType.getOreCount()));
                            selectedEnergy.setText(String.valueOf(selectedTileType.getEnergyCount()));
                            selectedCost.setText(String.valueOf(300));
                        }
                    });
                    // When the mouse moves off of a tile
                    currNode.setOnMouseExited(event -> {
                        // Same conditions from when a square is being highlighted ^^
                        if (!(GameManager.currentPlayer.isClicked()) && !TileManager.isTaken(i, j) && !claimLand.isDisabled()) {
                            currNode.setFill(Color.TRANSPARENT);
                            currNode.setOpacity(0);
                        } else if (StoreManager.isAlmostBought() && GameManager.currentPlayer.getLands()[i][j] == 1) {
                            currNode.setStrokeWidth(4.4);
                            currNode.setFill(Color.TRANSPARENT);
                            currNode.setStroke(GameManager.currentPlayer.getColor());
                            currNode.setOpacity(1);
                        }
                    });
                    currNode.setOnMouseClicked(event -> {
                        // Selects if
                        // 1) No property is selected
                        // 2) Property is not owned by anyone
                        // 3) It is still the land selection phase
                        if (!(GameManager.currentPlayer.isClicked()) && !TileManager.isTaken(i, j) && !claimLand.isDisabled()) {
                            currNode.setStrokeWidth(4.4);
                            currNode.setFill(Color.TRANSPARENT);
                            currNode.setStroke(GameManager.currentPlayer.getColor());
                            currNode.setOpacity(1);
                            GameManager.currentPlayer.setClicked(true);
                            //Tile Manager keeps track of selected tile
                            TileManager.setCurrRow(i);
                            TileManager.setCurrCol(j);
                            // Deselects if
                            // 1) Property is currently selected
                            // 2) Property is not taken
                            // 3) It is still the land selection phase
                            // 4) It is your color
                        } else if (GameManager.currentPlayer.isClicked()
                                && !TileManager.isTaken(i, j)
                                && !claimLand.isDisabled()
                                && currNode.getStroke() != null
                                && currNode.getStroke().equals(GameManager.currentPlayer.getColor())) {
                            currNode.setStrokeWidth(0);
                            currNode.setFill(Color.TRANSPARENT);
                            currNode.setStroke(Color.WHITE);
                            currNode.setOpacity(0);
                            TileManager.setCurrRow(i);
                            TileManager.setCurrCol(j);
                            // Needed so players can select land again next round
                            GameManager.currentPlayer.setClicked(false);
                        }
                        // If you need to place a mule
                        if (StoreManager.isAlmostBought()) {
                            StoreManager.setAlmostBought(false);
                            String mule = GameManager.currentPlayer.getCurMule();
                            if (!map.getCursor().equals(Cursor.DEFAULT)) {
                                // If player owns land and there's no mule on it
                                if (GameManager.currentPlayer.getLands()[i][j] == 1 && clickCount == 0) {
                                    Image muleImage = new Image("MULE/View/Images/mule" + mule + ".gif");
                                    ImageView mImageView = new ImageView(muleImage);
                                    mImageView.setPreserveRatio(true);
                                    mImageView.setFitWidth(50);
                                    map.add(mImageView, j, i);
                                    map.setCursor(Cursor.DEFAULT);
                                    // Takes highlight off of tile
                                    currNode.setStrokeWidth(4.4);
                                    currNode.setFill(Color.TRANSPARENT);
                                    currNode.setStroke(GameManager.currentPlayer.getColor());
                                    currNode.setOpacity(1);
                                    // Set player's land array to the type of mule they own
                                    GameManager.currentPlayer.setLands(mule, i, j);
                                    clickCount++;
                                    // If mule needs to be destroyed
                                } else if (clickCount == 0) {
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
                } // If current node is not null
            } // If not town
        } // For each node in GridPane
    } // Init GridPane
}