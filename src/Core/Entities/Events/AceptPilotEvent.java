package Core.Entities.Events;

import Core.Entities.Piloto;

public class AceptPilotEvent {
	private Piloto Piloto;
	
	public AceptPilotEvent(Piloto piloto) {
		this.Piloto = piloto;
	}
	
	public Piloto getPiloto() {
		return  Piloto;
	}
}
