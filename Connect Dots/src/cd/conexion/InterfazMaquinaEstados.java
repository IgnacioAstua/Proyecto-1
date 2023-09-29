package cd.conexion;

import javax.swing.JButton;
import cd.logica.Boton;
import cd.visualizacion.View;

/**
 * Interfaz que enlaza la vista principal del juego con la máquina de estados.
 * Permite a la máquina obtener un número de elementos de la vista para
 * modificarlos según se necesite.
 */
public interface InterfazMaquinaEstados {

    /**
     * Obtiene el objeto de la vista principal para poder utilizarlo.
     *
     * @return La instancia de la vista principal.
     */
    public View obtenerVista();

    /**
     * Crea la máquina de estados del juego con la configuración proporcionada.
     *
     * @param nombreField        El nombre del jugador.
     * @param ipField            La dirección IP del servidor.
     * @param puertoEntradaField El puerto de entrada.
     * @param puertoSalidaField  El puerto de salida.
     * @param modoPartida        El modo de partida (por ejemplo, modo de un jugador, multijugador).
     */
    public void crearMaquina(String nombreField, String ipField, int puertoEntradaField, int puertoSalidaField,
                             String modoPartida);

    /**
     * Actualiza y muestra los puntajes de los jugadores en la vista.
     *
     * @param uno    Puntuación del jugador 1.
     * @param dos    Puntuación del jugador 2.
     */
    public void puntajes(Integer uno, Integer dos);

    /**
     * Actualiza los nombres de los jugadores en la vista.
     *
     * @param player1 Nombre del jugador 1.
     * @param player2 Nombre del jugador 2.
     */
    public void nombres(String player1, String player2);

    /**
     * Agrega un botón al tablero de juego en la vista.
     *
     * @param boton El botón que se va a agregar.
     */
    public void agregar(Boton boton);

    /**
     * Obtiene el objeto de botón en una posición específica del tablero.
     *
     * @param x Coordenada x del botón.
     * @param y Coordenada y del botón.
     * @return El botón en la posición especificada.
     */
    public Boton getBoton(int x, int y);

    /**
     * Obtiene la matriz completa de botones que representa el tablero del juego.
     *
     * @return La matriz de botones del tablero.
     */
    public Boton[][] getMatriz();

    /**
     * Establece el historial de chat en la vista.
     *
     * @param history El historial de chat a establecer.
     */
    public void setHistory(String history);

    /**
     * Obtiene el historial de chat actual.
     *
     * @return El historial de chat actual.
     */
    public String getHistory();

    /**
     * Establece el mensaje de chat en la vista.
     *
     * @param message El mensaje de chat a establecer.
     */
    public void setMessage(String message);

    /**
     * Obtiene el mensaje de chat actual.
     *
     * @return El mensaje de chat actual.
     */
    public String getMessage();

    /**
     * Obtiene el botón utilizado para enviar mensajes en la vista.
     *
     * @return El botón de envío de mensajes.
     */
    public JButton getEnviar();

    /**
     * Obtiene el botón utilizado para rendirse en el juego en la vista.
     *
     * @return El botón de rendirse.
     */
    public JButton getRendir();
}
