package Grafos;

import Listas.ListaDE.Cola;
import Listas.ListasSE.ListaSE;
import Listas.ListasSE.Map;


public class Grafo<T> {
    private ListaSE<Vertice<T>> vertices= new ListaSE<Vertice<T>>();
    private ListaSE<Arista> aristas = new ListaSE<Arista>();


    public Grafo(){
        this.aristas = new ListaSE<Arista>();
        this.vertices = new ListaSE<Vertice<T>>();

    }
    public void addVertice(Vertice<T> v){
        if(this.vertices!= null) {
        if (vertices.is(v)) {
            System.out.println("El vértice ya pertenece al grafo");
        return;
    }
}
        this.vertices.add(v);
    }
    public void addArista(Arista<T> a){
        if(this.vertices.is(a.getOrigen())&&this.vertices.is(a.getDestino())&&!this.aristas.is(a)){
            //Si está el origen, el destino y no está ya incluida la arista

            if(a.getDestino().isEntrada(a.getOrigen()) ||a.getOrigen().isSalida(a.getDestino())){
                System.out.println("Ya existe una conexion entre esos 2 vértices");
                return;
            }
            this.aristas.add(a);
            a.getOrigen().addAristaSalida(a);
            a.getDestino().addAristaEntrada(a);

        }else System.out.println("La arista no es válida");
    }

    public void delArista(Arista<T> a){
        if (this.aristas.is(a)&& a.getOrigen().isSalida(a.getDestino())&& a.getDestino().isEntrada(a.getOrigen())){
            this.aristas.delDato(a);
            a.getOrigen().delAristaSalida(a);
            a.getDestino().delAristaEntrada(a);
        }
    }

    public ListaSE<Vertice<T>> getVertices() {
        return vertices;
    }

    public ListaSE<Arista> getAristas() {
        return aristas;
    }
    public String printCodigoGrafo() {
        //https://dreampuf.github.io/GraphvizOnline/
        //Aquí pued verse
        char com = '"'; // No puedes poner las comillas sin  cerrar el texto si no xd
        String codigo = "digraph regexp {\nfontname=" + com + "Helvetica,Arial,sans-serif" + com + "\nnode [fontname=" + com + "Helvetica,Arial,sans-serif" + com + "]\nedge [fontname=" + com + "Helvetica,Arial,sans-serif" + com + "]";

        for (Object v2 : vertices.values()){
            Vertice<T> v = (Vertice) v2;
            codigo += "\nn" + v.getDato() + " [label=" + com + v.getDato() + com + "];";
        }

        for(Object a2 : aristas.values()) {
            Arista a = (Arista) a2;
            codigo += "\nn" + a.getOrigen().getDato() + " -> n" + a.getDestino().getDato() + " [label=" + com + a.getPeso() + com + "];";

        }
        codigo += "\n}";
        return codigo;
    }

    public void delVertice(Vertice<T> v){


        for(Object a :v.getEntradas()){
            this.delArista((Arista<T>) a);
        }

        for(Object a :v.getSalidas()){
            this.delArista((Arista<T>) a);
        }
        this.vertices.delDato(v);
    }
    public Map<Vertice<T>, Camino<T>> dijkstra(Vertice<T> origen) {

        //Preparamos las variables.
        Map<Vertice<T>, Double> distancias = new Map();   // Mantiene las distancias mínimas conseguidas a cada vértice.
        Cola<Vertice<T>> colaPendientes = new Cola();  // Mantiene cuáles son los siguientes vértices a calcular.
        Map<Vertice<T>, Vertice<T>> verticesAnteriores = new Map(); //Guarda el rastro del camino para recalcularlo después.

        this.dijkstra_init(origen,distancias,colaPendientes,verticesAnteriores);  //Inicialización
        this.dijkstra_calcula(distancias,colaPendientes,verticesAnteriores);      //Cálculo
        return this.dijkstra_procesaResultados(distancias,verticesAnteriores);    //Procesamiento de resultados
    }

    protected void dijkstra_init(Vertice<T> origen, Map<Vertice<T>, Double> distancias, Cola<Vertice<T>> colaPendientes, Map<Vertice<T>, Vertice<T>> verticesAnteriores){

        distancias.add(0.0, origen);
        colaPendientes.add(origen);

        for (Object vertice2 : this.vertices.values()) {
            Vertice<T> vertice = (Vertice<T>) vertice2;
            distancias.add(Double.MAX_VALUE, vertice);
        }
    }

    /**
     * Realiza el cálculo de dijkstra, calculando tanto las distancias como los anteriores vértices que llegan a uno determinado.
     * @param distancias
     * @param colaPendientes
     * @param verticesAnteriores
     */
    protected void dijkstra_calcula(Map<Vertice<T>, Double> distancias, Cola<Vertice<T>> colaPendientes, Map<Vertice<T>, Vertice<T>> verticesAnteriores){
        while (!colaPendientes.isVacia()) {
            Vertice<T> verticeActual = colaPendientes.pop();  // Sacamos un vértice de la cola

            for (Object arco2 : verticeActual.getSalidas()) {   // Exploramos los arcos de salida del vértice.
                Arista arco = (Arista) arco2;
                Vertice<T> verticeVecino = arco.getDestino();       // Para cada vértice conectado a la salida del arco...
                double nuevoCalculoDeDistancia = distancias.getFromId(verticeActual) + arco.getPeso();  // Calculamos su distancia basándonos en lo que nos ha costado llegar al actual. Primer elemento: Coste 0.

                if (nuevoCalculoDeDistancia < distancias.getFromId(verticeVecino)) {  // Si resulta que la nueva distancia es mejor que la que se había calculado antes a ese vértice, sustituimos los valores por los nuevos.
                    distancias.eliminar(verticeVecino);
                    distancias.add(nuevoCalculoDeDistancia, verticeVecino);  // Guardamos la nueva distancia.
                    if(verticesAnteriores.isId(verticeVecino)) verticesAnteriores.eliminar(verticeVecino);
                    verticesAnteriores.add(verticeActual, verticeVecino);    // Guardamos el nuevo vértice anterior
                    colaPendientes.add(verticeVecino);                       // Añadimos el nuevo vértice a la cola de procesamiento, para en el futuro explorar sus salidas....
                }
            }
        }
    }

    /**
     * Cuando el cálculo ya ha acabado, toda la información queda en las variables por parámetros, pero hay que
     * procesar los resultados para generar algo que sea manejable.
     * El objetivo es generar un mapa o lista en la cuál para cada vértice se guarde el camino desde el origen hasta él
     * y el coste de ese camino completo.
     *
     * @param distancias
     * @param verticesAnteriores
     * @return
     */
    protected Map<Vertice<T>,Camino<T>> dijkstra_procesaResultados(Map<Vertice<T>, Double> distancias, Map<Vertice<T>, Vertice<T>> verticesAnteriores){
        Map<Vertice<T>,Camino<T> > caminos = new Map<>();

        for (Object verticeDestino2 : verticesAnteriores.keys()) {             // De todos los vértices calculados
            Vertice<T> verticeDestino = (Vertice) verticeDestino2;
            ListaSE<Vertice<T>> caminoCalculado = new ListaSE();                   // prepara un camino para cada uno
            Vertice<T> v = verticeDestino;                                          // y en un bucle recorre el camino
            while (v != null) {                                                     // hacia atrás.
                caminoCalculado.add(v);
                v = verticesAnteriores.getFromId(v); //El bucle es sobre v, o sea, los vértices: actualizo hasta que no tenga un origen (primero)
            }
            //Collections.reverse(caminoCalculado);  //Le damos la vuelta, para que el camino empiece en el origen, no en el último.

            Camino<T> camino = new Camino(caminoCalculado, distancias.getFromId(verticeDestino));
            caminos.add(camino,verticeDestino);
        }
        return caminos;
    }

}
