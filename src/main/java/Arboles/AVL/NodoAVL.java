package Arboles.AVL;

import Listas.ListaDE.ListaDE;

public class NodoAVL<Tipo> {
    Tipo dato;
    protected NodoAVL<Tipo> izq = null;
    protected NodoAVL<Tipo> drc = null;

    //int eq; // marca si está equilibrado o en caso contrario hacía que lado
    public NodoAVL(Tipo dato) {
        this.dato = dato;
        //this.eq=0;
    }

    NodoAVL() {
        this.dato = null;
    }

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

    public Tipo getElemento() {
        return this.dato;
    }

    public NodoAVL<Tipo> getDrc() {
        return this.drc;
    }

    public NodoAVL<Tipo> getIzq() {
        return this.izq;
    }

    public int getGrado() {
        int grado = 0;
        if (izq != null)
            grado += 1;
        if (drc != null)
            grado += 1;
        return grado;
    }

    public ListaDE check(Tipo dato, ListaDE<Tipo> camino) {

        Comparable dato1 = (Comparable) dato;
        Comparable dato2 = (Comparable) this.dato;

        if (dato1.compareTo(dato2) == 1) {
            if (isDrc()) {
                camino.add(this.dato);
                camino = this.drc.check(dato, camino);
            } else {
                return camino;
            }
        } else if (dato1.compareTo(dato2) == -1) {

            if (isIzq()) {
                camino.add(this.dato);
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

    public NodoAVL todoAIzq() {       //NodoPadreAizq a derechas es identico
        if (this.izq == null) return this;
        if (this.izq.izq == null) {
            return this;
        } else return this.izq.todoAIzq();

    }
    /*public void borradoNodo(Tipo dato) {
        Comparable dato1 = (Comparable) dato;
        Comparable dato2 = (Comparable) this.dato;

        if (dato1.compareTo(dato2) == 1) {
            if (isDrc()) {
                Comparable dato3 = (Comparable) this.drc.getElemento();
                if (dato3.compareTo(dato1) == 0) {
                    if (this.drc.getGrado() == 0) this.drc = null;
                    else if (this.drc.getGrado() == 1) this.drc = (this.drc.izq == null) ? this.drc.drc : this.drc.izq;
                    else if (this.drc.getGrado() == 2) {
                        NodoAVL<Tipo> n = this.drc.drc.todoAIzq();
                        if (n.izq != null) {
                            NodoAVL<Tipo> nodoASustituir = n.izq;
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
                        NodoAVL<Tipo> n = this.izq.drc.todoAIzq();
                        if (n.izq != null) {
                            NodoAVL nodoASustituir = n.izq;
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
*/

    protected int alturaNodo() {
        return this.alturaNodoR();
    }

    private int alturaNodoR() {
        int alturaIzq = 0;
        if (this.isIzq()) alturaIzq = this.izq.alturaNodoR();
        int alturaDch = 0;
        if (this.isDrc()) alturaDch = this.drc.alturaNodoR();
        return ((alturaIzq >= alturaDch) ? alturaIzq : alturaDch) + 1;
    }


    public ListaDE ordenCentral(NodoAVL<Tipo> n, ListaDE orden) {
        if (n.izq != null)
            ordenCentral(n.izq, orden);
        orden.add(n.dato);
        //       System.out.println(n.dato);
        if (n.drc != null)
            ordenCentral(n.drc, orden);
        return orden;
    }

    public ListaDE preOrden(NodoAVL<Tipo> n, ListaDE orden) {
        orden.add(n.dato);
        if (n.izq != null)
            preOrden(n.izq, orden);
        if (n.drc != null)
            preOrden(n.drc, orden);
        return orden;
    }


    public ListaDE postOrden(NodoAVL<Tipo> n, ListaDE orden) {
        if (n.izq != null)
            postOrden(n.izq, orden);
        if (n.drc != null)
            postOrden(n.drc, orden);
        orden.add(n.dato);
        return orden;

    }

    /*void rotarDcha(NodoAVL n, boolean doble) {
        //if (doble) rotoHijo();

        NodoAVL<Tipo> temporal = new NodoAVL();
        temporal.copiaLigera(n);

        //Solo uso ya el temporal

        NodoAVL<Tipo> nuevaRaiz = temporal.drc;
        temporal.drc = nuevaRaiz.izq;
        nuevaRaiz.izq = temporal;
        // Copio todo el árbol modificado sobre a (n)
        n.copiaLigera(nuevaRaiz);
    }*/

    void rotarDcha(boolean doble) {
        if (doble) this.drc.rotarIzq(false);

        NodoAVL<Tipo> temporal = new NodoAVL();
        temporal.copiaLigera(this);

        //Solo uso ya el temporal

        NodoAVL<Tipo> nuevaRaiz = temporal.drc;
        temporal.drc = nuevaRaiz.izq;
        nuevaRaiz.izq = temporal;
        // Copio todo el árbol modificado sobre a (n)
        this.copiaLigera(nuevaRaiz);
    }

    /*void rotarIzq(NodoAVL n, boolean doble) {
        //if (doble) rotoHijo();

        NodoAVL<Tipo> temporal = new NodoAVL();
        temporal.copiaLigera(n);

        //Solo uso ya el temporal

        NodoAVL<Tipo> nuevaRaiz = temporal.izq;
        temporal.izq = nuevaRaiz.drc;
        nuevaRaiz.drc = temporal;
        // Copio todo el árbol modificado sobre a (n)
        n.copiaLigera(nuevaRaiz);
    }*/

    void rotarIzq(boolean doble) {
        if (doble) this.izq.rotarDcha(false);

        NodoAVL<Tipo> temporal = new NodoAVL();
        temporal.copiaLigera(this);

        //Solo uso ya el temporal

        NodoAVL<Tipo> nuevaRaiz = temporal.izq;
        temporal.izq = nuevaRaiz.drc;
        nuevaRaiz.drc = temporal;
        // Copio todo el árbol modificado sobre a (n)
        this.copiaLigera(nuevaRaiz);
    }

    int isEquilibradoNodo() {
        if (this == null) return 0;
        int a = 0;
        int b = 0;
        if (this.isIzq()) a = this.izq.alturaNodo();

        if (this.isDrc()) b = this.drc.alturaNodo();

        if (Math.abs(a - b) < 2) return 0;
        return (a > b) ? -1 : 1;
    }

    /*public ListaDE desequilibrados(/*NodoAVL n, Prefiriría una pila*-/ListaDE desequilibrados) {
        //Esto deberia dar la lista de todos los nodos que están desequilibrados pero sus hijos no, que entiendo que habrá que ir modificando estos en cada pasada

        if (!this.isEquilibradoNodo() && this.izq.isEquilibradoNodo() && this.drc.isEquilibradoNodo())
            desequilibrados.add(this);
        else {
            this.izq.desequilibrados(desequilibrados);
            this.drc.desequilibrados(desequilibrados);
        }
        return desequilibrados;
    }

*/
    private boolean isEquilibradoArbol(NodoAVL n) {
        if (this.getGrado() == 0) return true;

        if (this.getGrado() == 1)
            return (Math.abs(n.izq.alturaNodo() - n.izq.alturaNodo()) < 2) && isEquilibradoArbol((this.izq == null) ? n.drc : n.izq);

        boolean b = (Math.abs(n.izq.alturaNodo() - n.drc.alturaNodo()) < 2) && isEquilibradoArbol(n.izq) && isEquilibradoArbol(n.drc);
        return b;
    }

    public void copiaLigera(NodoAVL n) {
        this.izq = n.izq;
        this.drc = n.drc;
        this.dato = (Tipo) n.dato;
    }


    public void add(Tipo dato) {
        Comparable dato1 = (Comparable) dato;
        Comparable dato2 = (Comparable) this.dato;

        if (dato1.compareTo(dato2) == 1) {
            if (this.isDrc()) {
                this.drc.add(dato);
            } else {
                this.drc = new NodoAVL<Tipo>(dato);
            }
        } else {
            if (this.isIzq()) {
                this.izq.add(dato);
            } else {
                this.izq = new NodoAVL<Tipo>(dato);
            }
        }
        int lado = this.isEquilibradoNodo();
        if (lado == 0) return;
        else if (lado == 1) this.rotarDcha(this.drc.drc == null);
        else this.rotarIzq(this.izq.izq == null);
    }


    public ListaDE checkNodo(Tipo dato, ListaDE<NodoAVL<Tipo>> camino) {

        Comparable dato1 = (Comparable) dato;
        Comparable dato2 = (Comparable) this.dato;

        if (dato1.compareTo(dato2) == 1) {
            if (isDrc()) {
                camino.add(this);
                camino = this.drc.checkNodo(dato, camino);
            } else {
                return camino;
            }
        } else if (dato1.compareTo(dato2) == -1) {

            if (isIzq()) {
                camino.add(this);
                camino = this.izq.checkNodo(dato, camino);
            } else {
                return camino;
            }
            return camino;
        }


        return camino;
    }

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
                        NodoAVL<Tipo> n = this.drc.drc.todoAIzq();
                        if (n.izq != null) {
                            NodoAVL<Tipo> nodoASustituir = n.izq;
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
                        NodoAVL<Tipo> n = this.izq.drc.todoAIzq();
                        if (n.izq != null) {
                            NodoAVL<Tipo> nodoASustituir = n.izq;
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
        int lado = this.isEquilibradoNodo();
        if (lado == 0) return;
        else if (lado == 1) this.rotarDcha(this.drc.drc == null);
        else this.rotarIzq(this.izq.izq == null);
    }

}