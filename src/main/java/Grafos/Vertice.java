package Grafos;

import Listas.ListasSE.Map;

public class Vertice<Tipo> {
    private Tipo dato;
    private Map<Vertice<Tipo>, Arista<Tipo>> entradas = new Map<>();
    private Map<Vertice<Tipo>, Arista<Tipo>> salidas = new Map<>();


    public Vertice(Tipo dato){
        this.dato = dato;
    }
    public void addAristaSalida(Arista<Tipo> a){
        salidas.add(a,a.getDestino());
    }
    public void addAristaEntrada(Arista<Tipo> a){
        //El mapa ya verifica que no exista ese vertice en las salidas.
        entradas.add(a,a.getOrigen());
    }

    public Tipo getDato() {
        return dato;
    }

    public boolean isSalida(Vertice<Tipo> v){
        return this.salidas.isId(v);
    }
    public boolean isEntrada(Vertice<Tipo> v){
        return this.entradas.isId(v);
    }
    public void delAristaSalida(Arista a) {
        this.salidas.eliminar(a.getDestino());
    }
    public void delAristaEntrada(Arista a) {
        this.entradas.eliminar(a.getOrigen());
    }
    public Object[] getEntradas(){
        if (this.entradas.isVacia()) return new Arista[0];
        return this.entradas.values();
    }
    public Object[] getSalidas(){
        if (this.salidas.isVacia()) return new Arista[0];
        return this.salidas.values();

    }

}
