package com.obligatorio;

import com.obligatorio.cola.ColaConPrioridad;

import java.sql.Time;
import java.util.Random;

/*
 * 
 */
public class Manual implements Runnable {

    private static ColaConPrioridad<Persona> colaManual = new ColaConPrioridad();

    public static void procesar(int numeroPuerta, double coincidencia, int identificadorPersona, int prioridad,
            Time tiempo, int prohibido, Tracker tracker) {
        Persona p = new Persona(numeroPuerta, coincidencia, identificadorPersona, prioridad, tiempo, prohibido, tracker);
        colaManual.encolar(p.prioridad, p);
    }

    public void run() {
        while (true) {
            Random random = new Random();
            int randomInt = random.nextInt(2);
            if (!colaManual.esVacia()) {
                if (randomInt == 0) {
                    Persona p = colaManual.desencolar();
                    if (p.prohibido == 0) {
                        Admin.recibirMensaje(p.numeroPuerta, 100, p.identificadorPersona, p.prioridad, p.tiempo,
                                p.prohibido);
                        System.out.println("La persona fue admitida manualmente.");
                    } else {
                        System.out.println("La persona fue rechazada manualmente por estar prohibida.");
                    }
                } else {
                    colaManual.desencolar();
                    System.out.println("La persona fue rechazada manualmente.");
                }

            }
        }
    }

}
