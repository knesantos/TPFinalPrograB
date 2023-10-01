package extremeF1.Controllers.Example;

import Core.Entities.Race;
import Core.Entities.Championship;
import Core.Entities.Player;
import extremeF1.Views.PostRaceView;
import extremeF1.Views.PreRaceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReportsViewsController {

    public interface PreRaceObserver {
        void onRaceStart();
    }

    public interface PostRaceObserver {
        void onRaceEnd();
    }

    public static class PreRaceViewController {

        private Race Race;
        private PreRaceView preRaceView;
        private PreRaceObserver observer;

        public void addObserver(PreRaceObserver observer) {
            this.observer = observer;
        }

        public PreRaceViewController(Race Race) {
            this.Race = Race;
            initPreRaceView();
        }

        private void initPreRaceView() {
            preRaceView = new PreRaceView(Race);
            preRaceView.getBtnContinuar().addActionListener(e -> handleContinueButton());
            preRaceView.setVisible(true);
        }

        private void handleContinueButton() {
            if (observer != null) {
                observer.onRaceStart();
            }
            preRaceView.dispose();
        }
    }

    public static class PostRaceViewController {

        private Race Race;
        private Championship championship;
        private PostRaceView postRaceView;
        private PostRaceObserver observer;

        public void addObserver(PostRaceObserver observer) {
            this.observer = observer;
        }

        public PostRaceViewController(Race Race,Championship championship) {
            this.Race = Race;
            initPostRaceView();
        }

        private void initPostRaceView() {
            postRaceView = new PostRaceView(Race,championship);
            postRaceView.getBtnContinuar().addActionListener(e -> handleContinueButton());
            postRaceView.setVisible(true);
        }

        private void handleContinueButton() {
            if (observer != null) {
                observer.onRaceEnd();
            }
            postRaceView.dispose();
        }
    }
}