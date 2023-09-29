package cd.logica;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Esta clase representa el cliente de salida que se encarga de enviar mensajes al otro jugador.
 */
public class OutServer {
    
    private int puertoSalidaField;
    private Socket cliente;
    private ObjectOutputStream flujo_de_salida;
    private String ipField;
    private Object mensaje;
    
    /**
     * Constructor de la clase OutServer.
     *
     * @param ipField           La dirección IP del destinatario.
     * @param puertoSalidaField El puerto de salida para la comunicación.
     * @param mensaje           El mensaje a enviar.
     */
    public OutServer(String ipField, int puertoSalidaField, String mensaje){
        this.puertoSalidaField=puertoSalidaField;
        this.ipField=ipField;
        this.mensaje=mensaje;
        mensajeSaliente();
    }
    
    /**
     * Envía el mensaje al destinatario a través de la conexión de salida.
     */
    public void mensajeSaliente(){
        try{
            cliente = new Socket(ipField, puertoSalidaField);
            flujo_de_salida = new ObjectOutputStream(cliente.getOutputStream());
            flujo_de_salida.writeObject(mensaje);
            cliente.close();
        }
        catch(Exception ex){}
    }
}
