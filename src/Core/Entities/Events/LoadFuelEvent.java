package Core.Entities.Events;

import Core.Entities.Car;

public class LoadFuelEvent {
	private Car car;
	private double x;
	public LoadFuelEvent(Car Car,double d) {
		car = Car;
		x = d;
	}
	 public double LoadFuel() {
		return (car.getFuel() + x);
	 }
	
	
}
