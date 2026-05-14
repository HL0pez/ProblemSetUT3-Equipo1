package ucu.edu.aed.tda.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TTrieHashMap<T> implements TTrie<T> {

    private NodoTrieHashMap<T> raiz;

    public TTrieHashMap() {
        this.raiz = new NodoTrieHashMap<>();
    }

    @Override
    public void recorrer(Consumer<Entry<T>> consumer) {
        raiz.recorrer(consumer);
    }

    @Override
    public Entry<T> buscar(String palabra) {
        return raiz.buscar(palabra.toLowerCase());
    }

    @Override
    public boolean insertar(String palabra, T dato) {
        return raiz.insertar(palabra.toLowerCase(), dato);
    }

    @Override
    public List<Entry<T>> predecir(String prefijo) {
        return raiz.predecir(prefijo.toLowerCase());
    }

    // busca todas las palabras del trie en el texto y devuelve sus posiciones
    public Map<String, List<Integer>> buscarPatrones(String texto) {
        Map<String, List<Integer>> resultado = new HashMap<>();
        texto = texto.toLowerCase();

        for (int i = 0; i < texto.length(); i++) {
            NodoTrieHashMap<T> nodoActual = raiz;
            for (int j = i; j < texto.length(); j++) {
                char c = texto.charAt(j);
                NodoTrieHashMap<T> hijo = nodoActual.getHijo(c);
                if (hijo == null) break;
                nodoActual = hijo;
                if (nodoActual.esPalabra()) {
                    String palabra = texto.substring(i, j + 1);
                    if (!resultado.containsKey(palabra)) {
                        resultado.put(palabra, new ArrayList<>());
                    }
                    resultado.get(palabra).add(i);
                }
            }
        }
        return resultado;
    }
}