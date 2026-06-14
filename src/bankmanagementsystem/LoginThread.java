package bankmanagementsystem;

import java.sql.ResultSet;

import javax.swing.SwingUtilities;

public class LoginThread extends Thread {

    String cardNo;
    String pin;
    Login loginFrame;

    LoginThread(String cardNo, String pin, Login loginFrame) {
        this.cardNo = cardNo;
        this.pin = pin;
        this.loginFrame = loginFrame;
    }

    public void run() {
        try {
            System.out.println("Login thread started...");

            Thread.sleep(2000);

            Conn c = new Conn();
            String query = "select * from login where CardNo = '" + cardNo
                         + "' and Pin = '" + pin + "'";
            ResultSet rs = c.s.executeQuery(query);

            boolean success = rs.next();

            SwingUtilities.invokeLater(() -> {
                if (success) {
                    loginFrame.setVisible(false);
                    new Transactions(pin, loginFrame).setVisible(true);
                } else {
                    loginFrame.statusLabel.setForeground(java.awt.Color.RED);
                    loginFrame.statusLabel.setText("Incorrect Card Number or PIN.");
                    loginFrame.login.setEnabled(true);
                    loginFrame.clear.setEnabled(true);
                    loginFrame.signup.setEnabled(true);
                }
            });

            System.out.println("Login thread finished.");

        } catch (InterruptedException ie) {
            System.out.println("Login thread was interrupted!");
        } catch (Exception e) {
            System.out.println(e);
            SwingUtilities.invokeLater(() -> {
                loginFrame.statusLabel.setForeground(java.awt.Color.RED);
                loginFrame.statusLabel.setText("An error occurred. Please try again.");
                loginFrame.login.setEnabled(true);
                loginFrame.clear.setEnabled(true);
                loginFrame.signup.setEnabled(true);
            });
        }
    }
}
