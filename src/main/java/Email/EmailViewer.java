package Email;

import javax.mail.*;
import java.util.Properties;


public class EmailViewer {
    public static Folder receiveMail(String EmailAddress, String EmailPassword, String hostNameAddress) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.imap.host", hostNameAddress);
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.ssl.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailAddress, EmailPassword);
            }
        });
        Store storage = session.getStore("imap");
        storage.connect(hostNameAddress, EmailAddress, EmailPassword);

        return storage.getFolder("INBOX");
    }
}
