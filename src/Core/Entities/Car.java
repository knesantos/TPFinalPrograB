package Core.Entities;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Car implements Runnable {
	private static final int OVERALLCONSUMPTION_FACTOR = 65 / 2;
    private int overtakingPerformance;
    private int curvesPerformance;
    private double weight;
    private int reliability;
    private int maxSpeed;
    private double acceleration;
    private int power;
    private int consumption; // Liters per lap
    private Tire tire = new Medium();
    private String brand;
    private String model;
    private int health; // Represents the "health" of the car, could be in a range of 0-100, where 100 is optimal and 0 is inoperable.
    private double fuel; // in liters
    private DrivingMode drivingMode = new DrivingMode(50, 50, "Moderate");
    private double metersDriven = 0.0;
    private Player player;
    private double lapTime;
    private int actualLap;
    private boolean isBroken = false;
    private boolean endRace = false;
    private boolean needPits = false;
    private String fuelState;
    private String imagePath;
    private Image avatar;

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
    
    public void updateFuelState() {
        if (fuel >= 75) {
            setFuelState("Tanque lleno");
        } else if (fuel >= 50) {
            setFuelState("3/4 de tanque");
        } else if (fuel >= 25) {
            setFuelState("1/2 tanque");
        } else {
            setFuelState("1/4 de tanque");
        }
    }



    public DrivingMode getDrivingMode() {
        return drivingMode;
    }

    public void setDrivingMode(DrivingMode drivingMode) {
        this.drivingMode = drivingMode;
     // Ajustamos el consumo de combustible según el modo de manejo
        this.consumption *= drivingMode.getConsumption();
    }

    public double getmetersDriven() {
        return metersDriven;
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
        needPits =false;
    }

   
    
    
    public void initializeCarForRace() { // aca deben llegar las deciciones del jugador de como empezar
        this.health = 100;
        this.fuel = 100.0;
        this.tire.setWear(0);
        this.lapTime = 0.0;
        this.metersDriven = 0.0;
        this.isBroken = false;
        this.needPits =false;
        this.fuel = 100.0;
    }
    
    public double getLapTime() {
        return lapTime;
    }
    
    public void simulateLap(Circuit Circuit, RaceCondition condition, Driver driver) {
        lapTime = 0.0;
        double timeMultiplier = 0.0551;

        // Ajustar maxSpeed y aceleración según el modo de conducción y habilidades del conductor
        int adjustedMaxSpeed = Math.max(1, (int) (maxSpeed * (1 + (drivingMode.getAggressiveness() + driver.getOvertaking()) / 200.0)));
        double adjustedAcceleration = Math.max(0, acceleration * (1 + (drivingMode.getAggressiveness() + driver.getTireCare()) / 200.0));
        double adjustedConsumption = consumption * (1 + drivingMode.getConsumption() / 100.0);

        double fuelFactor = Math.max(0, fuel / 100);
        double tireFactor = Math.max(0, (100 - tire.getWear()) / 100);
        double realMaxSpeed = Math.max(1, adjustedMaxSpeed * fuelFactor * tireFactor);
        double realAcceleration = Math.max(0, adjustedAcceleration * fuelFactor * tireFactor);

        // Simular diferentes secciones de la pista y sumar el tiempo
        lapTime += Math.max(0, (Circuit.getLength() / realMaxSpeed) * 60 * (1 - realAcceleration / 100));

        // Curvas
        double curveFactor = 1 + ((100 - curvesPerformance) + (drivingMode.getDefensiveness() - drivingMode.getAggressiveness())) / 200.0;
        lapTime += Math.max(0, (Circuit.getCurveCount() * 0.2) * 60 * curveFactor);

        // Zonas de adelantamiento
        double overtakingFactor = (drivingMode.getAggressiveness() + driver.getOvertaking()) / 200.0;
        lapTime += Math.max(0, (Circuit.getOvertakingZoneCount() * 0.3) * 60 * (1 - overtakingFactor));

        // Factor de neumáticos
        double tireWearFactor = 0.1 * tire.getDurability()*(drivingMode.getAggressiveness() + driver.getTireCare()) / 500.0;
        tire.setWear(Math.min(100, tire.getWear() + ((int)(tireWearFactor))));


        // Ajuste de condiciones climáticas
        if (tire.isSuitableFor(condition.getCondition())) {
            lapTime *= 0.95;
        }

        // Ajustar el tiempo de vuelta para que sea más realista
        lapTime = Math.max(0, lapTime * timeMultiplier);

        // Actualizar atributos del coche
        double lapLength = Circuit.getLength() / Circuit.getLapCount() + 1;
        metersDriven = lapLength * actualLap;

        health = (int) Math.max(0, health - (100 - reliability) * 0.01);
       
        double fuelConsumed = (adjustedConsumption * realMaxSpeed / maxSpeed)/10;
        fuel = Math.max(0, fuel - fuelConsumed);
        updateFuelState();
        weight = Math.max(0, weight - adjustedConsumption);

        // Parada en boxes
        if (needPits) {

            double randomChance = Math.random();
            if (randomChance >= 0.2) {
                pitStop();
                lapTime += 20 - (1 - (100 - player.getDriver().getBudget()) * 0.01);
            } else {
                System.out.println("El coche " + brand + " " + model + " se saltó la parada en boxes."+ player.getName());
            }
        }

        checkCarStatus();
        if (isBroken) {
            System.out.println("El coche " + brand + " " + model + " está roto y no puede continuar.");
            if (Circuit.getLapCount() == actualLap) {
                endRace = true;
                metersDriven = Circuit.getLength();
            }
        }
      
    }
    @Override
    public void run() {
        Player associatedPlayer = getPlayer();
        Circuit circuit = associatedPlayer.getActiveRace().getCircuit();
        RaceCondition condition = associatedPlayer.getActiveRace().getRaceCondition();
        Driver driver = associatedPlayer.getDriver();

        if (!this.isBroken) {
            simulateLap(circuit, condition, driver);
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

	public void setmetersDriven(int i) {
		this.metersDriven = i;
		
	}

	public void setFuel(double fuel) {
		 this.fuel = fuel;
	        this.weight += fuel; 
	}

	public boolean getNeedPits () {
		return needPits;
	}

	public void setNeedPits(boolean bool) {
		this.needPits = bool;
	}

	public String getFuelState() {
		return fuelState;
	}

	public void setFuelState(String fuelState) {
		this.fuelState = fuelState;
	}

	public int getActualLap() {
		return actualLap;
	}

	public void setActualLap(int actualLap) {
		this.actualLap = actualLap;
	}

	public void addLapTime(int i) {
		this.lapTime += i;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Image getAvatar() {
		return avatar;
	}
	
	

	public void setAvatar(String imagePath) {
        this.imagePath = imagePath;
        File f = new File(imagePath);
        if(f.exists() && !f.isDirectory()) { 
            System.out.println("Imagen existe: " + imagePath);
        } else {
            System.out.println("Imagen no existe: " + imagePath);
        }
        // Cargar la imagen en el atributo avatar
        ImageIcon imageIcon = new ImageIcon(imagePath);
        this.avatar = imageIcon.getImage();
    }


}