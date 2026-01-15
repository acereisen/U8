package de.tu_darmstadt.carsharing_app.gui;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.tu_darmstadt.carsharing_app.Car;
import de.tu_darmstadt.carsharing_app.Lot;
import de.tu_darmstadt.carsharing_app.Visualization;

public class CarList implements Visualization {
    private JPanel content;
    private Lot lot;

    public CarList(Lot lot) {
        this.lot = lot;
        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
    }

    public JPanel getContent() {
        return content;
    }

    @Override
    public void update(Lot lot) {
        List<Car> cars = lot.getAvailableCars();
        content.removeAll();
        for (Car car : cars) {
            JButton button = new JButton("<html><b>" + car.getModel() + "</b><br>UUID: " + car.getUUID() + "</html>");
            button.setEnabled(false);
            content.add(button);
        }
        content.revalidate();
        content.repaint();
    }

    public Lot getLot() {
        return lot;
    }
}
