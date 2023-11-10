package Core.Entities;

import java.io.Serializable;

public class Soft extends Tire implements Serializable{

    // Constructor con argumentos
    public Soft(int wear, int durability, int consumption, int speed, int grip) {
        super(wear, durability, consumption, speed, grip);
    }

    // Constructor sin argumentos con valores por defecto
    public Soft() {
        super(0, 60, 8, 95, 80); 
    }

    public boolean isSuitableFor(String weatherCondition) {
        return weatherCondition.equalsIgnoreCase("Dry");
    }

    public double getModificationFactorTime() {
        return 0.90; // 10% más rápido
    }
}