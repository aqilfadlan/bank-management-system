package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BalanceEnquiry extends JFrame implements ActionListener {
    String pin_number;
    Login loginFrame;
    JButton back;

    BalanceEnquiry(String pin_number, Login loginFrame){
        this.pin_number=pin_number;
        this.loginFrame=loginFrame;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2= i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        back=new JButton("Back");
        back.setBounds(355,520,150,30);
        back.addActionListener(this);
        image.add(back);

        // Create the balance label empty for now; the thread fills it in.
        JLabel text = new JLabel("");
        text.setForeground(Color.WHITE);
        text.setBounds(170,300,400,30);
        image.add(text);

        // Show a small non-modal "please wait" dialog while the balance loads.
        JDialog loading = new JDialog(this, "Please wait", false);
        JLabel msg = new JLabel("   Fetching your balance, please wait...   ");
        msg.setFont(new Font("Raleway", Font.BOLD, 16));
        loading.add(msg);
        loading.pack();
        loading.setLocationRelativeTo(this);
        loading.setVisible(true);

        // BalanceEnquiryThread implements Runnable and builds + starts its own
        // Thread inside its constructor, so simply creating it runs the query
        // in the background and updates the label when done.
        new BalanceEnquiryThread(pin_number, loading, text);

        setLayout(null);
        setSize(900,900);
        setVisible(true);
        setTitle("Balance Enquiry");
        setLocation(300,0);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Transactions(pin_number, loginFrame).setVisible(true);
    }
}
