package ucu.edu.aed.medible.medibles;

import java.util.List;

import ucu.edu.aed.medible.lib.Medible;
import ucu.edu.aed.tda.trie.Trie;

public class MedicionPredecirTrie extends Medible<List<String>> {

    private final Trie trie;

    public MedicionPredecirTrie(Trie trie) {
        this.trie = trie;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> data) {
        for (int i = 0; i < repeticiones; i++) {
            trie.predecir("cas");
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.trie;
    }

}
