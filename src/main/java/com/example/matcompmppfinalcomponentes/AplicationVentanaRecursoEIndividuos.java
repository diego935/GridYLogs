package com.example.matcompmppfinalcomponentes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AplicationVentanaRecursoEIndividuos extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ventanaRecursosEIndividuos.fxml"));
        Parent root = fxmlLoader.load();

        ControllerVentanaRecursoEIndividuos controller = fxmlLoader.getController(); // Obtener el controlador

        primaryStage.setTitle("Ventana Parametrizable");
        primaryStage.setScene(new Scene(root, 800, 600)); // Tamaño de la ventana
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}