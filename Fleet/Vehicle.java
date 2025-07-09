package Fleet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Vehicle implements Comparable<Vehicle> {
    private String registrationNumber;
    private String type; // "Truck" or "Van"
    private double mileage;
    private double fuelUsage; // Liters per 100km
    private String driverId;
    private List<Map<String, Object>> maintenanceHistory;

    public Vehicle(String registrationNumber, String type, double mileage, double fuelUsage) {
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.mileage = mileage;
        this.fuelUsage = fuelUsage;
        this.driverId = "Unassigned";
        this.maintenanceHistory = new ArrayList<>();
    }

    // Getters
    public String getRegistrationNumber() { return registrationNumber; }
    public String getType() { return type; }
    public double getMileage() { return mileage; }
    public double getFuelUsage() { return fuelUsage; }
    public String getDriverId() { return driverId; }
    public List<Map<String, Object>> getMaintenanceHistory() { return maintenanceHistory; }

    // Setters
    public void setDriverId(String driverId) { this.driverId = driverId; }
    public void addMileage(double miles) { this.mileage += miles; }
    public void addMaintenanceRecord(Map<String, Object> record) {
        this.maintenanceHistory.add(record);
    }

    @Override
    public String toString() {
        return String.format("Reg: %s, Type: %s, Mileage: %.1f km, Fuel Usage: %.1f L/100km, Driver: %s",
                registrationNumber, type, mileage, fuelUsage, driverId);
    }

    @Override
    public int compareTo(Vehicle other) {
        return this.registrationNumber.compareTo(other.getRegistrationNumber());
    }
}