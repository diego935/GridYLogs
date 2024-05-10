package ClasesProyecto.Individuos;

import Arboles.ArbolBinario;
import Arboles.ArbolGenialogico;
import ClasesProyecto.Recurso;
import Excepciones.*;

import static ClasesProyecto.Global.*;
import static com.example.matcompmppfinalcomponentes.HelloController.m;

public abstract class Individuo {

    public int id;
    public int generación;
    public ArbolBinario<Individuo> familia;
    public int vida;
    public float pReproduccion;
    public float pClonacion;
    public Integer[] pos;

    public Individuo() {
//Para Debug
    }

    public Individuo(int id, int vida, int generación, float pClonacion, float pReproduccion, Individuo padre, Individuo madre) {
        familia = new ArbolGenialogico(this, padre, madre);
        this.vida = vida;
        this.id = id;
        this.generación = generación;
        this.pReproduccion = pReproduccion;
        this.pClonacion = pClonacion;

    }

    public Individuo(int id, int vida, int generación, float pClonacion, float pReproduccion) {
        familia = new ArbolGenialogico(this);
        this.vida = vida;
        this.id = id;
        this.generación = generación;
        this.pReproduccion = pReproduccion;
        this.pClonacion = pClonacion;

    }

    public Individuo(int id, int vida, int generación, float pClonacion, float pReproduccion, Individuo padre) {
        //Si se clona que reciba todos los elementos del padre
        this.id = id;
        this.vida = vida;
        this.pClonacion = pClonacion;
        this.pReproduccion = pReproduccion;
        this.familia = padre.getFamilia();
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

    public float getpReproduccion() {
        return pReproduccion;
    }

    public void setpReproduccion(float pReproduccion) {
        this.pReproduccion = pReproduccion;
    }

    public void cambiarpReproduccion(float pReproduccion) {
        this.pReproduccion += pReproduccion / 100;
    }

    public float getpClonacion() {
        return pClonacion;
    }

    public void setpClonacion(float pClonacion) {
        this.pClonacion = pClonacion;
    }

    public void cambiarPClonacion(float pClonacion) {
        this.pClonacion += pClonacion / 100;
    }

    public abstract Individuo clone(int id, int vida, int generación);

    public void morir() throws Muerte {
        throw new Muerte(this);
    }

    public void actualizarVida() throws Muerte {
        this.vida -= 2;
        if (this.vida <= 0) this.morir();
    }

    public Integer[] getPos() {
        return pos;
    }

    public abstract int mover();

    public abstract int Type();

    public boolean reproduce() throws Muerte {
        Boolean reproduce = Math.random() <= this.pReproduccion;
        if (!reproduce) {
            this.morir();
            return false;
        } else return true;

    }

    public void recurso(Recurso r) throws Muerte {
        int tipo = r.getTipo();
        if (tipo == 1) this.vida += 2;
        if (tipo == 2) this.vida += 10;
        if (tipo == 3) this.vida -= 2;
        //if (tipo ==4) this.vida +=2;
        //if (tipo ==5) this.vida +=2;
        if (tipo == 6) this.morir();

    }


    public Integer[] recursoPositivoCercano() {
        if(recursos.isVacia()) return new Integer[]{(mapa.getMax()[0]+1)/2,(mapa.getMax()[1]+1)/2};
        Integer[] recursoCercanoPos = new Integer[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        int min = Integer.MAX_VALUE;
        Integer[] recursoPos;
        int distancia;

        for (Object r2 : recursos.values()) {
            Recurso r = (Recurso) r2;
            recursoPos = r.getPos();
            distancia = distancia(recursoPos, this.pos);
            if (distancia < min && distancia!= 0) {
                min = distancia;
                recursoCercanoPos = recursoPos;
            }
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
            if (distancia < min) {
                min = distancia;
                cercanoPos = pos;
            }
        }
        return cercanoPos;
    }

    public Integer[] getRecurspRandom(){
        if (recursos.isVacia()) return new Integer[]{(getMax()[0]+1)/2,(getMax()[1]+1)/2};
        int random = (int) (30 * Math.random()) % recursos.numElementos();
        return recursos.getElemento(random).getPos();

    }


    public int distancia(Integer[] p1, Integer[] p2) {
        //Norma 1 :)
        int distancia = Math.abs(p1[0] - p2[0]) + Math.abs(p1[0] - p2[0]);
        return distancia;
    }
}
