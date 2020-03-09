package test;

import java.sql.*;

public class QueryDataFromPosrgreSQL {

    public int getCompanyCount(){
        String sql = "select count(*) from company";
        ConnectPostgreSQL postgreSQL = new ConnectPostgreSQL();
        Connection conn = postgreSQL.connect();
        int count =0 ;
        try {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
             count = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }


    public static void main(String[] args) {
        QueryDataFromPosrgreSQL queryDataFromPosrgreSQL = new QueryDataFromPosrgreSQL();
        int count = queryDataFromPosrgreSQL.getCompanyCount();
        System.out.println("company count is : "+ count);
    }
}
