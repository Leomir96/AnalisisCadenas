import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Pila pila = new Pila();  // UNA SOLA PILA para todo
    private static AnalizadorCadenas analizador = new AnalizadorCadenas(pila);

    public static void main(String[] args) {
        mostrarBienvenida();
        menu();
    }

    // Muestra mensaje de bienvenida con informacion del proyecto
    private static void mostrarBienvenida() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("||" + " ".repeat(68) + "||");
        System.out.println("||" + centrar("ANALIZADOR DE CADENAS DE IMPRESION", 68) + "||");
        System.out.println("||" + centrar("Implementacion con Pilas en Java", 68) + "||");
        System.out.println("||" + " ".repeat(68) + "||");
        System.out.println("=".repeat(70));
        System.out.println("\nEste programa implementa UNA PILA para verificar el balanceo");
        System.out.println("de simbolos de agrupacion en expresiones y cadenas.");
        System.out.println("\nFlujo: 1. Cargar cadena  2. Manipular pila  3. Analizar");
        System.out.println("\nLa pila guarda TODOS los caracteres.");
        System.out.println("El analisis verifica SOLO los simbolos: ( ) [ ] { }");
        System.out.println("=".repeat(70) + "\n");
    }

    // Centra un texto en un ancho especifico
    private static String centrar(String texto, int ancho) {
        int espacios = (ancho - texto.length()) / 2;
        return " ".repeat(espacios) + texto + " ".repeat(ancho - texto.length() - espacios);
    }

    // Menu principal del programa
    private static void menu() {
        int opcion;
        
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 0);
        
        System.out.println("\nGracias por usar el Analizador de Cadenas!");
        System.out.println("Hasta pronto.\n");
        scanner.close();
    }

    // Muestra las opciones del menu
    private static void mostrarMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("            MENU PRINCIPAL");
        System.out.println("=".repeat(50));
        System.out.println("1. Cargar cadena en la pila");
        System.out.println("2. Apilar caracter manualmente");
        System.out.println("3. Desapilar caracter");
        System.out.println("4. Ver tope de la pila");
        System.out.println("5. Mostrar contenido de la pila");
        System.out.println("6. Vaciar pila");
        System.out.println("7. Analizar pila actual");
        System.out.println("8. Ver ejemplos de cadenas validas/invalidas");
        System.out.println("9. Informacion sobre el algoritmo");
        System.out.println("0. Salir");
        System.out.println("=".repeat(50));
        System.out.println("Estado actual -> Pila: " + pila.toString() + 
                          " | Tamano: " + pila.getTamano());
        System.out.println("=".repeat(50));
        System.out.print("Seleccione una opcion: ");
    }

    // Lee la opcion del usuario con validacion
    private static int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar buffer
            return opcion;
        } catch (Exception e) {
            scanner.nextLine();  // Limpiar buffer
            return -1;  // Opcion invalida
        }
    }

    // Procesa la opcion seleccionada por el usuario
    private static void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                cargarCadena();
                break;
            case 2:
                apilarCaracter();
                break;
            case 3:
                desapilarCaracter();
                break;
            case 4:
                verTope();
                break;
            case 5:
                mostrarPila();
                break;
            case 6:
                vaciarPila();
                break;
            case 7:
                analizarPila();
                break;
            case 8:
                mostrarEjemplos();
                break;
            case 9:
                mostrarInfoAlgoritmo();
                break;
            case 0:
                // Salir
                break;
            default:
                System.out.println("\nOpcion invalida. Por favor intente nuevamente.\n");
        }
    }

    // Opcion 1: Cargar una cadena en la pila
    private static void cargarCadena() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("CARGAR CADENA EN LA PILA");
        System.out.println("-".repeat(50));
        
        if (!pila.estaVacia()) {
            System.out.println("ADVERTENCIA: La pila actual contiene: " + pila.toString());
            System.out.print("¿Desea vaciarla y cargar nueva cadena? (s/n): ");
            String confirmacion = scanner.nextLine().toLowerCase();
            
            if (!confirmacion.equals("s") && !confirmacion.equals("si")) {
                System.out.println("\n[CANCELADO] Operacion cancelada.\n");
                pausa();
                return;
            }
        }
        
        System.out.print("\nIngrese la cadena a cargar: ");
        String cadena = scanner.nextLine();
        
        if (cadena.isEmpty()) {
            System.out.println("\n[ERROR] La cadena no puede estar vacia.\n");
            pausa();
            return;
        }
        
        analizador.cargarCadenaEnPila(cadena);
        pausa();
    }

    // Opcion 2: Apilar un caracter manualmente
    private static void apilarCaracter() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("APILAR CARACTER MANUALMENTE");
        System.out.println("-".repeat(50));
        System.out.println("Estado actual: " + pila.toString());
        System.out.print("\nIngrese el caracter a apilar: ");
        String input = scanner.nextLine();
        
        if (input.isEmpty()) {
            System.out.println("\n[ERROR] Debe ingresar un caracter.\n");
            pausa();
            return;
        }
        
        char c = input.charAt(0);
        pila.apilar(c);
        System.out.println("\n[EXITO] Caracter '" + c + "' apilado correctamente.");
        System.out.println("Estado de la pila: " + pila.toString());
        System.out.println("Tamano actual: " + pila.getTamano() + " elemento(s)\n");
        pausa();
    }

    // Opcion 3: Desapilar un caracter
    private static void desapilarCaracter() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("DESAPILAR CARACTER");
        System.out.println("-".repeat(50));
        
        if (pila.estaVacia()) {
            System.out.println("\n[ERROR] La pila esta vacia. No se puede desapilar.\n");
            pausa();
            return;
        }
        
        System.out.println("Estado actual de la pila: " + pila.toString());
        char desapilado = pila.desapilar();
        System.out.println("\n[EXITO] Caracter desapilado: '" + desapilado + "'");
        System.out.println("Estado de la pila: " + pila.toString());
        System.out.println("Tamano actual: " + pila.getTamano() + " elemento(s)\n");
        pausa();
    }

    // Opcion 4: Ver el tope de la pila
    private static void verTope() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("VER TOPE DE LA PILA");
        System.out.println("-".repeat(50));
        
        if (pila.estaVacia()) {
            System.out.println("\n[INFO] La pila esta vacia. No hay tope.\n");
            pausa();
            return;
        }
        
        char tope = pila.verTope();
        System.out.println("\n[INFO] Tope de la pila: '" + tope + "'");
        System.out.println("Estado de la pila: " + pila.toString());
        System.out.println("Tamano actual: " + pila.getTamano() + " elemento(s)\n");
        pausa();
    }

    // Opcion 5: Mostrar todo el contenido de la pila
    private static void mostrarPila() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("CONTENIDO DE LA PILA");
        System.out.println("-".repeat(50));
        
        if (pila.estaVacia()) {
            System.out.println("\n[INFO] La pila esta vacia.\n");
        } else {
            pila.mostrar();
            System.out.println("Tamano: " + pila.getTamano() + " elemento(s)");
            System.out.println("Representacion: " + pila.toString() + "\n");
        }
        
        pausa();
    }

    // Opcion 6: Vaciar la pila
    private static void vaciarPila() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("VACIAR PILA");
        System.out.println("-".repeat(50));
        
        if (pila.estaVacia()) {
            System.out.println("\n[INFO] La pila ya esta vacia.\n");
            pausa();
            return;
        }
        
        System.out.println("Estado actual: " + pila.toString());
        System.out.print("\n¿Esta seguro que desea vaciar la pila? (s/n): ");
        String confirmacion = scanner.nextLine().toLowerCase();
        
        if (confirmacion.equals("s") || confirmacion.equals("si")) {
            pila.vaciar();
            System.out.println("\n[EXITO] Pila vaciada correctamente.");
            System.out.println("Estado de la pila: " + pila.toString() + "\n");
        } else {
            System.out.println("\n[CANCELADO] Operacion cancelada.\n");
        }
        
        pausa();
    }

    // Opcion 7: Analizar la pila actual
    private static void analizarPila() {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("ANALIZAR PILA ACTUAL");
        System.out.println("-".repeat(50));
        
        analizador.analizarPilaActual();
        pausa();
    }

    // Opcion 8: Muestra ejemplos de cadenas validas e invalidas
    private static void mostrarEjemplos() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("EJEMPLOS DE CADENAS VALIDAS E INVALIDAS");
        System.out.println("=".repeat(70));
        
        System.out.println("\n--- CADENAS VALIDAS ---");
        System.out.println("1. (a + b)                    -> Parentesis balanceados");
        System.out.println("2. {x * [y + (z - 2)]}        -> Anidacion correcta");
        System.out.println("3. ((a + b) * (c - d))        -> Multiples parentesis");
        System.out.println("4. [a + {b * (c / d)}]        -> Todos los tipos mezclados");
        System.out.println("5. {[(a + b)]}                -> Anidacion perfecta");
        
        System.out.println("\n--- CADENAS INVALIDAS ---");
        System.out.println("1. (a + b                     -> Falta simbolo de cierre");
        System.out.println("2. a + b)                     -> Cierre sin apertura");
        System.out.println("3. {a + b]                    -> Tipos no coinciden");
        System.out.println("4. ((a + b)                   -> Falta un cierre");
        System.out.println("5. )a + b(                    -> Orden invertido");
        System.out.println("6. {[a + b}]                  -> Cruzamiento incorrecto");
        
        System.out.println("\n--- COMO USAR ---");
        System.out.println("1. Seleccione opcion 1 para cargar cualquiera de estas cadenas");
        System.out.println("2. La pila se llenara automaticamente con los simbolos");
        System.out.println("3. Puede manipular la pila con opciones 2-6");
        System.out.println("4. Seleccione opcion 7 para analizar el balanceo");
        
        System.out.println("\n" + "=".repeat(70) + "\n");
        pausa();
    }

    // Opcion 9: Muestra informacion sobre el algoritmo
    private static void mostrarInfoAlgoritmo() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("INFORMACION SOBRE EL ALGORITMO DE ANALISIS");
        System.out.println("=".repeat(70));
        
        System.out.println("\nFUNDAMENTO TEORICO:");
        System.out.println("Las pilas (LIFO - Last In First Out) son ideales para verificar");
        System.out.println("el balanceo de simbolos porque:");
        System.out.println("- El ultimo simbolo de apertura debe cerrarse primero");
        System.out.println("- Mantienen el orden de anidacion de los simbolos");
        
        System.out.println("\nFLUJO DEL PROGRAMA:");
        System.out.println("1. CARGAR: Se ingresa una cadena y se apilan TODOS los caracteres");
        System.out.println("           (letras, numeros, simbolos, espacios)");
        System.out.println("2. MANIPULAR: Se puede modificar la pila manualmente");
        System.out.println("              (apilar, desapilar, ver tope, vaciar)");
        System.out.println("3. ANALIZAR: Se reconstruye la cadena desde la pila, se extraen");
        System.out.println("             SOLO los simbolos de agrupacion ( ) [ ] { } y se");
        System.out.println("             verifica si estan balanceados");
        
        System.out.println("\nALGORITMO DE ANALISIS:");
        System.out.println("1. Recorrer cada caracter de la cadena reconstruida");
        System.out.println("2. Si es simbolo de apertura ( [ { -> APILAR en pila temporal");
        System.out.println("3. Si es simbolo de cierre ) ] }:");
        System.out.println("   a. Verificar que la pila temporal no este vacia");
        System.out.println("   b. Desapilar el tope");
        System.out.println("   c. Verificar que forme par con el cierre actual");
        System.out.println("4. Al finalizar, la pila temporal debe estar vacia");
        
        System.out.println("\nCOMPLEJIDAD:");
        System.out.println("- Temporal: O(n) - Se recorre la cadena una vez");
        System.out.println("- Espacial: O(n) - En el peor caso, todos son de apertura");
        
        System.out.println("\nAPLICACIONES PRACTICAS:");
        System.out.println("- Validacion de expresiones aritmeticas");
        System.out.println("- Compiladores e interpretes");
        System.out.println("- Editores de texto (verificar codigo)");
        System.out.println("- Procesadores de lenguajes de marcado (HTML, XML)");
        
        System.out.println("\n" + "=".repeat(70) + "\n");
        pausa();
    }

    // Pausa la ejecucion hasta que el usuario presione Enter
    private static void pausa() {
        System.out.print("Presione Enter para continuar...");
        scanner.nextLine();
    }
}
