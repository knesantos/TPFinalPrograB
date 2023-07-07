package Core.Entities;

public class Piloto {
	private long presupuesto;
	private int defensa;
	private int sobrepaso;
	private int clasificacion;
	private int cuidadoNeumaticos;
	private int largada;
	private String nombre;
	private String nombreAbrev;
	private int cantCarrerasGanadas;
	private int cantPolePosition;
	private int cantCampeonatos;
	private int cantParticipaciones;
	
	
	

	public Piloto(long presupuesto, int defensa, int sobrepaso, int clasificacion, int cuidadoNeumaticos, int largada,
			String nombre, String nombreAbrev, int cantCarrerasGanadas, int cantPolePosition, int cantCampeonatos,
			int cantParticipaciones) {
		super();
		this.presupuesto = presupuesto;
		this.defensa = defensa;
		this.sobrepaso = sobrepaso;
		this.clasificacion = clasificacion;
		this.cuidadoNeumaticos = cuidadoNeumaticos;
		this.largada = largada;
		this.nombre = nombre;
		this.nombreAbrev = nombreAbrev;
		this.cantCarrerasGanadas = cantCarrerasGanadas;
		this.cantPolePosition = cantPolePosition;
		this.cantCampeonatos = cantCampeonatos;
		this.cantParticipaciones = cantParticipaciones;
	}

	public long getpresupuesto () {
		return presupuesto;
	}
		
	public int getdefensa () {
		return defensa;
	}
		
	public int getsobrepaso () {
		return sobrepaso;
	}
		
	public int getclasificacion () {
		return clasificacion;
	}
		
	public int getcuidadoNeumaticos () {
		return cuidadoNeumaticos;
	}
		
	public int getlargada () {
		return largada;
	}
		
	public String getnombre () {
		return nombre;
	}
		
	public String getnombreAbrev () {
		return nombreAbrev;
	}
		
	public int getcantCarrerasGanadas () {
		return cantCarrerasGanadas;
	}
		
	public int getcantPolePosition () {
		return cantPolePosition;
	}
		
	public int getcantCampeonatos () {
		return cantCampeonatos;
	}
		
	public int getcantParticipaciones () {
		return cantParticipaciones;
	}
		
	
		
	
	
	
	public void setpresupuesto(long presupuesto) {
	       this.presupuesto = presupuesto;
	}
		
	public void setdefensa(int defensa) {
	    this.defensa = defensa;
	}
	
	public void setsobrepaso(int sobrepaso) {
	       this.sobrepaso = sobrepaso;
	}
		
	public void setclasificacion(int clasificacion) {
	    this.clasificacion = clasificacion;
	}
	
	public void setcuidadoNeumaticos(int cuidadoNeumaticos) {
	       this.cuidadoNeumaticos = cuidadoNeumaticos;
	}
		
	public void setlargada(int largada) {
	    this.largada = largada;
	}
	
	public void setnombre(String nombre) {
	       this.nombre = nombre;
	}
		
	public void setnombreAbrev(String nombreAbrev) {
	    this.nombreAbrev = nombreAbrev;
	}
	
	public void setcantCarrerasGanadas(int cantCarrerasGanadas) {
	       this.cantCarrerasGanadas = cantCarrerasGanadas;
	}
		
	public void setcantPolePosition(int cantPolePosition) {
	    this.cantPolePosition = cantPolePosition;
	}
	
	public void setcantCampeonatos(int cantCampeonatos) {
	       this.cantCampeonatos = cantCampeonatos;
	}
		
	public void setcantParticipaciones(int cantParticipaciones) {
	    this.cantParticipaciones = cantParticipaciones;
	}
}


