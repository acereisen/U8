package de.tu_darmstadt.carsharing_app;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Lot {
    private final UUID uuid;
    private final String address;
    private final int capacity;

    private CopyOnWriteArrayList<Visualization> observers;

    public Lot(UUID uuid, String address, int capacity) {
        this.uuid = uuid;
        this.address = address;
        this.capacity = capacity;
        this.observers = new CopyOnWriteArrayList<>();
    }


    public abstract List<Car> getAvailableCars();
    public void attach(Visualization v){
        if(v==null){
            throw new IllegalArgumentException("Visualization must not be null");
        }
        observers.addIfAbsent(v);
    }
    public void detach(Visualization v){
        observers.remove(v);
    }
    protected void notifyObservers(){
        for(Visualization v: observers){
            v.update(this);
        }
    }
    public int getCapacity() {
        return capacity;
    }

    public String getAddress() {
        return address;
    }

    public UUID getUUID() {
        return uuid;
    }

    @Override
    public String toString() {
        return address;
    }

    @Override
    public int hashCode() {
        return getUUID().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Lot)) return false;

        Lot other = (Lot)obj;
        return getUUID().equals(other.getUUID());
    }
}
