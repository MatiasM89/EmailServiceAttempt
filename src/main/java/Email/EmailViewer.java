package Email;

import com.sun.mail.pop3.POP3Folder;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Arrays;
import java.util.Properties;

public class EmailViewer {
    public static void receiveMail(String EmailAddress, String EmailPassword, String hostNameAddress) {
        Properties properties = new Properties();
        properties.put("mail.pop3.host", hostNameAddress);
        properties.put("mail.pop3.port", "995");
        properties.put("mail.pop3.ssl.enable", "true");
        properties.put("mail.store.protocol", "pop3");

        Session session = Session.getDefaultInstance(properties);
        try {
            Store storage = session.getStore("pops3");
            storage.connect(hostNameAddress, EmailAddress, EmailPassword);
            Folder emailFolder = storage.getFolder("INBOX");
            emailFolder.open(1); //read-only

        } catch (Exception e) {
            System.out.println(e.getMessage() + " trace " + Arrays.toString(e.getStackTrace()));
        }

    }
}
