package Core.Entities.Events;


public class AceptSelectionEvent {
	private boolean pressed;
	
	public AceptSelectionEvent(boolean pressed) {
		this.pressed = pressed;
	}
	
	public boolean getPiloto() {
		return pressed;
	}
}
