package Core.Entities;

public class RaceCondition {
	private String condition;
	private int temperature, precipitation;

	public RaceCondition(String condition, int temperature, int precipitation) {
	    this.condition = condition;
	    this.temperature = temperature;
	    this.precipitation = precipitation;
	}

	public int getTemperature() {
	    return temperature;
	}

	public int getPrecipitation() {
	    return precipitation;
	}

	public String getCondition() {
	    return condition;
	}
	
}
