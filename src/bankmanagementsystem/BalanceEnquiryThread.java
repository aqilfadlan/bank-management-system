package bankmanagementsystem;

import java.sql.ResultSet;
import javax.swing.JDialog;
import javax.swing.JLabel;

// We implement Runnable instead of extending Thread.
// Following the "NewThread" pattern: this class holds its OWN Thread,
// creates it with new Thread(this, name), and starts it in the constructor.
public class BalanceEnquiryThread implements Runnable {

    String name;        // a name for this thread (handy for debugging/printing)
    Thread thread;      // the actual Thread that runs this Runnable

    String pin;

    JDialog loadingDialog;
    JLabel balanceLabel;   // the label we fill in once the balance is calculated

    // Constructor: take the pin from the BalanceEnquiry screen.
    // It builds its own Thread (passing "this" as the Runnable) and starts it,
    // so the caller just does "new BalanceEnquiryThread(...)" and nothing else.
    BalanceEnquiryThread(String pin, JDialog loadingDialog, JLabel balanceLabel) {
        this.pin = pin;
        this.loadingDialog = loadingDialog;
        this.balanceLabel = balanceLabel;

        this.name = "BalanceEnquiryThread";
        thread = new Thread(this, name);
        System.out.println("A new thread: " + thread + " is created\n");
        thread.start();
    }

    // run() is what executes when thread.start() is called above
    @Override
    public void run() {
        int balance = 0;

        try {
            System.out.println(name + " started...");

            // Pretend the DB takes 2 seconds (so you can SEE the thread working)
            Thread.sleep(2000);

            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from bank where Pin = '" + pin + "'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            System.out.println(name + " finished.");

        } catch (InterruptedException ie) {
            // Someone called interrupt() on us
            System.out.println(name + " was interrupted!");
        } catch (Exception e) {
            System.out.println(e);
        }

        // Close the dialog and show the calculated balance.
        loadingDialog.dispose();
        balanceLabel.setText("Your Current Account Balance is RM " + balance);
    }
}
