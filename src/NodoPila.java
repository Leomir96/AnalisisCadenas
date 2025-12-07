public class NodoPila {

    // Atributos
    private char dato;           // Caracter almacenado en el nodo
    private NodoPila siguiente;  // Referencia al siguiente nodo en la pila

    // Constructor
    public NodoPila(char dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    // Getters
    public char getDato() {
        return dato;
    }

    public NodoPila getSiguiente() {
        return siguiente;
    }

    // Setters
    public void setDato(char dato) {
        this.dato = dato;
    }

    public void setSiguiente(NodoPila siguiente) {
        this.siguiente = siguiente;
    }
}
