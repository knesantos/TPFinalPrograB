package Core.Entities.Events;

import Core.Entities.Car;

public class AceptCarEvent {
	private Car car;
	
	public AceptCarEvent(Car car) {
		this.car = car;
	}
	
	public Car getCar() {
		return  car;
	}
}
