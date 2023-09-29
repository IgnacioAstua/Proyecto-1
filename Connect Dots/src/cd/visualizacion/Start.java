package cd.visualizacion;

import cd.conexion.StartView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Esta clase representa la ventana de inicio del juego "Connect Dots".
 * Los jugadores pueden ingresar su nombre, dirección IP y configurar los puertos de comunicación.
 * También pueden elegir si desean crear una partida o unirse a una existente.
 */
public class Start extends JFrame implements ActionListener, ItemListener, StartView {

    private JTextField nombreField, ipField, puertoEntradaField, puertoSalidaField;
    private JLabel etiquetaNombre, etiquetaIP, etiquetaPuertoEntrada, etiquetaPuertoSalida;
    private JComboBox<String> seleccionarModo;
    private JButton botonJugar;
    private JCheckBox avanzadoCheckBox;
    private String modoPartida = "CrearPartida"; // Variable para determinar si se crea o se une a una partida

    /**
     * Constructor de la clase Start.
     * Inicializa la interfaz de usuario.
     */
    public Start() {
        super("Connect Dots");
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        // Configuración de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        // Etiqueta para el nombre
        etiquetaNombre = new JLabel("Nombre:");
        etiquetaNombre.setBounds(10, 10, 50, 20);
        add(etiquetaNombre);

        // Campo de texto para el nombre
        nombreField = new JTextField();
        nombreField.setBounds(150, 10, 130, 20);
        add(nombreField);

        // Etiqueta para la dirección IP
        etiquetaIP = new JLabel("IP:");
        etiquetaIP.setBounds(10, 40, 50, 20);
        add(etiquetaIP);

        // Campo de texto para la dirección IP
        ipField = new JTextField();
        ipField.setBounds(150, 40, 130, 20);
        add(ipField);

        // Etiqueta para el puerto de entrada (se muestra si se selecciona "Avanzado")
        etiquetaPuertoEntrada = new JLabel("Puerto de entrada:");
        etiquetaPuertoEntrada.setBounds(10, 70, 150, 20);
        etiquetaPuertoEntrada.setVisible(false);
        add(etiquetaPuertoEntrada);
        
        // Campo de texto para el puerto de entrada (se muestra si se selecciona "Avanzado")
        puertoEntradaField = new JTextField("9000");
        puertoEntradaField.setBounds(150, 70, 130, 20);
        puertoEntradaField.setVisible(false);
        add(puertoEntradaField);

        // Etiqueta para el puerto de salida (se muestra si se selecciona "Avanzado")
        etiquetaPuertoSalida = new JLabel("Puerto de salida:");
        etiquetaPuertoSalida.setBounds(10, 100, 150, 20);
        etiquetaPuertoSalida.setVisible(false);
        add(etiquetaPuertoSalida);

        // Campo de texto para el puerto de salida (se muestra si se selecciona "Avanzado")
        puertoSalidaField = new JTextField("9001");
        puertoSalidaField.setBounds(150, 100, 130, 20);
        puertoSalidaField.setVisible(false);
        add(puertoSalidaField);

        // ComboBox para seleccionar "Crear partida" o "Unirse a una partida"
        String[] opcionesModo = { "Crear partida", "Unirse a una partida" };
        seleccionarModo = new JComboBox<>(opcionesModo);
        seleccionarModo.setBounds(150, 210, 130, 20);
        seleccionarModo.addItemListener(this);
        add(seleccionarModo);

        // Botón para iniciar el juego  
        botonJugar = new JButton("Jugar");
        botonJugar.setBounds(150, 240, 130, 20);
        botonJugar.setFocusable(false);
        botonJugar.addActionListener(this);
        add(botonJugar);

        // CheckBox para habilitar la configuración avanzada
        avanzadoCheckBox = new JCheckBox("Avanzado");
        avanzadoCheckBox.setBounds(10, 240, 100, 20);
        avanzadoCheckBox.addActionListener(this);
        add(avanzadoCheckBox);

        // Espacio en blanco
        add(new JLabel());

        setVisible(true);
    }

    /**
     * Método principal que inicia la aplicación.
     */
    public static void main(String[] args) {
        Start start = new Start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Maneja los eventos de los botones y el CheckBox

        if (ae.getSource() == this.avanzadoCheckBox) {
            if (avanzadoCheckBox.isSelected()) {
                // Muestra las etiquetas y campos de texto de puerto si se selecciona "Avanzado"
                etiquetaPuertoEntrada.setVisible(true);
                etiquetaPuertoSalida.setVisible(true);
                puertoEntradaField.setVisible(true);
                puertoSalidaField.setVisible(true);
            } else {
                // Oculta las etiquetas y campos de texto de puerto si no se selecciona "Avanzado"
                etiquetaPuertoEntrada.setVisible(false);
                etiquetaPuertoSalida.setVisible(false);
                puertoEntradaField.setVisible(false);
                puertoSalidaField.setVisible(false);
            }
        }

        if (ae.getSource() == this.botonJugar) {
            int band = 0;
            try {
                if (this.nombreField.getText().isEmpty())
                    throw new ClassNotFoundException();
                band = 1;
                if (this.ipField.getText().equalsIgnoreCase("localhost")) {

                } else {
                    String[] partesIP = this.ipField.getText().split("\\.");
                    if (this.ipField.getText().isEmpty() || partesIP.length != 4)
                        throw new ClassNotFoundException();
                    for (String parte : partesIP) {
                        int valor = Integer.parseInt(parte);
                        if (valor < 0 || valor > 255)
                            throw new Exception();
                    }
                }
                band = 2;
                Integer.parseInt(this.puertoEntradaField.getText());
                if (this.puertoEntradaField.getText().isEmpty())
                    throw new ClassNotFoundException();
                band = 3;
                Integer.parseInt(this.puertoSalidaField.getText());
                if (this.puertoSalidaField.getText().isEmpty())
                    throw new ClassNotFoundException();
                if (this.puertoSalidaField.getText().equals(this.puertoEntradaField.getText()))
                    throw new ArithmeticException();

                // Llama al método crear con los parámetros ingresados
                crear(nombreField.getText(), ipField.getText(), Integer.parseInt(puertoEntradaField.getText()),
                        Integer.parseInt(puertoSalidaField.getText()), modoPartida);
            } catch (ArithmeticException e) {
                JOptionPane.showMessageDialog(null, "No puede ser el mismo puerto para ambos jugadores. \nPor favor cambia uno de los dos");
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Por favor no dejes espacios vacíos. \nToda la información es importante");
            } catch (Exception e) {
                if (band == 1)
                    JOptionPane.showMessageDialog(null, "El formato ingresado para la IP es incorrecto. \nej. 192.168.255.252");
                if (band == 2)
                    JOptionPane.showMessageDialog(null, "Formato de puerto 1 incorrecto");
                if (band == 3)
                    JOptionPane.showMessageDialog(null, "Formato de puerto 2 incorrecto");
            }
        }
    }

    @Override
    public void crear(String nombreField, String ipField, int puertoEntrada, int puertoSalida, String modoPartida) {
        // Crea una instancia de la clase View y oculta la ventana actual
        View view = new View(nombreField, ipField, puertoEntrada, puertoSalida, modoPartida);
        setVisible(false);
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        // Maneja el evento de selección en el ComboBox
        Object[] seleccion = ie.getItemSelectable().getSelectedObjects();
        if (seleccion[0].toString().equalsIgnoreCase("Crear partida")) {
            modoPartida = "CrearPartida";
            puertoEntradaField.setText("9000");
            puertoSalidaField.setText("9001");
        }
        if (seleccion[0].toString().equalsIgnoreCase("Unirse a una partida")) {
            modoPartida = "UnirsePartida";
            puertoEntradaField.setText("9001");
            puertoSalidaField.setText("9000");
        }
    }
}


