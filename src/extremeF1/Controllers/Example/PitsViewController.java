package extremeF1.Controllers.Example;

import Core.Entities.Race;
import Core.Entities.Circuit;
import Core.Entities.Tire;
import Core.Entities.Real;
import extremeF1.Views.PitsView;

public class PitsViewController {
	private Race race;
	private Real player;
	public void PitsViewController (Race Race,Real Player) {
		 race = Race;
		 player = Player;
		PitsView v1 = new PitsView(race, player);
		v1.setVisible(true);
		v1.setLoadFuelListener(event -> {
			player.getCar().setFuel(event.LoadFuel());
		});
		
	}

}
