package controllers;

import dao.JDBCCardDAO;
import gui.CardView;
import gui.DatabaseView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ApplicationController {
    CardViewController cardViewController;
    DatabaseViewController databaseViewController;

    public ApplicationController(CardViewController cardViewController, DatabaseViewController databaseViewController) {
        this.cardViewController = new CardViewController(new CardView());
        this.databaseViewController = new DatabaseViewController(new DatabaseView());

        //Connect listener to update card view on database view selection
        JTable databaseTable =  databaseViewController.getView().getTable();
        databaseTable.getSelectionModel().addListSelectionListener(
            new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = (databaseTable.getSelectedRow() != -1) ? databaseTable.getSelectedRow() : 1;
                        JDBCCardDAO databaseConnection = databaseViewController.getDatabaseConnection();
                        cardViewController.updateViewInformation(
                                databaseConnection.getCardBySetAndNumber(
                                        (String) databaseTable.getValueAt(selectedRow, 2),
                                        (String) databaseTable.getValueAt(selectedRow, 4)
                                )
                        );

                    }
                }
            }
        );
    }
}
