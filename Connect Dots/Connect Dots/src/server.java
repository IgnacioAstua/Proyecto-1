import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class server {
    private static final int PORT = 12345;
    private static List<Socket> clients = new ArrayList<>();
    private static List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("El servidor del juego Connect Dots está en funcionamiento...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // Espera a que un cliente se conecte
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket);
                // Almacena el socket del cliente y crea un PrintWriter para enviar datos al cliente
                clients.add(clientSocket);
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                clientWriters.add(writer);

                // Crea un hilo para manejar las comunicaciones con el cliente.
                Thread clientThread = new Thread(new ClientHandler(clientSocket, writer));
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Clase interna para manejar la comunicación con un cliente específico.
    private static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter writer;

        public ClientHandler(Socket socket, PrintWriter writer) {
            this.socket = socket;
            this.writer = writer;
        }

        @Override
        public void run() {
            try (Scanner scanner = new Scanner(socket.getInputStream())) {
                while (true) {
                    if (scanner.hasNextLine()) {
                        String clientMessage = scanner.nextLine();
                        System.out.println("Recibido del cliente: " + clientMessage);

                        // Procesa el mensaje del cliente y actualiza el estado del juego aquí.

                        // Difunde el estado del juego a todos los clientes.
                        broadcastGameState("Estado del juego actualizado...");

                        //Implementar la lógica del juego y la gestión del estado aquí.
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //Envía un mensaje a todos los clientes conectados al servidor
    private static void broadcastGameState(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }
}


