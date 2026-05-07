package ucu.edu.aed.tda.generic_trie;

import java.util.function.Consumer;

public class NodoGenerico implements TNodoGenerico {

    private Comparable dato;
    private TNodoGenerico padre;
    private TNodoGenerico hijoIzquierdo;
    private TNodoGenerico hermanoDerecho;

    public NodoGenerico(Comparable dato) {
        this.dato = dato;
        this.padre = null;
        this.hijoIzquierdo = null;
        this.hermanoDerecho = null;
    }

    @Override
    public Comparable getDato() {
        return this.dato;
    }

    public TNodoGenerico getPadre() {
        return this.padre;
    }

    public TNodoGenerico getHijoIzquierdo() {
        return this.hijoIzquierdo;
    }

    public TNodoGenerico getHermanoDerecho(TNodoGenerico hijo) {
        return this.hermanoDerecho;
    }

    public void setPadre(TNodoGenerico padre) {
        this.padre = padre;
    }

    public void setHijoIzquierdo(TNodoGenerico hijo) {
        this.hijoIzquierdo = hijo;
    }

    public void setHermanoDerecho(TNodoGenerico hermano) {
        this.hermanoDerecho = hermano;
    }

    @Override
    public boolean agregarHijo(Comparable padre, Comparable hijo) {
        if (hijo == null) {
            return false;
        }
        return true;

    }

    @Override
    public TNodoGenerico eliminar(Comparable criterio) {
        TNodoGenerico hijo = this.hijoIzquierdo;
        TNodoGenerico hermanoAnterior = null;

        while (hijo != null) {
            if (hijo.getDato().equals(criterio)) {

                if (hermanoAnterior == null) {
                    this.hijoIzquierdo = getHermanoDerecho(hijo);
                } else {

                    TNodoGenerico hermanoDerecho = getHermanoDerecho(hijo);
                    hermanoAnterior.setHermanoDerecho(hermanoDerecho);

                }
                hijo.setHermanoDerecho(null);
                hijo.setPadre(null);

                return hijo;
            }
            TNodoGenerico eliminado = hijo.eliminar(criterio);
            if (eliminado != null) {
                return eliminado;
            }
            hermanoAnterior = hijo;
            hijo = getHermanoDerecho(hijo);

        }

        return null;

    }

    @Override
    public TNodoGenerico buscar(Comparable criterio) {
        if (this.dato.equals(criterio)) {
            return this;
        }
        TNodoGenerico hijo = this.hijoIzquierdo;

        while (hijo != null) {

            TNodoGenerico resultado = hijo.buscar(criterio);
            if (resultado != null) {
                return resultado;
            }
            hijo = getHermanoDerecho(hijo);
        }

        return null;
    }

    @Override
    public TNodoGenerico obtenerPadre(Comparable criterio) {
        return this.padre;
    }

    @Override
    public void preOrden(Consumer consumidor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'preOrden'");
    }

    @Override
    public void inOrden(java.util.function.Consumer consumidor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inOrden'");
    }

    @Override
    public void postOrden(Consumer consumidor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postOrden'");
    }

    @Override
    public int altura() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'altura'");
    }

    @Override
    public int grado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'grado'");
    }

    @Override
    public void vaciar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'vaciar'");
    }

    @Override
    public java.util.List obtenerHijos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerHijos'");
    }

    @Override
    public void setPadre(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPadre'");
    }

}
