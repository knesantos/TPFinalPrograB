package extremeF1.Views;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import Core.Entities.Carrera;
import Core.Entities.Championship;
import Core.Entities.Jugador;
import Core.Entities.Piloto;

public class PostRaceView extends JFrame {
    private Carrera carrera;
    private Championship championship;
    Map<Integer, Double> playersTimes = new HashMap<>();
    private JButton btnContinuar;
    
    public PostRaceView(Carrera carrera,Championship championship) {
        this.carrera = carrera;
        playersTimes = carrera.getTimes();
        this.championship=championship;
        // Configuración de la ventana
        setTitle("Resultados de la Carrera");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(44, 62, 80)); // Fondo oscuro

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(44, 62, 80)); // Fondo oscuro
        add(panel);

        // Título de la carrera
        JLabel lblCarrera = new JLabel("Carrera: " + carrera.getCircuito().getNombre());
        lblCarrera.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        lblCarrera.setForeground(new Color(236, 240, 241)); // Texto claro
        lblCarrera.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblCarrera, BorderLayout.NORTH);

        // Lista de pilotos, posiciones y tiempos
        JTextArea txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        txtInfo.setForeground(new Color(236, 240, 241)); // Texto claro
        txtInfo.setBackground(new Color(44, 62, 80)); // Fondo oscuro
        StringBuilder sb = new StringBuilder();
        sb.append("Resultados de la Carrera:\n");
        sb.append("=========================\n");
        int i = 0;
        for (Jugador player : carrera.getPlayers()) {
            sb.append("Piloto: ").append(player.getPiloto().getnombre()).append("\n");
            sb.append("Posición: ").append(++i).append("\n");
            sb.append("Tiempo: ").append(playersTimes.get(player.getId())).append(" segundos\n");
            sb.append("Recorrido: ").append(player.getAuto().getKilometrosRecorridos()).append(" Km");
            sb.append("------------------------\n");
        }
        txtInfo.setText(sb.toString());
        panel.add(new JScrollPane(txtInfo), BorderLayout.CENTER);

        // Botón para continuar
        btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        btnContinuar.setBackground(new Color(39, 174, 96)); // Botón verde
        btnContinuar.setForeground(new Color(236, 240, 241)); // Texto claro
        btnContinuar.addActionListener(e -> {
            // Este código se manejará en el controlador
        });
        panel.add(btnContinuar, BorderLayout.SOUTH);

        // Mostrar la ventana
        setVisible(true);
    }
    
    public JButton getBtnContinuar() {
        return btnContinuar;
    }
}