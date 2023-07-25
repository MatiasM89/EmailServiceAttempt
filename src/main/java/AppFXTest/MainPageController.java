package AppFXTest;

import Email.EmailSender;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
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
    public List<Message> listOfMessages;
    public List<Message> listOfMessagesFromReceiveMail;
    @FXML
    private TextField searchField;
    @FXML
    public Button searchButton;
    @FXML
    public Label noResultsText;
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

    public void search() throws MessagingException {
        String searchPhrase = searchField.getText();
        listOfMessages = listOfMessagesFromReceiveMail;

        searchButton.setDisable(false);
        searchButton.setVisible(true);
        noResultsText.setVisible(false);

        List<Message> matchingMessages = new ArrayList<>();

        for (int i = searchPhrase.length(); i >= 3; i--) {
            for (int j = listOfMessages.size() - 1; j >= listOfMessages.size() - 100; j--) {
                if (listOfMessages.get(j).getSubject().contains(searchPhrase)) {
                    matchingMessages.add(listOfMessages.get(j));
                }
            }
            searchPhrase = searchPhrase.substring(0, i);
        }
        if (matchingMessages.isEmpty()) {
            noResultsText.setVisible(true);
            listOfMessages = listOfMessagesFromReceiveMail;
            return;
        }
        Collections.reverse(matchingMessages);
        listOfMessages = matchingMessages;
        populateFields(matchingMessages.size() - 1);

        for (int i = 0; i < matchingMessages.size(); i++) {
            System.out.println(matchingMessages.get(i).getSubject());
        }
    }

    public void cancelSearch() {
        searchButton.setDisable(true);
        searchButton.setVisible(false);
        noResultsText.setVisible(false);
        listOfMessages = listOfMessagesFromReceiveMail;
        populateFields(listOfMessages.size() - 1);
    }

    public void logout(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage currentStage = (Stage) sourceNode.getScene().getWindow();
        currentStage.close();

        FXRun fxRun = new FXRun();
        try {
            fxRun.start(new Stage());
        } catch (Exception e) {
            System.out.println("Error while restarting the application.");
        }
    }

    public void sendButton() {
        new Thread(() -> {
            EmailSender.sendEmail(emailAddress, password, emailTo.getText(), emailSubject.getText(), emailBody.getText());
        }).start();
    }

    public void populateFields(int index) {

        if (index > listOfMessages.size() || index < 0) {
            return;
        }
        this.index = index;
        try {
            try {
                Message message1 = listOfMessages.get(index);

                address1.setText(getSenderEmailAddress(message1));
                date1.setText(getSentDateAsString(message1));
                sub1.setText(message1.getSubject());
            } catch (IndexOutOfBoundsException e) {
                address1.setText("");
                date1.setText("");
                sub1.setText("");
            }

            try {
                Message message2 = listOfMessages.get(index - 1);

                address2.setText(getSenderEmailAddress(message2));
                date2.setText(getSentDateAsString(message2));
                sub2.setText(message2.getSubject());
            } catch (IndexOutOfBoundsException e) {
                address2.setText("");
                date2.setText("");
                sub2.setText("");
            }

            try {
                Message message3 = listOfMessages.get(index - 2);

                address3.setText(getSenderEmailAddress(message3));
                date3.setText(getSentDateAsString(message3));
                sub3.setText(message3.getSubject());
            } catch (IndexOutOfBoundsException e) {
                address3.setText("");
                date3.setText("");
                sub3.setText("");
            }

            try {
                Message message4 = listOfMessages.get(index - 3);

                address4.setText(getSenderEmailAddress(message4));
                date4.setText(getSentDateAsString(message4));
                sub4.setText(message4.getSubject());
            } catch (IndexOutOfBoundsException e) {
                address4.setText("");
                date4.setText("");
                sub4.setText("");
            }

            try {
                Message message5 = listOfMessages.get(index - 4);

                address5.setText(getSenderEmailAddress(message5));
                date5.setText(getSentDateAsString(message5));
                sub5.setText(message5.getSubject());
            } catch (IndexOutOfBoundsException e) {
                address5.setText("");
                date5.setText("");
                sub5.setText("");
            }

            try {
                Message message6 = listOfMessages.get(index - 5);

                address6.setText(getSenderEmailAddress(message6));
                date6.setText(getSentDateAsString(message6));
                sub6.setText(message6.getSubject());
            } catch (IndexOutOfBoundsException e) {
                address6.setText("");
                date6.setText("");
                sub6.setText("");
            }

            try {
                Message message7 = listOfMessages.get(index - 6);

                address7.setText(getSenderEmailAddress(message7));
                date7.setText(getSentDateAsString(message7));
                sub7.setText(message7.getSubject());
            } catch (IndexOutOfBoundsException e) {
                address7.setText("");
                date7.setText("");
                sub7.setText("");
            }

            setCursorsToPopulatedFields();

            sub1.setOnMouseClicked(this::clickHeader);
            sub2.setOnMouseClicked(this::clickHeader);
            sub3.setOnMouseClicked(this::clickHeader);
            sub4.setOnMouseClicked(this::clickHeader);
            sub5.setOnMouseClicked(this::clickHeader);
            sub6.setOnMouseClicked(this::clickHeader);
            sub7.setOnMouseClicked(this::clickHeader);

        } catch (MessagingException e) {
            System.out.println("Failed to extract message information.");
        }
    }

    public void setCursorsToPopulatedFields() {
        sub1.setCursor(Cursor.HAND);
        sub2.setCursor(Cursor.HAND);
        sub3.setCursor(Cursor.HAND);
        sub4.setCursor(Cursor.HAND);
        sub5.setCursor(Cursor.HAND);
        sub6.setCursor(Cursor.HAND);
        sub7.setCursor(Cursor.HAND);
    }


    public void makeMessageVisible(int index) {
        Message msg = listOfMessages.get(index);
        try {
            Object content = msg.getContent();
            if (content instanceof MimeMultipart) {
                MimeMultipart multipart = (MimeMultipart) content;
                int count = multipart.getCount();
                for (int i = 0; i < count; i++) {
                    BodyPart bodyPart = multipart.getBodyPart(i);
                    if (bodyPart.isMimeType("text/plain")) {
                        selectedMailText.setText(bodyPart.getContent().toString());
                    }
                }
            } else if (content instanceof String) {
                selectedMailText.setText((String) content);
            }
            subject.setText(msg.getSubject());
            from.setText(getSenderEmailAddress(msg));
        } catch (Exception e) {
            System.out.println("Exception when making message visible");
        }
    }

    public void scrollRight() {
        populateFields(index - 7);
    }

    public void scrollLeft() {
        populateFields(index + 7);
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
        int j = getIndexFromTextField(sourceTextField);
        try {
            makeMessageVisible(index - j + 1);
        } catch (IndexOutOfBoundsException e) {

        }
    }
}
