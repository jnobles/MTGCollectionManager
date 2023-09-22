package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CardView extends JPanel {
    JTable table;

    public CardView() {
        setLayout(new BorderLayout());
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable tableCardInfo = new JTable(tableModel);
        tableModel.addColumn("Header");
        tableModel.addColumn("Info");
        add(tableCardInfo, BorderLayout.CENTER);

        JTextArea textareaCardText = new JTextArea(20, 20);
        textareaCardText.setLineWrap(true);
        textareaCardText.setWrapStyleWord(true);
        add(textareaCardText, BorderLayout.SOUTH);
    }

    public JTable getTable() {
        return table;
    }
}
