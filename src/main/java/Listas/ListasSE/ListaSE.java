package Listas.ListasSE;

public class ListaSE<tipo> {
    protected ElementoSE<tipo> primero;

    public ListaSE() {
        this.primero = null;
    }

    public boolean isVacia() {
        return this.primero == null;
    }

    public boolean is(tipo dato){
        if (this.primero==null) return false;
        return this.primero.is(dato);
    }
    public void add(tipo dato) {
        //El orden no está definido, pero en caso de hacerlo habría que recorrerla al revés, si metes "1" y luego "2" el primer elemento por el orden natural será el "2".
        ElementoSE<tipo> elem = new ElementoSE<>(dato);
        if (!isVacia())
            elem.setSiguiente(this.primero);
        this.primero = elem;

    }
  /*  public void add(tipo dato){
        Nodo<tipo> nuevoNodo=new Nodo<tipo>(dato);
        if(this.primero ==null){
            this.primero=nuevoNodo;
        }
    }*/

    public int numElementos() {
        if (isVacia())
            return 0;
        return 1 + contarSiguiente(this.primero);

    }

    private int contarSiguiente(ElementoSE<tipo> elem) {
        if (elem.getSiguiente() == null) return 0;

        return 1 + contarSiguiente(elem.getSiguiente());
    }

    public void eliminar(int pos) {
        if (pos < numElementos()) {
            ElementoSE<tipo> elem = this.primero;
            for (int i = pos; i < pos; i++) {
                elem = elem.getSiguiente();
            }
            elem.setSiguiente(elem.getSiguiente().getSiguiente());
        }
    }
    public void delDato(tipo dato) {
       if(this.primero.getData().equals((dato))) this.primero = this.primero.getSiguiente();
       else{
           ElementoSE<tipo> elem = this.primero;
        while (!elem.getSiguiente().getData().equals(dato)){
            try {
                elem = elem.getSiguiente();
            } catch (Exception e){
                System.out.println("No existe tal elemento." + dato);
                return;
            }
        }

        elem.setSiguiente(elem.getSiguiente().getSiguiente());
    }

    }

    public void imprimirLista() {
        if (this.primero == null) System.out.println("[]");
        ElementoSE<tipo> elem = this.primero;
        do {
            System.out.print(elem.getData()+", ");
            elem = elem.getSiguiente();
        } while (elem != null);

    }

    public tipo getElemento(int pos){
        ElementoSE<tipo> elem = this.primero;
        for (int i = 0;i< pos ;i++)
            elem= elem.getSiguiente();
        return elem.getData();
    }

    @Override
    public String toString() {
        String print = "";
        if (this.primero == null) return "[]";
        ElementoSE<tipo> elem = this.primero;
        do {

            print += (elem.getData()+", ");
            elem = elem.getSiguiente();
        } while (elem != null);

    return print;
    }

    public Object[] values(){
        //Salen en orden inverso a como han sido añadidos
        int numElementos = this.numElementos();
        tipo[] datos;
        datos = (tipo[]) new Object[numElementos];
        ElementoSE<tipo> elem = this.primero;
        for (int i =0;i<numElementos;i++){
            datos[i] = elem.getData();
            elem = elem.getSiguiente();
        }
        return datos;
    }
}
