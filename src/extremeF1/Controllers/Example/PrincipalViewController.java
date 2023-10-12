package extremeF1.Controllers.Example;

import java.util.List;

import javax.swing.JPanel;

import Core.Entities.Player;
import Core.Entities.Race;
import Core.Entities.Real;
import Repository.CarRepository;
import Repository.DriverRepository;
import extremeF1.Views.PrincipalView;
public class PrincipalViewController {
	private PrincipalView v1;
	private SelectionViewController selectionview;
	public PrincipalViewController(Race race, Real player, CarRepository carRepository, DriverRepository driverRepository) {
		v1 = new PrincipalView();
		v1.setVisible(true); 
		selectionview = new SelectionViewController();
		setPanel(selectionview.initSelectionScreen(player, carRepository, driverRepository));
		selectionview.getListPlayer();
		
		
		
		//PitsViewController pitview = new PitsViewController(race,player);
		//	setPanel(pitview.getPanel());
	}
	public void setPanel(JPanel panel) {
		v1.setPanel(panel);
	}
	public void removePanel(JPanel panel) {
		v1.removePanel(panel);
	}
	public List<Player> getListPlayer(){
		
		return selectionview.getListPlayer();
	}

}
