package MULE.Controller;

import MULE.Model.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * Created by SeYeon on 10/14/2015.
 */
public class PopUp{
    @FXML
    private Button okay;

    public final void okayButtonHandle(ActionEvent event) {
        ((Stage) okay.getScene().getWindow()).close();
        GameManager.getMapGrid().setCursor(Cursor.DEFAULT);
    }
}
