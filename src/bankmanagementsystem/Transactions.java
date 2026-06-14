package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transactions extends JFrame implements ActionListener {

    JButton deposit,withdrawl,fastcash,ministatement,pinchange,balance,exit;
    String pin_number;
    Login loginFrame;
    Transactions(String pin_number, Login loginFrame){
        this.loginFrame = loginFrame;

        this.pin_number = pin_number;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2= i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("Please select your Transaction");
        text.setBounds(215,300,700,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text);

        deposit = new JButton("Deposit");
        deposit.setBounds(170,415,150,30);
        image.add(deposit);
        deposit.addActionListener(this);

        withdrawl = new JButton("Cash Withdrawl");
        withdrawl.setBounds(355,415,150,30);
        image.add(withdrawl);
        withdrawl.addActionListener(this);

        fastcash = new JButton("Fast Cash");
        fastcash.setBounds(170, 450, 150, 30);
        image.add(fastcash);
        fastcash.addActionListener(this);

        ministatement = new JButton("Mini Statement");
        ministatement.setBounds(355, 450, 150, 30);
        image.add(ministatement);
        ministatement.addActionListener(this);

        pinchange = new JButton("PIN change");
        pinchange.setBounds(170, 485, 150, 30);
        image.add(pinchange);
        pinchange.addActionListener(this);

        balance = new JButton("Balance Enquiry");
        balance.setBounds(355, 485, 150, 30);
        image.add(balance);
        balance.addActionListener(this);

        exit = new JButton("Exit");
        exit.setBounds(355, 520, 150, 30);
        image.add(exit);
        exit.addActionListener(this);

        setSize(900,900);
        setLocation(300,0);
        setTitle("Main Transaction Frame");
        setLayout(null);
//        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exit){
            dispose();
            loginFrame.cardtext.setText("");
            loginFrame.pintext.setText("");
            loginFrame.statusLabel.setText("");
            loginFrame.login.setEnabled(true);
            loginFrame.clear.setEnabled(true);
            loginFrame.signup.setEnabled(true);
            loginFrame.setVisible(true);
        } else if (e.getSource()==deposit) {
            setVisible(false);
            new Deposit(pin_number, loginFrame).setVisible(true);
        } else if (e.getSource()==withdrawl) {
            setVisible(false);
            new Withdraw(pin_number, loginFrame).setVisible(true);
        } else if (e.getSource()==fastcash) {
            setVisible(false);
            new FastCash(pin_number, loginFrame).setVisible(true);
        } else if (e.getSource()==pinchange) {
            setVisible(false);
            new PinChange(pin_number, loginFrame).setVisible(true);
        } else if (e.getSource()==balance) {
            setVisible(false);
            new BalanceEnquiry(pin_number, loginFrame).setVisible(true);
        } else if (e.getSource()==ministatement) {
//            setVisible(false);
            new MiniStatement(pin_number).setVisible(true);
        }
    }
}
