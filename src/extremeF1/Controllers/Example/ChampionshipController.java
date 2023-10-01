package extremeF1.Controllers.Example;

import Core.Entities.Carrera;
import Core.Entities.Championship;
import Core.Entities.Jugador;
import extremeF1.Controllers.Example.ReportsViewsController.PreRaceViewController;
import extremeF1.Controllers.Example.ReportsViewsController.PostRaceViewController;

import java.util.List;

public class ChampionshipController implements ReportsViewsController.PreRaceObserver, ReportsViewsController.PostRaceObserver {
    private List<Carrera> carreras;
    private List<Jugador> players;
    private int currentRaceIndex = 0;
    private Championship championship;
    private RaceViewController raceController;

    public ChampionshipController(List<Carrera> carreras, List<Jugador> players) {
        this.carreras = carreras;
        this.players = players;
        this.championship = new Championship(carreras.size(), players);
        this.raceController = new RaceViewController();
        raceController.addRaceEndObserver(() -> onRaceEnd());
    }
    
    public void setRaceController(RaceViewController raceController) {
        this.raceController = raceController;
    }
    
    public void onRaceStart() {
        // Iniciar la carrera
    	System.out.println("onRaceStart index: " + currentRaceIndex);
        Carrera currentRace = carreras.get(currentRaceIndex);
        championship.setActiveCircuit(currentRace.getCircuito());
        raceController.startRace(championship.getPlayers(), currentRace.getCircuito());
    }

    @Override
    public void onRaceEnd() {
        // Actualizar puntos y completar la carrera
    	System.out.println("End race index: " + currentRaceIndex);
        championship.raceCompleted();
        championship.updatePointsBasedOnPosition(raceController.getRace().getPlayers());

        if (championship.isChampionshipOver()) {
            System.out.println("Campeonato terminado");
            // Mostrar la vista de resultados finales del campeonato
        } else {
            currentRaceIndex++;
            startNextRace();
        }
    }

    public void startNextRace() {
        if (currentRaceIndex < carreras.size()) {
        	System.out.println("startNextRace index: " + currentRaceIndex);
            Carrera nextRace = carreras.get(currentRaceIndex);
            championship.setActiveCircuit(nextRace.getCircuito()); 
            onRaceStart();
        } else {
            System.out.println("No hay más carreras en el campeonato");
        }
    }
}