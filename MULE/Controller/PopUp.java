package MULE.Controller;

import MULE.Model.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * Manages Pop up conditions
 */
public class PopUp {
    @FXML
    private Button okay;

    /**
     * Sets the cursor to default upon button press
     * @param event Action event
     */
    @FXML
    public final void okayButtonHandle(ActionEvent event) {
        ((Stage) okay.getScene().getWindow()).close();
        GameManager.getMapGrid().setCursor(Cursor.DEFAULT);
    }
}
