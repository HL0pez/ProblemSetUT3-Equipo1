package ucu.edu.aed.Ejercicios.Ejercicio16;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        NodoPersona elena = new NodoPersona(new Persona("Elena", 1940));

        NodoPersona carlos = new NodoPersona(new Persona("Carlos", 1965));
        NodoPersona marta = new NodoPersona(new Persona("Marta", 1968));
        NodoPersona luis = new NodoPersona(new Persona("Luis", 1970));

        NodoPersona ana = new NodoPersona(new Persona("Ana", 1990));
        NodoPersona pedro = new NodoPersona(new Persona("Pedro", 1992));
        NodoPersona lucia = new NodoPersona(new Persona("Lucia", 1995));
        NodoPersona diego = new NodoPersona(new Persona("Diego", 1998));

        NodoPersona sofia = new NodoPersona(new Persona("Sofia", 2015));
        NodoPersona mateo = new NodoPersona(new Persona("Mateo", 2018));

        // Generación 1
        elena.agregarHijo(carlos);
        elena.agregarHijo(marta);
        elena.agregarHijo(luis);

        // Generación 2
        carlos.agregarHijo(ana);
        carlos.agregarHijo(pedro);

        marta.agregarHijo(lucia);

        luis.agregarHijo(diego);

        // Generación 3
        ana.agregarHijo(sofia);
        lucia.agregarHijo(mateo);

        ArbolGenealogico arbol = new ArbolGenealogico(elena);

        System.out.println("Cantidad total de personas:");
        System.out.println(arbol.contarPersonas());

        System.out.println("\nAltura del árbol:");
        System.out.println(arbol.calcularAltura());

        System.out.println("\nDescendientes de Elena:");
        imprimirLista(arbol.listarDescendientes("Elena"));

        System.out.println("\nDescendientes de Carlos:");
        imprimirLista(arbol.listarDescendientes("Carlos"));

        System.out.println("\nPersonas de la generación 0:");
        imprimirLista(arbol.obtenerPersonasDeGeneracion(0));

        System.out.println("\nPersonas de la generación 1:");
        imprimirLista(arbol.obtenerPersonasDeGeneracion(1));

        System.out.println("\nPersonas de la generación 2:");
        imprimirLista(arbol.obtenerPersonasDeGeneracion(2));

        System.out.println("\nPersonas de la generación 3:");
        imprimirLista(arbol.obtenerPersonasDeGeneracion(3));

        System.out.println("\nAncestro común más cercano entre Sofia y Pedro:");
        System.out.println(arbol.ancestroComunMasCercano("Sofia", "Pedro"));

        System.out.println("\nAncestro común más cercano entre Sofia y Mateo:");
        System.out.println(arbol.ancestroComunMasCercano("Sofia", "Mateo"));

        System.out.println("\n¿Sofia es descendiente de Carlos?");
        System.out.println(arbol.esDescendiente("Sofia", "Carlos"));

        System.out.println("\n¿Lucia es descendiente de Luis?");
        System.out.println(arbol.esDescendiente("Lucia", "Luis"));

        System.out.println("\n¿Diego es descendiente de Elena?");
        System.out.println(arbol.esDescendiente("Diego", "Elena"));
    }

    private static void imprimirLista(List<Persona> personas) {
        if (personas.isEmpty()) {
            System.out.println("No hay personas para mostrar.");
            return;
        }

        for (Persona persona : personas) {
            System.out.println(persona);
        }
    }
}