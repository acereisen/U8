package de.tu_darmstadt.parking_system;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;


public class ParkingZone {
    private int zoneID;
    private BillingSystem billingSystem;
    private VehicleRegistry registry;
    private String member;
    private HashMap<String, LocalDateTime> arrivals;

    public ParkingZone(int zoneID, BillingSystem billingSystem, VehicleRegistry registry, String member) {
        this.zoneID = zoneID;
        this.billingSystem = billingSystem;
        this.registry = registry;
        this.member = member;

        arrivals = new HashMap<>();
    }

    public void processArrival(String licensePlate, LocalDateTime time) {
        if (arrivals.containsKey(licensePlate)) {
            throw new IllegalArgumentException();
        }
        arrivals.put(licensePlate, time);
    }

    public void processDeparture(String licensePlate, LocalDateTime time) {
        LocalDateTime arrivalTime = arrivals.get(licensePlate);
        if (arrivalTime == null) {
            throw new IllegalArgumentException();
        }

        long durationMinutes = Duration.between(arrivalTime, time).toMinutes();
        Vehicle vehicle = registry.getVehicle(licensePlate);
        int feesEuros = 0; // TODO: calculate actual fees
        if("easyParking".equals(member)){
            EasyParking pricing = new EasyParking(durationMinutes);
            feesEuros = pricing.calculation();
        }else if("residentParking".equals(member)){
            ResidentParking pricing = new ResidentParking(durationMinutes, vehicle, zoneID);
            feesEuros = pricing.calculation();
        }else if("HybridParking".equals(member)){
            HybridParking pricing = new HybridParking(durationMinutes, vehicle, zoneID);
            feesEuros = pricing.calculation();
        }else{
            throw new IllegalArgumentException("\n"+member+"is wrong"+"\n");
        }
        billingSystem.invoice(vehicle.owner, feesEuros);
        arrivals.remove(licensePlate);
    }
}
