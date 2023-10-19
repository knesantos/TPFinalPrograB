package extremeF1.Views;

import java.awt.Color;

import Core.Entities.Hard;
import Core.Entities.Medium;
import Core.Entities.Race;
import Core.Entities.Soft;
import Core.Entities.Player;
import Core.Entities.Wet;
import Core.Entities.Events.ChangeTireEvent;
import Core.Entities.Events.ChangeTireListener;
import Core.Entities.Events.LoadFuelEvent;
import Core.Entities.Events.LoadFuelListener;
import extremeF1.Controllers.Example.PitsViewController;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class PitsView extends JPanel implements PitsViewInterface{
	private JButton BtmChangeTires,BtmLoadFuel,BtmRepairEngine;
	private JLabel namerace,nameplayer,tirewear,carcondition,fuel,positionts;
	private Font font;
	private Race Race;
	private Player Player;
	private LoadFuelListener loadfuellistener;
	private ChangeTireListener changetirelistener;
	 private PitsViewController controller; 
	 private JButton btnResumeRace;
	
	
	public PitsView (Race race, Player player,PitsViewController controller) {
		Race = race;
		Player = player;
		this.controller = controller;
		setSize(1500, 1500);
		this.setLayout(null);
		
		font = new Font("Arial",Font.BOLD,50);
		
		
		namerace = new JLabel();
		namerace.setBounds(0, 50, 600,100);
		namerace.setText(Race.getCircuit().getName());
		namerace.setBackground(Color.white);
		namerace.setOpaque(true);
		namerace.setFont(font);
		namerace.setHorizontalAlignment(JLabel.CENTER);
		namerace.setVerticalAlignment(JLabel.CENTER);
		this.add(namerace);
		
		nameplayer = new JLabel();
		nameplayer.setBounds(0, 150, 600,100);
		nameplayer.setText("Jugador: "+ Player.getName());
		nameplayer.setBackground(Color.white);
		nameplayer.setOpaque(true);
		nameplayer.setFont(new Font("Arial",Font.BOLD,30));
		nameplayer.setHorizontalAlignment(JLabel.LEFT);
		nameplayer.setVerticalAlignment(JLabel.CENTER);
		this.add(nameplayer);
		
		
		
		tirewear = new JLabel();
		tirewear.setBounds(0, 300, 400,100);
		tirewear.setText("Desgaste de Neumaticos: "+ player.getCar().getTire().getWear());
		tirewear.setBackground(Color.white);
		tirewear.setOpaque(true);
		tirewear.setFont(new Font("Arial",Font.BOLD,25));
		tirewear.setHorizontalAlignment(JLabel.LEFT);
		tirewear.setVerticalAlignment(JLabel.CENTER);
		this.add(tirewear);
		
		carcondition = new JLabel();
		carcondition.setBounds(0, 400, 400,100);
		carcondition.setText("Condicion motor: "+ player.getCar().getHealth());
		carcondition.setBackground(Color.white);
		carcondition.setOpaque(true);
		carcondition.setFont(new Font("Arial",Font.BOLD,25));
		carcondition.setHorizontalAlignment(JLabel.LEFT);
		carcondition.setVerticalAlignment(JLabel.CENTER);
		this.add(carcondition);
		
		
		fuel = new JLabel();
		fuel.setBounds(0, 500, 400,100);
		fuel.setText("Combustible: "+ player.getCar().getFuel());
		fuel.setBackground(Color.white);
		fuel.setOpaque(true);
		fuel.setFont(new Font("Arial",Font.BOLD,25));
		fuel.setHorizontalAlignment(JLabel.LEFT);
		fuel.setVerticalAlignment(JLabel.CENTER);
		this.add(fuel);
		
		positionts = new JLabel();
		positionts.setBounds(1300, 50, 400,100);
		positionts.setText("Posiciones Acutuales");
		positionts.setBackground(Color.white);
		positionts.setOpaque(true);
		positionts.setFont(new Font("Arial",Font.BOLD,25));
		positionts.setHorizontalAlignment(JLabel.CENTER);
		positionts.setVerticalAlignment(JLabel.CENTER);
		this.add(positionts);
		//Botones//
		
		BtmChangeTires = new JButton();
		BtmChangeTires.setBounds(500, 300, 400, 100);
		BtmChangeTires.setText("Cambiar Neumaticos");
		BtmChangeTires.setFont(new Font("Arial",Font.BOLD,20));
		BtmChangeTires.setHorizontalAlignment(JLabel.CENTER);
		BtmChangeTires.setVerticalAlignment(JLabel.CENTER);
		this.add(BtmChangeTires);
		setChangeTireListener(changetirelistener);
		BtmChangeTires.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BtmRepairEngine.setEnabled(false);
				BtmChangeTires.setEnabled(false);
				BtmLoadFuel.setEnabled(false);
				JLabel text = new JLabel();
				text.setBounds(700, 700, 400, 100);
				text.setText("Eliga el tipo");
				text.setFont(new Font("Arial",Font.BOLD,20));
				text.setHorizontalAlignment(JLabel.CENTER);
				text.setVerticalAlignment(JLabel.CENTER);
				text.setBackground(Color.white);
				text.setOpaque(true);
				add(text);
				JButton BtmTiresMedium = new JButton();
				BtmTiresMedium.setBounds(800, 800, 100, 70);
				BtmTiresMedium.setText("Medium");
				BtmTiresMedium.setFont(new Font("Arial",Font.BOLD,15));
				BtmTiresMedium.setHorizontalAlignment(JLabel.CENTER);
				BtmTiresMedium.setVerticalAlignment(JLabel.CENTER);
				add(BtmTiresMedium);
				JButton BtmTiresHard = new JButton();
				BtmTiresHard.setBounds(900, 800, 100, 70);
				BtmTiresHard.setText("Hard");
				BtmTiresHard.setFont(new Font("Arial",Font.BOLD,20));
				BtmTiresHard.setHorizontalAlignment(JLabel.CENTER);
				BtmTiresHard.setVerticalAlignment(JLabel.CENTER);
				add(BtmTiresHard);
				JButton BtmTiresSoft = new JButton();
				BtmTiresSoft.setBounds(700, 800, 100, 70);
				BtmTiresSoft.setText("Soft");
				BtmTiresSoft.setFont(new Font("Arial",Font.BOLD,20));
				BtmTiresSoft.setHorizontalAlignment(JLabel.CENTER);
				BtmTiresSoft.setVerticalAlignment(JLabel.CENTER);
				add(BtmTiresSoft);
				JButton BtmTiresWet = new JButton();
				BtmTiresWet.setBounds(1000, 800, 100, 70);
				BtmTiresWet.setText("Lluvia");
				BtmTiresWet.setFont(new Font("Arial",Font.BOLD,20));
				BtmTiresWet.setHorizontalAlignment(JLabel.CENTER);
				BtmTiresWet.setVerticalAlignment(JLabel.CENTER);
				add(BtmTiresWet);
				
				
				repaint();
				
				BtmTiresMedium.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						BtmRepairEngine.setEnabled(true);
						BtmChangeTires.setEnabled(true);
						BtmLoadFuel.setEnabled(true);
						remove(BtmTiresMedium);
						remove(BtmTiresWet);
						remove(BtmTiresHard);
						remove(BtmTiresSoft);
						remove(text);
						changetirelistener.listenerChangeTireEvent(new ChangeTireEvent(new Medium(50,50,50,50,50)));  
						tirewear.setText("Desgaste de Neumaticos: "+ player.getCar().getTire().getWear());
						controller.updateView();
						repaint();
					}
					
				});
				BtmTiresWet.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						BtmRepairEngine.setEnabled(true);
						BtmChangeTires.setEnabled(true);
						BtmLoadFuel.setEnabled(true);
						remove(BtmTiresMedium);
						remove(BtmTiresWet);
						remove(BtmTiresHard);
						remove(BtmTiresSoft);
						remove(text);
						changetirelistener.listenerChangeTireEvent(new ChangeTireEvent(new Wet(50,50,50,50,50))); 
						tirewear.setText("Desgaste de Neumaticos: "+ player.getCar().getTire().getWear());
						controller.updateView();
						repaint();
					}
					
				});
				BtmTiresHard.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						BtmRepairEngine.setEnabled(true);
						BtmChangeTires.setEnabled(true);
						BtmLoadFuel.setEnabled(true);
						remove(BtmTiresMedium);
						remove(BtmTiresWet);
						remove(BtmTiresHard);
						remove(BtmTiresSoft);
						remove(text);
						changetirelistener.listenerChangeTireEvent(new ChangeTireEvent(new Hard(50,50,50,50,50))); 
						tirewear.setText("Desgaste de Neumaticos: "+ player.getCar().getTire().getWear());
						controller.updateView();
						repaint();
					}
					
				});
				BtmTiresSoft.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						BtmRepairEngine.setEnabled(true);
						BtmChangeTires.setEnabled(true);
						BtmLoadFuel.setEnabled(true);
						remove(BtmTiresMedium);
						remove(BtmTiresWet);
						remove(BtmTiresHard);
						remove(BtmTiresSoft);
						remove(text);
						changetirelistener.listenerChangeTireEvent(new ChangeTireEvent(new Soft(50,50,50,50,50))); 
						tirewear.setText("Desgaste de Neumaticos: "+ player.getCar().getTire().getWear());
						controller.updateView();
						repaint();
					}
					
				});
				
			}
			                     
		});
		BtmRepairEngine = new JButton();
		BtmRepairEngine.setBounds(500, 400, 400, 100);
		BtmRepairEngine.setText("Reparar motor");
		BtmRepairEngine.setFont(new Font("Arial",Font.BOLD,20));
		BtmRepairEngine.setHorizontalAlignment(JLabel.CENTER);
		BtmRepairEngine.setVerticalAlignment(JLabel.CENTER);
		this.add(BtmRepairEngine);
		
		BtmLoadFuel = new JButton();
		BtmLoadFuel.setBounds(500, 500, 400, 100);
		BtmLoadFuel.setText("Cargar Fuel");
		BtmLoadFuel.setFont(new Font("Arial",Font.BOLD,20));
		BtmLoadFuel.setHorizontalAlignment(JLabel.CENTER);
		BtmLoadFuel.setVerticalAlignment(JLabel.CENTER);
		this.add(BtmLoadFuel);
		setLoadFuelListener(loadfuellistener);
		BtmLoadFuel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BtmRepairEngine.setEnabled(false);
				BtmChangeTires.setEnabled(false);
				BtmLoadFuel.setEnabled(false);
				JLabel text = new JLabel();
				text.setBounds(700, 700, 400, 100);
				text.setText("Eliga la cantidad");
				text.setFont(new Font("Arial",Font.BOLD,20));
				text.setHorizontalAlignment(JLabel.CENTER);
				text.setVerticalAlignment(JLabel.CENTER);
				text.setBackground(Color.white);
				text.setOpaque(true);
				add(text);
				
				JButton BtmFuel1 = new JButton();
				BtmFuel1.setBounds(700, 800, 100, 70);
				BtmFuel1.setText("1/4");
				BtmFuel1.setFont(new Font("Arial",Font.BOLD,20));
				BtmFuel1.setHorizontalAlignment(JLabel.CENTER);
				BtmFuel1.setVerticalAlignment(JLabel.CENTER);
				add(BtmFuel1);
				JButton BtmFuel2 = new JButton();
				BtmFuel2.setBounds(800, 800, 100, 70);
				BtmFuel2.setText("1/2");
				BtmFuel2.setFont(new Font("Arial",Font.BOLD,20));
				BtmFuel2.setHorizontalAlignment(JLabel.CENTER);
				BtmFuel2.setVerticalAlignment(JLabel.CENTER);
				add(BtmFuel2);
				JButton BtmFuel3 = new JButton();
				BtmFuel3.setBounds(900, 800, 100, 70);
				BtmFuel3.setText("3/4");
				BtmFuel3.setFont(new Font("Arial",Font.BOLD,20));
				BtmFuel3.setHorizontalAlignment(JLabel.CENTER);
				BtmFuel3.setVerticalAlignment(JLabel.CENTER);
				add(BtmFuel3);
				JButton BtmFuel4 = new JButton();
				BtmFuel4.setBounds(1000, 800, 100, 70);
				BtmFuel4.setText("Full");
				BtmFuel4.setFont(new Font("Arial",Font.BOLD,20));
				BtmFuel4.setHorizontalAlignment(JLabel.CENTER);
				BtmFuel4.setVerticalAlignment(JLabel.CENTER);
				add(BtmFuel4);
				controller.updateView();
				repaint();
				
				BtmFuel1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						loadfuellistener.listenerLoadFuelEvent(new LoadFuelEvent(player.getCar(), 25));
						fuel.setText("Combustible: "+ player.getCar().getFuel());
						remove(BtmFuel1);
						remove(BtmFuel2);
						remove(BtmFuel3);
						remove(BtmFuel4);
						remove(text);
						BtmRepairEngine.setEnabled(true);
						BtmChangeTires.setEnabled(true);
						BtmLoadFuel.setEnabled(true);
						controller.updateView();
						repaint();
					}
					
				});
				BtmFuel2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						loadfuellistener.listenerLoadFuelEvent(new LoadFuelEvent(player.getCar(), 50));
						fuel.setText("Combustible: "+ player.getCar().getFuel());
						remove(BtmFuel1);
						remove(BtmFuel2);
						remove(BtmFuel3);
						remove(BtmFuel4);
						remove(text);
						BtmRepairEngine.setEnabled(true);
						BtmChangeTires.setEnabled(true);
						BtmLoadFuel.setEnabled(true);
						controller.updateView();
						repaint();
					}
					
				});
				BtmFuel3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						loadfuellistener.listenerLoadFuelEvent(new LoadFuelEvent(player.getCar(), 75));
						fuel.setText("Combustible: "+ player.getCar().getFuel());
						remove(BtmFuel1);
						remove(BtmFuel2);
						remove(BtmFuel3);
						remove(BtmFuel4);
						remove(text);
						BtmRepairEngine.setEnabled(true);
						BtmChangeTires.setEnabled(true);
						BtmLoadFuel.setEnabled(true);
						controller.updateView();
						repaint();
					}
					
				});
				BtmFuel4.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						loadfuellistener.listenerLoadFuelEvent(new LoadFuelEvent(player.getCar(), 100));
						fuel.setText("Combustible: "+ player.getCar().getFuel());
						remove(BtmFuel1);
						remove(BtmFuel2);
						remove(BtmFuel3);
						remove(BtmFuel4);
						remove(text);
						BtmRepairEngine.setEnabled(true);
						BtmChangeTires.setEnabled(true);
						BtmLoadFuel.setEnabled(true);
						controller.updateView();
						repaint();
					}
					
				});
				
			}
			
		});
		
		
		btnResumeRace = new JButton("Resume Race");
        btnResumeRace.setBounds(500, 600, 400, 100);  // Ajusta la posición y el tamaño según tus necesidades
        btnResumeRace.addActionListener(e -> {
            // Aquí notificas al observador para reanudar la carrera
        	if (controller != null) {
        		Race.setRealPlayerPitStop(false);
                controller.finishPitStop();  
                controller.notifyResumeRaceObservers();
                controller.switchToRaceView();
            }
        });
        this.add(btnResumeRace);
    }
	
	
	@Override
	public void setLoadFuelListener(LoadFuelListener listener) {
		// TODO Auto-generated method stub
		loadfuellistener = listener;
	}
	public void setChangeTireListener(ChangeTireListener listener) {
		// TODO Auto-generated method stub
		changetirelistener = listener;
	}
	
	 public void updateFuelLabel(int newFuelAmount) {
	        fuel.setText("Combustible: " + newFuelAmount);
	    }
	 
	 public void updateTireWearLabel(int newTireWear) {
	        tirewear.setText("Desgaste de Neumáticos: " + newTireWear);
	    }
}
