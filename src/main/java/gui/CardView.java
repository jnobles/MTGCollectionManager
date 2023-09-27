package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CardView extends JPanel {
    private final JTable tableCardInfo;
    private final JTextArea textareaCardText;

    public CardView() {
        setLayout(new BorderLayout());
        tableCardInfo = new JTable();

        DefaultTableModel tableModel = (DefaultTableModel) tableCardInfo.getModel();
        tableModel.addColumn("Header");
        tableModel.addColumn("Info");
        tableCardInfo.getColumnModel().getColumn(0).setMaxWidth(100);
        tableCardInfo.getColumnModel().getColumn(0).setCellRenderer(new BoldCellRenderer());

        textareaCardText = new JTextArea(10, 20);
        textareaCardText.setLineWrap(true);
        textareaCardText.setWrapStyleWord(true);

        add(tableCardInfo, BorderLayout.CENTER);
        add(textareaCardText, BorderLayout.SOUTH);
    }

    public JTable getTable() {
        return tableCardInfo;
    }

    public void setTextareaCardText(String text) {
        textareaCardText.setText(text);
    }

    static class BoldCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if(component instanceof JLabel) {
                component.setFont(component.getFont().deriveFont(Font.BOLD));
            }
            return component;
        }
    }
}
