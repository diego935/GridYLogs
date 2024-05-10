package com.example.matcompmppfinalcomponentes;

import javafx.beans.property.*;


/** Esta es una clase de utilidad, que permite generar unas propiedades observables para el GUI a partir
 *  de los datos del modelo original de java.
 *  Tiene los métodos de commit y rollback para establecer la operación final de traspasar los datos modificados
 *  o reiniciarlos según se quiera.
 */
public class ParameterDataModelProperties {
    //Modelo de datos original
    protected ParameterDataModel original;
    private IntegerProperty velocidad = new SimpleIntegerProperty();
    private IntegerProperty vida = new SimpleIntegerProperty();
    private StringProperty nombre = new SimpleStringProperty();

    public ParameterDataModelProperties(ParameterDataModel original){
        setOriginal(original);
    }

    public void commit(){
        original.setVelocidad(velocidad.get());
        original.setVida(vida.get());
        original.setNombre(nombre.get());
    }

    public void rollback(){
        velocidad.set(original.getVelocidad());
        vida.set(original.getVida());
        nombre.set(original.getNombre());
    }

    public ParameterDataModel getOriginal(){
        return original;
    }

    public void setOriginal(ParameterDataModel original){
        this.original = original;
        rollback(); //Se inicializan los properties.

    }

    public Property<Number> velocidadProperty() {
        return velocidad;
    }

    public Property<Number> vidaProperty() {
        return vida;
    }

    public Property<String> nombreProperty() {
        return nombre;
    }
}
