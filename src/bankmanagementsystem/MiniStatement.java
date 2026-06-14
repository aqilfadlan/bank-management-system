package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class MiniStatement extends JFrame {
    String pin_number;
    MiniStatement(String pin_number){
        this.pin_number=pin_number;

        JLabel mini = new JLabel();
        mini.setBounds(20,140,400,200);
        add(mini);

        JLabel bank = new JLabel("Bank Of Malaysia");
        bank.setBounds(150,20,100,20);
        add(bank);

        JLabel card = new JLabel();
        card.setBounds(20,80,300,20);
        add(card);

        JLabel balance = new JLabel();
        balance.setBounds(30,460,300,20);
        add(balance);
        
        // Placeholders the thread will fill in.
        mini.setText("<html>Loading transactions...<html>");
        balance.setText("Loading balance...");
        card.setText("Loading card number...");

        // Loading dialog (non-modal!)
        JDialog loading = new JDialog(this, "Please wait", false);
        JLabel msg = new JLabel("   Loading mini statement, please wait...   ");
        msg.setFont(new Font("Raleway", Font.BOLD, 16));
        loading.add(msg);
        loading.pack();
        loading.setLocationRelativeTo(this);
        loading.setVisible(true);

        // Thread
        MiniStatementThread t = new MiniStatementThread(pin_number, loading, card, mini, balance);
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();

        setSize(400,600);
        setLocation(20,20);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setVisible(true);
        setTitle("Mini Statement");
//      setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new MiniStatement("");
    }
}
