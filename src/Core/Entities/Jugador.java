package Core.Entities;

public abstract class Jugador {
	private String nombre;
	private int id;
	Auto auto;
	Piloto piloto;
	
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
		return auto;
	}
	
	public Piloto getPiloto () {
		return piloto;
	}
	
	public void setAuto(Auto auto) {
        this.auto = auto;
    }
	
	public void setPiloto(Piloto piloto) {
	    this.piloto = piloto;
	}
}
