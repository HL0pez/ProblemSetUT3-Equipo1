package ucu.edu.aed.Ejercicios.Ejercicio11;

import java.util.List;
import java.util.Map;

public class GraficadorConsola {
    public void graficar(List<Map.Entry<String, Integer>> top10) {
 
        if (top10.isEmpty()) {
            System.out.println("No hay datos.");
            return;
        }
 
        System.out.println("=== Top 10 palabras más frecuentes ===\n");
 
        int maxFrecuencia = top10.get(0).getValue();
 
        for (Map.Entry<String, Integer> entry : top10) {
            String palabra = entry.getKey();
            int frecuencia = entry.getValue();
 
            int largoBarra = (int) ((frecuencia / (double) maxFrecuencia) * 40);
            String barra = "#".repeat(largoBarra);
 
            System.out.printf("%-20s - %s (%d)%n", palabra, barra, frecuencia);
        }
    }
}
