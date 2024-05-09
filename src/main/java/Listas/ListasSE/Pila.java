package Listas.ListasSE;

public class Pila<Tipo> extends ListaSE<Tipo> {
    public Tipo pop(){
        try {
            Tipo retorno = this.primero.getData();
            this.primero = this.primero.getSiguiente();
            return retorno;

        }catch (Exception e){
            System.out.println("Index out of bound");
        }
        return null;
    }
}
