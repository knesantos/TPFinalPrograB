package Core.Entities;

import java.io.Serializable;

public class Wet extends Tire implements Serializable{

	public Wet(int wear, int durability, int consumption, int speed, int grip) {
		super(wear,durability,consumption,speed,grip);
	}
	
	public Wet() {
		super(0, 85, 10, 80, 95); 
	}
	
    public boolean isSuitableFor(String weatherCondition) {
        return weatherCondition.equalsIgnoreCase("Rainy");
    }

	public double getModificationFactorTime() {
		return 1.15;
	}
}
