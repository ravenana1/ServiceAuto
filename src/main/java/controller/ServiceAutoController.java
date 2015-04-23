package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import api.CarState;
import api.Operation;
import api.PieseOffered;
import api.ServicesOffered;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAutoController {

    @Autowired
    private Connection connection;

    @RequestMapping(method = RequestMethod.GET, value = "/aboutus")
    public String getAboutUs() {
        return new String("Detalii despre firma");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contact")
    public String getContact() {
        return new String("Contact firma");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/serviciiOferite")
    public ServicesOffered getServiciiOferite() {

        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql;
        sql = "SELECT Type, Name, Time FROM Operations ORDER BY Type;";
        ResultSet result = null;
        try {
            result = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String currentType = "";
        String nextType;
        List<Operation> operationsByType = new ArrayList<>();
        HashMap<String, List<Operation>> operations = new HashMap<>();
        try {
            if(result.next()) {
                currentType = result.getString("Type");
                operationsByType.add(new Operation(result.getString("Name"), result.getString("Time")));
            }
            while (result.next()) {
                nextType = result.getString("Type");
                if(currentType.equals(nextType)){
                    operationsByType.add(new Operation(result.getString("Name"), result.getString("Time")));
                }
                else{
                    operations.put(currentType, operationsByType);
                    operationsByType = new ArrayList<>();
                    operationsByType.add(new Operation(result.getString("Name"), result.getString("Time")));
                    currentType = nextType;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        operations.put(currentType, operationsByType);

        return new ServicesOffered(operations);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pieseOferite")
    public PieseOffered getPieseOferite() {

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

        return new PieseOffered(piese);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/statusMasina")
    public CarState getCarState(@RequestParam(value = "registrationID") String registrationID) {
        return new CarState();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/registerCar")
    public Integer registerCar(@RequestParam(value = "firstname") String firstname,
                                @RequestParam(value = "lastname") String lastname,
                                @RequestParam(value = "carBrand") String carBrand,
                                @RequestParam(value = "carModel") String carModel,
                                @RequestParam(value = "objective") String objective,
                                @RequestParam(value = "date") String date) {
        int registrationID = 0;
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO Clients (FirstName, LastName) VALUES (" + firstname + ", " + lastname + ");";
        try{
            statement.execute(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }

        sql = "SELECT id FROM Clients WHERE FirstName = " + firstname + " and LastName = " + lastname + ";";
        int id;
        ResultSet result = null;
        try {
            result = statement.executeQuery(sql);

            if(result.next()){
                id = result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }






        return registrationID;
    }

}
