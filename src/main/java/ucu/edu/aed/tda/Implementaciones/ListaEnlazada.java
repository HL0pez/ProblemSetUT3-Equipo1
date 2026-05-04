package ucu.edu.aed.tda.Implementaciones;

import java.util.function.Predicate;

import ucu.edu.aed.tda.Interfaces.TDALista;

public class ListaEnlazada<T> implements TDALista<T> {

    protected Nodo<T> cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    @Override
    public void agregar(T elem) {
        Nodo<T> nuevoNodo = new Nodo<>(elem);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
    }

    @Override
    public boolean eliminar(T elem) {

        if (cabeza == null) {
            return false;
        }
        if (cabeza.getDato().equals(elem)) {
            cabeza = cabeza.getSiguiente();
            return true;
        }
        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(elem)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    @Override
    public void agregar(int index, T elem) {

        if (index < 0) {
            throw new IndexOutOfBoundsException("Índice negativo: " + index);
        }
        Nodo<T> nuevoNodo = new Nodo<>(elem);
        if (index == 0) {
            nuevoNodo.setSiguiente(cabeza);
            cabeza = nuevoNodo;
            return;
        }
        Nodo<T> actual = cabeza;
        int contador = 0;
        while (actual != null) {
            if (contador == index - 1) {
                nuevoNodo.setSiguiente(actual.getSiguiente());
                actual.setSiguiente(nuevoNodo);
                return;
            }
            actual = actual.getSiguiente();
            contador++;
        }
        throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);

    }

    @Override
    public T obtener(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Índice negativo: " + index);
        }
        Nodo<T> actual = cabeza;
        int contador = 0;
        while (actual != null) {
            if (contador == index) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
            contador++;
        }
        throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
    }

    @Override
    public T remover(int index) {

        if (index < 0) {
            throw new IndexOutOfBoundsException("Índice negativo: " + index);
        }
        if (cabeza == null) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        if (index == 0) {
            T dato = cabeza.getDato();
            cabeza = cabeza.getSiguiente();
            return dato;
        }
        Nodo<T> actual = cabeza;
        int contador = 0;
        while (actual.getSiguiente() != null) {
            if (contador == index - 1) {
                T dato = actual.getSiguiente().getDato();
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return dato;
            }
            actual = actual.getSiguiente();
            contador++;
        }
        throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);

    }

    @Override
    public boolean contiene(T elem) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(elem)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    @Override
    public int indiceDe(T elem) {

        Nodo<T> actual = cabeza;
        int index = 0;
        while (actual != null) {
            if (actual.getDato().equals(elem)) {
                return index;
            }
            actual = actual.getSiguiente();
            index++;
        }
        return -1; // Elemento no encontrado

    }

    public Nodo<T> buscar(T elem) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (actual.getDato().equals(elem)) {
                System.out.println("Elemento encontrado: " + actual.getDato().toString());
                return actual;
            }
            actual = actual.getSiguiente();
        }
        System.out.println("Elemento no encontrado: " + elem);
        return null;
    }

    public boolean esVacia() {
        return cabeza == null;

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

    @Override
    public boolean esVacio() {
      return cabeza == null;
    }

    @Override
    public void vaciar() {
        cabeza = null;
    }

    @Override
    public T buscar(Predicate<T> criterio) {
        Nodo<T> actual = cabeza;
        while (actual != null) {
            if (criterio.test(actual.getDato())) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }




}
