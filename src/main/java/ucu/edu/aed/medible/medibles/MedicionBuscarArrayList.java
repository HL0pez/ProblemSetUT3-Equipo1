package ucu.edu.aed.medible.medibles;

import java.util.ArrayList;
import java.util.List;

import ucu.edu.aed.medible.lib.Medible;

public class MedicionBuscarArrayList extends Medible<List<String>> {

    private final ArrayList<String> list;

    public MedicionBuscarArrayList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> data) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : data) {
                // noinspection ResultOfMethodCallIgnored
                list.contains(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.list;
    }

}
