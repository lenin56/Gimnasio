public class Ejercicio {
    private String nombre;
    private String grupoMuscular;
    private int series;
    private int repeticiones;
    private String nivelDificultad;

    public Ejercicio(String nombre, String grupoMuscular, int series, int repeticiones, String nivelDificultad) {
        this.nombre = nombre;
        this.grupoMuscular = grupoMuscular;
        this.series = series;
        this.repeticiones = repeticiones;
        this.nivelDificultad = nivelDificultad;
    }

    public void mostrarEjercicio() {
        System.out.println("Ejercicio: " + nombre);
        System.out.println("Grupo muscular: " + grupoMuscular);
        System.out.println("Series: " + series);
        System.out.println("Repeticiones: " + repeticiones);
        System.out.println("Nivel: " + nivelDificultad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getNivelDificultad() {
        return nivelDificultad;
    }

    public void setNivelDificultad(String nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }
}
