/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Mary
 */
public class Canasta extends Objeto {
    public Canasta(int posX,int posY,Image image){
        super(posX,posY,image);
    }
    public Rectangle getPerimetro(){
     return new Rectangle(getPosX()+31,getPosY()+81,62,8);   
    }
}
