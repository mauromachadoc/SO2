package com.obligatorio;

import java.sql.Time;
import java.util.HashMap;

public class Tracker implements Runnable {

    public int esperaTotal = 0;
    public int cantidadPersonas = 0;
    public HashMap intervalosDeEspera = new HashMap<String, Integer>();
    public long tiempoInicial = System.currentTimeMillis();
    public long tiempoFinal = 0;
    public String direccionResultados;

    public Tracker(String direccionResultados){
        this.direccionResultados = direccionResultados;
        intervalosDeEspera.put("Poco tiempo(0s-2s)", 0);
        intervalosDeEspera.put("Buen tiempo(2s-5s)", 0);
        intervalosDeEspera.put("Bastante tiempo(5s-15s)", 0);
        intervalosDeEspera.put("Mucho tiempo(15s+)", 0);
    }

    public void setTiempoFinal(){
        tiempoFinal = System.currentTimeMillis() - tiempoInicial;

        String[] lineasResultado = new String[7];
        lineasResultado[0] = "El tiempo total de ejecucion fue de  " + this.getTiempoTotal() + "segundos";
        lineasResultado[1] = "La cantidad de personas que pasaron por la puerta fue de " + this.cantidadPersonas;
        lineasResultado[2] = "El tiempo promedio de espera es de " + this.getEsperaPromedio() + " segundos";
        lineasResultado[3] = "El tiempo moda de espera es de " + this.getEsperaModa();
        lineasResultado[4] = this.intervalosDeEspera.get("Poco tiempo(0s-2s)") + " personas esperaron menos de 2 segundos";
        lineasResultado[5] = this.intervalosDeEspera.get("Mucho tiempo(15s+)") + " personas esperaron más de 15 segundos";
        lineasResultado[6] = "Se espero una cantidad de tiempo total de " + this.getEsperaTotal() + " segundos";

        ManejadorArchivosGenerico.escribirArchivo(direccionResultados, lineasResultado);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000);
                System.out.println("El tiempo promedio de espera es de " + ((double) esperaTotal) / cantidadPersonas
                        + " segundos");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void entraPersona(int espera){
        esperaTotal += espera;
        cantidadPersonas++;

        if (espera < 2){
            intervalosDeEspera.put("Poco tiempo(0s-2s)", (int) intervalosDeEspera.get("Poco tiempo(0s-2s)") + 1);
        } else if (espera < 5){
            intervalosDeEspera.put("Buen tiempo(2s-5s)", (int) intervalosDeEspera.get("Buen tiempo(2s-5s)") + 1);
        } else if (espera < 15){
            intervalosDeEspera.put("Bastante tiempo(5s-15s)", (int) intervalosDeEspera.get("Bastante tiempo(5s-15s)") + 1);
        } else {
            intervalosDeEspera.put("Mucho tiempo(15s+)", (int) intervalosDeEspera.get("Mucho tiempo(15s+)") + 1);
        }
        
    }

    public double getEsperaPromedio() {
        return ((double) esperaTotal) / cantidadPersonas;
    }

    public String getEsperaModa(){
        String moda = "";
        int max = 0;
        for (Object key : intervalosDeEspera.keySet()){
            if ((int) intervalosDeEspera.get(key) > max){
                moda = (String) key;
                max = (int) intervalosDeEspera.get(key);
            }
        }
        return moda;
    }

    public int getCantidadPersonas(){
        return cantidadPersonas;
    }

    public double getTiempoTotal(){
        return ((double) tiempoFinal) / 1000;
    }

    public int getEsperaTotal(){
        return esperaTotal;
    }




}
