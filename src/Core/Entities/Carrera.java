package Core.Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Carrera {
    
    private Date fecha;
    private Circuito circuito;
    private CondicionCarrera condicion = new CondicionCarrera("Soleado", 20, 0);
    private int id;
    List<Jugador> jugadores = new ArrayList<>();
    Map<Integer, Double> tiemposJugadores = new HashMap<>();
    
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
    
    public List<Jugador> getPlayers() {
    	return jugadores;
    }
    
    public Map<Integer, Double> getTimes() {
    	return tiemposJugadores;
    }
    
    
    public boolean carreraEnProgreso;
    
    private void actualizaPosiciones() {
        // Ordenamos los jugadores basándonos en los tiempos totales (el jugador con el menor tiempo estará primero)
        List<Jugador> jugadoresOrdenados = jugadores.stream()
            .sorted((j1, j2) -> {
                Double tiempo1 = tiemposJugadores.getOrDefault(j1.getId(), Double.MAX_VALUE);
                Double tiempo2 = tiemposJugadores.getOrDefault(j2.getId(), Double.MAX_VALUE);
                return tiempo1.compareTo(tiempo2);
            })
            .collect(Collectors.toList());

        // Actualizamos la lista de jugadores con la lista ordenada
        jugadores = jugadoresOrdenados;

        // Mostramos las posiciones actuales
        for (int i = 0; i < jugadores.size(); i++) {
            System.out.println("Posición " + (i + 1) + ": " + jugadores.get(i).getNombre());
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
        
        for (Jugador jugador : jugadores) {
            tiemposJugadores.put(jugador.getId(), 0.0);  // Inicializa tiempos
        }

        // Simular cada vuelta
        for (int vuelta = 1; vuelta <= circuito.getcantVueltas() && carreraEnProgreso; vuelta++) {
            System.out.println("Vuelta " + vuelta);

            for (Jugador jugador : jugadores) {
                Auto auto = jugador.getAuto();
                Piloto piloto = jugador.getPiloto();
                if (!auto.isEstaRoto()) {
                    double tiempoVuelta = auto.simularVuelta(circuito, condicion, piloto);
                    double tiempoActual = tiemposJugadores.get(jugador.getId());
                    tiemposJugadores.put(jugador.getId(), tiempoActual + tiempoVuelta);
                }
            }
            
            actualizaPosiciones();
            verificaCondiciones();
        }

        System.out.println("La carrera ha terminado!");
        mostrarResultados();
    }
}
