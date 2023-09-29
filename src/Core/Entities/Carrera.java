package Core.Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Carrera {
    
    private Date fecha;
    private Circuito circuito;
    private CondicionCarrera condicion = new CondicionCarrera("Soleado", 20, 0);
    private int id;
    List<Jugador> jugadores = new ArrayList<>();
    List<Double> tiemposJugadores = new ArrayList<>();
    
    public Carrera(Date fecha, int id, List<Jugador> jugadores, Circuito circuito) {
        this.fecha = fecha;
        this.id = id;
        this.jugadores = jugadores;
        this.circuito = circuito;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public int getId() {
        return id;
    }
    public Circuito getCircuito() {
    	return circuito;
    }
    
    public boolean carreraEnProgreso;
    
    private void actualizaPosiciones(){
        // Ordenamos los jugadores basándonos en los tiempos totales (el jugador con el menor tiempo estará primero)
        List<Integer> indicesOrdenados = new ArrayList<>();
        for (int i = 0; i < jugadores.size(); i++) {
            indicesOrdenados.add(i);
        }
        Collections.sort(indicesOrdenados, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return tiemposJugadores.get(i1).compareTo(tiemposJugadores.get(i2));
            }
        });

        // Creamos una nueva lista de jugadores ordenados según los tiempos
        List<Jugador> jugadoresOrdenados = new ArrayList<>();
        for (Integer indice : indicesOrdenados) {
            jugadoresOrdenados.add(jugadores.get(indice));
        }

        // Actualizamos la lista de jugadores con la lista ordenada
        jugadores = jugadoresOrdenados;

        // Mostramos las posiciones actuales
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println("Posición " + (i+1) + ": " + jugadores.get(i).getNombre());
        }
    }
    
    private void verificaCondiciones(){
        if (condicion.getPrecipitaciones() > 100) {
            System.out.println("La carrera ha sido suspendida debido a precipitaciones extremas.");
            return;
        }
        
        boolean todosAutosRotos = true;
        int i = 0;
        while (todosAutosRotos && i < jugadores.size()) {
            if (!jugadores.get(i).getAuto().isEstaRoto()) {
                todosAutosRotos = false;
            }
            i++;
        }
        

        if (todosAutosRotos) {
            System.out.println("Todos los autos están rotos. La carrera ha terminado.");
            carreraEnProgreso = false;
        }
    }
    
    private void mostrarResultados(){
        // Implementa el código para mostrar los resultados finales aquí
    }
    
    public void simularCarrera() {
        // Iniciar la carrera
        System.out.println("La carrera ha comenzado en " + circuito.getNombre());
        carreraEnProgreso = true;
        for (int i = 0; i < jugadores.size(); i++) {
            tiemposJugadores.add(0.0); // Inicializa tiempos
        }

        // Simular cada vuelta
        for (int vuelta = 1; vuelta <= circuito.getcantVueltas() && carreraEnProgreso; vuelta++) {
            System.out.println("Vuelta " + vuelta);

            for (int i = 0; i < jugadores.size(); i++) {
                Jugador jugador = jugadores.get(i);
                Auto auto = jugador.getAuto();
                Piloto piloto = jugador.getPiloto();
                if (!auto.isEstaRoto()) {
                    double tiempoVuelta = auto.simularVuelta(circuito, condicion, piloto);
                    tiemposJugadores.set(i, tiemposJugadores.get(i) + tiempoVuelta);
                }
            }
            
            actualizaPosiciones();
            verificaCondiciones();
        }

        System.out.println("La carrera ha terminado!");
        mostrarResultados();
    }
}
