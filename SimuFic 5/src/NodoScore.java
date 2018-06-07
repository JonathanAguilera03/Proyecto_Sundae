/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Jonathan Aguilera 
 */
public class NodoScore {
    private int miDato;
    private NodoScore sig;

    public NodoScore() {
    }

    public NodoScore(int miDato, NodoScore sig) {
        this.miDato = miDato;
        this.sig = sig;
    }

    public NodoScore(int miDato) {
        this.sig=null;
        this.miDato = miDato;
    }

    public int getMiDato() {
        return miDato;
    }

    public void setSig(NodoScore sig) {
        this.sig = sig;
    }

    public NodoScore getSig() {
        return sig;
    }

    public void setMiDato(int miDato) {
        this.miDato = miDato;
    }
    
    
}
