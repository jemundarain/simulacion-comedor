package com.company;

public class Tiempo {
    int tiempo;
    Float probabilidad;
    Float acumulada;
    Integer rango1;
    Integer rango2;

    public Tiempo(Integer tiempo, Float probabilidad, Float acumulada, Integer rango1, Integer rango2) {
        this.tiempo = tiempo;
        this.probabilidad = probabilidad;
        this.acumulada = acumulada;
        this.rango1 = rango1;
        this.rango2 = rango2;
    }

    public Tiempo(){
        this.tiempo = 0;
        this.probabilidad = (float) 0;
        this.acumulada = (float) 0;
        this.rango1 = 0;
        this.rango2 = 0;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public Float getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Float probabilidad) {
        this.probabilidad = probabilidad;
    }

    public Float getAcumulada() {
        return acumulada;
    }

    public Integer getRango1() {
        return rango1;
    }

    public Integer getRango2() {
        return rango2;
    }

    public void setAcumulada(Float acumulada) {
        this.acumulada = acumulada;
    }

    public void setRango1(Integer rango1) {
        this.rango1 = rango1;
    }

    public void setRango2(Integer rango2) {
        this.rango2 = rango2;
    }
}