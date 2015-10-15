package M4;

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
    Button okay;
    @FXML
    public void okayButtonHandle(ActionEvent event) {
        ((Stage) okay.getScene().getWindow()).close();
        GameManager.mapGrid.setCursor(Cursor.DEFAULT);
    }
}
