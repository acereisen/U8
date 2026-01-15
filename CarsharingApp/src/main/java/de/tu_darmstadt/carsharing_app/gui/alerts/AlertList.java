package de.tu_darmstadt.carsharing_app.gui.alerts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.tu_darmstadt.carsharing_app.Alert;

public class AlertList {
    private JPanel content;
    private List<JButton> entries;
    private Consumer<Alert> onAlertRemoved;

    public AlertList(Consumer<Alert> onAlertRemoved) {
        entries = new ArrayList<>();
        this.onAlertRemoved = onAlertRemoved;

        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
    }

    public void addAlert(Alert alert) {
        JButton button = new JButton(alert.getModel() + " in " + alert.getLot());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.remove(button);
                content.revalidate();
                content.repaint();
                onAlertRemoved.accept(alert);
            }
        });
        entries.add(button);
        content.add(button);
        content.revalidate();
        content.repaint();
    }

    public JPanel getContent() {
        return content;
    }
}
