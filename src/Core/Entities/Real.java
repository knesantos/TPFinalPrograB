package Core.Entities;


public class Real extends Jugador {
	private Piloto Piloto;
	private Auto Auto;

	public Real(String nombre, int id) {
		super(nombre, id);
		
	}

	public void setPilot(Piloto piloto) {
		this.Piloto = piloto;
	}

	public Piloto getPilot() {
		return Piloto;
	}
	public void setAuto(Auto Auto) {
		this.Auto = Auto;
	}

	public Auto getAuto() {
		return Auto;
	}
}
