package com.obligatorio.cola;

public class Cola<T> extends Lista<T> implements ICola<T> {

    public Cola() {
        super();
    }

    @Override
    public void encolar(T objeto) {
        this.insertar(new Nodo<T>((String) objeto, objeto));
    }

    @Override
    public T desencolar() {
        if (this.esVacia()) {
            return null;
        } else {
            Nodo<T> aux = this.primero;
            this.primero = aux.getSiguiente();
            return aux.getDato();
        }
    }

    @Override
    public T frente() {
        return this.primero.getDato();
    }

    @Override
    public void vaciar() {
        this.primero = null;
    }

    @Override
    public boolean estaLlena() {
        return false;
    }

}
