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
    public static void main(String[] args) {
        new MainController().run();
    }

    public void run() {
    	   
        // Crear instancias de los repositorios
        CarRepository CarRepository = new CarRepository();
        CircuitRepository CircuitRepository = new CircuitRepository();
        CountryRepository CountryRepository = new CountryRepository();
        DriverRepository DriverRepository = new DriverRepository();

        // Cargar datos desde XML
        CarRepository.loadCarsFromXML();
        CircuitRepository.loadCircuitsFromXML();
        CountryRepository.loadContriesFromXML();
        DriverRepository.loadDriversFromXML();
        // Crear Player Real
        Real player = new Real("Gonzalo", 23);
   
        
        
        
        
       
        // Crear Races basadas en Circuits disponibles
        int i=0;
        for (Circuit Circuit : CircuitRepository.getCircuits()) {
            Race Race = new Race(null,++i,players,Circuit); 
            races.add(Race);
        }
  
        gameWindow = new PrincipalView();

        //Iniciar el SelectionController 
        SelectionViewController selectionController = new SelectionViewController(gameWindow);
 
        selectionController.initSelectionScreen(new Real("Gonzalo", 23), CarRepository, DriverRepository);
        selectionController.addObserver(() -> {
            players = selectionController.getListPlayer();
            startChampionship();
        });
    }
    
    private void startChampionship() {
        // Iniciar el ChampionshipController
        ChampionshipController championshipController = new ChampionshipController(races, players, gameWindow);
        championshipController.startNextRace();
    }
    
}