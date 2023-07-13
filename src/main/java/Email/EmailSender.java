package Email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String senderEmailAddress, String senderEmailPassword, String receiveEmailAddress, String subject, String body) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.user", senderEmailAddress);
        properties.put("mail.smtp.password", senderEmailPassword);

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmailAddress, senderEmailPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmailAddress));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmailAddress));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("should send");

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
}
