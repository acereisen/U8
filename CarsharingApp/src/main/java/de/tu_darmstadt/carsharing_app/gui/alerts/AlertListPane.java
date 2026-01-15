package de.tu_darmstadt.carsharing_app.gui.alerts;

import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import de.tu_darmstadt.carsharing_app.Alert;

class AlertListPane {
    private JPanel content;

    private AlertList list;
    private JScrollPane scrollList;
    private JLabel header;
    private JLabel subheader;

    public AlertListPane(Consumer<Alert> onAlertRemoved) {
        header = new JLabel("<html><b>Active Alerts</b></html>");
        subheader = new JLabel("Click an alert to remove it");

        list = new AlertList(onAlertRemoved);
        scrollList = new JScrollPane(list.getContent());

        content = new JPanel();
        content.setBorder(LineBorder.createBlackLineBorder());
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(header);
        content.add(subheader);
        content.add(scrollList);
    }

    public AlertList getList() {
        return list;
    }

    public JPanel getContent() {
        return content;
    }
}
