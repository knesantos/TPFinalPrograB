package extremeF1.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.Serializable;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Core.Entities.Championship;
import Core.Entities.Real;
import Core.Entities.Events.CloseGameEvent;
import Core.Entities.Events.CloseGameListener;
import Core.Entities.Events.LoadGameListener;
import Core.Entities.Events.StartGameEvent;
import Core.Entities.Events.StartGameListener;
import extremeF1.Controllers.Example.MainController;

public class StartView extends JPanel implements StartViewInterface, Serializable{
	private CloseGameListener closelistener;
	private StartGameListener startlistener;
	private LoadGameListener loadGamelistener;
	private MainController mainController;
	private Real player;
	
	private JTextField  text;
    private JButton aceptname;
    private JButton back;
    private JButton loadgame;
    private JButton closegame;
    private JButton startgame;
    
    
    

  
	
	public StartView(MainController main) {
			mainController=main;
		 GridBagLayout layout = new GridBagLayout();
	        GridBagConstraints gbc = new GridBagConstraints();
	        setLayout(layout);
	        setBackground(new Color(44, 62, 80));

	        // Configuraciones generales para el GridBagConstraints
	        gbc.fill = GridBagConstraints.BOTH;
	        gbc.weightx = 1;
	        gbc.weighty = 0.1;

        // Título del juego
        JLabel titleGame = new JLabel("EXTREME F1");
        titleGame.setFont(new Font("Comic Sans MS", Font.BOLD, 150));
        titleGame.setForeground(new Color(236, 240, 241));
        titleGame.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridx = 0; // Primera columna
        gbc.gridy = 0; // Primera fila
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Ocupa toda la fila
        gbc.insets = new Insets(10, 10, 10, 10); // Margen
        add(titleGame, gbc);

        
        
        startgame = createButton("Jugar", new Font("Comic Sans MS", Font.PLAIN, 18));    
        closegame = createButton("Cerrar", new Font("Comic Sans MS", Font.PLAIN, 18));   
        loadgame = createButton("Cargar Partida", new Font("Comic Sans MS", Font.PLAIN, 18)); 
        
        text = createTextField(new Font("Comic Sans MS", Font.PLAIN, 18));
        text.setVisible(false); // Inicialmente invisible
        
        aceptname = createButton("Aceptar", new Font("Comic Sans MS", Font.PLAIN, 18));
        back = createButton("Atrás", new Font("Comic Sans MS", Font.PLAIN, 18));

        text.setVisible(false);
        aceptname.setVisible(false);
        back.setVisible(false);

        // Añadiendo los componentes que siempre son visibles al inicio
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(startgame, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(closegame, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(loadgame, gbc);

        // Añadiendo los componentes que serán visibles después de una acción
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        text.setVisible(false); // Inicialmente invisible
        add(text, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        aceptname.setVisible(false); // Inicialmente invisible
        add(aceptname, gbc);
        
        
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        back.setVisible(false); // Inicialmente invisible
        add(back, gbc);
        setUpActionListeners();
      
	}

	private void setUpActionListeners() {
	    startgame.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Muestra los componentes para aceptar el nombre
	            showNameEntryComponents();
	        }
	    });

	    aceptname.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
	            acceptNameAndStartGame();
	        }
			 
		 });

	    back.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Muestra los componentes iniciales
	            showInitialComponents();
	        }
	    });

	    closegame.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Cierra el juego
	            closeGame();
	        }
	    });
	    
	    loadgame.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Este botón debería cargar un juego guardado.
	            Championship loadedChampionship = mainController.loadChampionship();
	            if (loadedChampionship != null) {
	                mainController.resumeGame(loadedChampionship);
	            } else {
	                System.out.println("No hay juego guardado para cargar.");
	            }
	        }
	    });
	    
	}

	private void showNameEntryComponents() {
	    loadgame.setVisible(false);
	    closegame.setVisible(false);
	    startgame.setVisible(false);

	    text.setVisible(true);
	    aceptname.setVisible(true);
	    back.setVisible(true);
	    
	    text.requestFocusInWindow(); // Enfocar en el campo de texto

	    repaint();
	    revalidate();
	}

	private void acceptNameAndStartGame() {
	    String playerName = text.getText().trim();
	    if (!playerName.isEmpty()) {
	        setPlayer(playerName);
	        if (startlistener != null) {
	            startlistener.ListenerStartGame(new StartGameEvent());
	        }
	    } else {
	        // Mostrar mensaje de error si el nombre está vacío
	        JOptionPane.showMessageDialog(this, "Por favor, ingresa un nombre para empezar.", "Nombre requerido", JOptionPane.WARNING_MESSAGE);
	    }
	}

	private void showInitialComponents() {
	    text.setVisible(false);
	    aceptname.setVisible(false);
	    back.setVisible(false);

	    loadgame.setVisible(true);
	    closegame.setVisible(true);
	    startgame.setVisible(true);
	    
	    repaint();
	    revalidate();
	}

	
	
	
	private void closeGame() {
	    if (closelistener != null) {
	        closelistener.ListenerCloseGameListener(new CloseGameEvent());
	    }
	}

        // Método para alternar la visibilidad de los componentes
        private void toggleComponentsVisibility(boolean showInitialComponents) {
            startgame.setVisible(showInitialComponents);
            closegame.setVisible(showInitialComponents);
            loadgame.setVisible(showInitialComponents);

            text.setVisible(!showInitialComponents);
            aceptname.setVisible(!showInitialComponents);
            back.setVisible(!showInitialComponents);

            repaint();
            revalidate();
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
	private JButton createButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(new Color(236, 240, 241));
        button.setBorder(new EmptyBorder(10, 10, 10, 10));
        return button;
    }

	private JTextField createTextField(Font font) {
	    JTextField textField = new JTextField("Ingrese su nombre");
	    textField.setFont(font);
	    textField.setForeground(Color.BLACK); 
	    textField.setBackground(new Color(236, 240, 241));

	    textField.addFocusListener(new FocusListener() {
	        boolean firstTime = true; 

	        @Override
	        public void focusGained(FocusEvent e) {
	            if (firstTime) {
	                textField.setText(""); 
	                firstTime = false; 
	            }
	        }

	        @Override
	        public void focusLost(FocusEvent e) {
	            
	        }
	    });

	    return textField;
	}


    public String getPlayerName() {
        return text.getText().trim();
    }



	@Override
	public void setLoadListener(Core.Entities.Events.LoadGameListener listener) {
		// TODO Auto-generated method stub
		this.loadGamelistener=listener;
	}
	  

}