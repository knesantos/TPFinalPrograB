package extremeF1.Views;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import Core.Entities.Race;

import Core.Entities.Player;
import Core.Entities.Driver;

public class PreRaceView extends JPanel implements java.io.Serializable{
    private Race Race;
    private JButton btnContinuar;
    private JLabel lblWeather, lblTemperature, lblPrecipitation;

    public interface ContinueButtonListener {
        void onContinueButtonPressed();
    }

    private ContinueButtonListener continueButtonListener;
    public void setContinueButtonListener(ContinueButtonListener listener) {
        this.continueButtonListener = listener;
    }
    
    
    public PreRaceView(Race Race) {
        this.Race = Race;

        // Panel principal
        setLayout(new BorderLayout());
        setBackground(new Color(44, 62, 80)); // Fondo oscuro

        // Título de la Race
        JLabel lblRace = new JLabel("Race: " + Race.getCircuit().getName());
        lblRace.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
        lblRace.setForeground(new Color(236, 240, 241)); // Texto claro
        lblRace.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblRace, BorderLayout.NORTH);

        // Lista de Drivers y Cars
        JTextArea txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        txtInfo.setForeground(new Color(236, 240, 241)); // Texto claro
        txtInfo.setBackground(new Color(44, 62, 80)); // Fondo oscuro
        StringBuilder sb = new StringBuilder();
        sb.append("Drivers y sus Cars:\n");
        sb.append("====================\n");
        for (Player player : Race.getPlayers()) {
            sb.append("Player: ").append(player.getName()).append("\n");
            sb.append("Driver: ").append(player.getDriver().getName()).append("\n");
            sb.append("Car: ").append(player.getCar().getModel()).append("\n");
            sb.append("--------------------\n");
        }
        txtInfo.setText(sb.toString());
        add(new JScrollPane(txtInfo), BorderLayout.CENTER);
        
        
        TitledBorder title = BorderFactory.createTitledBorder("Climate condition");
        title.setTitleColor(new Color(236, 240, 241)); 

        JPanel weatherPanel = new JPanel();
        weatherPanel.setLayout(new GridLayout(3, 1));  
        weatherPanel.setBorder(title);
        weatherPanel.setBackground(new Color(44, 62, 80)); 

        lblWeather = new JLabel("Clima: " + Race.getRaceCondition().getCondition());
        lblTemperature = new JLabel("Temperatura: " + Race.getRaceCondition().getTemperature() + "°C");
        lblPrecipitation = new JLabel("Precipitaciones: " + Race.getRaceCondition().getPrecipitation() + "%");

        lblWeather.setForeground(new Color(236, 240, 241)); // Texto claro
        lblTemperature.setForeground(new Color(236, 240, 241)); // Texto claro
        lblPrecipitation.setForeground(new Color(236, 240, 241)); // Texto claro

        weatherPanel.add(lblWeather);
        weatherPanel.add(lblTemperature);
        weatherPanel.add(lblPrecipitation);

        JPanel additionalPanel = new JPanel(null);
        additionalPanel.setBackground(new Color(44, 62, 80)); // Fondo oscuro
        additionalPanel.setPreferredSize(new Dimension(220, 120));  // Establecer un tamaño preferido para el panel adicional
        weatherPanel.setBounds(10, 10, 200, 100);  // x, y, ancho, alto
        additionalPanel.add(weatherPanel);

        // Añadir el panel adicional al panel principal
        add(additionalPanel, BorderLayout.EAST);
        
        // Botón para continuar
        btnContinuar = new JButton("Continuar");
        btnContinuar.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        btnContinuar.setBackground(new Color(39, 174, 96)); // Botón verde
        btnContinuar.setForeground(new Color(236, 240, 241)); // Texto claro
        add(btnContinuar, BorderLayout.SOUTH);
        
        
        btnContinuar.addActionListener(e -> {
            if (continueButtonListener != null) {
                continueButtonListener.onContinueButtonPressed();
            }
        });
        
    }

    public JButton getBtnContinuar() {
        return btnContinuar;
    }
}