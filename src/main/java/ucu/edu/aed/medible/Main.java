package ucu.edu.aed.medible;

import ucu.edu.aed.medible.lib.Medible;
import ucu.edu.aed.medible.lib.Medicion;
import ucu.edu.aed.medible.medibles.*;
import ucu.edu.aed.tda.trie.*;
import ucu.edu.aed.utils.FileUtils;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    private static final int REPETICIONES = 20;

    public static void main(String[] args) {
        Trie trie = new Trie();
        LinkedList<String> linkedList = new LinkedList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        Map<String, String> treeMap = new TreeMap<>();

        List<String> palabrasParaAgregar = new LinkedList<>();
        List<String> palabrasParaBuscar = new LinkedList<>();
        FileUtils.leerLineas("./ut03/listado-general-desordenado.txt", palabrasParaAgregar::add);
        FileUtils.leerLineas("./ut03/listado-general-palabrasBuscar.txt", palabrasParaBuscar::add);

        for (String p : palabrasParaAgregar) {
            // insertar la palabra p en el trie
            trie.insertar(p, p);
            // insertar la palabra p en el linkedList
            linkedList.add(p);
            // insertar la palabra p en el arrayList
            arrayList.add(p);
            // insertar la palabra p en el hashMap
            hashMap.put(p, p);
            // insertar la palabra p en el treeMap
            treeMap.put(p, p);
        }
        /////////////////////////////////////////////////////
        /*
         * List<Medible<List<String>>> medibles = new LinkedList<>();
         * medibles.add(new MedicionBuscarLinkedList(linkedList));
         * StringBuilder sb = new StringBuilder();
         * sb.append("algoritmo,tiempo,memoria\n");
         * for (Medible<List<String>> m : medibles) {
         * Medicion mi = m.medir(REPETICIONES, palabrasParaBuscar);
         * mi.print();
         * sb.append(mi.toCSV()).append("\n");
         * }
         * FileUtils.escribirLineas("./salidaLista.csv", sb.toString());
         */
        /////////////////////////////////////////////////////

        ArrayList<Medible<List<String>>> arrayList2 = new ArrayList<>();
        arrayList2.add(new MedicionBuscarArrayList(arrayList));
        StringBuilder sbArray = new StringBuilder();
        sbArray.append("algoritmo,tiempo,memoria\n");
        for (Medible<List<String>> m : arrayList2) {
            Medicion mi = m.medir(REPETICIONES, palabrasParaBuscar);
            mi.print();
            sbArray.append(mi.toCSV()).append("\n");
        }
        FileUtils.escribirLineas("./salidaArray.csv", sbArray.toString());
        ////////////////////// Ejercicio 7 - Parte 3////////////////////////////

        ArrayList<Medible<List<String>>> arrayTrie = new ArrayList<>();
        arrayTrie.add(new MedicionBuscarTrie(trie));
        StringBuilder sbArrayTrie = new StringBuilder();
        sbArrayTrie.append("algoritmo,tiempo,memoria\n");
        for (Medible<List<String>> m : arrayTrie) {
            Medicion mi = m.medir(REPETICIONES, palabrasParaBuscar);
            mi.print();
            sbArray.append(mi.toCSV()).append("\n");
        }
        FileUtils.escribirLineas("./salidaTrie.csv", sbArray.toString());
        ;

        ArrayList<Medible<List<String>>> hashList = new ArrayList<>();
        hashList.add(new MedicionBuscarHashMap(hashMap));

        StringBuilder sbHash = new StringBuilder();
        sbHash.append("algoritmo,tiempo,memoria\n");

        for (Medible<List<String>> m : hashList) {
            Medicion mi = m.medir(REPETICIONES, palabrasParaBuscar);
            mi.print();
            sbHash.append(mi.toCSV()).append("\n");
        }

        FileUtils.escribirLineas("./salidaHashMap.csv", sbHash.toString());
        System.out.println("Medicion HashMap realizada");

        // TODO implementar MedicionBuscarTreeMap
        // medibles.add(new MedicionBuscarTreeMap(treeMap));

        ArrayList<Medible<List<String>>> treeList = new ArrayList<>();
        treeList.add(new MedicionBuscarTreeMap(treeMap));

        StringBuilder sbTree = new StringBuilder();
        sbTree.append("algoritmo,tiempo,memoria\n");

        for (Medible<List<String>> m : treeList) {
            Medicion mi = m.medir(REPETICIONES, palabrasParaBuscar);
            mi.print();
            sbTree.append(mi.toCSV()).append("\n");
        }

        FileUtils.escribirLineas("./salidaTreeMap.csv", sbTree.toString());
        System.out.println("Medicion TreeMap realizada");

        ///// Ejercicio 7 - Parte 4
        List<Medible<List<String>>> mediblesTabla = new ArrayList<>();

        mediblesTabla.add(new MedicionBuscarLinkedList(linkedList));
        mediblesTabla.add(new MedicionBuscarArrayList(arrayList));
        mediblesTabla.add(new MedicionBuscarHashMap(hashMap));
        mediblesTabla.add(new MedicionBuscarTreeMap(treeMap));
        mediblesTabla.add(new MedicionBuscarTrie(trie));

        StringBuilder sbTabla = new StringBuilder();
        sbTabla.append("Estructura,Memoria (MB),Tiempo (ms)\n");

        for (Medible<List<String>> m : mediblesTabla) {
            Medicion mi = m.medir(REPETICIONES, palabrasParaBuscar);
            double memoriaMB = mi.getMemoria() / (1024.0 * 1024.0);
            double tiempoMS = mi.getTiempoEjecucion() / 1_000_000.0;
            mi.print();
            sbTabla.append(mi.getTexto()).append(",").append(String.format("%.2f", memoriaMB)).append(",")
                    .append(String.format("%.2f", tiempoMS)).append("\n");
        }
        FileUtils.escribirLineas("./tablaFinal.csv", sbTabla.toString());
        System.out.println("Mediciones completas. Archivo tablaFinal.csv generado.");

        /// Ejercicio 7 - Parte 5///
        ///
        ///
        mediblesTabla.add(new MedicionPredecirTrie(trie));
        mediblesTabla.add(new MedicionPredecirLinkedList(linkedList));
        mediblesTabla.add(new MedicionPredecirHashMap(hashMap));

        StringBuilder sbP = new StringBuilder();
        sbP.append("Estructura,Memoria (MB),Tiempo (ms)\n");

        for (Medible<List<String>> m : mediblesTabla) {

            Medicion mi = m.medir(REPETICIONES, palabrasParaBuscar);

            double memoriaMB = mi.getMemoria() / (1024.0 * 1024.0);
            double tiempoMS = mi.getTiempoEjecucion() / 1_000_000.0;

            mi.print();

            sbP.append(mi.getTexto()).append(",").append(String.format("%.2f", memoriaMB)).append(",")
                    .append(String.format("%.2f", tiempoMS)).append("\n");
        }

        FileUtils.escribirLineas("./tablaPrediccion.csv", sbP.toString());
        System.out.println("Table prediccion generada en tablaPrediccion.csv");

    }
}