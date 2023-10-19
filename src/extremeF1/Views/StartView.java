package extremeF1.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Core.Entities.Events.CloseGameEvent;
import Core.Entities.Events.CloseGameListener;
import Core.Entities.Events.StartGameEvent;
import Core.Entities.Events.StartGameListener;

public class StartView extends JPanel implements StartViewInterface{
	private CloseGameListener closelistener;
	private StartGameListener startlistener;
	
	
	public StartView() {
		 setLayout(null);
		 setBackground(Color.black);
		 Font titleFont = new Font("Comic Sans MS", Font.BOLD, 150);
	     Font infoFont = new Font("Comic Sans MS", Font.PLAIN, 18);
	     
	     
	      JLabel titleGame = new JLabel("EXTREME F1");
	       	titleGame.setFont(titleFont);
	       	titleGame.setBounds(450, 100, 1000, 300);
	       	titleGame.setHorizontalAlignment(JLabel.CENTER);
	       	titleGame.setVerticalAlignment(JLabel.CENTER);
	       	titleGame.setForeground(new Color(200, 17, 17));
	       add(titleGame);
	       
	       
	      
	      JButton startgame = new JButton("Jugar");
	      	startgame.setFont(infoFont);
	      	startgame.setBackground(new Color(200, 17, 17));
	      	startgame.setBounds(800,500,300,100);
	      	startgame.setBorder(new EmptyBorder(10, 10, 10, 10));
	      	startgame.setHorizontalAlignment(JLabel.CENTER);
	     	startgame.setVerticalAlignment(JLabel.CENTER);
	       
	      
	      JButton closegame = new JButton("Cerrar");
	      	closegame.setFont(infoFont);
	      	closegame.setBackground(new Color(200, 17, 17));
	      	closegame.setBounds(1200,500,300,100);
	      	closegame.setBorder(new EmptyBorder(10, 10, 10, 10));
	      	closegame.setHorizontalAlignment(JLabel.CENTER);
	      	closegame.setVerticalAlignment(JLabel.CENTER);
	       
	       
	       JButton loadgame = new JButton("Cargar Partida");
	       	loadgame.setFont(infoFont);
	       	loadgame.setBackground(new Color(200, 17, 17));
	       	loadgame.setBounds(400,500,300,100);
	       	loadgame.setBorder(new EmptyBorder(10, 10, 10, 10));
	       	loadgame.setHorizontalAlignment(JLabel.CENTER);
	       	loadgame.setVerticalAlignment(JLabel.CENTER);
	       
		 
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
				if (startlistener!=null) {
				startlistener.ListenerStartGame(new StartGameEvent());
			}
			}
	    	
	    });
	       
	    add(startgame);   
	    add(closegame);   
	    add(loadgame);
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