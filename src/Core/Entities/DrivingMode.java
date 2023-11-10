package Core.Entities;

import java.io.Serializable;

public class DrivingMode implements Serializable{
	
	private int aggressiveness;
	private int defensiveness;
	private int consumption;
	private String type;

	public DrivingMode(int aggressiveness, int consumption, String type) {
	    super();
	    this.aggressiveness = aggressiveness;
	    this.consumption = consumption;
	    this.type = type;
	}
	
	public DrivingMode(String type) {
	    super();
	    this.type = type;
	    
	    switch(type) {
	        case "Agressive":
	            this.aggressiveness = 90;
	            this.defensiveness= 20;
	            this.consumption = 90; 
	            break;
	        case "Moderated":
	            this.aggressiveness = 50;
	            this.defensiveness= 50;
	            this.consumption = 50; 
	            break;
	        case "Defensive":
	        	 this.defensiveness = 90; 
	            this.aggressiveness = 20; 
	            this.consumption = 10; 
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid driving mode type");
	    }
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

	public int getDefensiveness() {
		return defensiveness;
	}

	public void setDefensiveness(int defensiveness) {
		this.defensiveness = defensiveness;
	}
}
