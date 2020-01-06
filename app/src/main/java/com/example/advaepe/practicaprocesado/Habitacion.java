package com.example.advaepe.practicaprocesado;

public class Habitacion {

    private String nombre;
    private String temperatura;
    public Habitacion(String n, String t){
        this.nombre=n;
        this.temperatura=t;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }
}
