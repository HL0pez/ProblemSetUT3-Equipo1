package ucu.edu.aed.tda.Implementaciones;

import ucu.edu.aed.tda.Interfaces.TDACola;

public class Cola<T> extends ListaEnlazada<T> implements TDACola<T> {
    protected Nodo<T> ultimo;

    @Override
    public T frente() {
        if (esVacia()) {
            throw new java.util.NoSuchElementException("La cola está vacía");
        }
        return cabeza.getDato();
    }

    @Override
    public boolean poneEnCola(T dato) {
        agregar(dato);
        if (ultimo == null) {
            ultimo = cabeza;
        } else {
            ultimo = ultimo.getSiguiente();
        }
        return true;
    }

    @Override
    public T quitaDeCola() {
        if (esVacia()) {
            throw new java.util.NoSuchElementException("La cola está vacía");
        }
        T datoFrente = cabeza.getDato();
        eliminar(datoFrente);
        return datoFrente;
    }

    @Override
    public boolean esVacio() {
        return cabeza == null;
    }

    @Override
    public void vaciar() {
        cabeza = null;
        ultimo = null;
    }

    @Override
    public int tamaño() {
        int contador = 0;
        Nodo<T> actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.getSiguiente();
        }
        return contador;
    }

}
