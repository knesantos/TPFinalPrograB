package extremeF1.Controllers.Example;

import java.util.ArrayList;
import java.util.List;
import Core.Entities.*;
import Repository.CarRepository;
import Repository.CircuitRepository;
import Repository.CountryRepository;
import Repository.DriverRepository;

public class MainController {

 
    private List<Player> players = new ArrayList<>();
    private List<Race> races = new ArrayList<>();
    
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
        
        // Iniciar pantalla de selección
        SelectionViewController selectionController = new SelectionViewController();
        players = selectionController.initSelectionScreen(player, CarRepository, DriverRepository);
        System.out.println("Playeres seleccionados: " + players);
        
        // Crear Races basadas en Circuits disponibles
        int i=0;
        for (Circuit Circuit : CircuitRepository.getCircuits()) {
            Race Race = new Race(null,++i,players,Circuit); 
            races.add(Race);
        }
  

     // Iniciar el ChampionshipController
        ChampionshipController championshipController = new ChampionshipController(races, players);

        // Iniciar el RaceViewController
        RaceViewController raceController = new RaceViewController();
        raceController.addRaceEndObserver(() -> championshipController.onRaceEnd());

        // Pasar la instancia de RaceViewController al ChampionshipController
        championshipController.setRaceController(raceController);
        
        selectionController.addObserver(new SelectionViewController.SelectionObserver() {
            @Override
            public void onSelectionComplete() {
            	  PitsViewController pitsview = new PitsViewController();
                  pitsview.PitsViewController(races.get(1), player);
            }
        });
    }
}