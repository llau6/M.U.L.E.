package MULE.Controller;

import MULE.Model.GameManager;
import MULE.Model.Player;
import MULE.Model.Race;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//http://code.makery.ch/blog/javafx-8-event-handling-examples/ (ComboBox)
//https://blogs.oracle.com/jmxetc/entry/connecting_scenebuilder_edited_fxml_to (controller and fxml)
//pics: http://strategywiki.org/wiki/M.U.L.E./Getting_Started
//http://www.javafxtutorials.com/tutorials/switching-to-different-screens-in-javafx-and-fxml/  (switching scenes)
//https://docs.oracle.com/javafx/2/ui_controls/text-field.htm (Using TextField)
//http://stackoverflow.com/questions/10134856/javafx-2-0-how-to-application-getparameters-in-a-controller-java-file/10136403#10136403 (static variable to pass to other controller)

public class PlayerInitScreen implements Initializable {

    @FXML
    TextField name;

    @FXML
    Label playerID;

    private static String playerName;
    private static Color color;
    public static Race raceChosen;
    public final static int playernum = ConfigScreen.getPlayernum();
    private static int iteration = 1;

    public static int count;
    @FXML
    Button myButton;

    @FXML
    Label sameName;
    @FXML
    Label sameColor;

    @FXML
    private ComboBox<Race> raceBox;
    private ObservableList<Race> comboData = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> colorBox;

    @FXML
    private ImageView raceView;

    @FXML
    Label require_name;
    @FXML
    Label require_race;
    @FXML
    Label require_color;

    @FXML
    Pane pane2;
    @FXML
    GridPane test;

    /**
     *
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        test.getStylesheets().add(getClass().getResource("muleStyle.css").toExternalForm());
        pane2.getStyleClass().add("pane2");

        playerID.setText("Player: " + iteration);

        comboData.add(new Race("Packer", "MULE/View/Images/MULE_Packer.png"));
        comboData.add(new Race("Spheroid", "MULE/View/Images/MULE_Spheroid.png"));
        comboData.add(new Race("Humanoid", "MULE/View/Images/MULE_Humanoid.png"));
        comboData.add(new Race("Leggite", "MULE/View/Images/MULE_Leggite.png"));
        comboData.add(new Race("Flapper", "MULE/View/Images/MULE_Flapper.png"));
        comboData.add(new Race("Bonzoid", "MULE/View/Images/MULE_Bonzoid.png"));
        comboData.add(new Race("Mechtron", "MULE/View/Images/MULE_Mechtron.png"));
        comboData.add(new Race("Gollumer", "MULE/View/Images/MULE_Gollumer.png"));
        raceBox.setItems(comboData);

        //how to use Race data
        raceBox.setCellFactory((combobox) -> {
            return new ListCell<Race>() {
                @Override
                protected void updateItem(Race item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getName());
                    }
                }
            };
        });

        //rendering of selected value in ComboBox
        raceBox.setConverter(new StringConverter<Race>() {
            @Override
            public String toString(Race object) {
                if (object == null) {
                    return null;
                } else {
                    return object.getName();
                }
            }
            @Override
            public Race fromString(String string) {
                    return null;
                }
        });

        raceBox.setOnAction((event) -> {
            Race selected = raceBox.getSelectionModel().getSelectedItem();
            raceView.setImage(selected.getImage());
        });
    }

    /**
     * When button is clicked, show a new scene
     * @param event button is clicked
     * @throws java.io.IOException
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        Object[] playersArray = GameManager.players.toArray();
        boolean isSameName = false;
        boolean isSameColor = false;
        if (name.getText() != null && !name.getText().isEmpty()
                && !colorBox.getSelectionModel().isEmpty() && !raceBox.getSelectionModel().isEmpty()) {
            for (int i = 0; i < playersArray.length; i++) {
                if (((Player) playersArray[i]).getName().equals(name.getText())) {
                    isSameName = true;
                } else if (((Player) playersArray[i]).getColor().equals(Color.valueOf(colorBox.getSelectionModel().getSelectedItem()))) {
                    isSameColor = true;
                }
            }
            //Save name, color, and race
            if (iteration == 1 || (!isSameName && !isSameColor)) {
                playerName = name.getText();
                color = Color.valueOf(colorBox.getSelectionModel().getSelectedItem());
                raceChosen = raceBox.getSelectionModel().getSelectedItem();
                Player player = new Player(playerName, color);
                GameManager.players.add(player);
                GameManager.getOrderedPlayers().add(player);
                if (iteration < playernum) {
                    iteration++;
                    FXMLLoader loader = new FXMLLoader();
                    //get reference to the button's stage
                    stage = (Stage) myButton.getScene().getWindow();
                    //load up other FXML document
                    root = loader.load(getClass().getResource("../View/playerInitScreen.fxml"));
                    //create a new scene with root and set the stage
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    FXMLLoader loader = new FXMLLoader();
                    //get reference to the button's stage
                    stage = (Stage) myButton.getScene().getWindow();
                    //load up other FXML document
                    root = loader.load(getClass().getResource("../View/MapScreen.fxml"));
                    //create a new scene with root and set the stage
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } else {
                if (isSameName) {
                    sameName.setOpacity(1.0);
                    sameColor.setOpacity(0);
                } else if (isSameColor) {
                    sameName.setOpacity(0.0);
                    sameColor.setOpacity(1.0);
                }
            }
        } else {
            require_name.setOpacity(1.0);
            require_race.setOpacity(1.0);
            require_color.setOpacity(1.0);
        }
    }
}
