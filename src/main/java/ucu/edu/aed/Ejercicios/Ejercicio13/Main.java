package ucu.edu.aed.Ejercicios.Ejercicio13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Alumno a1 = new Alumno(1, "Juan Pérez", "juan@ucu.edu.uy");
        Alumno a2 = new Alumno(1, "Juan Pérez Modificado", "otro@ucu.edu.uy");
        Alumno a3 = new Alumno(2, "María García", "maria@ucu.edu.uy");

        System.out.println(" - equals");
        System.out.println("a1.equals(a2) [mismo id, datos distintos]: " + a1.equals(a2));
        System.out.println("a1.equals(a3) [id distinto]:               " + a1.equals(a3));
        System.out.println("a1.equals(a1) [reflexividad]:              " + a1.equals(a1));
        System.out.println("a2.equals(a1) [simetría]:                  " + a2.equals(a1));

        System.out.println("\n - hashCode");
        System.out.println("a1.hashCode(): " + a1.hashCode());
        System.out.println("a2.hashCode(): " + a2.hashCode());
        System.out.println("Iguales si equals es true: " + (a1.hashCode() == a2.hashCode()));
        System.out.println("a3.hashCode(): " + a3.hashCode());


        System.out.println("\n - HashSet");
        Set<Alumno> set = new HashSet<>();
        set.add(a1);
        set.add(a2);
        set.add(a3);
        System.out.println("Elementos en el set (esperado 2): " + set.size());

        System.out.println("\n - HashMap");
        Map<Alumno, String> mapa = new HashMap<>();
        mapa.put(a1, "Activo");
        mapa.put(a2, "Egresado");
        System.out.println("Entradas en el mapa (esperado 1): " + mapa.size());
        System.out.println("Valor con a1 como clave: " + mapa.get(a1));
        System.out.println("Valor con a2 como clave: " + mapa.get(a2));

        System.out.println("\n - hashCode de strings del enunciado");
        String[] strings = {"Hola", "HolaMundo", "HashMap", "Colecciones"};
        int capacidad = 16;
        for (String s : strings) {
            int hash = s.hashCode();
            int bucket = (capacidad - 1) & hash;
            System.out.printf("%-15s -> hashCode: %12d  bucket (cap=16): %2d%n", s, hash, bucket);
        }
    }
}