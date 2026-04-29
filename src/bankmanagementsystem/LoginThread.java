package bankmanagementsystem;

import java.sql.ResultSet;

// We extend Thread so this class IS a thread.
public class LoginThread extends Thread {

    String cardNo;
    String pin;
    boolean success = false;   // result we can read after the thread finishes

    // Constructor: take the card number and pin from the Login screen
    LoginThread(String cardNo, String pin) {
        this.cardNo = cardNo;
        this.pin = pin;
    }

    // run() is what executes when we call start()
    public void run() {
        try {
            System.out.println("Login thread started...");

            // Pretend the DB takes 2 seconds (so you can SEE the thread working)
            Thread.sleep(2000);

            Conn c = new Conn();
            String query = "select * from login where CardNo = '" + cardNo
                         + "' and Pin = '" + pin + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                success = true;
            }

            System.out.println("Login thread finished.");

        } catch (InterruptedException ie) {
            // Someone called interrupt() on us
            System.out.println("Login thread was interrupted!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
