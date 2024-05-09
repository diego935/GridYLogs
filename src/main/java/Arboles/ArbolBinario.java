package Arboles;

import Listas.ListaDE.ListaDE;

public class ArbolBinario<Tipo> {
    Nodo<Tipo> root = null;

    public ArbolBinario() {
        this.root = null;
    }

    public Boolean isVacio() {
        return this.root == null;
    }

    public void add(Tipo dato) {
        if (isVacio()) {
            root = new Nodo<Tipo>(dato);
        } else {
            root.add(dato);
        }
    }

    public ListaDE ordenCentral() {
        ListaDE orden = new ListaDE<Tipo>();
        if (this.root != null)
            return (this.root.ordenCentral(this.root, orden));
        else return orden;
    }

    public ListaDE postOrden() {
        ListaDE orden = new ListaDE<Tipo>();
        if (this.root != null)
            return (this.root.postOrden(this.root, orden));
        else return orden;
    }

    public ListaDE preOrden() {
        ListaDE orden = new ListaDE<Tipo>();
        if (this.root != null)
            return (this.root.preOrden(this.root, orden));
        else
            return orden;
    }

    public ListaDE<Tipo> camino(Tipo num) {
        //Si el dato introducido no existe en el árbol devolvería  el camino a seguir pero sin devolver ese dato al final de la lista
        ListaDE camino = new ListaDE<Tipo>();
        if (isVacio()) {
        } else {
            camino = root.check(num, camino);
        }
        return camino;
    }



    public int getAltura() {
        int mayor = 0;
        ListaDE orden = ordenCentral();
        for (int i = 0; i < orden.numElementos(); i++) {
            int num = this.camino((Tipo) orden.recuperar(i)).numElementos();
            mayor = (num > mayor) ? num : mayor;
        }
        return mayor;
    }

    /*public ArbolBinario<Tipo> getSubArbolIzquierda() {
        ArbolBinario arbol = new ArbolBinario<Tipo>();
        if (this.root != null && this.root.getIzq() != null){
            ListaDE orden = this.root.getIzq().preOrden();
            for (int i = 0; i < orden.numElementos(); i++) {
                arbol.add(orden.recuperar(i));
            }
        }

    }*/

    public ArbolBinario<Tipo> getSubArbolIzquierda() {
        ArbolBinario a1 = new ArbolBinario<Tipo>();
        a1.root = this.root.getIzq();
        return a1;
    }

    public ArbolBinario<Tipo> getSubArbolDerecha() {
        ArbolBinario a1 = new ArbolBinario<Tipo>();
        a1.root = this.root.getDrc();
        return a1;
    }

    private ArbolBinario<Tipo> getSubArbolDerecha(Nodo nodo) {
        ArbolBinario a1 = new ArbolBinario<Tipo>();
        a1.root = nodo.getDrc();
        return a1;
    }

    private ArbolBinario<Tipo> getSubArbolIzquierda(Nodo nodo) {
        ArbolBinario a1 = new ArbolBinario<Tipo>();
        a1.root = nodo.getIzq();
        return a1;
    }

    public boolean isArbolHomogeneo() {
        if (this.root == null) {
            return true; // Árbol vacío es completo
        }
        //Esta lista sería mucho mejor cambiarla por una cola
        ListaDE<Nodo> cola = new ListaDE<>();
        cola.add(root);


        while (!cola.isVacia()) {
            Nodo nodoActual = cola.ultimo();
            cola.eliminar(cola.numElementos() - 1);

            // Verificar si el nodo actual es incompleto
            if (nodoActual.getIzq() == null && nodoActual.getDrc() != null) {
                return false;
            }

            if (nodoActual.getIzq() != null && nodoActual.getDrc() == null) {
                return false;
            }

            // Agregar los hijos a la cola
            if (nodoActual.getIzq() != null) {
                cola.add(nodoActual.getIzq());
            }
            if (nodoActual.getDrc() != null) {
                cola.add(nodoActual.getDrc());
            }
        }

        return true;
    }


    public ListaDE<Tipo> getListaDatosNivel(int k) {
        ListaDE<Tipo> elementos = new ListaDE<Tipo>();
        obtenerElementosEnAlturaK(this.root, k, 0, elementos);
        return elementos;
    }

    public ListaDE<Nodo> getListaNodosNivel(int k) {
        ListaDE<Nodo> elementos = new ListaDE<Nodo>();
        obtenerNodosEnAlturaK(this.root, k, 0, elementos);
        return elementos;
    }

    private void obtenerElementosEnAlturaK(Nodo nodo, int k, int alturaActual, ListaDE<Tipo> elementos) {
        if (nodo != null) {
            if (alturaActual == k) {
                elementos.add((Tipo) nodo.getElemento());
            }
            obtenerElementosEnAlturaK(nodo.getDrc(), k, alturaActual + 1, elementos);

            obtenerElementosEnAlturaK(nodo.getIzq(), k, alturaActual + 1, elementos);

        }
    }

    private void obtenerNodosEnAlturaK(Nodo nodo, int k, int alturaActual, ListaDE<Nodo> elementos) {
        if (nodo != null) {
            if (alturaActual == k) {
                elementos.add((Nodo) nodo);
            } else {
                obtenerNodosEnAlturaK(nodo.getIzq(), k, alturaActual + 1, elementos);

                obtenerNodosEnAlturaK(nodo.getDrc(), k, alturaActual + 1, elementos);
            }

        }
    }

    public boolean isArbolCompleto() {
        //Si el arbol es completo como en cada nivel se duplican el numero de nodos en el último deberá haber 2 (grado del arbol)^Ultimo nivel-1.
        return ((Math.floor(Math.pow(2, this.getAltura() - 1))) == (this.getListaDatosNivel(this.getAltura() - 1).numElementos()));
    }
    //Otra opcion de is completo
    /*public boolean isArbolCompleto() {
        //Si el arbol es completo como en cada nivel se duplican el numero de nodos en el último deberá haber 2 (grado del arbol)^Ultimo nivel-1.
        if (isVacio())
            return true;
        return ((Math.pow(2, this.getAltura() - 1)) == (this.getListaDatosNivel(this.getAltura() - 1).numElementos()));
    }*/

    public boolean isArbolCasiCompleto() {
        if (this.isArbolCompleto()) return false;
        ListaDE alturaAnterior = this.getListaNodosNivel(this.getAltura() - 2);

        //Comprueba que hasta la capa anterior sea completo
        if ((Math.floor(Math.pow(2, this.getAltura() - 2))) == alturaAnterior.numElementos()) {
            boolean busqueda = false;
            for (int i = 0; i < alturaAnterior.numElementos(); i++) {
                boolean iz = false;
                boolean dr = false;
                Nodo nodo = (Nodo) alturaAnterior.recuperar(i);

                if (!busqueda) {
                    if (nodo.isIzq())
                        iz = true;
                    if (nodo.isDrc())
                        dr = true;

                    if (iz && dr)
                        continue;
                    if (!iz && dr)
                        return false;
                    if (!dr)
                        busqueda = true;
                } else {
                    if (nodo.isIzq() || nodo.isDrc())
                        return false;
                }
            }
            return true;
        } else return false;
    }

    public boolean isArbolCasiCompleto2() {
        if (this.isArbolCompleto()) return false;
        ListaDE alturaAnterior = this.getListaDatosNivel(this.getAltura() - 2);

        //Comprueba que hasta la capa anterior sea completo
        if ((Math.floor(Math.pow(2, this.getAltura() - 2))) == alturaAnterior.numElementos()) {
            ListaDE alturaFinal = this.getListaDatosNivel(this.getAltura() - 1);

            Comparable dato1 = (Comparable) alturaFinal.ultimo();
            Comparable dato2 = (Comparable) alturaAnterior.recuperar(0);
            return dato1.compareTo(dato2) == -1;

        }
        return false;
    }

    public void del(Tipo elem) {
        //Entiendo que se borra un elemento y no una posición como en las listas
        Comparable el = (Comparable) elem;
        Comparable root2 = (Comparable) this.root.getElemento();
        int lado = el.compareTo(root2);
        if (lado == 0) borrarRaiz();
        else { root.borradoNodo(elem);
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

    }



    public void borrarRaiz() {
        if (this.root.getGrado() == 0) this.root = null;

        if (this.root.getGrado() == 1) this.root = (this.root.izq == null) ? this.root.drc : this.root.izq;


        if (this.root.getGrado() == 2) { //Por si no es binario ( Pero realmente no haría falta.
            Nodo<Tipo> n = this.root.drc.todoAIzq();
            if (n.izq != null) {
                Nodo nodoASustituir = n.izq;
                this.root.setDato((Tipo) nodoASustituir.getElemento());
                n.izq = nodoASustituir.drc;

            } else {
                this.root.setDato(n.getElemento());
                this.root.drc = n.drc;
            }
        }


    }
}
