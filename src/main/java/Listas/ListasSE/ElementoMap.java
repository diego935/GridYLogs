package Listas.ListasSE;

public class ElementoMap<TipoId, Tipo> /*extends ElementoSE<Tipo>*/ {
    private TipoId id;
    private Tipo dato = null;
    private ElementoMap<TipoId, Tipo> siguiente;

    public ElementoMap(Tipo dato, TipoId id) {

        this.id = id;
        this.dato = dato;


    }

    public ElementoMap(TipoId id) {
        super();
        this.id = id;
    }

    public TipoId getId() {
        return this.id;
    }
    public Tipo getDato() {
        return this.dato;
    }



    public ElementoMap<TipoId, Tipo> getSiguiente() {
        //Quizás tendría que ser protected para impedir la navegación libre de la lista por un tercero
        return this.siguiente;
    }


    public void setSiguiente(ElementoMap<TipoId, Tipo> siguiente) {
        this.siguiente = siguiente;
    }



    int contarSiguiente() {
        if (this.getSiguiente() == null) return 0;

        return 1 + this.getSiguiente().contarSiguiente();
    }
}
