package Excepciones;

import ClasesProyecto.Individuos.Individuo;

import static ClasesProyecto.Global.individuos;
import static ClasesProyecto.Global.mapa;

public class Muerte extends Exception{
    public Muerte(Individuo i){
        super();
        System.out.println("Ha muerto:" + i + "con ID:" + i.id);
        individuos.eliminar(i);
        mapa.casillas[i.pos[0]][i.pos[1]].delColono(i);

    }
}
