package extremeF1.Controllers.Example;

import Core.Entities.Carrera;
import Core.Entities.Circuito;
import Core.Entities.Neumatico;
import Core.Entities.Real;
import extremeF1.Views.PitsView;

public class PitsViewController {
	private Carrera career;
	private Real player;
	public void PitsViewController (Carrera Career,Real Player) {
		 career = Career;
		 player = Player;
		PitsView v1 = new PitsView(career, player);
		v1.setVisible(true);
		
	}

}
