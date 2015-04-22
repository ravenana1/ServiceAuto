package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ana on 22.04.2015.
 */
public class ServicesOffered {
    //lista tututor serviciilor (primeste tipul si lista serviciilor de acel tip)
    private HashMap<String, List> servicesByType;

    public ServicesOffered(){
        this.servicesByType = new HashMap<String, List>();
    }

    public ServicesOffered(HashMap<String, List> servicesByType) {
        this.servicesByType = servicesByType;
    }

    public HashMap<String, List> getServicesByType() {
        return servicesByType;
    }
}
