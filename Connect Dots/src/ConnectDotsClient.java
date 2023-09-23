import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ConnectDotsClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ConnectDotsClient(String serverAddress, int serverPort) {
        try {
            socket = new Socket(serverAddress, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAction(JSONObject action) {
        out.println(action.toString());
    }

    public JSONObject receiveGameState() {
        try {
            String gameState = in.readLine();
            if (gameState != null) {
                return (JSONObject) new JSONParser().parse(gameState);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void startGameLoop() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                JSONObject gameState = receiveGameState();
                if (gameState != null) {
                    // Actualiza la interfaz gráfica con el estado del juego
                    updateGUI(gameState);
                }
            }
        }, 0, 1000); // Actualiza cada segundo (ajusta según sea necesario)
    }

    private void updateGUI(JSONObject gameState) {
        // Implementar la lógica para actualizar la interfaz gráfica
        // con el estado del juego recibido en gameState
    }

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1"; // Dirección del servidor
        int serverPort = 12345; // Puerto del servidor

        ConnectDotsClient client = new ConnectDotsClient(serverAddress, serverPort);
        client.startGameLoop();

        // Implementar la interacción del usuario y enviar acciones al servidor en JSON
        // Ejemplo:
        // JSONObject action = new JSONObject();
        // action.put("player", "PlayerName");
        // action.put("actionType", "DrawLine");
        // action.put("startPoint", "A1");
        // action.put("endPoint", "A2");
        // client.sendAction(action);
    }
}

