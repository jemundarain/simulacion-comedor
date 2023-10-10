package com.company;

import javax.swing.text.TableView;
import java.util.Vector;
import java.util.ArrayList;

public class TablaEventos {
    ArrayList<Evento> eventos = new ArrayList<Evento>(1);

    public TablaEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    public TablaEventos(){
        this.eventos.add(new Evento("inicialización", -1,0, new Vector<Boolean>(), 0, 0, Simulacion.MX+1));
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

}