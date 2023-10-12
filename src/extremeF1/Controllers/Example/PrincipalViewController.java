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
	private PitsViewController pitsview;
	public PrincipalViewController(Race race, Real player, CarRepository carRepository, DriverRepository driverRepository) {
		v1 = new PrincipalView();
		v1.setVisible(true); 
		selectionview = new SelectionViewController();
		v1.setPanel(selectionview.initSelectionScreen(player, carRepository, driverRepository));
		
		 selectionview.addObserver(new SelectionViewController.SelectionObserver() {
	            @Override
	            public void onSelectionComplete() {
	            	pitsview = new PitsViewController(race, player);
	            	v1.removePanel();
	            	v1.setPanel(pitsview.getPanel());
	            	
	            }
	        });
		
	}

	public List<Player> getListPlayer(){
		
		return selectionview.getListPlayer();
	}

}
