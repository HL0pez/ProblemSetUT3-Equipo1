package ucu.edu.aed.medible.medibles;

import java.util.LinkedList;
import java.util.List;

import ucu.edu.aed.medible.lib.Medible;

public class MedicionPredecirLinkedList extends Medible<List<String>> {

    private final LinkedList<String> lista;

    public MedicionPredecirLinkedList(LinkedList<String> lista) {
        this.lista = lista;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> data) {

        for (int i = 0; i < repeticiones; i++) {

            List<String> resultado = new LinkedList<>();

            for (String palabra : lista) {
                if (palabra.startsWith("cas")) {
                    resultado.add(palabra);
                }
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.lista;
    }

}
