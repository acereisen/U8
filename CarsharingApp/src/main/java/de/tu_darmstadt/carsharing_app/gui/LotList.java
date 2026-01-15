package de.tu_darmstadt.carsharing_app.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.tu_darmstadt.carsharing_app.Car;
import de.tu_darmstadt.carsharing_app.Lot;
import de.tu_darmstadt.carsharing_app.Visualization;

public class LotList implements Visualization {
    private JPanel content;
    private Map<Lot, JButton> entries;
    
    public LotList(Lot[] lots, Consumer<Lot> onLotSelection) {
        content = new JPanel();
        content.setLayout(new GridLayout(10, 1));
        entries = new HashMap<>();
        for (Lot lot : lots) {
            JButton button = new JButton("<html>" + lot.getAddress() + "<br>Available Cars: ?/" + lot.getCapacity() + "</html>");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onLotSelection.accept(lot);
                }
            });

            entries.put(lot, button);
            content.add(button);
        }
    }

    public JPanel getContent() {
        return content;
    }

    @Override
    public void update(Lot lot) {
        List<Car> cars = lot.getAvailableCars();
        entries.get(lot).setText("<html>" + lot.getAddress() + "<br>Available Cars: " + cars.size() + "/" + lot.getCapacity() + "</html>");
    }
}
