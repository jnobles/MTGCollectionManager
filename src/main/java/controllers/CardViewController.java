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
        DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
        model.setRowCount(0);

        if(card.getType().contains("Creature")) {
            updateViewInformationCreature(card, model);
        }
    }

    private void updateViewInformationCreature(CardDTO card, DefaultTableModel model) {
        model.addRow(new Object[]{"Name", card.getName()});
        model.addRow(new Object[]{"Type", card.getType()});
        model.addRow(new Object[]{"Set", card.getSetCode()});
        model.addRow(new Object[]{"Color", card.getColorIdentity()});
        model.addRow(new Object[]{"Mana Cost", card.getManaCost()});
        model.addRow(new Object[]{"P/T", card.getPower() + "/" + card.getToughness()});
        view.setTextareaCardText(card.getText());
    }

    public CardView getView() {
        return view;
    }
}
