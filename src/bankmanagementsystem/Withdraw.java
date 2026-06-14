package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class Withdraw extends JFrame implements ActionListener {
    JTextField amount;
    JButton withdrawl,back;
    String pin_number;
    Login loginFrame;
    Withdraw(String pin_number, Login loginFrame){
        this.pin_number=pin_number;
        this.loginFrame=loginFrame;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel  text = new JLabel("Enter the amount you want to withdraw : ");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        text.setBounds(170,300,400,20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,22));
        amount.setBounds(170,350,320,25);
        image.add(amount);

        withdrawl = new JButton("Withdraw");
        withdrawl.setBounds(355,485,150,30);
        image.add(withdrawl);
        withdrawl.addActionListener(this);

        back = new JButton("Back");
        back.setBounds(355,520,150,30);
        image.add(back);
        back.addActionListener(this);

        setSize(900,900);
        setLocation(300,0);
        setTitle("Deposit Page");
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
        if (e.getSource()==back) {
            setVisible(false);
            new Transactions(pin_number, loginFrame).setVisible(true);
        } else if (e.getSource()==withdrawl){
            String money = amount.getText();
            if(money.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter the amount you want to withdraw");
            }else{
                JDialog loading = new JDialog(this, "Please wait", false);
                JLabel msg = new JLabel("   Processing withdrawal, please wait...   ");
                msg.setFont(new Font("Raleway", Font.BOLD, 16));
                loading.add(msg);
                loading.pack();
                loading.setLocationRelativeTo(this);
                loading.setVisible(true);

                WithdrawThread t = new WithdrawThread(pin_number, money, loading, this, loginFrame);
                t.setPriority(Thread.MAX_PRIORITY);
                t.start();
            }
        }
    }
}

