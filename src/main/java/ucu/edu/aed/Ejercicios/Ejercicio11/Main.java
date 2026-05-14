package ucu.edu.aed.Ejercicios.Ejercicio11;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
 
        ContadorPalabras contador = new ContadorPalabras();
        GraficadorConsola graficador = new GraficadorConsola();
 
        Map<String, Integer> frecuencias = contador.procesarArchivo("./ut03/libro.txt");
 
        List<Map.Entry<String, Integer>> top10 = contador.obtenerTop10(frecuencias);
 
        graficador.graficar(top10);
    }
}