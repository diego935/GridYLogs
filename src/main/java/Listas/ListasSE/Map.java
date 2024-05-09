package Listas.ListasSE;


public class Map<TipoId, Tipo> /*extends ListaSE<Tipo>*/{

    ElementoMap primero;
    public Map() {
        this.primero = null;
        //super();
    }


    //@Override
    public void add(Tipo dato, TipoId id) /*throws Exception*/ {
        if (isId(id)) {
            //Exception YaExiste = new Exception();
            //throw YaExiste;
            System.out.println("Ya existe un elemento con el ID:"+ id);
            return;
        }
//El orden no está definido, pero en caso de hacerlo habría que recorrerla al revés, si metes "1" y luego "2" el primer elemento por el orden natural será el "2".
        ElementoMap<TipoId, Tipo> elem = new ElementoMap<TipoId,Tipo>(dato, id);
        if (!isVacia())
            elem.setSiguiente(this.primero);
        this.primero = elem;

    }

    public boolean isId(TipoId id) {
        ElementoMap el = this.primero;
        while (el != null) {
            if (el.getId().equals(id)) return true;
            el = el.getSiguiente();
        }
        return false;
    }

    public Tipo getFromId(TipoId id) {
        ElementoMap<TipoId, Tipo> el = this.primero;
        while (el != null) {
            if (el.getId().equals(id)) return el.getDato();
            el = el.getSiguiente();
        }
        return null;
    }
    public boolean isVacia() {
        return this.primero == null;
    }
    public int numElementos() {
        if (isVacia())
            return 0;
        return 1 + this.primero.contarSiguiente();

    }

    public void eliminar(TipoId pos) {
        try {
            ElementoMap<TipoId, Tipo> elem = this.primero;
            if (elem.getId().equals(pos)) {
                this.primero = this.primero.getSiguiente();
            }else {
                //elem = elem.getSiguiente();
                while (true) {
                    //Si es moralmente correcto poner un while true se discute luego xd
                    if (pos .equals(elem.getSiguiente().getId())) {
                        elem.setSiguiente(elem.getSiguiente().getSiguiente());
                        return;
                    }
                    elem = elem.getSiguiente();
                }
            }
        } catch (Exception e ){
            System.out.println("Index out of bound:" + pos);
        }
    }

    public Object[] keys(){
        //Salen en orden inverso a como han sido añadidos
        int numElementos = this.numElementos();

        TipoId[] Ids;
        Ids = (TipoId[]) new Object[numElementos];
        ElementoMap<TipoId, Tipo> elem = this.primero;
        for (int i =0;i<numElementos;i++){
            Ids[i] = elem.getId();
            elem = elem.getSiguiente();
        }
        return Ids;
    }
    public Object[] values(){
        //Salen en orden inverso a como han sido añadidos
        int numElementos = this.numElementos();
        Tipo[] Ids;
        Ids = (Tipo[]) new Object[numElementos];
        ElementoMap<TipoId, Tipo> elem = this.primero;
        for (int i =0;i<numElementos;i++){
            Ids[i] = elem.getDato();
            elem = elem.getSiguiente();
        }
        return Ids;
    }
}
