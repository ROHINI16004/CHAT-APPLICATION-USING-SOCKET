
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {

        try (
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
        ) {

            System.out.println(in.readLine());

            String username = scanner.nextLine();
            out.println(username);

            Thread receiveMessages = new Thread(() -> {
                try {
                    String serverMessage;

                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }

                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });

            receiveMessages.start();

            while (true) {
                String message = scanner.nextLine();
                out.println(message);

                if (message.equalsIgnoreCase("/exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Unable to connect to server: " + e.getMessage());
        }
    }
}
