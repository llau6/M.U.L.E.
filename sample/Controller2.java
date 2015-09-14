package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by SeYeon on 9/12/2015.
 */
public class Controller2 implements Initializable{
    @FXML
    Label playerName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Controller control = new Controller();
        String name = control.getName();
        System.out.println(name);
//        playerName.setText(name);
    }
}
