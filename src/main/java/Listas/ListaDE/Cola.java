package Listas.ListaDE;

public class Cola<Tipo> extends ListaDE<Tipo> {

    public Cola(){
        super();
    }
    public Tipo pop(){
        if(this.isVacia()) return null;
        ElementoDE<Tipo> dato = this.ultimo;
        int num = numElementos();
        if (num == 1) {
            this.primero = null;
            this.ultimo = null;
        }else {
            this.ultimo.getAnterior().setSiguiente(null);
            this.ultimo = this.ultimo.getAnterior();
        }
        return dato.getData();
    }
}
