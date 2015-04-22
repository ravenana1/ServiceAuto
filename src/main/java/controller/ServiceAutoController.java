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
        sql = "SELECT Type, Name, Time AS Duration FROM Operations;";
        ResultSet result = null;
        ResultSet urm = null;
        try {
                        result = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String currentType = "";
        List<Operation> operationsByType = new ArrayList<>();
        HashMap<String, List<Operation>> operations = new HashMap<>();
        try {
            while (result.next()) {

                if(result.getString("Type") == result.getString("Type")){
                    operationsByType.add(new Operation(result.getString("Name"), result.getString("Time")));
                }
                else{
                    operations.put(result.getString("Type"), operationsByType);
                    operationsByType = new ArrayList<>();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ServicesOffered();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pieseOferite")
    public PieseOffered getPieseOferite() {
        return new PieseOffered();
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
        int number = 0;
        return new Integer(number);
    }

}
