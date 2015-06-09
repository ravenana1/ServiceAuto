package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ana on 22.04.2015.
 */
public class ServicesOffered {
    //lista tututor serviciilor (primeste tipul si lista serviciilor de acel tip)
    private HashMap<String, List<Operation>> servicesByType;

    public ServicesOffered(){
        this.servicesByType = new HashMap<String, List<Operation>>();
    }

    public ServicesOffered(HashMap<String, List<Operation>> servicesByType) {
        this.servicesByType = servicesByType;
    }

    public HashMap<String, List<Operation>> getServicesByType() {
        return servicesByType;
    }
}
