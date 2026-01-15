package de.tu_darmstadt.carsharing_app.simulation;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.tu_darmstadt.carsharing_app.Car;

/**
 * A parking lot that is only operational during defined day-time hours.
 *
 * Similar to a regular StubLot, cars can be reserved and returned during the lot’s opening hours.
 * Outside these hours, the lot is locked, meaning that no cars may be reserved or returned.
 *
 * Consequently, the availability of cars depends on the current local time relative to
 * the lot’s configured opening and closing times.
 */
public class GuardedLot extends StubLot {
    private LocalTime openTime;
    private LocalTime closeTime;
    private LocalTime currentTime;

    public GuardedLot(UUID uuid, String address, int capacity, Car[] cars, LocalTime openTime, LocalTime closeTime,LocalTime currentTime) {
        super(uuid, address, capacity, cars);
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.currentTime = currentTime;
    }

    public void updateTime(LocalTime time) {
        this.currentTime = time;
    }

    public void reserveCar(Car car) throws IllegalStateException {
        if (currentTime.isAfter(openTime) && currentTime.isBefore(closeTime)) {
            super.reserveCar(car);
        } else {
            throw new IllegalStateException("Lot is closed!");
        }
        notifyObservers();
    }

    public void returnCar(Car car) throws IllegalStateException {
        if (currentTime.isAfter(openTime) && currentTime.isBefore(closeTime)) {
            super.returnCar(car);
        } else {
            throw new IllegalStateException("Lot is closed!");
        }
        notifyObservers();
    }

    @Override
    public List<Car> getAvailableCars() {
        if (currentTime.isAfter(openTime) && currentTime.isBefore(closeTime)) {
            return super.getAvailableCars();
        } else {
            return new ArrayList<>();
        }
    }
}
