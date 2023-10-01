package Core.Entities;

public class Wet extends Tire{

	public Wet(int wear, int durability, int consumption, int speed, int grip) {
		super(wear,durability,consumption,speed,grip);
	}
	
    public boolean isSuitableFor(String weatherCondition) {
        return weatherCondition.equalsIgnoreCase("Rainy");
    }

	public double getModificationFactorTime() {
		return 1.15;
	}
}
