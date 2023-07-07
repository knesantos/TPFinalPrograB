package Core.Entities;

public abstract class Jugador {
	private String nombre;
	private String avatar;
	private int id;
	private Piloto piloto;
	private Auto auto;
	
	public Jugador(String nombre, String avatar, int id, Piloto piloto, Auto auto) {
		this.nombre = nombre;
		this.avatar = avatar;
		this.id = id;
		this.piloto = piloto;
		this.auto = auto;
	}

	public String getnombre () {
		return nombre;
	}
	
	public String getavatar () {
		return avatar;
	}
	public int getid () {
		return id;
	}
	
	public Piloto getpiloto () {
		return piloto;
	}
	
	public Auto getauto () {
		return auto;
	}
	
	public void setnombre(String nombre) {
        this.nombre = nombre;
    }
	
	public void setavatar(String avatar) {
        this.avatar = avatar;
    }
	
	public void setid(int id) {
	    this.id = id;
	}
	
	public void setpiloto(Piloto piloto) {
	    this.piloto = piloto;
	}
	
	public void setauto(Auto auto) {
	    this.auto = auto;
	}
}
