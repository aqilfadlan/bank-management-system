package bankmanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupTwo extends JFrame implements ActionListener {
    JLabel heading,belif,category,income,educational,qualification,occupation,pan,adhar,senior,existing;
    JComboBox religion,combocat,comboin,comboed,profession;
    JTextField panno,adhartext;
    JRadioButton syes,sno,eyes,eno;
    JButton next;
    String formno;
    Login loginFrame;
    public SignupTwo(String formno, Login loginFrame){
        this.formno=formno;
        this.loginFrame=loginFrame;

        heading = new JLabel("Page 2 : Additional Details");
        heading.setFont(new Font("Raleway",Font.BOLD,22));
        heading.setBounds(290,80,400,30);
        add(heading);

        String[] belief = {"Islam","Buddhism","Christianity","Hinduism","Taoism","Others"};
        belif = new JLabel("Religion :");
        belif.setFont(new Font("Raleway",Font.BOLD,20));
        belif.setBounds(100,140,100,30);
        add(belif);
        religion = new JComboBox<>(belief);
        religion.setBounds(300,140,400,30);
        religion.setBackground(Color.WHITE);
        religion.setForeground(Color.BLACK);
        religion.setFont(new Font("Raleway",Font.BOLD,14));
        //aligning the items in the JComboBox to the center.
        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        religion.setRenderer(listRenderer); //this will align the items to the center in combobox
        add(religion);

        String[] caste = {"Bumiputera","Chinese","Indian","Others"};
        category = new JLabel("Category :");
        category.setFont(new Font("Raleway",Font.BOLD,20));
        category.setBounds(100,190,200,30);
        add(category);
        combocat = new JComboBox<>(caste);
        combocat.setBounds(300,190,400,30);
        combocat.setBackground(Color.WHITE);
        combocat.setForeground(Color.BLACK);
        combocat.setFont(new Font("Raleway",Font.BOLD,14));
        combocat.setRenderer(listRenderer);
        add(combocat);

        String [] salary = {"Null","< RM 30,000","< RM 60,000","< RM 120,000","Upto RM 250,000"};
        income = new JLabel("Income :");
        income.setFont(new Font("Raleway",Font.BOLD,20));
        income.setBounds(100,240,150,30);
        add(income);
        comboin = new JComboBox<>(salary);
        comboin.setBackground(Color.WHITE);
        comboin.setForeground(Color.BLACK);
        comboin.setFont(new Font("Raleway",Font.BOLD,14));
        comboin.setBounds(300, 240, 400, 30);
        comboin.setRenderer(listRenderer);
        add(comboin);

        String [] educationValues = {"Non-Graduate","Graduate","Post-Graduate","Doctorate"};
        educational = new JLabel("Educational");
        educational.setFont(new Font("Raleway",Font.BOLD,20));
        educational.setBounds(100,290,150,30);
        add(educational);
        qualification = new JLabel("Qualification :");
        qualification.setFont(new Font("Raleway",Font.BOLD,20));
        qualification.setBounds(100,315,150,30);
        add(qualification);
        comboed = new JComboBox<>(educationValues);
        comboed.setBackground(Color.WHITE);
        comboed.setForeground(Color.BLACK);
        comboed.setFont(new Font("Raleway",Font.BOLD,14));
        comboed.setRenderer(listRenderer);
        comboed.setBounds(300, 315, 400, 30);
        add(comboed);

        String[] OccValues={"Salaried","Self-Employed","Businessman","Student","Retired","Others"};
        occupation = new JLabel("Occupation :");
        occupation.setFont(new Font("Raleway",Font.BOLD,20));
        occupation.setBounds(100,390,150,30);
        add(occupation);
        profession = new JComboBox<>(OccValues);
        profession.setBackground(Color.WHITE);
        profession.setForeground(Color.BLACK);
        profession.setFont(new Font("Raleway",Font.BOLD,14));
        profession.setRenderer(listRenderer);
        profession.setBounds(300, 390, 400, 30);
        add(profession);

        pan = new JLabel("NRIC Number :");
        pan.setFont(new Font("Raleway",Font.BOLD,20));
        pan.setBounds(100,440,150,30);
        add(pan);
        panno = new JTextField();
        panno.setFont(new Font("Raleway",Font.BOLD,14));
        panno.setBounds(300, 440, 400, 30);
        add(panno);

        adhar = new JLabel("MyKad Number :");
        adhar.setFont(new Font("Raleway",Font.BOLD,20));
        adhar.setBounds(100,490,180,30);
        add(adhar);
        adhartext = new JTextField();
        adhartext.setFont(new Font("Raleway",Font.BOLD,14));
        adhartext.setBounds(300, 490, 400, 30);
        add(adhartext);

        senior = new JLabel("Senior Citizen :");
        senior.setFont(new Font("Raleway",Font.BOLD,20));
        senior.setBounds(100,540,150,30);
        add(senior);
        syes=new JRadioButton("YES");
        syes.setBounds(360,540,100,30);
        syes.setBackground(Color.WHITE);
        add(syes);
        sno=new JRadioButton("NO");
        sno.setBounds(490, 540, 100, 30);
        sno.setBackground(Color.WHITE);
        add(sno);
        ButtonGroup seniorgroup = new ButtonGroup();
        seniorgroup.add(syes);
        seniorgroup.add(sno);

        existing = new JLabel("Existing Account :");
        existing.setFont(new Font("Raleway",Font.BOLD,20));
        existing.setBounds(100,590,190,30);
        add(existing);
        eyes=new JRadioButton("YES");
        eyes.setBounds(360,590,100,30);
        eyes.setBackground(Color.WHITE);
        add(eyes);
        eno=new JRadioButton("NO");
        eno.setBounds(490, 590, 100, 30);
        eno.setBackground(Color.WHITE);
        add(eno);
        ButtonGroup existinggroup = new ButtonGroup();
        existinggroup.add(eyes);
        existinggroup.add(eno);

        //Adding Button
        next = new JButton("NEXT");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway",Font.BOLD,14));
        next.setBounds(620, 660, 80, 30);
        add(next);
        next.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);
        setSize(850,800);
        setLocation(350,10);
        setTitle("NEW ACCOUNT APPLICATION FORM- PAGE 2");
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String sreligion = religion.getSelectedItem().toString();
        String scategory = combocat.getSelectedItem().toString();
        String sincome = comboin.getSelectedItem().toString();
        String seducation = comboed.getSelectedItem().toString();
        String sprofession = profession.getSelectedItem().toString();
        String seniorcitizen = null;
        if(syes.isSelected()){
            seniorcitizen = "Yes";
        } else if (sno.isSelected()) {
            seniorcitizen="No";
        }
        String existingaccount = null;
        if(eyes.isSelected()){
            existingaccount="Yes";
        } else if (eno.isSelected()) {
            existingaccount="No";
        }
        String s_pan = panno.getText();
        String aadhar = adhartext.getText();

        try{
            if(scategory.equals("")){
                JOptionPane.showMessageDialog(null,"Please select proper category.");
            }
            if(sincome.equals("")){
                JOptionPane.showMessageDialog(null,"Please state your income.");
            }
            if(seducation.equals("")){
                JOptionPane.showMessageDialog(null,"Please mention educational qualification.");
            }
            if(sprofession.equals("")){
                JOptionPane.showMessageDialog(null,"Please select proper occupation.");
            }
            if(seniorcitizen.equals("")){
                JOptionPane.showMessageDialog(null,"Please Confirm if you are senior citizen.");
            }
            if(existingaccount.equals("")){
                JOptionPane.showMessageDialog(null,"Please confirm if you have any existing account");
            }
            if(s_pan.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter your NRIC number");
            }
            if(aadhar.equals("")){
                JOptionPane.showMessageDialog(null,"Please enter your MyKad number");
            }
            else {
                Conn c = new Conn();
                String query = "insert into signuptwo values ('"+formno+"', '"+sreligion+"', '"+scategory+"', '"+sincome+"', '"+seducation+"', '"+sprofession+"', '"+s_pan+"', '"+aadhar+"', '"+seniorcitizen+"', '"+existingaccount+"')";
                c.s.executeUpdate(query);

                //We have to link the signup 3 frame here
               setVisible(false);
               new SignupThree(formno, loginFrame).setVisible(true);
            }
        }
        catch (Exception e1){
            System.out.println(e1);
        }

    }
}
