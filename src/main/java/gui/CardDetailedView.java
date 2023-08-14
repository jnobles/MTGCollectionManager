package gui;

import javax.swing.*;
import java.awt.*;

public class CardDetailedView extends JPanel {
    public CardDetailedView() {
        JPanel pnlCardName = new JPanel(new BorderLayout());
        pnlCardName.add(new JLabel("Name:"), BorderLayout.WEST);
        JLabel lblName = new JLabel();
        pnlCardName.add(lblName, BorderLayout.CENTER);

        JPanel pnlCardManaCost = new JPanel(new BorderLayout());
        pnlCardManaCost.add(new JLabel("Mana Cost:"), BorderLayout.WEST);
        JLabel lblManaCost = new JLabel();
        pnlCardManaCost.add(lblManaCost, BorderLayout.CENTER);

        JPanel pnlCardType = new JPanel(new BorderLayout());
        pnlCardType.add(new JLabel("Type:"), BorderLayout.WEST);
        JLabel lblType = new JLabel();
        pnlCardType.add(lblType, BorderLayout.CENTER);

        JPanel pnlCardText = new JPanel();
        JTextArea txtText = new JTextArea(5, 20);
        pnlCardText.add(txtText);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(pnlCardName);
        add(pnlCardManaCost);
        add(pnlCardType);
        add(pnlCardText);


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CardDetailedView view = new CardDetailedView();
        frame.add(view);
        frame.pack();
        frame.setVisible(true);
    }
}
