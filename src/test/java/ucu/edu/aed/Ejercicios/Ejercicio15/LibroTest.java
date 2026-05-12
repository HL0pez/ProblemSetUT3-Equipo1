package ucu.edu.aed.Ejercicios.Ejercicio15;

import junit.framework.TestCase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LibroTest extends TestCase {

    public void testEqualsReflexivo() {
        Libro libro = new Libro("123", "Java", "Ana", 2020);

        assertEquals(libro, libro);
    }

    public void testEqualsSimetrico() {
        Libro a = new Libro("123", "Java", "Ana", 2020);
        Libro b = new Libro("123", "Java avanzado", "Ana", 2021);

        assertEquals(a, b);
        assertEquals(b, a);
    }

    public void testEqualsTransitivo() {
        Libro a = new Libro("123", "Java", "Ana", 2020);
        Libro b = new Libro("123", "Java avanzado", "Ana", 2021);
        Libro c = new Libro("123", "Java profesional", "Ana", 2022);

        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(a, c);
    }

    public void testEqualsConsistente() {
        Libro a = new Libro("123", "Java", "Ana", 2020);
        Libro b = new Libro("123", "Java avanzado", "Ana", 2021);

        assertEquals(a, b);
        assertEquals(a, b);
        assertEquals(a, b);
    }

    public void testLibroNoDebeSerIgualANull() {
        Libro libro = new Libro("123", "Java", "Ana", 2020);

        assertFalse(libro.equals(null));
    }

    public void testLibroNoDebeSerIgualAObjetoDeOtraClase() {
        Libro libro = new Libro("123", "Java", "Ana", 2020);

        assertFalse(libro.equals("123"));
    }

    public void testLibrosConMismoIsbnDebenSerIgualesAunqueTenganOtrosDatosDistintos() {
        Libro a = new Libro("123", "Java", "Ana", 2020);
        Libro b = new Libro("123", "Java avanzado", "Pedro", 2023);

        assertEquals(a, b);
    }

    public void testLibrosConDistintoIsbnNoDebenSerIguales() {
        Libro a = new Libro("123", "Java", "Ana", 2020);
        Libro b = new Libro("456", "Java", "Ana", 2020);

        assertFalse(a.equals(b));
    }

    public void testLibrosIgualesTienenMismoHashCode() {
        Libro a = new Libro("123", "Java", "Ana", 2020);
        Libro b = new Libro("123", "Java avanzado", "Pedro", 2023);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    public void testHashSetNoDebeAgregarDosLibrosConMismoIsbn() {
        Set<Libro> libros = new HashSet<>();

        Libro a = new Libro("123", "Java", "Ana", 2020);
        Libro b = new Libro("123", "Java avanzado", "Pedro", 2023);

        libros.add(a);
        libros.add(b);

        assertEquals(1, libros.size());
    }

    public void testHashSetDebeDetectarLibroEquivalenteConContains() {
        Set<Libro> libros = new HashSet<>();

        Libro a = new Libro("123", "Java", "Ana", 2020);
        libros.add(a);

        Libro buscado = new Libro("123", "Otro título", "Otro autor", 2030);

        assertTrue(libros.contains(buscado));
    }

    public void testHashSetDebePermitirLibrosConIsbnDistintos() {
        Set<Libro> libros = new HashSet<>();

        Libro a = new Libro("123", "Java", "Ana", 2020);
        Libro b = new Libro("456", "Java", "Ana", 2020);

        libros.add(a);
        libros.add(b);

        assertEquals(2, libros.size());
    }

    public void testHashMapDebeReemplazarValorCuandoLaClaveTieneMismoIsbn() {
        Map<Libro, String> mapa = new HashMap<>();

        Libro a = new Libro("123", "Java", "Ana", 2020);
        Libro b = new Libro("123", "Java avanzado", "Pedro", 2023);

        mapa.put(a, "Primer valor");
        mapa.put(b, "Segundo valor");

        assertEquals(1, mapa.size());
        assertEquals("Segundo valor", mapa.get(a));
        assertEquals("Segundo valor", mapa.get(b));
    }

    public void testHashMapDebeRecuperarValorUsandoObjetoEquivalente() {
        Map<Libro, String> mapa = new HashMap<>();

        Libro a = new Libro("123", "Java", "Ana", 2020);
        mapa.put(a, "Disponible");

        Libro claveEquivalente = new Libro("123", "Otro título", "Otro autor", 2030);

        assertEquals("Disponible", mapa.get(claveEquivalente));
    }
}