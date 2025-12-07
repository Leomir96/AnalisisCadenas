public class Pila {

    // Atributos
    private NodoPila tope;  // Referencia al nodo en el tope de la pila
    private int tamano;     // Contador de elementos en la pila

    // Constructor
    public Pila() {
        this.tope = null;
        this.tamano = 0;
    }

    // Verifica si la pila esta vacia
    public boolean estaVacia() {
        return tope == null;
    }

    // Retorna el tamano actual de la pila
    public int getTamano() {
        return tamano;
    }

    // Inserta un elemento en el tope de la pila
    // Complejidad: O(1)
    public void apilar(char dato) {
        NodoPila nuevoNodo = new NodoPila(dato);
        
        // Si la pila esta vacia, el nuevo nodo es el tope
        if (estaVacia()) {
            tope = nuevoNodo;
        } else {
            // El nuevo nodo apunta al tope actual
            nuevoNodo.setSiguiente(tope);
            // El nuevo nodo se convierte en el nuevo tope
            tope = nuevoNodo;
        }
        
        tamano++;
    }

    // Elimina y retorna el elemento del tope de la pila
    // Complejidad: O(1)
    public char desapilar() {
        if (estaVacia()) {
            System.out.println("Error: La pila esta vacia. No se puede desapilar.");
            return '\0';  // Retorna caracter nulo si la pila esta vacia
        }
        
        char datoTope = tope.getDato();
        tope = tope.getSiguiente();  // El tope ahora apunta al siguiente nodo
        tamano--;
        
        return datoTope;
    }

    // Retorna el elemento del tope sin eliminarlo
    // Complejidad: O(1)
    public char verTope() {
        if (estaVacia()) {
            System.out.println("Error: La pila esta vacia.");
            return '\0';
        }
        
        return tope.getDato();
    }

    // Vacia completamente la pila
    public void vaciar() {
        tope = null;
        tamano = 0;
    }

    // Muestra todos los elementos de la pila
    // (Desde el tope hacia abajo)
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La pila esta vacia.");
            return;
        }

        System.out.println("\n--- Contenido de la pila (tope -> base) ---");
        NodoPila actual = tope;
        int posicion = 1;
        
        while (actual != null) {
            System.out.println(posicion + ". [" + actual.getDato() + "]");
            actual = actual.getSiguiente();
            posicion++;
        }
        System.out.println("-------------------------------------------\n");
    }

    // Retorna una representacion en String de la pila
    public String toString() {
        if (estaVacia()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        NodoPila actual = tope;
        
        while (actual != null) {
            sb.append(actual.getDato());
            if (actual.getSiguiente() != null) {
                sb.append(", ");
            }
            actual = actual.getSiguiente();
        }
        
        sb.append("]");
        return sb.toString();
    }
}
