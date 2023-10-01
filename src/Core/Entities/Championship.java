package Core.Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Championship {

    private int remainingRaces;
    private Map<Integer, Integer> playerPoints; 
    private List<Circuito> circuits = new ArrayList<>();
    private List<Jugador> players = new ArrayList<>();
    private Circuito ActiveCircuit;
    public Championship(int totalRaces, List<Jugador> players) {
        this.remainingRaces = totalRaces;
        this.playerPoints = new HashMap<>();
        this.players=players;
        for (Jugador player : players) {
            playerPoints.put(player.getId(), 0);
        }
    }
    
    public List<Circuito> getCircuits() {
		return circuits;
	}

	public void setCircuits(List<Circuito> circuits) {
		this.circuits = circuits;
	}

	public List<Jugador> getPlayers() {
		return players;
	}

	public void setPlayers(List<Jugador> players) {
		this.players = players;
	}

	public Circuito getActiveCircuit() {
		return ActiveCircuit;
	}

	public void setActiveCircuit(Circuito activeCircuit) {
		ActiveCircuit = activeCircuit;
	}
    
	
    public int getRemainingRaces() {
        return remainingRaces;
    }

    public void setRemainingRaces(int remainingRaces) {
        this.remainingRaces = remainingRaces;
    }

    public Map<Integer, Integer> getPlayerPoints() {
        return playerPoints;
    }

    public void addPoints(int playerId, int points) {
        int currentPoints = playerPoints.getOrDefault(playerId, 0);
        playerPoints.put(playerId, currentPoints + points);
    }

    public void raceCompleted() {
        if (remainingRaces > 0) {
            remainingRaces--;
        }
    }

    public boolean isChampionshipOver() {
        return remainingRaces <= 0;
    }

    public int getPointsForPlayer(int playerId) {
        return playerPoints.getOrDefault(playerId, 0);
    }

    public void resetChampionship(List<Jugador> players) {
        remainingRaces = 0;
        playerPoints.clear();

        for (Jugador player : players) {
            playerPoints.put(player.getId(), 0);
        }
    }
    
    public void updatePointsBasedOnPosition(List<Jugador> jugadores) {
        int totalPlayers = jugadores.size();
        for (int i = 0; i < totalPlayers; i++) {
            Jugador jugador = jugadores.get(i);
            int position = i + 1;  // La posición comienza desde 1
            int points = totalPlayers - position + 1;  // Calcula los puntos basados en la posición
            addPoints(jugador.getId(), points);
        }
    }

	

	
    
}