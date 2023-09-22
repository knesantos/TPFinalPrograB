package Core.Entities;

public class Soft extends Neumatico {

	public Soft(int desgaste, int durabilidad, int consumo, int velocidad, int agarre) {
		super(desgaste, durabilidad, consumo, velocidad, agarre);
		
	}
	@Override
    public boolean esAdecuadoPara(String condicionClimatica) {
        return condicionClimatica.equalsIgnoreCase("Seco");
    }
	@Override
	public double obtenerFactorModificacionTiempo() {
		return 0.90; //10% mas rapido
	}
}
