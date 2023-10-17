package extremeF1.Controllers.Example;

import Core.Entities.Race;
import Core.Entities.Championship;
import Core.Entities.Player;
import extremeF1.Controllers.Example.ReportsViewsController.PreRaceViewController;
import extremeF1.Views.PreRaceView;
import extremeF1.Views.PrincipalView;
import extremeF1.Controllers.Example.ReportsViewsController.PostRaceViewController;

import java.util.List;

public class ChampionshipController implements ReportsViewsController.PreRaceObserver, ReportsViewsController.PostRaceObserver {
    private List<Race> Races;
    private List<Player> players;
    private int currentRaceIndex = 0;
    private Championship championship;
    private RaceViewController raceController;
    private PrincipalView gameWindow;

    public ChampionshipController(List<Race> Races, List<Player> players, PrincipalView gameWindow) {
        this.Races = Races;
        this.players = players;
        this.championship = new Championship(Races.size(), players);
        this.gameWindow = gameWindow;

        // Inicializa RaceViewController aquí y pasa el campeonato como argumento
        this.raceController = new RaceViewController(gameWindow, championship);
        raceController.addRaceEndObserver(() -> onRaceEnd());
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
            } else {
                currentRaceIndex++;
                startNextRace();
            }
        });
        gameWindow.showPanel("PostRace");  // Mostrar la vista PostRace
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
}