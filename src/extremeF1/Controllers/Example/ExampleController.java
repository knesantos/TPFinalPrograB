package extremeF1.Controllers.Example;

import java.util.ArrayList;
import java.util.List;

import Core.Entities.Events.AceptPilotEvent;
import Core.Entities.Events.AceptPilotListener;
import Core.Entities.Auto;
import Core.Entities.Circuito;
import Core.Entities.Jugador;
import Core.Entities.Pais;
import Core.Entities.Piloto;
import Core.Entities.Real;
import Repository.AutoRepository;
import Repository.CircuitoRepository;
import Repository.PaisRepository;
import Repository.PilotoRepository;
import extremeF1.Views.SelcetionView;
import extremeF1.Views.StartView;

public class ExampleController implements AceptPilotListener{
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

        SelcetionView v1 = new SelcetionView(pilotos);
		v1.setVisible(true);

		

    }

	@Override
	public void listenerAceptPilotEvent(AceptPilotEvent event) {
		// TODO Auto-generated method stub
		System.out.println(event.getIndex());
		
	}

}