package extremeF1.Controllers.Example;

import Core.Entities.Events.AceptCarEvent;
import Core.Entities.Events.AceptCarListener;
import Core.Entities.Events.AceptPilotEvent;
import Core.Entities.Events.AceptPilotListener;
import java.util.List;
import Core.Entities.Auto;
import Core.Entities.Piloto;
import Core.Entities.Real;
import Repository.AutoRepository;
import Repository.PilotoRepository;
import extremeF1.Views.SelcetionView;



public class SelectionViewController  {
	   public static void main(String[] args) {
	        // Crear instancias de los repositorios
	        AutoRepository autoRepository = new AutoRepository();
	        PilotoRepository pilotoRepository = new PilotoRepository();
	        Real player = new Real("Gonzalo", 10);

	        // Ruta absoluta al archivo autos.xml
	        autoRepository.loadAutosFromXML();
	        // Ruta absoluta al archivo pilotos.xml
	        pilotoRepository.loadPilotosFromXML();

	        List<Auto> autos = autoRepository.getAutos();
	        List<Piloto> pilotos = pilotoRepository.getPilotos();

	        SelcetionView v1 = new SelcetionView(pilotos,autos);
	        v1.setAceptPilotListener(new AceptPilotListener() {

				@Override
				public void listenerAceptPilotEvent(AceptPilotEvent event) {
					// TODO Aut// TODO Auto-generated method stub
					player.setPilot(event.getPiloto());
					System.out.println(player.getPilot().getnombre());
				}
	        	
	        });
	        
	        v1.setAceptCarListener(new AceptCarListener() {

				@Override
				public void listenerAceptCarEvent(AceptCarEvent event) {
					// TODO Auto-generated method stub
					player.setAuto(event.getAuto());
					System.out.println(player.getAuto().getMarca());
					System.out.println(player.getAuto().getModelo());
					
				}
	        	
	        });
			v1.setVisible(true);

			

	    }

}
