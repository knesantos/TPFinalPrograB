package Core.Entities;

public class Auto {
	private int performanceSobrepaso;
	private int performanceCurvas;
	private double peso;
	private int fiabilidad;
	private int velocidadMax;

	private double aceleracion;

	private int potencia;
	private int consumo;
	private Neumatico neumatico;
	private String marca;
	private String modelo;
	
	public Auto(int performanceSobrepaso, int performanceCurvas, double peso, int fiabilidad, int velocidadMax,
			double aceleracion, int potencia, int consumo, Neumatico neumatico, String marca, String modelo) {

		super();
		this.performanceSobrepaso = performanceSobrepaso;
		this.performanceCurvas = performanceCurvas;
		this.peso = peso;
		this.fiabilidad = fiabilidad;
		this.velocidadMax = velocidadMax;
		this.aceleracion = aceleracion;
		this.potencia = potencia;
		this.consumo = consumo;
		this.neumatico = neumatico;
		this.marca = marca;
		this.modelo = modelo;
	}
	
	public Auto() {
		// TODO Auto-generated constructor stub
	}

	public int getPerformanceSobrepaso() {
		return performanceSobrepaso;
	}
	public void setPerformanceSobrepaso(int performanceSobrepaso) {
		this.performanceSobrepaso = performanceSobrepaso;
	}
	public int getPerformanceCurvas() {
		return performanceCurvas;
	}
	public void setPerformanceCurvas(int performanceCurvas) {
		this.performanceCurvas = performanceCurvas;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public int getFiabilidad() {
		return fiabilidad;
	}
	public void setFiabilidad(int fiabilidad) {
		this.fiabilidad = fiabilidad;
	}
	public int getVelocidadMax() {
		return velocidadMax;
	}
	public void setVelocidadMax(int velocidadMax) {
		this.velocidadMax = velocidadMax;
	}

	public double getAceleracion() {

		return aceleracion;
	}
	public void setAceleracion(int aceleracion) {
		this.aceleracion = aceleracion;
	}
	public int getPotencia() {
		return potencia;
	}
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}
	public int getConsumo() {
		return consumo;
	}
	public void setConsumo(int consumo) {
		this.consumo = consumo;
	}
	public Neumatico getNeumatico() {
		return neumatico;
	}
	public void setNeumatico(Neumatico neumatico) {
		this.neumatico = neumatico;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public void changeNeumatico() {
		
	} 

}