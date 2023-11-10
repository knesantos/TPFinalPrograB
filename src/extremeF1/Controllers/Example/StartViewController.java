package extremeF1.Controllers.Example;


import Core.Entities.Real;

import extremeF1.Views.PrincipalView;
import extremeF1.Views.StartView;

public class StartViewController implements java.io.Serializable{
	private StartView startview;
	private PrincipalView gameWindow;
	private Real player;
	private MainController main;
		
	public StartViewController(PrincipalView gameWindow,MainController main) {
			this.gameWindow = gameWindow;
			this.main=main;
		}
	
	
		public interface StartObserver{
			void onStartObserver();
		}
		
		 public interface LoadObserver {
		        void onLoadObserver();
		    }
		
		private StartObserver startobserver;
		 private LoadObserver loadObserver;
		
		public void addObserverStart(StartObserver startobserver) {
			this.startobserver = startobserver;
		}
		
		

		    public void addObserverLoad(LoadObserver loadobserver) {
		        this.loadObserver = loadobserver;
		    }
		
		public interface CloseObserver{
			void onCloseObserver();
		}
		private CloseObserver closeobserver;
		public void addObserverClose(CloseObserver closeobserver) {
			this.closeobserver = closeobserver;
		}
		
		
		
		
		
		
		
	public void initialSartView() {
		startview = new StartView(main);
		gameWindow.addPanel(startview, "startview");
		gameWindow.showPanel("startview");
		
		 startview.setStartGameListener(event -> {
	            player = startview.getPlayer();
	            if (startobserver != null) {
	                startobserver.onStartObserver();
	            }
	        });

	        startview.setCloseGameListener(event -> {
	            if (closeobserver != null) {
	                closeobserver.onCloseObserver();
	            }
	        });

	        // Aquí necesitas un setter para el botón de carga
	        startview.setLoadListener(event -> {
	            if (loadObserver != null) {
	            	loadObserver.onLoadObserver();
	            }
	        });
	    }
		
		
		
		
	
	public Real getPlayer() {
		return player;
	}

	public void setPlayer(Real player) {
		this.player = player;
		
	}
	
}
