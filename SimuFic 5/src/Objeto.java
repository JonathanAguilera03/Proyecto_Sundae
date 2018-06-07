/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;


/**
 *
 * @author Mary
 */
public class Objeto {
    private int posX;
    private int posY;
    private ImageIcon icono;
    public Objeto(int posX, int posY,Image image){
        this.posX=posX;
        this.posY= posY;
        icono= new ImageIcon(image);
    }
    public void setPosX(int posX){
        this.posX=posX;
    }
     public void setPosY(int posy){
        this.posY= posy;
    }
     public void setImageIcon(ImageIcon icono){
         this.icono=icono;
     }
     public int getPosX(){
         return posX;
     }
     public int getPosY(){
         return posY;
     }
     public ImageIcon getImageIcon(){
         return icono;
     }
     public int getAncho(){
         return icono.getIconWidth();
     }
    public int getAlto(){
         return icono.getIconHeight();
       
     }
    public Image getImagenI(){
       return icono.getImage();
    }
    public Rectangle getPerimetro(){
       return new Rectangle (getPosX(),getPosY(),getAncho(),getAlto());
    }
    public boolean intersecta(Objeto obj){
        return getPerimetro().intersects(obj.getPerimetro());
    }
     
}
