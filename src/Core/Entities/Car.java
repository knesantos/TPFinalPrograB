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

    public int gethealth() {
        return health;
    }

    public void sethealth(int health) {
        this.health = health;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
        this.weight += fuel; // Asumiendo que cada litro de combustible aumenta el peso en 1kg
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
        System.out.println("The car " + brand + " " + model + " has made a pit stop.");
    }

    public double simulateLap(Circuit Circuit, RaceCondition condition, Driver driver) {
        double lapTime = 0.0;

        // Adjust max speed and acceleration based on driving mode
        int adjustedMaxSpeed = (int) (maxSpeed * (1 + drivingMode.getAggressiveness() / 100.0));
        double adjustedAcceleration = acceleration * (1 + drivingMode.getAggressiveness() / 100.0);

        // Simulate different track sections and add up the time
        lapTime += (Circuit.getLength() / adjustedMaxSpeed) * 60 * (1 - adjustedAcceleration / 100);

        // Corners
        lapTime += (Circuit.getCurveCount() * 0.2) * 60 * (1 + (100 - curvesPerformance) / 100) * (1 + tire.getWear() / 100);

        // Overtaking zones
        double defenseFactor = driver.getDefense() / 100.0;
        double overtakingFactor = driver.getOvertaking() / 100.0;
        lapTime += (Circuit.getOvertakingZoneCount() * 0.3) * 60 * (1 - (overtakingPerformance / 100) - (overtakingFactor * 0.05) + (defenseFactor * 0.05));

        // Tire factor
        double tireCareFactor = driver.getTireCare() / 100.0;
        tire.setWear(tire.getWear() + (tire.getDurability() * drivingMode.getAggressiveness() / 100.0) * (1 - tireCareFactor * 0.05));

        // Weather condition adjustment
        if (tire.isSuitableFor(condition.getCondition())) {
            lapTime *= 0.95;
        }

        // Update car attributes
        kilometersDriven += Circuit.getLength();
        health -= (100 - reliability) * 0.01;
        fuel -= consumption;
        weight -= consumption;

        if (Math.random() < 0.8) {
            pitStop(); // THIS SHOULD BE AN EVENT
        }

        checkCarStatus();
        if (isBroken) {
            System.out.println("The car " + brand + " " + model + " is broken and cannot continue.");
            return lapTime; // Is this correct?
        }

        System.out.println("The car " + brand + " " + model + " completed the lap in " + lapTime + " seconds and drove " + kilometersDriven + " Km");
        return lapTime;
    }

    @Override
    public void run() {
        Player associatedPlayer = getPlayer();
        Circuit circuit = associatedPlayer.getActiveRace().getCircuit();
        RaceCondition condition = associatedPlayer.getActiveRace().getRaceCondition();
        Driver driver = associatedPlayer.getDriver();

        if (!this.isBroken) {
            double lapTime = this.simulateLap(circuit, condition, driver);

            synchronized (associatedPlayer.getActiveRace().playerTimes) {
                int playerId = associatedPlayer.getId();
                double currentTime = associatedPlayer.getActiveRace().playerTimes.getOrDefault(playerId, 0.0);
                associatedPlayer.getActiveRace().playerTimes.put(playerId, currentTime + lapTime);
            }
        }
    }

	public boolean isBroken() {
		return isBroken;
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

}