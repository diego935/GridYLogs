/*package com.example.matcompmppfinalcomponentes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ParameterController implements Initializable {


    /**
     * Hooks de conexión entre los controles visuales y el código, llevan @FXML para identificarlos
     **//*

   /* @FXML
    private Slider sliderVelocidad;

    @FXML
    private Slider sliderVida;
    @FXML
    private TextField textfieldNombre;


    /**
     * Controlador con modelo de datos en el que trabajar
     **//*/*
    private ParameterDataModelProperties model;
    private Stage scene;
    private ParameterDataModel parametrosData = new ParameterDataModel(7, 10, "Juanito");
    private ParameterDataModelProperties modeloParaGUICompartido = new ParameterDataModelProperties(parametrosData);



    /** Métodos de respuesta a eventos: El GUI llama a estos métodos del controlador para realizar operaciones **//*
    /**
     * La convención es llamarlos on+TipoControl+operacionalaqueresponde :
     * onMiBotonEjemploClick indica que es un "manejador de evento de tipo click" del botón MiBotonEjemplo del interfaz
     *//*


    @FXML
    protected void onBotonGuardarClick() {
        model.commit();
    }

    @FXML
    protected void onBotonReiniciarClick() {
        model.rollback();
    }

    @FXML protected void onBotonCerrarClick(){
        scene.close();
    }

    /**
     * Métodos de configuración
     **//*
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.print("Inicialización en ejecución del controlador de parámetros\n");

        if (model != null) {
            this.updateGUIwithModel();
        }
    }

    /**
     * Este método se encarga de conectar los datos del modelo con el GUI
     **//*
    protected void updateGUIwithModel() {
        sliderVelocidad.valueProperty().bindBidirectional(model.velocidadProperty());
        sliderVida.valueProperty().bindBidirectional(model.vidaProperty());
        textfieldNombre.textProperty().bindBidirectional(model.nombreProperty());

    }

    /**
     * Este método recibe los datos del modelo y los establece
     **//*
    public void loadUserData(RecursosEIndividuosProperties parametrosData) {
        this.model = parametrosData;
        this.updateGUIwithModel();
    }

    public void setStage(Stage s){
        this.scene = s;
    }




    @FXML
    protected void onMiBotonNuevaVentanaParametrosClick() {
        Stage stage = new Stage();
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
        }

    }








}*/