package com.company;

public class ProximoEvento {
    Integer estacion;
    Integer tiempo;
    String tipo;

    public ProximoEvento(Integer estacion, Integer tiempo, String tipo) {
        this.estacion = estacion;
        this.tiempo = tiempo;
        this.tipo = tipo;
    }

    public ProximoEvento(){
        this.estacion = 0;
        this.tiempo = 0;
        this.tipo = "";
    }

    public Integer getEstacion() {
        return estacion;
    }

    public void setEstacion(Integer estacion) {
        this.estacion = estacion;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
