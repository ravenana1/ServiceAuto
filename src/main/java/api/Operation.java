package api;

/**
 * Created by ana on 22.04.2015.
 */
public class Operation{
    private String name;
    private String time;

    public Operation() {
    }

    public Operation(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String toString(){
        return name + " " + time;
    }


}
