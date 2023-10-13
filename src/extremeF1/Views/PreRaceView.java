package extremeF1.Views;

import javax.swing.*;
import java.awt.*;
import Core.Entities.Race;
import Core.Entities.Player;
import Core.Entities.Driver;

public class PreRaceView extends JPanel {
    private Race Race;
    private JButton btnContinuar;

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