package ucu.edu.aed.Ejercicios.Ejercicio11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ucu.edu.aed.utils.FileUtils;

public class ContadorPalabras {
    public Map<String, Integer> procesarArchivo(String ruta) {
 
        Map<String, Integer> frecuencias = new HashMap<>();
 
        FileUtils.leerLineas(ruta, linea -> {
            linea = linea.toLowerCase();
            linea = linea.replaceAll("[^\\p{L} ]", " ");
 
            String[] palabras = linea.split("\\s+");
 
            for (String palabra : palabras) {
                if (palabra.length() > 1) {
                    frecuencias.put(palabra, frecuencias.getOrDefault(palabra, 0) + 1);
                }
            }
        });
 
        return frecuencias;
    }
 
    public List<Map.Entry<String, Integer>> obtenerTop10(Map<String, Integer> frecuencias) {
 
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(frecuencias.entrySet());
 
        lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));
 
        return lista.subList(0, Math.min(10, lista.size()));
    }
}