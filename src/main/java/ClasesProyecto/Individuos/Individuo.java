package ClasesProyecto.Individuos;

import Arboles.ArbolBinario;
import Arboles.ArbolGenialogico;
import ClasesProyecto.Global;
import ClasesProyecto.Recurso;
import Excepciones.*;
import Listas.ListaDE.Cola;

import static ClasesProyecto.Global.*;
import static com.example.matcompmppfinalcomponentes.HelloController.m;

public abstract class Individuo {

    public int id;
    public int generación;
    public ArbolBinario<Individuo> familia;
    public int vida;
    public double pReproduccion;
    public double pClonacion;
    public Integer[] pos;

    public Cola<String> acciones = new Cola<>();
    public Individuo() {
//Para Debug
    }

    public Individuo(int id, int vida, int generación, double pClonacion, double pReproduccion, Individuo padre, Individuo madre) {
        familia = new ArbolGenialogico(this, padre, madre);
        this.vida = vida;
        this.id = id;
        this.generación = generación;
        this.pReproduccion = pReproduccion;
        this.pClonacion = pClonacion;

        this.acciones.add("Turno: "+ turno + " Nace, con: Vida: " + this.vida);
    }

    public Individuo(int id, int vida, int generación, double pClonacion, double pReproduccion) {
        familia = new ArbolGenialogico(this);
        this.vida = vida;
        this.id = id;
        this.generación = generación;
        this.pReproduccion = pReproduccion;
        this.pClonacion = pClonacion;

        this.acciones.add("Turno: "+ turno + " Nace");
    }

    public Individuo(int id, int generación, Individuo padre) {
        //Si se clona que reciba todos los elementos del padre
        this.id = id;
        this.vida = padre.vida;
        this.pClonacion = padre.pClonacion;
        this.pReproduccion = padre.pReproduccion;
        this.familia = new ArbolGenialogico(this, padre);
        this.acciones.add("Turno: "+ turno + " Muere");
    }

    public int getId() {
        return id;
    }

    public void setPos(Integer[] pos) {
        this.pos = pos;
    }

    public int getGeneración() {
        return generación;
    }


    public ArbolBinario<Individuo> getFamilia() {
        return familia;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void cambiarVida(int vida) {
        this.vida += vida;
    }

    public double getpReproduccion() {
        return pReproduccion;
    }

    public void setpReproduccion(float pReproduccion) {
        this.pReproduccion = pReproduccion;
    }

    public void cambiarpReproduccion(float pReproduccion) {
        this.pReproduccion += pReproduccion / 100;
    }

    public double getpClonacion() {
        return pClonacion;
    }

    public void setpClonacion(float pClonacion) {
        this.pClonacion = pClonacion;
    }

    public void cambiarPClonacion(float pClonacion) {
        this.pClonacion += pClonacion / 100;
    }

    //public abstract Individuo clone(int id, int vida, int generación);

    public void morir() throws Muerte {
        this.acciones.add("Turno: "+ turno + "Muere");
        throw new Muerte(this);
    }

    public void actualizarVida() throws Muerte {
        this.vida -= 1;
        if (this.vida <= 0) this.morir();
        this.acciones.add("Turno: "+ turno + "Pasa 1 turno Vida:"+ this.vida);
    }

    public Integer[] getPos() {
        return pos;
    }

    public abstract int mover(); //0 es Arriba 1 abajo 2 izquierda y 3 derecha

    public abstract int Type();

    public boolean reproduce() throws Muerte {
        Boolean reproduce = Math.random() <= this.pReproduccion;
        if (!reproduce) {
            this.morir();
            return false;
        } else {
            this.acciones.add("Turno: "+ turno + "Se reproduce ");
            return true;
        }
    }

    public void recurso(Recurso r) throws Muerte {
        int tipo = r.getTipo();
        if (tipo == 1) {
            this.vida += 2;
            this.acciones.add("Turno: "+ turno + "Recoge Agua, Vida:"+ this.vida);
        }
        if (tipo == 2) {
            this.vida += 10;
            this.acciones.add("Turno: "+ turno + "Recoge Comida, Vida: "+ this.vida);
        }
        if (tipo == 3) {
            this.vida -= 2;
            this.acciones.add("Turno: "+ turno + "Atraviesa Montaña, Vida: "+ this.vida);
        }
        if (tipo == 4) {
            this.pReproduccion += mejoraTesoro;
            this.acciones.add("Turno: "+ turno + "Recoge Tesoro, Probabilidad de Reproducción: "+ this.pReproduccion);
        }
        if (tipo == 5) {
            this.pClonacion += mejoraBiblio;
            this.acciones.add("Turno: "+ turno + "Biblioteca, Probabilidad Clonación: "+ this.pClonacion+ "Nuevo tipo: "+ Type()+1);
            //this.evolucionar();
        }

        if (tipo == 6) this.morir();

    }

    public void evolucionar() throws Muerte {
        if (Type() == 2) return;
        if (Type() == 0){
            Normal i = new Normal(this.id,
                    this.vida,
                    this.generación,
                    this.pClonacion,
                    this.pReproduccion,
                    this.familia.getSubArbolIzquierda().getListaDatosNivel(0).recuperar(0),
                    this.familia.getSubArbolDerecha().getListaDatosNivel(0).recuperar(0));
            i.acciones = this.acciones;
            Global.addIndividuo(i);
            this.morir();
        }else {
            Avanzado i = new Avanzado(this.id,
                    this.vida,
                    this.generación,
                    this.pClonacion,
                    this.pReproduccion,
                    this.familia.getSubArbolIzquierda().getListaDatosNivel(0).recuperar(0),
                    this.familia.getSubArbolDerecha().getListaDatosNivel(0).recuperar(0));

            Global.addIndividuo(i);
            this.morir();
        }


    }


    public Integer[] recursoPositivoCercano() {
        if (recursos.isVacia()) return new Integer[]{(mapa.getMax()[0] + 1) / 2, (mapa.getMax()[1] + 1) / 2};
        Integer[] recursoCercanoPos = new Integer[]{(mapa.getMax()[0] + 1) / 2, (mapa.getMax()[1] + 1) / 2};
        int min = Integer.MAX_VALUE;
        Integer[] recursoPos;
        int distancia;

        for (Object r2 : recursos.values()) {
            Recurso r = (Recurso) r2;
            if (r.getTipo() == 3 || r.getTipo() == 6) {continue;
            }else {
            recursoPos = r.getPos();
            distancia = distancia(recursoPos, this.pos);
            if (distancia < min && distancia != 0) {
                min = distancia;
                recursoCercanoPos = recursoPos;
            }
        }
        }
        for (int i =0;i<2;i++) {
            if (recursoCercanoPos[i] < 0) recursoCercanoPos[i] = - recursoCercanoPos[i];
        }
        return recursoCercanoPos;
    }

    public Integer[] IndividuoCercano() {
        //En este caso ya sabemos que hay alguno (Si no se habría acabado la partida).
        Integer[] pos;
        Integer[] cercanoPos = new Integer[2];
        int distancia;
        int min = Integer.MAX_VALUE;

        for (Object i2 : individuos.keys()) {
            Individuo i = (Individuo) i2;
            pos = i.getPos();
            distancia = distancia(pos, this.pos);
            if (distancia < min && distancia != 0) {
                min = distancia;
                cercanoPos = pos;
            }
        }
        return cercanoPos;
    }

    public Integer[] getRecurspRandom() {
        if (recursos.isVacia()) return new Integer[]{(getMax()[0] + 1) / 2, (getMax()[1] + 1) / 2};
        int random = (int) (30 * Math.random()) % recursos.numElementos();
        return recursos.getElemento(random).getPos();

    }


    public int distancia(Integer[] p1, Integer[] p2) {
        //Norma 1 :)
        int distancia = Math.abs(p1[0] - p2[0]) + Math.abs(p1[0] - p2[0]);
        return distancia;
    }

    /*public void clona(Individuo i){
        this.pos = i.pos;
        this.pClonacion = i.pClonacion;
        this.pReproduccion = i.pReproduccion;
        this
    }*/
}
