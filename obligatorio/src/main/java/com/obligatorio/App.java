package com.obligatorio;

import java.sql.Time;

public class App {
    public static void main(String[] args) {
        correrPrueba("obligatorio\\src\\main\\java\\com\\obligatorio\\Pruebita.txt", "resultado.txt");
    }

    public static void correrPrueba(String nombreArchivoDatos, String nombreArchivoResultado){
            Admin admin = new Admin();
            Puerta.init();
            Manual manual = new Manual();
    
            String[] lineas = ManejadorArchivosGenerico
                    .leerArchivo(nombreArchivoDatos);
            new Thread(admin).start();
            new Thread(manual).start();
            Tracker tracker = new Tracker(nombreArchivoResultado);
            Admin.tracker = tracker;
            new Thread(tracker).start();
            for (String linea : lineas) {
                int randomInt = (int) (Math.random() * 500);
                try {
                    Thread.sleep(randomInt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String[] datos = linea.split(";");
                Admin.recibirMensaje(Integer.parseInt(datos[0]), Double.parseDouble(datos[1]), Integer.parseInt(datos[2]),
                        Integer.parseInt(datos[3]), new Time(System.currentTimeMillis() + randomInt),
                        Integer.parseInt(datos[4]));
            }

            


    
        
    }
}


