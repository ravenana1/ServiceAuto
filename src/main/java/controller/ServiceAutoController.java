package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ServiceAutoController {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceAutoController.class);
    private ArrayList<Integer> allRegIDs = new ArrayList<Integer>();

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
    @ResponseBody
    public CarState getCarState(@RequestParam(value = "registrationID") String registrationID) {
        Statement statement = null;

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "INSERT INTO Clienti (FirstName, LastName) VALUES ('bubu', 'lulu');";
        try{
            statement.execute(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }

        return new CarState("masina", "masina", null);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registerCar")
    @ResponseBody
    public Integer registerCar(@RequestBody Registration registration) {

        int registrationID = 0;
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOG.error("\n------------" + registration.toString());

        String sql = "INSERT INTO Clienti (FirstName, LastName, Phone) VALUES ('" + registration.getFirstName()
                + "', '" + registration.getLastName() + "', '" + registration.getPhone()
                +"');";
        try{
           // LOG.error("\n------------Execut");
            statement.execute(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }

        sql = "SELECT id FROM Clienti WHERE FirstName='" + registration.getFirstName()
                + "' and LastName='" + registration.getLastName() + "';";
        int idClient = -1;
        ResultSet result = null;
        try {
            LOG.error("\n------------Execut2");
            result = statement.executeQuery(sql);
            if(result.next()){
                idClient = result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LOG.error("\n------------ID CLIENT: " + idClient);

        sql = "INSERT INTO Masini (SerieMasina, Brand, Model, fk_ClientM) " +
                "VALUES('" + registration.getCarSerial() + "', '" + registration.getCarBrand() + "', '" +
                registration.getCarModel() + "', " + idClient + ");";
        try{
            LOG.error("\n------------Execut3");
            statement.execute(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }

        int idCar = -1;
        sql = "SELECT id FROM Masini WHERE SerieMasina= '" + registration.getCarSerial() + "';";
        try {
            result = statement.executeQuery(sql);
            LOG.error("\n------------Execut4");
            if(result.next()){
                idCar = result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOG.error("\n------------ID Masina: " + idCar);

        Random r = new Random();
        registrationID = r.nextInt();

        allRegIDs.add(registrationID);

        sql = "INSERT INTO Registration (registrationID, StareMasina, Date, Objective, fk_MasinaR) " +
                "VALUES(" + registrationID + ", 'Just Registered', '"
                + registration.getDate() + "', '" + registration.getObjective() + "', " + idCar + ");";
        try{
            statement.execute(sql);
            LOG.error("\n------------Execut5");
        }catch(SQLException e){
            e.printStackTrace();
        }

        return registrationID;
    }

}
