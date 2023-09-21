package Core.Entities.Events;

public class AceptPilotEvent {
	private int index;
	
	public AceptPilotEvent(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return  index;
	}
}
