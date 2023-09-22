package Core.Entities;

public class Auto implements Runnable {
	private int performanceSobrepaso;
	private int performanceCurvas;
	private double peso;
	private int fiabilidad;
	private int velocidadMax;
	private double aceleracion;
	private int potencia;
	private int consumo; //Litros por vuelta
	private Neumatico neumatico = new Medium(50, 50, 50, 50, 50);
	private String marca;
	private String modelo;
	private int vida; // Representa la "vida" del auto, podría estar en un rango de 0-100, donde 100 es el estado óptimo y 0 es un auto inoperable.
	private double combustible; // en litros
	private ModoManejo modoManejo = new ModoManejo(50,50,"Moderado");
	private double kilometrosRecorridos = 0.0;
	private Jugador jugador;
	
	 private boolean estaRoto = false;
	
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


    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
	
	 public double getCombustible() {
	        return combustible;
	    }

	    public void setCombustible(double combustible) {
	        this.combustible = combustible;
	        this.peso += combustible; // Asumiendo que cada litro de combustible aumenta el peso en 1kg
	    }
	
	    public ModoManejo getModoManejo() {
	        return modoManejo;
	    }

	    public void setModoManejo(ModoManejo modoManejo) {
	        this.modoManejo = modoManejo;
	        // Ajustamos el consumo de combustible según el modo de manejo
	        this.consumo *= modoManejo.getConsumo();
	    }  
	    
	    public double getKilometrosRecorridos() {
	        return kilometrosRecorridos;
	    }
	  
	    public void changeConsumo(String modoManejo) { //EVENTO
	        // Ajustamos el consumo de combustible según el modo de manejo
	        switch (modoManejo) {
	            case "Rápido":
	                this.consumo *= 1.2;
	                break;
	            case "Moderado":
	                this.consumo *= 1.0;
	                break;
	            case "Lento":
	                this.consumo *= 0.8;
	                break;
	        }
	    }
	    
	    public void verificarEstadoAuto() { //EVENTOS 
	        if (vida <= 0) {
	            estaRoto = true;
	        } else if (vida < 20 || neumatico.getDesgaste() > 70) {
	            //PITS
	        } else {
	            //OK
	        }
	    }
	    
	    
	    public void paradaEnPits() {
	        // Simulamos una parada en los pits
	        vida = 100; // Restauramos la vida del auto a 100
	        neumatico.setDesgaste(0); // Restauramos el desgaste de los neumáticos a 0
	        System.out.println("El auto " + marca + " " + modelo + " ha realizado una parada en los pits.");
	    }
	    
	    
	    public double simularVuelta(Circuito circuito, CondicionCarrera condicion, Piloto piloto) {
	        // Definimos una variable para almacenar el tiempo del auto en la vuelta
	        double tiempoVuelta = 0.0;

	        // Ajustamos la velocidad máxima y la aceleración basándonos en el modo de manejo
	        int velocidadMaxAjustada = (int) (velocidadMax * (1 + modoManejo.getAgresividad() / 100.0));
	        double aceleracionAjustada = aceleracion * (1 + modoManejo.getAgresividad() / 100.0);

	        // Simulamos diferentes secciones de la pista y sumamos el tiempo 
	        // Sección Base
	        tiempoVuelta += (circuito.getLongitud() / velocidadMaxAjustada) * 60 * (1 - aceleracionAjustada / 100);

	        // Curvas
	        tiempoVuelta += (circuito.getcantCurvas() * 0.2) * 60 * (1 + (100 - performanceCurvas) / 100) * (1 + neumatico.getDesgaste() / 100);

	        // Zonas de sobrepaso
	        double factorDefensa = piloto.getDefensa() / 100.0;
	        double factorSobrepaso = piloto.getSobrepaso() / 100.0;
	        tiempoVuelta += (circuito.getcantZonasSobrepaso() * 0.3) * 60 * (1 - (performanceSobrepaso / 100) - (factorSobrepaso * 0.05) + (factorDefensa * 0.05));

	        // Factor neumático
	        double factorCuidadoNeumaticos = piloto.getCuidadoNeumaticos() / 100.0;
	        neumatico.setDesgaste(neumatico.getDesgaste() + (neumatico.getDurabilidad() * modoManejo.getAgresividad() / 100.0) * (1 - factorCuidadoNeumaticos * 0.05));

	        // Ajuste por condición climática
	        if (neumatico.esAdecuadoPara(condicion.getCondicion())) {
	            tiempoVuelta *= 0.95;
	        }

	        // Actualizamos los atributos del auto
	        kilometrosRecorridos += circuito.getLongitud();
	        vida -= (100 - fiabilidad) * 0.01;
	        combustible -= consumo;
	        peso -= consumo;
	        	

	        if (Math.random() < 0.6) { 
	              paradaEnPits();// ESTO DEBE SER UN EVENTO
	            }
	        verificarEstadoAuto();
	        if (estaRoto) {
	            System.out.println("El auto " + marca + " " + modelo + " se ha roto y no puede continuar.");
	            return tiempoVuelta; // Esto esta bien?
	        }
	        // Imprimimos el tiempo que tardó el auto en completar la vuelta
	        System.out.println("El auto " + marca + " " + modelo + " completó la vuelta en " + tiempoVuelta + " segundos y recorrio "+kilometrosRecorridos+" Km");
	        return tiempoVuelta;
	    }
	
	
	
	public void changeNeumatico() {
		
	}

	@Override
	public void run() {
		
		
	}

	public boolean isEstaRoto() {
		return estaRoto;
	}

	public void setEstaRoto(boolean estaRoto) {
		this.estaRoto = estaRoto;
	} 

}