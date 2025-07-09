package Fleet;

public class Delivery {
    private String packageId;
    private String origin;
    private String destination;
    private String assignedVehicleReg;
    private String assignedDriverId;
    private String eta;

    public Delivery(String packageId, String origin, String destination, String eta) {
        this.packageId = packageId;
        this.origin = origin;
        this.destination = destination;
        this.eta = eta;
        this.assignedVehicleReg = "None";
        this.assignedDriverId = "None";
    }

    // Getters
    public String getPackageId() { return packageId; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getAssignedVehicleReg() { return assignedVehicleReg; }
    public String getAssignedDriverId() { return assignedDriverId; }
    public String getEta() { return eta; }

    // Setters
    public void assign(String vehicleReg, String driverId) {
        this.assignedVehicleReg = vehicleReg;
        this.assignedDriverId = driverId;
    }

    @Override
    public String toString() {
        return String.format("Package ID: %s, From: %s, To: %s, ETA: %s, Vehicle: %s, Driver: %s",
                packageId, origin, destination, eta, assignedVehicleReg, assignedDriverId);
    }
}