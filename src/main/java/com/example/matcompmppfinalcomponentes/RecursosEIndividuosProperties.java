package com.example.matcompmppfinalcomponentes;

import javafx.beans.property.*;

public class RecursosEIndividuosProperties {

    protected DataModel original;
    private IntegerProperty velocidad = new SimpleIntegerProperty();
    private IntegerProperty vida = new SimpleIntegerProperty();
        private IntegerProperty agua = new SimpleIntegerProperty();
        private IntegerProperty comida = new SimpleIntegerProperty();
        private IntegerProperty pozo = new SimpleIntegerProperty();
        private IntegerProperty biblioteca = new SimpleIntegerProperty();
        private IntegerProperty tesoro = new SimpleIntegerProperty();

        private IntegerProperty montaña = new SimpleIntegerProperty();
        private StringProperty nombre = new SimpleStringProperty();

    public RecursosEIndividuosProperties(DataModel original){
        setOriginal(original);
    }

    public void commit(){
        original.setVelocidad(velocidad.get());
        original.setVida(vida.get());
        original.setNombre(nombre.get());
        original.setAgua(agua.get());
        original.setBiblioteca(biblioteca.get());
        original.setComida(comida.get());
        original.setPozo(pozo.get());
        original.setTesoro(tesoro.get());
    }

    public void rollback(){
        velocidad.set(original.getVelocidad());
        vida.set(original.getVida());
        nombre.set(original.getNombre());
        comida.set(original.getComida());
        agua.set(original.getAgua());
        biblioteca.set(original.getBiblioteca());
        pozo.set(original.getPozo());
        montaña.set(original.getComida());
        tesoro.set(original.getTesoro());
    }

    public DataModel getOriginal(){
        return original;
    }

    public void setOriginal(DataModel original){
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

    public IntegerProperty aguaProperty() {
        return agua;
    }

    public IntegerProperty comidaProperty() {
        return comida;
    }

    public IntegerProperty pozoProperty() {
        return pozo;
    }

    public IntegerProperty bibliotecaProperty() {
        return biblioteca;
    }

    public IntegerProperty tesoroProperty() {
        return tesoro;
    }

    public IntegerProperty montañaProperty() {
        return montaña;
    }




}
