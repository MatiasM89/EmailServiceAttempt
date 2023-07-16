package AppFXTest;

import Email.EmailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainPageController {
    @FXML
    private TextArea emailBody;
    @FXML
    private TextField emailTo;
    @FXML
    private TextField emailSubject;
    @FXML
    public TextField subject;
    @FXML
    public TextField from;
    @FXML
    public TextArea selectedMailText;
    public String emailAddress;
    public String password;

    public void logout(ActionEvent event){
        FXRun fxRun = new FXRun();
        try{
            fxRun.start(new Stage());
        }catch (Exception e){
            return;
        }
    }

    public void sendButton(ActionEvent event) {
        new Thread(() -> {
            EmailSender.sendEmail(emailAddress, password, emailTo.getText(), emailSubject.getText(), emailBody.getText());
        }).start();
    }

}
