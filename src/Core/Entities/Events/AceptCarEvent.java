package Core.Entities.Events;

import Core.Entities.Auto;

public class AceptCarEvent {
	private Auto Auto;
	
	public AceptCarEvent(Auto auto) {
		this.Auto = auto;
	}
	
	public Auto getAuto() {
		return  Auto;
	}
}
