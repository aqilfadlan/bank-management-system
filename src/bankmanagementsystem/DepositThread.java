package bankmanagementsystem;

import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

// We extend Thread so this class IS a thread.
public class DepositThread extends Thread {

    String pin;
    String money;
    boolean success = false;

    // Loading dialog and the Deposit frame so the thread can update the UI.
    JDialog loadingDialog;
    Deposit depositFrame;

    DepositThread(String pin, String money, JDialog loadingDialog, Deposit depositFrame) {
        this.pin = pin;
        this.money = money;
        this.loadingDialog = loadingDialog;
        this.depositFrame = depositFrame;
    }

    public void run() {
        try {
            System.out.println("Deposit thread started...");

            // Pretend the DB takes 2 seconds (so you can SEE the thread working)
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

        // Close the loading dialog now that we are done.
        loadingDialog.dispose();

        // Show the result to the user.
        if (success) {
            JOptionPane.showMessageDialog(null, "Rs. " + money + " is deposited successfully.");
            depositFrame.setVisible(false);
            new Transactions(pin).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Deposit failed. Please try again.");
        }
    }
}
