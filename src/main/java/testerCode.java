import controllers.ApplicationController;
import controllers.CardViewController;
import controllers.DatabaseViewController;
import dao.JDBCCardDAO;
import gui.CardView;
import gui.DatabaseView;
import javax.swing.*;

public class testerCode {
    public static void main(String[] args) {
        //String cardDbURL = "X:\\Downloads\\AllPrintings.sqlite";
        String cardDbURL = "C:\\Users\\m313436\\Downloads\\AllPrintings.sqlite";
        JDBCCardDAO cardDatabase = new JDBCCardDAO(cardDbURL);

        DatabaseViewController databaseViewController = new DatabaseViewController(new DatabaseView());
        databaseViewController.connectCardDatabase(cardDatabase);
        CardViewController cardViewController = new CardViewController(new CardView());
        ApplicationController applicationController = new ApplicationController(cardViewController, databaseViewController);

        JFrame databaseView = new JFrame("Database View");
        databaseView.setContentPane(databaseViewController.getView());
        databaseView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        databaseView.pack();
        databaseView.setVisible(true);

        JFrame cardView = new JFrame("Card View");
        cardView.setContentPane(cardViewController.getView());
        cardView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardView.pack();
        cardView.setVisible(true);

    }
}
