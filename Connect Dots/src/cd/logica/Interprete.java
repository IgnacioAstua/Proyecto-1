package cd.logica;

import cd.conexion.Conexion;

/**
 * La clase Interprete se encarga de interpretar mensajes recibidos y notificar a la máquina según su contenido.
 */
public class Interprete {

    private Conexion conexion;
    private final String victoria = "Victoria", rendirse = "Rendirse",
            continuar = "continuar", nocontinuar = "nocontinuar", ping = "ping",
            pong = "pong", pingpong = "pingpong";

    /**
     * Constructor de la clase Interprete.
     *
     * @param conexion La instancia de la conexión utilizada para notificar a la máquina.
     */
    public Interprete(Conexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Interpreta un objeto recibido y notifica a la máquina según su contenido.
     *
     * @param obj El objeto que se debe interpretar.
     */
    public void interpretar(Object obj) {
        String verificar = "";

        if (obj.getClass() == verificar.getClass()) {
            String mensaje = obj.toString();
            String[] mensajes = mensaje.split("-");

            if (mensajes[0].equalsIgnoreCase("**")) {
                // Si el mensaje tiene formato "**-MENSAJE", se considera un mensaje de chat.
                conexion.actualizar(mensajes[1]);
            } else {
                if (mensaje.equalsIgnoreCase(victoria)) {
                    // Si el mensaje indica victoria, se notifica a la conexión.
                    conexion.ganar(mensaje);
                } else if (mensaje.equalsIgnoreCase(continuar) || mensaje.equalsIgnoreCase(nocontinuar)) {
                    // Si el mensaje indica continuar o no continuar, se notifica a la conexión.
                    conexion.continuar(mensaje);
                } else if (mensaje.equalsIgnoreCase(ping)) {
                    // Si el mensaje es un ping, se responde con un pong y se notifica la conexión.
                    conexion.enviar(pong);
                } else if (mensaje.equalsIgnoreCase(pong)) {
                    // Si el mensaje es un pong, se verifica la conexión.
                    conexion.conectado();
                    conexion.enviar(pingpong);
                } else if (mensaje.equalsIgnoreCase(pingpong)) {
                    // Si el mensaje es un pingpong, se verifica la conexión.
                    conexion.conectado();
                } else {
                    // Si no es ninguno de los mensajes anteriores, se asume que son coordenadas
                    // de un movimiento y se notifica a la conexión.
                    String[] coord = mensaje.split(" ");
                    int x = Integer.parseInt(coord[0]);
                    int y = Integer.parseInt(coord[1]);
                    String nombre2 = coord[2];
                    conexion.reAcomodar(x, y, nombre2);
                }
            }
        }
    }
}
