package dao;

import model.CardDTO;

import java.util.List;

public interface CardDAO {
    List<CardDTO> getAllCards();
}
