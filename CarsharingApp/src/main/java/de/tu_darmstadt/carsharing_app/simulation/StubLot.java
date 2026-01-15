package de.tu_darmstadt.carsharing_app.simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import de.tu_darmstadt.carsharing_app.Car;
import de.tu_darmstadt.carsharing_app.Lot;

public class StubLot extends Lot {
    private List<Car> availableCars;
    private List<Car> reservedCars;

    public StubLot(UUID uuid, String address, int capacity, Car[] cars) {
        super(uuid, address, capacity);
        availableCars = new ArrayList<>(Arrays.asList(cars));
        reservedCars = new ArrayList<>();
    }

    public void reserveCar(Car car) throws IllegalStateException {
        if (!availableCars.remove(car)) {
            throw new IllegalStateException("Car not available");
        }
        reservedCars.add(car);
        notifyObservers();
    }

    public void returnCar(Car car) throws IllegalStateException {
        if (!reservedCars.remove(car)) {
            throw new IllegalStateException("Car not currently reserved");
        }
        availableCars.add(car);
        notifyObservers();
    }

    @Override
    public List<Car> getAvailableCars() {
        return availableCars;
    }

    public List<Car> getReservedCars() {
        return reservedCars;
    }
}
