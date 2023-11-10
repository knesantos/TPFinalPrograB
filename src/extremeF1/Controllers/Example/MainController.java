package extremeF1.Controllers.Example;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Core.Entities.*;
import Repository.CarRepository;
import Repository.CircuitRepository;
import Repository.CountryRepository;
import Repository.DriverRepository;
import extremeF1.Views.PrincipalView;

public class MainController implements Serializable{

 
    private List<Player> players = new ArrayList<>();
    private List<Race> races = new ArrayList<>();
    private Championship championship = null;
    private PrincipalView gameWindow;
    private Real player;
    private CarRepository CarRepository;
    private DriverRepository DriverRepository;
    

    public static void main(String[] args) {
        new MainController().run();
    }

    public void run() {
    	   
    	 prepareGameEnvironment();
    	 showStartScreen();

    	
    	    }
  
        
    private void prepareGameEnvironment() {
        CarRepository = new CarRepository();
        CircuitRepository CircuitRepository = new CircuitRepository();
        CountryRepository CountryRepository = new CountryRepository();
        DriverRepository = new DriverRepository();

        // Cargar datos desde XML
        CarRepository.loadCarsFromXML();
        CircuitRepository.loadCircuitsFromXML();
        CountryRepository.loadContriesFromXML();
        DriverRepository.loadDriversFromXML();

        // Crear Races basadas en Circuits disponibles
        int i = 0;
        for (Circuit circuit : CircuitRepository.getCircuits()) {
            Race race = new Race(null, ++i, players, circuit);
            races.add(race);
        }

        gameWindow = new PrincipalView();
    }
    
    private void showStartScreen() {
        StartViewController startcontroller = new StartViewController(gameWindow, this);

        // Crea la vista de inicio y muestra los botones pertinentes
        startcontroller.initialSartView();

        
        // Definir qué hacer cuando se presiona el botón "Cargar Partida"
        startcontroller.addObserverLoad(() -> {
            Championship loadedChampionship = loadChampionship();
            if (loadedChampionship != null) {
                resumeGame(loadedChampionship);
            } else {
                // No hay juego guardado, por lo tanto, inicia uno nuevo o muestra un mensaje
                System.out.println("No se encontró un campeonato guardado para cargar.");
                // Opcionalmente iniciar un nuevo juego o mostrar un diálogo de error
            }
        });

        // Definir qué hacer cuando se presiona el botón "Cerrar"
        startcontroller.addObserverClose(() -> {
            System.out.println("El programa ha sido detenido.");
            System.exit(0);
        });
        
        
        startcontroller.initialSartView();
        startcontroller.addObserverStart(()->{
        	startSelection(startcontroller.getPlayer(),CarRepository,DriverRepository);
        });
        startcontroller.addObserverClose(()->{
            System.out.println("El programa ha sido detenido.");
            System.exit(1); 
        });
    }

    
     
    
    private void startSelection(Real player,CarRepository CarRepository,DriverRepository DriverRepository) {
    	SelectionViewController selectionController = new SelectionViewController(gameWindow);
		selectionController.initSelectionScreen(player, CarRepository, DriverRepository);
    	 selectionController.addObserver(() -> {
             players = selectionController.getListPlayer();
             startChampionship();
         });
     }
    
    
    private void startChampionship() {
        // Iniciar el ChampionshipController
        ChampionshipController championshipController = new ChampionshipController(races, players, gameWindow,this.championship);
        this.championship =championshipController.getChampionship();
        championshipController.setMainController(this);
        championshipController.startNextRace();
        championshipController.addObserverEnd(()->{
        	startEndView(championshipController.getChampionship());
        });
    }

    private void startEndView(Championship championship) {
    	EndViewController endController = new EndViewController(gameWindow,championship);
    	endController.initalEndView();
    	endController.addObserverNext(()->{
    		
    	});
    	
    }
    
    public void resumeGame(Championship loadedChampionship) {
    	championship=loadedChampionship;
        ChampionshipController championshipController = new ChampionshipController(races, players, gameWindow,loadedChampionship);
        championshipController.setMainController(this);
        championshipController.resumeChampionship(loadedChampionship);
    }
 
    public void saveGame() {
        if (championship == null) {
            System.out.println("No se puede guardar el juego en este momento.");
            return;
        }
        try {
            PersistenceController.persistChampionship(championship);
            System.out.println("Juego guardado con éxito.");
        } catch (Exception e) {
            System.out.println("Un error inesperado ocurrió al guardar: " + e.getMessage());
           
        }
    }

    public Championship loadChampionship() {
        return PersistenceController.championshipInstance();
    }
}