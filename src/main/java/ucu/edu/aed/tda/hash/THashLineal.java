package ucu.edu.aed.tda.hash;

/**
 * Implementación concreta de THash usando direccionamiento lineal para resolver colisiones.
 * Esta clase está diseñada para la evaluación experimental del rendimiento de tablas hash.
 */
public class THashLineal<K, V> extends THash<K, V> {
    private int cantidadElementos = 0;
    private static final double FACTOR_DE_CARGA_MAXIMO = 0.85;

    public THashLineal(int elementosEsperados) {
        super(elementosEsperados);
    }

    @Override
    public V buscar(K clave, Report report) {
        if (clave == null) {
            return null;
        }

        int posicion = functionHashing(clave);
        int comparaciones = 0;

        // Búsqueda lineal a partir de la posición del hash
        for (int i = 0; i < hashTable.length; i++) {
            int indiceActual = (posicion + i) % hashTable.length;
            TNodoHash<K, V> nodo = hashTable[indiceActual];

            // Si encontramos un slot vacío, terminamos
            if (nodo == null) {
                comparaciones++;
                break;
            }

            // Comparamos la clave
            comparaciones++;
            if (nodo.getClave().equals(clave)) {
                report.setCantidadComparaciones(comparaciones);
                return nodo.getValor();
            }
        }

        report.setCantidadComparaciones(comparaciones);
        return null;
    }

    @Override
    public boolean insertar(K clave, V valor, Report report) {
        if (clave == null) {
            return false;
        }

        // Si la clave ya existe, no insertamos
        if (buscar(clave) != null) {
            report.setCantidadComparaciones(0);
            return false;
        }

        int posicion = functionHashing(clave);
        int comparaciones = 0;

        // Búsqueda lineal para encontrar un slot vacío
        for (int i = 0; i < hashTable.length; i++) {
            int indiceActual = (posicion + i) % hashTable.length;
            TNodoHash<K, V> nodo = hashTable[indiceActual];

            comparaciones++;
            if (nodo == null || nodo.isLoteLibre()) {
                // Insertamos aquí
                hashTable[indiceActual] = new TNodoHash<>(clave, valor);
                cantidadElementos++;
                report.setCantidadComparaciones(comparaciones);

                // Verificamos si necesitamos redimensionar
                if (redimensionar()) {
                    // Si se redimensionó, no contamos esas comparaciones adicionales
                }
                return true;
            }
        }

        report.setCantidadComparaciones(comparaciones);
        return false;
    }

    @Override
    public boolean delete(K clave, Report report) {
        if (clave == null) {
            return false;
        }

        int posicion = functionHashing(clave);
        int comparaciones = 0;

        for (int i = 0; i < hashTable.length; i++) {
            int indiceActual = (posicion + i) % hashTable.length;
            TNodoHash<K, V> nodo = hashTable[indiceActual];

            if (nodo == null) {
                comparaciones++;
                break;
            }

            comparaciones++;
            if (nodo.getClave().equals(clave)) {
                nodo.setLoteLibre(true);
                cantidadElementos--;
                report.setCantidadComparaciones(comparaciones);
                return true;
            }
        }

        report.setCantidadComparaciones(comparaciones);
        return false;
    }

    @Override
    protected int functionHashing(K valor) {
        if (valor == null) {
            return 0;
        }
        return Math.abs(valor.hashCode()) % hashTable.length;
    }

    @Override
    public boolean esVacio() {
        return cantidadElementos == 0;
    }

    @Override
    public void vaciar() {
        hashTable = new TNodoHash[hashTable.length];
        cantidadElementos = 0;
    }

    @Override
    protected int calcularCapacidadOptima(int elementosEsperados) {
        // Buscar el siguiente número primo aproximado a elementosEsperados / 0.75
        int capacidad = (int) (elementosEsperados / 0.75);
        return siguientePrimo(capacidad);
    }

    @Override
    protected boolean redimensionar() {
        double factorDeCarga = (double) cantidadElementos / hashTable.length;
        if (factorDeCarga > FACTOR_DE_CARGA_MAXIMO) {
            redimensionarTabla();
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void redimensionarTabla() {
        TNodoHash<K, V>[] tablaAntigua = hashTable;
        hashTable = new TNodoHash[siguientePrimo(tablaAntigua.length * 2)];
        cantidadElementos = 0;

        // Reinsertamos todos los elementos
        for (TNodoHash<K, V> nodo : tablaAntigua) {
            if (nodo != null && !nodo.isLoteLibre()) {
                insertar(nodo.getClave(), nodo.getValor());
            }
        }
    }

    private int siguientePrimo(int num) {
        if (num <= 2) return 2;
        if (num % 2 == 0) num++;

        for (; !esPrimo(num); num += 2) {
            // Continuamos hasta encontrar un primo
        }
        return num;
    }

    private boolean esPrimo(int num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;

        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterable<Entry<K, V>> entries() {
        java.util.List<Entry<K, V>> lista = new java.util.ArrayList<>();
        for (TNodoHash<K, V> nodo : hashTable) {
            if (nodo != null && !nodo.isLoteLibre()) {
                lista.add(nodo.getEntry());
            }
        }
        return lista;
    }

    @Override
    public Iterable<K> keys() {
        java.util.List<K> lista = new java.util.ArrayList<>();
        for (TNodoHash<K, V> nodo : hashTable) {
            if (nodo != null && !nodo.isLoteLibre()) {
                lista.add(nodo.getClave());
            }
        }
        return lista;
    }

    @Override
    public Iterable<V> values() {
        java.util.List<V> lista = new java.util.ArrayList<>();
        for (TNodoHash<K, V> nodo : hashTable) {
            if (nodo != null && !nodo.isLoteLibre()) {
                lista.add(nodo.getValor());
            }
        }
        return lista;
    }

    /**
     * Retorna la cantidad actual de elementos en la tabla hash
     */
    public int getCantidadElementos() {
        return cantidadElementos;
    }

    /**
     * Retorna la capacidad actual de la tabla hash
     */
    public int getCapacidad() {
        return hashTable.length;
    }

    /**
     * Retorna el factor de carga actual
     */
    public double getFactorDeCarga() {
        return (double) cantidadElementos / hashTable.length;
    }
}
