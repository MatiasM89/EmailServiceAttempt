package AppFXTest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class FXRun extends Application {

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FX/LoginPage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/FX/styleScene1.css").toExternalForm());
        Image appIcon = new Image("/FX/EmailPNG.png");

        stage.setResizable(false);
        stage.getIcons().add(appIcon);
        stage.setTitle("Emailer!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
