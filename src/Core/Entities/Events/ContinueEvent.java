package Core.Entities.Events;

import Core.Entities.Car;

public class ContinueEvent {
	private Car Car;
	
	public ContinueEvent(Car Car) {
		this.Car = Car;
	}
	
	public Car getCar() {
		return  Car;
	}
}
