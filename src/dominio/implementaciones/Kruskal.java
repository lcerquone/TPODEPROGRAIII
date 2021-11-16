package dominio.implementaciones;

import dominio.aplicaciones.ColaPrioridadKTDA;
import dominio.aplicaciones.ConjuntoTDA;
import dominio.aplicaciones.GrafoTDA;

import java.util.ArrayList;
import java.util.List;

public class Kruskal {
    public static GrafoTDA caminoMinimo(GrafoTDA grafo) {
        GrafoTDA grafoR = new Grafo();
        grafoR.inicializarGrafo();
        ConjuntoTDA conjunto = grafo.vertices();
        List<Integer> conjuntos = new ArrayList<>();
        int cantidadVertices = 0;
        int v = 0; // Número de nodos
        while(!conjunto.conjuntoVacio()){
            int valor = conjunto.obtener();
            grafoR.agregarVertice(valor);
            conjuntos.add(valor);
            cantidadVertices++;
            conjunto.sacar(valor);
        }
        ColaPrioridadKTDA cola = new ColaPrioridadKruskal();
        conjunto = grafo.vertices();
        ConjuntoTDA conjunto1 = grafo.vertices();
        while(!conjunto.conjuntoVacio()) {
            int o = conjunto.obtener();
            conjunto.sacar(o);
            conjunto1.sacar(o);
            while(!conjunto1.conjuntoVacio()) {
                int d = conjunto1.obtener();
                conjunto1.sacar(d);
                if (grafo.existeArista(o, d) && existe(cola,arista(o,d,grafo))) {
                    double p = grafo.peso(o, d);
                    cola.acolarPrioridad(arista(o,d,grafo),grafo.peso(o,d));
                }
            }
        }
        while (cantidadVertices > 1){
            Edge arista = cola.primero();
            cola.desacolar();
            if(conjuntos.get(arista.from) != conjuntos.get(arista.to)){
                cantidadVertices--;
                for(Integer e: conjuntos){
                    if (conjuntos.get(e) == conjuntos.get(arista.to)){
                        conjuntos.set(e,conjuntos.get(arista.from));
                    }
                }
                grafoR.agregarArista(arista.from, arista.to, arista.data);
            }
        }
        return grafoR;
    }

    private static Edge arista(int o, int d, GrafoTDA grafo) {
        return null;
    }

    private static boolean existe(ColaPrioridadKTDA cola, Edge arista) {
        return false;
    }
}
