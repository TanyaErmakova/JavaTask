import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Using of a singleton pattern for Connection creation

public class DBConnection {
    private static Connection con;
    static {
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "db", "Q1w2e3r4");
            System.out.println("Connection Succeed");
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            System.out.println(e.getMessage());

        }
    }

    public static Connection getConnection(){
        return con;
    }

    public static void close(){
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
};
