package AppFXTest;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

public class LoginPageController {
    @FXML
    private TextField emailInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label credentialsLabel;

    public void signUp() {
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
        } catch (MessagingException e) {
            credentialsLabel.setVisible(true);
        }

    }
}
