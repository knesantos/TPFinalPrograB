package extremeF1.Controllers.Example;

import Core.Entities.Carrera;
import Core.Entities.Circuito;
import Core.Entities.Jugador;
import extremeF1.Views.PostRaceView;
import extremeF1.Views.PreRaceView;

import java.util.List;

public class RaceViewController {
	private Carrera carrera;
    public void startRace(List<Jugador> jugadores, Circuito circuito) {
        carrera = new Carrera(null, 0, jugadores, circuito);
        PreRaceView preRaceView = new PreRaceView(carrera);
        preRaceView.setVisible(true);
        Carrera carrera = new Carrera(null, 0, jugadores, circuito);
        for (Jugador jugador : jugadores) {
            jugador.setCarreraActiva(carrera);
        }
        System.out.println("Se corre la carrera");
        carrera.simularCarrera();
        PostRaceView postRaceView = new PostRaceView(carrera);
        postRaceView.setVisible(true);
    }
    public Carrera getRace(){
    	return carrera;
    	
    }
}