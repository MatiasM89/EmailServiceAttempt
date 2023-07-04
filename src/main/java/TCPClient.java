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
            String messageToSent = keyboard.readLine();
            writer.write(messageToSent);
            System.out.println(reader.readLine());

        } catch (Exception e) {

        }
    }
}
