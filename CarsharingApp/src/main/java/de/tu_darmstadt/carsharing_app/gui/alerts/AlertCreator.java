package de.tu_darmstadt.carsharing_app.gui.alerts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import de.tu_darmstadt.carsharing_app.Alert;
import de.tu_darmstadt.carsharing_app.Lot;

public class AlertCreator implements ActionListener {
    private JPanel content;

    private JLabel header;
    private JLabel subheader;
    private JComboBox<String> modelDropdown;
    private JComboBox<Lot> lotDropdown;
    private JButton createButton;

    private Consumer<Alert> onAlertCreated;

    public AlertCreator(Lot[] lots, String[] carModels, Consumer<Alert> onAlertCreated) {
        this.onAlertCreated = onAlertCreated;
        header = new JLabel("<html><b>Create new Alerts</b></html>");
        subheader = new JLabel("Get notified when a certain model arrives in a lot");

        modelDropdown = new JComboBox<>(carModels);
        lotDropdown = new JComboBox<>(lots);

        createButton = new JButton("Create Alert");
        createButton.addActionListener(this);

        content = new JPanel();
        content.setBorder(LineBorder.createBlackLineBorder());
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(header);
        content.add(subheader);
        content.add(modelDropdown);
        content.add(lotDropdown);
        content.add(createButton);
    }

    public JPanel getContent() {
        return content;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String carModel = modelDropdown.getItemAt(modelDropdown.getSelectedIndex());
        Lot lot = lotDropdown.getItemAt(lotDropdown.getSelectedIndex());
        onAlertCreated.accept(new Alert(carModel, lot));
    }
}
