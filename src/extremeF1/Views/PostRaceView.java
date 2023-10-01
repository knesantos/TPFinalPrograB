package extremeF1.Views;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import Core.Entities.Carrera;
import Core.Entities.Jugador;
import Core.Entities.Piloto;

public class PostRaceView extends JFrame {
    private Carrera carrera;
    Map<Integer, Double> playersTimes = new HashMap<>();
    private JButton btnContinuar;
    
    public PostRaceView(Carrera carrera) {
        this.carrera = carrera;
        playersTimes = carrera.getTimes();
        // Configuración de la ventana
        setTitle("Resultados de la Carrera");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

        // Título de la carrera
        JLabel lblCarrera = new JLabel("Carrera: " + carrera.getCircuito().getNombre());
        lblCarrera.setFont(new Font("Arial", Font.BOLD, 24));
        lblCarrera.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblCarrera, BorderLayout.NORTH);

        // Lista de pilotos, posiciones y tiempos
        JTextArea txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        StringBuilder sb = new StringBuilder();
        sb.append("Resultados de la Carrera:\n");
        sb.append("=========================\n");
        int i =0;
        for (Jugador player : carrera.getPlayers()) {
            sb.append("Piloto: ").append(player.getPiloto().getnombre()).append("\n");
            sb.append("Posición: ").append(++i).append("\n");
            sb.append("Tiempo: ").append(playersTimes.get(player.getId())).append(" segundos\n");
            sb.append("------------------------\n");
        }
        txtInfo.setText(sb.toString());
        panel.add(new JScrollPane(txtInfo), BorderLayout.CENTER);

        // Botón para continuar
        btnContinuar = new JButton("Continuar");
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