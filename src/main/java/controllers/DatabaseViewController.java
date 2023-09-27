package controllers;

import dao.JDBCCardDAO;
import gui.DatabaseView;
import model.CardDTO;

import javax.swing.table.DefaultTableModel;

public class DatabaseViewController {
    private DatabaseView view;
    private JDBCCardDAO databaseConnection;

    public DatabaseViewController(DatabaseView view) {
        this.view = view;
    }

    public void connectCardDatabase(JDBCCardDAO cardDAO) {
        databaseConnection = cardDAO;
        DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
        for(CardDTO card : databaseConnection.getAllCards()) {
            tableModel.addRow(
                    new Object[]{
                            card.getName(),
                            card.getType(),
                            card.getSetCode(),
                            card.getColorIdentity(),
                            card.getNumber()
                    });
        }
    }

    public DatabaseView getView() {
        return view;
    }

    public JDBCCardDAO getDatabaseConnection() {
        return databaseConnection;
    }
}
