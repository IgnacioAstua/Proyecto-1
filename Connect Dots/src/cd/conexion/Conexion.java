package cd.conexion;
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
    public void enviar(String obj);

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
    public void reAcomodar(Boton[][] matriz);

    /**
     * Recibe coordenadas y un nombre y actualiza el tablero en la vista.
     *
     * @param x             La coordenada X del botón a actualizar.
     * @param y             La coordenada Y del botón a actualizar.
     * @param nombreField2  El nombre del jugador que realiza la acción.
     */
    public void reAcomodar(int x, int y, String nombreField2);

    /**
     * Indica que el juego ha terminado y muestra un mensaje de victoria o derrota.
     *
     * @param resultado El mensaje que indica el resultado del juego.
     */
    public void ganar(String maybe);

    /**
     * Permite al jugador decidir si desea continuar o no.
     *
     * @param continuar Indica si el jugador desea continuar o no.
     */
    public void continuar(String cont);

    /**
     * Verifica si la conexión está establecida.
     */
    public void conectado();
}
