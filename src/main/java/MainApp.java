import java.beans.*;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import api.Operation;
import org.springframework.boot.SpringApplication;

public class MainApp{
	
    public static void main( String[] args ){
        System.out.println("hello");
        try{
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

        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql;
        sql = "SELECT Type, Name FROM Piese ORDER BY Type;";
        ResultSet result = null;
        try {
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String currentType = "";
        String nextType;
        List<String> pieseByType = new ArrayList<>();
        HashMap<String, List<String>> piese = new HashMap<>();
        try {
            if(result.next()) {
                currentType = result.getString("Type");
                pieseByType.add(result.getString("Name"));
            }
            while (result.next()) {
                nextType = result.getString("Type");
                if(currentType.equals(nextType)){
                    pieseByType.add(result.getString("Name"));
                }
                else{
                    piese.put(currentType, pieseByType);
                    pieseByType = new ArrayList<>();
                    pieseByType.add(result.getString("Name"));
                    currentType = nextType;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        piese.put(currentType, pieseByType);

        System.out.println(piese);




    }
}