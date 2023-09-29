package Core.Entities;

public class Circuit {
	private int lapCount, numberOfCurves, numberOfOvertakingZones;
    private long length, fastestLapRecord;
    private Country country;
    private String name;
	
    public Circuit(int lapCount, int numberOfCurves, int numberOfOvertakingZones, long length, long fastestLapRecord, String name) {
        this.numberOfCurves = numberOfCurves;
        this.numberOfOvertakingZones = numberOfOvertakingZones;
        this.lapCount = lapCount;
        this.length = length;
        this.fastestLapRecord = fastestLapRecord;
        this.name = name;
    }

    public Country getCountry() {
    	return country;
    }
    public int getNumberOfCurves() {
        return numberOfCurves;
    }

    public int getLapCount() {
        return lapCount;
    }

    public int getNumberOfOvertakingZones() {
        return numberOfOvertakingZones;
    }

    public long getLength() {
        return length;
    }

    public long getFastestLapRecord() {
        return fastestLapRecord;
    }

    public String getName() {
        return name;
    }
}
