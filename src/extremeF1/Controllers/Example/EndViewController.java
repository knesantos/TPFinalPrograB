package extremeF1.Controllers.Example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Core.Entities.Championship;
import Core.Entities.Player;

import extremeF1.Controllers.Example.StartViewController.StartObserver;
import extremeF1.Views.EndView;
import extremeF1.Views.PrincipalView;

public class EndViewController implements java.io.Serializable{
	private EndView endView;
	private PrincipalView gameWindow;
	private Championship championship;
	
	public EndViewController(PrincipalView gameWindow,Championship championship) {
		this.gameWindow = gameWindow;
		this.championship = championship;
	}
	
	public interface NextObserver{
		void onNextObserver();
	}
	
	private NextObserver  nextobserver;
	
	public void addObserverNext(NextObserver nextobserver) {
		this.nextobserver = nextobserver;
	}
	
	
	
	
	public void initalEndView() {
		endView = new EndView(championship);
		gameWindow.addPanel(endView, "EndView");
		gameWindow.showPanel("EndView");
		
		endView.setNextListener(event ->{
			nextobserver.onNextObserver();
		});
		
		
		
		
		
	}
}
