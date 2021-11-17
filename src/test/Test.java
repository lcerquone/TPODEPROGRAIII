package test;

import dominio.BranchAndBound;
import dominio.CargadorMatriz;
import dominio.GeneradorGrafo;
import dominio.Lector;
import dominio.aplicaciones.GrafoTDA;
import dominio.implementaciones.Camino;
import dominio.implementaciones.Horario;
import dominio.implementaciones.Informacion;
import dominio.implementaciones.Kruskal;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Horario> h = Lector.leerArchivoDatosClientes();
        List<String> j = new ArrayList<>();
        h.forEach(horario -> j.add(horario.getLugar()));
        List<Informacion> c = Lector.leerArchivoCaminos();
        List<Camino>[][] caminos = CargadorMatriz.cargarMatriz(j,c);
        GrafoTDA grafo = GeneradorGrafo.generarGrafo(caminos,j);
        List<Integer> visitados = new ArrayList<>();
        visitados.add(0);
        List<Integer> a = BranchAndBound.ViajeroPodaRamificacion(caminos,grafo,h);
        a.forEach(System.out::println);
        GeneradorGrafo.copiarGrafo(grafo,visitados);
        GrafoTDA grafoR = Kruskal.caminoMinimo(GeneradorGrafo.copiarGrafo(grafo,visitados));
        System.out.println(Kruskal.valor(grafoR));

    }
}
