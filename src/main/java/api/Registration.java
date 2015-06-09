package api;

import java.io.Serializable;

/**
 * Created by ana on 09.06.2015.
 */
public class Registration implements Serializable{
    private String firstName;
    private String lastName;
    private String phone;
    private String carSerial;
    private String carBrand;
    private String carModel;
    private String objective;
    private String date;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCarSerial() {
        return carSerial;
    }

    public void setCarSerial(String carSerial) {
        this.carSerial = carSerial;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", carSerial='" + carSerial + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", carModel='" + carModel + '\'' +
                ", objective='" + objective + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
