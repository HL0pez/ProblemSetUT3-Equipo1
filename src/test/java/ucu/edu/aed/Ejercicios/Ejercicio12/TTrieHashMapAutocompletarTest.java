package ucu.edu.aed.Ejercicios.Ejercicio12;

import java.util.List;
import java.util.stream.Collectors;

import junit.framework.TestCase;
import ucu.edu.aed.tda.trie.Entry;
import ucu.edu.aed.tda.trie.TTrieHashMap;

public class TTrieHashMapAutocompletarTest extends TestCase {

    private TTrieHashMap<String> trie;

    @Override
    protected void setUp() {
        trie = new TTrieHashMap<>();
        trie.insertar("casa", "casa");
        trie.insertar("casada", "casada");
        trie.insertar("cazar", "cazar");
        trie.insertar("perro", "perro");
        trie.insertar("pera", "pera");
        trie.insertar("programa", "programa");
        trie.insertar("programar", "programar");
        trie.insertar("programacion", "programacion");
    }

    public void testPredecirConPrefijoExistente() {
        List<Entry<String>> resultado = trie.predecir("cas");
        assertEquals(2, resultado.size());
    }

    public void testPredecirConPrefijoNoExistente() {
        List<Entry<String>> resultado = trie.predecir("xyz");
        assertTrue(resultado.isEmpty());
    }

    public void testPredecirConPrefijoCompletoDevuelvePalabraYDerivadas() {
        List<Entry<String>> resultado = trie.predecir("programa");
        assertEquals(3, resultado.size());
    }

    public void testPredecirConPrefijoPrograma() {
        List<Entry<String>> resultado = trie.predecir("programa");
        List<String> palabras = resultado.stream().map(Entry::getPalabra).collect(Collectors.toList());
        assertTrue(palabras.contains("programa"));
        assertTrue(palabras.contains("programar"));
        assertTrue(palabras.contains("programacion"));
    }
    
    public void testPredecirConPrefijoVacioDevuelveTodo() {
        List<Entry<String>> resultado = trie.predecir("");
        assertEquals(8, resultado.size());
    }



}