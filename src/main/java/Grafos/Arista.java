package Grafos;

public class Arista<T> {
    private double peso;
    private Vertice<T> origen;
    private Vertice<T> destino;


    public Arista(Vertice<T> origen, Vertice<T> destino, double peso){
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public Vertice<T> getDestino() {
        return destino;
    }

    public Vertice<T> getOrigen() {
        return origen;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
