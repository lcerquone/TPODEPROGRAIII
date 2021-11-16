package dominio.implementaciones;

import java.time.LocalTime;
import java.util.List;

public class NodoArbol {
    Double kilometros;
    LocalTime hora;
    List<Integer> visitados;

    public NodoArbol(LocalTime hora, Double kilometros, List<Integer> visitados) {
        this.hora = hora;
        this.kilometros = kilometros;
        this.visitados = visitados;
    }

    public Double getKilometros() {
        return kilometros;
    }

    public List<Integer> getVisitados() {
        return visitados;
    }

    public LocalTime getHora() {
        return hora;
    }
}
