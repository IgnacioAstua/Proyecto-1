package cd.logica;

import javax.swing.JButton;

/**
 * Clase que representa un botón del juego.
 * Esta clase extiende JButton para proporcionar funcionalidades
 * específicas para el juego.
 */
public class Boton extends JButton {
    
    /**
     * Indica si este botón ha sido utilizado o no.
     */
    private boolean taken = false;
    
    /**
     * Indica si este botón representa un cuadro del juego.
     */
    private boolean square = false;
    
    /**
     * Guarda el nombre del jugador que cerró el cuadro, en caso de ser un cuadro.
     */
    private String owner;
    
    /**
     * Constructor por defecto de la clase Boton.
     */
    public Boton() {}
    
    /**
     * Constructor que permite configurar si el botón representa un cuadro.
     * 
     * @param square Indica si el botón es un cuadro del juego.
     */
    public Boton(boolean square) {
        this.square = square;
    }
    
    /**
     * Obtiene si este botón ha sido utilizado.
     * 
     * @return true si el botón ha sido utilizado, false de lo contrario.
     */
    public boolean getTaken() {
        return taken;
    }
    
    /**
     * Establece si este botón ha sido utilizado.
     * 
     * @param taken true si el botón ha sido utilizado, false de lo contrario.
     */
    public void setTaken(boolean taken) {
        this.taken = taken;
    }
    
    /**
     * Obtiene el nombre del jugador que cerró este cuadro.
     * 
     * @return El nombre del jugador propietario del cuadro.
     */
    public String getOwner() {
        return owner;
    }
    
    /**
     * Establece el nombre del jugador owner de este cuadro y muestra su inicial en el botón.
     * 
     * @param owner El nombre del jugador owner del cuadro.
     */
    public void setOwner(String owner) {
        this.owner = owner;
        try {
            // El botón mostrará el primer carácter del nombre del owner
            this.setText(Character.toString(owner.charAt(0)));
        } catch (Exception e) {
            this.setText(null);
        }
    }
    
    /**
     * Obtiene si este botón representa un cuadro del juego.
     * 
     * @return true si el botón es un cuadro, false de lo contrario.
     */
    public boolean getSquare() {
        return square;
    }
    
    /**
     * Establece si este botón representa un cuadro del juego.
     * 
     * @param square true si el botón es un cuadro, false de lo contrario.
     */
    public void setSquare(boolean square) {
        this.square = square;
    }
}
