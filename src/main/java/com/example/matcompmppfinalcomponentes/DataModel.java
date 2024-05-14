package com.example.matcompmppfinalcomponentes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DataModel {
    private int vida;
    private int velocidad;
    private String nombre;
    private Integer agua;
    private Integer comida;
    private Integer pozo;
    private Integer biblioteca ;
    private Integer tesoro;
    private Integer montaña ;

    /** Constructor **/
    public DataModel(int vida, int velocidad, String nombre) {
        this.vida = vida;
        this.velocidad = velocidad;
        this.nombre = nombre;
    }
    public DataModel(int vida, int velocidad, String nombre,Integer agua, Integer comida,Integer montaña,Integer tesoro,Integer biblioteca,Integer pozo ) {
        this.vida = vida;
        this.velocidad = velocidad;
        this.nombre = nombre;
        this.agua = agua;
        this.comida = comida;
        this.pozo = pozo;
        this.montaña = montaña;
        this.tesoro = tesoro;
        this.biblioteca = biblioteca;
    }

    /** Setters y Getters **/
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAgua() {
        return agua;
    }

    public void setAgua(Integer agua) {
        this.agua = agua;
    }

    public Integer getComida() {
        return comida;
    }

    public void setComida(Integer comida) {
        this.comida = comida;
    }

    public Integer getPozo() {
        return pozo;
    }

    public void setPozo(Integer pozo) {
        this.pozo = pozo;
    }

    public Integer getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Integer biblioteca) {
        this.biblioteca = biblioteca;
    }

    public Integer getTesoro() {
        return tesoro;
    }

    public void setTesoro(Integer tesoro) {
        this.tesoro = tesoro;
    }

    public Integer getMontaña() {
        return montaña;
    }

    public void setMontaña(Integer montaña) {
        this.montaña = montaña;
    }
}

