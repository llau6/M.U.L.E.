package MULE.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Class to Start the Application
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("config_settings.fxml"));
        //Scene scene = new Scene(root, 400, 275);
        Parent root = FXMLLoader.load(getClass().getResource("../View/SplashScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("M.U.L.E.");
        scene.getStylesheets().add(getClass().getResource("muleStyle.css").toExternalForm());
        root.getStyleClass().add("pane1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Launches the argument
     * @param args System console arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
