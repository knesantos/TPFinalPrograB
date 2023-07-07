package Core.Entities;
import java.util.Date;

public class Carrera {
	
	private Date fecha;
	private Circuito circuito;
	private int id;
	
	public Carrera(Date fecha, int id) {
		this.fecha = fecha;
		this.id = id;
			
	}
	public Date getFecha() {
		return fecha;
	}
	public int getId() {
		return id;
	}

}
