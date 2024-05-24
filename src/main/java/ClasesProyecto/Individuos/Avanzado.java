package ClasesProyecto.Individuos;

import ClasesProyecto.Mapa.CaminoMapa;
import ClasesProyecto.Mapa.Casilla;
import Listas.ListasSE.Map;

import static ClasesProyecto.Global.mapa;

public class Avanzado extends Individuo{
    Integer[] objetivo;
    int paso = 1;
    CaminoMapa<Casilla> camino = new CaminoMapa<>();
    public Avanzado(int id, int vida, int generación, double pClonacion, double pReproduccion, Individuo padre, Individuo madre){
        super(id,  vida, generación,  pClonacion, pReproduccion, padre, madre);
    }
    public Avanzado(int id, int vida, int generación, double pClonacion, double pReproduccion){
        super(id,  vida, generación,  pClonacion, pReproduccion);
    }
    /*public Avanzado(int id, int vida, int generación, double pClonacion, double pReproduccion, Individuo padre){
        super(id,  vida, generación,  pClonacion, pReproduccion, padre);
    }*/
    public Avanzado(int id,int generación, Individuo padre){
        super(id, generación, padre);
    }

    /*@Override
    public básico clone(int id) {
        return new Individuo(id, int ) ;
    }*/

    /*@Override
    public Avanzado clone(int id, int vida, int generación) {
        Avanzado b1 = new Avanzado(id, vida, generación,this.pClonacion,this.pReproduccion,this );
        b1.setPos(this.pos);
        return b1;
    }*/

    @Override
    public int mover() {

       /* if (objetivo==null) objetivo = recursoPositivoCercano();
        if(pos[0]==objetivo[0] && pos[1]==objetivo[1]) objetivo = recursoPositivoCercano();

            //Crea el mapa de mejores rutas a cualquier punto del mapa
            Map<Casilla<Casilla>, CaminoMapa<Casilla>> dijkstra = mapa.dijkstra(mapa.casillas[pos[0]][pos[1]]);
            System.out.println(dijkstra.getFromId(mapa.casillas[objetivo[0]][objetivo[1]]));

            Integer[] proximaPos = dijkstra.getFromId(mapa.casillas[objetivo[0]][objetivo[1]]).getCamino().getElemento(1).getPos();

            proximaPos[0] = proximaPos[0] - this.pos[0];
            if (proximaPos[0] == 1) return 0;
            if (proximaPos[0] == -1) return 1;

            proximaPos[1] = proximaPos[1] - this.pos[1];
            if (proximaPos[1] == 1) return 3;
            if (proximaPos[1] == -1) return 2;

            return 12;
    */
        if (camino == null) {
            objetivo = recursoPositivoCercano();
            if ((pos[0] == objetivo[0] && pos[1] == objetivo[1]) || objetivo == null) objetivo = IndividuoCercano();//En caso de no encontrar ningun recurso y ya estar en el centro del mapa busca el camino hacía el individuo más cercano para reproducirse.

            //Crea el mapa de mejores rutas a cualquier punto del mapa
            Map<Casilla<Casilla>, CaminoMapa<Casilla>> dijkstra = mapa.dijkstra(mapa.casillas[pos[0]][pos[1]]);
  //          System.out.println(objetivo[0]+","+ objetivo[1]);
  //          System.out.println(dijkstra.getFromId(mapa.casillas[objetivo[0]][objetivo[1]]));

            camino = dijkstra.getFromId(mapa.casillas[objetivo[0]][objetivo[1]]);
            this.paso = 1;
            return this.mover();
        }
        if (objetivo == null){
            objetivo = IndividuoCercano();
            return mover();
        } else if (camino.getCamino() == null) {
            objetivo = recursoPositivoCercano();
            if ((pos[0] == objetivo[0] && pos[1] == objetivo[1]) || objetivo == null){objetivo = IndividuoCercano();}//En caso de no encontrar ningun recurso y ya estar en el centro del mapa busca el camino hacía el individuo más cercano para reproducirse.

            //Crea el mapa de mejores rutas a cualquier punto del mapa
            Map<Casilla<Casilla>, CaminoMapa<Casilla>> dijkstra = mapa.dijkstra(mapa.casillas[pos[0]][pos[1]]);
            System.out.println(objetivo[0]+","+ objetivo[1]);
            System.out.println(dijkstra.getFromId(mapa.casillas[objetivo[0]][objetivo[1]]));

            camino = dijkstra.getFromId(mapa.casillas[objetivo[0]][objetivo[1]]);
            this.paso = 1;
            return this.mover();
        } else {
            if (camino.getCamino().getElemento(paso) != null) {

                Integer[] proximaPos = camino.getCamino().getElemento(paso++).getPos();


                proximaPos[0] = proximaPos[0] - this.pos[0];
                if (proximaPos[0] == 1) return 0;
                if (proximaPos[0] == -1) return 1;

                proximaPos[1] = proximaPos[1] - this.pos[1];
                if (proximaPos[1] == 1) return 3;
                if (proximaPos[1] == -1) return 2;


            } else {
                camino = null;
                return this.mover();
            }
        }
        return 12;
    }

    @Override
    public int Type() {
        return 2;
    }
}
