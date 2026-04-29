package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Deposit extends JFrame implements ActionListener {

    JTextField amount;
    JButton deposit,back;
    String pin_number;

    Deposit(String pin_number){

        this.pin_number=pin_number;

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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Deposit("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==deposit){
            String money = amount.getText();
            if(money.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter the amount you want to deposit");
            }else{
                // 1. Build a small "Processing..." dialog (non-modal so it does NOT block).
                JDialog loading = new JDialog(this, "Please wait", false);
                JLabel msg = new JLabel("   Processing deposit, please wait...   ");
                msg.setFont(new Font("Raleway", Font.BOLD, 16));
                loading.add(msg);
                loading.pack();
                loading.setLocationRelativeTo(this);
                loading.setVisible(true);

                // 2. Create the thread and hand it the dialog + this frame.
                DepositThread t = new DepositThread(pin_number, money, loading, this);

                // 3. MAX_PRIORITY because the user is waiting for confirmation.
                t.setPriority(Thread.MAX_PRIORITY);

                // 4. Start the thread - run() executes in the background.
                t.start();
            }
        } else if (e.getSource()==back) {
            setVisible(false);
            new Transactions(pin_number).setVisible(true);
        }
    }
}
