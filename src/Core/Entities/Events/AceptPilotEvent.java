package Core.Entities.Events;

import Core.Entities.Driver;

public class AceptPilotEvent {
	private Driver Driver;
	
	public AceptPilotEvent(Driver Driver) {
		this.Driver = Driver;
	}
	
	public Driver getDriver() {
		return  Driver;
	}
}
