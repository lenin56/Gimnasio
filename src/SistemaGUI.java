import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

public class SistemaGUI extends JFrame {
    private ArrayList<Ejercicio> catalogo;
    private ArrayList<Rutina> historial;

    private JTextField campoNombreCliente;
    private JTextField campoNombreInstructor;
    private JTextField campoNombreEjercicio;
    private JTextField campoGrupoMuscular;
    private JTextField campoRepeticiones;
    private JComboBox<String> comboExperiencia;
    private JComboBox<Integer> comboDias;
    private JComboBox<String> comboNivelTecnico;
    private JComboBox<String> comboObjetivo;
    private JComboBox<Integer> comboSeries;
    private JComboBox<String> comboNivelEjercicio;
    private JTextArea areaRutina;
    private DefaultTableModel modeloCatalogo;
    private DefaultTableModel modeloHistorial;

    public SistemaGUI() {
        catalogo = new ArrayList<Ejercicio>();
        historial = new ArrayList<Rutina>();
        cargarCatalogoInicial();
        configurarVentana();
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión de Rutinas - Gimnasio");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(crearEncabezado(), BorderLayout.NORTH);
        add(crearPestanas(), BorderLayout.CENTER);
    }

    private JPanel crearEncabezado() {
        JPanel encabezado = new JPanel();
        encabezado.setLayout(new GridLayout(2, 1));
        encabezado.setBackground(new Color(33, 37, 41));
        encabezado.setBorder(new EmptyBorder(14, 22, 14, 22));

        JLabel titulo = new JLabel("SISTEMA DE GESTIÓN DE RUTINAS");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 21));

        JLabel subtitulo = new JLabel(
                "Panel de Evaluación y Asignación para Instructores");
        subtitulo.setForeground(new Color(210, 214, 218));
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 13));

        encabezado.add(titulo);
        encabezado.add(subtitulo);
        return encabezado;
    }

    private JTabbedPane crearPestanas() {
        JTabbedPane pestanas = new JTabbedPane();
        pestanas.setFont(new Font("SansSerif", Font.PLAIN, 13));
        pestanas.addTab("Evaluar Cliente", crearPanelEvaluacion());
        pestanas.addTab("Catálogo de Ejercicios", crearPanelCatalogo());
        pestanas.addTab("Agregar Ejercicio", crearPanelAgregarEjercicio());
        pestanas.addTab("Historial de Rutinas", crearPanelHistorial());
        return pestanas;
    }

    private JPanel crearPanelEvaluacion() {
        JPanel contenedor = new JPanel(new GridLayout(1, 2, 15, 15));
        contenedor.setBorder(new EmptyBorder(15, 15, 15, 15));
        contenedor.add(crearFormularioEvaluacion());
        contenedor.add(crearFichaRutina());
        return contenedor;
    }

    private JPanel crearFormularioEvaluacion() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 12));
        panel.setBorder(crearBorde("Datos del Cliente e Instructor"));

        campoNombreCliente = new JTextField();
        comboExperiencia = new JComboBox<String>(new String[]{
                "1. Nada de experiencia",
                "2. Tiene experiencia",
                "3. Experto"
        });
        comboDias = new JComboBox<Integer>(crearNumeros(1, 7));
        comboNivelTecnico = new JComboBox<String>(new String[]{
                "1. Básico", "2. Moderado", "3. Avanzado"
        });
        comboObjetivo = new JComboBox<String>(new String[]{
                "Salud y acondicionamiento",
                "Bajar de peso",
                "Ganar masa muscular",
                "Aumentar fuerza"
        });
        campoNombreInstructor = new JTextField();

        agregarCampo(panel, "Nombre del cliente:", campoNombreCliente);
        agregarCampo(panel, "Tiempo de experiencia:", comboExperiencia);
        agregarCampo(panel, "Días por semana:", comboDias);
        agregarCampo(panel, "Nivel técnico:", comboNivelTecnico);
        agregarCampo(panel, "Objetivo:", comboObjetivo);
        agregarCampo(panel, "Nombre del instructor:", campoNombreInstructor);

        JButton botonGenerar = crearBoton("Generar Rutina", new Color(25, 135, 84));
        botonGenerar.addActionListener(new GenerarRutinaListener(this));
        panel.add(new JLabel());
        panel.add(botonGenerar);
        return panel;
    }

    private JPanel crearFichaRutina() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(crearBorde("Ficha de Rutina Personalizada"));

        areaRutina = new JTextArea();
        areaRutina.setEditable(false);
        areaRutina.setFont(new Font("Monospaced", Font.PLAIN, 13));
        areaRutina.setBackground(new Color(248, 249, 250));
        areaRutina.setMargin(new Insets(12, 12, 12, 12));
        areaRutina.setLineWrap(true);
        areaRutina.setWrapStyleWord(true);
        panel.add(new JScrollPane(areaRutina), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelCatalogo() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        modeloCatalogo = new TablaNoEditable(new String[]{
                "Ejercicio", "Grupo Muscular", "Series",
                "Repeticiones", "Nivel"
        });
        JTable tabla = new JTable(modeloCatalogo);
        prepararTabla(tabla);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        actualizarTablaCatalogo();
        return panel;
    }

    private JPanel crearPanelAgregarEjercicio() {
        JPanel exterior = new JPanel(new BorderLayout());
        exterior.setBorder(new EmptyBorder(25, 100, 25, 100));

        JPanel formulario = new JPanel(new GridLayout(6, 2, 12, 18));
        formulario.setBorder(crearBorde("Registrar Nuevo Ejercicio en Catálogo"));

        campoNombreEjercicio = new JTextField();
        campoGrupoMuscular = new JTextField();
        comboSeries = new JComboBox<Integer>(crearNumeros(1, 10));
        campoRepeticiones = new JTextField();
        comboNivelEjercicio = new JComboBox<String>(new String[]{
                "Junior", "Intermedio", "Experto"
        });

        agregarCampo(formulario, "Nombre del ejercicio:", campoNombreEjercicio);
        agregarCampo(formulario, "Grupo muscular:", campoGrupoMuscular);
        agregarCampo(formulario, "Series:", comboSeries);
        agregarCampo(formulario, "Repeticiones:", campoRepeticiones);
        agregarCampo(formulario, "Nivel de dificultad:", comboNivelEjercicio);

        JButton botonGuardar = crearBoton("Guardar Ejercicio",
                new Color(25, 135, 84));
        botonGuardar.addActionListener(new GuardarEjercicioListener(this));
        formulario.add(new JLabel());
        formulario.add(botonGuardar);
        exterior.add(formulario, BorderLayout.CENTER);
        return exterior;
    }

    private JPanel crearPanelHistorial() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        modeloHistorial = new TablaNoEditable(new String[]{
                "Cliente", "Instructor", "Días/Semana",
                "Nivel Asignado", "Objetivo"
        });
        JTable tabla = new JTable(modeloHistorial);
        prepararTabla(tabla);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panel;
    }

    private void generarRutina() {
        String nombre = validarTexto(campoNombreCliente, "nombre del cliente");
        if (nombre == null) {
            return;
        }
        String instructor = validarTexto(campoNombreInstructor, "nombre del instructor");
        if (instructor == null) {
            return;
        }

        int experiencia = comboExperiencia.getSelectedIndex() + 1;
        int dias = comboDias.getSelectedIndex() + 1;
        int nivelTecnico = comboNivelTecnico.getSelectedIndex() + 1;
        String objetivo = (String) comboObjetivo.getSelectedItem();
        Cliente cliente = new Cliente(nombre, experiencia, dias,
                nivelTecnico, objetivo);
        Rutina rutina = new Rutina(cliente, instructor, catalogo);
        historial.add(rutina);

        areaRutina.setText(crearTextoRutina(rutina));
        actualizarTablaHistorial();
    }

    private String crearTextoRutina(Rutina rutina) {
        StringBuilder texto = new StringBuilder();
        Cliente cliente = rutina.getCliente();
        texto.append("FICHA DE RUTINA\n\n");
        texto.append("Cliente: ").append(cliente.getNombre()).append("\n");
        texto.append("Instructor: ").append(rutina.getNombreInstructor()).append("\n");
        texto.append("Días por semana: ").append(cliente.getDiasSemana()).append("\n");
        texto.append("Nivel asignado: ").append(rutina.getNivel()).append("\n");
        texto.append("Objetivo: ").append(cliente.getObjetivo()).append("\n");
        texto.append("Recomendación: ").append(rutina.getObjetivo()).append("\n\n");
        texto.append("EJERCICIOS DE LA RUTINA\n");

        ArrayList<Ejercicio> plan = rutina.getEjerciciosPlan();
        if (plan.size() == 0) {
            texto.append("No hay ejercicios disponibles para este nivel.\n");
        } else {
            for (int i = 0; i < plan.size(); i++) {
                Ejercicio ejercicio = plan.get(i);
                texto.append("\n").append(i + 1).append(". ")
                        .append(ejercicio.getNombre()).append("\n");
                texto.append("   Grupo muscular: ")
                        .append(ejercicio.getGrupoMuscular()).append("\n");
                texto.append("   Series: ").append(ejercicio.getSeries())
                        .append("\n");
                texto.append("   Repeticiones: ")
                        .append(ejercicio.getRepeticiones()).append("\n");
                texto.append("   Nivel: ")
                        .append(ejercicio.getNivelDificultad()).append("\n");
            }
        }
        return texto.toString();
    }

    private void guardarEjercicio() {
        String nombre = validarTexto(campoNombreEjercicio, "nombre del ejercicio");
        if (nombre == null) {
            return;
        }
        String grupo = validarTexto(campoGrupoMuscular, "grupo muscular");
        if (grupo == null) {
            return;
        }
        String repeticionesTexto = campoRepeticiones.getText().trim();
        if (repeticionesTexto.isEmpty()) {
            mostrarAlerta("Las repeticiones no pueden estar vacías.");
            return;
        }

        int repeticiones;
        try {
            repeticiones = Integer.parseInt(repeticionesTexto);
        } catch (NumberFormatException excepcion) {
            mostrarAlerta("Las repeticiones deben ser un número entero.");
            return;
        }
        if (repeticiones < 1 || repeticiones > 10) {
            mostrarAlerta("Las repeticiones deben estar entre 1 y 10.");
            return;
        }

        int series = (Integer) comboSeries.getSelectedItem();
        String nivel = (String) comboNivelEjercicio.getSelectedItem();
        catalogo.add(new Ejercicio(nombre, grupo, series, repeticiones, nivel));
        actualizarTablaCatalogo();
        limpiarFormularioEjercicio();
        JOptionPane.showMessageDialog(this, "Ejercicio guardado correctamente.",
                "Confirmación", JOptionPane.INFORMATION_MESSAGE);
    }

    private String validarTexto(JTextField campo, String descripcion) {
        String texto = campo.getText().trim();
        if (texto.isEmpty()) {
            mostrarAlerta("El " + descripcion + " no puede estar vacío.");
            campo.requestFocus();
            return null;
        }
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (!Character.isLetter(caracter) && caracter != ' ') {
                mostrarAlerta("El " + descripcion
                        + " solo puede contener letras y espacios.");
                campo.requestFocus();
                return null;
            }
        }
        return texto;
    }

    private void actualizarTablaCatalogo() {
        if (modeloCatalogo == null) {
            return;
        }
        modeloCatalogo.setRowCount(0);
        for (int i = 0; i < catalogo.size(); i++) {
            Ejercicio ejercicio = catalogo.get(i);
            modeloCatalogo.addRow(new Object[]{
                    ejercicio.getNombre(), ejercicio.getGrupoMuscular(),
                    ejercicio.getSeries(), ejercicio.getRepeticiones(),
                    ejercicio.getNivelDificultad()
            });
        }
    }

    private void actualizarTablaHistorial() {
        modeloHistorial.setRowCount(0);
        for (int i = 0; i < historial.size(); i++) {
            Rutina rutina = historial.get(i);
            Cliente cliente = rutina.getCliente();
            modeloHistorial.addRow(new Object[]{
                    cliente.getNombre(), rutina.getNombreInstructor(),
                    cliente.getDiasSemana(), rutina.getNivel(),
                    cliente.getObjetivo()
            });
        }
    }

    private void limpiarFormularioEjercicio() {
        campoNombreEjercicio.setText("");
        campoGrupoMuscular.setText("");
        campoRepeticiones.setText("");
        comboSeries.setSelectedIndex(0);
        comboNivelEjercicio.setSelectedIndex(0);
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

    private Integer[] crearNumeros(int minimo, int maximo) {
        Integer[] numeros = new Integer[maximo - minimo + 1];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = minimo + i;
        }
        return numeros;
    }

    private TitledBorder crearBorde(String titulo) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(210, 214, 218)),
                titulo, TitledBorder.LEFT, TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 13), new Color(52, 58, 64));
    }

    private void agregarCampo(JPanel panel, String texto, java.awt.Component campo) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(etiqueta);
        panel.add(campo);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("SansSerif", Font.BOLD, 13));
        boton.setFocusPainted(false);
        return boton;
    }

    private void prepararTabla(JTable tabla) {
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowHeight(26);
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tabla.setFillsViewportHeight(true);
    }

    private void mostrarAlerta(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Datos inválidos",
                JOptionPane.WARNING_MESSAGE);
    }

    private class GenerarRutinaListener implements java.awt.event.ActionListener {
        private SistemaGUI sistema;

        public GenerarRutinaListener(SistemaGUI sistema) {
            this.sistema = sistema;
        }

        public void actionPerformed(java.awt.event.ActionEvent evento) {
            sistema.generarRutina();
        }
    }

    private class GuardarEjercicioListener implements java.awt.event.ActionListener {
        private SistemaGUI sistema;

        public GuardarEjercicioListener(SistemaGUI sistema) {
            this.sistema = sistema;
        }

        public void actionPerformed(java.awt.event.ActionEvent evento) {
            sistema.guardarEjercicio();
        }
    }

    private class TablaNoEditable extends DefaultTableModel {
        public TablaNoEditable(String[] columnas) {
            super(columnas, 0);
        }

        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    }
}
