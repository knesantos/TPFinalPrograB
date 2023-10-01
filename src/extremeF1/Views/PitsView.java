package extremeF1.Views;

import java.awt.Color;
import Core.Entities.Race;
import Core.Entities.Real;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class PitsView extends JFrame{
	private JPanel panel;
	private JButton BtmChangeTires,BtmLoadFuel,BtmRepairEngine;
	private JLabel namerace,nameplayer,tirewear,carcondition,fuel,positionts;
	private Font font;
	private Race Race;
	private Real Player;

	public PitsView (Race Race, Real player) {
		Race = Race;
		Player = player;
		setSize(1500, 500);
		setTitle("ExtremeF1");
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.black);
		
		font = new Font("Arial",Font.BOLD,50);
		
		
		panel = new JPanel();
		this.getContentPane().add(panel);
		panel.setBackground(Color.gray);
		panel.setLayout(null);
		
		namerace = new JLabel();
		namerace.setBounds(0, 50, 600,100);
		namerace.setText(Race.getCircuit().getName());
		namerace.setBackground(Color.white);
		namerace.setOpaque(true);
		namerace.setFont(font);
		namerace.setHorizontalAlignment(JLabel.CENTER);
		namerace.setVerticalAlignment(JLabel.CENTER);
		panel.add(namerace);
		
		nameplayer = new JLabel();
		nameplayer.setBounds(0, 150, 600,100);
		nameplayer.setText("Jugador: "+ Player.getName());
		nameplayer.setBackground(Color.white);
		nameplayer.setOpaque(true);
		nameplayer.setFont(new Font("Arial",Font.BOLD,30));
		nameplayer.setHorizontalAlignment(JLabel.LEFT);
		nameplayer.setVerticalAlignment(JLabel.CENTER);
		panel.add(nameplayer);
		
		
		
		tirewear = new JLabel();
		tirewear.setBounds(0, 300, 400,100);
		tirewear.setText("Desgaste de Tires: "+ player.getCar().getTire().getWear());
		tirewear.setBackground(Color.white);
		tirewear.setOpaque(true);
		tirewear.setFont(new Font("Arial",Font.BOLD,25));
		tirewear.setHorizontalAlignment(JLabel.LEFT);
		tirewear.setVerticalAlignment(JLabel.CENTER);
		panel.add(tirewear);
		
		carcondition = new JLabel();
		carcondition.setBounds(0, 400, 400,100);
		carcondition.setText("Condicion motor: "+ player.getCar().gethealth());
		carcondition.setBackground(Color.white);
		carcondition.setOpaque(true);
		carcondition.setFont(new Font("Arial",Font.BOLD,25));
		carcondition.setHorizontalAlignment(JLabel.LEFT);
		carcondition.setVerticalAlignment(JLabel.CENTER);
		panel.add(carcondition);
		
		
		fuel = new JLabel();
		fuel.setBounds(0, 500, 400,100);
		fuel.setText("Fuel: "+ player.getCar().getFuel());
		fuel.setBackground(Color.white);
		fuel.setOpaque(true);
		fuel.setFont(new Font("Arial",Font.BOLD,25));
		fuel.setHorizontalAlignment(JLabel.LEFT);
		fuel.setVerticalAlignment(JLabel.CENTER);
		panel.add(fuel);
		
		positionts = new JLabel();
		positionts.setBounds(1300, 50, 400,100);
		positionts.setText("Posiciones Acutuales");
		positionts.setBackground(Color.white);
		positionts.setOpaque(true);
		positionts.setFont(new Font("Arial",Font.BOLD,25));
		positionts.setHorizontalAlignment(JLabel.CENTER);
		positionts.setVerticalAlignment(JLabel.CENTER);
		panel.add(positionts);
		//Botones//
		
		BtmChangeTires = new JButton();
		BtmChangeTires.setBounds(500, 300, 400, 100);
		BtmChangeTires.setText("Cambiar Tires");
		BtmChangeTires.setFont(new Font("Arial",Font.BOLD,20));
		BtmChangeTires.setHorizontalAlignment(JLabel.CENTER);
		BtmChangeTires.setVerticalAlignment(JLabel.CENTER);
		panel.add(BtmChangeTires);

		BtmRepairEngine = new JButton();
		BtmRepairEngine.setBounds(500, 400, 400, 100);
		BtmRepairEngine.setText("Reparar motor");
		BtmRepairEngine.setFont(new Font("Arial",Font.BOLD,20));
		BtmRepairEngine.setHorizontalAlignment(JLabel.CENTER);
		BtmRepairEngine.setVerticalAlignment(JLabel.CENTER);
		panel.add(BtmRepairEngine);
		
		BtmLoadFuel = new JButton();
		BtmLoadFuel.setBounds(500, 500, 400, 100);
		BtmLoadFuel.setText("Cargar Fuel");
		BtmLoadFuel.setFont(new Font("Arial",Font.BOLD,20));
		BtmLoadFuel.setHorizontalAlignment(JLabel.CENTER);
		BtmLoadFuel.setVerticalAlignment(JLabel.CENTER);
		panel.add(BtmLoadFuel);
		
	
	}
}
