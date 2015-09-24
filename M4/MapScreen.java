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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
                        Color awtColor = GameManager.currentPlayer.getColor();
                        ((Button) event.getTarget()).setBackground(new Background(new BackgroundFill(awtColor, CornerRadii.EMPTY, Insets.EMPTY)));
                        riverNode.setDisable(true);
                        selectedFood.setText(String.valueOf(TileType.RIVER.getFoodCount()));
                        selectedOre.setText(String.valueOf(TileType.RIVER.getOreCount()));
                        selectedEnergy.setText(String.valueOf(TileType.RIVER.getEnergyCount()));
                        selectedCost.setText(String.valueOf(300));
                    });

                } else if ((i == 0 && j == 2) || (i == 1 && j == 1) || (i == 2 && j == 8)) {
                    Button mountain1Node = (Button)getNodeFromGridPane(map, j, i);
                    mountain1Node.setOnAction(event -> {
                        selectedImage.setImage(((ImageView) (mountain1Node.getGraphic())).getImage());
                        Color awtColor = GameManager.currentPlayer.getColor();
                        ((Button) event.getTarget()).setBackground(new Background(new BackgroundFill(awtColor, CornerRadii.EMPTY, Insets.EMPTY)));
                        mountain1Node.setDisable(true);
                        selectedFood.setText(String.valueOf(TileType.MOUNTAIN1.getFoodCount()));
                        selectedOre.setText(String.valueOf(TileType.MOUNTAIN1.getOreCount()));
                        selectedEnergy.setText(String.valueOf(TileType.MOUNTAIN1.getEnergyCount()));
                        selectedCost.setText(String.valueOf(300));
                    });
                } else if ((i == 3 && j == 1) || (i == 3 && j == 6) || (i == 4 && j == 2) && (i == 4 && j == 8)) {
                    Button mountain2Node = (Button)getNodeFromGridPane(map, j,i);
                    mountain2Node.setOnAction(event -> {
                        selectedImage.setImage(((ImageView) (mountain2Node.getGraphic())).getImage());
                        Color awtColor = GameManager.currentPlayer.getColor();
                        ((Button) event.getTarget()).setBackground(new Background(new BackgroundFill(awtColor, CornerRadii.EMPTY, Insets.EMPTY)));
                        mountain2Node.setDisable(true);
                        selectedFood.setText(String.valueOf(TileType.MOUNTAIN2.getFoodCount()));
                        selectedOre.setText(String.valueOf(TileType.MOUNTAIN2.getOreCount()));
                        selectedEnergy.setText(String.valueOf(TileType.MOUNTAIN2.getEnergyCount()));
                        selectedCost.setText(String.valueOf(300));
                    });
                } else if ((i == 0 && j == 3) || (i == 1 && j == 4) || (i == 3 && j == 4) || (i == 4 && j == 4)) {
                    Button mountain3Node = (Button)getNodeFromGridPane(map,j,i);
                    mountain3Node.setOnAction(event -> {
                        selectedImage.setImage(((ImageView) (mountain3Node.getGraphic())).getImage());
                        Color awtColor = GameManager.currentPlayer.getColor();
                        ((Button) event.getTarget()).setBackground(new Background(new BackgroundFill(awtColor, CornerRadii.EMPTY, Insets.EMPTY)));
                        mountain3Node.setDisable(true);
                        selectedFood.setText(String.valueOf(TileType.MOUNTAIN3.getFoodCount()));
                        selectedOre.setText(String.valueOf(TileType.MOUNTAIN3.getOreCount()));
                        selectedEnergy.setText(String.valueOf(TileType.MOUNTAIN3.getEnergyCount()));
                        selectedCost.setText(String.valueOf(300));
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
                        Color awtColor = GameManager.currentPlayer.getColor();
                        ((Button) event.getTarget()).setBackground(new Background(new BackgroundFill(awtColor, CornerRadii.EMPTY, Insets.EMPTY)));
                        planeNode.setDisable(true);
                        selectedFood.setText(String.valueOf(TileType.PLAIN.getFoodCount()));
                        selectedOre.setText(String.valueOf(TileType.PLAIN.getOreCount()));
                        selectedEnergy.setText(String.valueOf(TileType.PLAIN.getEnergyCount()));
                        selectedCost.setText(String.valueOf(300));
                    });
                }
            }
        }


        nextPlayer.setOnAction((event) -> {
            if (GameManager.currentTurn < GameManager.totalTurnsInitial) {
                GameManager.initLandSelection(currPlayer, energy, money, ore, food, score);
            } else {
                skipButt.setDisable(false);
                GameManager.buyLandSelection(currPlayer, energy, money, ore, food, score);
//            } else {
//                try {
//                    Stage stage;
//                    Parent root;
//                    FXMLLoader loader = new FXMLLoader();
//                    //get reference to the button's stage
//                    stage = (Stage) nextPlayer.getScene().getWindow();
//                    //load up other FXML document
//                    root = loader.load(getClass().getResource("Map2.fxml"));
//                    //create a new scene with root and set the stage
//
//                    Scene scene = new Scene(root, 800, 700);
//                    stage.setScene(scene);
//                    stage.show();
//                } catch (IOException e) {
//                    System.out.println("Pls");
//                }
            }
        });
        skipButt.setOnAction((event) -> {
            GameManager.buyLandSelection(currPlayer, energy, money, ore, food, score);
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
