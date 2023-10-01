package Core.Entities;

public class Soft extends Tire {

	public Soft(int wear, int durability, int consumption, int speed, int grip) {
		super(wear,durability,consumption,speed,grip);
		
	}

    public boolean isSuitableFor(String weatherCondition) {
        return weatherCondition.equalsIgnoreCase("Dry");
    }

	public double getModificationFactorTime() {
		return 0.90; //10% mas rapido
	}
}
