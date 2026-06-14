package bankmanagementsystem;

import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

// We extend Thread so this class IS a thread.
public class DepositThread extends Thread {

    String pin;
    String money;
    boolean success = false;

    JDialog loadingDialog;
    Deposit depositFrame;
    Login loginFrame;

    DepositThread(String pin, String money, JDialog loadingDialog, Deposit depositFrame, Login loginFrame) {
        this.pin = pin;
        this.money = money;
        this.loadingDialog = loadingDialog;
        this.depositFrame = depositFrame;
        this.loginFrame = loginFrame;
    }

    public void run() {
        try {
            System.out.println("Deposit thread started...");
            Thread.sleep(2000);

            Conn c = new Conn();
            Date date = new Date();
            String query = "insert into bank values ('" + pin + "', '" + date
                         + "', 'Deposit', '" + money + "')";
            c.s.executeUpdate(query);
            success = true;

            System.out.println("Deposit thread finished.");
        } catch (InterruptedException ie) {
            System.out.println("Deposit thread was interrupted!");
        } catch (Exception e) {
            System.out.println(e);
        }

        loadingDialog.dispose();
        if (success) {
            JOptionPane.showMessageDialog(null, "RM " + money + " is deposited successfully.");
            depositFrame.setVisible(false);
            new Transactions(pin, loginFrame).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Deposit failed. Please try again.");
        }
    }
}
