package AppFXTest;

import Email.EmailViewer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginPageController {
    @FXML
    private TextField emailInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label credentialsLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void signUp(ActionEvent event) throws IOException {
        String emailAddress = emailInput.getText();
        String password = passwordInput.getText();
        List<Message> messages = null;


        try {
            Folder emailFolder = EmailViewer.receiveMail(emailAddress, password, "smtp.gmail.com");
            emailFolder.open(Folder.READ_ONLY);
            messages = new ArrayList<>(Arrays.asList(emailFolder.getMessages()));

        } catch (NoSuchProviderException e) {
            System.out.println(e.getMessage());
            return;
        } catch (MessagingException e) {
            credentialsLabel.setVisible(true);
            return;
        } catch (Exception e) {
            System.out.println("different exception");
            e.getStackTrace();
            return;
        }

        credentialsLabel.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FX/MainPage.fxml"));
        root = loader.load();

        MainPageController mainPageController = loader.getController();
        mainPageController.emailAddress = emailAddress;
        mainPageController.password = password;
        mainPageController.listOfMessages = messages;
        mainPageController.populateFields(messages.size()-1);
        mainPageController.makeMessageVisible(mainPageController.index-1);


        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/FX/styleScene2.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }
}
