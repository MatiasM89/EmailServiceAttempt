import java.net.*;
import java.io.*;

public class TCPServer {
    public static void run() {
        final int portNumber = 8080;

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket socket = serverSocket.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            while (true) {
                String message;
                while ((message = reader.readLine()) != null) {
                    writer.write(message + "\n");
                    writer.flush();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

    }

    public static void main(String[] args) {
        run();
    }
}
