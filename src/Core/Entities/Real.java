package Core.Entities;

public class Real extends Player {
    
    public Real(String name, int id) {
        super(name, id);
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }
    
    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }
}