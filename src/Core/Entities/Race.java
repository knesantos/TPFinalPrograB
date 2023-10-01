package Core.Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Race {
    
    private Date date;
    private Circuit circuit;
    private RaceCondition condition = new RaceCondition("Sunny", 20, 0);
    private int id;
    
    List<Player> players = new ArrayList<>();
    List<Double> playersTimes = new ArrayList<>();

    public Race(Date date, int id, List<Player> players, Circuit circuit) {
        this.date = date;
        this.id = id;
        this.players = players;
        this.circuit = circuit;
    }

    public Date getDate() {
        return date;
    }
    
    public int getId() {
        return id;
    }
    
    public boolean raceInProgress;
    
    private void updatePositions() {
        // Ordenamos los jugadores basándonos en los tiempos totales (el jugador con el menor tiempo estará primero)
    	List<Integer> sortedIndices = new ArrayList<>();
    	for (int i = 0; i < players.size(); i++) {
            sortedIndices.add(i);
        }
        Collections.sort(sortedIndices, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return playersTimes.get(i1).compareTo(playersTimes.get(i2));
            }
        });


        // Creamos una nueva lista de jugadores ordenados según los tiempos
        List<Player> sortedPlayers = new ArrayList<>();
        for (Integer index : sortedIndices) {
            sortedPlayers.add(players.get(index));
        }

        // Actualizamos la lista de jugadores con la lista ordenada
        players = sortedPlayers;
        
        // Mostramos las posiciones actuales
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Position " + (i + 1) + ": " + players.get(i).getName());
        }
    }
    
 
    private void checkConditions() {
    	
	        if (condition.getPrecipitation() > 100) {
	            System.out.println("The race has been suspended due to extreme precipitation.");
	            return;
	        }
	        
        boolean allCarsBroken = true;
        int i = 0;
        
        while (allCarsBroken && i < players.size()) {
        	
	        if (!players.get(i).getCar().isBroken()) {
	            allCarsBroken = false;
	        }
	        
	        i++;
        }
        

        if (allCarsBroken) {
            System.out.println("All cars are broken. The race is over.");
            raceInProgress = false;
        }
    }
    
    private void showResults(){
        // Implementa el código para mostrar los resultados finales aquí
    }
    
    public void simulateRace() {
        System.out.println("The race has started at " + circuit.getName());
        raceInProgress = true;
        for (int i = 0; i < players.size(); i++) {
            playersTimes.add(0.0); // Initialize times
        }

        // Simular cada vuelta
        for (int lap = 1; lap <= circuit.getLapCount() && raceInProgress; lap++) {
            System.out.println("Lap " + lap);

            for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                Car car = player.getCar();
                Driver driver = player.getDriver();
                if (!car.isBroken()) {
                    double lapTime = car.simulateLap(circuit, condition, driver);
                    playersTimes.set(i, playersTimes.get(i) + lapTime);
                }
            }
            
            updatePositions();
            checkConditions();
        }

        System.out.println("The race has ended!");
        showResults();
    }
}