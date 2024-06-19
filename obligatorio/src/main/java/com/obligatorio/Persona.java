package com.obligatorio;

import java.sql.Time;
import java.util.concurrent.locks.ReentrantLock;
import java.lang.Thread;

public class Persona extends Thread {
    public static ReentrantLock lock = new ReentrantLock();

    int numeroPuerta;
    double coincidencia;
    int identificadorPersona;
    int prioridad;
    Time tiempo;
    int prohibido;
    Tracker tracker;

    public Persona(int numeroPuerta, double coincidencia, int identificadorPersona, int prioridad, Time tiempo,
            int prohibido, Tracker tracker) {
        this.numeroPuerta = numeroPuerta;
        this.coincidencia = coincidencia;
        this.identificadorPersona = identificadorPersona;
        this.prioridad = prioridad;
        this.tiempo = tiempo;
        this.prohibido = prohibido;
        this.tracker = tracker;
    }

    public void run() {
        Puerta.AbrirPuerta(numeroPuerta);
        
        try {
            if (prioridad == 2)
                Thread.sleep(500); // si es discapacitado/obeso.
            else
                Thread.sleep(1500);

        } catch (Exception e) {
            e.printStackTrace();
        }
        double espera = (double) (System.currentTimeMillis() - tiempo.getTime()) / 1000;
        tracker.esperaTotal += espera;
        tracker.cantidadPersonas++;
        System.out.println("Persona " + identificadorPersona + " ha pasado por la puerta " + numeroPuerta + " luego de "
                + espera + " segundos de espera");
        tracker.entraPersona((int) espera);
        Puerta.CerrarPuerta(numeroPuerta);
    }
}