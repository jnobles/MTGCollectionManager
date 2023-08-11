package dao;

import model.CardDTO;

import java.util.List;

public class JBDCInventoryDAO implements CardDAO {
    private final String dbURL;

    public JBDCInventoryDAO(String dbURL) {
        this.dbURL = dbURL;
    }

    @Override
    public List<CardDTO> getAllCards() {
        return null;
    }

    public List<CardDTO> getCardsBySet(String set) {
        return null;
    }

    public List<CardDTO> getCardBySetAndNumber(String set, String number) {
        return null;
    }
}
