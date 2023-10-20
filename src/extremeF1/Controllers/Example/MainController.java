package extremeF1.Controllers.Example;

import java.util.ArrayList;
import java.util.List;
import Core.Entities.*;
import Repository.CarRepository;
import Repository.CircuitRepository;
import Repository.CountryRepository;
import Repository.DriverRepository;
import extremeF1.Views.PrincipalView;

public class MainController {

 
    private List<Player> players = new ArrayList<>();
    private List<Race> races = new ArrayList<>();
    private PrincipalView gameWindow;
    private Real player;
    private CarRepository CarRepository;
    private DriverRepository DriverRepository;
    

    public static void main(String[] args) {
        new MainController().run();
    }

    public void run() {
    	   
        // Crear instancias de los repositorios
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
        int i=0;
        for (Circuit Circuit : CircuitRepository.getCircuits()) {
            Race Race = new Race(null,++i,players,Circuit); 
            races.add(Race);
        }
  
        gameWindow = new PrincipalView();

        StartGame(CarRepository,DriverRepository);
 
    }
        
      private void StartGame(CarRepository CarRepository,DriverRepository DriverRepository) { 

        StartViewController startcontroller = new  StartViewController(gameWindow);
        
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
        ChampionshipController championshipController = new ChampionshipController(races, players, gameWindow);
        championshipController.startNextRace();
        championshipController.addObserverEnd(()->{
        	startEndView(championshipController.getChampionship());
        });
    }

    private void startEndView(Championship championship) {
    	EndViewController endController = new EndViewController(gameWindow,championship);
    	endController.initalEndView();
    	endController.addObserverNext(()->{
    		StartGame(CarRepository,DriverRepository);
    	});
    	
    }
 
}