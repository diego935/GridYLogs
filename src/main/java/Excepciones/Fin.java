package Excepciones;

public class Fin extends Exception{
    public Fin(){
        super();
        System.out.println("Se ha acabado la simulación, se extingue la humanidad");
    }
}
