package extremeF1.Controllers.Example;

import Core.Entities.Race;

import javax.swing.JPanel;

import Core.Entities.Circuit;
import Core.Entities.Tire;
import Core.Entities.Real;
import extremeF1.Views.PitsView;
import extremeF1.Views.PrincipalView;

public class PitsViewController {
	private Race race;
	private Real player;
	private PitsView v1;
	private PrincipalView gameWindow;
	
	public PitsViewController(Race Race, Real Player, PrincipalView gameWindow) {
	    this.race = Race;
	    this.player = Player;
	    this.gameWindow = gameWindow;
	    initPitsView();
	}
	
	public JPanel getPanel() {
		return v1;
	}
	
	private void initPitsView() {
	    v1 = new PitsView(race, player);
	    v1.setVisible(true);
	    
	    v1.setLoadFuelListener(event -> {
			player.getCar().setFuel(event.LoadFuel());
		});
	    
		v1.setChangeTireListener(event ->{
			player.getCar().setTire(event.ChangeTire());
		});
		
	    gameWindow.addPanel(v1, "Pits");
	    gameWindow.showPanel("Pits");
	}
}

