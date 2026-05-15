package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;
import java.util.*;

public class MedicionPredecirHashMap extends Medible<List<String>> {

    private final HashMap<String, String> map;

    public MedicionPredecirHashMap(HashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> data) {

        for (int i = 0; i < repeticiones; i++) {

            List<String> resultado = new ArrayList<>();

            for (String palabra : map.keySet()) {

                if (palabra.startsWith("cas")) {
                    resultado.add(palabra);
                }
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.map;
    }
}