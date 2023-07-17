package AppFXTest;

import Email.EmailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    int index;
    List<Message> listOfMessages;
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

    public void logout(ActionEvent event) {
        FXRun fxRun = new FXRun();
        try {
            fxRun.start(new Stage());
        } catch (Exception e) {
            return;
        }
    }

    public void sendButton(ActionEvent event) {
        new Thread(() -> {
            EmailSender.sendEmail(emailAddress, password, emailTo.getText(), emailSubject.getText(), emailBody.getText());
        }).start();
    }

    public void populateFields(List<Message> messages, int index) {
        this.index = index;
        listOfMessages = new ArrayList<>();
        try {
            Message message1 = messages.get(index - 2);
            address1.setText(getSenderEmailAddress(message1));
            date1.setText(getSentDateAsString(message1));
            sub1.setText(message1.getSubject());
            listOfMessages.add(message1);

            Message message2 = messages.get(index - 3);

            address2.setText(getSenderEmailAddress(message2));
            date2.setText(getSentDateAsString(message2));
            sub2.setText(message2.getSubject());
            listOfMessages.add(message2);

            Message message3 = messages.get(index - 4);

            address3.setText(getSenderEmailAddress(message3));
            date3.setText(getSentDateAsString(message3));
            sub3.setText(message3.getSubject());
            listOfMessages.add(message3);

            Message message4 = messages.get(index - 5);

            address4.setText(getSenderEmailAddress(message4));
            date4.setText(getSentDateAsString(message4));
            sub4.setText(message4.getSubject());
            listOfMessages.add(message4);

            Message message5 = messages.get(index - 6);

            address5.setText(getSenderEmailAddress(message5));
            date5.setText(getSentDateAsString(message5));
            sub5.setText(message5.getSubject());
            listOfMessages.add(message5);

            Message message6 = messages.get(index - 7);

            address6.setText(getSenderEmailAddress(message6));
            date6.setText(getSentDateAsString(message6));
            sub6.setText(message6.getSubject());
            listOfMessages.add(message6);

            Message message7 = messages.get(index - 8);

            address7.setText(getSenderEmailAddress(message7));
            date7.setText(getSentDateAsString(message7));
            sub7.setText(message7.getSubject());
            listOfMessages.add(message7);

            Message msg = messages.get(index - 1);
            Object content = msg.getContent();
            MimeMultipart multipart = (MimeMultipart) content;
            int count = multipart.getCount();
            for (int i = 0; i < count; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    selectedMailText.setText(bodyPart.getContent().toString());
                }
            }
            subject.setText(msg.getSubject());
            from.setText(getSenderEmailAddress(msg));

            address1.setOnMouseClicked(mouseEvent -> clickHeader((MouseEvent) mouseEvent.getSource()));
            address2.setOnMouseClicked(mouseEvent -> clickHeader((MouseEvent) mouseEvent.getSource()));
            address3.setOnMouseClicked(mouseEvent -> clickHeader((MouseEvent) mouseEvent.getSource()));
            address4.setOnMouseClicked(mouseEvent -> clickHeader((MouseEvent) mouseEvent.getSource()));
            address5.setOnMouseClicked(mouseEvent -> clickHeader((MouseEvent) mouseEvent.getSource()));
            address6.setOnMouseClicked(mouseEvent -> clickHeader((MouseEvent) mouseEvent.getSource()));
            address7.setOnMouseClicked(mouseEvent -> clickHeader((MouseEvent) mouseEvent.getSource()));

        } catch (MessagingException | IOException e) {
            System.out.println("Failed to extract message information.");
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
        String format = "dd/MM/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = message.getSentDate();
        return dateFormat.format(date);
    }

    private int getIndexFromTextField(TextField textField) {
        String id = textField.getId();
        String indexStr = id.substring(id.length() - 1);
        return Integer.parseInt(indexStr);
    }


    public void clickHeader(MouseEvent event) {
        TextField sourceTextField = (TextField) event.getSource();
        int index = getIndexFromTextField(sourceTextField);
        Message msg = listOfMessages.get(index - 1);
        try {
            Object content = msg.getContent();
            MimeMultipart multipart = (MimeMultipart) content;
            int count = multipart.getCount();
            for (int i = 0; i < count; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                if (bodyPart.isMimeType("text/plain")) {
                    selectedMailText.setText(bodyPart.getContent().toString());
                }
            }
            subject.setText(msg.getSubject());
            from.setText(getSenderEmailAddress(msg));
        }catch (Exception e){
            System.out.println("error swaping main mail");
        }

    }

}
