package M4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


import javafx.scene.image.ImageView;
import javafx.scene.Node;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by SeYeon on 9/12/2015.
 */
public class MapScreen implements Initializable{

    private int playerCount = 0, skipCount = 0;

    @FXML
    public static Button townButton;

    @FXML
    public Button nextPlayer;

    @FXML
    public GridPane map;

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

    public TileType selectedTile;

    @FXML
    private ImageView selectedImage;

    @FXML
    private Label selectedFood;

    @FXML
    private Label selectedOre;

    @FXML
    private Label selectedEnergy;

    @FXML
    private Label selectedCost;

    @FXML
    private Button skipButt;

    private TileType selectedTileType;

    private Button selectedLand;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GameManager.initializeMap();
        skipButt.setDisable(true);
        GameManager.initLandSelection(currPlayer, energy, money, ore, food, score);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if ((i == 0 && j == 6) || (i == 2 && j == 0) || (i == 1 && j == 8)) {
                    Button riverNode = (Button)getNodeFromGridPane(map, j, i);
                    riverNode.setOnAction(event -> {
                        selectedImage.setImage(((ImageView) (riverNode.getGraphic())).getImage());
                        selectedFood.setText(String.valueOf(TileType.RIVER.getFoodCount()));
                        selectedOre.setText(String.valueOf(TileType.RIVER.getOreCount()));
                        selectedEnergy.setText(String.valueOf(TileType.RIVER.getEnergyCount()));
                        selectedCost.setText(String.valueOf(300));
                        selectedTileType = TileType.RIVER;
                        selectedLand = riverNode;
                    });

                } else if ((i == 0 && j == 2) || (i == 1 && j == 1) || (i == 2 && j == 8)) {
                    Button mountain1Node = (Button)getNodeFromGridPane(map, j, i);
                    mountain1Node.setOnAction(event -> {
                        selectedImage.setImage(((ImageView) (mountain1Node.getGraphic())).getImage());
                        selectedFood.setText(String.valueOf(TileType.MOUNTAIN1.getFoodCount()));
                        selectedOre.setText(String.valueOf(TileType.MOUNTAIN1.getOreCount()));
                        selectedEnergy.setText(String.valueOf(TileType.MOUNTAIN1.getEnergyCount()));
                        selectedCost.setText(String.valueOf(300));
                        selectedTileType = TileType.MOUNTAIN1;
                        selectedLand = mountain1Node;
                    });
                } else if ((i == 3 && j == 1) || (i == 3 && j == 6) || (i == 4 && j == 2) && (i == 4 && j == 8)) {
                    Button mountain2Node = (Button)getNodeFromGridPane(map, j,i);
                    mountain2Node.setOnAction(event -> {
                        selectedImage.setImage(((ImageView) (mountain2Node.getGraphic())).getImage());
                        selectedFood.setText(String.valueOf(TileType.MOUNTAIN2.getFoodCount()));
                        selectedOre.setText(String.valueOf(TileType.MOUNTAIN2.getOreCount()));
                        selectedEnergy.setText(String.valueOf(TileType.MOUNTAIN2.getEnergyCount()));
                        selectedCost.setText(String.valueOf(300));
                        selectedTileType = TileType.MOUNTAIN2;
                        selectedLand = mountain2Node;
                    });
                } else if ((i == 0 && j == 3) || (i == 1 && j == 4) || (i == 3 && j == 4) || (i == 4 && j == 4)) {
                    Button mountain3Node = (Button)getNodeFromGridPane(map,j,i);
                    mountain3Node.setOnAction(event -> {
                        selectedImage.setImage(((ImageView) (mountain3Node.getGraphic())).getImage());
                        selectedFood.setText(String.valueOf(TileType.MOUNTAIN3.getFoodCount()));
                        selectedOre.setText(String.valueOf(TileType.MOUNTAIN3.getOreCount()));
                        selectedEnergy.setText(String.valueOf(TileType.MOUNTAIN3.getEnergyCount()));
                        selectedCost.setText(String.valueOf(300));
                        selectedTileType = TileType.MOUNTAIN3;
                        selectedLand = mountain3Node;
                    });
                } else if (i == 2 && j == 4){
                    Button enterButton = (Button)getNodeFromGridPane(map,j,i);
                    enterButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                handleButtonAction(event);
                            } catch (IOException ex) {
                            }
                        }
                    });

                } else {
                    Button planeNode = (Button)getNodeFromGridPane(map, j,i);
                    planeNode.setOnAction(event -> {
                        selectedImage.setImage(((ImageView) (planeNode.getGraphic())).getImage());
                        selectedFood.setText(String.valueOf(TileType.PLAIN.getFoodCount()));
                        selectedOre.setText(String.valueOf(TileType.PLAIN.getOreCount()));
                        selectedEnergy.setText(String.valueOf(TileType.PLAIN.getEnergyCount()));
                        selectedCost.setText(String.valueOf(300));
                        selectedTileType = TileType.PLAIN;
                        selectedLand = planeNode;
                    });
                }
            }
        }

        nextPlayer.setOnAction((event) -> {
            if (GameManager.currentPlayer.getMoney() >= 300) {
                if (selectedTileType != null) {
                    if (!selectedLand.isDisable()) {
                        GameManager.currentPlayer.setLandCount(GameManager.currentPlayer.getLandCount()+1);
                        //sets the land to player's color after player buys the territory
                        Color awtColor = GameManager.currentPlayer.getColor();
                        selectedLand.setBackground(new Background(new BackgroundFill(awtColor, CornerRadii.EMPTY, Insets.EMPTY)));
                        //prevents players to buy already owned property
                        selectedLand.setDisable(true);
                    }
                }
                if (GameManager.currentTurn <= GameManager.totalTurnsInitial) {
                    if (GameManager.currentTurn == GameManager.totalTurnsInitial) {
                        skipButt.setDisable(false);
                    }
                    GameManager.initLandSelection(currPlayer, energy, money, ore, food, score);
                } else {
                    playerCount++;
                    if (playerCount + skipCount >= GameManager.players.size()) {
                        playerCount = 0;
                        skipCount = 0;
                    }
                    GameManager.buyLandSelection(GameManager.currentPlayer, currPlayer, energy, money, ore, food, score, true);
                }
            } else {
                nextPlayer.setDisable(true);
                nextPlayer.setText("Insufficient Funds!");
            }
        });

        skipButt.setOnAction((event) -> {
            GameManager.buyLandSelection(GameManager.currentPlayer, currPlayer, energy, money, ore, food, score, false);
            skipCount++;
            if (nextPlayer.isDisable()) {
                nextPlayer.setText("Claim Land!");
                nextPlayer.setDisable(false);
            }
            if ((playerCount == 0  && skipCount >= GameManager.players.size())) {
                try {
                    Stage stage;
                    Parent root;
                    FXMLLoader loader = new FXMLLoader();
                    //get reference to the button's stage
                    stage = (Stage) nextPlayer.getScene().getWindow();//load up other FXML document
                    root = loader.load(getClass().getResource("Map2.fxml"));
                    //create a new scene with root and set the stage

                    Scene scene = new Scene(root, 800, 700);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("pls");
                }
            } else if (playerCount + skipCount >= GameManager.players.size()) {
                playerCount = 0;
                skipCount = 0;
            }
        });
    }

    private javafx.scene.Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        ObservableList<Node> childrens = gridPane.getChildren();
        for (javafx.scene.Node node : childrens) {
            if (node instanceof Button && GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }


    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("TownOptions.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Town Actions");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

}