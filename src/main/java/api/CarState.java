package api;

import java.util.List;

/**
 * Created by ana on 22.04.2015.
 */
public class CarState {
    private String carState;
    private String estimatedPrice;
    private List<String> operationsToBeDone;

    public CarState() {}

    public CarState(String carState, String estimatedPrice, List<String> operationsToBeDone) {
        this.carState = carState;
        this.estimatedPrice = estimatedPrice;
        this.operationsToBeDone = operationsToBeDone;
    }


    public String getCarState() {
        return carState;
    }

    public String getEstimatedPrice() {
        return estimatedPrice;
    }

    public List<String> getOperationsToBeDone() {
        return operationsToBeDone;
    }
}
