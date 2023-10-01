package Core.Entities;

public class Medium extends Tire  {

	public Medium(int wear, int durability, int consumption, int speed, int grip) {
		super(wear,durability,consumption,speed,grip);
		
	}
	
	    public boolean isSuitableFor(String weatherCondition) {
	        return weatherCondition.equalsIgnoreCase("Dry") || weatherCondition.equalsIgnoreCase("humid");
	    }
	 
	 public double getModificationFactorTime() {
			return 1;//Ni mas lento ni ams rapido
		}
}
