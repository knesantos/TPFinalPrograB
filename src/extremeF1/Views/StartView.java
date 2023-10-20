package extremeF1.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Core.Entities.Real;
import Core.Entities.Events.CloseGameEvent;
import Core.Entities.Events.CloseGameListener;
import Core.Entities.Events.StartGameEvent;
import Core.Entities.Events.StartGameListener;

public class StartView extends JPanel implements StartViewInterface{
	private CloseGameListener closelistener;
	private StartGameListener startlistener;
	private Real player;
	
	public StartView() {
		 setLayout(null);
		 setBackground(new Color(44, 62, 80));
		 
		 
		 Font titleFont = new Font("Comic Sans MS", Font.BOLD, 150);
	     Font infoFont = new Font("Comic Sans MS", Font.PLAIN, 18);
	     
	     
	      JLabel titleGame = new JLabel("EXTREME F1");
	       	titleGame.setFont(titleFont);
	       	titleGame.setBounds(450, 100, 1000, 300);
	       	titleGame.setHorizontalAlignment(JLabel.CENTER);
	       	titleGame.setVerticalAlignment(JLabel.CENTER);
	       	titleGame.setForeground(new Color(236, 240, 241));
	       add(titleGame);
	       
	       
	      
	      JButton startgame = new JButton("Jugar");
	      	startgame.setFont(infoFont);
	      	startgame.setBackground(new Color(236, 240, 241));
	      	startgame.setBounds(800,500,300,100);
	      	startgame.setBorder(new EmptyBorder(10, 10, 10, 10));
	      	startgame.setHorizontalAlignment(JLabel.CENTER);
	     	startgame.setVerticalAlignment(JLabel.CENTER);
	       
	      
	      JButton closegame = new JButton("Cerrar");
	      	closegame.setFont(infoFont);
	      	closegame.setBackground(new Color(236, 240, 241));
	      	closegame.setBounds(1200,500,300,100);
	      	closegame.setBorder(new EmptyBorder(10, 10, 10, 10));
	      	closegame.setHorizontalAlignment(JLabel.CENTER);
	      	closegame.setVerticalAlignment(JLabel.CENTER);
	       
	       
	       JButton loadgame = new JButton("Cargar Partida");
	       	loadgame.setFont(infoFont);
	       	loadgame.setBackground(new Color(236, 240, 241));
	       	loadgame.setBounds(400,500,300,100);
	       	loadgame.setBorder(new EmptyBorder(10, 10, 10, 10));
	       	loadgame.setHorizontalAlignment(JLabel.CENTER);
	       	loadgame.setVerticalAlignment(JLabel.CENTER);
	       

	     // texto para el nombre
	        JTextField textfield = new JTextField();
	        textfield.setBounds(600, 500, 500, 30);
	        textfield.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
	        textfield.setBackground(new Color(236, 240, 241));
	        textfield.setVisible(false);  
	        add(textfield);

	        //aceptar el nombre
	        JButton aceptname = new JButton("Aceptar");
	        aceptname.setFont(infoFont);
	        aceptname.setBackground(new Color(236, 240, 241));
	        aceptname.setBounds(700, 600, 300, 100);  
	        aceptname.setBorder(new EmptyBorder(10, 10, 10, 10));
	        aceptname.setVisible(false);
	        add(aceptname);
	        
	        JButton back = new JButton("Atras");
		 	back.setFont(infoFont);
		 	back.setBackground(new Color(236, 240, 241));
		 	back.setBounds(1100,600,300,100);
		 	back.setBorder(new EmptyBorder(10, 10, 10, 10));
		 	back.setVisible(false);
			add(back);	
	       	
			JLabel text = new JLabel("Ingrese su nombre");
			text.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
			text.setForeground(new Color(236, 240, 241));
			text.setBounds(600,430,300,100);
			text.setHorizontalAlignment(JLabel.CENTER);
			text.setVerticalAlignment(JLabel.CENTER);
	        add(text);
	        text.setVisible(false);
			
	       	

	    setCloseGameListener(closelistener) ;
	    closegame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (closelistener != null) {
				closelistener.ListenerCloseGameListener(new CloseGameEvent());
			}
			}
	    });
	    
	    startgame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				remove(loadgame);
				remove(closegame);
				remove(startgame);

				text.setVisible(true);
				textfield.setVisible(true);
                aceptname.setVisible(true);
                back.setVisible(true);
                repaint();
                revalidate();
				
			 

			
			 back.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					remove(back);
					remove(aceptname);
					remove(textfield);
					remove(text);

					add(loadgame);
					add(closegame);
					add(startgame);
					repaint();
					
				}
				 
			 });
				
			 aceptname.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					setPlayer(textfield.getText());
				
					if (startlistener!=null) {
						startlistener.ListenerStartGame(new StartGameEvent());
						}
				}
				 
			 });
				
			}
	    	
	    });
	       
	    add(startgame);   
	    add(closegame);   
	    add(loadgame);

	   
	    }



	public void setPlayer(String name) {
		player = new Real(name,1);
	}
	public Real getPlayer() {
		return player;
	}

	@Override
	public void setCloseGameListener(CloseGameListener listener) {
		// TODO Auto-generated method stub
		this.closelistener = listener;
	}

	@Override
	public void setStartGameListener(StartGameListener listener) {
		// TODO Auto-generated method stub
		this.startlistener = listener;
	}
}