package extremeF1.Controllers.Example;

import java.util.ArrayList;
import java.util.List;
import Core.Entities.*;
import Repository.AutoRepository;
import Repository.CircuitoRepository;
import Repository.PaisRepository;
import Repository.PilotoRepository;

public class MainController {

 
    private List<Jugador> players = new ArrayList<>();
    private List<Carrera> races = new ArrayList<>();
    
    public static void main(String[] args) {
        new MainController().run();
    }

    public void run() {
    	   
        // Crear instancias de los repositorios
        AutoRepository autoRepository = new AutoRepository();
        CircuitoRepository circuitoRepository = new CircuitoRepository();
        PaisRepository paisRepository = new PaisRepository();
        PilotoRepository pilotoRepository = new PilotoRepository();

        // Cargar datos desde XML
        autoRepository.loadAutosFromXML();
        circuitoRepository.loadCircuitosFromXML();
        paisRepository.loadPaisesFromXML();
        pilotoRepository.loadPilotosFromXML();

        // Crear jugador Real
        Real player = new Real("Gonzalo", 23);
        
        // Iniciar pantalla de selección
        SelectionViewController selectionController = new SelectionViewController();
        players = selectionController.initSelectionScreen(player, autoRepository, pilotoRepository);
        System.out.println("Jugadores seleccionados: " + players);

        // Crear carreras basadas en circuitos disponibles
        int i=0;
        for (Circuito circuito : circuitoRepository.getCircuitos()) {
            Carrera carrera = new Carrera(null,++i,players,circuito); 
            races.add(carrera);
        }

     // Iniciar el ChampionshipController
        ChampionshipController championshipController = new ChampionshipController(races, players);

        // Iniciar el RaceViewController
        RaceViewController raceController = new RaceViewController();
        raceController.addRaceEndObserver(() -> championshipController.onRaceEnd());

        // Pasar la instancia de RaceViewController al ChampionshipController
        championshipController.setRaceController(raceController);
        
        // Registrar un observador para iniciar la carrera cuando se complete la selección
        selectionController.addObserver(new SelectionViewController.SelectionObserver() {
            @Override
            public void onSelectionComplete() {
                // Aquí puedes disparar la controladora de carrera
                championshipController.startNextRace();  // Esto iniciará la primera carrera
            }
        });
    }
}