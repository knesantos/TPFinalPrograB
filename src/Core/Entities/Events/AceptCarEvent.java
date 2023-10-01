package Core.Entities.Events;

import Core.Entities.Car;

public class AceptCarEvent {
	private Car car;
	
	public AceptCarEvent(Car auto) {
		this.car = auto;
	}
	
	public Car getCar() {
		return  car;
	}
}
