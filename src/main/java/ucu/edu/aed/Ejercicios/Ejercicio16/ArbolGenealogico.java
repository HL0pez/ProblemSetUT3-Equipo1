package ucu.edu.aed.Ejercicios.Ejercicio16;

import java.util.ArrayList;
import java.util.List;

public class ArbolGenealogico {

    private NodoPersona raiz;

    public ArbolGenealogico(NodoPersona raiz) {
        this.raiz = raiz;
    }

    public NodoPersona getRaiz() {
        return raiz;
    }


    public List<Persona> listarDescendientes(String nombrePersona) {
        List<Persona> descendientes = new ArrayList<>();

        NodoPersona nodo = buscarNodo(nombrePersona);

        if (nodo == null) {
            return descendientes;
        }

        listarDescendientesRec(nodo, descendientes);

        return descendientes;
    }

    private void listarDescendientesRec(NodoPersona nodo, List<Persona> descendientes) {
        for (NodoPersona hijo : nodo.getHijos()) {
            descendientes.add(hijo.getPersona());
            listarDescendientesRec(hijo, descendientes);
        }
    }


    public int calcularAltura() {
        return calcularAlturaRec(raiz);
    }

    private int calcularAlturaRec(NodoPersona nodo) {
        if (nodo == null) {
            return -1;
        }

        if (nodo.esHoja()) {
            return 0;
        }

        int alturaMaxima = 0;

        for (NodoPersona hijo : nodo.getHijos()) {
            int alturaHijo = calcularAlturaRec(hijo);

            if (alturaHijo > alturaMaxima) {
                alturaMaxima = alturaHijo;
            }
        }

        return alturaMaxima + 1;
    }


    public int contarPersonas() {
        return contarPersonasRec(raiz);
    }

    private int contarPersonasRec(NodoPersona nodo) {
        if (nodo == null) {
            return 0;
        }

        int total = 1;

        for (NodoPersona hijo : nodo.getHijos()) {
            total += contarPersonasRec(hijo);
        }

        return total;
    }


    public List<Persona> obtenerPersonasDeGeneracion(int generacionBuscada) {
        List<Persona> personas = new ArrayList<>();

        if (generacionBuscada < 0) {
            return personas;
        }

        obtenerPersonasDeGeneracionRec(raiz, 0, generacionBuscada, personas);

        return personas;
    }

    private void obtenerPersonasDeGeneracionRec(NodoPersona nodo, int generacionActual, int generacionBuscada, List<Persona> personas) {
        
        if (nodo == null) {
            return;
        }

        if (generacionActual == generacionBuscada) {
            personas.add(nodo.getPersona());
            return;
        }

        for (NodoPersona hijo : nodo.getHijos()) {
            obtenerPersonasDeGeneracionRec(
                    hijo,
                    generacionActual + 1,
                    generacionBuscada,
                    personas
            );
        }
    }


    public Persona ancestroComunMasCercano(String nombre1, String nombre2) {
        if (nombre1 == null || nombre2 == null) {
            return null;
        }

        if (!existePersona(nombre1) || !existePersona(nombre2)) {
            return null;
        }

        NodoPersona ancestro = ancestroComunMasCercanoRec(raiz, nombre1, nombre2);

        if (ancestro == null) {
            return null;
        }

        return ancestro.getPersona();
    }

    private NodoPersona ancestroComunMasCercanoRec(NodoPersona nodo, String nombre1, String nombre2) {
        
        if (nodo == null) {
            return null;
        }

        String nombreActual = nodo.getPersona().getNombre();

        if (nombreActual.equalsIgnoreCase(nombre1)
                || nombreActual.equalsIgnoreCase(nombre2)) {
            return nodo;
        }

        int cantidadCoincidencias = 0;
        NodoPersona posibleAncestro = null;

        for (NodoPersona hijo : nodo.getHijos()) {
            NodoPersona resultado = ancestroComunMasCercanoRec(hijo, nombre1, nombre2);

            if (resultado != null) {
                cantidadCoincidencias++;
                posibleAncestro = resultado;
            }
        }

        if (cantidadCoincidencias >= 2) {
            return nodo;
        }

        return posibleAncestro;
    }


    public boolean esDescendiente(String nombreDescendiente, String nombreAncestro) {
        
        NodoPersona nodoAncestro = buscarNodo(nombreAncestro);

        if (nodoAncestro == null) {
            return false;
        }

        return esDescendienteRec(nodoAncestro, nombreDescendiente);
    }

    private boolean esDescendienteRec(NodoPersona nodo, String nombreDescendiente) {
        if (nodo == null) {
            return false;
        }

        for (NodoPersona hijo : nodo.getHijos()) {
            if (hijo.getPersona().getNombre().equalsIgnoreCase(nombreDescendiente)) {
                return true;
            }

            if (esDescendienteRec(hijo, nombreDescendiente)) {
                return true;
            }
        }

        return false;
    }


    public boolean existePersona(String nombre) {
        return buscarNodo(nombre) != null;
    }

    public NodoPersona buscarNodo(String nombre) {
        return buscarNodoRec(raiz, nombre);
    }

    private NodoPersona buscarNodoRec(NodoPersona nodo, String nombre) {
       
        if (nodo == null || nombre == null) {
            return null;
        }

        if (nodo.getPersona().getNombre().equals(nombre)) {
            return nodo;
        }

        for (NodoPersona hijo : nodo.getHijos()) {
            NodoPersona encontrado = buscarNodoRec(hijo, nombre);

            if (encontrado != null) {
                return encontrado;
            }
        }

        return null;
    }
}