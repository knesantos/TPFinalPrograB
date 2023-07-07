package Core.Entities;

public abstract class Jugador {
	private String nombre;
	private int id;
	
	public Jugador(String nombre, int id) {
		this.nombre = nombre;
		this.id = id;
	}

	public String getnombre () {
		return nombre;
	}
	
	public int getid () {
		return id;
	}
	
	public void setnombre(String nombre) {
        this.nombre = nombre;
    }
	
	public void setid(int id) {
	    this.id = id;
	}
}
