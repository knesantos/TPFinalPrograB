package extremeF1.Views;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import Core.Entities.Race;
import Core.Entities.Championship;
import Core.Entities.Player;
import Core.Entities.Driver;

public class PostRaceView extends JPanel {
    private Race Race;
    private Championship championship;
    private Map<Integer, Double> playersTimes = new HashMap<>();
    private JButton btnContinuar;

    public PostRaceView(Race Race, Championship championship) {
        this.Race = Race;
        this.playersTimes = Race.getTimes();
        this.championship = championship;

        // Panel principal
        setLayout(new BorderLayout());
        setBackground(new Color(44, 62, 80)); // Fondo oscuro

        // Título de la Race
        JLabel lblRace = new JLabel("Race: " + Race.getCircuit().getName());
        lblRace.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        lblRace.setForeground(new Color(236, 240, 241)); // Texto claro
        lblRace.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblRace, BorderLayout.NORTH);

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
        add(new JScrollPane(txtInfo), BorderLayout.CENTER);

        // Botón para continuar
        btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        btnContinuar.setBackground(new Color(39, 174, 96)); // Botón verde
        btnContinuar.setForeground(new Color(236, 240, 241)); // Texto claro
        add(btnContinuar, BorderLayout.SOUTH);
    }

    public JButton getBtnContinuar() {
        return btnContinuar;
    }
}