package extremeF1.Views;

import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Core.Entities.Piloto;

import java.awt.Color;
import java.util.List;

public class SelcetionView extends JFrame{
	
	private JPanel panel;
	private JButton boton1,boton2;
	private JLabel titulo,nombre,nomabv,imagen,presupuesto,defensa,sobrepaso,clasificacion,cantCarrerasGanadas,cantPolePosition,cantCampeonatos,cantParticipaciones,cuidadoNeumaticos,largada;
	private List<Piloto> Pilotos = new ArrayList<>();
	
	
 public SelcetionView(List<Piloto> listpilotos) {
	 setSize(1000,500);
	 setTitle("ExtremeF1");
	// this.setLocation(500, 300);
	// this.setBounds(500, 300, 5000, 500);
	 setLocationRelativeTo(null);
	 getContentPane().setBackground(Color.black);

	 
	 Pilotos = listpilotos;

	 	panel = new JPanel();
	 	this.getContentPane().add(panel);
	 	panel.setBackground(Color.gray);
	 	panel.setLayout(null);
    
    
	 	boton1 = new JButton();
	 	boton2 = new JButton();
	 	panel.add(boton2);
	 	panel.add(boton1);
	 	boton1.setBounds(50, 50, 50, 50);
	 	boton2.setBounds(100, 50, 50, 50);
	 	boton1.setText("<");
	 	boton2.setText(">");
	
	
		titulo = new JLabel();
		titulo.setBounds(425, 0,300, 20);
		titulo.setText("Seleccione su piloto");
		panel.add(titulo);
		
		nombre = new JLabel();
		nombre.setBounds(75, 100,200,15);
		nombre.setText("Nombre: "+ Pilotos.get(0).getnombre());
		panel.add(nombre);
		
		nomabv = new JLabel();
		nomabv.setBounds(280, 100, 20, 15);
		nomabv.setText(Pilotos.get(0).getnombreAbrev());
		nomabv.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(nomabv);
		
		presupuesto = new JLabel();
		presupuesto.setBounds(75, 115, 200, 15);
		presupuesto.setText("Presupuesto: " + Pilotos.get(0).getpresupuesto());
		panel.add(presupuesto);
		
		defensa = new JLabel();
		defensa.setBounds(75, 130, 200, 15);
		defensa.setText("Defensa: "+ Pilotos.get(0).getdefensa());
		panel.add(defensa);
		
		sobrepaso = new JLabel();
		sobrepaso.setBounds(75, 145, 200, 15);
		sobrepaso.setText("Sobrepaso: "+ Pilotos.get(0).getsobrepaso());
		panel.add(sobrepaso);
		
		clasificacion = new JLabel();
		clasificacion.setBounds(75, 160, 200, 15);
		clasificacion.setText("Clasificacion: "+Pilotos.get(0).getclasificacion());
		panel.add(clasificacion);
		
		cantCarrerasGanadas = new JLabel();
		cantCarrerasGanadas.setBounds(75, 175, 220, 15);
		cantCarrerasGanadas.setText("Cantiddad de carreras ganadas: "+ Pilotos.get(0).getcantCarrerasGanadas());
		panel.add(cantCarrerasGanadas);
		
		cantPolePosition = new JLabel();
		cantPolePosition.setBounds(75, 190, 200, 15);
		cantPolePosition.setText("Cantiddad de pole: "+ Pilotos.get(0).getcantPolePosition());
		panel.add(cantPolePosition);
		
		cantCampeonatos = new JLabel();
		cantCampeonatos.setBounds(75, 205, 250, 15);
		cantCampeonatos.setText("Cantiddad de camponeatos ganados: "+ Pilotos.get(0).getcantCampeonatos());
		panel.add(cantCampeonatos);
		
		cantParticipaciones = new JLabel();
		cantParticipaciones.setBounds(75, 220, 200, 15);
		cantParticipaciones.setText("Cantiddad de parcitipaciones: "+ Pilotos.get(0).getcantParticipaciones());
		panel.add(cantParticipaciones);
		
		cuidadoNeumaticos = new JLabel();
		cuidadoNeumaticos.setBounds(75, 235, 200, 15);
		cuidadoNeumaticos.setText("Cuidado de neumaticos: "+ Pilotos.get(0).getcuidadoNeumaticos());
		panel.add(cuidadoNeumaticos);

		largada = new JLabel();
		largada.setBounds(75, 250, 200, 15);
		largada.setText("Largada: "+ Pilotos.get(0).getlargada());
		panel.add(largada);
		
		imagen = new JLabel();
		imagen.setBounds(400,100,300,300);
		imagen.setOpaque(true);
		imagen.setBackground(Color.black);
		panel.add(imagen);
	}

}
