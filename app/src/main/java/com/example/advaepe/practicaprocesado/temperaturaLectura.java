package com.example.advaepe.practicaprocesado;

public class temperaturaLectura {
    public long tiempo;

    @Override
    public String toString() {
        return "temperaturaLectura{" +
                "tiempo=" + tiempo +
                ", lugar='" + lugar + '\'' +
                ", temperatura=" + temperatura +
                '}';
    }

    public String lugar;
    public int temperatura;

    public temperaturaLectura(long l, String s, int i) {
        this.tiempo=l;
        this.lugar=s;
        this.temperatura=i;
    }



    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }
}
