package Core.Entities;

public abstract class Player {
	private String name;
	private int id;
	protected Driver driver;
	protected Car car;

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
}