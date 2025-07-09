package Fleet;

import java.util.Stack;

public class DriverManager {
    // A stack for available drivers (LIFO)
    private Stack<Driver> availableDrivers;

    public DriverManager() {
        this.availableDrivers = new Stack<>();
    }

    public void addDriver(Driver driver) {
        availableDrivers.push(driver);
    }

    public Driver assignDriver() {
        if (!availableDrivers.isEmpty()) {
            return availableDrivers.pop();
        }
        return null; // No drivers available
    }

    public void makeDriverAvailable(Driver driver) {
        availableDrivers.push(driver);
    }
    
    public boolean hasAvailableDrivers() {
        return !availableDrivers.isEmpty();
    }
    
    public void printAvailableDrivers() {
        if (availableDrivers.isEmpty()) {
            System.out.println("No drivers currently available.");
        } else {
            System.out.println("Available Drivers:");
            for (Driver driver : availableDrivers) {
                System.out.println("- " + driver);
            }
        }
    }
}
