public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SistemaGUI sistema = new SistemaGUI();
                sistema.setVisible(true);
            }
        });
    }
}
