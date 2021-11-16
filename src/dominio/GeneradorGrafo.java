package dominio;

import dominio.aplicaciones.ConjuntoTDA;
import dominio.aplicaciones.GrafoTDA;
import dominio.implementaciones.Camino;
import dominio.implementaciones.Conjunto;
import dominio.implementaciones.Grafo;

import java.util.List;

public class GeneradorGrafo {
    public static GrafoTDA generarGrafo(List<Camino>[][] caminos, List<String> lugares){
        GrafoTDA grafo = new Grafo();
        grafo.inicializarGrafo();
        int m = 0;
        for(int i = 0; i < lugares.size();i++){
            grafo.agregarVertice(m);
            m++;
        }
        for (int i = 0; i < lugares.size();i++){
            for (int j = 0; j < lugares.size();j++){
                if(i!=j){
                    List<Camino> caminos1 = caminos[i][j];
                    double min = Double.MAX_VALUE;
                    for (Camino camino: caminos1){
                        if (camino.getKms() < min){
                            min = camino.getKms();
                        }
                    }
                    grafo.agregarArista(i,j,min);
                }
            }
        }
        return grafo;
    }

    public static GrafoTDA copiarGrafo(GrafoTDA grafo, List<Integer> visitados){
        GrafoTDA grafoCopia = new Grafo();
        grafoCopia.inicializarGrafo();
        ConjuntoTDA conjunto = grafo.vertices();
        while (!conjunto.conjuntoVacio()){
            int valor = conjunto.obtener();
            grafoCopia.agregarVertice(valor);
            conjunto.sacar(valor);
        }
        visitados.forEach(grafoCopia::eliminarVertice);
        conjunto = grafoCopia.vertices();
        ConjuntoTDA conjunto1 = grafoCopia.vertices();
        ConjuntoTDA aux = new Conjunto();
        aux.inicializarConjunto();
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
                    grafoCopia.agregarArista(o, d, p);
                }
                if (grafo.existeArista(d, o)) {
                    double p = grafo.peso(d, o);
                    grafoCopia.agregarArista(d, o, p);
                }
            }
            while(!aux.conjuntoVacio()) {
                int x = aux.obtener();
                conjunto1.agregar(x);
                aux.sacar(x);
            }
        }
        return grafoCopia;
    }
}
