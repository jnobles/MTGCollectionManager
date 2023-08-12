import dao.JDBCInventoryDAO;
import dao.JDBCCardDAO;
import model.CardCondition;
import model.CardDTO;
import model.PhysicalCardDTO;

public class testerCode {
    public static void main(String[] args) {
        String cardDbURL = "X:\\Downloads\\AllPrintings.sqlite";
        //String cardDbURL = "C:\\Users\\m313436\\Downloads\\AllPrintings.sqlite";
        JDBCCardDAO cardDatabase = new JDBCCardDAO(cardDbURL);
        CardDTO cardDTO = cardDatabase.getCardBySetAndNumber("NPH", 6);

        String inventoryDbURL = "X:\\Downloads\\Test_db.sqlite";
        //String inventoryDbURL = "C:\\Users\\m313436\\Downloads\\Test_db.sqlite";
        JDBCInventoryDAO inventory = new JDBCInventoryDAO(inventoryDbURL);
        PhysicalCardDTO physicalCardDTO = new PhysicalCardDTO.PhysicalCardBuilder()
                .withCardInformation(cardDTO)
                .withCondition(CardCondition.MODERATELY_PLAYED)
                .withLocation("Binder")
                .build();
        inventory.writePhysicalCardToDatabase(physicalCardDTO);

    }
}
