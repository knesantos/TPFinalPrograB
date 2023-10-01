package extremeF1.Controllers.Example;


import Core.Entities.Carrera;
import Core.Entities.Jugador;
import extremeF1.Views.PostRaceView;
import extremeF1.Views.PreRaceView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReportsViewsController {

	
	

	public class PreRaceViewController {

	    private Carrera carrera;
	    private PreRaceView preRaceView;

	    public interface PreRaceObserver {
	        void onRaceStart();
	    }

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

	        // Manejar el evento del botón "Continuar"
	        preRaceView.getBtnContinuar().addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                handleContinueButton();
	            }
	        });

	        preRaceView.setVisible(true);
	    }

	    private void handleContinueButton() {
	        // Aquí puedes poner el código para continuar al inicio de la carrera
	        if (observer != null) {
	            observer.onRaceStart();
	        }
	        preRaceView.dispose();
	    }
	}
	
	
	public class PostRaceViewController {

        private Carrera carrera;
        private PostRaceView postRaceView;

        public interface PostRaceObserver {
            void onRaceEnd();
        }

        private PostRaceObserver observer;

        public void addObserver(PostRaceObserver observer) {
            this.observer = observer;
        }

        public PostRaceViewController(Carrera carrera) {
            this.carrera = carrera;
            initPostRaceView();
        }

        private void initPostRaceView() {
            postRaceView = new PostRaceView(carrera);

            // Manejar el evento del botón "Continuar"
            postRaceView.getBtnContinuar().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleContinueButton();
                }
            });

            postRaceView.setVisible(true);
        }
        private void handleContinueButton() {
            // Aquí puedes poner el código para continuar después de la carrera
            if (observer != null) {
                observer.onRaceEnd();
            }
            postRaceView.dispose();
        }
    }
}
	
