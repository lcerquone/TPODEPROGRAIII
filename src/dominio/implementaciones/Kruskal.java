package dominio.implementaciones;

import dominio.aplicaciones.ColaPrioridadKTDA;
import dominio.aplicaciones.ConjuntoTDA;
import dominio.aplicaciones.GrafoTDA;

import java.util.*;

public class Kruskal {
    public static GrafoTDA caminoMinimo(GrafoTDA grafo) {
        GrafoTDA grafoR = new Grafo();
        grafoR.inicializarGrafo();
        ConjuntoTDA conjunto = grafo.vertices();
        Map<Integer,Integer> conjuto3 = new HashMap<>();
        int cantidadVertices = 0;
        int v = 0; // Número de nodos
        while(!conjunto.conjuntoVacio()){
            int valor = conjunto.obtener();
            grafoR.agregarVertice(valor);
            conjuto3.put(valor,valor);
            cantidadVertices++;
            conjunto.sacar(valor);
        }
        ColaPrioridadKTDA cola = new ColaPrioridadKruskal();
        ColaPrioridadKTDA aux = new ColaPrioridadKruskal();
        aux.inicializarCola();
        cola.inicializarCola();
        conjunto = grafo.vertices();
        ConjuntoTDA conjunto1 = grafo.vertices();
        ConjuntoTDA aux1 = new Conjunto();
        aux1.inicializarConjunto();
        while(!conjunto.conjuntoVacio()) {
            int o = conjunto.obtener();
            conjunto.sacar(o);
            conjunto1.sacar(o);
            while(!conjunto1.conjuntoVacio()) {
                int d = conjunto1.obtener();
                aux1.agregar(d);
                conjunto1.sacar(d);
                if (grafo.existeArista(o, d) && !existe(cola,arista(o,d,grafo))) {
                    cola.acolarPrioridad(arista(o,d,grafo),grafo.peso(o,d));
                }
            }
            while(!aux1.conjuntoVacio()) {
                int x = aux1.obtener();
                conjunto1.agregar(x);
                aux1.sacar(x);
            }
        }
        while (cantidadVertices > 1 && !cola.colaVacia()){
            Edge arista = cola.primero();
            cola.desacolar();
            if(conjuto3.get(arista.from) != conjuto3.get(arista.to)){
                cantidadVertices--;
                Set<Integer> integerSet = conjuto3.keySet();
                for(Integer e: integerSet){
                    if (conjuto3.get(e) == conjuto3.get(arista.to)){
                        conjuto3.replace(e,conjuto3.get(e));
                    }
                }
                grafoR.agregarArista(arista.from, arista.to, arista.data);
            }
        }
        return grafoR;
    }

    private static Edge arista(int o, int d, GrafoTDA grafo) {
        Edge p = new Edge();
        if(grafo.existeArista(o,d)) {
            p.from = o;
            p.to = d;
            p.data = grafo.peso(o, d);
        }
        return p;
    }

    private static boolean existe(ColaPrioridadKTDA cola, Edge arista) {
        boolean exist = false;
        ColaPrioridadKruskal copia = new ColaPrioridadKruskal();
        copia.inicializarCola();
        while (!cola.colaVacia()){
            copia.acolarPrioridad(cola.primero(), cola.prioridad());
            if(arista.from == cola.primero().from && arista.to == cola.primero().to && arista.data == cola.primero().data ){
                exist = true;
            }
            cola.desacolar();
        }
        while (!copia.colaVacia()){
            cola.acolarPrioridad(copia.primero(), copia.prioridad());
            copia.desacolar();
        }
        return exist;
    }

    public static double valor(GrafoTDA grafo){
        ConjuntoTDA conjunto = grafo.vertices();
        ConjuntoTDA conjunto1 = grafo.vertices();
        ConjuntoTDA aux = new Conjunto();
        aux.inicializarConjunto();
        double camino = 0.0;
        while(!conjunto.conjuntoVacio()) {
            int o = conjunto.obtener();
            conjunto.sacar(o);
            conjunto1.sacar(o);
            while(!conjunto1.conjuntoVacio()) {
                int d = conjunto1.obtener();
                aux.agregar(d);
                conjunto1.sacar(d);
                if (grafo.existeArista(o, d)) {
                    double p = grafo.peso(o, d);
                    camino += p;
                }
            }
            while(!aux.conjuntoVacio()) {
                int x = aux.obtener();
                conjunto1.agregar(x);
                aux.sacar(x);
            }
        }
        return camino;
    }
}
