package Fleet;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    private String driverId;
    private String name;
    private List<String> activityLog;

    public Driver(String driverId, String name) {
        this.driverId = driverId;
        this.name = name;
        this.activityLog = new ArrayList<>();
    }

    // Getters
    public String getDriverId() { return driverId; }
    public String getName() { return name; }
    public List<String> getActivityLog() { return activityLog; }

    public void addActivity(String activity) {
        this.activityLog.add(activity);
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s", driverId, name);
    }
}