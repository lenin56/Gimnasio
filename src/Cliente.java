public class Cliente {
    private String nombre;
    private int tiempoExperiencia;
    private int diasSemana;
    private int nivelTecnico;
    private String objetivo;

    public Cliente(String nombre, int tiempoExperiencia, int diasSemana, int nivelTecnico, String objetivo) {
        this.nombre = nombre;
        this.tiempoExperiencia = tiempoExperiencia;
        this.diasSemana = diasSemana;
        this.nivelTecnico = nivelTecnico;
        this.objetivo = objetivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempoExperiencia() {
        return tiempoExperiencia;
    }

    public void setTiempoExperiencia(int tiempoExperiencia) {
        this.tiempoExperiencia = tiempoExperiencia;
    }

    public int getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(int diasSemana) {
        this.diasSemana = diasSemana;
    }

    public int getNivelTecnico() {
        return nivelTecnico;
    }

    public void setNivelTecnico(int nivelTecnico) {
        this.nivelTecnico = nivelTecnico;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
}
