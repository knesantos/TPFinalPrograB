package extremeF1.Views;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Core.Entities.Car;
import Core.Entities.Driver;
import Core.Entities.Player;
import Core.Entities.Race;
import java.util.ArrayList;

public class RaceView extends JPanel {
    private static final long serialVersionUID = 1L;
    private JPanel leftPanel, rightPanel, centerPanel;
    private JLabel lblRaceName, lblPlayerName, lblFuel, lblTireStatus, lblCarStatus;
    private JLabel lblLapNumber, lblDrivingStyle, lblWeather,lblTemperature, lblPrecipitation;;
    private JList<String> rankingList;
    private DefaultListModel<String> rankingModel;
    private Race race;

    public RaceView(Race race) {
        this.race = race;
        setLayout(new BorderLayout());

        // Estilos
        Font titleFont = new Font("Comic Sans MS", Font.BOLD, 36);
        Font infoFont = new Font("Comic Sans MS", Font.PLAIN, 18);
        
        
     // Panel para el título de la carrera
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(44, 62, 80)); // Fondo oscuro

        lblRaceName = new JLabel("Carrera: " + race.getCircuit().getName());
        lblRaceName.setFont(titleFont);
        lblRaceName.setForeground(new Color(236, 240, 241)); // Texto claro

        titlePanel.add(lblRaceName);

       
        
        // Panel izquierdo
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(5, 1));
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

      
        lblPlayerName = new JLabel("Jugador: " + race.getRealPlayer().getName());
        lblFuel = new JLabel("Combustible: " + race.getRealPlayer().getCar().getFuelState());
        lblTireStatus = new JLabel("Estado de las Gomas: " + race.getRealPlayer().getCar().getTire().getWear());
        lblCarStatus = new JLabel("Estado del Auto: " + race.getRealPlayer().getCar().getHealth());

        lblPlayerName.setFont(infoFont);
        lblFuel.setFont(infoFont);
        lblTireStatus.setFont(infoFont);
        lblCarStatus.setFont(infoFont);

        leftPanel.add(lblPlayerName);
        leftPanel.add(lblFuel);
        leftPanel.add(lblTireStatus);
        leftPanel.add(lblCarStatus);

        // Panel derecho
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(5, 1));  
        rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        lblLapNumber = new JLabel("Vuelta " + race.getActualLap() + "/" + race.getCircuit().getLapCount());
        lblDrivingStyle = new JLabel("Forma de Manejo: " + race.getRealPlayer().getDivingMode().getType());
       
        lblLapNumber.setFont(titleFont);
        lblDrivingStyle.setFont(infoFont);
      

        rightPanel.add(lblLapNumber);
        rightPanel.add(lblDrivingStyle);
      
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
        
        
        // Panel de clima
        TitledBorder title = BorderFactory.createTitledBorder("Climate condition");
        title.setTitleColor(new Color(236, 240, 241));

        JPanel weatherPanel = new JPanel();
        weatherPanel.setLayout(new GridLayout(3, 1));
        weatherPanel.setBorder(title);
        weatherPanel.setBackground(new Color(44, 62, 80));

        lblWeather = new JLabel("Clima: " + race.getRaceCondition().getCondition());
        lblTemperature = new JLabel("Temperatura: " + race.getRaceCondition().getTemperature() + "°C");
        lblPrecipitation = new JLabel("Precipitaciones: " + race.getRaceCondition().getPrecipitation() + "%");

        lblWeather.setForeground(new Color(236, 240, 241));
        lblTemperature.setForeground(new Color(236, 240, 241));
        lblPrecipitation.setForeground(new Color(236, 240, 241));

        weatherPanel.add(lblWeather);
        weatherPanel.add(lblTemperature);
        weatherPanel.add(lblPrecipitation);

        // Añadir el panel de clima al panel derecho
        rightPanel.add(weatherPanel);

        // Añadir paneles al panel principal
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);
        // Añadir el panel del título al panel principal en la parte superior
        add(titlePanel, BorderLayout.NORTH);
    }

    public void updateInfo(Race updatedRace) {
        // Actualiza la información de la carrera
        this.race = updatedRace;

        // Actualiza los JLabels con la nueva información
        lblRaceName.setText("Carrera: " + race.getCircuit().getName());
        lblPlayerName.setText("Jugador: " + race.getRealPlayer().getName());
        lblFuel.setText("Combustible: " + race.getRealPlayer().getCar().getFuelState());
        lblTireStatus.setText("Estado de las Gomas: " + race.getRealPlayer().getCar().getTire().getWear());
        lblCarStatus.setText("Estado del Auto: " + race.getRealPlayer().getCar().getHealth());
        lblLapNumber.setText("Vuelta " + race.getActualLap() + "/" + race.getCircuit().getLapCount());
        lblWeather.setText("Clima: " + race.getRaceCondition().getCondition());
        lblTemperature.setText("Temperatura: " + race.getRaceCondition().getTemperature() + "°C");  
        lblPrecipitation.setText("Precipitaciones: " + race.getRaceCondition().getPrecipitation() + "%");  
        lblDrivingStyle = new JLabel("Forma de Manejo: " + race.getRealPlayer().getDivingMode().getType());
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