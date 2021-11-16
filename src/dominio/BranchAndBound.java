package dominio;

import dominio.aplicaciones.ColaPrioridadTDA;
import dominio.aplicaciones.GrafoTDA;
import dominio.implementaciones.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BranchAndBound {


    public static List<Integer> ViajeroPodaRamificacion(List<Camino>[][] caminoList, GrafoTDA simple, List<Horario> horarios){
        ColaPrioridadTDA NodosVivos = new ColaPrioridad();
        NodosVivos.inicializarCola();
        NodoArbol o = GenerarRaiz();
        NodosVivos.acolarPrioridad(o,0);
        Double cota = Double.MAX_VALUE;
        NodoArbol MejorSolucion = null;
        while (!NodosVivos.colaVacia()){
            NodoArbol nodo = NodosVivos.primero();
            Double cotaPrimero = NodosVivos.prioridad();
            NodosVivos.desacolar();
            if (!Podar(nodo,cotaPrimero,cota)){
                List<NodoArbol> hijos = GenerarHijos(caminoList,nodo,NodosVivos,horarios);
                for(NodoArbol hijo: hijos){
                    Double p = CotaInferior();
                    if(!Podar(hijo,p,cota)){
                        if(esSolucion(hijo,horarios)){
                            NodoArbol solucion = Solucion(hijo,caminoList);
                            if(solucion.getKilometros()<cota){
                                cota = solucion.getKilometros();
                                MejorSolucion = solucion;
                            }
                        }
                        else{
                            NodosVivos.acolarPrioridad(hijo,p);
                        }
                    }
                }
            }
        }
        System.out.println(MejorSolucion.getKilometros());
        return MejorSolucion.getVisitados();

    }

    private static boolean Podar(NodoArbol n, Double cotainferior, Double cota){
        return false;
    }

    private static Double CotaInferior(){
        return 0.0;
    }

    private static boolean esSolucion(NodoArbol n, List<Horario> horarios){
        boolean flag = false;
        if (n.getVisitados().size()==horarios.size()){
            flag = true;
        }
        return flag;
    }

    private static NodoArbol Solucion(NodoArbol n, List<Camino>[][] caminoList){
        int i = n.getVisitados().get(n.getVisitados().size()-1);
        Double min = Double.MAX_VALUE;
        for (Camino camino: caminoList[i][0]){
            if (camino.getKms()<min){
                min = camino.getKms();
            }
        }
        Double kilometros = min+n.getKilometros();
        LocalTime hora = LocalTime.of(23,0);
        List<Integer> visitados = n.getVisitados();
        visitados.add(0);
        NodoArbol a = new NodoArbol(hora,kilometros,visitados);
        return a;
    }

    private static List<NodoArbol> GenerarHijos(List<Camino>[][] caminoList,NodoArbol origen,ColaPrioridadTDA NodosVivos, List<Horario> horarios){
        List<NodoArbol> hijos = new ArrayList<>();
        List<Integer> visitadosOrigen = origen.getVisitados();
        int i = origen.getVisitados().get(origen.getVisitados().size()-1);
        for (int j= 0; j< horarios.size(); j++){
            if (!origen.getVisitados().contains(j)){
                for (Camino camino: caminoList[i][j]){
                    Double kilometros = origen.getKilometros()+camino.getKms();
                    LocalTime hora = origen.getHora().plusMinutes(camino.getMinutos());
                    List<Integer> visitados = new ArrayList<>();
                    visitados.addAll(visitadosOrigen);
                    if(!visitados.contains(j))
                        visitados.add(j);
                    NodoArbol hijo = new NodoArbol(hora,kilometros,visitados);
                    hijos.add(hijo);
                }
            }
        }
        return hijos;
    }

    private static NodoArbol GenerarRaiz(){
        LocalTime hora = LocalTime.of(7,0);
        Double kilometros = 0.0;
        List<Integer> visitados = new ArrayList<>();
        visitados.add(0);
        NodoArbol origen = new NodoArbol(hora,kilometros,visitados);
        return origen;
    }

}
