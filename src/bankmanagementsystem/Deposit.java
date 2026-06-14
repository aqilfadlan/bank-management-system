package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount;
    JButton deposit,back;
    String pin_number;
    Login loginFrame;

    Deposit(String pin_number, Login loginFrame){

        this.pin_number=pin_number;
        this.loginFrame=loginFrame;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel  text = new JLabel("Enter the amount you want to deposit : ");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        text.setBounds(170,300,400,20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway",Font.BOLD,22));
        amount.setBounds(170,350,320,25);
        image.add(amount);

        deposit = new JButton("Deposit");
        deposit.setBounds(355,485,150,30);
        image.add(deposit);
        deposit.addActionListener(this);

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
        if (e.getSource()==deposit){
            String money = amount.getText();
            if(money.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter the amount you want to deposit");
            }else{
                // Show a small non-modal "please wait" dialog while the deposit runs.
                JDialog loading = new JDialog(this, "Please wait", false);
                JLabel msg = new JLabel("   Processing deposit, please wait...   ");
                msg.setFont(new Font("Raleway", Font.BOLD, 16));
                loading.add(msg);
                loading.pack();
                loading.setLocationRelativeTo(this);
                loading.setVisible(true);

                // Run the insert on a background thread so the UI stays responsive.
                DepositThread t = new DepositThread(pin_number, money, loading, this, loginFrame);
                t.start();
            }
        } else if (e.getSource()==back) {
            setVisible(false);
            new Transactions(pin_number, loginFrame).setVisible(true);
        }
    }
}
