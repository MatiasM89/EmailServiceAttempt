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

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        List<Message> messages;

        Properties properties = new Properties();
        properties.put("mail.pop3.host", "pop.gmail.com");
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.ssl.enable", "true");
        properties.put("mail.protocol.store", "pop3");
        try {
            Session session = Session.getDefaultInstance(properties);
            Store storage = session.getStore("pop3s");
            storage.connect("pop.gmail.com", emailAddress, password);
            Folder emailFolder = storage.getFolder("INBOX");
            emailFolder.open(1); //read-only
            messages = new ArrayList<>(Arrays.asList(emailFolder.getMessages()));

        } catch (NoSuchProviderException e) {
            System.out.println(e.getMessage());
            return;
        } catch (MessagingException e) {
            credentialsLabel.setVisible(true);
            return;
        }
        credentialsLabel.setVisible(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FX/MainPage.fxml"));
        root = loader.load();

        MainPageController mainPageController = loader.getController();
        mainPageController.emailAddress = emailAddress;
        mainPageController.password = password;
        try {
            Message msg = messages.get(250);
            Object content = msg.getContent();
            MimeMultipart multipart = (MimeMultipart) content;
            int count = multipart.getCount();
            for (int i = 0; i < count; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    mainPageController.selectedMailText.setText(bodyPart.getContent().toString());
                }
            }
            mainPageController.labelDown.setText(msg.getSubject());
        } catch (MessagingException e) {

        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/FX/styleScene2.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

    }
}
