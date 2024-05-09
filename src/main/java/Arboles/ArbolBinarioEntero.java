package Arboles;

import Listas.ListaDE.ListaDE;

public class ArbolBinarioEntero extends ArbolBinario<Integer>{
    public ArbolBinarioEntero(){
      super();
    }
    public Integer suma(){
        Integer suma = 0;
        ListaDE elementos = this.ordenCentral();
        for (int i = 0 ;i<elementos.numElementos();i++){
            suma = (Integer) (suma + (Integer)elementos.recuperar(i));
        }
        return suma;
    }
@Override
    public ArbolBinarioEntero getSubArbolIzquierda() {
        ArbolBinarioEntero a1 = new ArbolBinarioEntero();
        a1.root = this.root.getIzq();
        return a1;
    }
    @Override
    public ArbolBinarioEntero getSubArbolDerecha() {
        ArbolBinarioEntero a1 = new ArbolBinarioEntero();
        a1.root = this.root.getDrc();
        return a1;
    }
}





