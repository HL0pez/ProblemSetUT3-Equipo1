package ucu.edu.aed.tda.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class NodoTrieHashMap<T> implements TNodoTrie<T> {

    private Entry<T> dato;
    private Map<Character, NodoTrieHashMap<T>> hijos;
    private boolean esPalabra;

    public NodoTrieHashMap() {
        this.dato = null;
        this.hijos = new HashMap<>();
        this.esPalabra = false;
    }

    @Override
    public void recorrer(Consumer<Entry<T>> consumer) {
        if (this.esPalabra) {
            consumer.accept(this.dato);
        }
        for (NodoTrieHashMap<T> hijo : hijos.values()) {
            hijo.recorrer(consumer);
        }
    }

    @Override
    public Entry<T> buscar(String palabra) {
        NodoTrieHashMap<T> nodoActual = this;
        for (char c : palabra.toCharArray()) {
            NodoTrieHashMap<T> hijo = nodoActual.hijos.get(c);
            if (hijo == null) {
                return null;
            }
            nodoActual = hijo;
        }
        if (nodoActual.esPalabra) {
            return nodoActual.dato;
        }
        return null;
    }

    @Override
    public boolean insertar(String palabra, T dato) {
        NodoTrieHashMap<T> nodoActual = this;
        for (char c : palabra.toCharArray()) {
            nodoActual.hijos.putIfAbsent(c, new NodoTrieHashMap<>());
            nodoActual = nodoActual.hijos.get(c);
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
        NodoTrieHashMap<T> nodoActual = this;
        for (char c : prefijo.toCharArray()) {
            NodoTrieHashMap<T> hijo = nodoActual.hijos.get(c);
            if (hijo == null) {
                return new ArrayList<>();
            }
            nodoActual = hijo;
        }
        List<Entry<T>> resultados = new ArrayList<>();
        nodoActual.recorrer(resultados::add);
        return resultados;
    }

    @Override
    public T getDato() {
        return this.dato != null ? this.dato.getDato() : null;
    }

    @Override
    public boolean esPalabra() {
        return this.esPalabra;
    }

    public NodoTrieHashMap<T> getHijo(char c) {
    return hijos.get(c);
    }


}
