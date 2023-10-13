package extremeF1.Views;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PrincipalView extends JFrame implements PrincipalViewInterface {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public PrincipalView() {
        // Maximizar la ventana
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setTitle("ExtremeF1");
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.black);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel);

        pack();
        setVisible(true);
    }

    public void addPanel(JPanel panel, String name) {
        cardPanel.add(panel, name);
    }

    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }
}