package M4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("config_settings.fxml"));
        primaryStage.setTitle("M.U.L.E.");
        Scene scene = new Scene(root, 400, 275);
        scene.getStylesheets().add(getClass().getResource("muleStyle.css").toExternalForm());
        root.getStyleClass().add("pane1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}