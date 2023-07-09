package AppFXTest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class FXRun extends Application {

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FX/FXRun.fxml"));
        Scene scene = new Scene(root);
        Image appIcon = new Image("/FX/EmailPNG.png");


        stage.setWidth(1200);
        stage.setHeight(750);
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
