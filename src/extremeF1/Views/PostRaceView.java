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
    private Race race;
    private Championship championship;
    private Map<Integer, Double> playersTimes = new HashMap<>();
    private JButton btnContinuar;

    public PostRaceView(Race race, Championship championship) {
        this.race = race;
        this.playersTimes = race.getTimes();
        this.championship = championship;

        // Panel principal
        setLayout(new BorderLayout());
        setBackground(new Color(44, 62, 80)); // Fondo oscuro

        // Título de la carrera
        JLabel lblRace = new JLabel("Carrera: " + race.getCircuit().getName());
        lblRace.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        lblRace.setForeground(new Color(236, 240, 241)); // Texto claro
        lblRace.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblRace, BorderLayout.NORTH);

        // Lista de pilotos, posiciones y tiempos
        JTextArea txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        txtInfo.setForeground(new Color(236, 240, 241)); // Texto claro
        txtInfo.setBackground(new Color(44, 62, 80)); // Fondo oscuro
        StringBuilder sb = new StringBuilder();
        sb.append("Resultados de la Carrera:\n");
        sb.append("=========================\n");
        int i = 1;
        for (Player player : race.getPlayers()) {
            sb.append("Piloto: ").append(player.getDriver().getName()).append("\n");
            sb.append("Posición: ").append(i++).append("\n");

            double timeInSeconds = playersTimes.get(player.getId());
            int minutes = (int) (timeInSeconds / 60);
            int seconds = (int) (timeInSeconds % 60);
            int millis = (int) ((timeInSeconds * 1000) % 1000);

            sb.append("Tiempo: ").append(minutes).append("m ").append(seconds).append("s ").append(millis).append("ms\n");
            sb.append("Recorrido: ").append(player.getCar().getKilometersDriven()).append(" Km\n");
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