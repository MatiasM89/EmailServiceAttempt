package AppFXTest;

import Email.EmailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainPageController {
    @FXML
    private TextArea emailBody;
    @FXML
    private TextField emailTo;
    @FXML
    private TextField emailSubject;

    public void sendButton(ActionEvent event) {
        new Thread(() -> {
            EmailSender.sendEmail("email", "password", emailTo.getText(), emailSubject.getText(), emailBody.getText());
        }).start();
    }

}
