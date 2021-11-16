package dominio.implementaciones;
import dominio.aplicaciones.ColaPrioridadTDA;

public class ColaPrioridad implements ColaPrioridadTDA {
	private class Nodo{
		double p;
		NodoArbol x;
		Nodo sig;
	}
	private Nodo inicio;

	@Override
	public void acolarPrioridad(NodoArbol x, double p) {
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
	public NodoArbol primero() {
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
