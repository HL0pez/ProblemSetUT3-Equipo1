package ucu.edu.aed.tda.generic_trie;

import java.util.function.Consumer;

public class ArbolGenerico<T> implements TArbolGenerico {

    protected TNodoGenerico raiz;

    public ArbolGenerico() {
        super();
    }

    @Override
    public boolean agregarHijo(Comparable padre, Comparable hijo) {
        if (raiz == null) {
            return false;
        }

        return raiz.agregarHijo(padre, hijo);
    }

    @Override
    public void eliminar(Comparable criterio) {
        if (raiz != null) {
            eliminar(criterio);
        }

    }

    @Override
    public Comparable obtenerPadre(Comparable criterio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPadre'");
    }

    @Override
    public Comparable buscar(Comparable criterio) {
        if (raiz == null) {
            return null;
        }
        return raiz.buscar(criterio).getDato();
    }

    @Override
    public void preOrden(Consumer consumidor) {
        if (raiz != null) {
            raiz.preOrden(consumidor);
        }
    }

    @Override
    public void inOrden(Consumer consumidor) {
      if(raiz != null) {
          raiz.inOrden(consumidor);
        }

    @Override
    public void postOrden(Consumer consumidor) {
        if (raiz != null) {
            raiz.postOrden(consumidor);
        }
    }

    @Override
    public void vaciar() {
        this.raiz = null;
    }

    @Override
    public int grado(Comparable nodo) {
        if (raiz == null) {
            return 0;
        } else {
            return raiz.grado();
        }

    }

    @Override
    public int altura(Comparable nodo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'altura'");
    }

}
