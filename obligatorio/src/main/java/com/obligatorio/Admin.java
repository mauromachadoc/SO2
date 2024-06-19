package com.obligatorio;

import com.obligatorio.cola.ColaConPrioridad;

import java.util.concurrent.Semaphore;
import java.sql.Time;

public class Admin implements Runnable {
    public static Tracker tracker;
    public static long tiempoInicial;

    private static ColaConPrioridad<Persona>[] colas = new ColaConPrioridad[12];

    public static void recibirMensaje(int numeroPuerta, double coincidencia, int identificadorPersona, int prioridad,
            Time tiempo, int prohibido) {
        if (coincidencia < 0.95)
            return;
        if (coincidencia < 0.995) {
            Manual.procesar(numeroPuerta, coincidencia, identificadorPersona, prioridad, tiempo, prohibido, tracker);
            return;
        }
        if (prohibido == 1) {
            System.out.println("La persona fue rechazada por estar prohibida.");

            return;
        }
        Persona p = new Persona(numeroPuerta, coincidencia, identificadorPersona, prioridad, tiempo, prohibido, tracker);
        colas[numeroPuerta].encolar(p.prioridad, p);
    }

    public void admministrar() {
        int i = 0;
        tiempoInicial = System.currentTimeMillis();
        while (System.currentTimeMillis()-tiempoInicial < 10000) {
            if (!colas[i].esVacia()) {
                tiempoInicial = System.currentTimeMillis();
                Semaphore lock = Puerta.getPuertas()[i];
                if (lock.tryAcquire()) {
                    Persona p = colas[i].desencolar();
                    p.start();
                }
            }
            i++;
            if (i == 12)
                i = 0;
        }
        tracker.setTiempoFinal();
    }

    public void run() {
        admministrar();
    }

    public Admin() {
        for (int i = 0; i < 12; i++) {
            colas[i] = new ColaConPrioridad<Persona>();
        }
    }

}
