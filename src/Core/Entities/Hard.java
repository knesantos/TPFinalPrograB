package Core.Entities;

public class Hard extends Tire {

    // Constructor con argumentos
    public Hard(int wear, int durability, int consumption, int speed, int grip) {
        super(wear, durability, consumption, speed, grip);
    }
    
    public Hard() {
        super(0, 80, 5, 90, 70); 
    }

    public boolean isSuitableFor(String weatherCondition) {
        return weatherCondition.equalsIgnoreCase("Dry");
    }

    public double getModificationFactorTime() {
        return 1.1; // 10% más lento
    }
}