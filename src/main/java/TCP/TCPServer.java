package TCP;

import java.net.*;
import java.io.*;

public class TCPServer {
    public static void run() {
        final int portNumber = 8080;

        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
        ) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connected to the server " + socket.getInetAddress());
                new Thread(new ThreadedServer(socket)).start();
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

    }

    public static void main(String[] args) {
        run();
    }
}
