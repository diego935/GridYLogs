package Arboles.AVL;

import Arboles.ArbolBinario;
import Listas.ListaDE.ListaDE;

public class ArbolAVL<Tipo> extends ArbolBinario<Tipo> {
    NodoAVL root;

    @Override
    public Boolean isVacio() {
        return this.root == null;
    }

    @Override
    public void add(Tipo dato) {
        if (isVacio()) {
            root = new NodoAVL<Tipo>(dato);
        } else {
            root.add(dato);
            int lado = this.root.isEquilibradoNodo();
            if (lado == 0) return;
            else if (lado == 1) this.root.rotarDcha(this.root.drc.drc == null);
            else this.root.rotarIzq(this.root.izq.izq == null);
        }
        //Los nodos a confirmar que sigan balanceados son por los que se ha pasado al añadir un elemento


    }


    public ListaDE<NodoAVL> caminoNodo(Tipo num) {
        //Si el dato introducido no existe en el árbol devolvería  el camino a seguir pero sin devolver ese dato al final de la lista
        ListaDE camino = new ListaDE<NodoAVL>();
        if (isVacio()) {
        } else {
            camino = root.checkNodo(num, camino);
        }
        return camino;
    }


    public void del(Tipo elem) {
        //Entiendo que se borra un elemento y no una posición como en las listas
        Comparable el = (Comparable) elem;
        Comparable root2 = (Comparable) this.root.getElemento();
        int lado = el.compareTo(root2);
        if (lado == 0) borrarRaiz();
        else {
            root.borradoNodo(elem);
            /*
            Nodo padre = this.root.padre(elem);
            if (padre == null) {
                System.out.println("No existe el elemento a borrar");
                return;
            }

            if (el.compareTo((Comparable) padre.drc.getElemento()) == 1) getSubArbolDerecha(padre).borrarRaiz();
            else getSubArbolIzquierda(padre).borrarRaiz();
        */
        }
        if (this.root != null) {
            int ladoB = this.root.isEquilibradoNodo();
            if (ladoB == 0) return;
            else if (ladoB == 1) this.root.rotarDcha(this.root.drc.drc == null);
            else this.root.rotarIzq(this.root.izq.izq == null);
        }
    }

    public void borrarRaiz() {
        if (this.root.getGrado() == 0) this.root = null;

        if (this.root.getGrado() == 1) this.root = (this.root.izq == null) ? this.root.drc : this.root.izq;


        if (this.root.getGrado() == 2) { //Por si no es binario ( Pero realmente no haría falta.
            NodoAVL<Tipo> n = this.root.drc.todoAIzq();
            if (n.izq != null) {
                NodoAVL<Tipo> nodoASustituir = n.izq;
                this.root.setDato((Tipo) nodoASustituir.getElemento());
                n.izq = nodoASustituir.drc;

            } else {
                this.root.setDato(n.getElemento());
                this.root.drc = n.drc;
            }
        }


    }


}
