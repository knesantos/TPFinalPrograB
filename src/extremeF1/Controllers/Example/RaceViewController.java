package extremeF1.Controllers.Example;

import Core.Entities.Carrera;
import Core.Entities.Championship;
import Core.Entities.Circuito;
import Core.Entities.Jugador;
import extremeF1.Controllers.Example.ReportsViewsController.PostRaceViewController;
import extremeF1.Controllers.Example.ReportsViewsController.PreRaceViewController;
import extremeF1.Views.PostRaceView;
import extremeF1.Views.PreRaceView;

import java.util.ArrayList;
import java.util.List;

public class RaceViewController {
    private Carrera carrera;
    private Championship championship;
    private PreRaceViewController preRaceController;
    private PostRaceViewController postRaceController;

    private List<Runnable> raceEndObservers = new ArrayList<>();

    public void addRaceEndObserver(Runnable observer) {
        raceEndObservers.add(observer);
    }
    
    public  RaceViewController(Carrera race,Championship championship){
    	this.carrera=race;
    	this.championship=championship;
    }
    public  RaceViewController(){
    }
    
    public void startRace(List<Jugador> jugadores, Circuito circuito) {
        carrera = new Carrera(null, 0, jugadores, circuito);
        
        
        
        for(Jugador player:jugadores )
        	player.setCarreraActiva(carrera);
        preRaceController = new PreRaceViewController(carrera);
        preRaceController.addObserver(() -> {
            runRace();
        });
    }

    public void runRace() {
        System.out.println("Se corre la carrera");
        carrera.simularCarrera();
        
        
        
        postRaceController = new PostRaceViewController(carrera,championship);
        postRaceController.addObserver(() -> {
            for (Runnable observer : raceEndObservers) {
                observer.run();
            }
        });
    }

    public Carrera getRace() {
        return carrera;
    }



	public Championship getChampionship() {
		return championship;
	}



	public void setChampionship(Championship championship) {
		this.championship = championship;
	}
}