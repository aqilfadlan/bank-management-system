/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankmanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Aditya Dhanaraj
 */
public class Login extends JFrame implements ActionListener{
    JButton login,clear,signup;
    JTextField cardtext;
    JPasswordField pintext;
    JLabel statusLabel;   // on-screen "logging in..." text instead of a pop-up dialog

    //creating the constructor to create the login frame.
    Login(){

        //seting the properties of the JFrame
        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);
        //creating the image icon.
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));

         //scalling the image.
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);

        //Creating the JLabel with the image icon to place it in the frame.
        JLabel label = new JLabel (i3);
        //setting the location of the label on the frame.
        label.setBounds(70, 10, 100, 100);

        //Adding the label to the frame with add function
        add(label);

        getContentPane().setBackground(Color.WHITE);

        //adding the welcome text.
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward",Font.BOLD,38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        //adding the card no
        JLabel cardno = new JLabel("Card No. :");
        cardno.setFont(new Font("Raleway",Font.BOLD,28));
        cardno.setBounds(120, 150, 400, 40);
        add(cardno);

        //Adding textfield for Card no
        cardtext = new JTextField();
        cardtext.setBounds(300, 150, 230, 30);
        cardtext.setFont(new Font("Arial",Font.BOLD,14));
        add(cardtext);
        //adding pin
        JLabel pin = new JLabel("PIN :");
        pin.setFont(new Font("Raleway",Font.BOLD,28));
        pin.setBounds(120, 220, 250, 40);
        add(pin);

        //Adding textfield for PIN
        pintext = new JPasswordField();
        pintext.setFont(new Font("Arial",Font.BOLD,14));
        pintext.setBounds(300, 220, 230, 30);
        add(pintext);

        //Adding login Button to the frame
        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        //Adding the clear button to frame.
        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);

        //Adding Signup button to frame
        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);

        // On-screen status label (empty until login starts). Sits below the buttons,
        // so the "logging in..." text appears on the ATM screen itself, not in a pop-up.
        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Raleway", Font.BOLD, 16));
        statusLabel.setForeground(Color.BLUE);
        statusLabel.setBounds(120, 400, 410, 30);
        add(statusLabel);

        setSize(800,500);
        setVisible(true);
        setLocation(350,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getSource()==login){
            String card_number = cardtext.getText();
            String pin_number = pintext.getText();

            statusLabel.setText("Logging in, please wait...");
            login.setEnabled(false);
            clear.setEnabled(false);
            signup.setEnabled(false);

            LoginThread t = new LoginThread(card_number, pin_number, this);
            t.setPriority(Thread.MAX_PRIORITY);
            t.start();
        }
        else if(ae.getSource()==clear){
            cardtext.setText("");
            pintext.setText("");
        }
        else if(ae.getSource()==signup){
            setVisible(false);
            new SignupOne(this).setVisible(true);
        }
    }
    public static void main (String args[]){
        new Login();
    }

}