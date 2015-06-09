package api;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ana on 22.04.2015.
 */
public class PieseOffered {
    //lista tututor pieselor (primeste tipul si lista pieselor de acel tip)
    private HashMap<String, List<String>> servicesByType;

    public PieseOffered() {
    }


    public PieseOffered(HashMap<String, List<String>> servicesByType) {
        this.servicesByType = servicesByType;
    }

    public HashMap<String, List<String>> getServicesByType() {
        return servicesByType;
    }
}
