package Core.Entities;

public class Medium extends Tire {

    // Constructor con argumentos
    public Medium(int wear, int durability, int consumption, int speed, int grip) {
        super(wear, durability, consumption, speed, grip);
    }

    // Constructor sin argumentos con valores por defecto
    public Medium() {
        super(0, 70, 6, 92, 75); 
    }

    public boolean isSuitableFor(String weatherCondition) {
        return weatherCondition.equalsIgnoreCase("Dry") || weatherCondition.equalsIgnoreCase("Humid");
    }

    public double getModificationFactorTime() {
        return 1; // Ni más lento ni más rápido
    }
}