package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Simulacion {
    String nombre = new String();
    Integer dias = new Integer(0);
    Vector<Etapa> etapas = new Vector<Etapa>(4);
    Integer numClientes = new Integer(0);

    ArrayList<Tiempo> entreLlegadas = new ArrayList<Tiempo>();

    ProximoEvento proximoEvento = new ProximoEvento(0,0,"llegada");
    Integer TM = new Integer(0); //Tiempo de reloj de la simulacion
    static Integer MX = new Integer(0); //Longitud (en unidades de tiempo) de una ejecucion de la simulacion

    /*Estadisticas*/
    Integer clientesQueNoEsperan = new Integer(0);
    Float promedioClientesSistema = new Float(0);
    Float tiempoPromedioClienteSistema= new Float(0);
    Float tiempoPromedioClienteCola = new Float(0);
    Float tiempoPromedioEsperaClienteHaceCola = new Float(0);
    Float tiempoPromedioTrabajoAdicional = new Float(0);


    public String getNombre() {
        return nombre;
    }

    public Integer getDias() {
        return dias;
    }

    public Vector<Etapa> getEtapas() {
        return etapas;
    }

    public ArrayList<Tiempo> getEntreLlegadas() {
        return entreLlegadas;
    }

    public Integer getTM() {
        return TM;
    }

    public Integer getMX() {
        return MX;
    }

    public Integer getClientesQueNoEsperan() {
        return clientesQueNoEsperan;
    }

    public Float getPromedioClientesSistema() {
        return promedioClientesSistema;
    }

    public Float getTiempoPromedioClienteSistema() {
        return tiempoPromedioClienteSistema;
    }

    public Float getTiempoPromedioClienteCola() {
        return tiempoPromedioClienteCola;
    }

    public Float getTiempoPromedioEsperaClienteHaceCola() {
        return tiempoPromedioEsperaClienteHaceCola;
    }

    public Float getTiempoPromedioTrabajoAdicional() {
        return tiempoPromedioTrabajoAdicional;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public void setEtapas(Vector<Etapa> etapas) {
        this.etapas = etapas;
    }

    public void setEntreLlegadas(ArrayList<Tiempo> entreLlegadas) {
        this.entreLlegadas = entreLlegadas;
    }

    public void setTM(Integer TM) {
        this.TM = TM;
    }

    public void setMX(Integer MX) {
        this.MX = MX;
    }

    public Integer getNumClientes() {
        return numClientes;
    }

    public void setNumClientes(Integer numClientes) {
        this.numClientes = numClientes;
    }

    public ProximoEvento getProximoEvento() {
        return proximoEvento;
    }

    public void setProximoEvento(ProximoEvento proximoEvento) {
        this.proximoEvento = proximoEvento;
    }

    public void setClientesQueNoEsperan(Integer clientesQueNoEsperan) {
        this.clientesQueNoEsperan = clientesQueNoEsperan;
    }

    public void setPromedioClientesSistema(Float promedioClientesSistema) {
        this.promedioClientesSistema = promedioClientesSistema;
    }

    public void setTiempoPromedioClienteSistema(Float tiempoPromedioClienteSistema) {
        this.tiempoPromedioClienteSistema = tiempoPromedioClienteSistema;
    }

    public void setTiempoPromedioClienteCola(Float tiempoPromedioClienteCola) {
        this.tiempoPromedioClienteCola = tiempoPromedioClienteCola;
    }

    public void setTiempoPromedioEsperaClienteHaceCola(Float tiempoPromedioEsperaClienteHaceCola) {
        this.tiempoPromedioEsperaClienteHaceCola = tiempoPromedioEsperaClienteHaceCola;
    }

    public void setTiempoPromedioTrabajoAdicional(Float tiempoPromedioTrabajoAdicional) {
        this.tiempoPromedioTrabajoAdicional = tiempoPromedioTrabajoAdicional;
    }

    public void generarTiemposEntreLlegadas(){
        int i;
        this.entreLlegadas.get(0).setAcumulada(entreLlegadas.get(0).getProbabilidad());
        this.entreLlegadas.get(0).setRango1(0);
        this.entreLlegadas.get(0).setRango2(Math.round((entreLlegadas.get(0).getProbabilidad()*100)-1));

        for(i=1 ; i<this.entreLlegadas.size() ; i++) {
            this.entreLlegadas.get(i).setAcumulada(this.entreLlegadas.get(i - 1).getAcumulada() + this.entreLlegadas.get(i).getProbabilidad());
            this.entreLlegadas.get(i).setRango1(this.entreLlegadas.get(i-1).getRango2() + 1);
            this.entreLlegadas.get(i).setRango2(Math.round((this.entreLlegadas.get(i).getAcumulada()*100-1)));
        }
    }

    public Integer getTiempoEntreLlegada(){
        int i;
        Random azar = new Random();
        int rango = azar.nextInt(100);

        for (i = 0; i < this.entreLlegadas.size(); i++) {
            if(this.entreLlegadas.get(i).getRango1()<=rango && this.entreLlegadas.get(i).getRango2()>=rango)
                return this.entreLlegadas.get(i).getTiempo();
        }
        return null;
    }

    public void actualizarProximoEvento(){
        Vector<ProximoEvento> proximosEventos = new Vector<>();

        for(int i=0 ; i<4 ; i++){
            if(this.getEtapas().get(i).AT <= this.getEtapas().get(i).DT){
                proximosEventos.add(i, new ProximoEvento(i, this.getEtapas().get(i).AT, "llegada"));
            }else{
                proximosEventos.add(i, new ProximoEvento(i, this.getEtapas().get(i).DT, "salida"));
            }
        }

        int menor = proximosEventos.get(0).getTiempo();
        this.proximoEvento.setTiempo(menor);
        this.proximoEvento.setEstacion(0);
        this.proximoEvento.setTipo(proximosEventos.get(0).getTipo());

        for(int i=1 ; i<4; i++){
            if(proximosEventos.get(i).getTiempo() < menor) {
                menor = proximosEventos.get(i).getTiempo();
                this.proximoEvento.setTiempo(menor);
                this.proximoEvento.setEstacion(i);
                this.proximoEvento.setTipo(proximosEventos.get(i).getTipo());
            }
        }
    }

}
