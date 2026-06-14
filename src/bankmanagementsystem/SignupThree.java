package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SignupThree extends JFrame implements ActionListener {

    JRadioButton r1,r2,r3,r4;
    JCheckBox c1,c2,c3,c4,c5,c6,c7;
    JButton submit,cancel;
    String formno;
    Login loginFrame;

    SignupThree(String formno, Login loginFrame){

        this.formno=formno;
        this.loginFrame=loginFrame;

        JLabel heading = new JLabel("Page 3 : Account Details");
        heading.setFont(new Font("Raleway",Font.BOLD,22));
        heading.setBounds(280,40,400,40);
        add(heading);

        JLabel type = new JLabel("Account Type :");
        type.setFont(new Font("Raleway",Font.BOLD,22));
        type.setBounds(100,140,200,30);
        add(type);
        r1 = new JRadioButton("Savings Account");
        r1.setBackground(Color.WHITE);
        r1.setFont(new Font("Raleway",Font.BOLD,16));
        r1.setBounds(100,180,180,20);
        add(r1);
        r2 = new JRadioButton("Fixed Deposit Account");
        r2.setBackground(Color.WHITE);
        r2.setFont(new Font("Raleway",Font.BOLD,16));
        r2.setBounds(350,180,200,20);
        add(r2);
        r3 = new JRadioButton("Current Account");
        r3.setBackground(Color.WHITE);
        r3.setFont(new Font("Raleway",Font.BOLD,16));
        r3.setBounds(100,220,180,20);
        add(r3);
        r4 = new JRadioButton("Reccurring Deposit Account");
        r4.setBackground(Color.WHITE);
        r4.setFont(new Font("Raleway",Font.BOLD,16));
        r4.setBounds(350,220,250,20);
        add(r4);
        ButtonGroup accountgroup = new ButtonGroup();
        accountgroup.add(r1);
        accountgroup.add(r2);
        accountgroup.add(r3);
        accountgroup.add(r4);

        JLabel card = new JLabel("Card Number :");
        card.setFont(new Font("Raleway",Font.BOLD,22));
        card.setBounds(100,300,200,30);
        add(card);
        JLabel card_details = new JLabel("(Your 16 Digit Card Number)");
        card_details.setFont(new Font("Raleway",Font.BOLD,12));
        card_details.setBounds(100,330,300,15);
        add(card_details);
        JLabel number = new JLabel("XXXX-XXXX-XXXX-4156");
        number.setFont(new Font("Raleway",Font.BOLD,22));
        number.setBounds(330,300,300,30);
        add(number);

        JLabel pin = new JLabel("PIN Number :");
        pin.setFont(new Font("Raleway",Font.BOLD,22));
        pin.setBounds(100,370,200,30);
        add(pin);
        JLabel pin_details = new JLabel("(Your 4 Digit PIN Number)");
        pin_details.setFont(new Font("Raleway",Font.BOLD,12));
        pin_details.setBounds(100,400,300,15);
        add(pin_details);
        JLabel pnumber = new JLabel("XXXX");
        pnumber.setFont(new Font("Raleway",Font.BOLD,22));
        pnumber.setBounds(330,370,300,30);
        add(pnumber);

        JLabel services = new JLabel("Services Required : ");
        services.setFont(new Font("Raleway",Font.BOLD,18));
        services.setBounds(100,450,200,30);
        add(services);
        c1=new JCheckBox("ATM Card");
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Raleway",Font.BOLD,16));
        c1.setBounds(100,500,200,30);
        add(c1);
        c2=new JCheckBox("Internet Banking");
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Raleway",Font.BOLD,16));
        c2.setBounds(350,500,200,30);
        add(c2);
        c3=new JCheckBox("Mobile Banking");
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Raleway",Font.BOLD,16));
        c3.setBounds(100,550,200,30);
        add(c3);
        c4=new JCheckBox("Email & SMS Alerts");
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Raleway",Font.BOLD,16));
        c4.setBounds(350,550,200,30);
        add(c4);
        c5=new JCheckBox("Cheque Book");
        c5.setBackground(Color.WHITE);
        c5.setFont(new Font("Raleway",Font.BOLD,16));
        c5.setBounds(100,600,200,30);
        add(c5);
        c6=new JCheckBox("E-Statement");
        c6.setBackground(Color.WHITE);
        c6.setFont(new Font("Raleway",Font.BOLD,16));
        c6.setBounds(350,600,200,30);
        add(c6);
        c7=new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge");
        c7.setBackground(Color.WHITE);
        c7.setFont(new Font("Raleway",Font.BOLD,12));
        c7.setBounds(100,680,600,30);
        add(c7);

        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway",Font.BOLD,14));
        submit.setBounds(250,720,100,30);
        add(submit);
        submit.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway",Font.BOLD,14));
        cancel.setBounds(420,720,100,30);
        add(cancel);
        cancel.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setSize(850,820);
        setLocation(350,10);
        setTitle("NEW ACCOUNT APPLICATION FORM- PAGE 3");
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){
            String account_type = null;
            if(r1.isSelected()){
                account_type = "Savings Account";
            } else if (r2.isSelected()) {
                account_type="Fixed Deposit Account";
            } else if (r3.isSelected()) {
                account_type = "Current Account";
            } else if (r4.isSelected()) {
                account_type = "Recurring Deposit Account";
            }

            Random random = new Random();
            String cardnumber = ""+Math.abs((random.nextLong()%90000000L+5040936000000000L));
            String pinnumber = ""+Math.abs((random.nextLong()%9000L+1000L));

            String facility = "";
            if (c1.isSelected()){
                facility+="ATM Card";
            }if (c2.isSelected()){
                facility+="Internet Banking";
            }if(c3.isSelected()){
                facility+="Mobile Banking";
            }if(c4.isSelected()){
                facility+="Email & SMS Alerts";
            }if(c5.isSelected()){
                facility+="Cheque Book";
            }if (c6.isSelected()){
                facility+="E-Statements";
            }

            try{
                if(account_type.equals("")){
                    JOptionPane.showMessageDialog(null,"Please select proper account.");
                }
                if(facility.equals("")){
                    JOptionPane.showMessageDialog(null,"Select atleast one service.");
                }
                else{
                    Conn c = new Conn();
                    String query = "insert into signupthree values ('"+formno+"', '"+account_type+"', '"+cardnumber+"', '"+pinnumber+"', '"+facility+"')";
                    c.s.executeUpdate(query);
                    String query2 = "insert into login values ('"+formno+"', '"+cardnumber+"', '"+pinnumber+"')";
                    c.s.executeUpdate(query2);

                    JOptionPane.showMessageDialog(null,"Card Number : "+cardnumber+"\nPIN : "+pinnumber);

                    setVisible(false);
                    new Deposit(pinnumber, loginFrame).setVisible(true);
                }
            }
            catch(Exception e1){
                System.out.println(e1);
            }
        } else if (e.getSource()==cancel) {
            dispose();
            loginFrame.setVisible(true);
        }
    }
}
