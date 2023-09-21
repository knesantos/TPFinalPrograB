package extremeF1.Controllers.Example;


import java.util.List;
import Core.Entities.Auto;
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



}