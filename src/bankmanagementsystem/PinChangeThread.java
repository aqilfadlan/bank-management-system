package bankmanagementsystem;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

// We implement Runnable instead of extending Thread.
// Following the "NewThread" pattern: this class holds its OWN Thread,
// creates it with new Thread(this, name), and starts it in the constructor.
public class PinChangeThread implements Runnable {

    String name;        // a name for this thread (handy for debugging/printing)
    Thread thread;      // the actual Thread that runs this Runnable

    String oldPin;      // the pin currently logged in with
    String newPin;      // the new pin to set
    boolean success = false;   // result we can read after the thread finishes

    JDialog loadingDialog;
    PinChange pinChangeFrame;
    Login loginFrame;

    PinChangeThread(String oldPin, String newPin, JDialog loadingDialog, PinChange pinChangeFrame, Login loginFrame) {
        this.oldPin = oldPin;
        this.newPin = newPin;
        this.loadingDialog = loadingDialog;
        this.pinChangeFrame = pinChangeFrame;
        this.loginFrame = loginFrame;

        this.name = "PinChangeThread";
        thread = new Thread(this, name);
        System.out.println("A new thread: " + thread + " is created\n");
        thread.start();
    }

    // run() is what executes when thread.start() is called above
    @Override
    public void run() {
        try {
            System.out.println(name + " started...");

            // Pretend the DB takes 2 seconds (so you can SEE the thread working)
            Thread.sleep(2000);

            Conn c = new Conn();
            // The pin is stored in three tables, so we update all three.
            String query1 = "update bank set pin = '" + newPin + "' where pin = '" + oldPin + "'";
            String query2 = "update login set pin = '" + newPin + "' where pin = '" + oldPin + "'";
            String query3 = "update signupthree set pin = '" + newPin + "' where pin = '" + oldPin + "'";

            c.s.executeUpdate(query1);
            c.s.executeUpdate(query2);
            c.s.executeUpdate(query3);
            success = true;

            System.out.println(name + " finished.");

        } catch (InterruptedException ie) {
            // Someone called interrupt() on us
            System.out.println(name + " was interrupted!");
        } catch (Exception e) {
            System.out.println(e);
        }

        // Close the dialog and react to the result.
        loadingDialog.dispose();
        if (success) {
            JOptionPane.showMessageDialog(null, "Your PIN has been changed successfully");
            pinChangeFrame.setVisible(false);
            new Transactions(newPin, loginFrame).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "PIN change failed. Please try again.");
        }
    }
}
