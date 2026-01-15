package de.tu_darmstadt.carsharing_app;

import java.util.UUID;

public class Car{
    private final UUID uuid;
    private final String model;

    public Car(UUID uuid, String model) {
        this.uuid = uuid;
        this.model = model;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getModel() {
        return model;
    }
}
