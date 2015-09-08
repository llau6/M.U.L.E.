package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

//http://code.makery.ch/blog/javafx-8-event-handling-examples/ (ComboBox)
//https://blogs.oracle.com/jmxetc/entry/connecting_scenebuilder_edited_fxml_to (controller and fxml)
//pics: http://strategywiki.org/wiki/M.U.L.E./Getting_Started

public class Controller {
    @FXML
    private ComboBox<Race> raceBox;
    private ObservableList<Race> comboData = FXCollections.observableArrayList();

    @FXML
    private ImageView raceView;

    @FXML
    void initialize() {

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

        //redering of selected value in ComboBox
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

}
