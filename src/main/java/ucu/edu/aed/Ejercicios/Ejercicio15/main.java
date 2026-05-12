package ucu.edu.aed.Ejercicios.Ejercicio15;

import java.util.HashSet;
import java.util.Set;

public class main {
    public static void main(String[] args) {
        
        Libro libro1 = new Libro("53453","Quijote", "Miguel de Cervantes", 1605);

        Libro libro2 = new Libro("53453", "Quijote", "Miguel de Cervantes", 1605);

        Set<Libro> libros = new HashSet<>();

        libros.add(libro1);
        libros.add(libro2);

        System.out.println(libro1 == libro2);
        System.out.println(libro1.equals(libro2));
        System.out.println(libros.size());
    }
}
