package dominio.aplicaciones;

/**
 * @author Luis
 * Conjunto es una estructura que permite almacenar una colecci�n de valores enteros,
 * no repetidos y no necesariamente ordenado.
 */
public interface ConjuntoTDA {
	/**
	 * @Tarea Agregar agrega un elemento x suministrado.
	 * @Precondicion la estrucura debe estar inicializada y el elemento no debe existir.
	 */
	void agregar(int x);
	/**
	 * @Tarea Sacar elimina un elemento suministrado x. 
	 * @Precondicion el elemento debe pertenecer al conjunto.
	 */
	void sacar(int x);
	/**
	 * @Tarea Obtener devuelve un valor cualquiera.
	 * @Precondicion el conjunto no debe estar vacio.
	 */
	int obtener();
	/**
	 * @Tarea ConjuntoVacio indica si el conjunto tiene elementos o no.
	 * @Precondicion el conjunto debe estar inicializado.
	 */
	boolean conjuntoVacio();
	/**
	 * @Tarea Pertenece : devuelve verdadero si el valor x recibido como par�metro pertenece al conjunto. 
	 * @Precondicion el conjunto debe estar inicializado.
	 */
	boolean pertenece(int x);
	/**
	 * @Tarea InicializarConjunto inicializa la estructura conjunto.
	 */
	void inicializarConjunto();	
}
