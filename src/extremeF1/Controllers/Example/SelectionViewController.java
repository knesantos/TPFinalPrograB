package extremeF1.Controllers.Example;

import Core.Entities.Events.AceptCarEvent;
import Core.Entities.Events.AceptCarListener;
import Core.Entities.Events.AceptPilotEvent;
import Core.Entities.Events.AceptPilotListener;
import Core.Entities.Car;
import Core.Entities.Player;
import Core.Entities.Driver;
import Core.Entities.Real;
import Core.Entities.Simulated;
import Repository.CarRepository;
import Repository.DriverRepository;
import extremeF1.Views.SelectionView;

import java.util.ArrayList;
import java.util.List;

public class SelectionViewController {
	
	private boolean isButtonAcceptSelectionPressed = false;
	  private SelectionView v1;
	
	
	public interface SelectionObserver {
        void onSelectionComplete();
    }

    private SelectionObserver observer;

    public void addObserver(SelectionObserver observer) {
        this.observer = observer;
    }
	
	public List<Player> initSelectionScreen(Real player, CarRepository CarRepository, DriverRepository DriverRepository) {
        List<Car> Cars = CarRepository.getCars();
        List<Driver> Drivers = DriverRepository.getDrivers();
        List<Player> Playeres = new ArrayList<>();

        v1 = new SelectionView(Drivers, Cars);
        
        v1.setAceptPilotListener(event -> {
            player.setDriver(event.getDriver());
            System.out.println(player.getDriver().getName());
        });

        v1.setAceptCarListener(event -> {
            player.setCar(event.getCar());
            System.out.println(player.getCar().getBrand());
            System.out.println(player.getCar().getBrand());
        });
        
        v1.setButtonAcceptSelectionListener((event) -> {
            setButtonPressed(true);
            v1.dispose();
        });
        
        v1.setVisible(true);
        
        
        

        // Crear Playeres Simulateds para los Drivers y Cars no seleccionados
        int CarIndex = 0;
        for (int i = 0; i < Drivers.size(); i++) {
            if (Drivers.get(i) != player.getDriver()) {
                Player PlayerSimulated = new Simulated();
                PlayerSimulated.setDriver(Drivers.get(i));
                if (CarIndex < Cars.size()) {
                    PlayerSimulated.setCar(Cars.get(CarIndex));
                    Cars.get(CarIndex).setPlayer(PlayerSimulated);
                    CarIndex++;
                }
                
                PlayerSimulated.setName("Player Simulated " + (i + 1));
                PlayerSimulated.setId(i+1);
                Playeres.add(PlayerSimulated);
            }
        }
        
        Playeres.add(player);
        return Playeres;
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