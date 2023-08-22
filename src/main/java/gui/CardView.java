package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CardView extends JPanel {
    DefaultTableModel tableModel;

    public CardView() {
        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel();
        JTable tableCardInfo = new JTable(tableModel);
        DefaultTableModel tableModel = (DefaultTableModel) tableCardInfo.getModel();
        tableModel.addColumn("Header");
        tableModel.addColumn("Info");
        add(tableCardInfo, BorderLayout.CENTER);

        JTextArea textareaCardText = new JTextArea(20, 20);
        textareaCardText.setLineWrap(true);
        textareaCardText.setWrapStyleWord(true);
        add(textareaCardText, BorderLayout.SOUTH);
    }

    public void addRow(String header, String data) {
        tableModel.addRow(new Object[]{header, data});
    }
}
