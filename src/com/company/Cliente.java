package com.company;

import java.util.Vector;

public class Cliente {
    Integer id ; //Numero del cliente
    Vector WQi;  //Tiempo en cola en cada etapa
    Float W; //Tiempo promedio en el sistema
    Integer tiempoDeServicio;

    public Cliente(Integer id, Vector WQi, Float w) {
        this.id = id;
        this.WQi = WQi;
        this.W = w;
    }

    public Cliente(){
        this.id = 0;
        this.W = (float)0;
    }

    public Cliente(Integer id, Integer tiempoDeServicio){
        this.id = id;
        this.tiempoDeServicio = tiempoDeServicio;
    }

    public Cliente(Integer id){
        this.id =id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vector getWQi() {
        return WQi;
    }

    public void setWQi(Vector WQi) {
        this.WQi = WQi;
    }

    public Float getW() {
        return W;
    }

    public void setW(Float w) {
        W = w;
    }
}

