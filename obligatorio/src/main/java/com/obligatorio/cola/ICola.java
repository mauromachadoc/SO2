package com.obligatorio.cola;

public interface ICola<T> {
    public void encolar(T objeto);

    public T desencolar();

    public boolean esVacia();

    public T frente();

    public void vaciar();

    public boolean estaLlena();

    public int cantElementos();

    public String imprimir(String separador);
}
