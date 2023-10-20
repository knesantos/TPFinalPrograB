package extremeF1.Controllers.Example;


import Core.Entities.Real;
import extremeF1.Views.PrincipalView;
import extremeF1.Views.StartView;

public class StartViewController {
	private StartView startview;
	private PrincipalView gameWindow;
	private Real player;
	
		
	public StartViewController(PrincipalView gameWindow) {
			this.gameWindow = gameWindow;
		}
	
	
		public interface StartObserver{
			void onStartObserver();
		}
		
		private StartObserver startobserver;
		
		public void addObserverStart(StartObserver startobserver) {
			this.startobserver = startobserver;
		}
		
		public interface CloseObserver{
			void onCloseObserver();
		}
		private CloseObserver closeobserver;
		public void addObserverClose(CloseObserver closeobserver) {
			this.closeobserver = closeobserver;
		}
		
		
		
		
		
		
		
	public void initialSartView() {
		startview = new StartView();
		gameWindow.addPanel(startview, "startview");
		gameWindow.showPanel("startview");
		
		startview.setStartGameListener(event ->{
			player = startview.getPlayer();
			startobserver.onStartObserver();
		});
		
		startview.setCloseGameListener(event ->{
			closeobserver.onCloseObserver();
		});
		
		
		
		
	}
	public Real getPlayer() {
		return player;
	}
	
}
