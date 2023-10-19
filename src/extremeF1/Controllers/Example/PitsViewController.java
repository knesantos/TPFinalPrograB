package extremeF1.Controllers.Example;

import extremeF1.Views.PreRaceView;
import extremeF1.Views.PrincipalView;

import java.util.ArrayList;
import java.util.List;

import Core.Entities.Player;
import Core.Entities.Race;
import Core.Entities.Real;
import extremeF1.Views.PitsView;


public class PitsViewController {
	private PrincipalView gameWindow;
	private PitsView PitsView;
	private Race race;
	 private List<Runnable> resumeRaceObservers = new ArrayList<>();
	
	  public interface PitsViewObserver {
	        void onPitsView();
	    }
	
	private PitsViewObserver observer;
	
	public void addObserver(PitsViewObserver observer) {
        this.observer = observer;
    }
	
	public PitsViewController(Race Race,Player player, PrincipalView gamewindow) {
		  this.gameWindow = gamewindow;
		  this.race = Race;
		  PitsView = new PitsView(race, player,this);
		  gameWindow.addPanel(PitsView, "pitsView");
		  gameWindow.showPanel("pitsView");
	}
	
	 public void addResumeRaceObserver(Runnable observer) {
	        resumeRaceObservers.add(observer);
	    }

	    public void notifyResumeRaceObservers() {
	        for (Runnable observer : resumeRaceObservers) {
	            observer.run();
	        }
	    }
	
	public void changeTires(String tireType) {
        // Aquí puedes implementar la lógica para cambiar los neumáticos
        // Por ejemplo, puedes emitir un evento que el modelo escuchará y actuará en consecuencia
        //observer.onTireChange(tireType);
    }

    public void loadFuel(int fuelAmount) {
        // Similarmente, implementa la lógica para cargar combustible
       // observer.onFuelLoad(fuelAmount);
    }

    public void repairEngine() {
        // Implementa la lógica para reparar el motor
        //observer.onEngineRepair();
    }
	
    public void finishPitStop() {
        if (race.getPitStopLatch() != null) {
            race.getPitStopLatch().countDown();  // Libera el CountDownLatch
        }
    }
    
    public void switchToRaceView() {
        gameWindow.showPanel("RaceView");
    }
    
    public void updateView() {
        PitsView.updateFuelLabel((int)race.getRealPlayer().getCar().getFuel());
        PitsView.updateTireWearLabel((int)race.getRealPlayer().getCar().getTire().getWear());
      
    }
	
}
