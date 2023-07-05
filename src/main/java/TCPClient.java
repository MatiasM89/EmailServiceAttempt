import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("127.0.0.1", 8080);
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            String messageToSent;
            while (true) {
                if (keyboard.ready()) {
                    messageToSent = keyboard.readLine();
                    if (messageToSent.contains("exit") && messageToSent.length() == 4) {
                        break;
                    }
                    writer.write(messageToSent + "\n");
                    writer.flush();
                }
                if (reader.ready()) {
                    System.out.println(reader.readLine());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
