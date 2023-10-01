package Core.Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Championship {

    private int remainingRaces;
    private Map<Integer, Integer> playerPoints;
    private List<Circuit> circuits = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Circuit activeCircuit;

    public Championship(int totalRaces, List<Player> players) {
        this.remainingRaces = totalRaces;
        this.playerPoints = new HashMap<>();
        this.players = players;
        for (Player player : players) {
            playerPoints.put(player.getId(), 0);
        }
    }

    public List<Circuit> getCircuits() {
        return circuits;
    }

    public void setCircuits(List<Circuit> circuits) {
        this.circuits = circuits;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Circuit getActiveCircuit() {
        return activeCircuit;
    }

    public void setActiveCircuit(Circuit activeCircuit) {
        this.activeCircuit = activeCircuit;
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

    public void resetChampionship(List<Player> players) {
        remainingRaces = 0;
        playerPoints.clear();

        for (Player player : players) {
            playerPoints.put(player.getId(), 0);
        }
    }

    public void updatePointsBasedOnPosition(List<Player> players) {
        int totalPlayers = players.size();
        for (int i = 0; i < totalPlayers; i++) {
            Player player = players.get(i);
            int position = i + 1;  // Position starts from 1
            int points = totalPlayers - position + 1;  // Calculate points based on position
            addPoints(player.getId(), points);
        }
    }
}