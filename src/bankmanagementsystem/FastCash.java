package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FastCash extends JFrame implements ActionListener {
    JButton hundred,five_h,thousand,two_t,five_t,ten_t,back;
    String pin_number;
    Login loginFrame;
    FastCash(String pin_number, Login loginFrame){
        this.pin_number = pin_number;
        this.loginFrame = loginFrame;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2= i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("Select your withdraw amount");
        text.setBounds(215,300,700,35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        image.add(text);

        hundred = new JButton("RM 100");
        hundred.setBounds(170,415,150,30);
        hundred.setFont(new Font("Raleway",Font.BOLD,16));
        image.add(hundred);
        hundred.addActionListener(this);

        five_h = new JButton("RM 500");
        five_h.setBounds(355,415,150,30);
        image.add(five_h);
        five_h.setFont(new Font("Raleway",Font.BOLD,16));
        five_h.addActionListener(this);

        thousand = new JButton("RM 1000");
        thousand.setBounds(170, 450, 150, 30);
        image.add(thousand);
        thousand.setFont(new Font("Raleway",Font.BOLD,16));
        thousand.addActionListener(this);

        two_t = new JButton("RM 2000");
        two_t.setBounds(355, 450, 150, 30);
        image.add(two_t);
        two_t.setFont(new Font("Raleway",Font.BOLD,16));
        two_t.addActionListener(this);

        five_t = new JButton("RM 5000");
        five_t.setBounds(170, 485, 150, 30);
        image.add(five_t);
        five_t.setFont(new Font("Raleway",Font.BOLD,16));
        five_t.addActionListener(this);

        ten_t = new JButton("RM 10000");
        ten_t.setBounds(355, 485, 150, 30);
        image.add(ten_t);
        ten_t.setFont(new Font("Raleway",Font.BOLD,16));
        ten_t.addActionListener(this);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.setFont(new Font("Raleway",Font.BOLD,16));
        image.add(back);
        back.addActionListener(this);

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
        if(e.getSource()==back){
            setVisible(false);
            new Transactions(pin_number, loginFrame).setVisible(true);
        } else {
            String amount = ((JButton)e.getSource()).getText().substring(3);

            JDialog loading = new JDialog(this, "Please wait", false);
            JLabel msg = new JLabel("   Processing transaction, please wait...   ");
            msg.setFont(new Font("Raleway", Font.BOLD, 16));
            loading.add(msg);
            loading.pack();
            loading.setLocationRelativeTo(this);
            loading.setVisible(true);

            FastCashThread t = new FastCashThread(pin_number, amount, loading, this, loginFrame);
            t.start();
        }
    }
}
