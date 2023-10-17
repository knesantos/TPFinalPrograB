package Core.Entities.Events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import Core.Entities.Player;
import Core.Entities.DrivingMode;

public class DrivingModeButtonListener implements ActionListener {
    private String mode;
    private Player realPlayer;
    private JButton btnAggressive, btnNormal, btnDefensive;

    public DrivingModeButtonListener(String mode, Player realPlayer, JButton btnAggressive, JButton btnNormal, JButton btnDefensive) {
        this.mode = mode;
        this.realPlayer = realPlayer;
        this.btnAggressive = btnAggressive;
        this.btnNormal = btnNormal;
        this.btnDefensive = btnDefensive;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        realPlayer.setDrivingMode(new DrivingMode(mode));  // Asume que tienes un método setDrivingMode

        // Reset all buttons to default state
        btnAggressive.setEnabled(true);
        btnNormal.setEnabled(true);
        btnDefensive.setEnabled(true);

        // Disable the clicked button to indicate it's the active mode
        ((JButton)e.getSource()).setEnabled(false);
    }
}