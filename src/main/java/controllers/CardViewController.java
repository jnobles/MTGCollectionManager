package controllers;

import gui.CardView;
import model.CardDTO;

import javax.swing.table.DefaultTableModel;

public class CardViewController {
    private CardView view;
    public CardViewController(CardView view) {
        this.view = view;
    }

    public void updateViewInformation(CardDTO card) {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
        tableModel.addRow(new Object[]{"Name", card.getName()});
        tableModel.addRow(new Object[]{"Type", card.getType()});
        tableModel.addRow(new Object[]{"Set", card.getSetCode()});
    }
}
