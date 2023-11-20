package extremeF1.Views;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Core.Entities.Car;
import Core.Entities.Driver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.Font;

import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import Core.Entities.Events.AceptCarEvent;
import Core.Entities.Events.AceptCarListener;
import Core.Entities.Events.AceptPilotEvent;
import Core.Entities.Events.AceptPilotListener;
import Core.Entities.Events.AceptSelectionEvent;
import Core.Entities.Events.AceptSelectionListener;
import Core.Interfaces.SelectionObserver;

public class SelectionView extends JPanel implements SelectionViewInterface , Serializable{

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

	 public SelectionView(List<Driver> listDrivers, List<Car> listaCars) {
	        Drivers = listDrivers;
	        Cars = listaCars;
	        setBackground(new Color(44, 62, 80));
	        setLayout(new BorderLayout(10, 10)); // Añade espacio entre componentes

	        JPanel pilotPanel = createPilotPanel();
	        JPanel carPanel = createCarPanel();
	        JPanel acceptButtonPanel = createAcceptButtonPanel();

	        add(pilotPanel, BorderLayout.WEST);
	        add(carPanel, BorderLayout.EAST);
	        add(acceptButtonPanel, BorderLayout.SOUTH);
	    }
	 private JPanel createAcceptButtonPanel() {
		    JPanel acceptButtonPanel = new JPanel();
		    acceptButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Puedes ajustar el layout según tus preferencias
		    acceptButtonPanel.setBackground(new Color(44, 62, 80)); // Color de fondo del panel

		    // Creación del botón Aceptar Selección
		    btnAceptarSeleccion = new JButton("Aceptar Selección");
		    btnAceptarSeleccion.setEnabled(false); // Inicialmente desactivado
		    btnAceptarSeleccion.setBackground(new Color(236, 240, 241));
		    btnAceptarSeleccion.setPreferredSize(new Dimension(150, 50)); // Dimensiones del botón

		    // Añadir ActionListener al botón
		    btnAceptarSeleccion.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            if (buttonAcceptSelectionListener != null) {
		                buttonAcceptSelectionListener.listenerAceptSelectionEvent(new AceptSelectionEvent(true));
		            }
		        }
		    });

		    // Añadir el botón al panel
		    acceptButtonPanel.add(btnAceptarSeleccion);

		    return acceptButtonPanel;
		}
	 
	 private void addAcceptCancelButtons(JPanel panel, boolean isPilotPanel) {
		    JButton acceptButton = new JButton("Aceptar");
		    JButton cancelButton = new JButton("Cancelar");

		    // Configuración de los botones
		    acceptButton.setBackground(new Color(236, 240, 241));
		    cancelButton.setBackground(new Color(236, 240, 241));

		    // Acción para el botón Aceptar
		    acceptButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            if (isPilotPanel) {
		                // Lógica para aceptar la selección del piloto
		                Driver selectedPilot = Drivers.get(i);
		                isPilotSelected = true;
		                if (aceptpilotlistener != null) {
		                    aceptpilotlistener.listenerAceptPilotEvent(new AceptPilotEvent(selectedPilot));
		                }
		            } else {
		                // Lógica para aceptar la selección del auto
		                Car selectedCar = Cars.get(j);
		                isCarSelected = true;
		                if (aceptcarlistener != null) {
		                    aceptcarlistener.listenerAceptCarEvent(new AceptCarEvent(selectedCar));
		                }
		            }
		            checkBothSelected();
		        }
		    });

		    // Acción para el botón Cancelar
		    cancelButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            if (isPilotPanel) {
		                isPilotSelected = false;
		            } else {
		                isCarSelected = false;
		            }
		            checkBothSelected();
		        }
		    });

		    // Añadir los botones al panel
		    panel.add(acceptButton);
		    panel.add(cancelButton);
		}
		
	 private JPanel createPilotPanel() {
		    JPanel pilotPanel = new JPanel();
		    pilotPanel.setLayout(new BoxLayout(pilotPanel, BoxLayout.Y_AXIS));
		    pilotPanel.setBackground(new Color(44, 62, 80));

		    // Añadir título
		    JLabel titulo = new JLabel("Seleccione su Piloto");
		    titulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
		    titulo.setForeground(new Color(236, 240, 241));
		    titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		    pilotPanel.add(titulo);

		    // Botones de navegación y Aceptar/Cancelar
		    JPanel navPanel = createNavPanel(true);
		    pilotPanel.add(navPanel);

		    // Añadir labels y la imagen del piloto
		    addPilotInfoLabels(pilotPanel);

		    return pilotPanel;
		}
	 
	 private void configButton(JButton button) {
		    button.setBackground(new Color(236, 240, 241));
		    button.setMaximumSize(new Dimension(50, 25));
		    button.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
	 
	 private void addPilotInfoLabels(JPanel panel) {
		    // Asumiendo que tienes una variable de instancia 'Drivers' y un índice 'i' para el piloto actual

		    // Nombre del piloto
		    Name = new JLabel("Nombre: " + Drivers.get(i).getName());
		    configLabel(Name);
		    panel.add(Name);

		    // Nombre abreviado del piloto
		    nomabv = new JLabel(Drivers.get(i).getShortName());
		    configLabel(nomabv);
		    panel.add(nomabv);

		    // Presupuesto del piloto
		    Budget = new JLabel("Presupuesto: " + Drivers.get(i).getBudget());
		    configLabel(Budget);
		    panel.add(Budget);

		    // Defensa del piloto
		    Defense = new JLabel("Defensa: " + Drivers.get(i).getDefense());
		    configLabel(Defense);
		    panel.add(Defense);

		    // Sobrepaso del piloto
		    sobrepaso = new JLabel("Sobrepaso: " + Drivers.get(i).getOvertaking());
		    configLabel(sobrepaso);
		    panel.add(sobrepaso);

		    // Clasificación del piloto
		    clasificacion = new JLabel("Clasificación: " + Drivers.get(i).getRanking());
		    configLabel(clasificacion);
		    panel.add(clasificacion);

		    // Carreras ganadas
		    cantCarrerasGanadas = new JLabel("Cantidad de carreras ganadas: " + Drivers.get(i).getRacesWon());
		    configLabel(cantCarrerasGanadas);
		    panel.add(cantCarrerasGanadas);

		    // Pole Positions
		    cantPolePosition = new JLabel("Cantidad de pole: " + Drivers.get(i).getPolePositions());
		    configLabel(cantPolePosition);
		    panel.add(cantPolePosition);

		    // Campeonatos ganados
		    cantCampeonatos = new JLabel("Cantidad de campeonatos ganados: " + Drivers.get(i).getChampionships());
		    configLabel(cantCampeonatos);
		    panel.add(cantCampeonatos);

		    // Participaciones
		    cantParticipaciones = new JLabel("Cantidad de participaciones: " + Drivers.get(i).getParticipations());
		    configLabel(cantParticipaciones);
		    panel.add(cantParticipaciones);

		    // Cuidado de neumáticos
		    cuidadoNeumaticos = new JLabel("Cuidado de neumáticos: " + Drivers.get(i).getTireCare());
		    configLabel(cuidadoNeumaticos);
		    panel.add(cuidadoNeumaticos);

		    // Largada
		    largada = new JLabel("Largada: " + Drivers.get(i).getStart());
		    configLabel(largada);
		    panel.add(largada);

		    // Configuración de imagen del piloto
		    String avatar = Drivers.get(i).getAvatarPath();  
		    imagenDriver = new JLabel();
		    if (avatar != null) {
		        ImageIcon icon = new ImageIcon(avatar);
		        Image image = icon.getImage(); // Transforma el icono en una imagen
		        Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // Escala la imagen
		        icon = new ImageIcon(newimg);  // Transforma la imagen escalada en un icono
		        imagenDriver.setIcon(icon);
		    } else {
		        imagenDriver.setIcon(null);
		    }
		    imagenDriver.setAlignmentX(Component.CENTER_ALIGNMENT);
		    panel.add(imagenDriver);
		}

		private void configLabel(JLabel label) {
		    label.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		    label.setForeground(new Color(236, 240, 241));
		    label.setAlignmentX(Component.CENTER_ALIGNMENT);
		}

		private void addAcceptCancelButtons(JPanel panel) {
		    ButtomSelect1 = new JButton("Aceptar");
		    ButtomSelect1.setBackground(new Color(236, 240, 241));
		    ButtomSelect1.setBounds(300, 900, 100, 50); // Ajusta la posición y el tamaño según sea necesario
		    ButtomSelect1.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            // Código que se ejecuta al presionar "Aceptar"
		            // Por ejemplo, confirmar la selección del piloto actual
		        }
		    });
		    panel.add(ButtomSelect1);

		    ButtomCancel1 = new JButton("Cancelar");
		    ButtomCancel1.setBackground(new Color(236, 240, 241));
		    ButtomCancel1.setBounds(400, 900, 100, 50); // Ajusta la posición y el tamaño según sea necesario
		    ButtomCancel1.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            // Código que se ejecuta al presionar "Cancelar"
		            // Por ejemplo, cancelar la selección actual
		        }
		    });
		    
		    panel.add(ButtomCancel1);
		}

		private void navigateDrivers(int direction) {
		    i += direction; // Actualizar el índice basado en la dirección (-1 para anterior, 1 para siguiente)
		    i = Math.max(0, Math.min(i, Drivers.size() - 1)); // Asegurar que 'i' se mantenga en los límites de la lista

		    // Actualizar la información mostrada del piloto
		    Name.setText("Nombre: " + Drivers.get(i).getName());
		    nomabv.setText(Drivers.get(i).getShortName());
		    // Actualizar el resto de las etiquetas de la misma manera
		    // ...
		    String avatar = Drivers.get(i).getAvatarPath();  
		    if (avatar != null) {
		        ImageIcon icon = new ImageIcon(avatar);
		        Image image = icon.getImage(); // Transforma el icono en una imagen
		        Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // Escala la imagen
		        icon = new ImageIcon(newimg);  // Transforma la imagen escalada en un icono
		        imagenDriver.setIcon(icon);
		    } else {
		        imagenDriver.setIcon(null);
		    }
		    revalidate();
		    repaint();
		}
		
		private void fillPilotPanel(JPanel panel, List<Driver> drivers) {
			if (i < 0 || i >= drivers.size()) return;

		    // Configuración del panel
		    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		    panel.setBackground(new Color(44, 62, 80));

		    // Título
		    JLabel titulo = new JLabel("Seleccione su Piloto");
		    titulo.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
		    titulo.setForeground(new Color(236, 240, 241));
		    titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		    panel.add(titulo);

		    // Botones de navegación y sus configuraciones
		    JButton boton1 = new JButton("<");
		    JButton boton2 = new JButton(">");
		    JButton ButtomSelect1 = new JButton("Aceptar");
		    JButton ButtomCancel1 = new JButton("Cancelar");
		    // [Configuración de los botones aquí...]

		    // Panel para botones
		    JPanel buttonPanel = new JPanel();
		    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		    buttonPanel.add(boton1);
		    buttonPanel.add(ButtomSelect1);
		    buttonPanel.add(ButtomCancel1);
		    buttonPanel.add(boton2);
		    panel.add(buttonPanel);

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
						    imagenDriver.setIcon(icon);
						    imagenDriver.setVisible(true);
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
						    imagenDriver.setIcon(icon);
						    imagenDriver.setVisible(true);
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
		    // Similar a lo que ya tienes en SeleccionDriver

		    // Agregar JLabels para atributos del piloto
		    // Suponiendo que 'i' es el índice del piloto actual
			fillPilotPanelWithLabels(panel, drivers, i);
		    // Agregar el resto de los atributos aquí...

		    // Imagen del piloto
		    String avatar = Drivers.get(i).getAvatarPath();  
			  imagenDriver = new JLabel();
		    if (avatar != null) {
		        ImageIcon icon = new ImageIcon(avatar);
		        Image image = icon.getImage(); // transforma el icono en una imagen
		        Image newimg = image.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH); // escala la imagen
		        icon = new ImageIcon(newimg);  // transforma la imagen escalada en un icono
		        System.out.println("Asignando imagen desde: " + avatar);
		        imagenDriver.setIcon(icon);
		        imagenDriver.setVisible(true);
		    } else {
		        System.out.println("No se pudo asignar la imagen desde: " + avatar);
		        imagenDriver.setIcon(null);
		    }
		}
		
		private void fillPilotPanelWithLabels(JPanel panel, List<Driver> drivers, int selectedIndex) {
		    if (selectedIndex < 0 || selectedIndex >= drivers.size()) return;

		    Driver selectedDriver = drivers.get(selectedIndex);

		    // Crear y agregar labels al panel
		    panel.add(createAttributeLabel("Nombre: " + selectedDriver.getName()));
		    panel.add(createAttributeLabel(selectedDriver.getShortName(), SwingConstants.CENTER));
		    panel.add(createAttributeLabel("Presupuesto: " + selectedDriver.getBudget()));
		    panel.add(createAttributeLabel("Defensa: " + selectedDriver.getDefense()));
		    panel.add(createAttributeLabel("Sobrepaso: " + selectedDriver.getOvertaking()));
		    panel.add(createAttributeLabel("Clasificacion: " + selectedDriver.getRanking()));
		    panel.add(createAttributeLabel("Cantidad de carreras ganadas: " + selectedDriver.getRacesWon()));
		    panel.add(createAttributeLabel("Cantidad de pole: " + selectedDriver.getPolePositions()));
		    panel.add(createAttributeLabel("Cantidad de campeonatos ganados: " + selectedDriver.getChampionships()));
		    panel.add(createAttributeLabel("Cantidad de participaciones: " + selectedDriver.getParticipations()));
		    panel.add(createAttributeLabel("Cuidado de neumáticos: " + selectedDriver.getTireCare()));
		    panel.add(createAttributeLabel("Largada: " + selectedDriver.getStart()));
		}

		private JLabel createAttributeLabel(String text, int alignment) {
		    JLabel label = new JLabel(text);
		    label.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		    label.setForeground(new Color(236, 240, 241));
		    label.setAlignmentX(Component.CENTER_ALIGNMENT);
		    label.setHorizontalAlignment(alignment);
		    return label;
		}

		private JLabel createAttributeLabel(String text) {
		    return createAttributeLabel(text, SwingConstants.LEFT);
		}
	
		private JPanel createCarPanel() {
		    JPanel carPanel = new JPanel();
		    carPanel.setLayout(new BoxLayout(carPanel, BoxLayout.Y_AXIS));
		    carPanel.setBackground(new Color(44, 62, 80));

		    // Añadir título
		    JLabel tituloCar = new JLabel("Seleccione su Auto");
		    tituloCar.setFont(new Font("Comic Sans MS", Font.PLAIN, 60));
		    tituloCar.setForeground(new Color(236, 240, 241));
		    tituloCar.setAlignmentX(Component.CENTER_ALIGNMENT);
		    carPanel.add(tituloCar);

		    // Botones de navegación y Aceptar/Cancelar
		    JPanel navCarPanel = createNavPanel(false);
		    carPanel.add(navCarPanel);

		    // Añadir información y la imagen del auto
		    addCarInfoLabels(carPanel);

		    return carPanel;
		}
		
		private void addCarInfoLabels(JPanel panel) {
		    // Asumiendo que tienes una variable de instancia 'Cars' y un índice 'j' para el auto actual

		    // Marca del auto
		    marca = new JLabel("Marca: " + Cars.get(j).getBrand());
		    configLabel(marca);
		    panel.add(marca);

		    // Modelo del auto
		    modelo = new JLabel("Modelo: " + Cars.get(j).getModel());
		    configLabel(modelo);
		    panel.add(modelo);

		    // Peso del auto
		    peso = new JLabel("Peso: " + Cars.get(j).getWeight() + " Kg");
		    configLabel(peso);
		    panel.add(peso);

		    // Aceleración del auto
		    aceleracion = new JLabel("Aceleración: " + Cars.get(j).getAcceleration() + " m/s²");
		    configLabel(aceleracion);
		    panel.add(aceleracion);

		    // Velocidad máxima
		    velocidadmax = new JLabel("Velocidad Máxima: " + Cars.get(j).getMaxSpeed() + " Km/h");
		    configLabel(velocidadmax);
		    panel.add(velocidadmax);

		    // Potencia del auto
		    potencia = new JLabel("Potencia: " + Cars.get(j).getPower() + " CV");
		    configLabel(potencia);
		    panel.add(potencia);

		    // Consumo del auto
		    consumo = new JLabel("Consumo: " + Cars.get(j).getConsumption());
		    configLabel(consumo);
		    panel.add(consumo);

		    // Fiabilidad del auto
		    fiabilidad = new JLabel("Fiabilidad: " + Cars.get(j).getReliability());
		    configLabel(fiabilidad);
		    panel.add(fiabilidad);

		    // Rendimiento en curvas
		    performancecurvas = new JLabel("Rendimiento en Curvas: " + Cars.get(j).getCurvesPerformance());
		    configLabel(performancecurvas);
		    panel.add(performancecurvas);

		    // Rendimiento en sobrepasos
		    performancesobrepaso = new JLabel("Rendimiento en Sobrepasos: " + Cars.get(j).getOvertakingPerformance());
		    configLabel(performancesobrepaso);
		    panel.add(performancesobrepaso);

		    // Configuración de imagen del auto
		    Image avatarCar = Cars.get(j).getAvatar();  
		    imagenAuto = new JLabel();
		    if (avatarCar != null) {
		        ImageIcon iconCar = new ImageIcon(avatarCar);
		        Image imageCar = iconCar.getImage(); 
		        Image newimgCar = imageCar.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
		        iconCar = new ImageIcon(newimgCar);  
		        imagenAuto.setIcon(iconCar);
		    } else {
		        imagenAuto.setIcon(null);
		    }
		    imagenAuto.setAlignmentX(Component.CENTER_ALIGNMENT);
		    panel.add(imagenAuto);
		}

		private JPanel createNavPanel(boolean isPilotPanel) {
		    JPanel navPanel = new JPanel();
		    navPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		    navPanel.setBackground(new Color(44, 62, 80));

		    // Botones de navegación
		    JButton leftButton = new JButton("<");
		    JButton rightButton = new JButton(">");
		    configButton(leftButton);
		    configButton(rightButton);

		    navPanel.add(leftButton);
		    navPanel.add(rightButton);

		    // Agregar botones Aceptar y Cancelar
		    JButton acceptButton = new JButton("Aceptar");
		    JButton cancelButton = new JButton("Cancelar");
		    configButton(acceptButton);
		    configButton(cancelButton);

		    navPanel.add(acceptButton);
		    navPanel.add(cancelButton);

		    // Configuración de los ActionListeners
		    configureNavButtons(leftButton, rightButton, acceptButton, cancelButton, isPilotPanel);

		    return navPanel;
		}
		
		private void configureNavButtons(JButton leftButton, JButton rightButton, JButton acceptButton, JButton cancelButton, boolean isPilotPanel) {
		    // Listener para el botón izquierdo ("<")
		    leftButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            if (isPilotPanel) {
		                navigateDrivers(-1);
		            } else {
		                navigateCars(-1);
		            }
		        }
		    });

		    // Listener para el botón derecho (">")
		    rightButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            if (isPilotPanel) {
		                navigateDrivers(1);
		            } else {
		                navigateCars(1);
		            }
		        }
		    });

		    // Listener para el botón Aceptar
		    acceptButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            if (isPilotPanel) {
		                // Lógica para aceptar la selección del piloto
		                if (i < Drivers.size()) {
		                    Driver selectedPilot = Drivers.get(i);
		                    if (aceptpilotlistener != null) {
		                        aceptpilotlistener.listenerAceptPilotEvent(new AceptPilotEvent(selectedPilot));
		                    }
		                    isPilotSelected = true;
		                }
		            } else {
		                // Lógica para aceptar la selección del auto
		                if (j < Cars.size()) {
		                    Car selectedCar = Cars.get(j);
		                    if (aceptcarlistener != null) {
		                        aceptcarlistener.listenerAceptCarEvent(new AceptCarEvent(selectedCar));
		                    }
		                    isCarSelected = true;
		                }
		            }
		            checkBothSelected();
		        }
		    });

		    // Listener para el botón Cancelar
		    cancelButton.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            if (isPilotPanel) {
		                isPilotSelected = false;
		            } else {
		                isCarSelected = false;
		            }
		            checkBothSelected();
		        }
		    });
		}
		
		private void navigateCars(int direction) {
		    j += direction;
		    j = Math.max(0, Math.min(j, Cars.size() - 1));

		    // Actualizar la información mostrada del auto
		    marca.setText("Marca: " + Cars.get(j).getBrand());
		    modelo.setText("Modelo: " + Cars.get(j).getModel());
		    peso.setText("Peso: " + Cars.get(j).getWeight() + " Kg");
		    aceleracion.setText("Aceleración: " + Cars.get(j).getAcceleration() + " m/s²");
		    velocidadmax.setText("Velocidad Máxima: " + Cars.get(j).getMaxSpeed() + " Km/h");
		    potencia.setText("Potencia: " + Cars.get(j).getPower() + " CV");
		    consumo.setText("Consumo: " + Cars.get(j).getConsumption());
		    fiabilidad.setText("Fiabilidad: " + Cars.get(j).getReliability());
		    performancecurvas.setText("Rendimiento en Curvas: " + Cars.get(j).getCurvesPerformance());
		    performancesobrepaso.setText("Rendimiento en Sobrepasos: " + Cars.get(j).getOvertakingPerformance());

		    // Actualización de la imagen del auto
		    Image avatarCar = Cars.get(j).getAvatar();  
		    if (avatarCar != null) {
		        ImageIcon iconCar = new ImageIcon(avatarCar);
		        Image imageCar = iconCar.getImage(); 
		        Image newimgCar = imageCar.getScaledInstance(300, 300,  java.awt.Image.SCALE_SMOOTH);
		        iconCar = new ImageIcon(newimgCar);  
		        imagenAuto.setIcon(iconCar);
		    } else {
		        imagenAuto.setIcon(null);
		    }
		    revalidate();
		    repaint();
		}
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
