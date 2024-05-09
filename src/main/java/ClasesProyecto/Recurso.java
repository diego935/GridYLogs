package ClasesProyecto;

import ClasesProyecto.Mapa.Casilla;

public class Recurso {
    int tipo;
// 1. Agua
//2. Comida
//3. Montaña
//4. Tesoro
//5. Biblioteca
//6. Pozo

    int vida;
    Casilla posicion;
    public Recurso(int tipo, Casilla posicion){
        this.tipo = tipo;
        this.vida=3 ;
        this.posicion = posicion;
    }
    public void actualizar(){
        this.vida--;
        if (this.vida ==0){
            this.posicion.recursos.delDato(this);
            this.posicion.cambioPeso(-Global.CambioPeso(this.tipo));
        }
    }

    public int getTipo() {
        return tipo;
    }
    public Integer[] getPos(){
        return this.posicion.getPos();
    }
}
