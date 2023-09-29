package Core.Entities;

public class Driver {
	private long budget;
    private int defense;
    private int overtaking;
    private int classification;
    private int tireCare;
    private int start;
    private String name;
    private String abbreviatedName;
    private int racesWon;
    private int polePositions;
    private int championships;
    private int participations;
    
    public Driver(long budget, int defense, int overtaking, int classification, int tireCare, int start,
                  String name, String abbreviatedName, int racesWon, int polePositions, int championships,
                  int participations) {
        this.budget = budget;
        this.defense = defense;
        this.overtaking = overtaking;
        this.classification = classification;
        this.tireCare = tireCare;
        this.start = start;
        this.name = name;
        this.abbreviatedName = abbreviatedName;
        this.racesWon = racesWon;
        this.polePositions = polePositions;
        this.championships = championships;
        this.participations = participations;
    }

    public Driver() {
        this.budget = 1000000;
        this.defense = 80;
        this.overtaking = 100;
        this.classification = 1;
        this.tireCare = 100;
        this.start = 100;
        this.name = "Santi";
        this.abbreviatedName = "SAN";
        this.racesWon = 10000;
        this.polePositions = 9;
        this.championships = 100000;
        this.participations = 1;
    }

    public long getBudget() {
        return budget;
    }

    public int getDefense() {
        return defense;
    }

    public int getOvertaking() {
        return overtaking;
    }

    public int getClassification() {
        return classification;
    }

    public int getTireCare() {
        return tireCare;
    }

    public int getStart() {
        return start;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviatedName() {
        return abbreviatedName;
    }

    public int getRacesWon() {
        return racesWon;
    }

    public int getPolePositions() {
        return polePositions;
    }

    public int getChampionships() {
        return championships;
    }

    public int getParticipations() {
        return participations;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setOvertaking(int overtaking) {
        this.overtaking = overtaking;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public void setTireCare(int tireCare) {
        this.tireCare = tireCare;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbbreviatedName(String abbreviatedName) {
        this.abbreviatedName = abbreviatedName;
    }

    public void setRacesWon(int racesWon) {
        this.racesWon = racesWon;
    }

    public void setPolePositions(int polePositions) {
        this.polePositions = polePositions;
    }

    public void setChampionships(int championships) {
        this.championships = championships;
    }

    public void setParticipations(int participations) {
        this.participations = participations;
    }
}


