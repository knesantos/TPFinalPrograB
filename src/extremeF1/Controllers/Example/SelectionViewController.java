package extremeF1.Controllers.Example;

import Core.Entities.Events.AceptCarEvent;
import Core.Entities.Events.AceptCarListener;
import Core.Entities.Events.AceptPilotEvent;
import Core.Entities.Events.AceptPilotListener;
import Core.Entities.Auto;
import Core.Entities.Jugador;
import Core.Entities.Piloto;
import Core.Entities.Real;
import Core.Entities.Simulado;
import Repository.AutoRepository;
import Repository.PilotoRepository;
import extremeF1.Views.SelectionView;

import java.util.ArrayList;
import java.util.List;

public class SelectionViewController {
	
	private boolean isButtonAcceptSelectionPressed = false;
	
	public interface SelectionObserver {
        void onSelectionComplete();
    }

    private SelectionObserver observer;

    public void addObserver(SelectionObserver observer) {
        this.observer = observer;
    }
	
	public List<Jugador> initSelectionScreen(Real player, AutoRepository autoRepository, PilotoRepository pilotoRepository) {
        List<Auto> autos = autoRepository.getAutos();
        List<Piloto> pilotos = pilotoRepository.getPilotos();
        List<Jugador> jugadores = new ArrayList<>();

        SelectionView v1 = new SelectionView(pilotos, autos);
        
        v1.setAceptPilotListener(event -> {
            player.setPilot(event.getPiloto());
            System.out.println(player.getPilot().getnombre());
        });

        v1.setAceptCarListener(event -> {
            player.setAuto(event.getAuto());
            System.out.println(player.getAuto().getMarca());
            System.out.println(player.getAuto().getModelo());
        });
        
        v1.setButtonAcceptSelectionListener((event) -> {
            setButtonPressed(true);
        });
        
        v1.setVisible(true);
        
        
        

        // Crear jugadores simulados para los pilotos y autos no seleccionados
        int autoIndex = 0;
        for (int i = 0; i < pilotos.size(); i++) {
            if (pilotos.get(i) != player.getPilot()) {
                Jugador jugadorSimulado = new Simulado();
                jugadorSimulado.setPiloto(pilotos.get(i));
                if (autoIndex < autos.size()) {
                    jugadorSimulado.setAuto(autos.get(autoIndex));
                    autoIndex++;
                }
                
                jugadorSimulado.setNombre("Jugador Simulado " + (i + 1));
                jugadores.add(jugadorSimulado);
            }
        }
        
        jugadores.add(player);
        return jugadores;
    }
	
	
	private void checkSelectionComplete() {
        if (isButtonAcceptSelectionPressed) {
            if (observer != null) {
            	
                observer.onSelectionComplete();
            }
        }
    }
	public void setButtonPressed(boolean isPressed) {
	    this.isButtonAcceptSelectionPressed = isPressed;
	    checkSelectionComplete();  
	    }
}