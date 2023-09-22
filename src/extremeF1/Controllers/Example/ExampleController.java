package extremeF1.Controllers.Example;

import java.util.List;

import Core.Entities.Auto;
import Core.Entities.Carrera;
import Core.Entities.Circuito;
import Core.Entities.Pais;
import Core.Entities.Piloto;
import Repository.AutoRepository;
import Repository.CircuitoRepository;
import Repository.PaisRepository;
import Repository.PilotoRepository;
import extremeF1.Views.StartView;

public class ExampleController {

    public static void main(String[] args) {
        // Crear instancias de los repositorios
        AutoRepository autoRepository = new AutoRepository();
        CircuitoRepository circuitoRepository = new CircuitoRepository();
        PaisRepository paisRepository = new PaisRepository();
        PilotoRepository pilotoRepository = new PilotoRepository();
        
        

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
        
        Carrera carrera = new Carrera(null, 0,autos,circuitos.get(1));
        System.out.println("Se corre la carrer");
        carrera.simularCarrera();
        
        // Mostrar las listas por consola
        System.out.println("Lista de Autos:");
        for (Auto auto : autos) {
            System.out.println(auto.toString());
        }

        System.out.println("Lista de Circuitos:");
        for (Circuito circuito : circuitos) {
            System.out.println(circuito.toString());
        }

        System.out.println("Lista de Paises:");
        for (Pais pais : paises) {
            System.out.println(pais.toString());
        }

        System.out.println("Lista de Pilotos:");
        for (Piloto piloto : pilotos) {
            System.out.println(piloto.toString());
        }
        
        
    }
}