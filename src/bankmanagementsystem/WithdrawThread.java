package bankmanagementsystem;

import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class WithdrawThread extends Thread {

    String pin;
    String money;
    boolean success = false;

    JDialog loadingDialog;
    Withdraw withdrawFrame;

    WithdrawThread(String pin, String money, JDialog loadingDialog, Withdraw withdrawFrame) {
        this.pin = pin;
        this.money = money;
        this.loadingDialog = loadingDialog;
        this.withdrawFrame = withdrawFrame;
    }

    public void run() {
        try {
            System.out.println("Withdraw thread started...");
            Thread.sleep(2000);

            Conn c = new Conn();
            Date date = new Date();
            String query = "insert into bank values ('" + pin + "', '" + date
                         + "', 'Withdrawl', '" + money + "')";
            c.s.executeUpdate(query);
            success = true;

            System.out.println("Withdraw thread finished.");
        } catch (InterruptedException ie) {
            System.out.println("Withdraw thread was interrupted!");
        } catch (Exception e) {
            System.out.println(e);
        }

        loadingDialog.dispose();
        if (success) {
            JOptionPane.showMessageDialog(null, "Rs. " + money + " is withdrawn successfully.");
            withdrawFrame.setVisible(false);
            new Transactions(pin).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Withdraw failed. Please try again.");
        }
    }
}