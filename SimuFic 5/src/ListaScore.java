/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Jonathan Aguilera
 */
public class ListaScore {
    public NodoScore nIni;//EL NODO QUE MARCA EL INICIO DE LA LISTA
    private NodoScore nFin;
    public ListaScore(){
        nIni=null;
        nFin=null;
    }

    public ListaScore(NodoScore nIni) {
        this.nIni = nIni;
        this.nFin = nIni;
    }
    
    //AGREGAR UN NODO AL FINAL DE LA LISTA
    public void add(NodoScore nNodo){
        if(nIni==null){//LA LISTA ESTÁ VACIA
            nIni=nNodo;
            nFin=nNodo;
        }else{//LA LISTA TIENE ELEMENTOS
            //HAY QUE MOVERNOS AL FINAL DE LA LISTA
            NodoScore nTemp=nIni;
            while(nTemp.getSig()!=null){
                nTemp=nTemp.getSig();
            }
            //CONECTAR EL NUEVO NODO AL FINAL DE LA LISTA
            nTemp.setSig(nNodo);
            nFin.setSig(nNodo);
            nFin=nNodo;
        }
    }
    //IMPRIMIR DATOS DE LA LISTA
    public void printList(){
        NodoScore nTemp=nIni;
        while(nTemp!=null){
            System.out.print(nTemp.getMiDato()+" - ");
            nTemp=nTemp.getSig();
        }
    }
}
