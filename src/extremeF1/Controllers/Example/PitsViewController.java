package extremeF1.Controllers.Example;

import Core.Entities.Race;

import javax.swing.JPanel;

import Core.Entities.Circuit;
import Core.Entities.Tire;
import Core.Entities.Real;
import extremeF1.Views.PitsView;

public class PitsViewController {
	private Race race;
	private Real player;
	private PitsView v1;
	public PitsViewController (Race Race,Real Player) {
		 race = Race;
		 player = Player;
		v1 = new PitsView(race, player);
		v1.setVisible(true);
		v1.setLoadFuelListener(event -> {
			player.getCar().setFuel(event.LoadFuel());
		});
		v1.setChangeTireListener(event ->{
			player.getCar().setTire(event.ChangeTire());
		});
	}
	public JPanel getPanel() {
		return v1;
	}

}
