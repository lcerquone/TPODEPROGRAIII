package dominio.implementaciones;

import dominio.aplicaciones.ColaPrioridadKTDA;

public class ColaPrioridadKruskal implements ColaPrioridadKTDA {

    private class Nodo{
        double p;
        Edge x;
        Nodo sig;
    }
    private Nodo inicio;

    @Override
    public void acolarPrioridad(Edge x, double p) {
        Nodo n = new Nodo();
        n.x = x;
        n.p = p;
        if (inicio == null || p < inicio.p) {
            n.sig = inicio;
            inicio = n;
        } else {
            Nodo actual = inicio, anterior = null;
            while(actual!= null && p >= actual.p) {
                anterior = actual;
                actual = actual.sig;
            }
            n.sig = actual;
            anterior.sig = n;
        }

    }

    @Override
    public void desacolar() {
        inicio = inicio.sig;
    }

    @Override
    public Edge primero() {
        return inicio.x;
    }

    @Override
    public boolean colaVacia() {
        return inicio == null;
    }

    @Override
    public double prioridad() {
        return inicio.p;
    }

    @Override
    public void inicializarCola() {
        inicio = null;
    }
}
