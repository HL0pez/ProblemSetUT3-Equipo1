package ucu.edu.aed.Ejercicios.Ejercicio16;

import java.util.Objects;

public class Persona {

    private String nombre;
    private int anioNacimiento;

    public Persona(String nombre, int anioNacimiento) {
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    @Override
    public String toString() {
        return nombre + " (" + anioNacimiento + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Persona)) {
            return false;
        }

        Persona otra = (Persona) obj;

        return this.anioNacimiento == otra.anioNacimiento
                && this.nombre.equalsIgnoreCase(otra.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), anioNacimiento);
    }
}