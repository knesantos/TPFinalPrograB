package extremeF1.Views;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import Core.Entities.Race;
import Core.Entities.Championship;
import Core.Entities.Player;
import Core.Entities.Driver;

public class PostRaceView extends JFrame {
    private Race Race;
    private Championship championship;
    Map<Integer, Double> playersTimes = new HashMap<>();
    private JButton btnContinuar;
    
    public PostRaceView(Race Race,Championship championship) {
        this.Race = Race;
        playersTimes = Race.getTimes();
        this.championship=championship;
        // Configuración de la ventana
        setTitle("Resultados de la Race");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(44, 62, 80)); // Fondo oscuro

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(44, 62, 80)); // Fondo oscuro
        add(panel);

        // Título de la Race
        JLabel lblRace = new JLabel("Race: " + Race.getCircuit().getName());
        lblRace.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        lblRace.setForeground(new Color(236, 240, 241)); // Texto claro
        lblRace.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblRace, BorderLayout.NORTH);

        // Lista de Drivers, posiciones y tiempos
        JTextArea txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        txtInfo.setForeground(new Color(236, 240, 241)); // Texto claro
        txtInfo.setBackground(new Color(44, 62, 80)); // Fondo oscuro
        StringBuilder sb = new StringBuilder();
        sb.append("Resultados de la Race:\n");
        sb.append("=========================\n");
        int i = 0;
        for (Player player : Race.getPlayers()) {
            sb.append("Driver: ").append(player.getDriver().getName()).append("\n");
            sb.append("Posición: ").append(++i).append("\n");
            sb.append("Tiempo: ").append(playersTimes.get(player.getId())).append(" segundos\n");
            sb.append("Recorrido: ").append(player.getCar().getKilometersDriven()).append(" Km");
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