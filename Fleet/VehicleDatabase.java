package Fleet;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VehicleDatabase {
    // Using a HashMap for efficient search by registration number (O(1) average)
    private Map<String, Vehicle> vehiclesByReg;

    public VehicleDatabase() {
        this.vehiclesByReg = new HashMap<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehiclesByReg.put(vehicle.getRegistrationNumber(), vehicle);
    }

    public Vehicle findVehicleByReg(String registrationNumber) {
        return vehiclesByReg.get(registrationNumber);
    }

    public boolean removeVehicle(String registrationNumber) {
        return vehiclesByReg.remove(registrationNumber) != null;
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehiclesByReg.values());
    }

    // Binary search implementation
    public Vehicle binarySearchByReg(String regNumber) {
        List<Vehicle> sortedVehicles = getAllVehicles();
        // Sort list by registration number before searching
        Collections.sort(sortedVehicles); 
        
        int low = 0;
        int high = sortedVehicles.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = sortedVehicles.get(mid).getRegistrationNumber().compareTo(regNumber);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return sortedVehicles.get(mid);
            }
        }
        return null; // Not found
    }
    
    // Sort vehicles by mileage using a merge sort approach
    public List<Vehicle> sortVehiclesByMileage() {
        List<Vehicle> vehicleList = getAllVehicles();
        vehicleList.sort(Comparator.comparingDouble(Vehicle::getMileage));
        return vehicleList;
    }
}
