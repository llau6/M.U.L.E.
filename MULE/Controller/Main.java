package MULE.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public final void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/SplashScreen.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("M.U.L.E.");
            scene.getStylesheets().add(getClass().getResource("muleStyle.css").toExternalForm());
            root.getStyleClass().add("pane1");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("oops");
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
