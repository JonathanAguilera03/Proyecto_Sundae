
import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David
 */
public class Menu extends JFrame {
    private static final int WIDTH=1024;
    private static final int HEIGHT= 768;
    ImageIcon Teoria=new ImageIcon(getClass().
                getResource("/Img/teoria.png"));
    ImageIcon Inicio=new ImageIcon(getClass().
                getResource("/Img/Inicio.png"));
    ImageIcon Records=new ImageIcon(getClass().
                getResource("/Img/Records.png"));
    ImageIcon atras=new ImageIcon(getClass().
                getResource("/Img/atras.png"));
    ImageIcon Fteoria=new ImageIcon(getClass().
                getResource("/Img/FTeoria.png"));
    ImageIcon ON=new ImageIcon(getClass().
                getResource("/Img/ON.png"));
    ImageIcon OFF=new ImageIcon(getClass().
                getResource("/Img/OFF.png"));
    
    
    Clip SonFondo;
     public void SonidoFondo(){
        try {
            SonFondo=AudioSystem.getClip();
            SonFondo.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/musica/nb.wav")));
            SonFondo.loop(SonFondo.LOOP_CONTINUOUSLY);
            
            
            
        } catch (Exception e) {
            
        }
        }
    
    public void btnTrasparente(JButton btn){
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setLayout(null);
    }
    
             
             
   public Menu(){
       
       SonidoFondo();
       JPanel Fondo= new Fondo("/img/SimuFIc.jpg");
       add(Fondo);
       Fondo.setVisible(true);
       Fondo.setLayout(null);
         JButton Inicio=new JButton();      
        JButton Sonido=new JButton();   
        Inicio.setIcon(this.Inicio);
        Sonido.setIcon(ON);
        btnTrasparente(Inicio);
        btnTrasparente(Sonido);
        Inicio.setBounds(450,390, 179, 53);
        Sonido.setBounds(900,600, 100, 100);
        Fondo.add(Inicio);
        Fondo.add(Sonido); 
        
        Inicio.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               SonFondo.stop();
               JFrame juego= new SimuFic();
                  
        
        
        
        
           }
       });
        
        Sonido.addActionListener(new ActionListener() {
            
           @Override
           public void actionPerformed(ActionEvent ae) {
              
              if(Sonido.getIcon()==ON){
                  SonFondo.stop();
                  Sonido.setIcon(OFF);
              }
              else{
                  SonFondo.loop(SonFondo.LOOP_CONTINUOUSLY);
                  Sonido.setIcon(ON);
              }
                      
           }
       });
  
   }
    public static void main(String[] args) {
      
       JFrame Ventana = new Menu();  
       Ventana.setSize(WIDTH,HEIGHT);
       Ventana.setVisible(true);
       Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Ventana.setLocationRelativeTo(null);
       Ventana.setResizable(false);
    }

   
   
}
