package Core.Entities;

public class Hard extends Neumatico {

	public Hard(int desgaste, int durabilidad, int consumo, int velocidad, int agarre) {
		super(desgaste, durabilidad, consumo, velocidad, agarre);
		
	}

	@Override
    public boolean esAdecuadoPara(String condicionClimatica) {
        return condicionClimatica.equalsIgnoreCase("Seco");
    }

	@Override
	public double obtenerFactorModificacionTiempo() {
		return 1.1;//10% mas lento
	}
	
}
