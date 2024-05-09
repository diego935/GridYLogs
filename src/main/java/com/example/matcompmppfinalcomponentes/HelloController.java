package com.example.matcompmppfinalcomponentes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML private GridPane tableroDeJuego;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Cargando el tablero de juego");

        // Mismo bucle que en el ejemplo de MainGridApplication
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // Aquí podrías instanciar tu LetrasColoresGrid
                // LetrasColoresGrid customComponent = new LetrasColoresGrid();
                // mainGrid.add(customComponent, i, j);

                // Ejemplo simplificado con un Label
                Label placeholder = new Label("Celda " + i + "," + j);
                placeholder.setMinSize(30, 30); // Tamaño mínimo para visualización
                placeholder.setStyle("-fx-border-color: black; -fx-text-alignment: center;");
                tableroDeJuego.add(placeholder, i, j);
            }
        }
    }


    /** Nota 1: IMPORTANTE:
     * la inicialización en este caso la hemos hecho cuando el usuario ha pulsado el botón.
     * También podríamos hacerla en el método "initialize" si indicamos que el controlador es "Initializable",
     * como en los ejemplos anteriores.
     */

    /** Nota 2:
     * Este ejemplo está hecho para reducir al máximo la complejidad, pero hay que recordar que podríamos querer
     * enlazar lo que aparece en cada celda del grid con objetos y properties...
     */

    /** Nota 3:
     * Si quiero varios elementos en la misma celda, debo introducirlos en un layout y ese layour meterlo en la celda.
     */

}