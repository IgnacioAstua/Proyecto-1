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
    private boolean usado = false;
    
    /**
     * Indica si este botón representa un cuadro del juego.
     */
    private boolean esCuadro = false;
    
    /**
     * Guarda el nombre del jugador que cerró el cuadro, en caso de ser un cuadro.
     */
    private String propietario;
    
    /**
     * Constructor por defecto de la clase Boton.
     */
    public Boton() {}
    
    /**
     * Constructor que permite configurar si el botón representa un cuadro.
     * 
     * @param esCuadro Indica si el botón es un cuadro del juego.
     */
    public Boton(boolean esCuadro) {
        this.esCuadro = esCuadro;
    }
    
    /**
     * Obtiene si este botón ha sido utilizado.
     * 
     * @return true si el botón ha sido utilizado, false de lo contrario.
     */
    public boolean estaUsado() {
        return usado;
    }
    
    /**
     * Establece si este botón ha sido utilizado.
     * 
     * @param usado true si el botón ha sido utilizado, false de lo contrario.
     */
    public void setUsado(boolean usado) {
        this.usado = usado;
    }
    
    /**
     * Obtiene el nombre del jugador que cerró este cuadro.
     * 
     * @return El nombre del jugador propietario del cuadro.
     */
    public String getPropietario() {
        return propietario;
    }
    
    /**
     * Establece el nombre del jugador propietario de este cuadro y muestra su inicial en el botón.
     * 
     * @param propietario El nombre del jugador propietario del cuadro.
     */
    public void setPropietario(String propietario) {
        this.propietario = propietario;
        try {
            // El botón mostrará el primer carácter del nombre del propietario
            this.setText(Character.toString(propietario.charAt(0)));
        } catch (Exception e) {
            this.setText(null);
        }
    }
    
    /**
     * Obtiene si este botón representa un cuadro del juego.
     * 
     * @return true si el botón es un cuadro, false de lo contrario.
     */
    public boolean esCuadro() {
        return esCuadro;
    }
    
    /**
     * Establece si este botón representa un cuadro del juego.
     * 
     * @param esCuadro true si el botón es un cuadro, false de lo contrario.
     */
    public void setEsCuadro(boolean esCuadro) {
        this.esCuadro = esCuadro;
    }
}
