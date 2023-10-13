package extremeF1.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.border.EmptyBorder;


public class RaceView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel,nameplayer,namerace;
	private List<Piloto> Pilotos = new ArrayList<>();
	private List<Auto> Autos = new ArrayList<>();
	private ImageIcon imagen;
	private Icon icono;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExampleView frame = new ExampleView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void ajustarImagen(JLabel jlabel, String ruta) {
		this.imagen= new ImageIcon(ruta);
		this.icono= new ImageIcon(
				this.imagen.getImage().getScaledInstance(
						jlabel.getWidth(),
						jlabel.getHeight(),
						Image.SCALE_DEFAULT
						)
				);
		jlabel.setIcon(this.icono);
		this.repaint();
	}

	public RaceView(List<Piloto> listpilotos,List<Auto> listaautos) {
		Pilotos = listpilotos;
		Autos = listaautos;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0, 0, getWidth(),getHeight());

		setContentPane(contentPane);
		
		setTitle("ExtremeF1");
		setSize(500, 500);
		getContentPane().setBackground(Color.green);
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(0, 0, getWidth(), getHeight());
		this.ajustarImagen(this.lblNewLabel, "C:\\Users\\NACHO ARCIDIÁCONO\\ExtremeF1\\TPFinalPrograB\\src\\Resources\\Images\\imagencarrera.jpeg");
	
		this.setLocationRelativeTo(this);
		contentPane.add(lblNewLabel);
        
	}
	
}
