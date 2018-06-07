

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author David
 */
public class Fondo extends JPanel {
     public String Fondo;

    Fondo(String fon) {
        Fondo=fon;
        
    }
    public void paintComponent(Graphics g){
       
        Dimension tamanio = getSize();        
        ImageIcon imagenFondo = new ImageIcon(getClass().
                getResource(Fondo));
        g.drawImage(imagenFondo.getImage(), 0, 0, 
                tamanio.width, tamanio.height, null);
        setOpaque(false);
        super.paintComponent(g);
        
    }
    
    
}
