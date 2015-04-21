import java.sql.*;

import org.springframework.boot.SpringApplication;

public class MainApp{
	
    public static void main( String[] args ){
       /* try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
	    }

        Connection connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Licenta2", "root", "root");
        }catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql;
        sql = "INSERT into Piese (Type, Name) VALUES ('bau', 'baubau');";
        ResultSet rs = null;
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sql = "SELECT id, Type, Name from Piese;";
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            while (rs.next()) {
                System.out.println(rs.getString("Type"));
                System.out.println(rs.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
    }
}