package Arboles;

import Listas.ListaDE.ListaDE;

public class Nodo<Tipo> {
    protected Tipo dato;
    Nodo<Tipo> izq = null;
    Nodo<Tipo> drc = null;


    public boolean isIzq() {
        return this.izq != null;
    }

    void setDato(Tipo dato) {
        //Necesario o al menos útil para borrar la raiz
        this.dato = dato;
    }

    public boolean isDrc() {
        return this.drc != null;
    }

    public Nodo(Tipo dato) {
        this.dato = dato;
        this.drc = null;
        this.izq = null;
    }

    public void add(Tipo dato) {

        Comparable dato1 = (Comparable) dato;
        Comparable dato2 = (Comparable) this.dato;

        if (dato1.compareTo(dato2) == 1) {
            if (isDrc()) {
                this.drc.add(dato);
            } else {
                this.drc = new Nodo<Tipo>(dato);
            }
        } else {
            if (isIzq()) {
                this.izq.add(dato);
            } else {
                this.izq = new Nodo<Tipo>(dato);
            }
        }
    }

    public Tipo getElemento() {
        return this.dato;
    }

    public Nodo<Tipo> getDrc() {
        return drc;
    }

    public Nodo<Tipo> getIzq() {
        return izq;
    }

    public int getGrado() {
        int grado = 0;
        if (izq != null)
            grado += 1;
        if (drc != null)
            grado += 1;
        return grado;
    }


    public ListaDE ordenCentral(Nodo<Tipo> n, ListaDE orden) {
        if (n.izq != null)
            ordenCentral(n.izq, orden);
        orden.add(n.dato);
        //       System.out.println(n.dato);
        if (n.drc != null)
            ordenCentral(n.drc, orden);
        return orden;
    }

    public ListaDE preOrden(Nodo<Tipo> n, ListaDE orden) {
        orden.add(n.dato);
        if (n.izq != null)
            preOrden(n.izq, orden);
        if (n.drc != null)
            preOrden(n.drc, orden);
        return orden;
    }


    public ListaDE postOrden(Nodo<Tipo> n, ListaDE orden) {
        if (n.izq != null)
            postOrden(n.izq, orden);
        if (n.drc != null)
            postOrden(n.drc, orden);
        orden.add(n.dato);
        return orden;

    }

    /*  public int getGrado(){
          int contador = 0;
          if (isIzq())
              contador +=1;
          if (isDrc())
              contador +=1;
          return contador;
      }
  */
    public ListaDE check(Tipo dato, ListaDE<Tipo> camino) {

        Comparable dato1 = (Comparable) dato;
        Comparable dato2 = (Comparable) this.dato;

        if (dato1.compareTo(dato2) == 1) {
            camino.add(this.dato);

            if (isDrc()) {
                camino = this.drc.check(dato, camino);
            } else {
                return camino;
            }
        } else if (dato1.compareTo(dato2) == -1) {
            camino.add(this.dato);

            if (isIzq()) {
                camino = this.izq.check(dato, camino);
            } else {
                return camino;
            }
        } else {
            camino.add(dato);

            return camino;
        }
        return camino;
    }

    public Nodo todoAIzq() {       //NodoPadreAizq a derechas es identico
        if (this.izq == null) return this;
        if (this.izq.izq == null) {
            return this;
        } else return this.izq.todoAIzq();

    }

    /*public Nodo padre(Tipo dato) {
        Comparable dato1 = (Comparable) dato;
        Comparable dato2 = (Comparable) this.dato;

        if (dato1.compareTo(dato2) == 1) {
            if (isDrc()) {
                Comparable dato3 = (Comparable) this.drc.getElemento();
                if (dato3.compareTo(dato1) == 0) return this;
                else this.drc.padre(dato);
            } else {
                return null;
            }
        } else {
            if (isIzq()) {
                Comparable dato3 = (Comparable) this.izq.getElemento();
                if (dato3.compareTo(dato1) == 0) return this;
                else this.izq.padre(dato);
            } else {
                return null;
            }
        }
        return null;
    }*/

   /* public void borrado(Tipo valor) {
        //En principio este nodo no debería borrar
        Nodo<Tipo> n;
        Comparable valor2 = (Comparable) valor;

        int lado = valor2.compareTo((Comparable) this.getElemento());
        if (lado < 0) {
            n = this.izq;
        } else {
            n = this.drc;
        }
        if (valor2.compareTo((Comparable) n.getElemento()) == 0) {
            if (n == this.izq) {
                this.izq = null;
            } else {
                this.drc = null;
            }
        } else {
            n.borrado(valor);
        }
    }*/


    public void borradoNodo(Tipo dato) {
        Comparable dato1 = (Comparable) dato;
        Comparable dato2 = (Comparable) this.dato;

        if (dato1.compareTo(dato2) == 1) {
            if (isDrc()) {
                Comparable dato3 = (Comparable) this.drc.getElemento();
                if (dato3.compareTo(dato1) == 0) {
                    if (this.drc.getGrado() == 0) this.drc = null;
                    else if (this.drc.getGrado() == 1) this.drc = (this.drc.izq == null) ? this.drc.drc : this.drc.izq;
                    else if (this.drc.getGrado() == 2) {
                        Nodo<Tipo> n = this.drc.drc.todoAIzq();
                        if (n.izq != null) {
                            Nodo nodoASustituir = n.izq;
                            this.drc.setDato((Tipo) nodoASustituir.getElemento());
                            n.izq = nodoASustituir.drc;

                        } else {
                            this.setDato(n.getElemento());
                            this.drc = n.drc;
                        }
                    }

                } else this.drc.borradoNodo(dato);
            }
        } else {
            if (isIzq()) {
                Comparable dato3 = (Comparable) this.izq.getElemento();
                if (dato3.compareTo(dato1) == 0) {
                    if (this.izq.getGrado() == 0) this.izq = null;
                    else if (this.izq.getGrado() == 1) this.izq = (this.izq.drc == null) ? this.izq.izq : this.izq.drc;
                    else if (this.izq.getGrado() == 2) {
                        Nodo<Tipo> n = this.izq.drc.todoAIzq();
                        if (n.izq != null) {
                            Nodo nodoASustituir = n.izq;
                            this.izq.setDato((Tipo) nodoASustituir.getElemento());
                            n.izq = nodoASustituir.drc;

                        } else {
                            this.izq.setDato(n.getElemento());
                            this.izq.drc = n.drc;
                        }
                    }

                } else this.izq.borradoNodo(dato);
            }
        }

    }


    protected int alturaNodo() {
        return this.alturaNodoR();
    }

    private int alturaNodoR() {
        int alturaIzq=0;
        int alturaDch=0;

        if(this.isIzq()) alturaIzq = this.izq.alturaNodoR();


        if(this.isDrc()) alturaDch = this.drc.alturaNodoR();


        return ((alturaIzq >= alturaDch) ? alturaIzq : alturaDch) + 1;

    }




/*
    public ListaDE desequilibrados(Nodo<Tipo> n, ListaDE desequilibrados) {
        desequilibrados.add(n.dato);
        if (n.izq != null)
            preOrden(n.izq, orden);
        if (n.drc != null)
            preOrden(n.drc, orden);
        return orden;

    }
}


*/


    /*private ListaDE<Nodo> isEquilibrado(Nodo n,ListaDE nDes){
        if(this.getGrado()==0) return nDes;

        if(this.getGrado()==1) {
            if ((Math.abs(n.izq.alturaNodo()-n.izq.alturaNodo()) < 2) && isEquilibrado((this.izq==null)?n.drc:n.izq)==false) nDes
        }

        boolean b = (Math.abs(n.izq.alturaNodo()-n.drc.alturaNodo()) < 2) && isEquilibrado(n.izq) && isEquilibrado(n.drc);
        return b;
    }*/



    /*Casilla{

        Accionrecursos(){
            for individuo in this{
                Recurso recusos = {Agua,Comida}

                for(int i =1;i<=6;i++){
                    if this.recusos[i]==1{
                        individuo.Recurso(i);
                    }
                }
            }
        }


    }
Individuo{

        Recurso(int i ){
            if (i ==1){
                //Es agua
                this.vida+=CambioVidaAgua;
            }
            if(i==6) this.muerte;
    }

}*/

}








