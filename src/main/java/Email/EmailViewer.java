package Email;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ArrayList;

public class EmailViewer {
    public static void receiveMail(String EmailAddress, String EmailPassword, String hostNameAddress) {
        Properties properties = new Properties();
        properties.put("mail.pop3.host", hostNameAddress);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.ssl.enable", "true");
        properties.put("mail.protocol.store", "pop3");

        Session session = Session.getDefaultInstance(properties);
        try {
            Store storage = session.getStore("pop3s");
            storage.connect(hostNameAddress, EmailAddress, EmailPassword);
            Folder emailFolder = storage.getFolder("INBOX");
            emailFolder.open(1); //read-only
            List<Message> messages = new ArrayList<>(Arrays.asList(emailFolder.getMessages()));
            for(Message msg : messages){
                System.out.println(msg.getSubject());
            }

            storage.close();
            emailFolder.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
