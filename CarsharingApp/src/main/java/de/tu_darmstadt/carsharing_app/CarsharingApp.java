package de.tu_darmstadt.carsharing_app;

import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import de.tu_darmstadt.carsharing_app.gui.alerts.AlertPanel;
import de.tu_darmstadt.carsharing_app.gui.CarPanel;
import de.tu_darmstadt.carsharing_app.gui.LotPanel;

public class CarsharingApp {
    private LotPanel lotPanel;
    private CarPanel carPanel;
    private AlertPanel alertPanel;
    private JFrame frame = new JFrame();

    public CarsharingApp(Lot[] lots, String[] carModels) {
        frame = new JFrame("Carsharing App");

        carPanel = new CarPanel();

        lotPanel = new LotPanel(lots, new Consumer<Lot>() {
            @Override
            public void accept(Lot lot) {
                carPanel.showLot(lot);
            }
        });

        alertPanel = new AlertPanel(lots, carModels);

        frame.setSize(1280, 720);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        frame.add(lotPanel.getContent());
        frame.add(carPanel.getContent());
        frame.add(alertPanel.getContent());
    }

    public void start() {
        // Show the GUI
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
