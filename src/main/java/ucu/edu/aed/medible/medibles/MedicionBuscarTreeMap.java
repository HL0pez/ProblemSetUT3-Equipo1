package ucu.edu.aed.medible.medibles;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;

import ucu.edu.aed.medible.lib.Medible;

public class MedicionBuscarTreeMap extends Medible<List<String>> {

    private final TreeMap<String, String> treeMap;

    public MedicionBuscarTreeMap(Map<String, String> treeMap2) {
        this.treeMap = (TreeMap<String, String>) treeMap2;
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.treeMap;

    }

    @Override
    public void ejecutar(int repeticiones, List<String> data) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : data) {
                treeMap.containsKey(palabra);
            }
        }

    }

}
