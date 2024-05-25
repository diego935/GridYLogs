package ClasesProyecto.Mapa;

import Listas.ListaDE.Cola;
import Listas.ListasSE.ListaSE;
import Listas.ListasSE.Map;

//import static es.uah.matcomp.mp.pfinal.componentesylogs.MainGridApplication.log;

public class Mapa<T> {

    private static int[] max;
    public Casilla<T>[][] casillas;
    private ListaSE<Enlace> aristas = new ListaSE<Enlace>();

    public static int[] getMax() {
        return max;
    }

    public Mapa(int n, int m) {
       // log.info("Creando Mapa");
        //Estaría bien que fuese el mapa al menos 3x3
        this.aristas = new ListaSE<Enlace>();
        this.casillas = new Casilla[n][m];
        this.max = new int[]{n-1, m-1};
        //Esto  será la función crear mapa por orden
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                this.casillas[i][j] = new Casilla<T>(new Integer[]{i,j});
            }
        }


        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                //casillas[i][j].addEntrada(casillas[i - 1][j]);
                //casillas[i][j].addEntrada(casillas[i + 1][j]);
                //casillas[i][j].addEntrada(casillas[i][j - 1]);
                //casillas[i][j].addEntrada(casillas[i][j + 1]);

                casillas[i][j].addSalida(casillas[i - 1][j]);
                casillas[i][j].addSalida(casillas[i + 1][j]);
                casillas[i][j].addSalida(casillas[i][j - 1]);
                casillas[i][j].addSalida(casillas[i][j + 1]);
            }
        }
        for (int i : new int[]{0, n-1}) {
            for (int j = 0; j < m; j++) {
                if (i == 0) {
                    //casillas[i][j].addEntrada(casillas[i + 1][j]);
                    casillas[i][j].addSalida(casillas[i + 1][j]);
                } else {
                    //casillas[i][j].addEntrada(casillas[i - 1][j]);
                    casillas[i][j].addSalida(casillas[i - 1][j]);
                }
            }
        }
        for (int j : new int[]{0, m-1}) {
            for (int i = 0; i < n ; i++) {
                if (j == 0) {
                    //casillas[i][j].addEntrada(casillas[i][j+1]);
                    casillas[i][j].addSalida(casillas[i][j+1]);
                } else {
                    //casillas[i][j].addEntrada(casillas[i ][j-1]);
                    casillas[i][j].addSalida(casillas[i][j-1]);
                }
            }
        }
        for (int i : new int[]{0, n-1}) {
            for (int j = 0; j < m-1; j++) {
                    //casillas[i][j].addEntrada(casillas[i + 1][j]);
                    casillas[i][j].addSalida(casillas[i][j+1]);
                    casillas[i][j+1].addSalida(casillas[i][j]);

            }
        }
        for (int j : new int[]{0, m-1}) {
            for (int i = 0; i < n-1 ; i++) {
                casillas[i][j].addSalida(casillas[i+1][j]);
                casillas[i+1][j].addSalida(casillas[i][j]);


            }
        }




    }




    public String printCodigoGrafo() {
        //https://dreampuf.github.io/GraphvizOnline/
        //Aquí pued verse
        char com = '"'; // No puedes poner las comillas sin  cerrar el texto si no xd
        String codigo = "digraph regexp {\nfontname=" + com + "Helvetica,Arial,sans-serif" + com + "\nnode [fontname=" + com + "Helvetica,Arial,sans-serif" + com + "]\nedge [fontname=" + com + "Helvetica,Arial,sans-serif" + com + "]";

        for (Casilla casillas2[] : casillas) {
            for (Casilla c : casillas2){
                codigo += "\nn" + c.getPos()[0]+""+c.getPos()[1] + " [label=" + com +  c.getPos()[0]+","+c.getPos()[1] + com + "];";
        }
    }

        for (Casilla casillas2[] : casillas) {
            for (Casilla c : casillas2){
                for (Object a2 : c.getSalidas()) {
                    Enlace a = (Enlace) a2;
                    codigo += "\nn" + a.getOrigen().getPos()[0] + "" + a.getOrigen().getPos()[1] + " -> n" + a.getDestino().getPos()[0] + "" + a.getDestino().getPos()[1] + " [label=" + com +"1" + com + "];";
                }
            }
        }
        for (Object a2 : aristas.values()) {
            Enlace a = (Enlace) a2;
            codigo += "\nn" + a.getOrigen().getPos()[0]+""+ a.getOrigen().getPos()[1] + " -> n" + a.getDestino().getPos()[0]+""+a.getDestino().getPos()[1] + " [label=" + com + a.getPeso() + com + "];";

        }
        codigo += "\n}";
        return codigo;
    }


/*
    public Casilla<T>[][] getVertices() {
        return this.casillas;
    }

    public ListaSE<Enlace> getAristas() {
        return aristas;
    }

*/
    public Map<Casilla<T>, CaminoMapa<T>> dijkstra(Casilla<T> origen) {

        //Preparamos las variables.
        Map<Casilla<T>, Double> distancias = new Map();   // Mantiene las distancias mínimas conseguidas a cada vértice.
        Cola<Casilla<T>> colaPendientes = new Cola();  // Mantiene cuáles son los siguientes vértices a calcular.
        Map<Casilla<T>, Casilla<T>> verticesAnteriores = new Map(); //Guarda el rastro del camino para recalcularlo después.

        this.dijkstra_init(origen, distancias, colaPendientes, verticesAnteriores);  //Inicialización
        this.dijkstra_calcula(distancias, colaPendientes, verticesAnteriores);      //Cálculo
        return this.dijkstra_procesaResultados(distancias, verticesAnteriores);    //Procesamiento de resultados
    }

    protected void dijkstra_init(Casilla<T> origen, Map<Casilla<T>, Double> distancias, Cola<Casilla<T>> colaPendientes, Map<Casilla<T>, Casilla<T>> verticesAnteriores) {

        distancias.add(0.0, origen);
        colaPendientes.add(origen);

        for (Casilla<T>[] casillas : this.casillas) {
            for (Casilla<T> casilla : casillas) {
                distancias.add(Double.MAX_VALUE, casilla);
            }
        }
    }

    /**
     * Realiza el cálculo de dijkstra, calculando tanto las distancias como los anteriores vértices que llegan a uno determinado.
     *
     * @param distancias
     * @param colaPendientes
     * @param verticesAnteriores
     */
    protected void dijkstra_calcula(Map<Casilla<T>, Double> distancias, Cola<Casilla<T>> colaPendientes, Map<Casilla<T>, Casilla<T>> verticesAnteriores) {
        while (!colaPendientes.isVacia()) {
            Casilla<T> verticeActual = colaPendientes.pop();  // Sacamos un vértice de la cola

            for (Object arco2 : verticeActual.getSalidas()) {   // Exploramos los arcos de salida del vértice.
                Enlace arco = (Enlace) arco2;
                Casilla<T> verticeVecino = arco.getDestino();       // Para cada vértice conectado a la salida del arco...
                double nuevoCalculoDeDistancia = distancias.getFromId(verticeActual) + verticeVecino.getPeso();  // Calculamos su distancia basándonos en lo que nos ha costado llegar al actual. Primer elemento: Coste 0.

                if (nuevoCalculoDeDistancia < distancias.getFromId(verticeVecino)) {  // Si resulta que la nueva distancia es mejor que la que se había calculado antes a ese vértice, sustituimos los valores por los nuevos.
                    distancias.eliminar(verticeVecino);
                    distancias.add(nuevoCalculoDeDistancia, verticeVecino);  // Guardamos la nueva distancia.
                    if (verticesAnteriores.isId(verticeVecino)) verticesAnteriores.eliminar(verticeVecino);
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
    protected Map<Casilla<T>, CaminoMapa<T>> dijkstra_procesaResultados(Map<Casilla<T>, Double> distancias, Map<Casilla<T>, Casilla<T>> verticesAnteriores) {
        Map<Casilla<T>, CaminoMapa<T>> caminos = new Map<>();

        for (Object verticeDestino2 : verticesAnteriores.keys()) {             // De todos los vértices calculados
            Casilla<T> verticeDestino = (Casilla) verticeDestino2;
            ListaSE<Casilla<T>> caminoCalculado = new ListaSE();                   // prepara un camino para cada uno
            Casilla<T> v = verticeDestino;                                          // y en un bucle recorre el camino
            while (v != null) {                                                     // hacia atrás.
                caminoCalculado.add(v);
                v = verticesAnteriores.getFromId(v); //El bucle es sobre v, o sea, los vértices: actualizo hasta que no tenga un origen (primero)
            }
            //Collections.reverse(caminoCalculado);  //Le damos la vuelta, para que el camino empiece en el origen, no en el último.

            CaminoMapa<T> camino = new CaminoMapa<>(caminoCalculado, distancias.getFromId(verticeDestino));
            caminos.add(camino, verticeDestino);
        }
        return caminos;
    }

/*
    public Map<Casilla<T>, Camino<T>> dijkstra(Casilla<T> origen) {

        //Preparamos las variables.
        Map<Casilla<T>, Double> distancias = new Map();   // Mantiene las distancias mínimas conseguidas a cada vértice.
        Cola<Casilla<T>> colaPendientes = new Cola();  // Mantiene cuáles son los siguientes vértices a calcular.
        Map<Casilla<T>, Casilla<T>> verticesAnteriores = new Map(); //Guarda el rastro del camino para recalcularlo después.

        this.dijkstra_init(origen, distancias, colaPendientes, verticesAnteriores);  //Inicialización
        this.dijkstra_calcula(distancias, colaPendientes, verticesAnteriores);      //Cálculo
        return this.dijkstra_procesaResultados(distancias, verticesAnteriores);    //Procesamiento de resultados
    }

    protected void dijkstra_init(Casilla<T> origen, Map<Casilla<T>, Double> distancias, Cola<Casilla<T>> colaPendientes, Map<Casilla<T>, Casilla<T>> verticesAnteriores) {

        distancias.add(0.0, origen);
        colaPendientes.add(origen);

        for (Casilla<T>[] casillas : this.casillas) {
            for (Casilla<T> casilla : casillas) {
                distancias.add(Double.MAX_VALUE, casilla);
            }
        }
    }

    /**
     * Realiza el cálculo de dijkstra, calculando tanto las distancias como los anteriores vértices que llegan a uno determinado.
     *
     * @param distancias
     * @param colaPendientes
     * @param verticesAnteriores
     *//*
    protected void dijkstra_calcula(Map<Casilla<T>, Double> distancias, Cola<Casilla<T>> colaPendientes, Map<Casilla<T>, Casilla<T>> verticesAnteriores) {
        while (!colaPendientes.isVacia()) {
            Casilla<T> verticeActual = colaPendientes.pop();  // Sacamos un vértice de la cola

            for (Cai arco2 : verticeActual.getSalidas()) {   // Exploramos los arcos de salida del vértice.
                Arista arco = (Arista) arco2;
                Casilla<T> verticeVecino = arco.getDestino();       // Para cada vértice conectado a la salida del arco...
                double nuevoCalculoDeDistancia = distancias.getFromId(verticeActual) + arco.getPeso();  // Calculamos su distancia basándonos en lo que nos ha costado llegar al actual. Primer elemento: Coste 0.

                if (nuevoCalculoDeDistancia < distancias.getFromId(verticeVecino)) {  // Si resulta que la nueva distancia es mejor que la que se había calculado antes a ese vértice, sustituimos los valores por los nuevos.
                    distancias.eliminar(verticeVecino);
                    distancias.add(nuevoCalculoDeDistancia, verticeVecino);  // Guardamos la nueva distancia.
                    if (verticesAnteriores.isId(verticeVecino)) verticesAnteriores.eliminar(verticeVecino);
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
     *//*
    protected Map<Casilla<T>, Camino<T>> dijkstra_procesaResultados(Map<Casilla<T>, Double> distancias, Map<Casilla<T>, Casilla<T>> verticesAnteriores) {
        Map<Casilla<T>, Camino<T>> caminos = new Map<>();

        for (Object verticeDestino2 : verticesAnteriores.keys()) {             // De todos los vértices calculados
            Casilla<T> verticeDestino = (Casilla) verticeDestino2;
            ListaSE<Casilla<T>> caminoCalculado = new ListaSE();                   // prepara un camino para cada uno
            Casilla<T> v = verticeDestino;                                          // y en un bucle recorre el camino
            while (v != null) {                                                     // hacia atrás.
                caminoCalculado.add(v);
                v = verticesAnteriores.getFromId(v); //El bucle es sobre v, o sea, los vértices: actualizo hasta que no tenga un origen (primero)
            }
            //Collections.reverse(caminoCalculado);  //Le damos la vuelta, para que el camino empiece en el origen, no en el último.

            Camino<T> camino = new Camino(caminoCalculado, distancias.getFromId(verticeDestino));
            caminos.add(camino, verticeDestino);
        }
        return caminos;
    }

*/
}




























