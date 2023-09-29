package cd.logica;

import cd.conexion.Conexion;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase que representa un servidor de entrada. Este servidor se encarga de la
 * comunicación y reenvía los datos recibidos al intérprete para su procesamiento.
 */
public class InServer extends Thread {

    private Socket socket;
    private ServerSocket server;
    private ObjectInputStream flujoEntrada;
    private Conexion conexion;

    /**
     * Constructor de la clase InServer.
     *
     * @param puertoEntradaField El puerto de entrada para el servidor.
     * @param conexion            El enlace de comunicación.
     */
    public InServer(int puertoEntradaField, Conexion conexion) {
        this.conexion = conexion;
        try {
            server = new ServerSocket(puertoEntradaField);
        } catch (IOException ex) {
            System.out.println("Este puerto se encuentra ocupado");
        }
        conexion.enviar("Realizando ping");
    }

    /**
     * Método principal del hilo de ejecución del servidor de entrada.
     */
    @Override
    public void run() {
        while (true) {
            Object input = null;
            try {
                socket = server.accept();
                flujoEntrada = new ObjectInputStream(socket.getInputStream());
                input = flujoEntrada.readObject();
                socket.close();
            } catch (IOException | ClassNotFoundException e) {
                // Manejar cualquier excepción que ocurra durante la comunicación
            }
            // Recibe un mensaje/dato y lo envía al intérprete para su procesamiento
            conexion.interpretar(input);
        }
    }
}

