package AppFXTest;

import Email.EmailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainPageController {
    @FXML
    private TextArea emailBody;
    @FXML
    private TextField emailTo;
    @FXML
    private TextField emailSubject;
    @FXML
    public Label labelUp;
    @FXML
    public Label labelDown;
    @FXML
    public Label selectedMailText;
    public String emailAddress;
    public String password;

    public void sendButton(ActionEvent event) {
        new Thread(() -> {
            EmailSender.sendEmail(emailAddress, password, emailTo.getText(), emailSubject.getText(), emailBody.getText());
        }).start();
    }

}
