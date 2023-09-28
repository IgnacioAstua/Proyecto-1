package cd.conexion;

//Recibe datos para crear la visualización del juego 
public interface StartView {
    public void crear(String nombreField, String ipField, int puertoEntradaField, int puertoSalidaField, String modoPartida);
}
