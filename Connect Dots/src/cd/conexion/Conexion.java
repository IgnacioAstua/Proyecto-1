package cd.conexion;

import java.util.List;
import cd.logica.Boton;

/**
 * Esta interfaz proporciona métodos para la comunicación y control del juego
 * entre la máquina, el intérprete y el servidor de entrada.
 */
public interface Conexion {

    /**
     * Actualiza el historial de conversación en la vista con el mensaje recibido.
     *
     * @param mensaje El mensaje que se debe agregar al historial de conversación.
     */
    public void actualizar(String mensaje);

    /**
     * Envía un mensaje del chat al otro jugador.
     */
    public void enviar();

    /**
     * Envía un mensaje específico del chat al otro jugador.
     *
     * @param mensaje El mensaje que se va a enviar al otro jugador.
     */
    public void enviar(String mensaje);

    /**
     * Recibe un mensaje o dato y lo envía al intérprete para su procesamiento.
     *
     * @param obj El objeto o mensaje que se debe procesar.
     */
    public void interpretar(Object obj);

    /**
     * Recibe una lista de botones y reorganiza la disposición actual en la vista.
     *
     * @param botones La lista de botones que se va a reorganizar en la vista.
     */
    public void reAcomodar(List<Boton> botones);

    /**
     * Recibe coordenadas y un nombre y actualiza el tablero en la vista.
     *
     * @param x       La coordenada X del botón a actualizar.
     * @param y       La coordenada Y del botón a actualizar.
     * @param nombre2 El nombre del jugador que realiza la acción.
     */
    public void reAcomodar(int x, int y, String nombre2);

    /**
     * Indica que el juego ha terminado y muestra un mensaje de victoria o derrota.
     *
     * @param resultado El mensaje que indica el resultado del juego.
     */
    public void ganar(String resultado);

    /**
     * Permite al jugador decidir si desea continuar o no.
     *
     * @param continuar Indica si el jugador desea continuar o no.
     */
    public void continuar(String continuar);

    /**
     * Verifica si la conexión está establecida.
     */
    public void conectado();
}
