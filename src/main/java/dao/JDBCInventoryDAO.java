package dao;

import model.CardCondition;
import model.CardDTO;
import model.PhysicalCardDTO;
import org.sqlite.SQLiteConfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCInventoryDAO implements CardDAO<PhysicalCardDTO> {
    private final String dbURL;

    public JDBCInventoryDAO(String databasePath) {
        if (databasePath.startsWith("jdbc:sqlite:")) {
            if (!(new File(databasePath).exists())) {
                initializeDatabase(databasePath);
            }
        } else {
            if (!(new File(databasePath).exists())) {
                initializeDatabase(databasePath);
            }
            databasePath = "jdbc:sqlite:" + databasePath;
        }
        this.dbURL = databasePath;


    }

    private void initializeDatabase(String databasePath) {
        try {
            File database = new File(databasePath);
            database.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:" + databasePath);
             Statement statement = connection.createStatement()) {
            ClassLoader classLoader = getClass().getClassLoader();
            try (InputStream inputStream = classLoader.getResourceAsStream("initializeInventory.sql")) {
                if (inputStream != null) {
                    String[] sqlQueries = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8).split(";");
                    for (String query : sqlQueries) {
                        statement.executeUpdate(query);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void writePhysicalCardToDatabase(PhysicalCardDTO physicalCardDTO) {
        writePhysicalCardToDatabase(physicalCardDTO, false);
    }

    private void writePhysicalCardToDatabase(PhysicalCardDTO physicalCardDTO, Boolean isRecursiveCall) {
        String sqlQuery = "INSERT INTO physical_cards (condition, location, setCode, number) VALUES (?, ?, ?, ?)";
        try {
            executePreparedStatement(sqlQuery,
                    physicalCardDTO.getCondition().toString(),
                    physicalCardDTO.getLocation(),
                    physicalCardDTO.getCardInformation().getSetCode(),
                    physicalCardDTO.getCardInformation().getNumber());
        }catch (SQLException e) {
            if (e.getErrorCode() == SQLiteErrorCodes.CONSTRAINT.getCode()) {
                if (isRecursiveCall) {
                    throw new RuntimeException("Issue when creating item for use as foreign key.");
                } else {
                    writeCardToDatabase(physicalCardDTO.getCardInformation());
                    writePhysicalCardToDatabase(physicalCardDTO, true);
                }
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    private void writeCardToDatabase(CardDTO cardDTO) {
        String sqlQuery = "INSERT INTO cards (colorIdentity, colors, manaCost, manaValue, name, number, power, subtypes, supertypes, text, toughness, type, types, setCode)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            executePreparedStatement(sqlQuery,
                    cardDTO.getColorIdentity(),
                    cardDTO.getColors(),
                    cardDTO.getManaCost(),
                    cardDTO.getManaValue(),
                    cardDTO.getName(),
                    cardDTO.getNumber(),
                    cardDTO.getPower(),
                    cardDTO.getSubtypes(),
                    cardDTO.getSupertypes(),
                    cardDTO.getText(),
                    cardDTO.getToughness(),
                    cardDTO.getType(),
                    cardDTO.getTypes(),
                    cardDTO.getSetCode());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executePreparedStatement(String sqlQuery, Object ... parameters) throws SQLException {
        SQLiteConfig sqLiteConfig = new SQLiteConfig();
        sqLiteConfig.enforceForeignKeys(true);
        try (Connection connection = DriverManager.getConnection(dbURL, sqLiteConfig.toProperties());
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof String[]) { // convert String[] to a comma separated String for storage
                    parameters[i] = String.join(",", (String[]) parameters[i]);
                }
                statement.setObject(i + 1, parameters[i]);
            }
            statement.executeUpdate();
        }
    }

    @Override
    public List<PhysicalCardDTO> getAllCards() {
        String query = "SELECT * FROM physical_cards";

        List<PhysicalCardDTO> physicalCardDTOs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbURL);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                physicalCardDTOs.add(extractPhysicalCardDTOFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return physicalCardDTOs;
    }

    private PhysicalCardDTO extractPhysicalCardDTOFromResultSet(ResultSet resultSet) throws SQLException {
        String set = resultSet.getString("setCode");
        String number = resultSet.getString("number");
        String location = resultSet.getString("location");
        CardCondition condition = CardCondition.valueOf(resultSet.getString("condition"));

        JDBCCardDAO cardInformationLookup = new JDBCCardDAO(dbURL);
        CardDTO cardInformation = cardInformationLookup.getCardBySetAndNumber(set, number);

        return new PhysicalCardDTO(cardInformation, location, condition);
    }


}
