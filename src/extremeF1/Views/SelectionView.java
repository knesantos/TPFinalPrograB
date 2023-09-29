package extremeF1.Views;

import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Core.Entities.Auto;
import Core.Entities.Piloto;
import java.awt.Color;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Core.Entities.Events.AceptCarEvent;
import Core.Entities.Events.AceptCarListener;
import Core.Entities.Events.AceptPilotEvent;
import Core.Entities.Events.AceptPilotListener;
import Core.Entities.Events.AceptSelectionEvent;
import Core.Entities.Events.AceptSelectionListener;
import Core.Interfaces.SelectionObserver;

public class SelectionView extends JFrame implements SelectionViewInterface {

	private JPanel panel;
	private JButton boton1, boton2,boton3,boton4, ButtomSelect1, ButtomCancel1,ButtomSelect2,ButtomCancel2 ,btnAceptarSeleccion;
	private JLabel titulo, nombre, nomabv, imagen, presupuesto, defensa, sobrepaso, clasificacion, cantCarrerasGanadas,
			cantPolePosition, cantCampeonatos, cantParticipaciones, cuidadoNeumaticos, largada,marca,modelo,peso,aceleracion,
			velocidadmax,potencia,consumo,fiabilidad,performancecurvas,performancesobrepaso;
	private List<Piloto> Pilotos = new ArrayList<>();
	private List<Auto> Autos = new ArrayList<>();
	private int i = 0, j= 0;
	private AceptPilotListener aceptpilotlistener;
	private AceptCarListener aceptcarlistener;
	private AceptSelectionListener buttonAcceptSelectionListener;
	
	private List<SelectionObserver> observers = new ArrayList<>();
	
	private boolean isPilotSelected = false;
	private boolean isCarSelected = false;
	
	private boolean isAceptSelectionButtonPressed = false;
	
	 public void addObserver(SelectionObserver observer) {
	        observers.add(observer);
	    }

	public SelectionView(List<Piloto> listpilotos,List<Auto> listaautos) {
		Pilotos = listpilotos;
		Autos = listaautos;
		
		// ventana//
		setSize(1500, 500);
		setTitle("ExtremeF1");
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.black);

		// Panel principal// 
		panel = new JPanel();
		this.getContentPane().add(panel);
		panel.setBackground(Color.gray);
		panel.setLayout(null);
		
		SeleccionPiloto();
		SeleccionAuto();
	}
		
		public void SeleccionPiloto () {
		// Botones//
		btnAceptarSeleccion = new JButton("Aceptar Selección");
		btnAceptarSeleccion.setEnabled(false);
	    btnAceptarSeleccion.setBounds(600, 400, 150, 50);  // Ajusta la posición y tamaño según necesites
	    panel.add(btnAceptarSeleccion);	
		
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
				if (i == Pilotos.size() - 1) {
					boton2.setEnabled(false);
				} else {
					i++;
					boton1.setEnabled(true);
					nombre.setText("Nombre: " + Pilotos.get(i).getnombre());
					nomabv.setText(Pilotos.get(i).getnombreAbrev());
					presupuesto.setText("Presupuesto: " + Pilotos.get(i).getpresupuesto());
					defensa.setText("Defensa: " + Pilotos.get(i).getDefensa());
					sobrepaso.setText("Sobrepaso: " + Pilotos.get(i).getSobrepaso());
					clasificacion.setText("Clasificacion: " + Pilotos.get(i).getclasificacion());
					cantCarrerasGanadas.setText("Cantiddad de carreras ganadas: " + Pilotos.get(i).getcantCarrerasGanadas());
					cantParticipaciones.setText("Cantiddad de parcitipaciones: " + Pilotos.get(i).getcantParticipaciones());
					cuidadoNeumaticos.setText("Cuidado de neumaticos: " + Pilotos.get(i).getCuidadoNeumaticos());
					largada.setText("Largada: " + Pilotos.get(i).getlargada());
				}
			}

		});
		boton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (i == 0) {
					boton1.setEnabled(false);
				} else {
					i--;
					boton2.setEnabled(true);
					nombre.setText("Nombre: " + Pilotos.get(i).getnombre());
					nomabv.setText(Pilotos.get(i).getnombreAbrev());
					presupuesto.setText("Presupuesto: " + Pilotos.get(i).getpresupuesto());
					defensa.setText("Defensa: " + Pilotos.get(i).getDefensa());
					sobrepaso.setText("Sobrepaso: " + Pilotos.get(i).getSobrepaso());
					clasificacion.setText("Clasificacion: " + Pilotos.get(i).getclasificacion());
					cantCarrerasGanadas.setText("Cantiddad de carreras ganadas: " + Pilotos.get(i).getcantCarrerasGanadas());
					cantParticipaciones.setText("Cantiddad de parcitipaciones: " + Pilotos.get(i).getcantParticipaciones());
					cuidadoNeumaticos.setText("Cuidado de neumaticos: " + Pilotos.get(i).getCuidadoNeumaticos());
					largada.setText("Largada: " + Pilotos.get(i).getlargada());
				}
			}

		});

		ButtomSelect1 = new JButton();
		ButtomSelect1.setBounds(75, 270, 100, 50);
		ButtomSelect1.setText("Aceptar");
		panel.add(ButtomSelect1);
		setAceptPilotListener(aceptpilotlistener);
		ButtomSelect1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Piloto p1 = new Piloto();
				p1 = Pilotos.get(i);
				aceptpilotlistener.listenerAceptPilotEvent(new AceptPilotEvent(p1));
				ButtomSelect1.setEnabled(false);
				boton1.setEnabled(false);
				boton2.setEnabled(false);
				ButtomCancel1.setEnabled(true);
				isPilotSelected = true;
		        checkBothSelected();
			}

		});

		ButtomCancel1 = new JButton();
		ButtomCancel1.setBounds(175, 270, 100, 50);
		ButtomCancel1.setText("Cancelar");
		ButtomCancel1.setEnabled(false);
		panel.add(ButtomCancel1);
		ButtomCancel1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtomSelect1.setEnabled(true);
				boton1.setEnabled(true);
				boton2.setEnabled(true);
				ButtomCancel1.setEnabled(false);

			}

		});

		// labels//
		titulo = new JLabel();
		titulo.setBounds(425, 0, 300, 20);
		titulo.setText("Seleccione su piloto");
		panel.add(titulo);

		nombre = new JLabel();
		nombre.setBounds(75, 100, 200, 15);
		nombre.setText("Nombre: " + Pilotos.get(i).getnombre());
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
		defensa.setText("Defensa: " + Pilotos.get(i).getDefensa());
		panel.add(defensa);

		sobrepaso = new JLabel();
		sobrepaso.setBounds(75, 145, 200, 15);
		sobrepaso.setText("Sobrepaso: " + Pilotos.get(i).getSobrepaso());
		panel.add(sobrepaso);

		clasificacion = new JLabel();
		clasificacion.setBounds(75, 160, 200, 15);
		clasificacion.setText("Clasificacion: " + Pilotos.get(i).getclasificacion());
		panel.add(clasificacion);

		cantCarrerasGanadas = new JLabel();
		cantCarrerasGanadas.setBounds(75, 175, 220, 15);
		cantCarrerasGanadas.setText("Cantiddad de carreras ganadas: " + Pilotos.get(i).getcantCarrerasGanadas());
		panel.add(cantCarrerasGanadas);

		cantPolePosition = new JLabel();
		cantPolePosition.setBounds(75, 190, 200, 15);
		cantPolePosition.setText("Cantiddad de pole: " + Pilotos.get(i).getcantPolePosition());
		panel.add(cantPolePosition);

		cantCampeonatos = new JLabel();
		cantCampeonatos.setBounds(75, 205, 250, 15);
		cantCampeonatos.setText("Cantiddad de camponeatos ganados: " + Pilotos.get(i).getcantCampeonatos());
		panel.add(cantCampeonatos);

		cantParticipaciones = new JLabel();
		cantParticipaciones.setBounds(75, 220, 200, 15);
		cantParticipaciones.setText("Cantiddad de parcitipaciones: " + Pilotos.get(i).getcantParticipaciones());
		panel.add(cantParticipaciones);

		cuidadoNeumaticos = new JLabel();
		cuidadoNeumaticos.setBounds(75, 235, 200, 15);
		cuidadoNeumaticos.setText("Cuidado de neumaticos: " + Pilotos.get(i).getCuidadoNeumaticos());
		panel.add(cuidadoNeumaticos);

		largada = new JLabel();
		largada.setBounds(75, 250, 200, 15);
		largada.setText("Largada: " + Pilotos.get(i).getlargada());
		panel.add(largada);

		imagen = new JLabel();
		imagen.setBounds(400, 100, 300, 300);
		imagen.setOpaque(true);
		imagen.setBackground(Color.black);
		panel.add(imagen);

	}
	
	public void SeleccionAuto(){
		boton3 = new JButton();
		boton4 = new JButton();
		panel.add(boton3);
		panel.add(boton4);
		boton3.setBounds(800, 50, 50, 50);
		boton4.setBounds(850, 50, 50, 50);
		boton3.setText("<");
		boton4.setText(">");
		boton4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (j == Autos.size() - 1) {
					boton4.setEnabled(false);
				} else {
					j++;
					boton3.setEnabled(true);
					marca.setText("Marca: " + Autos.get(j).getMarca());
					modelo.setText("Modelo: "+ Autos.get(j).getModelo());
					peso.setText("Peso: " + Autos.get(j).getPeso()+" Kg");
					aceleracion.setText("Aceleracion: " + Autos.get(j).getAceleracion()+" m/s");
					velocidadmax.setText("Velocidad Maxima: " + Autos.get(j).getVelocidadMax()+" Km/h");					
					potencia.setText("Potencia: " + Autos.get(j).getPotencia());										
					consumo.setText("Consumo: " + Autos.get(j).getConsumo());					
					fiabilidad.setText("Fiabilidad: " + Autos.get(j).getFiabilidad());
					performancecurvas.setText("Performancecurvas: " + Autos.get(j).getPerformanceCurvas());
					performancesobrepaso.setText("Performancesobrepaso: " + Autos.get(j).getPerformanceSobrepaso());
				}
			}

		});
		boton3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (j == 0) {
					boton3.setEnabled(false);
				} else {
					j--;
					boton4.setEnabled(true);
					marca.setText("Marca: " + Autos.get(j).getMarca());
					modelo.setText("Modelo: "+ Autos.get(j).getModelo());
					peso.setText("Peso: " + Autos.get(j).getPeso()+" Kg");
					aceleracion.setText("Aceleracion: " + Autos.get(j).getAceleracion()+" m/s");
					velocidadmax.setText("Velocidad Maxima: " + Autos.get(j).getVelocidadMax()+" Km/h");					
					potencia.setText("Potencia: " + Autos.get(j).getPotencia());										
					consumo.setText("Consumo: " + Autos.get(j).getConsumo());					
					fiabilidad.setText("Fiabilidad: " + Autos.get(j).getFiabilidad());
					performancecurvas.setText("Performancecurvas: " + Autos.get(j).getPerformanceCurvas());
					performancesobrepaso.setText("Performancesobrepaso: " + Autos.get(j).getPerformanceSobrepaso());
				}
			}

		});

		ButtomSelect2 = new JButton();
		ButtomSelect2.setBounds(825, 270, 100, 50);
		ButtomSelect2.setText("Aceptar");
		panel.add(ButtomSelect2);
		setAceptCarListener(aceptcarlistener);
		
		ButtomSelect2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Auto a1 = new Auto();
				a1 = Autos.get(j);
				aceptcarlistener.listenerAceptCarEvent(new AceptCarEvent(a1));
				ButtomSelect2.setEnabled(false);
				boton3.setEnabled(false);
				boton4.setEnabled(false);
				ButtomCancel2.setEnabled(true);
				isCarSelected = true;
		        checkBothSelected();
			}

		});

		ButtomCancel2 = new JButton();
		ButtomCancel2.setBounds(925, 270, 100, 50);
		ButtomCancel2.setText("Cancelar");
		ButtomCancel2.setEnabled(false);
		panel.add(ButtomCancel2);
		ButtomCancel2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtomSelect2.setEnabled(true);
				boton3.setEnabled(true);
				boton4.setEnabled(true);
				ButtomCancel2.setEnabled(false);

			}

		});

		// labels//
		titulo = new JLabel();
		titulo.setBounds(1000, 0, 300, 20);
		titulo.setText("Seleccione su auto");
		panel.add(titulo);

		marca = new JLabel();
		marca.setBounds(825, 100, 200, 15);
		marca.setText("Marca: " + Autos.get(j).getMarca());
		panel.add(marca);

		modelo = new JLabel();
		modelo.setBounds(950, 100, 100, 15);
		modelo.setText("Modelo: "+Autos.get(i).getModelo());
		modelo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(modelo);

		peso = new JLabel();
		peso.setBounds(825, 115, 200, 15);
		peso.setText("Peso: " + Autos.get(i).getPeso()+" Kg");
		panel.add(peso);

		aceleracion = new JLabel();
		aceleracion.setBounds(825, 130, 200, 15);
		aceleracion.setText("Aceleracion: " + Autos.get(i).getAceleracion()+" m/s");
		panel.add(aceleracion);

		velocidadmax = new JLabel();
		velocidadmax.setBounds(825, 145, 200, 15);
		velocidadmax.setText("Velocidad Maxima: " + Autos.get(i).getVelocidadMax()+" Km/h");
		panel.add(velocidadmax);

		potencia = new JLabel();
		potencia.setBounds(825, 160, 200, 15);
		potencia.setText("Potencia: " + Autos.get(i).getPotencia());
		panel.add(potencia);

		consumo = new JLabel();
		consumo.setBounds(825, 175, 220, 15);
		consumo.setText("Consumo: " + Autos.get(i).getConsumo());
		panel.add(consumo);

		fiabilidad = new JLabel();
		fiabilidad.setBounds(825, 190, 200, 15);
		fiabilidad.setText("Fiabilidad: " + Autos.get(i).getFiabilidad());
		panel.add(fiabilidad);

		performancecurvas = new JLabel();
		performancecurvas.setBounds(825, 205, 250, 15);
		performancecurvas.setText("Performancecurvas: " + Autos.get(i).getPerformanceCurvas());
		panel.add(performancecurvas);

		performancesobrepaso = new JLabel();
		performancesobrepaso.setBounds(825, 220, 200, 15);
		performancesobrepaso.setText("Performancesobrepaso: " + Autos.get(i).getPerformanceSobrepaso());
		panel.add(performancesobrepaso);

		imagen = new JLabel();
		imagen.setBounds(1150, 100, 300, 300);
		imagen.setOpaque(true);
		imagen.setBackground(Color.black);
		panel.add(imagen);
		
	
		
		btnAceptarSeleccion.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (buttonAcceptSelectionListener != null) {
		            buttonAcceptSelectionListener.listenerAceptSelectionEvent(new AceptSelectionEvent(true));
		        }
		    }
		});}

	@Override
	public void setAceptPilotListener(AceptPilotListener listener) {
		aceptpilotlistener = listener;
	}

	@Override
	public void setAceptCarListener(AceptCarListener listener) {
		aceptcarlistener = listener;
	}
	
	private void checkBothSelected() {
	    if (isPilotSelected && isCarSelected) {
	        btnAceptarSeleccion.setEnabled(true);
	    }
	}


	@Override
	public void setButtonAcceptSelectionListener(AceptSelectionListener listener) {
		buttonAcceptSelectionListener = listener;
		
	}

	
	
	

}
