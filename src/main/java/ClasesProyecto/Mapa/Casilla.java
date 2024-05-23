package ClasesProyecto.Mapa;

import ClasesProyecto.Global;
import ClasesProyecto.Individuos.Avanzado;
import ClasesProyecto.Individuos.Individuo;
import ClasesProyecto.Individuos.Normal;
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
        boolean b1 = c1.reproduce();
        boolean b2 = c2.reproduce();

        if (b1 && b2 ){
            Reproduce(c1,c2);
        } else if (colonos.numElementos()==3 &&(b1||b2)) {
            if (colonos.getElemento(2).reproduce()){
                if(b1) Reproduce(c1,colonos.getElemento(2));
            } else Reproduce(c2,colonos.getElemento(2));
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





    public void Reproduce(Individuo i1, Individuo i2){
        int type;
        if (i1.Type() == i2.Type()){
            type= i1.Type();
        }
        else {
            if (Math.random() <= porcentajeMejora){
                type =Math.max(i1.Type(),i2.Type());
            }else {
                type = Math.min(i1.Type(),i2.Type());
            }
        }
        System.out.println("Ha nacido un colono " + type + " Con ID=" + id );
        //System.out.println(this.pos[0]+","+this.pos[1]+" De la casilla");
        if (type ==0){
            básico i = new básico(id++, 10, turno, (i1.pClonacion + i2.pClonacion) / 2, (i1.pReproduccion + i2.pReproduccion) / 2, i1, i2);
            i.setPos(this.pos);
            Global.addIndividuo(i);
        } else if (type==1) {
            //System.out.println("Ha nacido un colono " + type + " Con ID=" + id );
            Normal i = new Normal(id++, 10, turno, (i1.pClonacion + i2.pClonacion) / 2, (i1.pReproduccion + i2.pReproduccion) / 2, i1, i2);
            i.setPos(this.pos);
            Global.addIndividuo(i);
        }else {
            //System.out.println("Ha nacido un colono " + type + " Con ID=" + id );
            Avanzado i = new Avanzado(id++, 10, turno, (i1.pClonacion + i2.pClonacion) / 2, (i1.pReproduccion + i2.pReproduccion) / 2, i1, i2);
            i.setPos(this.pos);
            Global.addIndividuo(i);
        }
    }
    public void clona(Individuo individuo) throws Muerte{
        int type = individuo.Type();
        System.out.println(this.pos[0]+","+this.pos[1]);
        if (type ==0){
            System.out.println("Ha sido clonado un colono " + type + " Con ID=" + id );
            básico i = new básico(id++, turno,individuo);
            i.setPos(this.pos);
            Global.addIndividuo(i);
        } else if (type==1) {
            System.out.println("Ha sido clonado un colono " + type + " Con ID=" + id );
            Normal i = new Normal(id++, turno, individuo);
            i.setPos(this.pos);
            Global.addIndividuo(i);
        }else {
            System.out.println("Ha sido clonado un colono " + type + " Con ID=" + id );
            Avanzado i = new Avanzado(id++, turno, individuo);
            i.setPos(this.pos);
            Global.addIndividuo(i);
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
