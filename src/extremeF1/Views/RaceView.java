package extremeF1.Views;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import Core.Entities.Car;
import Core.Entities.Driver;
import Core.Entities.Player;
import Core.Entities.Race;
import java.util.ArrayList;

public class RaceView extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel leftPanel, rightPanel, centerPanel;
    private JLabel lblRaceName, lblPlayerName, lblFuel, lblTireStatus, lblCarStatus;
    private JLabel lblLapNumber, lblDrivingStyle, lblWeather;
    private JList<String> rankingList;
    private DefaultListModel<String> rankingModel;
    private Race race;

    public RaceView(Race race) {
        this.race = race;
        setLayout(new BorderLayout());

        // Estilos
        Font titleFont = new Font("Arial", Font.BOLD, 16);
        Font infoFont = new Font("Arial", Font.PLAIN, 14);

        // Panel izquierdo
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(5, 1));
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        lblRaceName = new JLabel("Nombre de la Carrera: " + race.getCircuit().getName());
        lblPlayerName = new JLabel("Nombre del Jugador: " + race.getRealPlayer().getName());
        lblFuel = new JLabel("Combustible: " + race.getRealPlayer().getCar().getFuel());
        lblTireStatus = new JLabel("Estado de las Gomas: " + race.getRealPlayer().getCar().getTire().getWear());
        lblCarStatus = new JLabel("Estado del Auto: " + race.getRealPlayer().getCar().getHealth());

        lblRaceName.setFont(titleFont);
        lblPlayerName.setFont(infoFont);
        lblFuel.setFont(infoFont);
        lblTireStatus.setFont(infoFont);
        lblCarStatus.setFont(infoFont);

        leftPanel.add(lblRaceName);
        leftPanel.add(lblPlayerName);
        leftPanel.add(lblFuel);
        leftPanel.add(lblTireStatus);
        leftPanel.add(lblCarStatus);

        // Panel derecho
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3, 1));
        rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        lblLapNumber = new JLabel("Vuelta " + race.getActualLap() + "/" + race.getCircuit().getLapCount());
        lblDrivingStyle = new JLabel("Forma de Manejo: ");
        lblWeather = new JLabel("Estado del Clima: " + race.getRaceCondition().getCondition());

        lblLapNumber.setFont(titleFont);
        lblDrivingStyle.setFont(infoFont);
        lblWeather.setFont(infoFont);

        rightPanel.add(lblLapNumber);
        rightPanel.add(lblDrivingStyle);
        rightPanel.add(lblWeather);

        // Panel central (Ranking)
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblRanking = new JLabel("Ranking");
        lblRanking.setFont(titleFont);
        centerPanel.add(lblRanking, BorderLayout.NORTH);

        rankingModel = new DefaultListModel<>();
        rankingList = new JList<>(rankingModel);
        rankingList.setFont(infoFont);
        centerPanel.add(new JScrollPane(rankingList), BorderLayout.CENTER);

        // Añadir paneles al panel principal
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void updateInfo(Race updatedRace) {
        // Actualiza la información de la carrera
        this.race = updatedRace;

        // Actualiza los JLabels con la nueva información
        lblRaceName.setText("Nombre de la Carrera: " + race.getCircuit().getName());
        lblPlayerName.setText("Nombre del Jugador: " + race.getRealPlayer().getName());
        lblFuel.setText("Combustible: " + race.getRealPlayer().getCar().getFuel());
        lblTireStatus.setText("Estado de las Gomas: " + race.getRealPlayer().getCar().getTire().getWear());
        lblCarStatus.setText("Estado del Auto: " + race.getRealPlayer().getCar().getHealth());
        lblLapNumber.setText("Vuelta " + race.getActualLap() + "/" + race.getCircuit().getLapCount());
        lblWeather.setText("Estado del Clima: " + race.getRaceCondition().getCondition());

        // Actualiza el ranking
        rankingModel.clear();
        for (Player player : race.getPlayers()) {  
            rankingModel.addElement(player.getName());
        }

        // Refresca el panel para mostrar las actualizaciones
        this.revalidate();
        this.repaint();
    }
}