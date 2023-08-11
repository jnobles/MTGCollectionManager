import dao.JDBCCardDAO;
import model.CardDTO;

import java.util.List;

public class testerCode {
    public static void main(String[] args) {
        JDBCCardDAO CardDatabaseDAO = new JDBCCardDAO("jdbc:sqlite:X:\\Downloads\\AllPrintings.sqlite");
        CardDTO cardDTO = CardDatabaseDAO.getCardBySetAndNumber("NPH", 6);
        System.out.println(cardDTO);
        System.out.println("Done");
    }
}
