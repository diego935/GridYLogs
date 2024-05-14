package ClasesProyecto.Individuos;

import static ClasesProyecto.Global.mapa;

public class Normal extends Individuo{

    Integer[] objetivo;
    public Normal(int id, int vida, int generación, float pClonacion, float pReproduccion, Individuo padre, Individuo madre){
        super(id,  vida, generación,  pClonacion, pReproduccion, padre, madre);
    }
    public Normal(int id, int vida, int generación, float pClonacion, float pReproduccion){
        super(id,  vida, generación,  pClonacion, pReproduccion);
    }
    public Normal(int id, int vida, int generación, float pClonacion, float pReproduccion, Individuo padre){
        super(id,  vida, generación,  pClonacion, pReproduccion, padre);

    }


    @Override
    public Normal clone(int id, int vida, int generación) {
        Normal b1 = new Normal(id, vida, generación,this.pClonacion,this.pReproduccion,this );
        b1.setPos(this.pos);
        return b1;
    }

    @Override
    public int mover() {
        if (objetivo==null) objetivo = getRecurspRandom();
        if(this.pos == objetivo) objetivo = getRecurspRandom();
        Integer[] proximaPos = objetivo;
        proximaPos[0] = proximaPos[0] - this.pos[0];
        proximaPos[1] = proximaPos[1] - this.pos[1];

        if(Math.abs(proximaPos[0])>= Math.abs(proximaPos[1])) {
            if (proximaPos[0] >=1) return 0;
            if (proximaPos[0] <= -1) return 2;
        }
        else {
            if (proximaPos[1] >= 1) return 1;
            if (proximaPos[1] <= -1) return 3;
        }
    return 0;
    }

    @Override
    public int Type() {
        return 1;
    }


}


