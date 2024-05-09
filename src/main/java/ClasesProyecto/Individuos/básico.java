package ClasesProyecto.Individuos;

import ClasesProyecto.Global;
import Excepciones.Abajo;
import Excepciones.Arriba;
import Excepciones.Derecha;
import Excepciones.Izquierda;

public class básico extends Individuo{

    public básico(int id, int vida, int generación, float pClonacion, float pReproduccion, Individuo padre, Individuo madre){
        super(id,  vida, generación,  pClonacion, pReproduccion, padre, madre);
    }
    public básico(int id, int vida, int generación, float pClonacion, float pReproduccion){
        super(id,  vida, generación,  pClonacion, pReproduccion);
    }
    public básico(int id, int vida, int generación, float pClonacion, float pReproduccion, Individuo padre){
        super(id,  vida, generación,  pClonacion, pReproduccion, padre);

    }

    /*@Override
    public básico clone(int id) {
        return new Individuo(id, int ) ;
    }*/

    @Override
    public básico clone(int id, int vida, int generación) {
        básico b1 = new básico(id, vida, generación,this.pClonacion,this.pReproduccion,this );
        b1.setPos(this.pos);
        return b1;
    }


    @Override
    public void mover() throws Arriba, Abajo, Izquierda,Derecha{
        int random = (int) ((20*Math.random())%4);
        if (random==0) {
            if (this.pos[0] !=0)throw new Abajo();
            throw new Arriba();

        }
        if (random==1) {
            if (this.pos[0] != Global.getMax()[0])  throw new Arriba();
            throw new Abajo();
        }

        if (random==2) {
            if (this.pos[1] != 0) throw new Izquierda();

            throw new Derecha();
        }
        if (random==3){
            if (this.pos[1] != Global.getMax()[1])  throw new Derecha();
            throw new Izquierda();
        }

    }


    @Override
    public int Type(){
        return 0;
    }
}
