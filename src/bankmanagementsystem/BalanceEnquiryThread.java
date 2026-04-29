package bankmanagementsystem;

import java.sql.ResultSet;
import javax.swing.JDialog;
import javax.swing.JLabel;

// We extend Thread so this class IS a thread.
public class BalanceEnquiryThread extends Thread {

    String pin;
    int balance = 0;

    // Loading dialog and the label we will fill in once the balance is known.
    JDialog loadingDialog;
    JLabel resultLabel;

    BalanceEnquiryThread(String pin, JDialog loadingDialog, JLabel resultLabel) {
        this.pin = pin;
        this.loadingDialog = loadingDialog;
        this.resultLabel = resultLabel;
    }

    public void run() {
        try {
            System.out.println("Balance enquiry thread started...");

            // Pretend the DB takes 2 seconds (so you can SEE the thread working)
            Thread.sleep(2000);

            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(
                "select * from bank where Pin = '" + pin + "'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            System.out.println("Balance enquiry thread finished.");

        } catch (InterruptedException ie) {
            System.out.println("Balance enquiry thread was interrupted!");
        } catch (Exception e) {
            System.out.println(e);
        }

        // Close the loading dialog and write the balance into the label.
        loadingDialog.dispose();
        resultLabel.setText("Your Current Account Balance is Rs. " + balance);
    }
}
