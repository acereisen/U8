package de.tu_darmstadt.parking_system;

import java.util.Map;

public class ResidentParking implements Strategy{
    private double duration;
    private Vehicle vehicles;
    private int zoneID;
    public ResidentParking(double duration, Vehicle vehicles, int zoneID){
        this.duration = duration;

        this.vehicles=vehicles;
        this.zoneID=zoneID;
    }
    @Override
    public int calculation(){
        if(vehicles.residentialZoneID != null && vehicles.residentialZoneID==zoneID){
            return 0;
        }
        if(duration<30){
            return 0;
        }
        return (int)(80 * Math.ceil(duration/30.0));
    }
}
