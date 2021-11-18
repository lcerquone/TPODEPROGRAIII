package dominio;

import dominio.aplicaciones.GrafoTDA;
import dominio.implementaciones.Camino;
import dominio.implementaciones.Horario;
import dominio.implementaciones.Informacion;
import dominio.implementaciones.NodoArbol;

import java.util.ArrayList;
import java.util.List;

public class Iniciador {
    public static void MinimoCamino(){
        List<Horario> h = Lector.leerArchivoDatosClientes();
        List<String> j = new ArrayList<>();
        h.forEach(horario -> j.add(horario.getLugar()));
        List<Informacion> c = Lector.leerArchivoCaminos();
        List<Camino>[][] caminos = CargadorMatriz.cargarMatriz(j,c);
        GrafoTDA grafo = GeneradorGrafo.generarGrafo(caminos,j);
        NodoArbol a = BranchAndBound.ViajeroPodaRamificacion(caminos,grafo,h);
        System.out.println("Km: " + a.getKilometros());
        for(Integer i : a.getVisitados()){
            String f = j.get(i);
            System.out.print("->"+f);
        }
    }
}
