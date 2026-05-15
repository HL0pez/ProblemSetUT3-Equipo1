package ucu.edu.aed.medible.medibles;

import java.util.List;

import ucu.edu.aed.medible.lib.Medible;
import ucu.edu.aed.tda.trie.Trie;

public class MedicionBuscarTrie extends Medible<List<String>> {

    private final Trie trie;

    public MedicionBuscarTrie(Trie trie) {
        this.trie = trie;
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.trie;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> palabras) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : palabras) {
                trie.buscar(palabra);
            }
        }
    }

}
