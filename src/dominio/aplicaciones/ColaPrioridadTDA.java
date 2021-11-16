package dominio.aplicaciones;
/**
  * @autor Luis
  * Cola con prioridad es una estructura que permite almacenar una colección de valores enteros, 
  * con la particularidad de que el elemento que el primer elemento en ingresar es el primer elemento en salir.  
  */
public interface ColaPrioridadTDA {
	/**
	 * @Tarea AcolarPrioridad agrega un elemento x con prioridad p a la cola, ambas suministradas.
	 * @Precondicion la cola debe estar inicializada.
	 */
	void acolarPrioridad(int x, int p);
	/**
	 * @Tarea Desacolar elimina el elemento con mayor prioridad de la cola.
	 * @Precondicion la cola no debe estar vacia.
	 */
	void desacolar();
	/**
	 * @Tarea Primero devuelve el elemento de mayor prioridad.
	 * @Precondicion la estructura debe contener elementos.
	 */
	int primero();
	/**
	 * @Tarea ColaVacia indica si la cola contiene elementos o no.
	 * @Precondicion la cola debe estar inicializada.
	 */
	boolean colaVacia();
	/**
	 * @Tarea Prioridad obtiene la prioridad del valor del dato de mayor prioridad. 
	 * @Precondicion la estructura no debe estar vacía.
	 */
	int prioridad();
	/**
	 * @Tarea InicilaizarCola inicializa la estructura cola.
	 */
	void inicializarCola();

}

