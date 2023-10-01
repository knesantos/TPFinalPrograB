package Core.Entities.Events;

import Core.Entities.Auto;

public class ContinueEvent {
	private Auto Auto;
	
	public ContinueEvent(Auto auto) {
		this.Auto = auto;
	}
	
	public Auto getAuto() {
		return  Auto;
	}
}
