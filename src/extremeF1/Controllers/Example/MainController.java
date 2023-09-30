package extremeF1.Controllers.Example;

import java.util.ArrayList;
import java.util.List;
import Core.Entities.*;
import Repository.AutoRepository;
import Repository.CircuitoRepository;
import Repository.PaisRepository;
import Repository.PilotoRepository;

public class MainController {

    // Variable de instancia para saber si la carrera ha terminado
    private boolean raceFinished = false;
    private List<Jugador> jugadores = new ArrayList<>();

    public static void main(String[] args) {
        new MainController().run();
    }

    public void run() {
   
        // Crear instancias de los repositorios
        AutoRepository autoRepository = new AutoRepository();
        CircuitoRepository circuitoRepository = new CircuitoRepository();
        PaisRepository paisRepository = new PaisRepository();
        PilotoRepository pilotoRepository = new PilotoRepository();

        // Cargar datos desde XML
        autoRepository.loadAutosFromXML();
        circuitoRepository.loadCircuitosFromXML();
        paisRepository.loadPaisesFromXML();
        pilotoRepository.loadPilotosFromXML();

        // Crear jugador Real
        Real player = new Real("Gonzalo", 23);

 
        // Iniciar pantalla de selección
       
        
        SelectionViewController selectionController = new SelectionViewController();
        jugadores = selectionController.initSelectionScreen(player, autoRepository, pilotoRepository);
        System.out.println("Jugadores seleccionados: " + jugadores);
        // Registrar un observador para iniciar la carrera cuando se complete la selección
        selectionController.addObserver(new SelectionViewController.SelectionObserver() {
            @Override
            public void onSelectionComplete() {
                // Aquí puedes disparar la controladora de carrera
            	Circuito circuito = circuitoRepository.getCircuitos().get(0);
                RaceViewController raceController = new RaceViewController();
                System.out.println("Iniciando la carrera con los siguientes jugadores: " + jugadores);
                raceController.startRace(jugadores, circuito);
                PitsViewController pitscontroller = new PitsViewController();
                pitscontroller.PitsViewController(raceController.getRace(),player);
                // Marcar la carrera como terminada
                raceFinished = true;
            }
        });
      
        
    }
}