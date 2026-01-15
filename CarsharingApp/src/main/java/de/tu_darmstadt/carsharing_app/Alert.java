package de.tu_darmstadt.carsharing_app;

public class Alert implements Visualization {
    private String carModel;
    private Lot lot;
    private boolean executed = false;
    public Alert(String carModel, Lot lot) {
        this.carModel = carModel;
        this.lot = lot;
    }
    @Override
    public void update(Lot updatedLot) {
        if (executed || !updatedLot.equals(lot)) {
            return;
        }

        for (Car car : updatedLot.getAvailableCars()) {
            if (carModel.equals(car.getModel())) {

                System.out.println(
                        "ALERT: " + carModel +
                                " available in lot: " + updatedLot.getAddress()
                );

                executed = true;
                break;
            }
        }
    }

    public String getModel() {
        return carModel;
    }

    public Lot getLot() {
        return lot;
    }
}
