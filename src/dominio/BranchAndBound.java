package dominio;

import dominio.aplicaciones.ColaPrioridadTDA;
import dominio.aplicaciones.ConjuntoTDA;
import dominio.aplicaciones.GrafoTDA;
import dominio.implementaciones.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class BranchAndBound {


    public static NodoArbol ViajeroPodaRamificacion(List<Camino>[][] caminoList, GrafoTDA simple, List<Horario> horarios){
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
            if (!Podar(nodo,cotaPrimero,cota,horarios)){
                List<NodoArbol> hijos = GenerarHijos(caminoList,nodo,NodosVivos,horarios);
                for(NodoArbol hijo: hijos){
                    Double p = CotaInferior(hijo,simple);
                    if(!Podar(hijo,p,cota,horarios)){
                        if(esSolucion(hijo,horarios)){
                            NodoArbol solucion = Solucion(hijo,caminoList);
                            if(solucion.getKilometros()<cota){
                                cota = solucion.getKilometros();
                                MejorSolucion = solucion;
                            }
                        }
                        else{
                            if (p!=0.0)NodosVivos.acolarPrioridad(hijo,p);
                        }
                    }
                }
            }
        }
        return MejorSolucion;

    }

    private static boolean Podar(NodoArbol n, Double cotainferior, Double cota, List<Horario> listahorario){
        boolean podar = false;
        if (cota!=Double.MAX_VALUE) {
            if (cotainferior >= cota) podar = true;
        }
        if (n.getVisitados().size()!=1) {
            Horario horariolocal = listahorario.get(n.getVisitados().get(n.getVisitados().size() - 1));
            LocalTime horarioInicio = horariolocal.getFechaInicio();
            LocalTime horarioCierre = horariolocal.getFechaFin();
            if (n.getHora().isBefore(horarioInicio) || n.getHora().isAfter(horarioCierre)) {
                podar = true;
            }
            LocalTime horarioMinimo = LocalTime.MAX;
            for (Horario horario : listahorario) {
                if (horario.getFechaFin().compareTo(LocalTime.MIN)==1 && horario.getFechaFin().isBefore(horarioMinimo)) {
                    horarioMinimo = horario.getFechaFin();
                }
            }
            //if (n.getHora().isAfter(horarioMinimo)) podar = true;

        }
        return podar;
    }

    private static Double CotaInferior(NodoArbol hijo, GrafoTDA simple){
        GrafoTDA auxiliar1 = CopiarGrafo(simple);
        GrafoTDA auxiliar2 = CopiarGrafo(simple);
        int destino = hijo.getVisitados().get(hijo.getVisitados().size()-1);
        for (int i = 0;i < hijo.getVisitados().size();i++){
            if (hijo.getVisitados().get(i)!=destino) auxiliar1.eliminarVertice(hijo.getVisitados().get(i));
        }
        Double pesoDestino = Double.MAX_VALUE;
        ConjuntoTDA adyacentes = auxiliar1.adyacentes(destino);
        if (adyacentes.conjuntoVacio()) pesoDestino = 0.0;
        while (!adyacentes.conjuntoVacio()){
            int adyacente = adyacentes.obtener();
            adyacentes.sacar(adyacente);
            if (simple.peso(destino,adyacente)<pesoDestino){
                pesoDestino = auxiliar1.peso(destino,adyacente);
            }
        }
        for (int i = 0;i < hijo.getVisitados().size();i++){
            if (hijo.getVisitados().get(i)!=0) auxiliar2.eliminarVertice(hijo.getVisitados().get(i));
        }
        Double pesoOrigen = Double.MAX_VALUE;
        adyacentes = auxiliar2.adyacentes(0);
        if (adyacentes.conjuntoVacio()) pesoOrigen = 0.0;
        while (!adyacentes.conjuntoVacio()){
            int adyacente = adyacentes.obtener();
            adyacentes.sacar(adyacente);
            if (simple.peso(0,adyacente)<pesoOrigen){
                pesoOrigen = auxiliar2.peso(0,adyacente);
            }
        }
        Double pesoKruskal = Kruskal.valor(GeneradorGrafo.copiarGrafo(simple,hijo.getVisitados()));
        return pesoOrigen + pesoDestino + pesoKruskal;
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
                    LocalTime hora = LocalTime.of(origen.getHora().getHour(),origen.getHora().getMinute());
                    hora = hora.plusMinutes(camino.getMinutos());
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

    private  static GrafoTDA CopiarGrafo(GrafoTDA grafo){
        GrafoTDA copia = new Grafo();
        copia.inicializarGrafo();
        ConjuntoTDA conjunto = grafo.vertices();
        while (!conjunto.conjuntoVacio()){
            int valor = conjunto.obtener();
            copia.agregarVertice(valor);
            conjunto.sacar(valor);
        }
        conjunto = copia.vertices();
        ConjuntoTDA conjunto1 = copia.vertices();
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
                    copia.agregarArista(o, d, p);
                }
                if (grafo.existeArista(d, o)) {
                    double p = grafo.peso(d, o);
                    copia.agregarArista(d, o, p);
                }
            }
            while(!aux.conjuntoVacio()) {
                int x = aux.obtener();
                conjunto1.agregar(x);
                aux.sacar(x);
            }
        }
        return copia;
    }

}
