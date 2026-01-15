package de.tu_darmstadt.carsharing_app.gui;

import java.util.function.Consumer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import de.tu_darmstadt.carsharing_app.Lot;

public class LotPanel {
    private JPanel content;

    private JLabel header;
    private JLabel subheader;
    private JScrollPane scrollList;
    private LotList list;

    public LotPanel(Lot[] lots, Consumer<Lot> onLotSelection) {
        header = new JLabel("<html><b>Available Lots</b></html>");
        subheader = new JLabel("Click on a lot to select it.");
        list = new LotList(lots, onLotSelection);
        scrollList = new JScrollPane(list.getContent());

        content = new JPanel();
        content.setBorder(LineBorder.createBlackLineBorder());
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(header);
        content.add(subheader);
        content.add(scrollList);
        for (Lot lot : lots) {
            lot.attach(list);
            list.update(lot);
        }

    }

    public JPanel getContent() {
        return content;
    }
}
