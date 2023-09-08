package extremeF1.Views;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartView extends JFrame {

	private JPanel contentPane;

	public StartView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Start Button
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(50, 50, 100, 30);
		contentPane.add(btnStart);

		// Load Game Button
		JButton btnLoadGame = new JButton("Load Game");
		btnLoadGame.setBounds(50, 100, 100, 30);
		contentPane.add(btnLoadGame);

		// Exit Button
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(50, 150, 100, 30);
		contentPane.add(btnExit);

		// F1 Image
		JLabel lblImage = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("Resources/Images/startImage.jpg")).getImage();
		lblImage.setIcon(new ImageIcon(img));
		lblImage.setBounds(200, 50, 200, 130);
		contentPane.add(lblImage);
	}
}