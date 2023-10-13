package Core.Entities;

public class Car implements Runnable {
    private int overtakingPerformance;
    private int curvesPerformance;
    private double weight;
    private int reliability;
    private int maxSpeed;
    private double acceleration;
    private int power;
    private int consumption; // Liters per lap
    private Tire tire = new Medium(50, 50, 50, 50, 50);
    private String brand;
    private String model;
    private int health; // Represents the "health" of the car, could be in a range of 0-100, where 100 is optimal and 0 is inoperable.
    private double fuel; // in liters
    private DrivingMode drivingMode = new DrivingMode(50, 50, "Moderate");
    private double kilometersDriven = 0.0;
    private Player player;
    private double lapTime;
    private boolean isBroken = false;

    public Car(int overtakingPerformance, int curvesPerformance, double weight, int reliability, int maxSpeed,
               double acceleration, int power, int consumption, Tire tire, String brand, String model) {
        super();
        this.overtakingPerformance = overtakingPerformance;
        this.curvesPerformance = curvesPerformance;
        this.weight = weight;
        this.reliability = reliability;
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.power = power;
        this.consumption = consumption;
        this.tire = tire;
        this.brand = brand;
        this.model = model;
        this.health = 100;
    }

    public Car() {
    }

    public int getOvertakingPerformance() {
        return overtakingPerformance;
    }

    public void setOvertakingPerformance(int overtakingPerformance) {
        this.overtakingPerformance = overtakingPerformance;
    }

    public int getCurvesPerformance() {
        return curvesPerformance;
    }

    public void setCurvesPerformance(int curvesPerformance) {
        this.curvesPerformance = curvesPerformance;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getAcceleration() {

        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    
    public int getConsumption() {
		return consumption;
	}


	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}


	public Tire getTire() {
        return tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getFuel() {
        return fuel;
    }


    public DrivingMode getDrivingMode() {
        return drivingMode;
    }

    public void setDrivingMode(DrivingMode drivingMode) {
        this.drivingMode = drivingMode;
     // Ajustamos el consumo de combustible según el modo de manejo
        this.consumption *= drivingMode.getConsumption();
    }

    public double getKilometersDriven() {
        return kilometersDriven;
    }

    public void checkCarStatus() {
        if (health <= 0) {
            isBroken = true;
        } else if (health < 20 || tire.getWear() > 70) {
            // PIT STOP
        } else {
            // OK
        }
    }

    public void pitStop() {
        // Simulate a pit stop
        health = 100; // Restore the car's health to 100
        tire.setWear(0); // Restore tire wear to 0
        fuel=100.0;
        System.out.println("The car " + brand + " " + model + " has made a pit stop.");
    }

   
    
    
    public void initializeCarForRace() { // aca deben llegar las deciciones del jugador de como empezar
        this.health = 100;
        this.fuel = 100.0;
        this.tire.setWear(0);
        this.lapTime = 0.0;
        this.kilometersDriven = 0.0;
        this.isBroken = false;
    }
    
    public double getLapTime() {
        return lapTime;
    }
    
    public double simulateLap(Circuit Circuit, RaceCondition condition, Driver driver) {
    	 lapTime = 0.0;

    	    // Ajustar maxSpeed y aceleración según el modo de conducción
    	    int adjustedMaxSpeed = (int) (maxSpeed * (1 + drivingMode.getAggressiveness() / 100.0));
    	    double adjustedAcceleration = acceleration * (1 + drivingMode.getAggressiveness() / 100.0);

    	    double fuelFactor = fuel / 100;  // Suponiendo que el combustible es un porcentaje
    	    double tireFactor = (100 - tire.getWear()) / 100;
    	    double realMaxSpeed = adjustedMaxSpeed * fuelFactor * tireFactor;
    	    double realAcceleration = adjustedAcceleration * fuelFactor * tireFactor;

    	    // Simular diferentes secciones de la pista y sumar el tiempo
    	    lapTime += (Circuit.getLength() / realMaxSpeed) * 60 * (1 - realAcceleration / 100);

    	    // Curvas
    	    lapTime += (Circuit.getCurveCount() * 0.2) * 60 * (1 + (100 - curvesPerformance) / 100) * (1 + tire.getWear() / 100);

    	    // Zonas de adelantamiento
    	    double defenseFactor = driver.getDefense() / 100.0;
    	    double overtakingFactor = driver.getOvertaking() / 100.0;
    	    lapTime += (Circuit.getOvertakingZoneCount() * 0.3) * 60 * (1 - (overtakingPerformance / 100) - (overtakingFactor * 0.05) + (defenseFactor * 0.05));

    	    // Factor de neumáticos
    	    double tireCareFactor = driver.getTireCare() / 100.0;
    	    tire.setWear(tire.getWear() + (tire.getDurability() * drivingMode.getAggressiveness() / 100.0) * (1 - tireCareFactor * 0.05));

    	    // Ajuste de condiciones climáticas
    	    if (tire.isSuitableFor(condition.getCondition())) {
    	        lapTime *= 0.95;
    	    }

    	    // Ajustar el tiempo de vuelta para que sea más realista
    	    lapTime /= 10;  // Dividir por un factor para hacerlo más realista

    	    // Actualizar atributos del coche
    	    kilometersDriven += Circuit.getLength();  // Ajuste para que la distancia sea más realista
    	    health -= (100 - reliability) * 0.01;
    	    double speedFactor = realMaxSpeed / maxSpeed;
    	    fuel -= consumption * speedFactor * (1 + drivingMode.getAggressiveness() / 100.0);
    	    weight -= consumption;

        // Parada en boxes
        if (shouldPitStop()) {
            double randomChance = Math.random();
            if (randomChance >= 0.2) {
                pitStop();
                lapTime += 80; // ESTO DEBERÍA SER UN EVENTO
            } else {
                System.out.println("El coche " + brand + " " + model + " se saltó la parada en boxes.");
            }
        }

        // Fallo mecánico
        if (Math.random() < 0.01) {
            isBroken = true;
            System.out.println("El coche " + brand + " " + model + " ha sufrido un fallo mecánico.");
        }

        checkCarStatus();
        if (isBroken) {
            System.out.println("El coche " + brand + " " + model + " está roto y no puede continuar.");
            return lapTime;
        }

        System.out.println("El coche " + brand + " " + model + " completó la vuelta en " + lapTime + " segundos y recorrió " + kilometersDriven + " Km");
        return lapTime;
    }
    @Override
    public void run() {
        Player associatedPlayer = getPlayer();
        Circuit circuit = associatedPlayer.getActiveRace().getCircuit();
        RaceCondition condition = associatedPlayer.getActiveRace().getRaceCondition();
        Driver driver = associatedPlayer.getDriver();

        if (!this.isBroken) {
            lapTime = this.simulateLap(circuit, condition, driver);
        }
    }

	public boolean isBroken() {
		return isBroken;
	}
	
	private boolean shouldPitStop() {
	    return tire.getWear() > 70 || fuel < 10 || health < 20;
	}

	public void setEstaRoto(boolean isBroken) {
		this.isBroken= isBroken;
	} 
	
	public Player getPlayer () {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setKilometersDriven(int i) {
		this.kilometersDriven = i;
		
	}

	public void setFuel(double fuel) {
		// TODO Auto-generated method stub
		 this.fuel = fuel;
	        this.weight += fuel; // Asumiendo que cada litro de combustible aumenta el peso en 1kg
	}




}