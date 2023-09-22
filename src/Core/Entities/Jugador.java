package Core.Entities;

public abstract class Jugador {
	private String nombre;
	private int id;
	protected Piloto Piloto;
	protected Auto Auto;
	
	public Jugador() {
		
	}
	
	public Jugador(String nombre, int id) {
		this.nombre = nombre;
		this.id = id;
	}

	public String getNombre () {
		return nombre;
	}
	
	public int getId () {
		return id;
	}
	
	public void setNombre(String nombre) {
        this.nombre = nombre;
    }
	
	public void setId(int id) {
	    this.id = id;
	}
	
	public Auto getAuto () {
		return Auto;
	}
	
	public Piloto getPiloto () {
		return Piloto;
	}
	
	public void setAuto(Auto auto) {
        this.Auto = auto;
    }
	
	public void setPiloto(Piloto piloto) {
	    this.Piloto = piloto;
	}
}
