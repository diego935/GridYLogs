package ClasesProyecto.Individuos;

import ClasesProyecto.Global;
import Excepciones.Abajo;
import Excepciones.Arriba;
import Excepciones.Derecha;
import Excepciones.Izquierda;

public class básico extends Individuo{

    public básico(int id, int vida, int generación, double pClonacion, double pReproduccion, Individuo padre, Individuo madre){
        super(id,  vida, generación,  pClonacion, pReproduccion, padre, madre);
    }
    public básico(int id, int vida, int generación, double pClonacion, double pReproduccion){
        super(id,  vida, generación,  pClonacion, pReproduccion);
    }
    /*public básico(int id, int vida, int generación, double pClonacion, double pReproduccion, Individuo padre){
        super(id,  vida, generación,  pClonacion, pReproduccion, padre);
    }*/
    public básico(int id,int generación, Individuo padre){
        super(id, generación, padre);
    }

    /*@Override
    public básico clone(int id) {
        return new Individuo(id, int ) ;
    }*/

    /*@Override
    public básico clone(int id, int vida, int generación) {
        básico b1 = new básico(id, vida, generación,this.pClonacion,this.pReproduccion,this );
        b1.setPos(this.pos);
        return b1;
    }*/


    @Override
    public int mover() {
        int random = (int) ((20*Math.random())%4);
        System.out.println(random);

        if (random==0) {
            if (this.pos[0] !=0) return 1;
            return 0;
        }
        if (random==1) {
            if (this.pos[0] != Global.getMax()[0])  return 0;
            return 1;
        }

        if (random==2) {
            if (this.pos[1] != 0) return 2;

            return  3;
        }
        if (random==3){
            if (this.pos[1] != Global.getMax()[1])  return 3;
            return 2;
        }
        return -1;
    }


    @Override
    public int Type(){
        return 0;
    }
}
