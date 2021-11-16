package dominio.implementaciones;


import dominio.aplicaciones.ConjuntoTDA;
import dominio.aplicaciones.GrafoTDA;

public class Grafo implements GrafoTDA {
	
	class NodoV{
		int verticeOrigen;
		NodoV verticeSig;
		NodoA inicioArista;
	}
	
	class NodoA{
		double peso;
		NodoV verticeDestino;
		NodoA aristaSig;
	}
	
	private NodoV inicioVertice;

	@Override
	public void agregarVertice(int x) {
		NodoV n = new NodoV();
		n.verticeOrigen = x;
		n.verticeSig = inicioVertice;
		inicioVertice = n;
		n.inicioArista = null;

	}

	@Override
	public void eliminarVertice(int x) {
		NodoV anterior = null, actual = inicioVertice;
		while(actual!=null) {
			if(actual.verticeOrigen!=x && existeArista(actual.verticeOrigen, x)) {
				eliminarArista(actual.verticeOrigen, x);
			}
			actual = actual.verticeSig;
		}
		actual = inicioVertice;
		if (inicioVertice.verticeOrigen == x) {
			inicioVertice = inicioVertice.verticeSig;
		} else {
			while(actual.verticeOrigen != x) {
				anterior = actual;
				actual = actual.verticeSig;
			}
			anterior.verticeSig = actual.verticeSig;
		}	
	}

	@Override
	public void agregarArista(int o, int d, double p) {
		NodoV origen = inicioVertice;
		NodoV destino = inicioVertice;
		NodoA a = new NodoA();
		while(origen.verticeOrigen!=o) {
			origen = origen.verticeSig;
		}
		while(destino.verticeOrigen!=d) {
			destino = destino.verticeSig;
		}
		a.peso = p;
		a.aristaSig = origen.inicioArista;
		origen.inicioArista = a;
		a.verticeDestino = destino;	
	}

	@Override
	public void eliminarArista(int o, int d) {
		NodoV origen = inicioVertice;
		while(origen.verticeOrigen!=o) {
			origen = origen.verticeSig;
		}
		NodoA anterior = null, actual = origen.inicioArista;
		if (origen.inicioArista.verticeDestino.verticeOrigen == d) {
			origen.inicioArista = origen.inicioArista.aristaSig;
		} else {
			while(actual.verticeDestino.verticeOrigen != d) {
				anterior = actual;
				actual = actual.aristaSig;
			}
			anterior.aristaSig = actual.aristaSig;	
		}
	}

	@Override
	public double peso(int o, int d) {
		NodoV origen = inicioVertice;
		while(origen.verticeOrigen!=o) {
			origen = origen.verticeSig;
		}
		NodoA  actual = origen.inicioArista;
		while(actual.verticeDestino.verticeOrigen != d) {
			actual = actual.aristaSig;
		}
		return actual.peso;
	}

	@Override
	public ConjuntoTDA vertices() {
		ConjuntoTDA con = new Conjunto();
		con.inicializarConjunto();
		NodoV actual = inicioVertice;
		while(actual!=null) {
			con.agregar(actual.verticeOrigen);
			actual = actual.verticeSig;
		}
		return con;
	}

	@Override
	public boolean existeArista(int o, int d) {
		NodoV origen = inicioVertice;
		while(origen.verticeOrigen!=o) {
			origen = origen.verticeSig;
		}
		NodoA  actual = origen.inicioArista;
		while(actual != null && actual.verticeDestino.verticeOrigen != d) {
			actual = actual.aristaSig;
		}
		return actual!=null;
	}

	@Override
	public void inicializarGrafo() {
		inicioVertice = null;

	}

	@Override
	public ConjuntoTDA adyacentes(int v){
		ConjuntoTDA conjunto = vertices();
		ConjuntoTDA aux = new Conjunto();
		aux.inicializarConjunto();
		while(!conjunto.conjuntoVacio()){
			int valor = conjunto.obtener();
			if(valor!=v && existeArista(v,valor))
				aux.agregar(valor);
			conjunto.sacar(valor);
		}
		return aux;
	}

}
