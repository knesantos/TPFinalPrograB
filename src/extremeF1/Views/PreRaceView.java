package extremeF1.Views;

import javax.swing.*;
import java.awt.*;
import Core.Entities.Race;
import Core.Entities.Player;
import Core.Entities.Driver;

public class PreRaceView extends JFrame {
    private Race Race;
    
    private JButton btnContinuar;
    
    public PreRaceView(Race Race) {
        this.Race = Race;

        // Configuración de la ventana
        setTitle("Previa a la Race");
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