package cd.conexion;

/**
 * Interfaz que define el contrato para recibir datos y crear la visualización del juego.
 */
public interface StartView {
    
    /**
     * Crea la visualización del juego utilizando los datos proporcionados.
     *
     * @param nombreField      El nombre del jugador.
     * @param ipField          La dirección IP para la comunicación.
     * @param puertoEntradaField El puerto de entrada para la comunicación.
     * @param puertoSalidaField  El puerto de salida para la comunicación.
     * @param modoPartida      El modo de partida seleccionado.
     */
    public void crear(String nombreField, String ipField, int puertoEntradaField, int puertoSalidaField, String modoPartida);
}

