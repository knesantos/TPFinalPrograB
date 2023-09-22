package Core.Entities;

public class Medium extends Neumatico  {

	public Medium(int desgaste, int durabilidad, int consumo, int velocidad, int agarre) {
		super(desgaste, durabilidad, consumo, velocidad, agarre);
		
	}
	
	 @Override
	    public boolean esAdecuadoPara(String condicionClimatica) {
	        return condicionClimatica.equalsIgnoreCase("Seco") || condicionClimatica.equalsIgnoreCase("Húmedo");
	    }
	 
	 public double obtenerFactorModificacionTiempo() {
			return 1;//Ni mas lento ni ams rapido
		}
}
