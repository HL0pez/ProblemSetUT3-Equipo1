package ucu.edu.aed.Ejercicios.Ejercicio16;

import java.util.ArrayList;
import java.util.List;

public class NodoPersona {

    private Persona persona;
    private List<NodoPersona> hijos;

    public NodoPersona(Persona persona) {
        this.persona = persona;
        this.hijos = new ArrayList<>();
    }

    public Persona getPersona() {
        return persona;
    }

    public List<NodoPersona> getHijos() {
        return hijos;
    }

    public void agregarHijo(NodoPersona hijo) {
        if (hijo != null) {
            hijos.add(hijo);
        }
    }

    public boolean esHoja() {
        return hijos.isEmpty();
    }
}