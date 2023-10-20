package extremeF1.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Core.Entities.Championship;
import Core.Entities.Player;
import Core.Entities.Events.NextBtnEndViewEvent;
import Core.Entities.Events.NextBtnEndViewListener;

import javax.swing.JButton;

public class EndView extends JPanel implements EndViewInterface{
	private JTextArea txtChampionshipRanking;
	private Championship championship;
	private NextBtnEndViewListener nextlistener;
	public EndView(Championship championship) {
		
		setLayout(null);
		Font titleFont = new Font("Comic Sans MS", Font.BOLD, 150);
	     Font infoFont = new Font("Comic Sans MS", Font.PLAIN, 18);
	     
	     
	      JLabel titleGame = new JLabel("EXTREME F1");
	       	titleGame.setFont(titleFont);
	       	titleGame.setBounds(300, 100, 1000, 300);
	       	titleGame.setHorizontalAlignment(JLabel.CENTER);
	       	titleGame.setVerticalAlignment(JLabel.CENTER);
	       	titleGame.setForeground(new Color(200, 17, 17));
	       add(titleGame);
	    
	       
	     JButton btsNext = new JButton("Siguente");
	     	btsNext.setFont(infoFont);
	     	btsNext.setBackground(new Color(200, 17, 17));
	     	btsNext.setBounds(450,500,300,100);
	     	btsNext.setBorder(new EmptyBorder(10, 10, 10, 10));
	     	btsNext.setHorizontalAlignment(JLabel.CENTER);
	     	btsNext.setVerticalAlignment(JLabel.CENTER);
	     	
	     	btsNext.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					nextlistener.ListenerNextBtnEndViewEvent(new NextBtnEndViewEvent());
				}
	     		
	     	});
	     	add(btsNext);
	     	
	     	
	     	
	     	
       
	      
	       
	       	txtChampionshipRanking = new JTextArea();
	        txtChampionshipRanking.setEditable(false);
	        txtChampionshipRanking.setBounds(1300, 200, 300, 800);
	        txtChampionshipRanking.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
	        txtChampionshipRanking.setForeground(new Color(236, 240, 241)); // Texto claro
	        txtChampionshipRanking.setBackground(new Color(44, 62, 80)); // Fondo oscuro

	        
	        updateChampionshipRanking(championship);
	        
	        add(txtChampionshipRanking);
	       
	}
	public void updateChampionshipRanking(Championship championship) {
        StringBuilder sb = new StringBuilder();
        sb.append("Ranking del Campeonato:\n");
        sb.append("========================\n");

        // Ordenar la lista de jugadores por puntos de mayor a menor
        ArrayList<Player> sortedPlayers = new ArrayList<>(championship.getPlayers());
        Collections.sort(sortedPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(championship.getPointsForPlayer(p2.getId()), championship.getPointsForPlayer(p1.getId()));
            }
        });

        // Mostrar la lista ordenada
        for (Player player : sortedPlayers) {
            int playerId = player.getId();
            int points = championship.getPointsForPlayer(playerId);
            sb.append("Jugador: ").append(player.getName()).append("\n");
            sb.append("Puntos: ").append(points).append("\n");
            sb.append("------------------------\n");
        }

        txtChampionshipRanking.setText(sb.toString());
        add(txtChampionshipRanking);
    }
	public void setNextListener(NextBtnEndViewListener listener) {
		// TODO Auto-generated method stub
		this.nextlistener = listener;
	}
	
	
}
