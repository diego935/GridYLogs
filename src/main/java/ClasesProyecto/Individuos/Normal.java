package ClasesProyecto.Individuos;

import static ClasesProyecto.Global.mapa;

public class Normal extends Individuo{

    Integer[] objetivo;
    public Normal(int id, int vida, int generación, double pClonacion, double pReproduccion, Individuo padre, Individuo madre){
        super(id,  vida, generación,  pClonacion, pReproduccion, padre, madre);
    }
    public Normal(int id, int vida, int generación, double pClonacion, double pReproduccion){
        super(id,  vida, generación,  pClonacion, pReproduccion);
    }
    /*public Normal(int id, int vida, int generación, double pClonacion, double pReproduccion, Individuo padre){
        super(id,  vida, generación,  pClonacion, pReproduccion, padre);
    }*/
    public Normal(int id,int generación, Individuo padre){
        super(id, generación, padre);
    }


    /*@Override
    public Normal clone(int id, int vida, int generación) {
        Normal b1 = new Normal(id, vida, generación,this.pClonacion,this.pReproduccion,this );
        b1.setPos(this.pos);
        return b1;
    }*/

    @Override
    public int mover() {
        if (objetivo==null) objetivo = getRecurspRandom();
        if(this.pos[0] == objetivo[0] && this.pos[1] == objetivo[1]) objetivo = getRecurspRandom();
        objetivo[0] = Math.abs(objetivo[0]);
        objetivo[1] = Math.abs(objetivo[1]);

        Integer[] proximaPos = objetivo;
        System.out.println(objetivo[0] + ","+ objetivo[1]);
        proximaPos[0] = proximaPos[0] - this.pos[0];
        proximaPos[1] = proximaPos[1] - this.pos[1];

        if(Math.abs(proximaPos[0])>= Math.abs(proximaPos[1])) {
            if (proximaPos[0] >=1) return 0;
            if (proximaPos[0] <= -1) return 1;
        }
        else {
            if (proximaPos[1] >= 1) return 3;
            if (proximaPos[1] <= -1) return 2;
        }
    return 0;
    }

    @Override
    public int Type() {
        return 1;
    }


}


