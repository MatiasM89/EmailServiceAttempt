package AppFXTest;

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

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import java.io.IOException;
import java.util.Properties;

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
        // login part
        Properties properties = new Properties();
        properties.put("mail.pop3.host", "pop.gmail.com");
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.ssl.enable", "true");
        properties.put("mail.protocol.store", "pop3");
        try {
            Session session = Session.getDefaultInstance(properties);
            Store storage = session.getStore("pop3s");
            storage.connect("pop.gmail.com", emailAddress, password);
        } catch (NoSuchProviderException e) {
            System.out.println(e.getMessage());
            return;
        } catch (MessagingException e) {
            credentialsLabel.setVisible(true);
            return;
        }

        credentialsLabel.setVisible(false);
        root = FXMLLoader.load(getClass().getResource("/FX/MainPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
