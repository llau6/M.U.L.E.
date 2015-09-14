package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//http://code.makery.ch/blog/javafx-8-event-handling-examples/ (ComboBox)
//https://blogs.oracle.com/jmxetc/entry/connecting_scenebuilder_edited_fxml_to (controller and fxml)
//pics: http://strategywiki.org/wiki/M.U.L.E./Getting_Started
//https://us-mg6.mail.yahoo.com/neo/launch#9530  (switching scenes)
//https://docs.oracle.com/javafx/2/ui_controls/text-field.htm (Using TextField)

public class Controller implements Initializable {

    @FXML
    TextField name;

    String playerName;

    @FXML
    Button myButton;

    @FXML
    private ComboBox<Race> raceBox;
    private ObservableList<Race> comboData = FXCollections.observableArrayList();

    @FXML
    private ImageView raceView;

    /**
     *
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboData.add(new Race("Packer", "sample/images/MULE_Packer.png"));
        comboData.add(new Race("Spheroid", "sample/images/MULE_Spheroid.png"));
        comboData.add(new Race("Humanoid", "sample/images/MULE_Humanoid.png"));
        comboData.add(new Race("Leggite", "sample/images/MULE_Leggite.png"));
        comboData.add(new Race("Flapper", "sample/images/MULE_Flapper.png"));
        comboData.add(new Race("Bonzoid", "sample/images/MULE_Bonzoid.png"));
        comboData.add(new Race("Mechtron", "sample/images/MULE_Mechtron.png"));
        comboData.add(new Race("Gollumer", "sample/images/MULE_Gollumer.png"));
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

        //Save name
        playerName = name.getText();
        System.out.println("1:  " + playerName);

    }

    /**
     * When button is clicked, show a new scene
     * @param event button is clicked
     * @throws IOException
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        if (name.getText() != null && !name.getText().isEmpty()) {
            Stage stage;
            Parent root;
            playerName = name.getText();
            System.out.println(playerName);
            FXMLLoader loader = new FXMLLoader();
            //get reference to the button's stage
            stage = (Stage) myButton.getScene().getWindow();
            //load up other FXML document
            root = loader.load(getClass().getResource("sample2.fxml"));
            //create a new scene with root and set the stage
            Scene scene = new Scene(root, 400, 275);
            stage.setScene(scene);
            stage.show();
        } else {
            System.out.println("Name!");
        }
    }

    public String getName() {
        return playerName;
    }
}
