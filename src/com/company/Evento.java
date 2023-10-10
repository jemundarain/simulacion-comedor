package com.company;

import java.util.Vector;

public class Evento {
    String tipo;
    Integer cliente;
    Integer TM;
    Vector<Boolean> SS = new Vector<Boolean>(1);
    Integer WL;
    Integer AT;
    Integer DT;

    /*
    public enum TipoEvento{
        inicializacion, llegada, salida
    }*/

    public Evento(String tipo, Integer cliente, Integer TM, Vector<Boolean> SS, Integer WL, Integer AT, Integer DT) {
        this.tipo = tipo;
        this.cliente = cliente;
        this.TM = TM;
        this.SS = SS;
        this.WL = WL;
        this.AT = AT;
        this.DT = DT;
    }


    public String getTipo() {
        return tipo;
    }

    public Integer getCliente() {
        return cliente;
    }

    public Integer getTM() {
        return TM;
    }

    public Vector<Boolean> getSS() {
        return SS;
    }

    public Integer getWL() {
        return WL;
    }

    public Integer getAT() {
        return AT;
    }

    public Integer getDT() {
        return DT;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public void setTM(Integer TM) {
        this.TM = TM;
    }

    public void setSS(Vector<Boolean> SS) {
        this.SS = SS;
    }

    public void setWL(Integer WL) {
        this.WL = WL;
    }

    public void setAT(Integer AT) {
        this.AT = AT;
    }

    public void setDT(Integer DT) {
        this.DT = DT;
    }
}
