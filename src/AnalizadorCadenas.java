public class AnalizadorCadenas {

    private Pila pila;  // UNA SOLA PILA compartida

    // Constructor
    public AnalizadorCadenas(Pila pila) {
        this.pila = pila;  // Recibe la pila del Main
    }

    // Carga una cadena COMPLETA en la pila (todos los caracteres)
    public void cargarCadenaEnPila(String cadena) {
        pila.vaciar();  // Limpiar pila antes de cargar
        
        System.out.println("\n=== Cargando cadena en la pila ===");
        System.out.println("Cadena original: " + cadena);
        System.out.println("\nApilando todos los caracteres:\n");
        
        // Apilar TODOS los caracteres de la cadena
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            pila.apilar(c);
            
            // Mostrar solo cada 5 caracteres para no saturar la pantalla
            if (i % 5 == 0 || i == cadena.length() - 1) {
                System.out.println("Posicion " + i + ": '" + c + "' -> APILADO. Pila tiene " + 
                                 pila.getTamano() + " elementos");
            }
        }
        
        System.out.println("\n[EXITO] Se cargaron " + cadena.length() + " caracteres en la pila.");
        System.out.println("Estado final de la pila: " + pila.toString());
        System.out.println("Tamano: " + pila.getTamano() + " elemento(s)\n");
    }

    // Analiza la pila actual para verificar balanceo
    // IMPORTANTE: Solo verifica los simbolos de agrupacion, ignora el resto
    public boolean analizarPilaActual() {
        if (pila.estaVacia()) {
            System.out.println("\n[ERROR] La pila esta vacia. No hay nada que analizar.\n");
            return true;  // Pila vacia = balanceada
        }
        
        // Paso 1: Extraer todos los caracteres de la pila (invierte el orden)
        StringBuilder cadenaInvertida = new StringBuilder();
        Pila pilaTemp = new Pila();
        
        while (!pila.estaVacia()) {
            char c = pila.desapilar();
            cadenaInvertida.append(c);
            pilaTemp.apilar(c);
        }
        
        // Paso 2: Restaurar la pila original
        while (!pilaTemp.estaVacia()) {
            pila.apilar(pilaTemp.desapilar());
        }
        
        // La cadena esta invertida, hay que darle la vuelta
        String cadena = cadenaInvertida.reverse().toString();
        
        System.out.println("\n=== Analizando pila actual ===");
        System.out.println("Contenido de la pila (" + pila.getTamano() + " caracteres): " + pila.toString());
        System.out.println("Cadena reconstruida: " + cadena);
        System.out.println("\nExtrayendo SOLO simbolos de agrupacion para analisis...\n");
        
        // Paso 3: Extraer SOLO los simbolos de agrupacion
        StringBuilder soloSimbolos = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (c == '(' || c == ')' || c == '[' || c == ']' || c == '{' || c == '}') {
                soloSimbolos.append(c);
            }
        }
        
        String cadenaSimbolos = soloSimbolos.toString();
        System.out.println("Simbolos extraidos: " + cadenaSimbolos);
        
        if (cadenaSimbolos.isEmpty()) {
            System.out.println("\n[RESULTADO] No hay simbolos de agrupacion en la pila.");
            System.out.println("Pila VALIDA: No hay nada que balancear.\n");
            return true;
        }
        
        System.out.println("\nIniciando analisis de balanceo...\n");
        
        // Paso 4: Analizar el balanceo usando una pila temporal
        Pila pilaAnalisis = new Pila();
        
        for (int i = 0; i < cadenaSimbolos.length(); i++) {
            char caracterActual = cadenaSimbolos.charAt(i);
            
            if (esCaracterApertura(caracterActual)) {
                pilaAnalisis.apilar(caracterActual);
                System.out.println("Posicion " + i + ": '" + caracterActual + 
                                 "' (apertura) -> APILADO. Pila analisis: " + pilaAnalisis.toString());
            } 
            else if (esCaracterCierre(caracterActual)) {
                System.out.print("Posicion " + i + ": '" + caracterActual + "' (cierre) -> ");
                
                if (pilaAnalisis.estaVacia()) {
                    System.out.println("ERROR - No hay simbolo de apertura correspondiente.");
                    System.out.println("\n[RESULTADO] Pila INVALIDA: Simbolo de cierre sin apertura.\n");
                    return false;
                }
                
                char caracterTope = pilaAnalisis.desapilar();
                
                if (formanPar(caracterTope, caracterActual)) {
                    System.out.println("COINCIDE con '" + caracterTope + 
                                     "'. Pila analisis: " + pilaAnalisis.toString());
                } else {
                    System.out.println("ERROR - '" + caracterActual + 
                                     "' no coincide con '" + caracterTope + "'.");
                    System.out.println("\n[RESULTADO] Pila INVALIDA: Simbolos no coinciden.\n");
                    return false;
                }
            }
        }
        
        if (!pilaAnalisis.estaVacia()) {
            System.out.println("\nERROR - Quedan simbolos sin cerrar: " + pilaAnalisis.toString());
            System.out.println("[RESULTADO] Pila INVALIDA: Simbolos de apertura sin cierre.\n");
            return false;
        }
        
        System.out.println("\n[RESULTADO] Pila VALIDA: Todos los simbolos estan balanceados correctamente.\n");
        return true;
    }

    // Verifica si un caracter es de apertura
    private boolean esCaracterApertura(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    // Verifica si un caracter es de cierre
    private boolean esCaracterCierre(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    // Verifica si dos caracteres forman un par valido
    private boolean formanPar(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')') ||
               (apertura == '[' && cierre == ']') ||
               (apertura == '{' && cierre == '}');
    }
}
