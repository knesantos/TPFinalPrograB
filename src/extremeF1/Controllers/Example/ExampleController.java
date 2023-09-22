package extremeF1.Controllers.Example;


import java.util.ArrayList;
import java.util.List;
import Core.Entities.*;


import java.util.List;
import Core.Entities.Auto;
import Core.Entities.Carrera;
import Core.Entities.Circuito;
import Core.Entities.Pais;
import Core.Entities.Piloto;
import Core.Entities.Real;

import Repository.AutoRepository;
import Repository.CircuitoRepository;
import Repository.PaisRepository;
import Repository.PilotoRepository;



public class ExampleController{
    public static void main(String[] args) {
        // Crear instancias de los repositorios
        AutoRepository autoRepository = new AutoRepository();
        CircuitoRepository circuitoRepository = new CircuitoRepository();
        PaisRepository paisRepository = new PaisRepository();
        PilotoRepository pilotoRepository = new PilotoRepository();
        

        Real player = new Real("Gonzalo",23);


        // Ruta absoluta al archivo autos.xml
        autoRepository.loadAutosFromXML();

        // Ruta absoluta al archivo circuitos.xml
        circuitoRepository.loadCircuitosFromXML();

        // Ruta absoluta al archivo paises.xml
        paisRepository.loadPaisesFromXML();

        // Ruta absoluta al archivo pilotos.xml
        pilotoRepository.loadPilotosFromXML();

        List<Auto> autos = autoRepository.getAutos();
        List<Circuito> circuitos = circuitoRepository.getCircuitos();
        List<Pais> paises = paisRepository.getPaises();

        List<Piloto> pilotos = pilotoRepository.getPilotos();	

    }

        List<Piloto> pilotos = pilotoRepository.getPilotos();
        List<Jugador> jugadores = new ArrayList<>();

     
     if(pilotos.size() <= autos.size()) {
         for (int i = 0; i < pilotos.size(); i++) {
             Jugador jugador = new Simulado();
             jugador.setPiloto(pilotos.get(i)); // Asigna un piloto al jugador
             jugador.setAuto(autos.get(i)); // Asigna un auto al jugador
             jugador.setNombre("Jugador " + (i + 1)); // Asigna un nombre al jugador
             jugadores.add(jugador); // Añade el jugador a la lista
         }
     } else {
         System.out.println("No hay suficientes autos para todos los pilotos.");
     }
     
        Carrera carrera = new Carrera(null, 0,jugadores,circuitos.get(1));
        System.out.println("Se corre la carrer");
        carrera.simularCarrera();
        
        // Mostrar las listas por consola
        System.out.println("Lista de Autos:");
        for (Auto auto : autos) {
            System.out.println(auto.toString());
        }
}