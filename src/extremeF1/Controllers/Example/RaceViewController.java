package extremeF1.Controllers.Example;

import Core.Entities.Carrera;
import Core.Entities.Circuito;
import Core.Entities.Jugador;
import java.util.List;

public class RaceViewController {
	private Carrera carrera;
    public void startRace(List<Jugador> jugadores, Circuito circuito) {
        carrera = new Carrera(null, 0, jugadores, circuito);
        System.out.println("Se corre la carrera");
        carrera.simularCarrera();
    }
    public Carrera getRace(){
    	return carrera;
    	
    }
}