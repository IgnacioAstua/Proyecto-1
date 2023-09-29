package cd.visualizacion;

import cd.conexion.ViewMaquinaEstados;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import cd.logica.Boton;
//import cd.logica.MaquinaEstados;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa la vista principal del juego "Connect Dots".
 * Aquí se gestiona la interfaz gráfica del juego, incluyendo el tablero de juego,
 * el historial de chat, los mensajes, los puntajes y los botones para enviar mensajes y rendirse.
 */
public class View extends JFrame implements VistaMaquinaEstados, KeyListener, ActionListener {
    
    private List<Boton> botones = new ArrayList<>(); // Lista de botones para el tablero de juego
    private JPanel CrearPartida, UnirsePartida;
    private JButton send, help, surrender; // Botones para enviar mensajes, obtener ayuda y rendirse
    private JTextPane history, message; // Paneles de texto para el historial de chat y mensajes
    private JLabel player1, player2, puntaje1, puntaje2; // Etiquetas para los nombres de los jugadores y sus puntajes
    private JScrollPane sp1, sp2; // Barras de desplazamiento para los paneles de texto
    private MaquinaEstados machine; // Instancia de la clase Maquina que maneja la lógica del juego
    
    /**
     * Constructor de la clase View.
     * Inicializa la vista del juego con los parámetros proporcionados.
     *
     * @param nombreField       El nombre del jugador.
     * @param ipField           La dirección IP del servidor.
     * @param puertoEntradaField El puerto de entrada para la comunicación.
     * @param puertoSalidaField El puerto de salida para la comunicación.
     * @param modoPartida       El modo de partida (Crear o Unirse).
     */
    public View(String  nombreField, String ipField, int puertoEntradaField, int puertoSalidaField, String modoPartida){
        // Crear una instancia de la máquina y establecer la configuración inicial
        crearMaquina(nombreField, ipField, puertoEntradaField, puertoSalidaField, modoPartida);
        // Inicializar la interfaz gráfica
        inicializar();
    } 

    /**
     * Inicializa la interfaz gráfica del juego.
     */
    public void inicializar(){
        setSize(805, 600); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cerrar la ventana al salir
        setResizable(false); // Evitar que se redimensione
        setBackground(Color.WHITE); // Fondo blanco
        
        CrearPartida = new JPanel(); // Panel para el tablero de juego
        CrearPartida.setLayout(null);
        // Acomodar los botones en el tablero de juego usando la instancia de la máquina
        machine.acomodar(botones, true);
        
        history = new JTextPane(); // Panel de texto para el historial de chat
        sp1 = new JScrollPane(history); // Barra de desplazamiento para el historial de chat
        sp1.setBounds(510, 10, 280, 450); // Ubicación y tamaño
        add(sp1);
        
        message = new JTextPane(); // Panel de texto para escribir mensajes
        message.addKeyListener(this); // Agregar un KeyListener para habilitar el botón "Enviar"
        sp2 = new JScrollPane(message); // Barra de desplazamiento para el panel de mensajes
        sp2.setBounds(510, 470, 230, 30); // Ubicación y tamaño
        add(sp2);
        
        send = new JButton(">>"); // Botón para enviar mensajes
        send.setBounds(740, 470, 49, 29); // Ubicación y tamaño
        send.setContentAreaFilled(false); // No llenar el área del botón
        send.addActionListener(machine); // Asociar el botón con la acción de la máquina
        send.setEnabled(false); // Inicialmente deshabilitado
        add(send);
        
        player1 = new JLabel("Player 1"); // Etiqueta para el nombre del jugador 1
        player1.setBounds(10, 550, 100, 20); // Ubicación y tamaño
        add(player1);
        
        puntaje1 = new JLabel("0"); // Etiqueta para el puntaje del jugador 1
        puntaje1.setBounds(110, 550, 100, 20); // Ubicación y tamaño
        add(puntaje1);
        
        player2 = new JLabel("Player 2"); // Etiqueta para el nombre del jugador 2
        player2.setBounds(200, 550, 100, 20); // Ubicación y tamaño
        add(player2);
        
        puntaje2 = new JLabel("0"); // Etiqueta para el puntaje del jugador 2
        puntaje2.setBounds(300, 550, 100, 20); // Ubicación y tamaño
        add(puntaje2);
        
        help = new JButton("Ayuda"); // Botón para obtener ayuda
        help.setBounds(510, 550, 100, 20); // Ubicación y tamaño
        help.addActionListener(this); // Asociar el botón con la acción del ActionListener
        add(help);
        
        surrender = new JButton("Rendirse"); // Botón para rendirse
        surrender.setBounds(620, 550, 100, 20); // Ubicación y tamaño
        surrender.addActionListener(machine); // Asociar el botón con la acción de la máquina
        add(surrender);
        
        add(CrearPartida); // Agregar el panel del tablero al JFrame
        
        setVisible(true); // Hacer visible la ventana
    }
    
    /**
     * Verifica si el cuadro de texto de mensajes está vacío y habilita o deshabilita el botón de enviar en consecuencia.
     */
    public void avanzadoCheckBox(){
        if(message.getText().equalsIgnoreCase(""))
            send.setEnabled(false); // Si está vacía, deshabilitar el botón "Enviar"
        else
            send.setEnabled(true); // Si hay texto, habilitar el botón "Enviar"
    }

    @Override
    public View obtenerVista() {
        return this; // Devolver la instancia actual de la vista
    }

    @Override
    public void crearMaquina(String  nombreField, String ipField, int puertoEntradaField, int puertoSalidaField, String modoPartida) {
        machine = new Maquina(this,  nombreField, ipField, puertoEntradaField, puertoSalidaField, modoPartida); // Crear una instancia de la máquina
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        avanzadoCheckBox(); // Verificar si se debe habilitar el botón "Enviar"
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        avanzadoCheckBox(); // Verificar si se debe habilitar el botón "Enviar"
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        avanzadoCheckBox(); // Verificar si se debe habilitar el botón "Enviar"
    }

    @Override
    public void puntajes(Integer uno, Integer dos) {
        puntaje1.setText(uno.toString()); // Actualizar el puntaje del jugador 1
        puntaje2.setText(dos.toString()); // Actualizar el puntaje del jugador 2
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == help){
            // Mostrar un cuadro de diálogo con información de ayuda
            JOptionPane.showMessageDialog(null, null, null, WIDTH, new ImageIcon("src/reglas.png"));//añadir imagen con las reglas
        }
    }

    @Override
    public void agregar(Boton boton) {
        CrearPartida.add(boton); // Agregar un botón al panel del tablero
    }

    @Override
    public Boton getBoton(int x, int y) {
        // Obtener el botón en una posición específica (implementar la lógica para buscar en la lista)
        // Si los botones se almacenan en una lista en orden de fila por fila, podría ser algo como:
        // int indice = x * NUMERO_DE_COLUMNAS + y;
        // return botones.get(indice);
        return null; // Reemplazar esto con la lógica adecuada
    }

    @Override
    public List<Boton> getBotones() {
        return botones; // Obtener la lista de botones del tablero
    }

    @Override
    public void setHistory(String history) {
        this.history.setText(history); // Establecer el historial de chat
    }

    @Override
    public String getHistory() {
        return this.history.getText(); // Obtener el historial de chat
    }

    @Override
    public void setMessage(String message){
        this.message.setText(message); // Establecer el mensaje de chat
    }
    
    @Override
    public String getMessage() {
        return this.message.getText(); // Obtener el mensaje de chat
    }
    
    @Override
    public JButton getEnviar(){
        return this.send; // Obtener el botón "Enviar"
    }
    
    @Override
    public JButton getRendir(){
        return this.surrender; // Obtener el botón "Rendirse"
    }

    @Override
    public void nombres(String player1, String player2) {
        this.player1.setText(player1 + ":"); // Establecer el nombre del jugador 1
        this.player2.setText(player2 + ":"); // Establecer el nombre del jugador 2
    }
}

