package Core.Entities;

import java.io.Serializable;

public class Circuit implements Serializable {

    private int lapCount, curveCount, overtakingZoneCount;
    private long length, fastestLapRecord;
    private Country country;
    private String name;
    
    public Circuit(int lapCount, int curveCount, int overtakingZoneCount, long length, long fastestLapRecord, String name) {
        this.curveCount = curveCount;
        this.overtakingZoneCount = overtakingZoneCount;

        this.lapCount = lapCount;
        this.length = length;
        this.fastestLapRecord = fastestLapRecord;
        this.name = name;
    }

    
    public int getCurveCount() {
        return curveCount;
    }
    
    public int getLapCount() {
        return lapCount;
    }
    
    public int getOvertakingZoneCount() {
        return overtakingZoneCount;
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

