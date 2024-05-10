package ClasesProyecto.Mapa;

import ClasesProyecto.Global;
import ClasesProyecto.Individuos.Individuo;
import ClasesProyecto.Individuos.básico;
import ClasesProyecto.Recurso;
import Excepciones.Muerte;
import Grafos.Arista;
import Listas.ListasSE.ListaSE;
import Listas.ListasSE.Map;

import java.util.Random;

import static ClasesProyecto.Global.*;

public class Casilla<Tipo> {

    private Tipo dato;
    //private Map<Casilla<Tipo>, Enlace<Tipo>> entradas = new Map<>();
    private Map<Casilla<Tipo>, Enlace<Tipo>> salidas = new Map<>();
    public ListaSE<Individuo> colonos = new ListaSE<>();
    public ListaSE<Recurso> recursos = new ListaSE<>();
    public Integer[] pos;
    public int peso =40;
    public Casilla(Tipo dato) {
        this.dato = dato;
    }

    public Casilla(Integer[] pos) {
        this.pos = pos;
    }

    public Casilla() {
        this.dato = null;
    }

    public void addAristaSalida(Enlace<Tipo> a) {
        salidas.add(a, a.getDestino());
    }

    //public void addAristaEntrada(Enlace<Tipo> a) {
    //    //El mapa ya verifica que no exista ese vertice en las salidas.
    //    entradas.add(a, a.getOrigen());
    //}

    //public void addEntrada(Casilla casilla) {
    //    addAristaEntrada(new Enlace<Tipo>(casilla, this, 1));
    //}

    public void addSalida(Casilla casilla) {
        addAristaSalida(new Enlace<Tipo>(this, casilla, 1));
    }

    public Integer[] getPos() {
        return pos;
    }

    public Tipo getDato() {
        return dato;
    }

    public boolean isSalida(Casilla<Tipo> v) {
        return this.salidas.isId(v);
    }

    //public boolean isEntrada(Casilla<Tipo> v) {
     //   return this.entradas.isId(v);
    //}

    public void delAristaSalida(Enlace a) {
        //this.salidas.eliminar(a.getDestino());
    }

    public void delAristaEntrada(Arista a) {
        //this.entradas.eliminar(a.getOrigen());
    }

    /*public Object[] getEntradas() {
        if (this.entradas.isVacia()) return new Arista[0];
        return this.entradas.values();
    }*/

    public Object[] getSalidas() {
        if (this.salidas.isVacia()) return new Arista[0];
        return this.salidas.values();

    }

    public void cambioPeso(int diferencia) {
        //for (Object casillas : entradas.keys()) {
        //    Casilla casilla = (Casilla) casillas;
            this.peso += diferencia;
        //}
    }

    public void addColono(Individuo i) {
        this.colonos.add(i);
    }

    public void delColono(Individuo i) {
        this.colonos.delDato(i);

    }

    public void generarRecurso() {

        //Comprueba que haya espacio para él
        if (this.recursos.numElementos() >= 3) return;

        if (Math.random() > Global.pRecursos) return;


        Random r = new Random();
        int tipo =1;
        int prop = r.nextInt(pAgua+pComida+pBiblio+pMontaña+pPozo+pTesoro);
        for (Integer p : new Integer[]{pAgua,pComida,pMontaña,pTesoro, pBiblio, pPozo}) {
        if (prop<= p) break;
        prop-=p;
        tipo++;
        }

        System.out.println(tipo);





        Recurso recurso = new Recurso(tipo, this);
        this.recursos.add(recurso);
        Global.recursos.add(recurso);
        this.peso+= Global.CambioPeso(tipo);
        this.cambioPeso(Global.CambioPeso(tipo));
    }


    public void generarRecurso(int tipo){

        Recurso recurso = new Recurso(tipo, this);
        this.recursos.add(recurso);
        Global.recursos.add(recurso);
        this.cambioPeso(Global.CambioPeso(tipo));

    }


    public void gestionReproducción() throws Muerte {
        Individuo c1 = colonos.getElemento(0);
        Individuo c2 = colonos.getElemento(1);
        String tipo = "básico" ;
        if (c1.reproduce() && c2.reproduce()) {
            // if(c1.Type()==c2.Type()){
            /*if (c1.Type()==0)*/
            System.out.println("Ha nacido un colono" + tipo + "Con ID=" + id );
            básico i = new básico(id++, 10, turno, (c1.pClonacion + c2.pClonacion) / 2, (c1.pReproduccion + c2.pReproduccion) / 2, c1, c2);

            // }
            Global.addIndividuo(i);
            //id++;
        }
        while (this.colonos.numElementos()>3){
            Individuo minimo = this.colonos.getElemento(0); // No uso c1 por qué podria haber muerto ya.
            for(Object c3 : this.colonos.values()){
                Individuo c = (Individuo) c3;
                if (c.getVida()< minimo.getVida()) minimo = c;
            }
            minimo.morir();
        }
    }

    public void aplicarRecursos() throws Muerte {
        for (Object c2 : colonos.values()) {
            for (Object r2 : recursos.values()) {
                Individuo c1 = (Individuo) c2;
                c1.recurso((Recurso) r2);
            }
        }
    }

    public String getInfo(){
        if(this.colonos.isVacia()&& this.recursos.isVacia()) return "   ";
        String info ="";
        for (Object c2 : colonos.values()) {
            Individuo c1 = (Individuo) c2;
            info+=( "Individuo tipo"+ c1.Type()+"\n");
        }
        for (Object r2 : recursos.values()) {
            Recurso r1 = (Recurso) r2;
            info+=( "Recurso tipo"+ r1.getTipo()+"\n");
        }
        return info;
    }


    public int getPeso() {
        return peso;
    }
}
