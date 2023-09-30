package cd.logica;

import cd.conexion.Conexion;
import cd.conexion.InterfazMaquinaEstados;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * La clase MaquinaEstados es responsable de gestionar el flujo de juego y la interacción entre los jugadores.
 *
 * @version 1.0

 */
public class MaquinaEstados implements ActionListener, Conexion {

    private InterfazMaquinaEstados vistamaquina;
    private InServer entrada;
    private Interprete interprete;
    private int puertoEntradaField, puertoSalidaField;
    private String nombreField, ipField;
    private final String victoria = "victoria", rendirse = "Rendirse",
            continuar = "continuar", nocontinuar = "nocontinuar", ping = "ping",
            pong = "pong", pingpong = "pingpong";
    private int player1 = 0, player2 = 0;
    private boolean turno, cont1 = false, cont2 = false, connect = false;

    /**
     * Constructor de la clase MaquinaEstados.
     *
     * @param vistamaquina          La instancia de la interfaz vista/maquina.
     * @param nombreField           El nombre del jugador.
     * @param ipField               La dirección IP.
     * @param puertoEntradaField    El puerto de entrada.
     * @param puertoSalidaField     El puerto de salida.
     * @param modoPartida           El identificador del jugador.
     */
    public MaquinaEstados(InterfazMaquinaEstados vistamaquina, String nombreField, String ipField,
                          int puertoEntradaField, int puertoSalidaField, String modoPartida) {
        this.vistamaquina = vistamaquina;
        this.nombreField = nombreField;
        this.ipField = ipField;
        this.puertoEntradaField = puertoEntradaField;
        this.puertoSalidaField = puertoSalidaField;
        interprete = new Interprete(this);
        entrada = new InServer(puertoEntradaField, this);
        entrada.start();
        if (modoPartida.equalsIgnoreCase("CrearPartida"))
            turno = true;
        else
            turno = false;
    }
    /**
     * Método para acomodar la matriz de botones en la interfaz.
     *
     * @param matriz La matriz de botones a acomodar.
     * @param add    Indica si se deben agregar botones nuevos o simplemente restablecer los valores de la matriz existente.
    */
    public void acomodar(Boton[][] matriz, boolean add){
        // Bucle para recorrer las filas de la matriz
        for(int x=0;x<19;x++){
            // Bucle para recorrer las columnas de la matriz
            for(int y=0;y<19;y++){
                if(add){
                    // Si se debe agregar botones nuevos, se crea una nueva instancia de Boton y se agrega un ActionListener
                    matriz[x][y]=new Boton();
                    matriz[x][y].addActionListener(this);
                }
                else{
                    // Si no se deben agregar botones nuevos, se restablecen los valores del botón existente
                    matriz[x][y].setOwner(null);
                    matriz[x][y].setTaken(false);
                }
                if(x%2==0){
                    if(y%2==0){
                        // Configuración de botones en casillas pares
                        matriz[x][y].setBounds(0+(55*(y/2)),0+(55*(x/2)),5,5);
                        matriz[x][y].setBorderPainted(false);
                        matriz[x][y].setBackground(Color.black);
                        matriz[x][y].setEnabled(false);
                    }
                    else{
                        // Configuración de botones en casillas impares
                        matriz[x][y].setBounds(5+(55*(y/2)),0+(55*(x/2)),50,5);
                        matriz[x][y].setBorderPainted(false);
                        matriz[x][y].setBackground(Color.white);
                    }
                }
                else{
                    if(y%2==0){
                        // Configuración de botones en casillas impares
                        matriz[x][y].setBounds(0+(55*(y/2)),5+(55*(x/2)),5,50);
                        matriz[x][y].setBorderPainted(false);
                        matriz[x][y].setBackground(Color.white);
                    }
                    else{
                         // Configuración de botones en casillas pares (cuadradas)
                        matriz[x][y].setBounds(5+(55*(y/2)),5+(55*(x/2)),50,50);
                        matriz[x][y].setSquare(true);
                        matriz[x][y].setBorderPainted(false);
                        matriz[x][y].setBackground(Color.white);
                        matriz[x][y].setEnabled(false);
                    }
                }
                if(add)
                    vistamaquina.agregar(matriz[x][y]);
            }
        }
    }
    /**
     * Método para verificar si hay nuevas casillas cerradas en la matriz de botones y otorgar puntos al jugador correspondiente.
     *
     * @param player Nombre del jugador.
     * @param matriz Matriz de botones.
     * @param who    Identificador del jugador.
    */
    public void check(String player, Boton[][] matriz, int who){
        // Bucle para recorrer las filas de la matriz
        for(int x=0;x<19;x++){
            // Bucle para recorrer las columnas de la matriz
            for(int y=0;y<19;y++){
                if(matriz[x][y].getSquare() && !matriz[x][y].getTaken()){
                    if(matriz[x-1][y].getTaken() && matriz[x+1][y].getTaken() 
                            && matriz[x][y-1].getTaken() 
                            && matriz[x][y+1].getTaken()){
                        // Si las casillas adyacentes están tomadas, se marca la casilla actual como tomada por el jugador
                        matriz[x][y].setOwner(player);
                        matriz[x][y].setTaken(true);
                        if(who==1)
                            player1++;// Incrementar los puntos del jugador 1
                        if(who==2)
                            player2++;// Incrementar los puntos del jugador 2
                        vistamaquina.puntajes(player1, player2);// Actualizar la puntuación en la interfaz
                        turno=true;// Cambiar el turno al jugador siguiente
                    }
                }
            }
        }
    }
    /**
     * Método para verificar si todos los botones en la matriz han sido usados.
     *
     * @return true si todos los botones han sido utilizados, false en caso contrario.
    */
    public boolean forTheWin(){
        // Bucle para recorrer las filas de la matriz
        for(int x=0;x<19;x++){
            // Bucle para recorrer las columnas de la matriz
            for(int y=0;y<19;y++){
                // Si un botón en la posición (x, y) todavía está habilitado (no ha sido utilizado), se retorna false
                if(vistamaquina.getBoton(x,y).isEnabled())
                    return false;
            }
        }
        // Si se recorren todos los botones y ninguno está habilitado, se retorna true (todos los botones han sido utilizados)
        return true;
    }
    
    /**
     * Método para finalizar la partida y mostrar un mensaje de victoria o derrota.
     *
     * @param maybe Cadena que indica si se rinde o no.
    */
    public void finDePartida(String maybe){
        // Verifica si se recibe una cadena vacía o "Rendirse" para determinar el resultado de la partida
        if(maybe.equalsIgnoreCase("")){
            // Comprueba el puntaje de los jugadores y muestra un mensaje de victoria, derrota o empate
            if(player1>player2)
                JOptionPane.showMessageDialog(null, null, "Victoria", WIDTH, 
                        new ImageIcon("src/victoria.png"));
            else if(player1<player2)
                JOptionPane.showMessageDialog(null, null, "Derrota", WIDTH, 
                        new ImageIcon("src/derrota.png"));
            else
                JOptionPane.showMessageDialog(null, null, "Empate", WIDTH, 
                        new ImageIcon("src/empate.png"));
        }
        else{
            if(maybe.equalsIgnoreCase("Rendirse"))
                JOptionPane.showMessageDialog(null, null, "Derrota", WIDTH, 
                        new ImageIcon("src/derrota.png"));
            else
                JOptionPane.showMessageDialog(null, null, "Victoria", WIDTH, 
                        new ImageIcon("src/victoria.png"));
        }
        // Solicita al usuario si desea reiniciar la partida
        String[] opcs={"Si", "No"};
        if(JOptionPane.showOptionDialog(null, "¿Desea reiniciar la partida?", 
                "Continuar", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, null, opcs, opcs[0])==0){
            cont1=true;
            enviar(continuar);
            continuar();
        }
        else{
            enviar(nocontinuar);
            System.exit(0);
        }
    }
    /**
     * Método para reiniciar la partida.
    */
    public void reiniciar(){
        player1=0;
        player2=0;
        vistamaquina.puntajes(player1, player2);
        acomodar(vistamaquina.getMatriz(),false);
    }
    /**
     * Método para continuar la partida.
    */
    public void continuar(){
        if(cont1 && cont2)
            reiniciar();
    }
    @Override
    public void continuar(String cont){
        if(cont.equalsIgnoreCase(continuar))
            cont2=true;
        else if(cont.equalsIgnoreCase(nocontinuar)){
            JOptionPane.showMessageDialog(null, "El oponente no desea continuar");
            System.exit(0);
        }
        if(cont1 && cont2)
            reiniciar();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Verifica si el evento proviene del botón de envío de chat
        if(ae.getSource()==vistamaquina.getEnviar()){
            // Envía el mensaje de chat
            enviar();
            // Limpia el campo de mensaje en la interfaz y deshabilita el botón de envío
            vistamaquina.setMessage("");
            vistamaquina.getEnviar().setEnabled(false);
        }
        // Verifica si el evento proviene del botón de rendición
        else if(ae.getSource()==vistamaquina.getRendir()){
            // Envía un mensaje de victoria al oponente y finaliza la partida
            enviar(victoria);
            finDePartida(rendirse);
        }
        // Si no es ninguno de los casos anteriores, se asume que es un botón de la matriz
        else if(turno && connect){
            turno=false;
            Integer x=0;
            Integer y=0;
            boolean ban=false;
            // Itera sobre los botones de la matriz para encontrar cuál fue presionado
            for(x=0;x<19;x++){
                for(y=0;y<19;y++){
                    if(ae.getSource()==vistamaquina.getBoton(x, y)){
                        // Marca el botón como "tomado" y lo colorea de negro
                        vistamaquina.getBoton(x, y).setTaken(true);
                        vistamaquina.getBoton(x, y).setBackground(Color.black);
                        vistamaquina.getBoton(x, y).setEnabled(false);
                        ban=true;
                        break;
                    }
                }
                if(ban)
                    break;
            }
            // Verifica si se han cerrado nuevas casillas y otorga puntos al jugador
            check(nombreField, vistamaquina.getMatriz(), 1);
            // Envía las coordenadas y el nombre del jugador separados por espacios para que el intérprete los reconozca
            enviar(x.toString()+" "+y.toString()+" "+nombreField);
            // Comprueba si se ha alcanzado un estado de empate o victoria
            if(forTheWin()){
                finDePartida("");
            }
        }
    }

    @Override
    public void interpretar(Object obj) {
         // Delega la interpretación al objeto 'interprete'
        interprete.interpretar(obj);
    }

    @Override
    public void actualizar(String mensaje) {
        // Actualiza el historial de mensajes en la interfaz
        if(vistamaquina.getHistory().equalsIgnoreCase(""))
            vistamaquina.setHistory(mensaje);
        else
            vistamaquina.setHistory(vistamaquina.getHistory()+"\n"+mensaje);
    }

    @Override
    public void enviar() {
        // Crea un mensaje de chat con el nombre del jugador y el mensaje ingresado en la interfaz
        String mensaje=nombreField+": "+vistamaquina.getMessage();
        // Envia el mensaje al servidor
        new OutServer(ipField,puertoSalidaField,"##-"+mensaje);
        // Actualiza el historial de mensajes en la interfaz
        actualizar(mensaje);
    }

    @Override
    public void reAcomodar(Boton[][] matriz) {
        // Actualiza la interfaz para reflejar el estado de la matriz recibida
        for(int x=0;x<19;x++)
            for(int y=0;y<19;y++){
                if(matriz[x][y].getTaken()!=vistamaquina.getBoton(x, y).getTaken()){
                    vistamaquina.getBoton(x, y).setTaken(matriz[x][y].getTaken());
                    vistamaquina.getBoton(x, y).setOwner(matriz[x][y].getOwner());
                    vistamaquina.getBoton(x, y).setBackground(matriz[x][y].getBackground());
                    if(matriz[x][y].getTaken())
                        vistamaquina.getBoton(x, y).setEnabled(false);
                }

            }
        turno=true;
        // Comprueba si se ha alcanzado un estado de empate o victoria
        if(forTheWin()){
            finDePartida("");
        }
    }

    @Override
    public void enviar(String obj) {
        // Envía un mensaje al servidor
        new OutServer(ipField,puertoSalidaField,obj);
    }

    @Override
    public void reAcomodar(int x, int y, String nombreField2) {
        // Actualiza la interfaz para reflejar el estado de un botón específico según las coordenadas
        vistamaquina.nombres(nombreField, nombreField2);
        try{
            vistamaquina.getBoton(x, y).setTaken(true);
            vistamaquina.getBoton(x, y).setBackground(Color.black);
            // Comprueba si se han cerrado nuevas casillas y otorga puntos al jugador
            check(nombreField2,vistamaquina.getMatriz(), 2);
            turno=true;
        }
        catch(Exception e){
            turno=true;
        }
        // Comprueba si se ha alcanzado un estado de empate o victoria
        if(forTheWin()){
            finDePartida("");
        }
    }

    @Override
    public void ganar(String maybe) {
        // Finaliza la partida y muestra un mensaje de victoria o derrota
        finDePartida(maybe);
    }

    @Override
    public void conectado() {
        // Indica que se ha establecido la conexión
        connect=true;
    }
}
