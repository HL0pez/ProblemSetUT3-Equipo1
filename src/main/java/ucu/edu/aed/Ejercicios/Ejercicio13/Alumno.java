package ucu.edu.aed.Ejercicios.Ejercicio13;

import java.util.Objects;

public class Alumno {
    private int id;
    private String fullName;
    private String email;

    public Alumno(int id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Alumno otro = (Alumno) obj;
        return this.id == otro.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Alumno{id=" + id + ", fullName='" + fullName + "', email='" + email + "'}";
    }
}