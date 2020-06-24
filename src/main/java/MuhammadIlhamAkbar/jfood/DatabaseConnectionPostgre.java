package MuhammadIlhamAkbar.jfood;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * <h1>JFood Program based on Object Oriented Programming<h1>
 * This DatabaseConnectionPostgre Class is used to connect intellij to database postgres
 * <p>
 * @author Muhammad Ilham Akbar
 * @version 2020-06-06
 */

public class DatabaseConnectionPostgre {
    protected static Connection connection() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jfood", "postgres", "12345");
            System.out.println("Opened Database Succesfully");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);

        }
        return c;
    }
}
