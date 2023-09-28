package mx.uabc.poo2.pf.vista;

import mx.uabc.poo2.pf.comunicacion.StartVista;
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


//Esta es la clase que inicia todo


public class Start extends JFrame implements ActionListener, ItemListener, StartVista{
    
    private JTextField ip, port1, port2, name;
    private JLabel lip, lport1, lport2, lname;
    private JComboBox combo;
    private JButton play;
    private JCheckBox check;
    private String p="p1";
    
    public Start(){
        super("Timbiriche");
        inicializar();
    }
    
    private void inicializar(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setResizable(false);
        
        lname=new JLabel("Nombre:");
        lname.setBounds(10,10,50,20);
        add(lname);
        
        name=new JTextField();
        name.setBounds(150,10,130,20);
        add(name);
        
        lip=new JLabel("IP:");
        lip.setBounds(10,40,50,20);
        add(lip);
        
        ip=new JTextField();
        ip.setBounds(150,40,130,20);
        add(ip);
        
        lport1=new JLabel("Puerto de entrada:");
        lport1.setBounds(10,70,150,20);
        lport1.setVisible(false);
        add(lport1);
        
        port1=new JTextField("9000");
        port1.setBounds(150,70,130,20);
        port1.setVisible(false);
        add(port1);
        
        lport2=new JLabel("Puerto de salida:");
        lport2.setBounds(10,100,150,20);
        lport2.setVisible(false);
        add(lport2);
        
        port2=new JTextField("9001");
        port2.setBounds(150,100,130,20);
        port2.setVisible(false);
        add(port2);
        
        String[] opcs={"Crear partida", "Unirse a una partida"};
        combo=new JComboBox(opcs);
        combo.setBounds(150,210,130,20);
        combo.addItemListener(this);
        add(combo);
        
        play=new JButton("Jugar");
        play.setBounds(150,240,130,20);
        play.setFocusable(false);
        play.addActionListener(this);
        add(play);
        
        check=new JCheckBox("Avanzado");
        check.setBounds(10,240,100,20);
        check.addActionListener(this);
        add(check);
        
        add(new JLabel());
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        Start start=new Start();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource()==this.check){
            if(check.isSelected()){
                lport1.setVisible(true);
                lport2.setVisible(true);
                port1.setVisible(true);
                port2.setVisible(true);
            }
            else{
                lport1.setVisible(false);
                lport2.setVisible(false);
                port1.setVisible(false);
                port2.setVisible(false);
            }
        }

        if(ae.getSource()==this.play){
            int band=0;
            try{
                if(this.name.getText().equalsIgnoreCase(""))
                    throw new ClassNotFoundException();
                band=1;
                if(this.ip.getText().equalsIgnoreCase("localhost")){

                }
                else{
                    String[] ip=this.ip.getText().split(".");
                    if(this.ip.getText().equalsIgnoreCase(""))
                        throw new ClassNotFoundException();
                    for(int x=0;x<3;x++)
                        for(int y=0;y<ip[x].length();y++)
                            if(Character.isDigit(ip[x].charAt(y)))
                                throw new Exception();
                }
                band=2;
                Integer.parseInt(this.port1.getText());
                if(this.port1.getText().equalsIgnoreCase(""))
                    throw new ClassNotFoundException();
                band=3;
                Integer.parseInt(this.port2.getText());
                if(this.port2.getText().equalsIgnoreCase(""))
                    throw new ClassNotFoundException();
                if(this.port2.getText().equalsIgnoreCase(this.port1.getText()))
                    throw new ArithmeticException();

                crear(name.getText(), ip.getText(), 
                        Integer.parseInt(port1.getText()), 
                        Integer.parseInt(port2.getText()), p);
            }
            catch(ArithmeticException e){
                JOptionPane.showMessageDialog(null,"Los puertos no pueden ser iguales. \nPorfavor cambia uno de los dos");
            }
            catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null,"Procura no dejar espacios vacíos. \nToda la información es importante");
            }
            catch(Exception e){
                if(band==1)
                    JOptionPane.showMessageDialog(null,"Formato de IP incorrecto. \nej. 189.220.191.252");
                if(band==2)
                    JOptionPane.showMessageDialog(null,"Formato de puerto 1 incorrecto");
                if(band==3)
                    JOptionPane.showMessageDialog(null,"Formato de puerto 2 incorrecto");
            }
        }
    }

    @Override
    public void crear(String nombre, String ip, int port1, int port2, String p){
        Vista vista=new Vista(nombre, ip, port1, port2, p);
        setVisible(false);
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        Object[] obj=ie.getItemSelectable().getSelectedObjects();
        if(obj[0].toString().equalsIgnoreCase("Crear partida")){
            p="p1";
            port1.setText("9000");
            port2.setText("9001");
        }
        if(obj[0].toString().equalsIgnoreCase("Unirse a una partida")){
            p="p2";
            port1.setText("9001");
            port2.setText("9000");
        }
