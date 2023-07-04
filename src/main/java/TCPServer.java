import java.net.*;
import java.io.*;

public class TCPServer {

    private final int portNumber = 8080;

    public void run() throws Exception {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket socket = serverSocket.accept();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            String message = "";
            String temporary;
            while ((temporary = reader.readLine()) != null) {
                message = message.concat(temporary);
            }
            writer.write(message+ " address of the message "+socket.getLocalAddress());

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }
    public static void main(String[] args){

    }
}
