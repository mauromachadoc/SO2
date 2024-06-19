package com.obligatorio.cola;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ernesto
 * @param <T>
 */
public class ListaOrdenada<T> extends Lista<T> implements iOrdenar {
    // Primitivas heredadas de lista, implementar la inserción ordenada.
    @Override
    public boolean insertar(Comparable etiqueta, T dato) {
        return insertar(new Nodo<T>(etiqueta, dato));

    }

    public boolean insertar(Nodo<T> unNodo) {
        if (esVacia()) {
            primero = unNodo;
        } else {
            Nodo<T> aux = primero;
            Nodo<T> anterior = null;
            while (aux != null && aux.getEtiqueta().compareTo(unNodo.getEtiqueta()) < 0) {
                anterior = aux;
                aux = aux.getSiguiente();
            }
            if (anterior == null) {
                unNodo.setSiguiente(primero);
                primero = unNodo;
            } else {
                anterior.setSiguiente(unNodo);
                unNodo.setSiguiente(aux);
            }
        }
        return true;
    }

    @Override
    public Lista insercionDirecta() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insercionDirecta'");
    }

    @Override
    public Lista seleccionDirecta() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seleccionDirecta'");
    }
}
