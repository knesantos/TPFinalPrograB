package Core.Entities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JList;

public class Carrera {
	
	private Date fecha;
	private Circuito circuito;
	private CondicionCarrera condicion;
	private int id;
	List<Auto> autos = new ArrayList<>();
	List<Double> tiemposAutos = new ArrayList<>();
	
	public Carrera(Date fecha, int id,List<Auto> autos,Circuito circuito) {
		this.fecha = fecha;
		this.id = id;
		this.autos=autos;
		this.circuito=circuito;
			
	}
	public Date getFecha() {
		return fecha;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean carreraEnProgreso;
	
	
	private void actualizaPosiciones(){
	    // Ordenamos los autos basándonos en los tiempos totales (el auto con el menor tiempo estará primero)
	    List<Integer> indicesOrdenados = new ArrayList<>();
	    for (int i = 0; i < autos.size(); i++) {
	        indicesOrdenados.add(i);
	    }
	    Collections.sort(indicesOrdenados, new Comparator<Integer>() {
	        public int compare(Integer i1, Integer i2) {
	            return tiemposAutos.get(i1).compareTo(tiemposAutos.get(i2));
	        }
	    });

	    // Creamos una nueva lista de autos ordenados según los tiempos
	    List<Auto> autosOrdenados = new ArrayList<>();
	    for (Integer indice : indicesOrdenados) {
	        autosOrdenados.add(autos.get(indice));
	    }

	    // Actualizamos la lista de autos con la lista ordenada
	    autos = autosOrdenados;

	    // Mostramos las posiciones actuales
	    for (int i = 0; i < autos.size(); i++) {
	        System.out.println("Posición " + (i+1) + ": " + autos.get(i).getMarca() + " " + autos.get(i).getModelo());
	    }
	}
	
	private void verificaCondiciones(){
		 if (condicion.getPrecipitaciones() > 100) { // Asumiendo que 100 es el límite para precipitaciones extremas
	            System.out.println("La carrera ha sido suspendida debido a precipitaciones extremas.");
	            return;
	        }
	}
	
	private void mostrarResultados(){
		
	}
	
	public void simularCarrera() {
        // Iniciar la carrera
        System.out.println("La carrera ha comenzado en " + circuito.getNombre());
        carreraEnProgreso = true;
        for (int i = 0; i < autos.size(); i++) {
            tiemposAutos.add(0.0);//Inicializa tiempos
        }

        // Simular cada vuelta
        for (int vuelta = 1; vuelta <= circuito.getcantVueltas() && carreraEnProgreso; vuelta++) {
            System.out.println("Vuelta " + vuelta);

            for (int i = 0; i < autos.size(); i++) {
                Auto auto = autos.get(i);
                Piloto piloto =new Piloto();
                double tiempoVuelta = auto.simularVuelta(circuito, condicion,piloto);
                tiemposAutos.set(i, tiemposAutos.get(i) + tiempoVuelta);
            }
            
            actualizaPosiciones();
            verificaCondiciones();
        }

        System.out.println("La carrera ha terminado!");
        mostrarResultados();
    }

}
