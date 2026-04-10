
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private static final int PORT = 5000;
    private static final Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("🟢 Chat Server Started on port " + PORT);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println(" New client connected: " + socket);

                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
            }

        } catch (IOException e) {
            System.out.println(" Server error: " + e.getMessage());
        }
    }

    static class ClientHandler extends Thread {

        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String username;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                out = new PrintWriter(socket.getOutputStream(), true);

                out.println("Enter your username:");
                username = in.readLine();

                synchronized (clientWriters) {
                    clientWriters.add(out);
                }

                broadcast("🔵 " + username + " joined the chat.");

                String message;

                while ((message = in.readLine()) != null) {

                    if (message.equalsIgnoreCase("/exit")) {
                        break;
                    }

                    broadcast(username + ": " + message);
                }

            } catch (IOException e) {
                System.out.println("❌ Connection lost.");
            } finally {

                if (username != null) {
                    broadcast("🔴 " + username + " left the chat.");
                }

                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }

                try {
                    socket.close();
                } catch (IOException e) {
                    System.out.println(" Error closing socket.");
                }
            }
        }

        private void broadcast(String message) {
            synchronized (clientWriters) {
                for (PrintWriter writer : clientWriters) {
                    writer.println(message);
                }
            }
        }
    }
}
