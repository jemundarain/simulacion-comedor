package com.company;

import java.util.ArrayList;
import java.util.Vector;
import java.util.Random;

public class Etapa {
    ArrayList<Tiempo> deServicios = new ArrayList<Tiempo>();
    ArrayList<Cliente> cola = new ArrayList<Cliente>();
    Vector<Servidor> servidores = new Vector<Servidor>(0);
    Estadistica estadistica = new Estadistica();
    TablaEventos tabla = new TablaEventos();
    Integer WL = new Integer(0); //longitud de la cola
    Integer AT = new Integer(0); //tiempo programado para la siguiente llegada
    Integer DT = new Integer(0); //tiempo programado para la siguiente salida

    public ArrayList<Tiempo> getDeServicios() {
        return deServicios;
    }

    public void setDeServicios(ArrayList<Tiempo> deServicios) {
        this.deServicios = deServicios;
    }

    public ArrayList<Cliente> getCola() {
        return cola;
    }

    public void setCola(ArrayList<Cliente> cola) {
        this.cola = cola;
    }

    public Vector<Servidor> getServidores() {
        return servidores;
    }

    public void setServidores(Vector<Servidor> servidores) {
        this.servidores = servidores;
    }

    public Estadistica getEstadistica() {
        return estadistica;
    }

    public void setEstadistica(Estadistica estadistica) {
        this.estadistica = estadistica;
    }

    public TablaEventos getTabla() {
        return tabla;
    }

    public void setTabla(TablaEventos tabla) {
        this.tabla = tabla;
    }

    public Integer getWL() {
        return WL;
    }

    public void setWL(Integer WL) {
        this.WL = WL;
    }

    public Integer getAT() {
        return AT;
    }

    public void setAT(Integer AT) {
        this.AT = AT;
    }

    public Integer getDT() {
        return DT;
    }

    public void setDT(Integer DT) {
        this.DT = DT;
    }

    public int buscarServidorDisponible(){
        Random azar = new Random();
        Vector<Integer> disponibles = new Vector<Integer>(this.servidores.size());
        for(int i=0 ; i < this.servidores.size() ; i++){
            if(!servidores.get(i).getSS()) {
                disponibles.add(i);
            }
        }
        if(disponibles.size()==0) {
            return -1; //todos estan ocupados
        }
        return disponibles.get(azar.nextInt(disponibles.size()));
    }

    public void generarTiemposDeServicio(){
        int i;
        this.deServicios.get(0).setAcumulada(deServicios.get(0).getProbabilidad());
        this.deServicios.get(0).setRango1(0);
        this.deServicios.get(0).setRango2(Math.round((deServicios.get(0).getProbabilidad()*100)-1));

        for(i=1 ; i<this.deServicios.size() ; i++) {
            this.deServicios.get(i).setAcumulada(this.deServicios.get(i - 1).getAcumulada() + this.deServicios.get(i).getProbabilidad());
            this.deServicios.get(i).setRango1(this.deServicios.get(i-1).getRango2() + 1);
            this.deServicios.get(i).setRango2(Math.round((this.deServicios.get(i).getAcumulada()*100-1)));
        }
    }

    public Integer getTiempoDeServicio(){
        int i;
        Random azar = new Random();
        int rango = azar.nextInt(100);
        for (i = 0; i < this.deServicios.size(); i++) {
            if(this.deServicios.get(i).getRango1()<=rango && this.deServicios.get(i).getRango2()>=rango)
                return this.deServicios.get(i).getTiempo();
        }
        return null;
    }



}
