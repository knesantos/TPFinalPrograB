package extremeF1.Controllers.Example;

import Core.Entities.Carrera;
import Core.Entities.Championship;
import Core.Entities.Jugador;
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

        private Carrera carrera;
        private PreRaceView preRaceView;
        private PreRaceObserver observer;

        public void addObserver(PreRaceObserver observer) {
            this.observer = observer;
        }

        public PreRaceViewController(Carrera carrera) {
            this.carrera = carrera;
            initPreRaceView();
        }

        private void initPreRaceView() {
            preRaceView = new PreRaceView(carrera);
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

        private Carrera carrera;
        private Championship championship;
        private PostRaceView postRaceView;
        private PostRaceObserver observer;

        public void addObserver(PostRaceObserver observer) {
            this.observer = observer;
        }

        public PostRaceViewController(Carrera carrera,Championship championship) {
            this.carrera = carrera;
            initPostRaceView();
        }

        private void initPostRaceView() {
            postRaceView = new PostRaceView(carrera,championship);
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