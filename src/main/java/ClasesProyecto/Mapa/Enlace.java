package ClasesProyecto.Mapa;

public class Enlace<T> {
    private double peso;
    private Casilla<T> origen;
    private Casilla<T> destino;


    public Enlace(Casilla<T> origen, Casilla<T> destino, double peso){
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public Casilla<T> getDestino() {
        return destino;
    }

    public Casilla<T> getOrigen() {
        return origen;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }


}

