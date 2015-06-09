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
    public CarState getCarState(@RequestParam(value = "registrationID") String registrationID) {
        return new CarState();
    }
    //TODO verify if the params are correct
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
        LOG.error("------------" + registration.toString());

        String sql = "INSERT INTO Clienti (FirstName, LastName) VALUES (" + registration.getFirstName()
                + ", " + registration.getLastName() + ");";
        try{
            statement.execute(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }

        sql = "SELECT id FROM Clienti WHERE FirstName = " + registration.getFirstName()
                + " and LastName = " + registration.getLastName() + ";";
        int idClient = -1;
        ResultSet result = null;
        try {
            result = statement.executeQuery(sql);
            if(result.next()){
                idClient = result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO Masini (SerieMasina, Brand, Model, fk_Client) " +
                "VALUES(" + registration.getCarSerial() + ", " + registration.getCarBrand() + ", " +
                registration.getCarModel() + ", " + idClient + ");";
        try{
            statement.execute(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }

        int idCar = -1;
        sql = "SELECT id FROM Masini WHERE  = " + registration.getCarSerial() + ";";
        try {
            result = statement.executeQuery(sql);
            if(result.next()){
                idCar = result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Random r = new Random();
        registrationID = r.nextInt();

        while(!allRegIDs.contains(registrationID)){
            registrationID = r.nextInt();
        }
        allRegIDs.add(registrationID);


        sql = "INSERT INTO Registration (registrationID, Date, Objective, fk_Masina) " +
                "VALUES(" + registrationID + ", " + registration.getDate() + ", " + registration.getDate()
                        + ", " + registration.getObjective() + ", " + idCar + ");";
        try{
            statement.execute(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }

        return registrationID;
    }

}
