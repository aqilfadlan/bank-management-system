package bankmanagementsystem;
// There are mainly 5 important steps in Java Database Connectivity
//1) Register the driver
//2) Create Connection
//3) Create Statement
//4) Execute Query
//5) Close Connection
import java.sql.*;
public class Conn {

    Connection c;
    Statement s;
    public Conn(){

        try {
            c=DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem","root","1234");
            s = c.createStatement();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
