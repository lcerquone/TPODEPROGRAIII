package dominio.aplicaciones;

/**
 * @author Luis
 * Grafo es una colección de vértices y aristas tipo dirigido, sin aristas paralelas ni bucles,
 * donde los  vértices y los pesos son enteros .
 */
public interface GrafoTDA {
	/**
	 * @Tarea AgregarVertice agrega un vertice.
	 * @Precondicion el grafo debe estar inicializado y el vertice no debe existir.
	 */
	void agregarVertice(int x);
	/**
	 * @Tarea EliminarVertice elimina un vertice suministrado.
	 * @Precondicion el vertice debe existir.
	 */
	void eliminarVertice(int x);
	/**
	 * @Tarea AgregarArista agrega una arista, identificada por los vértices de origen y destino; y el peso.
	 * @Precondicion los vértices deben existir, pero no debe existir la arista.
	 */
	void agregarArista(int o, int d, double p);
	/**
	 * @Tarea EliminarArista elimina la arista suministrando los vértices de origen y destino.
	 * @Precondicion la arista debe existir.
	 */
	void eliminarArista(int o, int d);
	/**
	 * @Tarea Peso devuelve el peso de la arista, identificada por los vértices de origen y destino.
	 * @Precondicion la arista debe existir
	 */
	double peso(int o, int d);
	/**
	 * @Tarea Vertice devuelve el conjunto de vertices que tiene el grafo.
	 * @Precondicion el grafo debe estar inicializado.
	 */
	ConjuntoTDA vertices();
	/**
	 * @Tarea ExisteArista indica si una arista existe o no, identificada por los vértices de origen y destino. 
	 * @Precondicion el grafo debe estar inicializado.
	 */
	boolean existeArista(int o, int d);
	/**
	 * @Tarea InicializarGrafo inicializa la estructura grafo.
	 */
	void inicializarGrafo();
	/**
	 * @Tarea Devuelve los adyacentes de un nodo dado.
	 */
	ConjuntoTDA adyacentes(int v);

}
