package Core.Entities;

public class Pais {
	private String nombre;
	private String nombreAbrev;
	
	
	public Pais(String nombre, String nombreAbrev) {
		this.nombre = nombre;
		this.nombreAbrev = nombreAbrev;
	}

	public String getnombre () {
		return nombre;
	}
	
	public String getnombreAbrev () {
		return nombreAbrev;
	}
	
	public void setnombre(String nombre) {
        this.nombre = nombre;
    }
	
	public void setnombreAbrev(String nombreAbrev) {
	    this.nombreAbrev = nombreAbrev;
	}

	
}
