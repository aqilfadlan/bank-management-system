package bankmanagementsystem;

import java.sql.ResultSet;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class MiniStatementThread extends Thread {

    String pin;

    JDialog loadingDialog;
    JLabel cardLabel;
    JLabel miniLabel;
    JLabel balanceLabel;

    MiniStatementThread(String pin, JDialog loadingDialog,
                        JLabel cardLabel, JLabel miniLabel, JLabel balanceLabel) {
        this.pin = pin;
        this.loadingDialog = loadingDialog;
        this.cardLabel = cardLabel;
        this.miniLabel = miniLabel;
        this.balanceLabel = balanceLabel;
    }

    public void run() {
        String cardText = "";
        StringBuilder miniText = new StringBuilder();
        int bal = 0;

        try {
            System.out.println("Mini statement thread started...");
            Thread.sleep(2000);

            // Query 1: card number
            Conn c1 = new Conn();
            ResultSet rs1 = c1.s.executeQuery(
                "select * from login where Pin = '" + pin + "'");
            while (rs1.next()) {
                String cn = rs1.getString("CardNo");
                cardText = "Card Number : " + cn.substring(0, 4) + "XXXXXXXX" + cn.substring(12);
            }

            // Query 2: transactions + running balance
            Conn c2 = new Conn();
            ResultSet rs2 = c2.s.executeQuery(
                "select * from bank where Pin = '" + pin + "'");
            while (rs2.next()) {
                miniText.append("<html>")
                        .append(rs2.getString("date"))
                        .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(rs2.getString("type"))
                        .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(rs2.getString("amount"))
                        .append("<br><br><html>");
                if (rs2.getString("type").equals("Deposit")) {
                    bal += Integer.parseInt(rs2.getString("amount"));
                } else {
                    bal -= Integer.parseInt(rs2.getString("amount"));
                }
            }

            System.out.println("Mini statement thread finished.");
        } catch (InterruptedException ie) {
            System.out.println("Mini statement thread was interrupted!");
        } catch (Exception e) {
            System.out.println(e);
        }

        // Close the dialog and update all three labels.
        loadingDialog.dispose();
        cardLabel.setText(cardText);
        miniLabel.setText(miniText.toString());
        balanceLabel.setText("Your account balance is Rs. " + bal);
    }
}