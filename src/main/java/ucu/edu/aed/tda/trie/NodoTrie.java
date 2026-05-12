package ucu.edu.aed.tda.trie;

import java.util.List;
import java.util.function.Consumer;

public class NodoTrie<T> implements TNodoTrie<T> {

    private Entry<T> dato;
    private NodoTrie<T>[] hijos;
    private boolean esPalabra;

    @SuppressWarnings("unchecked")
    public NodoTrie() {
        this.dato = null;
        this.hijos = new NodoTrie[26];
        this.esPalabra = false;
    }

    @Override
    public void recorrer(Consumer<Entry<T>> consumer) {
        if (this.esPalabra) {
            consumer.accept(this.dato);
        }
        for (NodoTrie<T> hijo : hijos) {
            if (hijo != null) {
                hijo.recorrer(consumer);
            }
        }
    }

    @Override
    public Entry<T> buscar(String palabra) {
        // throw new UnsupportedOperationException("Unimplemented method 'buscar'");
        NodoTrie<T> nodoActual = this;
        for (char c : palabra.toCharArray()) {
            int indice = c - 'a';
            NodoTrie<T> unHijo = nodoActual.hijos[indice];

            if (unHijo == null) {
                return null; // No se encontró la palabra
            }
            nodoActual = unHijo;

        }
        if (nodoActual.esPalabra()) {
            return nodoActual.dato;
        }
        return null;

    }

    @Override
    public boolean insertar(String palabra, T dato) {
        // throw new UnsupportedOperationException("Unimplemented method 'insertar'");

        NodoTrie<T> nodoActual = this;
        for (char c : palabra.toCharArray()) {
            int indice = c - 'a';
            if (nodoActual.hijos[indice] == null) {

                NodoTrie<T> nuevoHijo = new NodoTrie<>();
                nodoActual.hijos[indice] = nuevoHijo;
            }
            nodoActual = nodoActual.hijos[indice];
        }
        if (!nodoActual.esPalabra) {
            nodoActual.dato = new Entry<>(dato, true, palabra);
            nodoActual.esPalabra = true;
            return true;
        }
        return false;
    }

    @Override
    public List<Entry<T>> predecir(String prefijo) {
        throw new UnsupportedOperationException("Unimplemented method 'predecir'");
    }

    @Override
    public T getDato() {

        return this.dato.getDato();
    }

    @Override
    public boolean esPalabra() {
        return esPalabra();
    }
}
