package Core.Entities.Events;

import Core.Entities.Tire;

public class ChangeTireEvent {
	private Tire Tire;
	public ChangeTireEvent(Tire tire) {
		Tire = tire;
	}
	public Tire ChangeTire() {
		return Tire;
	}
	
}
