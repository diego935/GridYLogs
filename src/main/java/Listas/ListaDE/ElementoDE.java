package Listas.ListaDE;

public class ElementoDE<tipo> {
    private tipo data;
    private ElementoDE<tipo> siguiente;
    private ElementoDE<tipo> anterior;


    public ElementoDE() {
        this.siguiente = null;
        this.anterior = null;
        this.data = null;
    }

    public ElementoDE (tipo data) {
        this.siguiente = null;
        this.anterior = null;
        this.data = data;
    }

    /*public ElementoDE(Object data, ElementoSE siguiente){
        this.siguiente = data;
        this.data = siguiente;
    }*/

    public tipo getData() {
        return this.data;
    }


    public ElementoDE<tipo> getSiguiente() {
        //Quizás tendría que ser protected para impedir la navegación libre de la lista por un tercero
        return this.siguiente;
    }

    public void setData(tipo data) {
        this.data = data;

    }

    public void setSiguiente(ElementoDE<tipo> siguiente) {
        this.siguiente = siguiente;
    }

    public void setAnterior(ElementoDE<tipo> anterior) {
        this.anterior = anterior;
    }

    public ElementoDE<tipo> getAnterior() {
        return anterior;
    }






    // Parte copiada del profe:
    public void addAfter(ElementoDE<tipo> en){

        en.siguiente = this.siguiente;
        en.anterior =this;
        this.siguiente.anterior = en;
        this.siguiente = en;
        if(this.siguiente!= null){
            en.siguiente.anterior = en;
        }
        this.siguiente = en;

    }
    public void addBefore(ElementoDE<tipo> en){

        en.anterior = this.anterior;
        en.siguiente =this;
        this.anterior.siguiente = en;
        this.anterior = en;
        if(this.anterior!= null){
            en.anterior.siguiente= en;
        }
        this.anterior = en;

    }

    public void del(){
    //No funciona si el elemento que queremos borrar es el unico de la lista, en ese caso la lista tendrá que mirar si el primero es el mismo que el final y en ese caso poner ambos a null

        if (this.siguiente != null)
            this.siguiente.anterior = this.anterior;
        if (this.anterior != null)
            this.anterior.siguiente = this.siguiente;

    }



}

