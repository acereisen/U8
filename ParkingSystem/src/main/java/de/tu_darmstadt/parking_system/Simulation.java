package de.tu_darmstadt.parking_system;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Simulation {
    public static void main(String[] args) {
        Vehicle[] vehicles = {
            new Vehicle("TC AM 0000", "Archie May", 0),
            new Vehicle("TC LM 0000", "Luke Mitchell", 1),
            new Vehicle("TC JL 0000", "Joseph Lloyd", null),
            new Vehicle("TC GB 0000", "George Baxter", 4),
            new Vehicle("TC RT 0000", "Riley Thompson", null)
        };
        System.out.println("hier1");
        ParkingZone testZone = new ParkingZone(
            0,
            new BillingSystem(),

            new VehicleRegistry(vehicles),"easyParking"
        );

        // All vehicles arrive at 2025-Oct-1, 08:00 (AM)
        LocalDateTime now = LocalDate.of(2025, 10, 1).atTime(8, 00); 
        for (Vehicle v : vehicles) {
            testZone.processArrival(v.licensePlate, now);
        }

        // 08:02 (AM): George Baxter departs
        now = LocalDate.of(2025, 10, 1).atTime(8, 02); 
        testZone.processDeparture(vehicles[3].licensePlate, now);

        // 08:25 (AM): Riley Thompson departs
        now = LocalDate.of(2025, 10, 1).atTime(8, 25); 
        testZone.processDeparture(vehicles[4].licensePlate, now);

        // 09:00 (AM): Archie May and Luke Mitchell depart, George Baxter arrives
        now = LocalDate.of(2025, 10, 1).atTime(9, 00); 
        testZone.processDeparture(vehicles[0].licensePlate, now);
        testZone.processDeparture(vehicles[1].licensePlate, now);
        testZone.processArrival(vehicles[3].licensePlate, now);

        // 18:00 : Joseph Lloyd and George Baxter depart
        now = LocalDate.of(2025, 10, 1).atTime(18, 00); 
        testZone.processDeparture(vehicles[2].licensePlate, now);
        testZone.processDeparture(vehicles[3].licensePlate, now);
    }
}
