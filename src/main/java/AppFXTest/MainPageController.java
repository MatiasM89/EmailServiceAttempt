package AppFXTest;

import Email.EmailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.List;

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

    @FXML
    private TextField address1, date1, sub1;
    @FXML
    private TextField address2, date2, sub2;
    @FXML
    private TextField address3, date3, sub3;
    @FXML
    private TextField address4, date4, sub4;
    @FXML
    private TextField address5, date5, sub5;
    @FXML
    private TextField address6, date6, sub6;
    @FXML
    private TextField address7, date7, sub7;




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

    public void populateFields(List<Message> messages) {
        try {
            Message message1 = messages.get(240);
            address1.setText(getSenderEmailAddress(message1));
            date1.setText(getSentDateAsString(message1));
            sub1.setText(message1.getSubject());

            Message message2 = messages.get(241);
            address2.setText(getSenderEmailAddress(message2));
            date2.setText(getSentDateAsString(message2));
            sub2.setText(message2.getSubject());

            Message message3 = messages.get(242);
            address3.setText(getSenderEmailAddress(message3));
            date3.setText(getSentDateAsString(message3));
            sub3.setText(message3.getSubject());

            Message message4 = messages.get(243);
            address4.setText(getSenderEmailAddress(message4));
            date4.setText(getSentDateAsString(message4));
            sub4.setText(message4.getSubject());

            Message message5 = messages.get(244);
            address5.setText(getSenderEmailAddress(message5));
            date5.setText(getSentDateAsString(message5));
            sub5.setText(message5.getSubject());

            Message message6 = messages.get(246);
            address6.setText(getSenderEmailAddress(message6));
            date6.setText(getSentDateAsString(message6));
            sub6.setText(message6.getSubject());

            Message message7 = messages.get(245);
            address7.setText(getSenderEmailAddress(message7));
            date7.setText(getSentDateAsString(message7));
            sub7.setText(message7.getSubject());

        } catch (MessagingException e) {
            System.out.println("Failed to extract message information.");
            e.printStackTrace();
        }
    }

    private String getSenderEmailAddress(Message message) throws MessagingException {
        Address[] fromAddresses = message.getFrom();
        if (fromAddresses.length > 0) {
            return fromAddresses[0].toString();
        }
        return "";
    }

    private String getSentDateAsString(Message message) throws MessagingException {
        return message.getSentDate().toString();
    }

}
