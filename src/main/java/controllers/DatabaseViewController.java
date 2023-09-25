package controllers;

import dao.JDBCCardDAO;
import gui.DatabaseView;
import model.CardDTO;

import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DatabaseViewController {
    private DatabaseView view;

    public DatabaseViewController(DatabaseView view) {
        this.view = view;
    }

    public void connectCardDatabase(JDBCCardDAO cardDAO) {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
        for(CardDTO card : cardDAO.getAllCards()) {
            tableModel.addRow(
                    new Object[]{
                            card.getName(),
                            card.getType(),
                            card.getSetCode(),
                            card.getColorIdentity()
                    });
        }
    }

    public Container getView() {
        return view;
    }
}
