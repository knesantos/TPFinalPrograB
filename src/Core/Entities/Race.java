package Core.Entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.concurrent.CountDownLatch;


public class Race {
    
    private Date date;
    private int actualLap=0;
    private Circuit circuit;
    private RaceCondition condition = new RaceCondition("Sunny", 20, 0);
    private int id;
    List<Player> players = new ArrayList<>();
    Map<Integer, Double> playerTimes = new HashMap<>();
    private volatile boolean stopSimulation = false; 
    private boolean realPlayerPitStop = false;
    private CountDownLatch pitStopLatch = null;
    private Real realPlayer = null;

    public Race(Date date, int id, List<Player> players, Circuit circuit) {
        this.date = date;
        this.id = id;
        this.players = players;
        this.circuit = circuit;
        this.condition = generateRandomCondition();
    }

    private RaceCondition generateRandomCondition() {
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;

        if (chance <= 50) {
            return new RaceCondition("Sunny", 20, 0);
        } else if (chance <= 80) {
            return new RaceCondition("Cloudy", 15, 10);
        } else if (chance <= 95) {
            return new RaceCondition("Rainy", 10, 80);
        } else {
            return new RaceCondition("Stormy", 5, 100);
        }
    }
    

    public Date getDate() {
        return date;
    }
    
    public int getId() {
        return id;
    }
    

    public Circuit getCircuit() {
        return circuit;
    }
    
    public List<Player> getPlayers() {
        return players;
    }
    
    public Player getRealPlayer() {
        for (Player player : players) {
            if (player instanceof Real) {  
                return player;
            }
        }
        return null;  // Devuelve null si no hay jugadores reales
    }
    
    public Map<Integer, Double> getTimes() {
        return playerTimes;
    }
    
    public RaceCondition getRaceCondition() {
        return condition;
    }
    
    public boolean raceInProgress;
    
    private void updatePositions() {
        // Sort players based on kilometers driven and total times
        List<Player> sortedPlayers = players.stream()
            .sorted((p1, p2) -> {
                Double km1 = p1.getCar().getmetersDriven();
                Double km2 = p2.getCar().getmetersDriven();
                Double time1 = playerTimes.getOrDefault(p1.getId(), Double.MAX_VALUE);
                Double time2 = playerTimes.getOrDefault(p2.getId(), Double.MAX_VALUE);

                int kmComparison = km2.compareTo(km1);  // Nota: km2 vs km1 para ordenar de mayor a menor
                int timeComparison = time1.compareTo(time2);

                if (kmComparison == 0) {
                    return timeComparison;
                } else {
                    return kmComparison;
                }
            })
            .collect(Collectors.toList());

        // Update the player list with the sorted list
        players = sortedPlayers;

        // Display current positions
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Position " + (i + 1) + ": " + players.get(i).getName());
        }
    }
    

    private void checkConditions() {
        if (condition.getPrecipitation() > 100) {
            System.out.println("The race has been suspended due to extreme precipitation.");
            return;
        }
        
        boolean allCarsBroken = true;  // Iniciar como true
        int i = 0;
        while (allCarsBroken && i < players.size()) {
            if (!players.get(i).getCar().isBroken()) {
                allCarsBroken = false;  // Establecer como false si un auto que no está roto
            }
            i++;
        }
        

        if (allCarsBroken) {
            System.out.println("All cars are broken. The race is over.");
            raceInProgress = false;
        }
    }
    

    public void simulateRace() {
        // Start the race
        System.out.println("The race has started on " + circuit.getName());
        raceInProgress = true;
        
        for (Player player : players) {
            playerTimes.put(player.getId(), 0.0);
            player.getCar().initializeCarForRace();
        }

        // Simulate each lap
        int lap;
        for (lap = 1; lap <= circuit.getLapCount() && raceInProgress && !stopSimulation; lap++) {
            System.out.println("Lap " + lap);
            setActualLap(lap);
           
            //Sim IA
            for (Player player : players) {
                if (player instanceof Simulated) {  
                    ((Simulated) player).SimIA(this);  
                }
            }
            
            //Change condition
            if (new Random().nextInt(100) < 10) {
                this.condition = generateRandomCondition();
                System.out.println("Weather condition has changed to: " + this.condition.getCondition());
            }
            
            CountDownLatch latch = new CountDownLatch(players.size());
            for (Player player : players) {
                new Thread(() -> {
                    Car car = player.getCar();
                    if (!car.isBroken()) {
                    	car.setActualLap(getActualLap());
                        car.run();
                        double lapTime = car.getLapTime();  
                        synchronized (playerTimes) {
                            int playerId = player.getId();
                            double currentTime = playerTimes.getOrDefault(playerId, 0.0); 
                            playerTimes.put(playerId, currentTime + lapTime);
                        }
                    }
                    else {
                    	System.out.println("El coche de " + player.getName() + " esta roto y no corrio la vuelta" + getActualLap());
                    	 
                    }
                    
                    latch.countDown();
                }).start();
            }
            if(realPlayerPitStop) {
            	
                pitStopLatch = new CountDownLatch(1);  
                try {
                	getRealPlayer().getCar().addLapTime(20);
                    pitStopLatch.await();  
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                latch.await();
                Thread.sleep(90); // Sleep for 1000 milliseconds to slow down the simulation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            updatePositions();
            checkConditions();
        }
        System.out.println("The race is over!");
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }
    
    public void stopSimulation() {
        stopSimulation = true;
    }



	public int getActualLap() {
		return actualLap;
	}



	public void setActualLap(int actualLap) {
		this.actualLap = actualLap;
	}
	
	
	public CountDownLatch getPitStopLatch() {
		return pitStopLatch;
	}



	public void setPitStopLatch(CountDownLatch pitStopLatch) {
		this.pitStopLatch = pitStopLatch;
	}

	public boolean isRealPlayerPitStop() {
		return realPlayerPitStop;
	}

	public void setRealPlayerPitStop(boolean realPlayerPitStop) {
		this.realPlayerPitStop = realPlayerPitStop;
	}
}