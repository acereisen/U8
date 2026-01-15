package de.tu_darmstadt.carsharing_app.gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import de.tu_darmstadt.carsharing_app.Lot;

public class CarPanel {
    private JPanel content;

    private JLabel header;
    private JLabel subheader;
    private JScrollPane scrollList;
    private CarList list;

    public CarPanel() {
        header = new JLabel("<html><b>Select a lot to view available cars</b></html>");
        subheader = new JLabel("");
        scrollList = new JScrollPane();
        list = null;

        content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(LineBorder.createBlackLineBorder());
        content.add(header);
        content.add(subheader);
        content.add(scrollList);
    }

    public void showLot(Lot lot) {
        if (list != null) {
            Lot oldLot = list.getLot();
            oldLot.detach(list);
        }

        list = new CarList(lot);

        lot.attach(list);

        list.update(lot);

        scrollList.setViewportView(list.getContent());
        header.setText("<html><b>Available Cars in Lot: " + lot + "</b></html>");

        content.revalidate();
        content.repaint();
    }

    public JPanel getContent() {
        return content;
    }
}
