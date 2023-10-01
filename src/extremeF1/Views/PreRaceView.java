package extremeF1.Views;

import javax.swing.*;
import java.awt.*;
import Core.Entities.Carrera;
import Core.Entities.Jugador;
import Core.Entities.Piloto;

public class PreRaceView extends JFrame {
    private Carrera carrera;
    
    private JButton btnContinuar;
    
    public PreRaceView(Carrera carrera) {
        this.carrera = carrera;

        // Configuración de la ventana
        setTitle("Previa a la Carrera");
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

        // Lista de pilotos y autos
        JTextArea txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        StringBuilder sb = new StringBuilder();
        sb.append("Pilotos y sus Autos:\n");
        sb.append("====================\n");
        for (Jugador player : carrera.getPlayers()) {
            sb.append("Piloto: ").append(player.getPiloto().getnombre()).append("\n");
            sb.append("Auto: ").append(player.getAuto().getModelo()).append("\n");
            sb.append("--------------------\n");
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