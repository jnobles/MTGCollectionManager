package gui;

import javax.swing.*;

public class ListDisplayView extends JPanel {
    public ListDisplayView() {
        JTable mainTable = new JTable();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ListDisplayView view = new ListDisplayView();
        frame.add(view);
        frame.pack();
        frame.setVisible(true);
    }
}
