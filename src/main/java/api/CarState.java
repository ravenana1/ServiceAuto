package api;

import java.util.List;

/**
 * Created by ana on 22.04.2015.
 */
public class CarState {
    private String carState;
    private String estimatedPrice;
    private String date;

    public CarState() {
    }

    public CarState(String carState, String estimatedPrice, String date) {
        this.carState = carState;
        this.estimatedPrice = estimatedPrice;
        this.date = date;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(String estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
