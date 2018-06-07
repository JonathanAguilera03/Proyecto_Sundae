/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mary
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;



public class SimuFic extends JFrame implements Runnable,KeyListener,MouseListener {

    public ListaScore lista1=new ListaScore();
    private static final long serialVersionUID =-4561387616954048688L;
    private static final int WIDTH=1024;
    private static final int HEIGHT= 768;
    private int direccion;
    private int vidas;
    private int tiroFallado;
    private int dificultad;
            private int score;
            
            private int velx;
            private int vely;
            private int difx=3;
            private int dify=4;
            private int min;
            private int seg;
            private int posX;
            private int posY;
            private Image dbImage;
            private Image fondo;
            private Image gameover;
            private Image instrucciones;
            private Image pImage;
            private Graphics dbg;
            private Pelota ball;
            private Canasta basquet;
            private animacion animBall;
            private long tiempoActual;
            private boolean empieza;
            private boolean pause;
            private boolean sonido;
            private boolean opt;
            private SoundClip swish;
            private SoundClip buzzer;
            private String nombreArchivos;
            private int arreglo[]=new int[10];
            public SimuFic(){
                setSize(WIDTH, HEIGHT);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 setVisible(true);
                 setLocationRelativeTo(null);
                 setResizable(false);
                
                // Varaible De control para El Inicio del juego
                empieza=false;
                //Variable para Pausar el juego
                pause=false;
                //Variable Para controlar el Sonido
                sonido=false;
                
                opt=false;
                //Vidas Del juego
                vidas=3;
                //Numero de Intentos 
                tiroFallado=0;
                //Marcador
                score=0;
                //Dificultad De Movimiento
                dificultad=10;
                //Direcion De la canasta
                direccion=0;
                // Cargamos Las Imagenes
                fondo=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/fondob.png"));
                pImage=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/pause.gif"));
                gameover=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/fin.jpg"));
                posX=0;// posicion en x de la pelota
                posY=HEIGHT-70;// posicion en y de la pelota
                int posCanX=(WIDTH/4)*3;
                int posCanY=(HEIGHT/4)*3;
                velx=(int)(Math.random()*difx)+10;
                vely=-((int)(Math.random()*dify)+30);
                Image bola1=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/basketball.gif"));
                Image bola2=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/basketball2.gif"));
                Image bola3=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/basketball3.gif"));
                Image bola4=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/basketball4.gif"));
                animBall=new animacion();
                animBall.sumaCuadro(bola1,100);
                animBall.sumaCuadro(bola2,100);
                animBall.sumaCuadro(bola3,100);
                animBall.sumaCuadro(bola4,100);
                
                ball=new Pelota(posX,posY,bola1);
                Image canasta=Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/canasta.gif"));
                basquet= new Canasta(posCanX,posCanY,canasta);
                swish=new SoundClip("sounds/swish.wav");
                buzzer= new SoundClip("sounds/buzzer.wav");
                addKeyListener(this);
                addMouseListener(this);
                Thread t= new Thread(this);
                t.start();
                
            }
            public void run(){
                tiempoActual=System.currentTimeMillis();
                while(vidas>-1){
                    if (!pause) {
                        actualiza();
                        checarColision();
                        
                    
                    
                    }
                    repaint();
                    try{
                        Thread.sleep(20);
                    }catch(InterruptedException ex){
                        System.out.println("Error en"+ ex.toString());
                    }
                   
                }
                   
               lista1.add(new NodoScore(score));     
            }
            public void actualiza(){
                long tiempoTranscurrido=System.currentTimeMillis()-tiempoActual;
                tiempoActual+=tiempoTranscurrido;
                animBall.actualiza(tiempoTranscurrido);
                switch(direccion){
                    case 1:{
                    basquet.setPosX(basquet.getPosX()- dificultad);
                    break;
                }
                    case 2:{
                        basquet.setPosX(basquet.getPosX()+dificultad);
                        break;
                    }   
                }
                if (empieza) {
                    vely++;
                    ball.setPosX(ball.getPosX()+velx);
                    ball.setPosY(ball.getPosY()+vely);
                }
            }
            public void checarColision(){
                switch(direccion){// evita que se salga de la pantalla
                    case 1:{// se mueve a la izquierda
                        if (basquet.getPosX()<getWidth()/2) {//impide que pase de la mitad
                            basquet.setPosX(basquet.getPosX()+ dificultad);
                        }
                        break;
                    }
                    case 2:{// se mueve a la dercha
                        if (basquet.getPosX()+basquet.getAncho()>getWidth()) {//para que no pase del jframe
                         basquet.setPosX(basquet.getPosX()- dificultad);   
                        }
                        break;
                    }
                }
                if (ball.getPosY()> HEIGHT) {//INICIA
                    ball.setPosX(posX);
                    ball.setPosY(posY);
                    velx=(int)(Math.random()*difx)+10;
                    vely=-((int)(Math.random()*dify)+30);
                    empieza=false;
                    tiroFallado++;
                    if (sonido==true) {
                        buzzer.play();
                    }
                    if (tiroFallado==2) {
                        vidas--;
                        tiroFallado=0;
                        dificultad-=2;
                    }
                }//fin
                if (ball.intersecta(basquet)) {
                    ball.setPosX(posX);
                    ball.setPosY(posY);
                    velx=(int)(Math.random()*difx)+10;
                    vely=((int)(Math.random()*dify)+30);
                    if (sonido==true) {
                        swish.play();
                    }
                    score+=2;
                    empieza=false;
                }
            }
            public void paint(Graphics g){
                if (dbImage==null) {
                    dbImage=createImage(this.getSize().width,this.getSize().height);
                    dbg= dbImage.getGraphics();
                }
                dbg.setColor(getBackground());
                dbg.fillRect(0,0,this.getSize().width,this.getSize().height);
                dbg.setColor(getForeground());
                paint1(dbg);
                g.drawImage(dbImage, 0, 0, this);
                
            }
            public void paint1(Graphics g){
                if (vidas>0) {
                    g.drawImage(fondo, 0, 0, getSize().width,getSize().height,this);
                    if (ball !=null) {
                        g.drawImage(animBall.getImagen(),ball.getPosX(),ball.getPosY(),this);
                        g.drawImage(basquet.getImagenI(),basquet.getPosX(),basquet.getPosY(),this);
                        g.setColor(Color.white);
                        g.setFont(new Font("Serif",Font.BOLD,18));
                        g.drawString("Vidas:"+vidas+" Score: "+score,800,50);
                        g.setColor(Color.ORANGE);
                        g.drawString("Tecla: R Reinicio",400,50);
                        g.drawString("Tecla: P Pausa",600,50);
                        g.drawString("Tecla: F Finalizar",400,80);
                        g.drawString("Tecla: I Inicializar",600,80);
                        g.drawString("Tecla: ESPACIO Lanzar Pelota",50,50);
                        g.drawString("Tecla: S Sonido ", 50, 80);
                        if (!sonido) {
                            g.drawString("Sonido Desactivado!", 50, 110);
                        }
                        if (opt) {
                            g.drawImage(instrucciones, 0, 0, getSize().width,getSize().height,this);
                        }
                        if(pause&&!opt){
                            g.drawImage(pImage, 400, 190, this);
                    }
                }
                    else{
                        g.drawString("no se cargo la imagen...", 20, 20);
                    }
            }
                else{
                    
                    g.drawImage(gameover, 0, 0, getSize().width,getSize().height, this);
                    
                    
                }
                }
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode()==KeyEvent.VK_SPACE) {//si presiona la flecha iz
                        empieza=true;
                    }
                
                if (e.getKeyCode()==KeyEvent.VK_LEFT) {//si presiona la flecha iz
                    direccion=1;
                }
                else if(e.getKeyCode()==KeyEvent.VK_RIGHT){// SI PRESIONA LA FLECHA DRECHA
                    direccion=2;                    
                }
                if (e.getKeyCode()== KeyEvent.VK_R) {// si se deja de presionar la tecla derecha
                     if(vidas==0){
                    
                    lista1.add(new NodoScore(score));
                    
                    }
               
                  
                empieza=false;
                pause=false;
                sonido=false;
                opt=false;
                vidas=3;
                tiroFallado=0;
                score=0;
                dificultad=10;
                direccion=0;
                    
                }
 
            }
            public void keyTyped(KeyEvent e){
                
            }
            public void keyReleased(KeyEvent e){
                 
                 if(e.getKeyCode()==KeyEvent.VK_F){
                     
                     opt=!opt;
                    pause=!pause;
                    vidas=-1;
                    
                    
                }
                if (e.getKeyCode()== KeyEvent.VK_LEFT) {// si se deja de presionar la tecla derecha
                    direccion=0;
                }
                  if (e.getKeyCode()== KeyEvent.VK_RIGHT) {// si se deja de presionar la tecla izquierda
                    direccion=0;
                }
                if(e.getKeyCode()==KeyEvent.VK_P){// SI SE PRESIONA LA TECLA P PAUSA
                    pause=!pause;
                }
                if(e.getKeyCode()==KeyEvent.VK_S){// SI SE PRESIONA LA TECLA s 
                    sonido=!sonido;
                }
                 if(e.getKeyCode()==KeyEvent.VK_I){// SI SE PRESIONA LA TECLA I 
                     if(vidas==0){
                          
                         lista1.printList();
                    lista1.nIni=merge(lista1.nIni);
                         System.out.println("\n Puntaje ordenado");
                         lista1.printList();
                    
                     }
                     if(vidas==-1){
                          
                         lista1.printList();
                    lista1.nIni=merge(lista1.nIni);
                    System.out.println("\nPuntaje ordenado");
                         lista1.printList();
                    
                     }
                    
                    
                     
                    
                }
                  
                
              if(e.getKeyCode()==KeyEvent.VK_G){// SI SE PRESIONA LA TECLA G 
                 try{
                     grabaJuego();//graba el juego en el archivo
                 }catch(IOException h){
                  System.out.println("Error en"+e.toString());
              }
                }
            }
            public boolean estaDentro(MouseEvent e){
                if ((e.getX()>ball.getPosX())&&(e.getX()<ball.getPosX()+ball.getAncho())&&(e.getY()>ball.getPosY())&&(e.getY()<ball.getPosY()+ball.getAlto())) {
                    return true;
                }
                else{
                    return false;
                }
  
            }
            public void mouseClicked(MouseEvent e){
                if (e.getButton()== MouseEvent.BUTTON1 && estaDentro(e)) {
                    empieza=true;
                    
                }
            }
            public void mouseEntered(MouseEvent e){
                
            }
            public void mouseExited(MouseEvent e){
                
            }
            public void mousePressed(MouseEvent e){
                
            }
            public void mouseReleased(MouseEvent e){
                
            }
            public void grabaJuego()throws IOException{
             PrintWriter fileout= new PrintWriter( new FileWriter(nombreArchivos));
             fileout.println(ball.getPosX()+"#"+ball.getPosY()+"#"+basquet.getPosY()+"#"+vely+"#"+dificultad+"#"+vidas+"#"+tiroFallado+"#"+score);
             fileout.close();
            }
            
            /**
     * @param args the command line arguments
     */ 

    public static NodoScore merge(NodoScore inicial){
        if(inicial==null||inicial.getSig()==null){
            return inicial;
        }
        NodoScore medio=mitad(inicial);
        NodoScore medioSig=medio.getSig();
        medio.setSig(null);
        NodoScore primero=merge(inicial);
        NodoScore segundo=merge(medioSig);
        NodoScore junto=ordenar(primero, segundo);
        return junto;
    }
    
    public static NodoScore ordenar(NodoScore izq, NodoScore der){
        NodoScore result=null;
        if(izq==null){
            return der;
        }else if(der==null){
            return izq;
        }else if(izq.getMiDato()<=der.getMiDato()){
            result=izq;
            result.setSig(ordenar(izq.getSig(), der));
        }else if(izq.getMiDato()>der.getMiDato()){
            result=der;
            result.setSig(ordenar(izq, der.getSig()));
        }
        return result;
    }
    
    public static NodoScore mitad(NodoScore inicial){
        if(inicial==null){
            return inicial;
        }
        NodoScore simple=inicial;
        NodoScore doble=inicial.getSig();
        while(doble!=null){
            doble=doble.getSig();
            if(doble!=null){
                simple=simple.getSig();
                doble=doble.getSig();
            }
        }
        return simple;
    }
}

    


     