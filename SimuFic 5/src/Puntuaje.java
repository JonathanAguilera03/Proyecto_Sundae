/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Mary
 */
public class Puntuaje {
 private String nombre;
 private int puntuaje;
 public Puntuaje(){
     nombre="";
     puntuaje=0;
 }
 public Puntuaje (String nombre, int puntuaje){
     this.nombre=nombre;
     this.puntuaje=puntuaje;
 }
 public void setNombre(String nombre){
     this.nombre=nombre;
 }
 public void setPuntuaje(int puntuaje){
     this.puntuaje=puntuaje;
 }
 public String getNombre(){
     return nombre;
 }
 public int getPuntuaje(){
     return puntuaje;
 }
 public String toString(){
     return""+getPuntuaje()+","+getNombre();
 }
}
