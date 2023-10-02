package extremeF1.Views;

import java.awt.Color;
import Core.Entities.Race;
import Core.Entities.Real;
import Core.Entities.Events.LoadFuelEvent;
import Core.Entities.Events.LoadFuelListener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class PitsView extends JFrame implements PitsViewInterface{
	private JPanel panel;
	private JButton BtmChangeTires,BtmLoadFuel,BtmRepairEngine;
	private JLabel namerace,nameplayer,tirewear,carcondition,fuel,positionts;
	private Font font;
	private Race Race;
	private Real Player;
	private LoadFuelListener loadfuellistener;
	public PitsView (Race race, Real player) {
		Race = race;
		Player = player;
		setSize(1500, 1500);
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
		tirewear.setText("Desgaste de Neumaticos: "+ player.getCar().getTire().getWear());
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
		fuel.setText("Combustible: "+ player.getCar().getFuel());
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
		BtmChangeTires.setText("Cambiar Neumaticos");
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
		setLoadFuelListener(loadfuellistener);
		BtmLoadFuel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel text = new JLabel();
				text.setBounds(700, 700, 400, 100);
				text.setText("Eliga la cantidad");
				text.setFont(new Font("Arial",Font.BOLD,20));
				text.setHorizontalAlignment(JLabel.CENTER);
				text.setVerticalAlignment(JLabel.CENTER);
				text.setBackground(Color.white);
				text.setOpaque(true);
				panel.add(text);
				
				JButton BtmFuel1 = new JButton();
				BtmFuel1.setBounds(700, 800, 100, 70);
				BtmFuel1.setText("1/4");
				BtmFuel1.setFont(new Font("Arial",Font.BOLD,20));
				BtmFuel1.setHorizontalAlignment(JLabel.CENTER);
				BtmFuel1.setVerticalAlignment(JLabel.CENTER);
				panel.add(BtmFuel1);
				JButton BtmFuel2 = new JButton();
				BtmFuel2.setBounds(800, 800, 100, 70);
				BtmFuel2.setText("1/2");
				BtmFuel2.setFont(new Font("Arial",Font.BOLD,20));
				BtmFuel2.setHorizontalAlignment(JLabel.CENTER);
				BtmFuel2.setVerticalAlignment(JLabel.CENTER);
				panel.add(BtmFuel2);
				JButton BtmFuel3 = new JButton();
				BtmFuel3.setBounds(900, 800, 100, 70);
				BtmFuel3.setText("3/4");
				BtmFuel3.setFont(new Font("Arial",Font.BOLD,20));
				BtmFuel3.setHorizontalAlignment(JLabel.CENTER);
				BtmFuel3.setVerticalAlignment(JLabel.CENTER);
				panel.add(BtmFuel3);
				JButton BtmFuel4 = new JButton();
				BtmFuel4.setBounds(1000, 800, 100, 70);
				BtmFuel4.setText("Full");
				BtmFuel4.setFont(new Font("Arial",Font.BOLD,20));
				BtmFuel4.setHorizontalAlignment(JLabel.CENTER);
				BtmFuel4.setVerticalAlignment(JLabel.CENTER);
				panel.add(BtmFuel4);
				repaint();
				
				BtmFuel1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						loadfuellistener.listenerLoadFuelEvent(new LoadFuelEvent(player.getCar(), 25));
						fuel.setText("Combustible: "+ player.getCar().getFuel());
						panel.remove(BtmFuel1);
						panel.remove(BtmFuel2);
						panel.remove(BtmFuel3);
						panel.remove(BtmFuel4);
						panel.remove(text);
						repaint();
					}
					
				});
				BtmFuel2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						loadfuellistener.listenerLoadFuelEvent(new LoadFuelEvent(player.getCar(), 50));
						fuel.setText("Combustible: "+ player.getCar().getFuel());
						panel.remove(BtmFuel1);
						panel.remove(BtmFuel2);
						panel.remove(BtmFuel3);
						panel.remove(BtmFuel4);
						panel.remove(text);
						repaint();
					}
					
				});
				BtmFuel3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						loadfuellistener.listenerLoadFuelEvent(new LoadFuelEvent(player.getCar(), 75));
						fuel.setText("Combustible: "+ player.getCar().getFuel());
						panel.remove(BtmFuel1);
						panel.remove(BtmFuel2);
						panel.remove(BtmFuel3);
						panel.remove(BtmFuel4);
						panel.remove(text);
						repaint();
					}
					
				});
				BtmFuel4.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						loadfuellistener.listenerLoadFuelEvent(new LoadFuelEvent(player.getCar(), 100));
						fuel.setText("Combustible: "+ player.getCar().getFuel());
						panel.remove(BtmFuel1);
						panel.remove(BtmFuel2);
						panel.remove(BtmFuel3);
						panel.remove(BtmFuel4);
						panel.remove(text);
						repaint();
					}
					
				});
				
			}
			
		});
		
		repaint();
	
	}
	@Override
	public void setLoadFuelListener(LoadFuelListener listener) {
		// TODO Auto-generated method stub
		loadfuellistener = listener;
	}
}
