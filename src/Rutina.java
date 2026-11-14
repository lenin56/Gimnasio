import java.util.ArrayList;

public class Rutina {
    private Cliente cliente;
    private String nombreInstructor;
    private ArrayList<Ejercicio> catalogo;
    private ArrayList<Ejercicio> ejerciciosPlan;
    private String nivel;
    private String objetivo;

    public Rutina(Cliente cliente, String nombreInstructor,
                  ArrayList<Ejercicio> catalogo) {
        this.cliente = cliente;
        this.nombreInstructor = nombreInstructor;
        this.catalogo = catalogo;
        this.ejerciciosPlan = new ArrayList<Ejercicio>();
        calcularNivel();
        definirObjetivo();
        generarPlan();
    }

    private void calcularNivel() {
        if (cliente.getTiempoExperiencia() == 1 || cliente.getNivelTecnico() == 1) {
            nivel = "Junior";
        } else if (cliente.getTiempoExperiencia() == 2 || cliente.getNivelTecnico() == 2) {
            nivel = "Intermedio";
        } else {
            nivel = "Experto";
        }
    }

    private void definirObjetivo() {
        if (cliente.getObjetivo().equals("Salud y acondicionamiento")) {
            objetivo = "Ritmo moderado, descanso de 60-90 seg + 15 min " + "en caminadora o bicicleta estatica.";
        } else if (cliente.getObjetivo().equals("Bajar de peso")) {
            objetivo = "Ritmo rapido, descansos cortos de 30-45 seg + 20 min " + "de eliptica o escaladora al final.";
        } else if (cliente.getObjetivo().equals("Ganar masa muscular")) {
            objetivo = "Movimientos controlados, descanso de 60-90 seg " + "cuidando la postura.";
        } else if (cliente.getObjetivo().equals("Aumentar fuerza")) {
            objetivo = "Cargas altas, descansos largos de 2 a 3 minutos " + "entre series.";
        } else {
            objetivo = "Mantener una ejecucion segura y controlada.";
        }
    }

    private void generarPlan() {
        for (int i = 0; i < catalogo.size(); i++) {
            Ejercicio ejercicio = catalogo.get(i);
            if (nivel.equals("Junior") && ejercicio.getNivelDificultad().equals("Junior")) {
                ejerciciosPlan.add(ejercicio);
            } else if (nivel.equals("Intermedio") && (ejercicio.getNivelDificultad().equals("Junior") || ejercicio.getNivelDificultad().equals("Intermedio"))) {
                ejerciciosPlan.add(ejercicio);
            } else if (nivel.equals("Experto") && (ejercicio.getNivelDificultad().equals("Intermedio") || ejercicio.getNivelDificultad().equals("Experto"))) {
                ejerciciosPlan.add(ejercicio);
            }
        }
    }

    public void mostrarRutinaCompleta() {
        System.out.println();
        System.out.println("Ficha de rutina");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Instructor: " + nombreInstructor);
        System.out.println("Dias por semana: " + cliente.getDiasSemana());
        System.out.println("Nivel asignado: " + nivel);
        System.out.println("Objetivo: " + cliente.getObjetivo());
        System.out.println("Recomendacion: " + objetivo);
        System.out.println("Ejercicios de la rutina:");

        if (ejerciciosPlan.size() == 0) {
            System.out.println("No hay ejercicios disponibles para este nivel.");
        } else {
            for (int i = 0; i < ejerciciosPlan.size(); i++) {
                System.out.println();
                System.out.println((i + 1) + ". ");
                ejerciciosPlan.get(i).mostrarEjercicio();
            }
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNombreInstructor() {
        return nombreInstructor;
    }

    public void setNombreInstructor(String nombreInstructor) {
        this.nombreInstructor = nombreInstructor;
    }

    public ArrayList<Ejercicio> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(ArrayList<Ejercicio> catalogo) {
        this.catalogo = catalogo;
    }

    public ArrayList<Ejercicio> getEjerciciosPlan() {
        return ejerciciosPlan;
    }

    public String getNivel() {
        return nivel;
    }

    public String getObjetivo() {
        return objetivo;
    }
}
