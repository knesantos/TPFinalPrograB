package extremeF1.Controllers.Example;

import Core.Entities.Race;
import Core.Entities.Championship;
import Core.Entities.Circuit;
import Core.Entities.Player;
import extremeF1.Controllers.Example.ReportsViewsController.PostRaceViewController;
import extremeF1.Controllers.Example.ReportsViewsController.PreRaceViewController;
import extremeF1.Views.PostRaceView;
import extremeF1.Views.PreRaceView;

import java.util.ArrayList;
import java.util.List;

public class RaceViewController {
    private Race Race;
    private Championship championship;
    private PreRaceViewController preRaceController;
    private PostRaceViewController postRaceController;

    private List<Runnable> raceEndObservers = new ArrayList<>();

    public void addRaceEndObserver(Runnable observer) {
        raceEndObservers.add(observer);
    }
    
    public  RaceViewController(Race race,Championship championship){
    	this.Race=race;
    	this.championship=championship;
    }
    public  RaceViewController(){
    }
    
    public void startRace(List<Player> Players, Circuit Circuit) {
        Race = new Race(null, 0, Players, Circuit);
        
        
        
        for(Player player:Players )
        	player.setActiveRace(Race);
        preRaceController = new PreRaceViewController(Race);
        preRaceController.addObserver(() -> {
            runRace();
        });
    }

    public void runRace() {
        System.out.println("Se corre la Race");
        Race.simulateRace();
        postRaceController = new PostRaceViewController(Race,championship);
        postRaceController.addObserver(() -> {
            for (Runnable observer : raceEndObservers) {
                observer.run();
            }
        });
    }

    public Race getRace() {
        return Race;
    }



	public Championship getChampionship() {
		return championship;
	}



	public void setChampionship(Championship championship) {
		this.championship = championship;
	}
}