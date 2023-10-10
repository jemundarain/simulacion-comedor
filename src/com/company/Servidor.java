package com.company;

public class Servidor {
    Boolean SS = new Boolean(false); //status del servidor
    Integer tiempOcupado = new Integer(0);
    Integer tiempoDesocupado = new Integer(0);
    Integer clientesAtendidos = new Integer(0);
    Float porcentajeDeUtilizacion = new Float(0);
    Cliente cliente = new Cliente();

    public Servidor() {
        this.SS = false;
        this.tiempOcupado = 0;
        this.tiempoDesocupado = 0;
        this.clientesAtendidos = 0;
        this.porcentajeDeUtilizacion = (float)0;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setSS(Boolean SS) {
        this.SS = SS;
    }

    public void setTiempOcupado(Integer tiempOcupado) {
        this.tiempOcupado = tiempOcupado;
    }

    public void setTiempoDesocupado(Integer tiempoDesocupado) {
        this.tiempoDesocupado = tiempoDesocupado;
    }

    public void setClientesAtendidos(Integer clientesAtendidos) {
        this.clientesAtendidos = clientesAtendidos;
    }

    public void setPorcentajeDeUtilizacion(Float porcentajeDeUtilizacion) {
        this.porcentajeDeUtilizacion = porcentajeDeUtilizacion;
    }

    public Boolean getSS() {
        return SS;
    }

    public Integer getTiempOcupado() {
        return tiempOcupado;
    }

    public Integer getTiempoDesocupado() {
        return tiempoDesocupado;
    }

    public Integer getClientesAtendidos() {
        return clientesAtendidos;
    }

    public Float getPorcentajeDeUtilizacion() {
        return porcentajeDeUtilizacion;
    }
}
