package Arboles;


import ClasesProyecto.Individuos.Individuo;

public class ArbolGenialogico extends ArbolBinario<Individuo> {

    public ArbolGenialogico(Individuo hijo, Individuo padre, Individuo madre){
        super();
        this.add(hijo);
        this.root.izq = padre.familia.root;
        this.root.drc = madre.familia.root;
    }
    public ArbolGenialogico(Individuo hijo, Individuo padre){
        super();
        this.add(hijo);
        this.root.izq = padre.familia.root;
    }
    public ArbolGenialogico(Individuo hijo){
        super();
        this.add(hijo);
    }




}
