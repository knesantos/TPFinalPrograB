package extremeF1.Views;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Core.Entities.Car;
import Core.Entities.Driver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import Core.Entities.Events.AceptCarEvent;
import Core.Entities.Events.AceptCarListener;
import Core.Entities.Events.AceptPilotEvent;
import Core.Entities.Events.AceptPilotListener;
import Core.Entities.Events.AceptSelectionEvent;
import Core.Entities.Events.AceptSelectionListener;
import Core.Interfaces.SelectionObserver;

public class SelectionView extends JPanel implements SelectionViewInterface {

	private JButton boton1, boton2,boton3,boton4, ButtomSelect1, ButtomCancel1,ButtomSelect2,ButtomCancel2 ,btnAceptarSeleccion;
	private JLabel titulo,titulo1, Name, nomabv, imagenDriver,imagenAuto, Budget, Defense, sobrepaso, clasificacion, cantCarrerasGanadas,
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
		setBackground(new Color(44, 62, 80));
		this.setPreferredSize(new Dimension(1500,1500));
		this.setLayout(null);
		imagenDriver = new JLabel();
		imagenAuto = new JLabel();
		Font infoFont = new Font("Comic Sans MS", Font.PLAIN, 18);
		SeleccionDriver();
		SeleccionCar();
	}
		
		public void SeleccionDriver () {
		// Botones//
		btnAceptarSeleccion = new JButton("Aceptar Selección");
		btnAceptarSeleccion.setEnabled(false);
		btnAceptarSeleccion.setBackground(new Color(236, 240, 241));    
	    btnAceptarSeleccion.setBounds(770, 900, 150, 50);  // Ajusta la posición y tamaño según necesites
	    this.add(btnAceptarSeleccion);	
		
		boton1 = new JButton();
		boton2 = new JButton();
		this.add(boton2);
		this.add(boton1);
		boton1.setBounds(100, 900, 50, 50);
		boton2.setBounds(150, 900, 50, 50);
		boton1.setBackground(new Color(236, 240, 241));
		boton2.setBackground(new Color(236, 240, 241));
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
					Name.setText("Nombre: " + Drivers.get(i).getName());
					nomabv.setText(Drivers.get(i).getShortName());
					Budget.setText("Presupuesto: " + Drivers.get(i).getBudget());
					Defense.setText("Defensa: " + Drivers.get(i).getDefense());
					sobrepaso.setText("Sobrepaso: " + Drivers.get(i).getOvertaking());
					clasificacion.setText("Clasificacion: " + Drivers.get(i).getRanking());
					cantCarrerasGanadas.setText("Cantiddad de carreras ganadas: " + Drivers.get(i).getRacesWon());
					cantParticipaciones.setText("Cantiddad de parcitipaciones: " + Drivers.get(i).getParticipations());
					cuidadoNeumaticos.setText("Cuidado de neumaticos: " + Drivers.get(i).getTireCare());
					largada.setText("Largada: " + Drivers.get(i).getStart());
					String avatar = Drivers.get(i).getAvatarPath();  
					if (avatar != null) {
					    ImageIcon icon = new ImageIcon(avatar);
					    Image image = icon.getImage(); // transforma el icono en una imagen
					    Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // escala la imagen
					    icon = new ImageIcon(newimg);  // transforma la imagen escalada en un icono
					    System.out.println("Asignando imagen desde: " + avatar);
					    imagenDriver.setBounds(600, 200, 300, 300);  // x, y, width, height
					    imagenDriver.setIcon(icon);
					    imagenDriver.setVisible(true);
					    add(imagenDriver);
					    revalidate();
					    repaint();
					} else {
					    System.out.println("No se pudo asignar la imagen desde: " + avatar);
					    imagenDriver.setIcon(null);
					}
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
					Name.setText("Nombre: " + Drivers.get(i).getName());
					nomabv.setText(Drivers.get(i).getShortName());
					Budget.setText("Presupuesto: " + Drivers.get(i).getBudget());
					Defense.setText("Defensa: " + Drivers.get(i).getDefense());
					sobrepaso.setText("Sobrepaso: " + Drivers.get(i).getOvertaking());
					clasificacion.setText("Clasificacion: " + Drivers.get(i).getRanking());
					cantCarrerasGanadas.setText("Cantiddad de carreras ganadas: " + Drivers.get(i).getRacesWon());
					cantParticipaciones.setText("Cantiddad de parcitipaciones: " + Drivers.get(i).getParticipations());
					cuidadoNeumaticos.setText("Cuidado de neumaticos: " + Drivers.get(i).getTireCare());
					largada.setText("Largada: " + Drivers.get(i).getStart());
					Image avatar = Drivers.get(i).getAvatar();  
					if (avatar != null) {
					    ImageIcon icon = new ImageIcon(avatar);
					    Image image = icon.getImage(); // transforma el icono en una imagen
					    Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // escala la imagen
					    icon = new ImageIcon(newimg);  // transforma la imagen escalada en un icono
					    System.out.println("Asignando imagen desde: " + avatar);
					    imagenDriver.setBounds(600, 200, 300, 300);  // x, y, width, height
					    imagenDriver.setIcon(icon);
					    imagenDriver.setVisible(true);
					    add(imagenDriver);
					    revalidate();
					    repaint();
					} else {
					    System.out.println("No se pudo asignar la imagen desde: " + avatar);
					    imagenDriver.setIcon(null);
					}
				}
				revalidate();
				 repaint();
			}

		});

		ButtomSelect1 = new JButton();
		ButtomSelect1.setBounds(300, 900, 100, 50);
		ButtomSelect1.setBackground(new Color(236, 240, 241));
		ButtomSelect1.setText("Aceptar");
		this.add(ButtomSelect1);
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
		ButtomCancel1.setBounds(400, 900, 100, 50);
		ButtomCancel1.setText("Cancelar");
		ButtomCancel1.setBackground(new Color(236, 240, 241));
		ButtomCancel1.setEnabled(false);
		this.add(ButtomCancel1);
		ButtomCancel1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtomSelect1.setEnabled(true);
				boton1.setEnabled(true);
				boton2.setEnabled(true);
				ButtomCancel1.setEnabled(false);
				isPilotSelected = false;
				checkBothSelected();
			}

		});

		// labels//
		titulo = new JLabel();
		titulo.setBounds(200, 0, 600, 100);
		titulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
		titulo.setForeground(new Color(236, 240, 241));
		titulo.setText("Seleccione su Piloto");
		this.add(titulo);

		Name = new JLabel();
		Name.setBounds(100, 100, 400, 70);
		Name.setPreferredSize(new Dimension(200, 15));
		Name.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		Name.setForeground(new Color(236, 240, 241));
		Name.setText("Nombre: " + Drivers.get(i).getName());
		this.add(Name);

		nomabv = new JLabel();
		nomabv.setBounds(500, 100, 100, 70);
		nomabv.setText(Drivers.get(i).getShortName());
		nomabv.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		nomabv.setForeground(new Color(236, 240, 241));
		nomabv.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(nomabv);

		Budget = new JLabel();
		Budget.setBounds(100, 170, 400, 70);
		Budget.setText("Presupuesto: " + Drivers.get(i).getBudget());
		Budget.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		Budget.setForeground(new Color(236, 240, 241));
		this.add(Budget);

		Defense = new JLabel();
		Defense.setBounds(100, 240, 400, 70);
		Defense.setText("Defensa: " + Drivers.get(i).getDefense());
		Defense.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		Defense.setForeground(new Color(236, 240, 241));
		this.add(Defense);

		sobrepaso = new JLabel();
		sobrepaso.setBounds(100, 310, 400, 70);
		sobrepaso.setText("Sobrepaso: " + Drivers.get(i).getOvertaking());
		sobrepaso.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		sobrepaso.setForeground(new Color(236, 240, 241));
		this.add(sobrepaso);

		clasificacion = new JLabel();
		clasificacion.setBounds(100, 380, 400, 70);
		clasificacion.setText("Clasificacion: " + Drivers.get(i).getRanking());
		clasificacion.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		clasificacion.setForeground(new Color(236, 240, 241));
		this.add(clasificacion);

		cantCarrerasGanadas = new JLabel();
		cantCarrerasGanadas.setBounds(100, 450, 600, 70);
		cantCarrerasGanadas.setText("Cantiddad de carreras ganadas: " + Drivers.get(i).getRacesWon());
		cantCarrerasGanadas.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		cantCarrerasGanadas.setForeground(new Color(236, 240, 241));
		this.add(cantCarrerasGanadas);

		cantPolePosition = new JLabel();
		cantPolePosition.setBounds(100, 520, 400, 70);
		cantPolePosition.setText("Cantiddad de pole: " + Drivers.get(i).getPolePositions());
		cantPolePosition.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		cantPolePosition.setForeground(new Color(236, 240, 241));
		this.add(cantPolePosition);

		cantCampeonatos = new JLabel();
		cantCampeonatos.setBounds(100, 590, 600, 70);
		cantCampeonatos.setText("Cantiddad de camponeatos ganados: " + Drivers.get(i).getChampionships());
		cantCampeonatos.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		cantCampeonatos.setForeground(new Color(236, 240, 241));
		this.add(cantCampeonatos);

		cantParticipaciones = new JLabel();
		cantParticipaciones.setBounds(100, 660, 600, 70);
		cantParticipaciones.setText("Cantiddad de parcitipaciones: " + Drivers.get(i).getParticipations());
		cantParticipaciones.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		cantParticipaciones.setForeground(new Color(236, 240, 241));
		this.add(cantParticipaciones);

		cuidadoNeumaticos = new JLabel();
		cuidadoNeumaticos.setBounds(100, 730, 600, 70);
		cuidadoNeumaticos.setText("Cuidado de neumaticos: " + Drivers.get(i).getTireCare());
		cuidadoNeumaticos.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		cuidadoNeumaticos.setForeground(new Color(236, 240, 241));
		this.add(cuidadoNeumaticos);

		largada = new JLabel();
		largada.setBounds(100, 800, 400, 70);
		largada.setText("Largada: " + Drivers.get(i).getStart());
		largada.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		largada.setForeground(new Color(236, 240, 241));
		this.add(largada);
		
		
		String avatar = Drivers.get(i).getAvatarPath();  
		
		if (avatar != null) {
		    ImageIcon icon = new ImageIcon(avatar);
		    Image image = icon.getImage(); // transforma el icono en una imagen
		    Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // escala la imagen
		    icon = new ImageIcon(newimg);  // transforma la imagen escalada en un icono
		    System.out.println("Asignando imagen desde: " + avatar);
		    imagenDriver.setBounds(600, 200, 300, 300);  // x, y, width, height
		    imagenDriver.setIcon(icon);
		    imagenDriver.setVisible(true);
		    this.add(imagenDriver);
		    this.revalidate();
		    this.repaint();
		} else {
		    System.out.println("No se pudo asignar la imagen desde: " + avatar);
		    imagenDriver.setIcon(null);
		}
		this.add(imagenDriver);

	}
	
	public void SeleccionCar(){
		boton3 = new JButton();
		boton4 = new JButton();
		this.add(boton3);
		this.add(boton4);
		boton3.setBounds(1000, 900, 50, 50);
		boton4.setBounds(1050, 900, 50, 50);
		boton3.setBackground(new Color(236, 240, 241));
		boton4.setBackground(new Color(236, 240, 241));
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
					Image avatar = Cars.get(j).getAvatar();  // Asumiendo que Cars.get(j).getAvatar() te da la imagen del auto
					if (avatar != null) {
					    ImageIcon icon = new ImageIcon(avatar);
					    Image image = icon.getImage(); // transforma el icono en una imagen
					    Image newimg = image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH); // escala la imagen
					    icon = new ImageIcon(newimg);  // transforma la imagen escalada en un icono
					    System.out.println("Asignando imagen del auto desde: " + avatar);
					    imagenAuto.setBounds(1500, 200, 300, 300);  // x, y, width, height
					    imagenAuto.setIcon(icon);
					    imagenAuto.setVisible(true);
					    add(imagenAuto);
					    revalidate();
					    repaint();
					} else {
					    System.out.println("No se pudo asignar la imagen del auto desde: " + avatar);
					    imagenAuto.setIcon(null);
					}
				}
				 revalidate();
				 repaint();
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
					Image avatar = Cars.get(j).getAvatar();  // Asumiendo que Cars.get(j).getAvatar() te da la imagen del auto
					if (avatar != null) {
					    ImageIcon icon = new ImageIcon(avatar);
					    Image image = icon.getImage(); // transforma el icono en una imagen
					    Image newimg = image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH); // escala la imagen
					    icon = new ImageIcon(newimg);  // transforma la imagen escalada en un icono
					    System.out.println("Asignando imagen del auto desde: " + avatar);
					    imagenAuto.setBounds(1500, 200, 300, 300);  // x, y, width, height
					    imagenAuto.setIcon(icon);
					    imagenAuto.setVisible(true);
					    add(imagenAuto);
					    revalidate();
					    repaint();
					} else {
					    System.out.println("No se pudo asignar la imagen del auto desde: " + avatar);
					    imagenAuto.setIcon(null);
					}
				}
				revalidate();
				 repaint();
			}

		});

		ButtomSelect2 = new JButton();
		ButtomSelect2.setBounds(1200, 900, 100, 50);
		ButtomSelect2.setBackground(new Color(236, 240, 241));
		ButtomSelect2.setText("Aceptar");
		this.add(ButtomSelect2);
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
		ButtomCancel2.setBounds(1300, 900, 100, 50);
		ButtomCancel2.setBackground(new Color(236, 240, 241));
		ButtomCancel2.setText("Cancelar");
		ButtomCancel2.setEnabled(false);
		this.add(ButtomCancel2);
		ButtomCancel2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ButtomSelect2.setEnabled(true);
				boton3.setEnabled(true);
				boton4.setEnabled(true);
				ButtomCancel2.setEnabled(false);
				isCarSelected = false;
				checkBothSelected();
			}

		});

		// labels//
		titulo1 = new JLabel();
		titulo1.setBounds(1100, 0, 600, 100);
		titulo1.setText("Seleccione su Auto");
		titulo1.setForeground(new Color(236, 240, 241));
		titulo1.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
		this.add(titulo1);

		marca = new JLabel();
		marca.setBounds(1000, 100, 400, 70);
		marca.setText("Marca: " + Cars.get(j).getBrand());
		marca.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		marca.setForeground(new Color(236, 240, 241));
		this.add(marca);

		modelo = new JLabel();
		modelo.setBounds(1000, 170, 400, 70);
		modelo.setText("Modelo: "+Cars.get(i).getModel());
		modelo.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		modelo.setForeground(new Color(236, 240, 241));
		this.add(modelo);

		peso = new JLabel();
		peso.setBounds(1000, 240, 400, 70);
		peso.setText("Peso: " + Cars.get(i).getWeight()+" Kg");
		peso.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		peso.setForeground(new Color(236, 240, 241));
		this.add(peso);

		aceleracion = new JLabel();
		aceleracion.setBounds(1000, 310, 400, 70);
		aceleracion.setText("Aceleracion: " + Cars.get(i).getAcceleration()+" m/s");
		aceleracion.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		aceleracion.setForeground(new Color(236, 240, 241));
		this.add(aceleracion);

		velocidadmax = new JLabel();
		velocidadmax.setBounds(1000, 380, 400, 70);
		velocidadmax.setText("Velocidad Maxima: " + Cars.get(i).getMaxSpeed()+" Km/h");
		velocidadmax.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		velocidadmax.setForeground(new Color(236, 240, 241));
		this.add(velocidadmax);

		potencia = new JLabel();
		potencia.setBounds(1000, 450, 400, 70);
		potencia.setText("Potencia: " + Cars.get(i).getPower());
		potencia.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		potencia.setForeground(new Color(236, 240, 241));
		this.add(potencia);

		consumo = new JLabel();
		consumo.setBounds(1000, 520, 400, 70);
		consumo.setText("Consumo: " + Cars.get(i).getConsumption());
		consumo.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		consumo.setForeground(new Color(236, 240, 241));
		this.add(consumo);

		fiabilidad = new JLabel();
		fiabilidad.setBounds(1000, 590, 400, 70);
		fiabilidad.setText("Fiabilidad: " + Cars.get(i).getReliability());
		fiabilidad.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		fiabilidad.setForeground(new Color(236, 240, 241));
		this.add(fiabilidad);

		performancecurvas = new JLabel();
		performancecurvas.setBounds(1000, 660, 400, 70);
		performancecurvas.setText("Performancecurvas: " + Cars.get(i).getCurvesPerformance());
		performancecurvas.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		performancecurvas.setForeground(new Color(236, 240, 241));
		this.add(performancecurvas);

		performancesobrepaso = new JLabel();
		performancesobrepaso.setBounds(1000, 730, 400, 70);
		performancesobrepaso.setText("Performancesobrepaso: " + Cars.get(i).getOvertakingPerformance());
		performancesobrepaso.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		performancesobrepaso.setForeground(new Color(236, 240, 241));
		this.add(performancesobrepaso);

		Image avatar = Cars.get(j).getAvatar();  // Asumiendo que Cars.get(j).getAvatar() te da la imagen del auto
		if (avatar != null) {
		    ImageIcon icon = new ImageIcon(avatar);
		    Image image = icon.getImage(); // transforma el icono en una imagen
		    Image newimg = image.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH); // escala la imagen
		    icon = new ImageIcon(newimg);  // transforma la imagen escalada en un icono
		    System.out.println("Asignando imagen del auto desde: " + avatar);
		    imagenAuto.setBounds(1500, 200, 300, 300);  // x, y, width, height
		    imagenAuto.setIcon(icon);
		    imagenAuto.setVisible(true);
		    add(imagenAuto);
		    revalidate();
		    repaint();
		} else {
		    System.out.println("No se pudo asignar la imagen del auto desde: " + avatar);
		    imagenAuto.setIcon(null);
		}
		this.add(imagenAuto);
		
		System.out.println("JLabel imagenAuto es visible: " + imagenAuto.isVisible());
		System.out.println("JLabel imagenDriver es visible: " + imagenDriver.isVisible());
		
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
	    }else {
	    	 btnAceptarSeleccion.setEnabled(false);
	    }
	}


	@Override
	public void setButtonAcceptSelectionListener(AceptSelectionListener listener) {
		buttonAcceptSelectionListener = listener;
		
	}

	
	public Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();
	    return resizedImg;
	}
	

}
