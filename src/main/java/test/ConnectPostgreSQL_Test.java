package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectPostgreSQL_Test {
    private final String url = "jdbc:postgresql://hadoop001:5432/testdb";
    private final String user = "postgres";
    private final String password = "123456";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConnectPostgreSQL_Test connectPostgreSQLTest = new ConnectPostgreSQL_Test();
        connectPostgreSQLTest.connect();
    }
}
