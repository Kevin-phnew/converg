package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectPostgreSQL {

    private final  String url = "jdbc:postgresql://hadoop001/testdb";
    private final  String user = "postgres";
    private final  String password = "123456";

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

    public static void main(String[] args) {
        ConnectPostgreSQL sql = new ConnectPostgreSQL();
        sql.connect();

    }
}
