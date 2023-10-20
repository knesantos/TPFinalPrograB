package Core.Entities;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Driver {

    private long budget;
    private int defense;
    private int overtaking;
    private int ranking;
    private int tireCare;
    private int start;
    private String name;
    private String shortName;

    private int racesWon;
    private int polePositions;
    private int championships;
    private int participations;
    private Image avatar;
    private String avatarPath;


    public Driver(long budget, int defense, int overtaking, int ranking, int tireCare, int start,
                  String name, String shortName, int racesWon, int polePositions, int championships,
                  int participations) {
        this.budget = budget;
        this.defense = defense;
        this.overtaking = overtaking;
        this.ranking = ranking;
        this.tireCare = tireCare;
        this.start = start;
        this.name = name;
        this.shortName = shortName;
        this.racesWon = racesWon;
        this.polePositions = polePositions;
        this.championships = championships;
        this.participations = participations;
    }

    public Driver() {
        this.budget = 1000000;
        this.defense = 80;
        this.overtaking = 100;
        this.ranking = 1;
        this.tireCare = 100;
        this.start = 100;
        this.name = "Santi";
        this.shortName = "SAN";
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


    public int getRanking() {
        return ranking;
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


    public String getShortName() {
        return shortName;
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


    public void setRanking(int ranking) {
        this.ranking = ranking;

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

    public void setShortName(String shortName) {
        this.shortName = shortName;

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

	
	public Image getAvatar() {
        return avatar;
    }
	
	public String getAvatarPath() {
        return avatarPath;
    }
	
	public void setAvatar(String imagePath) {
        this.avatarPath = imagePath;
        File f = new File(imagePath);
        if(f.exists() && !f.isDirectory()) { 
            System.out.println("Imagen existe: " + imagePath);
        } else {
            System.out.println("Imagen no existe: " + imagePath);
        }
        // Cargar la imagen en el atributo avatar
        ImageIcon imageIcon = new ImageIcon(imagePath);
        this.avatar = imageIcon.getImage();
    }

	public void setAvatarPath(String imagePath) {
		this.avatarPath =imagePath;
	}
}

