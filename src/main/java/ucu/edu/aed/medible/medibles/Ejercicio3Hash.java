package ucu.edu.aed.medible.medibles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import ucu.edu.aed.tda.hash.Report;
import ucu.edu.aed.tda.hash.THashLineal;

public class Ejercicio3Hash {

    private static final double[] FACTORES_DE_CARGA = {
            0.70, 0.75, 0.80, 0.85, 0.90, 0.91, 0.92, 0.93, 0.94, 0.95, 0.96, 0.97, 0.98, 0.99
    };

    private static final int CAPACIDAD_INICIAL = 1000;
    private static final int INTENTOS_BUSQUEDA_POR_FACTOR = 1000;

    public void evaluarRendimiento() {
        System.out.println("\n=====================================");
        System.out.println("EVALUACIÓN DE RENDIMIENTO - TABLA HASH");
        System.out.println("=====================================\n");


        System.out.printf("%-10s | %-15s | %-17s | %-17s%n",
                "Factor", "Inserción", "Búsqueda Exitosa", "Búsqueda Fallida");
        System.out.println("---------|-----------------|------------------|------------------");

        List<String> clavesBase = generarClavesBase();

        for (double factor : FACTORES_DE_CARGA) {
            THashLineal<String, String> tabla = new THashLineal<>(CAPACIDAD_INICIAL);
            int cantidadAInsertar = (int) (tabla.getCapacidad() * factor);
            double promedioComparacionesInsercion = insertarYMedir(tabla, clavesBase, cantidadAInsertar);
            double promedioComparacionesBusquedaExitosa = medirBusquedasExitosas(tabla, cantidadAInsertar);
            double promedioComparacionesBusquedaFallida = medirBusquedaFallidas(tabla, clavesBase, cantidadAInsertar);
            System.out.printf("%8.0f%% | %15.4f | %17.4f | %17.4f%n",
                    factor * 100,
                    promedioComparacionesInsercion,
                    promedioComparacionesBusquedaExitosa,
                    promedioComparacionesBusquedaFallida);
        }

        System.out.println("\n=====================================\n");
    }

    private List<String> generarClavesBase() {
        List<String> claves = new ArrayList<>();
        for (int i = 0; i < CAPACIDAD_INICIAL * 2; i++) {
            claves.add("clave_" + i);
        }
        Collections.shuffle(claves);
        return claves;
    }

    private double insertarYMedir(THashLineal<String, String> tabla, 
                                   List<String> claves, 
                                   int cantidadAInsertar) {
        long totalComparaciones = 0;
        int inserciones_exitosas = 0;

        for (int i = 0; i < cantidadAInsertar && i < claves.size(); i++) {
            Report report = new Report();
            String clave = claves.get(i);
            
            boolean insertado = tabla.insertar(clave, "valor_" + i, report);
            if (insertado) {
                totalComparaciones += report.getCantidadComparaciones();
                inserciones_exitosas++;
            }
        }

        return inserciones_exitosas > 0 ? (double) totalComparaciones / inserciones_exitosas : 0.0;
    }

    private double medirBusquedasExitosas(THashLineal<String, String> tabla, int cantidadInsertada) {
        long totalComparaciones = 0;
        int busquedas_realizadas = 0;

        List<String> clavesInsertadas = new ArrayList<>();
        for (String clave : tabla.keys()) {
            clavesInsertadas.add(clave);
            if (clavesInsertadas.size() >= cantidadInsertada) {
                break;
            }
        }

        int repeticiones = Math.min(INTENTOS_BUSQUEDA_POR_FACTOR, cantidadInsertada);
        for (int i = 0; i < repeticiones && i < clavesInsertadas.size(); i++) {
            Report report = new Report();
            tabla.buscar(clavesInsertadas.get(i), report);
            totalComparaciones += report.getCantidadComparaciones();
            busquedas_realizadas++;
        }

        return busquedas_realizadas > 0 ? (double) totalComparaciones / busquedas_realizadas : 0.0;
    }

    private double medirBusquedaFallidas(THashLineal<String, String> tabla, 
                                         List<String> todasLasClaves, 
                                         int cantidadInsertada) {
        long totalComparaciones = 0;
        int busquedas_realizadas = 0;

        Set<String> clavesInsertadas = new HashSet<>();
        for (String clave : tabla.keys()) {
            clavesInsertadas.add(clave);
        }

        int repeticiones = Math.min(INTENTOS_BUSQUEDA_POR_FACTOR, todasLasClaves.size() - cantidadInsertada);
        
        for (int i = 0; i < todasLasClaves.size() && busquedas_realizadas < repeticiones; i++) {
            String clave = todasLasClaves.get(i);
            if (!clavesInsertadas.contains(clave)) {
                Report report = new Report();
                tabla.buscar(clave, report);
                totalComparaciones += report.getCantidadComparaciones();
                busquedas_realizadas++;
            }
        }

        if (busquedas_realizadas < repeticiones) {
            Random random = new Random(42);
            for (int i = 0; i < repeticiones - busquedas_realizadas; i++) {
                String clave = "no_existe_" + random.nextInt(100000);
                if (!clavesInsertadas.contains(clave)) {
                    Report report = new Report();
                    tabla.buscar(clave, report);
                    totalComparaciones += report.getCantidadComparaciones();
                    busquedas_realizadas++;
                }
            }
        }

        return busquedas_realizadas > 0 ? (double) totalComparaciones / busquedas_realizadas : 0.0;
    }

    public static void main(String[] args) {
        Ejercicio3Hash evaluador = new Ejercicio3Hash();
        evaluador.evaluarRendimiento();
    }
}



/*

RESULTADO OBTENIDO:

=====================================
EVALUACIÓN DE RENDIMIENTO - TABLA HASH
=====================================

Factor     | Inserción       | Búsqueda Exitosa | Búsqueda Fallida 
---------|-----------------|------------------|------------------
      70% |          2,2941 |            2,2941 |            6,1960
      75% |          2,6294 |            2,6140 |            7,6061
      80% |          3,1590 |            3,1030 |           11,2072
      85% |          3,5580 |            3,6510 |           34,8306
      90% |          3,7966 |            2,5620 |            6,7899
      91% |          3,8425 |            2,6080 |            7,0407
      92% |          3,8810 |            2,6650 |            7,1457
      93% |          3,9138 |            2,6950 |            7,2857
      94% |          3,9507 |            2,7610 |            7,5659
      95% |          4,0000 |            2,8240 |            7,6243
      96% |          4,0291 |            2,8390 |            7,7738
      97% |          4,0894 |            2,9200 |            7,8603
      98% |          4,1238 |            2,9490 |            7,9640
      99% |          4,1745 |            2,9950 |            8,0919

=====================================


*/
