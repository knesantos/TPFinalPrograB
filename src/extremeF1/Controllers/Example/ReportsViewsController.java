package extremeF1.Controllers.Example;

import Core.Entities.Race;
import Core.Entities.Championship;
import Core.Entities.Player;
import extremeF1.Views.PostRaceView;
import extremeF1.Views.PreRaceView;
import extremeF1.Views.PrincipalView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReportsViewsController {
	private static PrincipalView ReportsGameWindow;
	
	public ReportsViewsController(PrincipalView ReportsGameWindow) {
		ReportsViewsController.ReportsGameWindow =  ReportsGameWindow;
	}
	
	
    public interface PreRaceObserver {
        void onRaceStart();
    }

    public interface PostRaceObserver {
        void onRaceEnd();
    }

    public static class PreRaceViewController {
    	private PrincipalView gameWindow = ReportsGameWindow;
        private Race Race;
        private PreRaceView preRaceView;
        private PreRaceObserver observer;

        public void addObserver(PreRaceObserver observer) {
            this.observer = observer;
        }

        public PreRaceViewController(Race Race,PrincipalView gameWindow) {
            this.Race = Race;
            this.gameWindow=gameWindow;
            initPreRaceView();
        }

        private void initPreRaceView() {
            preRaceView = new PreRaceView(Race);
            preRaceView.getBtnContinuar().addActionListener(e -> handleContinueButton());
            gameWindow.addPanel(preRaceView, "PreRace");
            gameWindow.showPanel("PreRace");
        }

        private void handleContinueButton() {
            if (observer != null) {
                observer.onRaceStart();
            }
        }
    }

    public static class PostRaceViewController {
    	private PrincipalView gameWindow= ReportsGameWindow;;
        private Race Race;
        private Championship championship;
        private PostRaceView postRaceView;
        private PostRaceObserver observer;

        public void addObserver(PostRaceObserver observer) {
            this.observer = observer;
        }

        public PostRaceViewController(Race Race,Championship championship,PrincipalView gameWindow) {
            this.Race = Race;
            this.gameWindow=gameWindow;
            initPostRaceView();
        }

        private void initPostRaceView() {
            postRaceView = new PostRaceView(Race,championship);
            postRaceView.getBtnContinuar().addActionListener(e -> handleContinueButton());
            gameWindow.addPanel(postRaceView, "PostRace");
            gameWindow.showPanel("PostRace");
        }

        private void handleContinueButton() {
            if (observer != null) {
                observer.onRaceEnd();
            }
        }
    }
}