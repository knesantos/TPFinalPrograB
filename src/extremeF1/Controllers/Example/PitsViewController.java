package extremeF1.Controllers.Example;

import extremeF1.Views.PreRaceView;
import extremeF1.Views.PrincipalView;
import Core.Entities.Player;
import Core.Entities.Race;
import Core.Entities.Real;
import extremeF1.Views.PitsView;


public class PitsViewController {
	private PrincipalView gameWindow;
	private PitsView PitsView;
	private Race race;
	
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
		  PitsView = new PitsView(race, player);
		  gameWindow.addPanel(PitsView, "pitsView");
		  gameWindow.showPanel("pitsView");
	}
	
}
