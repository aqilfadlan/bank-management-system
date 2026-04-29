package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BalanceEnquiry extends JFrame implements ActionListener {
    String pin_number;
    JButton back;

    BalanceEnquiry(String pin_number){
        this.pin_number=pin_number;

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

        // Placeholder label - the thread will fill this in when the balance is ready.
        JLabel text = new JLabel("Calculating balance...");
        text.setForeground(Color.WHITE);
        text.setBounds(170,300,400,30);
        image.add(text);

        setLayout(null);
        setSize(900,900);
        setVisible(true);
        setTitle("Balance Enquiry");
        setLocation(300,0);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 1. Build a small "Loading..." dialog (non-modal so it does NOT block).
        JDialog loading = new JDialog(this, "Please wait", false);
        JLabel msg = new JLabel("   Fetching balance, please wait...   ");
        msg.setFont(new Font("Raleway", Font.BOLD, 16));
        loading.add(msg);
        loading.pack();
        loading.setLocationRelativeTo(this);
        loading.setVisible(true);

        // 2. Create the thread and hand it the dialog + the label to update.
        BalanceEnquiryThread t = new BalanceEnquiryThread(pin_number, loading, text);

        // 3. MIN_PRIORITY because this is a background read - the user can wait
        //    while looking at the screen.
        t.setPriority(Thread.MIN_PRIORITY);

        // 4. Start the thread - run() executes in the background.
        t.start();
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Transactions(pin_number).setVisible(true);
    }
}
