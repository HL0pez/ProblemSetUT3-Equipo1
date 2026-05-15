package ucu.edu.aed.tda.trie;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

public class Trie implements TTrie, Serializable {

    private NodoTrie raiz;

    public Trie() {
        this.raiz = new NodoTrie();
    }

    @Override
    public Entry buscar(String palabra) {
        return this.raiz.buscar(palabra);
    }

    @Override
    public boolean insertar(String palabra, Object dato) {
        return this.raiz.insertar(palabra, dato);
    }

    @Override
    public List<Entry> predecir(String prefijo) {
        return this.raiz.predecir(prefijo);
    }

    @Override
    public void recorrer(Consumer consumer) {
        this.raiz.recorrer(consumer);
    }

}
