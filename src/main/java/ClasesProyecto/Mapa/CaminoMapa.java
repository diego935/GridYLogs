package ClasesProyecto.Mapa;

import Listas.ListasSE.ListaSE;

public class CaminoMapa<T> {
    private double coste;
    ListaSE<Casilla<T>> camino;

    public CaminoMapa(ListaSE<Casilla<T>> camino, double cost){
        this.coste = cost;
        this.camino = camino;
    }

    public ListaSE<Casilla<T>> getCamino() {
        return camino;
    }

    public double getCoste() {
        return coste;
    }


    public String toString() {
        StringBuffer salida = new StringBuffer();
        Casilla inicio = this.getCamino().getElemento(0);
        Casilla fin = this.getCamino().getElemento(this.camino.numElementos()-1);
        salida.append("======= Volcado del camino desde [" + inicio.getPos()[0]+","+ inicio.getPos()[1] + "] hasta [" + fin.getPos()[0]+","+ fin.getPos()[1]+ "]: ======\n");
        salida.append("Referencias a los vértices: " + this.getCamino() + "\n");
        salida.append("Lista de datos en vértices: [");
        for (Object vertex : this.getCamino().values()) {
            Casilla vertexx = (Casilla) vertex;
            salida.append("["+ vertexx.getPos()[0] +","+vertexx.getPos()[1] +"]");
        }
        salida.append("] - Coste: " + this.getCoste() + "\n");

        return salida.toString();
    }
}
