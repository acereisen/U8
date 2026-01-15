package de.tu_darmstadt.carsharing_app.gui.alerts;

import java.awt.GridLayout;
import java.util.function.Consumer;

import javax.swing.JPanel;

import de.tu_darmstadt.carsharing_app.Alert;
import de.tu_darmstadt.carsharing_app.Lot;

public class AlertPanel {
    private JPanel content;

    private AlertCreator creator;
    private AlertListPane listPane;

    public AlertPanel(Lot[] lots, String[] carModels) {
        creator = new AlertCreator(lots, carModels, new Consumer<Alert>() {
            @Override
            public void accept(Alert alert) {
                alertCreated(alert);
                listPane.getList().addAlert(alert);
            }
        });
        listPane = new AlertListPane(new Consumer<Alert>() {
            @Override
            public void accept(Alert alert) {
                alertRemoved(alert);
            }
        });

        content = new JPanel(new GridLayout(2, 1));
        content.add(creator.getContent());
        content.add(listPane.getContent());
    }

    private void alertCreated(Alert alert) {
        Lot lot = alert.getLot();
        lot.attach(alert);
        alert.update(lot);

    }

    private void alertRemoved(Alert alert) {
        Lot lot = alert.getLot();

        lot.detach(alert);

    }

    public JPanel getContent() {
        return content;
    }
}
