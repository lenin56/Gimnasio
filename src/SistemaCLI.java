import java.util.ArrayList;
import java.util.Scanner;

public class SistemaCLI {
    private Scanner scanner;
    private ArrayList<Ejercicio> catalogo;
    private ArrayList<Rutina> historial;

    public SistemaCLI() {
        scanner = new Scanner(System.in);
        catalogo = new ArrayList<Ejercicio>();
        historial = new ArrayList<Rutina>();
        cargarCatalogoInicial();
    }

    private void cargarCatalogoInicial() {
        catalogo.add(new Ejercicio("Sentadilla libre", "Piernas", 3, 10, "Junior"));
        catalogo.add(new Ejercicio("Press de pecho en maquina", "Pecho", 3, 10, "Junior"));
        catalogo.add(new Ejercicio("Remo sentado", "Espalda", 3, 10, "Junior"));
        catalogo.add(new Ejercicio("Zancadas con mancuernas", "Piernas", 4, 10, "Intermedio"));
        catalogo.add(new Ejercicio("Press inclinado con mancuernas", "Pecho", 4, 8, "Intermedio"));
        catalogo.add(new Ejercicio("Peso muerto rumano", "Espalda", 4, 8, "Intermedio"));
        catalogo.add(new Ejercicio("Sentadilla frontal", "Piernas", 5, 6, "Experto"));
        catalogo.add(new Ejercicio("Press banca con barra", "Pecho", 5, 5, "Experto"));
        catalogo.add(new Ejercicio("Dominadas lastradas", "Espalda", 5, 5, "Experto"));
    }

    public void iniciar() {
        int opcion = 0;
        while (opcion != 5) {
            mostrarMenu();
            opcion = leerEnteroEnRango("Seleccione una opcion: ", 1, 5);
            if (opcion == 1) {
                evaluarCliente();
            } else if (opcion == 2) {
                mostrarCatalogo();
            } else if (opcion == 3) {
                agregarEjercicio();
            } else if (opcion == 4) {
                mostrarHistorial();
            } else {
                System.out.println("Saliendo del sistema.");
            }
        }
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("Sistema de gimnasio");
        System.out.println("1. Evaluar nuevo cliente y generar rutina");
        System.out.println("2. Ver catalogo de ejercicios");
        System.out.println("3. Agregar nuevo ejercicio al catalogo");
        System.out.println("4. Ver historial de rutinas asignadas");
        System.out.println("5. Salir");
    }

    private void evaluarCliente() {
        System.out.println();
        System.out.println("Evaluacion de cliente");
        String nombre = leerTextoConLetras("Nombre del cliente: ");
        int experiencia = mostrarExperiencia();
        int dias = leerEnteroEnRango("Dias de entrenamiento por semana (1-7): ", 1, 7);
        int nivelTecnico = mostrarNivelTecnico();
        String objetivo = mostrarObjetivos();
        String instructor = leerTextoConLetras("Nombre del instructor: ");

        Cliente cliente = new Cliente(nombre, experiencia, dias, nivelTecnico, objetivo);
        Rutina rutina = new Rutina(cliente, instructor, catalogo);
        historial.add(rutina);
        rutina.mostrarRutinaCompleta();
    }

    private int mostrarExperiencia() {
        System.out.println("Tiempo de experiencia");
        System.out.println("1. Recién empieza sin experiencia previa");
        System.out.println("2. De 1 a 5 meses entrenando");
        System.out.println("3. De 6 a 24 meses");
        System.out.println("4. Mas de 24 meses");
        return leerEnteroEnRango("Seleccione una opcion: ", 1, 4);
    }

    private int mostrarNivelTecnico() {
        System.out.println("Nivel tecnico");
        System.out.println("1. Basico");
        System.out.println("2. Moderado");
        System.out.println("3. Avanzado");
        return leerEnteroEnRango("Seleccione una opcion: ", 1, 3);
    }

    private String mostrarObjetivos() {
        System.out.println("Objetivo");
        System.out.println("1. Salud y acondicionamiento general");
        System.out.println("2. Bajar de peso");
        System.out.println("3. Ganar masa muscular");
        System.out.println("4. Aumentar fuerza");
        int opcion = leerEnteroEnRango("Seleccione una opcion: ", 1, 4);

        switch (opcion) {
            case 1:
                return "Salud y acondicionamiento";
            case 2:
                return "Bajar de peso";
            case 3:
                return "Ganar masa muscular";
            default:
                return "Aumentar fuerza";
        }
    }

    private void mostrarCatalogo() {
        System.out.println();
        System.out.println("Catalogo de ejercicios");
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.println();
            System.out.println((i + 1) + ". ");
            catalogo.get(i).mostrarEjercicio();
        }
    }

    private void agregarEjercicio() {
        System.out.println();
        System.out.println("Agregar ejercicio");
        String nombre = leerTextoConLetras("Nombre del ejercicio: ");
        String grupo = leerTextoConLetras("Grupo muscular: ");
        int series = leerEnteroEnRango("Series (1-5): ", 1, 5);
        int repeticiones = leerEnteroEnRango("Repeticiones (1-10): ", 1, 10);
        int opcionNivel = leerEnteroEnRango(
                "Nivel (1 Junior, 2 Intermedio, 3 Experto): ", 1, 3);
        String nivel;
        if (opcionNivel == 1) {
            nivel = "Junior";
        } else if (opcionNivel == 2) {
            nivel = "Intermedio";
        } else {
            nivel = "Experto";
        }
        catalogo.add(new Ejercicio(nombre, grupo, series, repeticiones, nivel));
        System.out.println("Ejercicio agregado correctamente.");
    }

    private void mostrarHistorial() {
        System.out.println();
        System.out.println("Historial de rutinas asignadas");
        if (historial.size() == 0) {
            System.out.println("Todavia no hay rutinas asignadas.");
        } else {
            for (int i = 0; i < historial.size(); i++) {
                Rutina rutina = historial.get(i);
                System.out.println((i + 1) + ". Cliente: "
                        + rutina.getCliente().getNombre()
                        + " | Nivel: " + rutina.getNivel()
                        + " | Instructor: " + rutina.getNombreInstructor());
            }
        }
    }

    private String leerTextoConLetras(String mensaje) {
        String texto = "";
        boolean valido = false;
        while (
                valido) {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            valido = texto.length() > 0 && contieneSoloLetrasYEspacios(texto);
            if (!valido) {
                System.out.println("Ingrese un texto con solo letras y espacios.");
            }
        }
        return texto;
    }

    private boolean contieneSoloLetrasYEspacios(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (!Character.isLetter(caracter) && caracter != ' ') {
                return false;
            }
        }
        return true;
    }

    private int leerEnteroEnRango(String mensaje, int minimo, int maximo) {

        int numero = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                numero = Integer.parseInt(entrada);
                if (numero >= minimo && numero <= maximo) {
                    valido = true;
                } else {
                    System.out.println("Ingrese un numero entre " + minimo + " y " + maximo + ".");
                }
            } catch (NumberFormatException excepcion) {
                System.out.println("Debe ingresar un numero entero.");
            }
        }
        return numero;
    }
}
