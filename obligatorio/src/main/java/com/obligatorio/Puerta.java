package com.obligatorio;

import java.util.concurrent.Semaphore;

public class Puerta {

    // hola profe
    private static Semaphore[] puertas = new Semaphore[12];

    public static void init() {
        for (int i = 0; i < 12; i++) {
            puertas[i] = new Semaphore(1);
        }
    }

    public static Semaphore[] getPuertas() {
        return puertas;
    }

    public static void AbrirPuerta(int numeroPerta) {

    }

    public static void CerrarPuerta(int numeroPerta) {
        // cerrar puerta
        puertas[numeroPerta].release();
    }

}
