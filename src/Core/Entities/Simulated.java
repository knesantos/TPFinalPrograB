package Core.Entities;

import java.util.Random;
import java.util.List;

public class Simulated extends Player {

    public Simulated() {
        super();
    }

    public Simulated(String name, int id) {
        super(name, id);
    }

    public void SimIA(Race race) {
        Random rand = new Random();
        Driver driver = getDriver();
        Car car = getCar();  

        // Decisiones basadas en condiciones climáticas
        RaceCondition condition = race.getRaceCondition();
        if (condition.getCondition().equalsIgnoreCase("Rainy")) {
           
        }

        // Decisiones basadas en el modo de conducción y la vuelta actual
        int actualLap = race.getActualLap();
        int totalLaps = race.getCircuit().getLapCount();
        List<Player> players = race.getPlayers();
        int myPosition = players.indexOf(this) + 1;
        String goodOptionDrivingMode;
        String badOptionDrivingMode;

        boolean goodOption = rand.nextInt(100) >= 70;// Esto determina si se toma la "buena" o la "mala" decisión

        if (myPosition <= players.size() / 2) {  // En la mitad superior
            if (totalLaps - actualLap <= 5) {  // Últimas 5 vueltas
                goodOptionDrivingMode = "Defensive";
                badOptionDrivingMode = "Agressive";
            } else if (totalLaps - actualLap > 20) {  // Muchas vueltas restantes
                goodOptionDrivingMode = "Moderated";
                badOptionDrivingMode = "Agressive";
            } else {
                goodOptionDrivingMode = "Defensive";
                badOptionDrivingMode = "Agressive";
            }
        } else {  // En la mitad inferior
            if (totalLaps - actualLap <= 5) {  // Últimas 5 vueltas
                goodOptionDrivingMode = "Agressive";
                badOptionDrivingMode = "Defensive";
            } else if (totalLaps - actualLap > 20) {  // Muchas vueltas restantes
                goodOptionDrivingMode = "Moderated";
                badOptionDrivingMode = "Defensive";
            } else {
                goodOptionDrivingMode = "Agressive";
                badOptionDrivingMode = "Defensive";
            }
        }
        
       
        if (goodOption) {
            this.setDrivingMode(new DrivingMode(goodOptionDrivingMode));
        } else {
            this.setDrivingMode(new DrivingMode(badOptionDrivingMode));
        }


        // Decisiones sobre si parar o no en pits la próxima vuelta
        goodOption = rand.nextInt(100) >= 70;
        boolean needPitStop = false;
        if (driver.getTireCare() < 50 || car.getHealth() < 30 || car.getFuel() < 30) {
            needPitStop = true;
        }
        if (needPitStop && goodOption ) {
            car.setNeedPits(true); 
        } else {
            car.setNeedPits(false);
        }
    }
}