package Core.Entities;

import java.io.Serializable;

public abstract class Player implements Serializable{

    private String name;
    private int id;
    private Driver driver;
    private Car car;
    private Race activeRace;
    private DrivingMode divingMode = new DrivingMode("Moderated");
    
    public Player() {
        
    }
    
    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public int getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Car getCar() {
        return car;
    }
    
    public Driver getDriver() {
        return driver;
    }
    
    public void setCar(Car car) {
        this.car = car;
    }
    
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setActiveRace(Race race) {
        this.activeRace = race;
    }
    
    public Race getActiveRace() {
        return activeRace;
    }

	public DrivingMode getDrivingMode() {
		return divingMode;
	}

	public void setDrivingMode(DrivingMode divingMode) {
		this.divingMode = divingMode;
	}
	
	public  abstract void SimIA(Race race);

	protected abstract boolean isReal();
}

