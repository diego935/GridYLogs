package Listas.ListasSE;

public class ElementoSE<tipo> {
    protected tipo data;
    private ElementoSE<tipo> siguiente;

    public ElementoSE() {
        this.siguiente = null;
        this.data = null;
    }

    public ElementoSE(tipo data) {
        this.siguiente = null;
        this.data = data;
    }

    /*public ElementoSE(Object data, ElementoSE siguiente){
        this.siguiente = data;
        this.data = siguiente;
    }*/

    public tipo getData() {
        return this.data;
    }


    public ElementoSE<tipo> getSiguiente() {
        //Quizás tendría que ser protected para impedir la navegación libre de la lista por un tercero
        return this.siguiente;
    }

    public void setData(tipo data) {
        this.data = data;

    }

    public void setSiguiente(ElementoSE<tipo> siguiente) {
        this.siguiente = siguiente;
    }
    public boolean is(tipo dato){
        if (this.data == dato) return true;
        if (this.siguiente!=null) return this.siguiente.is(dato);
        return false;
    }

    @Override
    public String toString() {
        return "ElementoSE{" +
                "data=" + data +
                ", siguiente=" + siguiente +
                '}';
    }
}


