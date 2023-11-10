package extremeF1.Controllers.Example;

import extremeF1.Views.PreRaceView;
import extremeF1.Views.PrincipalView;

import java.util.ArrayList;
import java.util.List;

import Core.Entities.Hard;
import Core.Entities.Medium;
import Core.Entities.Player;
import Core.Entities.Race;
import Core.Entities.Real;

import Core.Entities.Soft;
import Core.Entities.Tire;
import Core.Entities.Wet;
import extremeF1.Views.PitsView;


public class PitsViewController implements java.io.Serializable{
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
		  
		  
		  
		  PitsView.setChangeTireListener((event)->{
			  if (observer!= null) {
				  observer.onPitsView();
			  }
		  });
		  
		  
		  
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
	        Tire tire = null; 
	        switch (tireType) {
	            case "Medium":
	                tire = new Medium();
	                break;
	            case "Hard":
	                tire = new Hard();
	                break;
	            case "Soft":
	                tire = new Soft();
	                break;
	            case "Wet":
	                tire = new Wet();
	                break;
	            default:
	                System.out.println("Tipo de neumático no válido");
	                return;
	        }

	        race.getRealPlayer().getCar().setTire(tire); 
	    }

	    public void loadFuel(int fuelAmount) {
	        int actualFuelAmount = 0; // Inicializa la variable para la cantidad real de combustible

	        switch (fuelAmount) {
	            case 1:
	                actualFuelAmount = 25;
	                break;
	            case 2:
	                actualFuelAmount = 50;
	                break;
	            case 3:
	                actualFuelAmount = 75;
	                break;
	            case 4:
	                actualFuelAmount = 100;
	                break;
	            default:
	                System.out.println("Cantidad de combustible no válida");
	                return;
	        }
	        race.getRealPlayer().getCar().setFuel(actualFuelAmount); 
	    }

    public void repairEngine() {
    	race.getRealPlayer().getCar().setHealth(100); 
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
