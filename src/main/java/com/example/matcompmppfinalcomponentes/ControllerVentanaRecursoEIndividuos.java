package com.example.matcompmppfinalcomponentes;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerVentanaRecursoEIndividuos {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab porcentajeAparicionRecursosTab;

    @FXML
    private Tab porcentajeRecursosTab;

    @FXML
    private Slider sliderNumIndividuosIniciales;

    @FXML
    private Slider sliderNumTurnos;

    @FXML
    private Slider sliderProbAgua;

    @FXML
    private Slider sliderProbAparicionCasillaRecursos;

    @FXML
    private Slider sliderProbBiblioteca;

    @FXML
    private Slider sliderProbClonacionBiblioteca;

    @FXML
    private Slider sliderProbClonacionIndividuo;

    @FXML
    private Slider sliderProbComida;

    @FXML
    private Slider sliderProbMontana;

    @FXML
    private Slider sliderProbPozo;

    @FXML
    private Slider sliderProbReproduccionIndividuo;

    @FXML
    private Slider sliderProbReproduccionTesoro;

    @FXML
    private Slider sliderProbTesoro;

    @FXML
    private Tab turnosDeVidaTab;

    @FXML
    void initialize() {
        assert porcentajeAparicionRecursosTab != null : "fx:id=\"porcentajeAparicionRecursosTab\" was not injected: check your FXML file 'Untitled'.";
        assert porcentajeRecursosTab != null : "fx:id=\"porcentajeRecursosTab\" was not injected: check your FXML file 'Untitled'.";
        assert sliderNumIndividuosIniciales != null : "fx:id=\"sliderNumIndividuosIniciales\" was not injected: check your FXML file 'Untitled'.";
        assert sliderNumTurnos != null : "fx:id=\"sliderNumTurnos\" was not injected: check your FXML file 'Untitled'.";
        assert sliderProbAgua != null : "fx:id=\"sliderProbAgua\" was not injected: check your FXML file 'Untitled'.";
        assert sliderProbAparicionCasillaRecursos != null : "fx:id=\"sliderProbAparicionCasillaRecursos\" was not injected: check your FXML file 'Untitled'.";
        assert sliderProbBiblioteca != null : "fx:id=\"sliderProbBiblioteca\" was not injected: check your FXML file 'Untitled'.";
        assert sliderProbClonacionBiblioteca != null : "fx:id=\"sliderProbClonacionBiblioteca\" was not injected: check your FXML file 'Untitled'.";
        assert sliderProbClonacionIndividuo != null : "fx:id=\"sliderProbClonacionIndividuo\" was not injected: check your FXML file 'Untitled'.";
        assert sliderProbComida != null : "fx:id=\"sliderProbComida\" was not injected: check your FXML file 'Untitled'.";
        assert sliderProbMontana != null : "fx:id=\"sliderProbMontana\" was not injected: check your FXML file 'Untitled'.";
        assert sliderProbPozo != null : "fx:id=\"sliderProbPozo\" was not injected: check your FXML file 'Untitled'.";
        assert sliderProbReproduccionIndividuo != null : "fx:id=\"sliderProbReproduccionIndividuo\" was not injected: check your FXML file 'Untitled'.";
        assert sliderProbReproduccionTesoro != null : "fx:id=\"sliderProbReproduccionTesoro\" was not injected: check your FXML file 'Untitled'.";
        assert sliderProbTesoro != null : "fx:id=\"sliderProbTesoro\" was not injected: check your FXML file 'Untitled'.";
        assert turnosDeVidaTab != null : "fx:id=\"turnosDeVidaTab\" was not injected: check your FXML file 'Untitled'.";

    }

}
