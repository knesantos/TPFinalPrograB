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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Core.Entities.Events.AceptPilotEvent;
import Core.Entities.Events.AceptPilotListener;

public class SelcetionView extends JFrame implements SelectionViewInterface  {
	
	private JPanel panel;
	private JButton boton1,boton2,ButtomSelect,ButtomCancel;
	private JLabel titulo,nombre,nomabv,imagen,presupuesto,defensa,sobrepaso,clasificacion,cantCarrerasGanadas,cantPolePosition,cantCampeonatos,cantParticipaciones,cuidadoNeumaticos,largada;
	private List<Piloto> Pilotos = new ArrayList<>();
	private int i=0;
	private AceptPilotListener aceptpilotlistener;
	
 public SelcetionView(List<Piloto> listpilotos) {
	 Pilotos = listpilotos;

	 
	 //ventana//
	 setSize(1000,500);
	 setTitle("ExtremeF1");
	 setLocationRelativeTo(null);
	 getContentPane().setBackground(Color.black);

	 //Panel principal//
	 	panel = new JPanel();
	 	this.getContentPane().add(panel);
	 	panel.setBackground(Color.gray);
	 	panel.setLayout(null);
    
    //Botones//
	 	boton1 = new JButton();
	 	boton2 = new JButton();
	 	panel.add(boton2);
	 	panel.add(boton1);
	 	boton1.setBounds(50, 50, 50, 50);
	 	boton2.setBounds(100, 50, 50, 50);
	 	boton1.setText("<");
	 	boton2.setText(">");
	 	boton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (i == Pilotos.size()-1) {
					boton2.setEnabled(false);
				}else {
					i++;
					boton1.setEnabled(true);
				nombre.setText("Nombre: "+ Pilotos.get(i).getnombre());
				nomabv.setText(Pilotos.get(i).getnombreAbrev());
				presupuesto.setText("Presupuesto: " + Pilotos.get(i).getpresupuesto());
				defensa.setText("Defensa: "+ Pilotos.get(i).getdefensa());
				sobrepaso.setText("Sobrepaso: "+ Pilotos.get(i).getsobrepaso());
				clasificacion.setText("Clasificacion: "+Pilotos.get(i).getclasificacion());
				cantCarrerasGanadas.setText("Cantiddad de carreras ganadas: "+ Pilotos.get(i).getcantCarrerasGanadas());
				cantParticipaciones.setText("Cantiddad de parcitipaciones: "+ Pilotos.get(i).getcantParticipaciones());
				cuidadoNeumaticos.setText("Cuidado de neumaticos: "+ Pilotos.get(i).getcuidadoNeumaticos());
				largada.setText("Largada: "+ Pilotos.get(i).getlargada());
				}
			}
	 		
	 	});
	 	boton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (i == 0) {
					boton1.setEnabled(false);
				}else {
					i--;
					boton2.setEnabled(true);
				nombre.setText("Nombre: "+ Pilotos.get(i).getnombre());
				nomabv.setText(Pilotos.get(i).getnombreAbrev());
				presupuesto.setText("Presupuesto: " + Pilotos.get(i).getpresupuesto());
				defensa.setText("Defensa: "+ Pilotos.get(i).getdefensa());
				sobrepaso.setText("Sobrepaso: "+ Pilotos.get(i).getsobrepaso());
				clasificacion.setText("Clasificacion: "+Pilotos.get(i).getclasificacion());
				cantCarrerasGanadas.setText("Cantiddad de carreras ganadas: "+ Pilotos.get(i).getcantCarrerasGanadas());
				cantParticipaciones.setText("Cantiddad de parcitipaciones: "+ Pilotos.get(i).getcantParticipaciones());
				cuidadoNeumaticos.setText("Cuidado de neumaticos: "+ Pilotos.get(i).getcuidadoNeumaticos());
				largada.setText("Largada: "+ Pilotos.get(i).getlargada());
				}
			}
	 		
	 	});
	 	
	 	ButtomSelect = new JButton();
	 	ButtomSelect.setBounds(75,270, 100, 50);
	 	ButtomSelect.setText("Aceptar");
	 	panel.add(ButtomSelect);
	 	
	 		ButtomSelect.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
				 int index = i;
				aceptpilotlistener.listenerAceptPilotEvent(new AceptPilotEvent(index));

				}
	 			
	 		});
	 	
	 	ButtomCancel = new JButton();
	 	ButtomCancel.setBounds(175,270, 100, 50);
	 	ButtomCancel.setText("Cancelar");
	 	panel.add(ButtomCancel);
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	//labels//
		titulo = new JLabel();
		titulo.setBounds(425, 0,300, 20);
		titulo.setText("Seleccione su piloto");
		panel.add(titulo);
		
		nombre = new JLabel();
		nombre.setBounds(75, 100,200,15);
		nombre.setText("Nombre: "+ Pilotos.get(i).getnombre());
		panel.add(nombre);
		
		nomabv = new JLabel();
		nomabv.setBounds(220, 100, 100, 15);
		nomabv.setText(Pilotos.get(i).getnombreAbrev());
		nomabv.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(nomabv);
		
		presupuesto = new JLabel();
		presupuesto.setBounds(75, 115, 200, 15);
		presupuesto.setText("Presupuesto: " + Pilotos.get(i).getpresupuesto());
		panel.add(presupuesto);
		
		defensa = new JLabel();
		defensa.setBounds(75, 130, 200, 15);
		defensa.setText("Defensa: "+ Pilotos.get(i).getdefensa());
		panel.add(defensa);
		
		sobrepaso = new JLabel();
		sobrepaso.setBounds(75, 145, 200, 15);
		sobrepaso.setText("Sobrepaso: "+ Pilotos.get(i).getsobrepaso());
		panel.add(sobrepaso);
		
		clasificacion = new JLabel();
		clasificacion.setBounds(75, 160, 200, 15);
		clasificacion.setText("Clasificacion: "+Pilotos.get(i).getclasificacion());
		panel.add(clasificacion);
		
		cantCarrerasGanadas = new JLabel();
		cantCarrerasGanadas.setBounds(75, 175, 220, 15);
		cantCarrerasGanadas.setText("Cantiddad de carreras ganadas: "+ Pilotos.get(i).getcantCarrerasGanadas());
		panel.add(cantCarrerasGanadas);
		
		cantPolePosition = new JLabel();
		cantPolePosition.setBounds(75, 190, 200, 15);
		cantPolePosition.setText("Cantiddad de pole: "+ Pilotos.get(i).getcantPolePosition());
		panel.add(cantPolePosition);
		
		cantCampeonatos = new JLabel();
		cantCampeonatos.setBounds(75, 205, 250, 15);
		cantCampeonatos.setText("Cantiddad de camponeatos ganados: "+ Pilotos.get(i).getcantCampeonatos());
		panel.add(cantCampeonatos);
		
		cantParticipaciones = new JLabel();
		cantParticipaciones.setBounds(75, 220, 200, 15);
		cantParticipaciones.setText("Cantiddad de parcitipaciones: "+ Pilotos.get(i).getcantParticipaciones());
		panel.add(cantParticipaciones);
		
		cuidadoNeumaticos = new JLabel();
		cuidadoNeumaticos.setBounds(75, 235, 200, 15);
		cuidadoNeumaticos.setText("Cuidado de neumaticos: "+ Pilotos.get(i).getcuidadoNeumaticos());
		panel.add(cuidadoNeumaticos);

		largada = new JLabel();
		largada.setBounds(75, 250, 200, 15);
		largada.setText("Largada: "+ Pilotos.get(i).getlargada());
		panel.add(largada);
		
		imagen = new JLabel();
		imagen.setBounds(400,100,300,300);
		imagen.setOpaque(true);
		imagen.setBackground(Color.black);
		panel.add(imagen);

		
 }

@Override
public void setAceptPilotListener(AceptPilotListener listener) {
	// TODO Auto-generated method stub
	aceptpilotlistener = listener;
}

}
