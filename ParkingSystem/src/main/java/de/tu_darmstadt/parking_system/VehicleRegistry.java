package de.tu_darmstadt.parking_system;

import java.util.HashMap;
import java.util.Map;

public class VehicleRegistry {
    private Map<String, Vehicle> vehicles;

    public VehicleRegistry(Vehicle[] vehicles) {
        this.vehicles = new HashMap<>();
        for (Vehicle vehicle : vehicles) {
            if (this.vehicles.containsKey(vehicle.licensePlate)) {
                throw new IllegalArgumentException();
            }
            this.vehicles.put(vehicle.licensePlate, vehicle);
        }

    }

    public Vehicle getVehicle(String license_plate) {
        return this.vehicles.get(license_plate); 
    }
}
