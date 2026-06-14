package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinChange extends JFrame implements ActionListener {
    String pin_number;
    Login loginFrame;
    JTextField pin,repin;
    JButton change,back;
    PinChange(String pin_number, Login loginFrame){
        this.pin_number=pin_number;
        this.loginFrame=loginFrame;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2= i1.getImage().getScaledInstance(900,900,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,900);
        add(image);

        JLabel text = new JLabel("Change Your PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        text.setBounds(250,280,500,35);
        image.add(text);

        JLabel pintext = new JLabel("New PIN");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System",Font.BOLD,16));
        pintext.setBounds(165,320,180,25);
        image.add(pintext);

        pin = new JTextField();
        pin.setFont(new Font("Raleway",Font.BOLD,25));
        pin.setBounds(330,320,180,25);
        image.add(pin);

        JLabel repintext = new JLabel("Re-Enter New PIN");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System",Font.BOLD,16));
        repintext.setBounds(165,360,180,25);
        image.add(repintext);

        repin = new JTextField();
        repin.setFont(new Font("Raleway",Font.BOLD,25));
        repin.setBounds(330,360,180,25);
        image.add(repin);

        change = new JButton("CHANGE");
        change.setBounds(355,460,150,30);
        image.add(change);
        change.addActionListener(this);

        back = new JButton("BACK");
        back.setBounds(355,495,150,30);
        image.add(back);
        back.addActionListener(this);

        setSize(900,900);
        setVisible(true);
        setTitle("PIN Change");
        setLocation(300,0);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==change){
            String s_pin = pin.getText();
            String s_repin = repin.getText();
            if(!s_pin.equals(s_repin)){
                JOptionPane.showMessageDialog(null,"Entered PIN does not match");
                return;
            }
            if(s_pin.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter new PIN");
                return;
            }
            if(s_repin.equals("")){
                JOptionPane.showMessageDialog(null,"Please re-enter the new PIN");
                return;
            }

            // 1. Build a small "Please wait..." dialog.
            //    The "false" makes it non-modal so it does NOT block the UI.
            JDialog loading = new JDialog(this, "Please wait", false);
            JLabel msg = new JLabel("   Changing your PIN, please wait...   ");
            msg.setFont(new Font("Raleway", Font.BOLD, 16));
            loading.add(msg);
            loading.pack();
            loading.setLocationRelativeTo(this);
            loading.setVisible(true);

            // 2. Create the thread task. PinChangeThread implements Runnable and
            //    builds + starts its OWN Thread inside its constructor, so simply
            //    creating it kicks off run() in the background. We do NOT join()
            //    here, otherwise the loading dialog would freeze.
            new PinChangeThread(pin_number, s_repin, loading, this, loginFrame);
        }
        else{
            setVisible(false);
            new Transactions(pin_number, loginFrame).setVisible(true);
        }
    }
}
