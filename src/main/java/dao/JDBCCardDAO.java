package dao;

import model.CardDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCardDAO implements CardDAO {
    private final String dbURL;

    public JDBCCardDAO(String dbURL) {
        this.dbURL = dbURL;
    }

    @Override
    public List<CardDTO> getAllCards() {
        String query = "SELECT * FROM cards";
        return runQuery(query);
    }

    public List<CardDTO> getCardsBySet(String set) {
        String query = "SELECT * FROM cards WHERE setCode = ?";
        return runQuery(query, set);
    }

    public CardDTO getCardBySetAndNumber(String set, String number) {
        String query = "SELECT * FROM cards WHERE setCode = ? AND number = ?";
        return runQuery(query, set, number).get(0);
    }

    public CardDTO getCardBySetAndNumber(String set, int number) {
        String query = "SELECT * FROM cards WHERE setCode = ? AND number = ?";
        return runQuery(query, set, Integer.toString(number)).get(0);
    }

    private List<CardDTO> runQuery(String query, String ... parameters) {
        if (dbURL == null) {
            throw new RuntimeException("Database URL not set");
        }

        List<CardDTO> cardsDTOs = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(dbURL);
             PreparedStatement statement = conn.prepareStatement(query);) {
            for (int i = 0; i < parameters.length; i++) {
                statement.setString(i + 1, parameters[i]);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CardDTO cardDTO = extractCardDTOFromResultSet(resultSet);
                    cardsDTOs.add(cardDTO);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cardsDTOs;
    }

    private CardDTO extractCardDTOFromResultSet(ResultSet resultSet) throws SQLException {
        return new CardDTO.CardDTOBuilder()
                .withColors(resultSet.getString("colors"))
                .withColorIdentity(resultSet.getString("colorIdentity"))
                .withName(resultSet.getString("name"))
                .withNumber(resultSet.getString("number"))
                .withManaCost(resultSet.getString("manaCost"))
                .withManaValue(resultSet.getFloat("manaValue"))
                .withPower(resultSet.getString("power"))
                .withSubtypes(resultSet.getString("subtypes").split(","))
                .withSupertypes(resultSet.getString("supertypes").split(","))
                .withText(resultSet.getString("text"))
                .withToughness(resultSet.getString("toughness"))
                .withType(resultSet.getString("type"))
                .withTypes(resultSet.getString("types").split(","))
                .withSetCode(resultSet.getString("setCode"))
                .build();
    }
}
