package dominio.implementaciones;

import dominio.aplicaciones.ConjuntoTDA;

public class Conjunto implements ConjuntoTDA {
	class Nodo{
		int x;
		Nodo sig;
	}
	private Nodo inicio;

	@Override
	public void agregar(int x) {
		Nodo n = new Nodo();
		n.x = x;
		n.sig = inicio;
		inicio = n;
	}

	@Override
	public void sacar(int x) {
		if (inicio.x == x) {
			inicio = inicio.sig;
		} else {
			Nodo actual = inicio, anterior = null;
			while(actual.x != x) {
				anterior = actual;
				actual = actual.sig;
			}
			anterior.sig = actual.sig;	
		}
	}

	@Override
	public int obtener() {
		return inicio.x;
	}

	@Override
	public boolean conjuntoVacio() {
		return inicio == null;
	}

	@Override
	public boolean pertenece(int x) {
		Nodo actual = inicio;
		while (actual!= null && actual.x != x) {
			actual = actual.sig;
		}
		return actual!=null;
	}

	@Override
	public void inicializarConjunto() {
		inicio = null;

	}

}
