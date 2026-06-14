package bankmanagementsystem;

import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

// We extend Thread so this class IS a thread.
public class FastCashThread extends Thread {

    String pin;
    String money;
    boolean success = false;
    boolean insufficient = false;

    JDialog loadingDialog;
    FastCash fastCashFrame;
    Login loginFrame;

    FastCashThread(String pin, String money, JDialog loadingDialog, FastCash fastCashFrame, Login loginFrame) {
        this.pin = pin;
        this.money = money;
        this.loadingDialog = loadingDialog;
        this.fastCashFrame = fastCashFrame;
        this.loginFrame = loginFrame;
    }

    public void run() {
        try {
            System.out.println("Fast cash thread started...");
            Thread.sleep(2000);

            Conn c = new Conn();

            // Query 1: work out the current balance.
            ResultSet rs = c.s.executeQuery("select * from bank where Pin = '" + pin + "'");
            int balance = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            // Not enough money: flag it and stop before the withdrawal.
            if (balance < Integer.parseInt(money)) {
                insufficient = true;
            } else {
                // Query 2: record the withdrawal.
                Date date = new Date();
                String query = "insert into bank values ('" + pin + "', '" + date
                             + "', 'Withdrawl', '" + money + "')";
                c.s.executeUpdate(query);
                success = true;
            }

            System.out.println("Fast cash thread finished.");
        } catch (InterruptedException ie) {
            System.out.println("Fast cash thread was interrupted!");
        } catch (Exception e) {
            System.out.println(e);
        }

        loadingDialog.dispose();
        if (insufficient) {
            JOptionPane.showMessageDialog(null, "Insufficient Balance");
            fastCashFrame.setVisible(false);
            new Transactions(pin, loginFrame).setVisible(true);
        } else if (success) {
            JOptionPane.showMessageDialog(null, "RM " + money + " is debited successfully");
            fastCashFrame.setVisible(false);
            new Transactions(pin, loginFrame).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Transaction failed. Please try again.");
        }
    }
}
