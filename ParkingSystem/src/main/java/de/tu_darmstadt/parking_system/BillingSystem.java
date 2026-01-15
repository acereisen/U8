package de.tu_darmstadt.parking_system;

public class BillingSystem {
    public void invoice(String recipient, int amount) {
        System.out.println("Billing " + amount + " € to: " + recipient);
    }
}

