package com.example.matcompmppfinalcomponentes;

import ClasesProyecto.Global;
import ClasesProyecto.Individuos.Avanzado;
import ClasesProyecto.Individuos.Individuo;
import ClasesProyecto.Individuos.básico;
import ClasesProyecto.Mapa.Mapa;
import Listas.ListasSE.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import static ClasesProyecto.Global.mapa;
import static es.uah.matcomp.mp.pfinal.componentesylogs.MainGridApplication.labelMap;

public class HelloController {
    public static int m = 15; //Largo
    public static int n = 20; //Ancho
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
    private DataModel parametrosData = new DataModel(7, 10, "Juanito",10,10,10,10,101,0);
    private RecursosEIndividuosProperties modeloParaGUICompartido = new RecursosEIndividuosProperties(parametrosData);

    //juego

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
        Mapa<Integer> mapa = new Mapa<>(n, m);
        Map<Individuo, Integer[]> individuos = new Map<>();


        Individuo i1 = new básico(1, 10, 0, 0, 0);
        Individuo i2 = new básico(2, 10, 0, 0, 0);
        Individuo i3 = new Avanzado(3, 10, 0, 0, 0, i1, i2);
        Individuo i4 = new básico(4, 10, 0, 0, 0, i1, i2);
        Individuo i5 = new básico(5, 10, 0, 0, 0, i3, i2);
        Individuo i6 = new Avanzado(6, 10, 0, 0, 0, i4, i5);


        i1.setPos(new Integer[]{1, 7});
        i2.setPos(new Integer[]{3, 3});
        i3.setPos(new Integer[]{1, 2});
        i4.setPos(new Integer[]{7, 4});
        i5.setPos(new Integer[]{9, 4});
        i6.setPos(new Integer[]{7, 5});


        //Global
        juego = new Global(mapa, individuos);


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
                Label placeholder = new Label("Celda " + i + "," + j);
                placeholder.setMinSize(90, 50); // Tamaño mínimo para visualización
                placeholder.setStyle("-fx-border-color: black; -fx-text-alignment: center;");
                tableroDeJuego.add(placeholder, i, j);
                labelMap[i][j] = placeholder;

            }
        }
        botonTurnos.setDisable(false);
        botonTurnos.setVisible(true);

    }

    @FXML
    protected void onPassTurnButton() {
        juego.pasarTurno();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                //Label label =labelMap.getFromId(new Integer[]{i, j});
                //texto = mapa.casillas[i][j].getInfo();
                //System.out.println(texto);
                //label.setText(texto);
                labelMap[i][j].setText(mapa.casillas[i][j].getInfo());
            }
        }
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
 */

/** Nota 2:
 * Este ejemplo está hecho para reducir al máximo la complejidad, pero hay que recordar que podríamos querer
 * enlazar lo que aparece en cada celda del grid con objetos y properties...
 */

/** Nota 3:
 * Si quiero varios elementos en la misma celda, debo introducirlos en un layout y ese layour meterlo en la celda.
 */

