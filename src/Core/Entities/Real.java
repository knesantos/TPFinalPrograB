package Core.Entities;

public class Real extends Player {
    
    public Real(String name, int id) {
        super(name, id);
    }


    public void setDriver(Driver driver) {
        super.setDriver(driver);
    }

    public Driver getDriver() {
    	return super.getDriver();
    }
    
    public void setCar(Car car) {
       super.setCar(car);
    }

    public Car getCar() {
        return super.getCar();
    }


	@Override
	public void SimIA(Race race) {

	}


	@Override
	protected boolean isReal() {
		return true;
	}
}

