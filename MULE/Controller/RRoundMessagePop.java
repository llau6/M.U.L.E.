package MULE.Controller;

import MULE.Model.GameManager;
import MULE.Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by SeYeon on 12/4/2015.
 */
public class RRoundMessagePop {
    @FXML
    Button okayButton;
    @FXML
    Label taxMessage;
    int[] taxes;
    public void initialize() {
        taxes = new int[3];
        for (int i = 0; i < taxes.length; i++) {
            taxes[i] = 5 + (i * 2);
        }
        int rand = (int)(Math.random() * taxes.length);
        taxMessage.setText(taxes[rand] + ".0% will be deducted from your money!");
        initiateEvent(rand);
        okayButton.setOnMouseClicked((event) -> {
            ((Stage) okayButton.getScene().getWindow()).close();
            MapScreen.updateResources();
        });
    }

    private void initiateEvent(int rand) {
        Player p = RRoundPopUp.getScurPlayer();
        int taxDollar = (int) (p.getMoney() * (taxes[rand]/100.0));
        p.setMoney(p.getMoney() - taxDollar);
    }
}
