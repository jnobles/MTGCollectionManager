import controllers.CardViewController;
import controllers.DatabaseViewController;
import dao.JDBCInventoryDAO;
import dao.JDBCCardDAO;
import gui.CardView;
import gui.DatabaseView;
import model.CardCondition;
import model.CardDTO;
import model.PhysicalCardDTO;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class testerCode {
    public static void main(String[] args) {
        //String cardDbURL = "X:\\Downloads\\AllPrintings.sqlite";
        String cardDbURL = "C:\\Users\\m313436\\Downloads\\AllPrintings.sqlite";
        JDBCCardDAO cardDatabase = new JDBCCardDAO(cardDbURL);
        List<CardDTO> cardDTOs = new ArrayList<>();
        String[][] testCards = {{"NPH", "13"},{"AKH", "10"}};
        for (String[] card: testCards) {
            cardDTOs.add(cardDatabase.getCardBySetAndNumber(card[0], card[1]));
        }

        //String inventoryDbURL = "X:\\Downloads\\Test_db.sqlite";
        //String inventoryDbURL = "C:\\Users\\m313436\\Downloads\\Test_db.sqlite";
        //JDBCInventoryDAO inventory = new JDBCInventoryDAO(inventoryDbURL);
        //List<PhysicalCardDTO> physicalCardDTOS = new ArrayList<>();
        //Object[][] testPhysicalCards = {{cardDTOs.get(0), "Binder", CardCondition.MODERATELY_PLAYED},{cardDTOs.get(1), "Box", CardCondition.NOT_SET}};
        //for (Object[] physicalCard : testPhysicalCards) {
        //    physicalCardDTOS.add((new PhysicalCardDTO((CardDTO) physicalCard[0], (String) physicalCard[1], (CardCondition) physicalCard[2])));
        //}

        //for (PhysicalCardDTO physicalCardDTO : physicalCardDTOS) {
        //    inventory.writePhysicalCardToDatabase(physicalCardDTO);
        //}

        CardDTO testCard = cardDTOs.get(0);
        //System.out.println(testCard);

        JFrame frame = new JFrame("Test");
        DatabaseViewController controller = new DatabaseViewController(new DatabaseView());
        controller.connectCardDatabase(cardDatabase);
        frame.setContentPane(controller.getView());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
