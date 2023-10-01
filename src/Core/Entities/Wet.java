package Core.Entities;

public class Wet extends Neumatico{

	public Wet(int desgaste, int durabilidad, int consumo, int velocidad, int agarre) {
		super(desgaste, durabilidad, consumo, velocidad, agarre);
	}
	
	@Override
    public boolean esAdecuadoPara(String condicionClimatica) {
        return condicionClimatica.equalsIgnoreCase("Lluvioso");
    }

	@Override
	public double obtenerFactorModificacionTiempo() {
		return 1.15;
	}
}
