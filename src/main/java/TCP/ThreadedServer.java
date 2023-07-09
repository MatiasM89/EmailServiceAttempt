package TCP;

import java.io.*;
import java.net.Socket;

public class ThreadedServer implements Runnable {
    Socket socket;

    ThreadedServer(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
            System.out.println(e.getMessage());
        }
    }
}
