package Grafos;

import Listas.ListasSE.ListaSE;

public class Camino<T> {
    private double coste;
    ListaSE<Vertice<T>> camino;

    public Camino(ListaSE<Vertice<T>> camino, double cost){
        this.coste = cost;
        this.camino = camino ;
    }



    public ListaSE<Vertice<T>> getCamino() {
        return camino;
    }

    public double getCoste() {
        return coste;
    }


    public String toString() {
        StringBuffer salida = new StringBuffer();
        salida.append("======= Volcado del camino desde [" + this.getCamino().getElemento(0).getDato() + "] hasta [" + this.getCamino().getElemento(this.camino.numElementos()-1).getDato() + "]: ======\n");
        salida.append("Referencias a los vértices: " + this.getCamino() + "\n");
        salida.append("Lista de datos en vértices: [");
        for (Object vertex : this.getCamino().values()) {
            Vertice vertexx = (Vertice) vertex;
            salida.append(vertexx.getDato());
        }
        salida.append("] - Coste: " + this.getCoste() + "\n");

        return salida.toString();
    }
}
