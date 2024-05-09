package ClasesProyecto.Individuos;

import ClasesProyecto.Mapa.CaminoMapa;
import ClasesProyecto.Mapa.Casilla;
import Excepciones.Abajo;
import Excepciones.Arriba;
import Excepciones.Derecha;
import Excepciones.Izquierda;
import Listas.ListasSE.Map;

import static ClasesProyecto.Global.mapa;

public class Avanzado extends Individuo{

    public Avanzado(int id, int vida, int generación, float pClonacion, float pReproduccion, Individuo padre, Individuo madre){
        super(id,  vida, generación,  pClonacion, pReproduccion, padre, madre);
    }
    public Avanzado(int id, int vida, int generación, float pClonacion, float pReproduccion){
        super(id,  vida, generación,  pClonacion, pReproduccion);
    }
    public Avanzado(int id, int vida, int generación, float pClonacion, float pReproduccion, Individuo padre){
        super(id,  vida, generación,  pClonacion, pReproduccion, padre);

    }

    /*@Override
    public básico clone(int id) {
        return new Individuo(id, int ) ;
    }*/

    @Override
    public Avanzado clone(int id, int vida, int generación) {
        Avanzado b1 = new Avanzado(id, vida, generación,this.pClonacion,this.pReproduccion,this );
        b1.setPos(this.pos);
        return b1;
    }

    @Override
    public void mover() throws Arriba, Abajo, Izquierda, Derecha {


        //Crea el mapa de mejores rutas a cualquier punto del mapa
        Map<Casilla<Casilla>, CaminoMapa<Casilla>> dijkstra = mapa.dijkstra(mapa.casillas[pos[0]][pos[1]]);
        System.out.println(dijkstra.getFromId(mapa.casillas[2][2]));


        Integer[] proximaPos = dijkstra.getFromId(mapa.casillas[2][2]).getCamino().getElemento(1).getPos();
        proximaPos[0] = proximaPos[0]- this.pos[0];
        if (proximaPos[0] ==1) throw new Arriba();
        if (proximaPos[0] ==-1) throw new Abajo();

        proximaPos[1] = proximaPos[1]- this.pos[1];
        if (proximaPos[1] ==1) throw new Derecha();
        if (proximaPos[1] ==-1) throw new Izquierda();
    }


    @Override
    public int Type() {
        return 2;
    }
}
