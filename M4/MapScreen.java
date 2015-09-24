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
import javafx.scene.control.Alert;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GameManager.initializeMap();

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
                    });

                } else if ((i == 0 && j == 2) || (i == 1 && j == 1) || (i == 2 && j == 8)) {
                    Button mountain1Node = (Button)getNodeFromGridPane(map, j, i);
                    mountain1Node.setOnAction(event -> {
                        selectedImage.setImage(((ImageView) (mountain1Node.getGraphic())).getImage());
                        Color awtColor = GameManager.currentPlayer.getColor();
                        ((Button) event.getTarget()).setBackground(new Background(new BackgroundFill(awtColor, CornerRadii.EMPTY, Insets.EMPTY)));
                        mountain1Node.setDisable(true);
                    });
                } else if ((i == 3 && j == 1) || (i == 3 && j == 6) || (i == 4 && j == 2) && (i == 4 && j == 8)) {
                    Button mountain2Node = (Button)getNodeFromGridPane(map, j,i);
                    mountain2Node.setOnAction(event -> {
                        selectedImage.setImage(((ImageView) (mountain2Node.getGraphic())).getImage());
                        Color awtColor = GameManager.currentPlayer.getColor();
                        ((Button) event.getTarget()).setBackground(new Background(new BackgroundFill(awtColor, CornerRadii.EMPTY, Insets.EMPTY)));
                        mountain2Node.setDisable(true);
                    });
                } else if ((i == 0 && j == 3) || (i == 1 && j == 4) || (i == 3 && j == 4) || (i == 4 && j == 4)) {
                    Button mountain3Node = (Button)getNodeFromGridPane(map,j,i);
                    mountain3Node.setOnAction(event -> {
                        selectedImage.setImage(((ImageView) (mountain3Node.getGraphic())).getImage());
                        Color awtColor = GameManager.currentPlayer.getColor();
                        ((Button) event.getTarget()).setBackground(new Background(new BackgroundFill(awtColor, CornerRadii.EMPTY, Insets.EMPTY)));
                        mountain3Node.setDisable(true);
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
                    });
                }
            }
        }


        nextPlayer.setOnAction((event) -> {
            if (GameManager.currentTurn < GameManager.totalTurnsInitial + 1) {
                GameManager.initLandSelection(currPlayer, energy, money, ore, food, score);
            } else if (GameManager.currentTurn >= GameManager.totalTurnsInitial - 1){
                GameManager.buyLandSelection(currPlayer, energy, money, ore, food, score);
            } else {
                try {
                    Stage stage;
                    Parent root;
                    FXMLLoader loader = new FXMLLoader();
                    //get reference to the button's stage
                    stage = (Stage) nextPlayer.getScene().getWindow();
                    //load up other FXML document
                    root = loader.load(getClass().getResource("Map2.fxml"));
                    //create a new scene with root and set the stage

                    Scene scene = new Scene(root, 800, 700);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    System.out.println("Pls");
                }
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
