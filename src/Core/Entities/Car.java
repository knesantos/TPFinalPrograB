package Core.Entities;

public class Car implements Runnable {
	private int overtakingPerformance;
	private int curvesPerformance;
	private double weight;
	private int reliability;
	private int maxSpeed;
	private double acceleration;
	private int power;
	private int consumption; //Litros por vuelta
	private Tire tire = new Medium(50, 50, 50, 50, 50);
	private String brand;
	private String model;
	private int life; // Representa la "vida" del auto, podría estar en un rango de 0-100, donde 100 es el estado óptimo y 0 es un auto inoperable.
	private double fuel; // en litros
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
	}


	
	public Car() {
		// TODO Auto-generated constructor stub
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

	    public int getLife() {
	        return life;
	    }

	    public void setLife(int life) {
	        this.life = life;
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
	    
	    public void changeConsumption(String drivingMode) { // EVENT
	    	 // Ajustamos el consumo de combustible según el modo de manejo
	        switch (drivingMode) {
	            case "Fast":
	                this.consumption *= 1.2;
	                break;
	            case "Moderate":
	                this.consumption *= 1.0;
	                break;
	            case "Slow":
	                this.consumption *= 0.8;
	                break;
	        }
	    }

	    public void checkCarState() { // EVENTS
	        if (life <= 0) {
	            isBroken = true;
	        } else if (life < 20 || tire.getWear() > 70) {
	            // PIT STOP
	        } else {
	            // OK
	        }
	    }
	   
	    public void pitStop() {
	    	// Simulamos una parada en los pits
	        life = 100; // Restauramos la vida del auto a 100
	        tire.setWear(0); // Restauramos el desgaste de los neumáticos a 0
	        System.out.println("The car " + brand + " " + model + " has made a pit stop.");
	    }

	    
	    public double simulateLap(Circuit circuit, RaceCondition condition, Driver driver){ 
	    	// Definimos una variable para almacenar el tiempo del auto en la vuelta
	        double lapTime = 0.0;
	        
	     // Ajustamos la velocidad máxima y la aceleración basándonos en el modo de manejo
	        int adjustedMaxSpeed = (int) (maxSpeed * (1 + drivingMode.getAggressiveness() / 100.0));
	        double adjustedAcceleration = acceleration * (1 + drivingMode.getAggressiveness() / 100.0);
	    
	 

	        // Simulamos diferentes secciones de la pista y sumamos el tiempo 
	        // Sección Base
	        lapTime += (circuit.getLength() / adjustedMaxSpeed) * 60 * (1 - adjustedAcceleration / 100);


	        // Curvas
	        lapTime += (circuit.getNumberOfCurves() * 0.2) * 60 * (1 + (100 - curvesPerformance) / 100)
	                * (1 + tire.getWear() / 100);

	        // Zonas de sobrepaso
	        double defenseFactor = driver.getDefense() / 100.0;
	        double overtakingFactor = driver.getOvertaking() / 100.0;
	        lapTime += (circuit.getNumberOfOvertakingZones() * 0.3) * 60
	                * (1 - (overtakingPerformance / 100) - (overtakingFactor * 0.05) + (defenseFactor * 0.05));

	        // Factor neumático
	        double tireCareFactor = driver.getTireCare() / 100.0;
	        tire.setWear(tire.getWear() + (tire.getDurability() * drivingMode.getAggressiveness() / 100.0)
	                * (1 - tireCareFactor * 0.05));


	 
	     // Ajuste por condición climática
	        if (tire.isSuitableFor(condition.getCondition())) {
	            lapTime *= 0.95;
	        }

	        // Actualizamos los atributos del auto
	        kilometersDriven += circuit.getLength();
	        life -= (100 - reliability) * 0.01;
	        fuel -= consumption;
	        weight -= fuel;

	        if (Math.random() < 0.6) {
	            pitStop(); // ESTO DEBE SER UN EVENTO
	        }
	        checkCarState();
	        
	        
	        if (isBroken) {
	        	System.out.println("The car " + brand + " " + model + " is broken and cannot continue.");
	        	return lapTime;// Esto esta bien?
	        }
	     
	        // Imprimimos el tiempo que tardó el auto en completar la vuelta
	        System.out.println("The car " + brand+ " " + model + " has compleated the lap in " + lapTime + " seconds and covered "+kilometersDriven+" Km");
	        return lapTime;
	    }
	
	
	
	public void changeTire() {
		
	}

	public void run() {
		
		
	}

	public boolean isBroken() {
		return isBroken;
	}

	public void setEstaRoto(boolean isBroken) {
		this.isBroken= isBroken;
	} 

}