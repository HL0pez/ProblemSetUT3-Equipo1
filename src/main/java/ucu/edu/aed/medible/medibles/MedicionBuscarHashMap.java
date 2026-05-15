package ucu.edu.aed.medible.medibles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ucu.edu.aed.medible.lib.Medible;

public class MedicionBuscarHashMap extends Medible<List<String>> {

    private final Map<String, String> hashMap;

    public MedicionBuscarHashMap(Map<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> data) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : data) {

                hashMap.containsKey(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.hashMap;
    }

}
