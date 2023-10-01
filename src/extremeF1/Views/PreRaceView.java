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

        // Lista de pilotos y autos
        JTextArea txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
        txtInfo.setForeground(new Color(236, 240, 241)); // Texto claro
        txtInfo.setBackground(new Color(44, 62, 80)); // Fondo oscuro
        StringBuilder sb = new StringBuilder();
        sb.append("Pilotos y sus Autos:\n");
        sb.append("====================\n");
        for (Jugador player : carrera.getPlayers()) {
        	 sb.append("Jugador: ").append(player.getNombre()).append("\n");
            sb.append("Piloto: ").append(player.getPiloto().getnombre()).append("\n");
            sb.append("Auto: ").append(player.getAuto().getModelo()).append("\n");
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