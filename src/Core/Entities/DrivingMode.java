package Core.Entities;

public class DrivingMode {
	
	private int aggressiveness;
	private int consumption;
	private String type;

	public DrivingMode(int aggressiveness, int consumption, String type) {
	    super();
	    this.aggressiveness = aggressiveness;
	    this.consumption = consumption;
	    this.type = type;
	}

	public int getAggressiveness() {
	    return aggressiveness;
	}

	public void setAggressiveness(int aggressiveness) {
	    this.aggressiveness = aggressiveness;
	}

	public int getConsumption() {
	    return consumption;
	}

	public void setConsumption(int consumption) {
	    this.consumption = consumption;
	}

	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}
	
	
}
