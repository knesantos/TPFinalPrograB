package Core.Entities;

public class ModoManejo {
	private int agresividad;
	private int consumo;
	private String tipo;
	
	public ModoManejo(int agresividad, int consumo, String tipo) {
		super();
		this.agresividad = agresividad;
		this.consumo = consumo;
		this.tipo = tipo;
	}

	public int getAgresividad() {
		return agresividad;
	}

	public void setAgresividad(int agresividad) {
		this.agresividad = agresividad;
	}

	public int getConsumo() {
		return consumo;
	}

	public void setConsumo(int consumo) {
		this.consumo = consumo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
	
	
}
