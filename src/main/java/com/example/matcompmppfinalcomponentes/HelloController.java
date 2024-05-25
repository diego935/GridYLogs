package com.example.matcompmppfinalcomponentes;

import ClasesProyecto.Global;
import ClasesProyecto.Individuos.Avanzado;
import ClasesProyecto.Individuos.Individuo;
import ClasesProyecto.Individuos.Normal;
import ClasesProyecto.Individuos.básico;
import ClasesProyecto.Mapa.Casilla;
import ClasesProyecto.Mapa.Mapa;
import Listas.ListasSE.Map;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

import static ClasesProyecto.Global.individuos;
import static ClasesProyecto.Global.mapa;
import static es.uah.matcomp.mp.pfinal.componentesylogs.MainGridApplication.labelMap;

public class HelloController implements Runnable{
    public static int m = 8; //Largo
    public static int n = 10; //Ancho
    @FXML
    public static Button botonTurnos;
    @FXML
    public static Slider ancho;
    @FXML
    public static Slider largo;
    @FXML
    public static Slider pDeRecursos;
    @FXML
    public static Button generarMapa;
    @FXML
    public static Button Settings;

    @FXML
    private Label welcomeText;

    @FXML
    protected GridPane tableroDeJuego;
    private Global juego;
    boolean ya;
    private DataModel parametrosData = new DataModel(7, 10, "Juanito", 10, 10, 10, 10, 101, 0);
    private RecursosEIndividuosProperties modeloParaGUICompartido = new RecursosEIndividuosProperties(parametrosData);

    //juego
    boolean pause = true;
    boolean first = false;
    Thread thread;


    @FXML
    protected void onPlay() {
        generarMapa.setVisible(true);
        //generarMapa.setDisable(false);

    }

    @FXML
    protected void onHelloButtonClick() {
        if (ya) return;
        ya = true;
        welcomeText.setText("Cargando el tablero de juego");

        thread = new Thread(this, "turnos");

        Mapa<Integer> mapa = new Mapa<>(n, m);
        Map<Individuo, Integer[]> individuos = new Map<>();

        Individuo i1 = new Avanzado(1, 10, 0, 0.05, 30);
        Individuo i2 = new Avanzado(2, 15, 0, 0.05, 30);
        Individuo i3 = new Avanzado(3, 10, 0, 0.05, 30, i1, i2);
        Individuo i4 = new Avanzado(4, 10, 0, 0.05, 30, i1, i2);
        Individuo i5 = new Avanzado(5, 10, 0, 0.05, 50, i3, i2);
        Individuo i6 = new Avanzado(6, 20, 0, 0.05, 50, i4, i5);

        i1.setPos(new Integer[]{1, 7});
        i2.setPos(new Integer[]{3, 3});
        i3.setPos(new Integer[]{1, 2});
        i4.setPos(new Integer[]{7, 4});
        i5.setPos(new Integer[]{9, 4});
        i6.setPos(new Integer[]{9, 4});


        //Global
        juego = new Global(mapa, individuos);
        //mapa.casillas[6][5].generarRecurso(1);
        System.out.println(mapa.printCodigoGrafo());
        juego.addIndividuo(i1);
        juego.addIndividuo(i2);
        juego.addIndividuo(i3);
        juego.addIndividuo(i4);
        juego.addIndividuo(i5);
        juego.addIndividuo(i6);


        // Mismo bucle que en el ejemplo de MainGridApplication
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Aquí podrías instanciar tu LetrasColoresGrid
                //LetrasColoresGrid customComponent = new LetrasColoresGrid();
                //mainGrid.add(customComponent, i, j);
                // Ejemplo simplificado con un Label
                Button placeholder = new Button("Celda " + i + "," + j);
                placeholder.setId(i + "_"+ j);
                placeholder.setOnAction(onBotton());
                placeholder.setMinSize(100, 60); // Tamaño mínimo para visualización
                placeholder.setStyle("-fx-border-color: black; -fx-text-alignment: center;");
                tableroDeJuego.add(placeholder, i, j);
                labelMap[i][j] = placeholder;

            }
        }
    //    botonTurnos.setDisable(false);
    //    botonTurnos.setVisible(true);

        for (Casilla[] casillas : mapa.casillas){
            for(Casilla casilla :casillas){
                System.out.println(casilla.pos[0]+","+ casilla.pos[1]);
            }
        }



    }


    @FXML
    protected void onCargar() {
        if (ya) return;
        Gson gson = new Gson();
        try {
            FileWriter writer = new FileWriter("./Partida.txt");

            gson.toJson(juego, writer);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    protected synchronized void onGuardar() {
        pause = true;
        if (pause) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
            Gson gson = new Gson();
            try {
                FileWriter writer = new FileWriter("./Partida.txt");
                gson.toJson(juego, writer);
            } catch (IOException e){
                e.printStackTrace();
            }

    }


    @FXML
    protected synchronized EventHandler<ActionEvent> onBotton() {
        return event -> {
            Button boton = (Button) event.getSource();
            String[] coordenadas = boton.getId().split("_");
            int i = Integer.parseInt(coordenadas[0]);
            int j = Integer.parseInt(coordenadas[1]);
            System.out.println("Posición: " + i + ", " + j);
        };
    }



        @FXML
    protected synchronized void onPassTurnButton() {
        pause = !pause;
        //botonTurnos.setText(pause ? "Resume" : "Pause");
            System.out.println(!pause ? "Resume" : "Pause");

        if (pause) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else {
            thread = new Thread(this, "turnos");
            thread.start();
        }
//        while (!pause) {
//            juego.pasarTurno();
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    //Label label =labelMap.getFromId(new Integer[]{i, j});
//                    //texto = mapa.casillas[i][j].getInfo();
//                    //System.out.println(texto);
//                    //label.setText(texto);
//                    labelMap[i][j].setText(mapa.casillas[i][j].getInfo());
//                }
//            }
//            try {
//                Thread.sleep(2000);
//            } catch (Exception a) {
//
//                System.out.println("todo");
//            }
//        }
    }


    @FXML
    protected void onSettings() {


    }


    @FXML
    protected void onMiBotonNuevaVentanaParametrosClick() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AplicationVentanaRecursoEIndividuos.class.getResource("ventanaRecursosEIndividuos.fxml"));

        try {
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Establezca parámetros: ");
            stage.setScene(scene);
            ControllerVentanaRecursoEIndividuos p = fxmlLoader.getController();
            p.loadUserData(this.modeloParaGUICompartido); //Carga los datos del modelo en el gui, todas las ventanas comparten el mismo en este caso
            p.setStage(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }



        /*Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("parameters-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 420, 340);
            stage.setTitle("Establezca parámetros: ");
            stage.setScene(scene);
            ParameterController p = fxmlLoader.getController();
            p.loadUserData(this.modeloParaGUICompartido); //Carga los datos del modelo en el gui, todas las ventanas comparten el mismo en este caso
            p.setStage(stage);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }*/


    }

    @Override
    public void run() {
        System.out.println(pause);
        while (!pause) {
            juego.pasarTurno();

            // Use Platform.runLater() to update the GUI on the JavaFX Application Thread
            Platform.runLater(() -> {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        labelMap[i][j].setText(mapa.casillas[i][j].getInfo());
                    }
                }
            });

            /*Individuo i = (Individuo )(individuos.keys()[0]);
            System.out.println(i.getFamilia());
            String accion;
            do {
                accion = i.acciones.pop();
                System.out.println(accion);
            } while (!i.acciones.isVacia());
*/

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(individuos.numElementos()<2) {
                pause = true;
                if (individuos.numElementos() ==1 ){
                    System.out.println("Acciones del superviviente:");
                    Individuo i = (Individuo )(individuos.keys()[0]);
                    System.out.println(i.getFamilia());
                    String accion;
                    do {
                        accion = i.acciones.pop();
                        System.out.println(accion);
                    } while (!i.acciones.isVacia());
                 System.out.println(i.acciones);

                }
            }
        }
    }
}


/**
 * Nota 1: IMPORTANTE:
 * la inicialización en este caso la hemos hecho cuando el usuario ha pulsado el botón.
 * También podríamos hacerla en el método "initialize" si indicamos que el controlador es "Initializable",
 * como en los ejemplos anteriores.
 * Nota 2:
 * Este ejemplo está hecho para reducir al máximo la complejidad, pero hay que recordar que podríamos querer
 * enlazar lo que aparece en cada celda del grid con objetos y properties...
 * Nota 3:
 * Si quiero varios elementos en la misma celda, debo introducirlos en un layout y ese layour meterlo en la celda.
 * Nota 2:
 * Este ejemplo está hecho para reducir al máximo la complejidad, pero hay que recordar que podríamos querer
 * enlazar lo que aparece en cada celda del grid con objetos y properties...
 * Nota 3:
 * Si quiero varios elementos en la misma celda, debo introducirlos en un layout y ese layour meterlo en la celda.
 * Nota 2:
 * Este ejemplo está hecho para reducir al máximo la complejidad, pero hay que recordar que podríamos querer
 * enlazar lo que aparece en cada celda del grid con objetos y properties...
 * Nota 3:
 * Si quiero varios elementos en la misma celda, debo introducirlos en un layout y ese layour meterlo en la celda.
 */

/** Nota 2:
 * Este ejemplo está hecho para reducir al máximo la complejidad, pero hay que recordar que podríamos querer
 * enlazar lo que aparece en cada celda del grid con objetos y properties...
 */

/** Nota 3:
 * Si quiero varios elementos en la misma celda, debo introducirlos en un layout y ese layour meterlo en la celda.
 */

