package Listas.ListaDE;

public class ListaDE<tipo> {
    protected ElementoDE<tipo> primero;
    protected ElementoDE<tipo> ultimo;

    public ListaDE() {
        this.primero = null;
        this.ultimo = null;
    }
    /*public int buscar(dato){
        for (int i =0;;i++)
    }*/

    public boolean isVacia() {
        return this.primero == null;
    }

    public void add(tipo dato) {
        //El orden no está definido, pero en caso de hacerlo habría que recorrerla al revés, si metes "1" y luego "2" el primer elemento por el orden natural será el "2".
        ElementoDE<tipo> elem = new ElementoDE<tipo>(dato);
        if (!isVacia()) {
            elem.setSiguiente(this.primero);
            this.primero.setAnterior(elem);
        } else {
            this.ultimo = elem;
        }

        this.primero = elem;

    }

    public void insert(tipo dato, int pos) {
        ElementoDE<tipo> elem = new ElementoDE<tipo>(dato);

        if (pos <= numElementos() && pos >= 0) {
            if (pos == 0) {
                this.ultimo.setSiguiente(elem);
                elem.setAnterior(this.ultimo);
                this.ultimo = elem;
            } else if (pos == numElementos()) {
                this.primero.setAnterior(elem);
                elem.setSiguiente(this.primero);
                this.primero = elem;
            } else {
                ElementoDE<tipo> posicion = this.ultimo;
                for (int i = 0; i < pos - 1; i++) {
                    posicion = posicion.getAnterior();

                }
                posicion.getAnterior().setSiguiente(elem);
                elem.setAnterior(posicion.getAnterior());
                elem.setSiguiente(posicion);
                posicion.setAnterior(elem);
            }
        } else System.out.println("Índice fuera de rango");
    }

    public int numElementos() {
        if (isVacia())
            return 0;
        return 1 + contarSiguiente(this.primero);

    }

    private int contarSiguiente(ElementoDE<tipo> elem) {
        if (elem.getSiguiente() == null) return 0;

        return 1 + contarSiguiente(elem.getSiguiente());
    }

    public tipo recuperar(int pos) {
        ElementoDE<tipo> elem = this.ultimo;
        for (int i = 0; i < pos; i++)
            elem = elem.getAnterior();
        return elem.getData();
    }

    public void eliminar(int pos) {
        if (!isVacia()) {
            if (pos < numElementos()) {
                int num = numElementos();
                if (num == 1) {
                    this.primero = null;
                    this.ultimo = null;
                } else {
                    if (pos == 0) {
                        this.primero.getSiguiente().setAnterior(null);
                        this.primero = this.primero.getSiguiente();

                    }
                    if (pos == num - 1) {
                        this.ultimo.getAnterior().setSiguiente(null);
                        this.ultimo = this.ultimo.getAnterior();

                    }
                    if (pos != 0 && pos != num - 1) {
                        ElementoDE<tipo> elem = this.primero;
                        for (int i = pos; i <= pos; i++) {
                            elem = elem.getSiguiente();
                        }
                        elem.getSiguiente().setAnterior(elem.getAnterior());
                        elem.getAnterior().setSiguiente(elem.getSiguiente());
                    }
                }
            } else System.out.println("No existe esa posición");
        }
    }

    public void imprimirLista() {
        if (this.ultimo == null) System.out.println("[]");
        else {
            ElementoDE<tipo> elem = this.ultimo;
            do {
                System.out.print(elem.getData() + ", ");
                elem = elem.getAnterior();
            } while (elem != null);
            System.out.println("");

        }
    }

    public void imprimirListaInverso() {
        if (this.primero == null) System.out.println("[]");
        else {
            ElementoDE<tipo> elem = this.primero;
            do {
                System.out.print(elem.getData() + ", ");
                elem = elem.getSiguiente();
            } while (elem != null);
            System.out.println("");

        }
    }

    public tipo ultimo() {
        return this.primero.getData();
    }

    public void vaciar() {
        this.primero = null;
        this.ultimo = null;
    }

    @Override
    public String toString() {
        String print = "[";
        if (this.ultimo == null) return ("[]");
        else {
            ElementoDE<tipo> elem = this.ultimo;
            do {
                if (elem.getAnterior() != null)
                    print += (elem.getData() + ", ");
                else print += elem.getData();
                elem = elem.getAnterior();
            } while (elem != null);
            print += "]";
            return print;
//            System.out.println("");

        }

    }

    public void del(int pos) {
        if (this.primero == this.ultimo && pos == 0) {
            this.primero = null;
            this.ultimo = null;
        } else if (pos == 0) {
            this.ultimo = this.ultimo.getAnterior();
        } else {
            ElementoDE<tipo> elem = this.ultimo;
            for (int i = 0; i < pos; i++)
                elem = elem.getAnterior();
            elem.del();
        }

    }
    public void delDato(tipo dato) {
        if (this.primero == this.ultimo && this.primero.getData().equals(dato)) {
            this.primero = null;
            this.ultimo = null;
        } else if (this.ultimo.getData().equals(dato)) {
            this.ultimo = this.ultimo.getAnterior();
        } else {
            ElementoDE<tipo> elem = this.ultimo.getAnterior();
            while (!elem.getData().equals(dato)){
                try {
                    elem = elem.getAnterior();
                } catch (Exception e){
                    System.out.println("No existe tal elemento." + dato);
                    return;
                }
        }
            elem.del();
        }

    }



}
