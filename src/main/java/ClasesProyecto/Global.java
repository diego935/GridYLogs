package ClasesProyecto;

import ClasesProyecto.Individuos.Individuo;
import ClasesProyecto.Mapa.Casilla;
import ClasesProyecto.Mapa.Mapa;
import Excepciones.*;
import Listas.ListasSE.ListaSE;
import Listas.ListasSE.Map;
import javafx.scene.control.cell.TextFieldListCell;

public class Global {
    //Desde donde se dirige todo.
    public static int id;
    public static int turno;
    public static Map<Individuo, Integer[]> individuos = new Map<>();
    public static Mapa<Casilla> mapa;
    public static ListaSE<Recurso> recursos = new ListaSE<>();
    public static double pRecursos=0.05;
    public static int pAgua=1;
    public static int pComida=1;
    public static int pMontaña=1;
    public static int pTesoro=1;
    public static int pBiblio=1;
    public static int pPozo=1;
    public static double porcentajeMejora =0.5;
    public static double mejoraTesoro =0.3;
    public static double mejoraBiblio =0.3;



    public Global(Mapa mapa, Map<Individuo, Integer[]> individuos) {
        this.mapa = mapa;
        this.individuos = individuos;
        //Aqui ya hay una base
    }


    public void moverIndividuo(Individuo i, Integer[] dirección) {
        Integer[] pos = individuos.getFromId(i);
        try{
        mapa.casillas[Math.abs(pos[0])][Math.abs(pos[1])].delColono(i);
        pos[0] += dirección[0];
        pos[1] += dirección[1];
        i.setPos(pos);
        individuos.eliminar(i);
        individuos.add(pos, i);
        mapa.casillas[pos[0]][pos[1]].addColono(i);
    }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addIndividuo(Individuo i) {
        //System.out.println(i.pos[0]+","+i.pos[1]);

        try {
            mapa.casillas[i.pos[0]][i.pos[1]].addColono(i);
            individuos.add(i.getPos(), i);
        } catch (Exception e) {
            System.out.println("Mal");
        }

    }

    public void moverIndividuos() {
        for (Object i2 : individuos.keys()) {
            Individuo i = (Individuo) i2;

            int movimiento = i.mover();

            if (movimiento == 0) moverIndividuo(i, new Integer[]{1, 0});
            else if (movimiento==1) {moverIndividuo(i, new Integer[]{-1, 0});}
            else if (movimiento==2) {moverIndividuo(i, new Integer[]{0, -1});}
            else if(movimiento == 3) moverIndividuo(i, new Integer[]{0, 1});
            //Podría optimizarse más metiendo eso dentro de la función move del individuo pero bueno.
        }
    }

public void generarRecursos() {
    for (Casilla[] c2 : mapa.casillas) {
        for (Casilla c : c2) {
            c.generarRecurso();
        }
    }
}


public void generarRecurso(int n, int m, int tipo) {
    mapa.casillas[n][m].generarRecurso(tipo);
}

public void ActualizarRecursos() {
    for (Object r2 : recursos.values()) {
        Recurso r = (Recurso) r2;
        r.actualizar();
    }
}

public void ActualizarVida() {
    for (Object i2 : individuos.keys()) {
        Individuo i = (Individuo) i2;
        try {
            i.actualizarVida();
        } catch (Muerte muerte) {
            //individuos.eliminar(i);
            //mapa.casillas[i.pos[0]][i.pos[1]].delColono(i);
        }
    }
}

public void reproducción() {
    for (Casilla[] casillas : mapa.casillas) {
        for (Casilla casilla : casillas) {
            if (casilla.colonos.numElementos() >= 2 /*Se podría poner tambien como mayor que 2*/) {
                try {
                    casilla.gestionReproducción();
                } catch (Muerte muerte) {
                }

            }
        }
    }
}

public void clonacion(){
    for (Object i2 : individuos.keys()) {
        Individuo i = (Individuo)i2;
        if (Math.random() <=i.pClonacion ) {
                try {
                    mapa.casillas[i.pos[0]][i.pos[1]].clona(i);
                } catch (Muerte muerte) {
                }

            }

    }
}

public void aplicarRecursos() {
    for (Casilla[] casillas : mapa.casillas) {
        for (Casilla casilla : casillas) {
            if (!casilla.colonos.isVacia() && !casilla.recursos.isVacia()) {

                try {
                    casilla.aplicarRecursos();
                } catch (Muerte muerte) {

                }


            }
        }
    }

}

public static int[] getMax() {
    return Mapa.getMax();
}

public void pasarTurno() {
        try {
            this.ActualizarVida();
            this.ActualizarRecursos();
            this.generarRecursos();
            this.moverIndividuos();
            this.reproducción();
            this.clonacion();
            this.aplicarRecursos();
        } catch (Exception a){
            }
    turno++;

    /*if (turno % 10 == 0) {
        try {
            Thread.sleep(500);
            System.out.println("aasd");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }*/

   /* if (Global.individuos.numElementos() <= 1) {
        //pause =true;
        System.out.println("Se ha terminado");
        if (turno % 10 == 0) {
            try {
                Thread.sleep(5000);
                System.out.println("aasd");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                //System.exit(1);
            }
        }
    }*/
}


public static int CambioPeso(int r) {
    int diferencia = 0;
    if (r == 1) diferencia += 2;
    else if (r == 2) diferencia += 10;
    else if (r == 3) diferencia -= 2;
    else if (r == 4) diferencia += 1;
    else if (r == 5) diferencia += 2;
    else if (r == 6) diferencia -= 20000;
    return -diferencia;
}


}
