package extremeF1.Controllers.Example;

import Core.Entities.Race;
import Core.Entities.Championship;
import Core.Entities.Circuit;
import Core.Entities.Player;
import extremeF1.Controllers.Example.ReportsViewsController.PostRaceViewController;
import extremeF1.Controllers.Example.ReportsViewsController.PreRaceViewController;
import extremeF1.Views.PostRaceView;
import extremeF1.Views.PreRaceView;
import extremeF1.Views.PrincipalView;
import extremeF1.Views.RaceView;

import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import javax.swing.SwingUtilities;

public class RaceViewController {
    private Race Race;
    private Championship championship;
    private PreRaceViewController preRaceController;
    private PostRaceViewController postRaceController;
    private RaceView raceView; 
    private List<Runnable> raceEndObservers = new ArrayList<>();
    private PrincipalView gameWindow;
    
    Timer timer = new Timer(900, e -> SwingUtilities.invokeLater(() -> updateRaceView()));  // Actualiza cada segundo

    public void updateRaceView() {
        // Actualiza la información en raceView
        raceView.updateInfo(Race);
    }
    
    
    public void addRaceEndObserver(Runnable observer) {
        raceEndObservers.add(observer);
    }
    
    public RaceViewController(PrincipalView gameWindow, Championship championship) {
        this.gameWindow = gameWindow;
        this.championship = championship;
    }
    
    public  RaceViewController(Race race,Championship championship,PrincipalView gameWindow){
    	this.Race=race;
    	this.championship=championship;
    	this.gameWindow =gameWindow;
    }
    public  RaceViewController(){
    }
    
    public void startRace(List<Player> Players, Circuit Circuit) {
        Race = new Race(null, 0, Players, Circuit);
        
        for(Player player:Players )
        	player.setActiveRace(Race);
        preRaceController = new PreRaceViewController(Race,gameWindow);
        preRaceController.addObserver(() -> {
            initRaceView();  // Inicializar la vista de la carrera
            SwingUtilities.invokeLater(() -> runRace());  // Ejecutar la carrera en un nuevo hilo
        });
    }

    private void initRaceView() {
        raceView = new RaceView(Race);  
        gameWindow.addPanel(raceView, "RaceView");
        gameWindow.showPanel("RaceView");
    }
    
    public void runRace() {
        System.out.println("Se corre la carrera");
        timer.start();
        new Thread(() -> {
            Race.simulateRace();  // Simula la carrera en un hilo separado
            SwingUtilities.invokeLater(() -> {
                for (Runnable observer : raceEndObservers) {
                    observer.run();
                }
            });
        }).start();
    }


    public Race getRace() {
        return Race;
    }
    public void setRace(Race race) {
        this.Race= race;
    }



	public Championship getChampionship() {
		return championship;
	}



	public void setChampionship(Championship championship) {
		this.championship = championship;
	}
}