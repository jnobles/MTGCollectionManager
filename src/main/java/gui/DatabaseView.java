package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DatabaseView extends JPanel {
    private JTable tableDatabaseView;
    private JScrollPane scrollPaneDatabaseTable;
    private String[] columnHeaders = new String[]{"Name", "Type", "Set", "Color", "Number"};

    public DatabaseView() {
        setLayout(new BorderLayout());

        tableDatabaseView = new JTable();
        DefaultTableModel tableModel = (DefaultTableModel) tableDatabaseView.getModel();

        for(String header : columnHeaders) {
            tableModel.addColumn(header);
        }

        tableDatabaseView.getColumn("Set").setMaxWidth(50);
        tableDatabaseView.getColumn("Color").setMaxWidth(100);

        scrollPaneDatabaseTable = new JScrollPane(tableDatabaseView);
        scrollPaneDatabaseTable.setPreferredSize(new Dimension(800, 200));

        add(scrollPaneDatabaseTable, BorderLayout.CENTER);
    }

    public JTable getTable() {
        return tableDatabaseView;
    }
}
