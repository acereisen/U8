package de.tu_darmstadt.parking_system;

public class Vehicle {
    /***
     * License plate including spaces.
     * Example: "TC AB 1234"
     */
    public final String licensePlate;

    /***
     * Owner of the vehicle
     */
    public final String owner;

    /***
     * ID of the parking zone the vehicle is registered as a
     * residential vehicle to.
     *
     * `null` if the vehicle is not registered as residential.
     */
    public final Integer residentialZoneID;

    public Vehicle(String licensePlate, String owner, Integer residentialZoneID) {
        this.licensePlate = licensePlate;
        this.owner = owner;
        this.residentialZoneID = residentialZoneID;
    }
}
