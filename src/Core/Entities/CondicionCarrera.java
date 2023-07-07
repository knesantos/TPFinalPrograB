package Core.Entities;

public class CondicionCarrera {
	private String condicion;
	private  int temperatura,precipitaciones;

public CondicionCarrera (String condicion,int temperatura, int precipitaciones ) {
	this.condicion = condicion;
	this.temperatura = temperatura;
	this.precipitaciones = precipitaciones;
}
	
 public int getTemperatura() {
	 return temperatura;
 }
 public int getPrecipitaciones() {
	 return precipitaciones;
 }
 public String getCondicion() {
	 return condicion;
 }

	
	
}
