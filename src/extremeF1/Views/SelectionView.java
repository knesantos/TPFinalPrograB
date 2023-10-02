package extremeF1.Views;

import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Core.Entities.Car;
import Core.Entities.Driver;
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
	private JLabel titulo, Name, nomabv, imagen, Budget, Defense, sobrepaso, clasificacion, cantCarrerasGanadas,
			cantPolePosition, cantCampeonatos, cantParticipaciones, cuidadoNeumaticos, largada,marca,modelo,peso,aceleracion,
			velocidadmax,potencia,consumo,fiabilidad,performancecurvas,performancesobrepaso;
	private List<Driver> Drivers = new ArrayList<>();
	private List<Car> Cars = new ArrayList<>();
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

	public SelectionView(List<Driver> listDrivers,List<Car> listaCars) {
		Drivers = listDrivers;
		Cars = listaCars;
		
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
		
		SeleccionDriver();
		SeleccionCar();
	}
		
		public void SeleccionDriver () {
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
				if (i == Drivers.size() - 1) {
					boton2.setEnabled(false);
				} else {
					i++;
					boton1.setEnabled(true);
					Name.setText("Name: " + Drivers.get(i).getName());
					nomabv.setText(Drivers.get(i).getShortName());
					Budget.setText("Budget: " + Drivers.get(i).getBudget());
					Defense.setText("Defense: " + Drivers.get(i).getDefense());
					sobrepaso.setText("Sobrepaso: " + Drivers.get(i).getOvertaking());
					clasificacion.setText("Clasificacion: " + Drivers.get(i).getRanking());
					cantCarrerasGanadas.setText("Cantiddad de carreras ganadas: " + Drivers.get(i).getRacesWon());
					cantParticipaciones.setText("Cantiddad de parcitipaciones: " + Drivers.get(i).getParticipations());
					cuidadoNeumaticos.setText("Cuidado de neumaticos: " + Drivers.get(i).getTireCare());
					largada.setText("Largada: " + Drivers.get(i).getStart());
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
					Name.setText("Name: " + Drivers.get(i).getName());
					nomabv.setText(Drivers.get(i).getShortName());
					Budget.setText("Budget: " + Drivers.get(i).getBudget());
					Defense.setText("Defense: " + Drivers.get(i).getDefense());
					sobrepaso.setText("Sobrepaso: " + Drivers.get(i).getOvertaking());
					clasificacion.setText("Clasificacion: " + Drivers.get(i).getRanking());
					cantCarrerasGanadas.setText("Cantiddad de carreras ganadas: " + Drivers.get(i).getRacesWon());
					cantParticipaciones.setText("Cantiddad de parcitipaciones: " + Drivers.get(i).getParticipations());
					cuidadoNeumaticos.setText("Cuidado de neumaticos: " + Drivers.get(i).getTireCare());
					largada.setText("Largada: " + Drivers.get(i).getStart());
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
				Driver p1 = new Driver();
				p1 = Drivers.get(i);
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
		titulo.setText("Seleccione su Driver");
		panel.add(titulo);

		Name = new JLabel();
		Name.setBounds(75, 100, 200, 15);
		Name.setText("Name: " + Drivers.get(i).getName());
		panel.add(Name);

		nomabv = new JLabel();
		nomabv.setBounds(220, 100, 100, 15);
		nomabv.setText(Drivers.get(i).getShortName());
		nomabv.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(nomabv);

		Budget = new JLabel();
		Budget.setBounds(75, 115, 200, 15);
		Budget.setText("Budget: " + Drivers.get(i).getBudget());
		panel.add(Budget);

		Defense = new JLabel();
		Defense.setBounds(75, 130, 200, 15);
		Defense.setText("Defense: " + Drivers.get(i).getDefense());
		panel.add(Defense);

		sobrepaso = new JLabel();
		sobrepaso.setBounds(75, 145, 200, 15);
		sobrepaso.setText("Sobrepaso: " + Drivers.get(i).getOvertaking());
		panel.add(sobrepaso);

		clasificacion = new JLabel();
		clasificacion.setBounds(75, 160, 200, 15);
		clasificacion.setText("Clasificacion: " + Drivers.get(i).getRanking());
		panel.add(clasificacion);

		cantCarrerasGanadas = new JLabel();
		cantCarrerasGanadas.setBounds(75, 175, 220, 15);
		cantCarrerasGanadas.setText("Cantiddad de carreras ganadas: " + Drivers.get(i).getRacesWon());
		panel.add(cantCarrerasGanadas);

		cantPolePosition = new JLabel();
		cantPolePosition.setBounds(75, 190, 200, 15);
		cantPolePosition.setText("Cantiddad de pole: " + Drivers.get(i).getPolePositions());
		panel.add(cantPolePosition);

		cantCampeonatos = new JLabel();
		cantCampeonatos.setBounds(75, 205, 250, 15);
		cantCampeonatos.setText("Cantiddad de camponeatos ganados: " + Drivers.get(i).getChampionships());
		panel.add(cantCampeonatos);

		cantParticipaciones = new JLabel();
		cantParticipaciones.setBounds(75, 220, 200, 15);
		cantParticipaciones.setText("Cantiddad de parcitipaciones: " + Drivers.get(i).getParticipations());
		panel.add(cantParticipaciones);

		cuidadoNeumaticos = new JLabel();
		cuidadoNeumaticos.setBounds(75, 235, 200, 15);
		cuidadoNeumaticos.setText("Cuidado de neumaticos: " + Drivers.get(i).getTireCare());
		panel.add(cuidadoNeumaticos);

		largada = new JLabel();
		largada.setBounds(75, 250, 200, 15);
		largada.setText("Largada: " + Drivers.get(i).getStart());
		panel.add(largada);

		imagen = new JLabel();
		imagen.setBounds(400, 100, 300, 300);
		imagen.setOpaque(true);
		imagen.setBackground(Color.black);
		panel.add(imagen);

	}
	
	public void SeleccionCar(){
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
				if (j == Cars.size() - 1) {
					boton4.setEnabled(false);
				} else {
					j++;
					boton3.setEnabled(true);
					marca.setText("Marca: " + Cars.get(j).getBrand());
					modelo.setText("Modelo: "+ Cars.get(j).getModel());
					peso.setText("Peso: " + Cars.get(j).getWeight()+" Kg");
					aceleracion.setText("Aceleracion: " + Cars.get(j).getAcceleration()+" m/s");
					velocidadmax.setText("Velocidad Maxima: " + Cars.get(j).getMaxSpeed()+" Km/h");					
					potencia.setText("Potencia: " + Cars.get(j).getPower());										
					consumo.setText("Consumo: " + Cars.get(j).getConsumption());					
					fiabilidad.setText("Fiabilidad: " + Cars.get(j).getReliability());
					performancecurvas.setText("Performancecurvas: " + Cars.get(j).getCurvesPerformance());
					performancesobrepaso.setText("Performancesobrepaso: " + Cars.get(j).getOvertakingPerformance());
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
					marca.setText("Marca: " + Cars.get(j).getBrand());
					modelo.setText("Modelo: "+ Cars.get(j).getModel());
					peso.setText("Peso: " + Cars.get(j).getWeight()+" Kg");
					aceleracion.setText("Aceleracion: " + Cars.get(j).getAcceleration()+" m/s");
					velocidadmax.setText("Velocidad Maxima: " + Cars.get(j).getMaxSpeed()+" Km/h");					
					potencia.setText("Potencia: " + Cars.get(j).getPower());										
					consumo.setText("Consumo: " + Cars.get(j).getConsumption());					
					fiabilidad.setText("Fiabilidad: " + Cars.get(j).getReliability());
					performancecurvas.setText("Performancecurvas: " + Cars.get(j).getCurvesPerformance());
					performancesobrepaso.setText("Performancesobrepaso: " + Cars.get(j).getOvertakingPerformance());
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
				Car a1 = new Car();
				a1 = Cars.get(j);
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
		titulo.setText("Seleccione su Car");
		panel.add(titulo);

		marca = new JLabel();
		marca.setBounds(825, 100, 200, 15);
		marca.setText("Marca: " + Cars.get(j).getBrand());
		panel.add(marca);

		modelo = new JLabel();
		modelo.setBounds(950, 100, 100, 15);
		modelo.setText("Modelo: "+Cars.get(i).getModel());
		modelo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(modelo);

		peso = new JLabel();
		peso.setBounds(825, 115, 200, 15);
		peso.setText("Peso: " + Cars.get(i).getWeight()+" Kg");
		panel.add(peso);

		aceleracion = new JLabel();
		aceleracion.setBounds(825, 130, 200, 15);
		aceleracion.setText("Aceleracion: " + Cars.get(i).getAcceleration()+" m/s");
		panel.add(aceleracion);

		velocidadmax = new JLabel();
		velocidadmax.setBounds(825, 145, 200, 15);
		velocidadmax.setText("Velocidad Maxima: " + Cars.get(i).getMaxSpeed()+" Km/h");
		panel.add(velocidadmax);

		potencia = new JLabel();
		potencia.setBounds(825, 160, 200, 15);
		potencia.setText("Potencia: " + Cars.get(i).getPower());
		panel.add(potencia);

		consumo = new JLabel();
		consumo.setBounds(825, 175, 220, 15);
		consumo.setText("Consumo: " + Cars.get(i).getConsumption());
		panel.add(consumo);

		fiabilidad = new JLabel();
		fiabilidad.setBounds(825, 190, 200, 15);
		fiabilidad.setText("Fiabilidad: " + Cars.get(i).getReliability());
		panel.add(fiabilidad);

		performancecurvas = new JLabel();
		performancecurvas.setBounds(825, 205, 250, 15);
		performancecurvas.setText("Performancecurvas: " + Cars.get(i).getCurvesPerformance());
		panel.add(performancecurvas);

		performancesobrepaso = new JLabel();
		performancesobrepaso.setBounds(825, 220, 200, 15);
		performancesobrepaso.setText("Performancesobrepaso: " + Cars.get(i).getOvertakingPerformance());
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
