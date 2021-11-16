package dominio.aplicaciones;

import dominio.implementaciones.Edge;

public interface ColaPrioridadKTDA {
    /**
     * @Tarea AcolarPrioridad agrega un elemento x con prioridad p a la cola, ambas suministradas.
     * @Precondicion la cola debe estar inicializada.
     */
    void acolarPrioridad(Edge x, double p);
    /**
     * @Tarea Desacolar elimina el elemento con mayor prioridad de la cola.
     * @Precondicion la cola no debe estar vacia.
     */
    void desacolar();
    /**
     * @Tarea Primero devuelve el elemento de mayor prioridad.
     * @Precondicion la estructura debe contener elementos.
     */
    Edge primero();
    /**
     * @Tarea ColaVacia indica si la cola contiene elementos o no.
     * @Precondicion la cola debe estar inicializada.
     */
    boolean colaVacia();
    /**
     * @Tarea Prioridad obtiene la prioridad del valor del dato de mayor prioridad.
     * @Precondicion la estructura no debe estar vacía.
     */
    double prioridad();
    /**
     * @Tarea InicilaizarCola inicializa la estructura cola.
     */
    void inicializarCola();
}
