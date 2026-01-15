package de.tu_darmstadt.carsharing_app.simulation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.swing.Timer;

import de.tu_darmstadt.carsharing_app.Car;
import de.tu_darmstadt.carsharing_app.CarsharingApp;
import de.tu_darmstadt.carsharing_app.Lot;

public class Simulation {
    private static final float pEvent = 0.3f;

    private List<UUID> usedUUIDs = new ArrayList<>();
    private LocalTime time;
    private StubLot[] lots;

    public Simulation(String carModels[]) {
        // Start simulation at 6 PM
        time = LocalTime.of(18, 00);

        // Create stub lots
        lots = createStubLots(carModels, time);

        // Create initial state by reserving some cars at random
        // Also reserve all cars of one model to allow demonstrating Alerts.
        Random rand = new Random();
        for (StubLot lot : lots) {
            List<Car> cars = new ArrayList<>(lot.getAvailableCars());
            for (Car car : cars) {
                if (car.getModel().equals(carModels[0]) || rand.nextBoolean()) {
                    lot.reserveCar(car);
                }
            }
        }
    }

    public UUID genUUID() {
        UUID uuid;
        do {
            uuid = UUID.randomUUID();
        } while (usedUUIDs.contains(uuid));
        return uuid;
    }

    public Car[] generateRandomCars(String[] models, int count) {
        Random rand = new Random();
        Car[] cars = new Car[count];
        for (int i = 0; i < cars.length; i++) {
            String name = models[rand.nextInt(models.length)];
            cars[i] = new Car(genUUID(), name);
        }
        return cars;
    }

    public StubLot[] createStubLots(String[] carModels, LocalTime currentTime) {
        return new StubLot[] {
            new StubLot(
                genUUID(),
                "Somestreet 1, TestCity",
                15,
                generateRandomCars(carModels, 15)
            ),   
            new GuardedLot(
                genUUID(),
                "Teststreet 42, TestCity",
                15,
                generateRandomCars(carModels, 15),
                LocalTime.of(8, 00),
                LocalTime.of(22, 00),
                currentTime
            ),
            new StubLot(
                genUUID(),
                "Random Way 17, TestCity",
                20,
                generateRandomCars(carModels, 20)
            ),
        };
    }

    public void updateLot(StubLot lot, LocalTime time) throws IllegalStateException {
        Random rand = new Random();

        if (lot instanceof GuardedLot) {
            GuardedLot guardedLot = (GuardedLot) lot;
            guardedLot.updateTime(time);
        }

        if (rand.nextDouble() >= pEvent) {
            return;
        }
        
        if (rand.nextBoolean()) {
            List<Car> cars = lot.getAvailableCars();
            if (cars.size() <= 0) {
                return;
            }

            Car c = cars.get(rand.nextInt(cars.size()));
            lot.reserveCar(c);
            System.out.println("Simulation: Reserved a " + c.getModel() + " from lot: " + lot);
        } else {
            List<Car> cars = lot.getReservedCars();
            if (cars.size() <= 0) {
                return;
            }
            Car c = cars.get(rand.nextInt(cars.size()));
            lot.returnCar(c);
            System.out.println("Simulation: Returned a " + c.getModel() + " to lot: " + lot);
        }
    }

    public void tick() {
        System.out.println("Simulation: Time is " + time);
        for (StubLot lot : lots) {
            try {
                updateLot(lot, time);
            } catch (IllegalStateException _e) { }
        }

        time = time.plusMinutes(30);
    }

    public Lot[] getLots() {
        return lots;
    }

    public static void main(String[] args) {
        // Some car models
        String carModels[] = {
            "Ford Focus (C346)",
            "Mini Roadster (R59)",
            "Fiat Multipla (186)"
        };

        Simulation sim = new Simulation(carModels);
        CarsharingApp app = new CarsharingApp(sim.getLots(), carModels);

        Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                sim.tick();
			}
        });

        app.start();
        timer.start();
    }
}
