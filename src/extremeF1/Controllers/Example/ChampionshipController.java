package extremeF1.Controllers.Example;

import Core.Entities.Race;

import Core.Entities.Championship;
import Core.Entities.Circuit;
import Core.Entities.Player;
import extremeF1.Controllers.Example.ReportsViewsController.PreRaceViewController;
import extremeF1.Controllers.Example.StartViewController.StartObserver;
import extremeF1.Views.PreRaceView;
import extremeF1.Views.PrincipalView;
import extremeF1.Controllers.Example.ReportsViewsController.PostRaceViewController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChampionshipController implements ReportsViewsController.PreRaceObserver, ReportsViewsController.PostRaceObserver , java.io.Serializable {
    private List<Race> Races;
    private List<Player> players;
    private int currentRaceIndex ;
    private Championship championship;
    private RaceViewController raceController;
    private PrincipalView gameWindow;
    private MainController mainController;

    public ChampionshipController(List<Race> Races, List<Player> players, PrincipalView gameWindow,Championship championship) {
    	 
    	    this.Races = Races;
    	    this.players = players;
    	    this.gameWindow = gameWindow;

    	    // Determinar si se crea un nuevo campeonato o se carga uno existente
    	    if (championship == null) {
    	    	currentRaceIndex = 0;
    	        this.championship = new Championship(Races.size(), players);

    	        // Configura los circuitos del campeonato utilizando los circuitos dentro de las Races
    	        List<Circuit> circuits = Races.stream()
    	                                      .map(Race::getCircuit)
    	                                      .collect(Collectors.toList());
    	        this.championship.setCircuits(circuits);
    	        this.championship.initializeRemainingCircuits(circuits);
    	    } else {
    	        this.championship = championship;
    	        currentRaceIndex = championship.getRaceIndex();
    	        if (this.championship.getCircuits().isEmpty() && !Races.isEmpty()) {
    	            List<Circuit> circuits = Races.stream()
    	                                          .map(Race::getCircuit)
    	                                          .collect(Collectors.toList());
    	            this.championship.setCircuits(circuits);
    	        }
    	    }

    	    // Inicializa RaceViewController aquí y pasa el campeonato como argumento
    	    this.raceController = new RaceViewController(gameWindow, this.championship);
    	    raceController.addRaceEndObserver(() -> onRaceEnd());
    }
    
   
    
    
    public interface EndObserver{
		void onEndObserver();
	}
	
	private EndObserver endobserver;
	
	public void addObserverEnd(EndObserver endobserver) {
		// TODO Auto-generated method stub
		this.endobserver = endobserver;
	}
	
    
    public void setRaceController(RaceViewController raceController) {
        this.raceController = raceController;
    }
    
    public void onRaceStart() {
        // iniciar Carrera
        System.out.println("onRaceStart index: " + currentRaceIndex);
        Race currentRace = Races.get(currentRaceIndex);
        championship.setActiveCircuit(currentRace.getCircuit());
        raceController.startRace(championship.getPlayers(), currentRace.getCircuit());
    }

    @Override
    public void onRaceEnd() {
        // Actualizar puntos y completar la Race
        System.out.println("End race index: " + currentRaceIndex);
        championship.raceCompleted();
        championship.updatePointsBasedOnPosition(raceController.getRace().getPlayers());

        // Mostrar la vista PostRace
        ReportsViewsController.PostRaceViewController postRaceController = new ReportsViewsController.PostRaceViewController(raceController.getRace(), championship, gameWindow);
        postRaceController.addObserver(() -> {
            if (championship.isChampionshipOver()) {
                System.out.println("Campeonato terminado");
                // Mostrar la vista de resultados finales del campeonato
                endobserver.onEndObserver();
            } else {
                currentRaceIndex++;
                startNextRace();
            }
        });
        gameWindow.showPanel("PostRace"); // Mostrar la vista PostRace
		mainController.saveGame();
    }

    public void startNextRace() {
        if (currentRaceIndex < Races.size()) {
        	System.out.println("startNextRace index: " + currentRaceIndex);
            Race nextRace = Races.get(currentRaceIndex);
            championship.setActiveCircuit(nextRace.getCircuit()); 
            onRaceStart();
        } else {
            System.out.println("No hay más Races en el campeonato");
        }
    }
    public Championship getChampionship() {
    	return  championship;
    }


	public void setMainController(MainController mainController) {
		this.mainController=mainController;
		
	}

	public void resumeChampionship(Championship loadedChampionship) {
	    this.championship = loadedChampionship;
	    this.players = loadedChampionship.getPlayers();
	    initializeRaces(loadedChampionship.getCircuits(),loadedChampionship.getPlayers());
	    // Restablecer la carrera actual y el circuito activo
	    Race currentRace = Races.get(currentRaceIndex);
	    raceController.setRace(currentRace);
	    championship.setActiveCircuit(currentRace.getCircuit());

	    // Continuar el juego basado en el estado del campeonato
	    if (!championship.isChampionshipOver()) {
	        onRaceStart();
	    } else {
	        endobserver.onEndObserver(); // Si el campeonato ha terminado, ir a la vista final
	    }
	}

	

    private void initializeRaces(List<Circuit> circuits, List<Player> players) {
        int raceNumber = 0;
        for (Circuit circuit : circuits) {
            raceNumber++;
            Race race = new Race(null, raceNumber, new ArrayList<>(players), circuit);
            Races.add(race);
        }
        // Si es necesario, establece los circuitos en el campeonato también
        this.championship.setCircuits(circuits);
    }

}