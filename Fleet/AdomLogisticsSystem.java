package Fleet;

import java.util.Scanner;
import java.util.List;

public class AdomLogisticsSystem {
    private static final VehicleDatabase vehicleDB = new VehicleDatabase();
    private static final DriverManager driverManager = new DriverManager();
    private static final DeliveryTracker deliveryTracker = new DeliveryTracker();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeData();
        System.out.println("Welcome to the Adom Logistics Management System!");
        runMainMenu();
    }

    private static void initializeData() {
        // Pre-populate with some data for demonstration
        vehicleDB.addVehicle(new Vehicle("GT-1111-20", "Truck", 50000, 25.5));
        vehicleDB.addVehicle(new Vehicle("AS-2222-21", "Van", 25000, 10.2));
        vehicleDB.addVehicle(new Vehicle("WR-3333-19", "Truck", 120000, 30.0));

        driverManager.addDriver(new Driver("D001", "Kofi Annan"));
        driverManager.addDriver(new Driver("D002", "Ama Ata"));
        driverManager.addDriver(new Driver("D003", "Yaw Mensah"));

        deliveryTracker.addDelivery(new Delivery("PKG01", "Tema", "Accra", "2 hours"));
        deliveryTracker.addDelivery(new Delivery("PKG02", "Tema", "Takoradi", "6 hours"));
    }

    private static void runMainMenu() {
        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Vehicle Management");
            System.out.println("2. Driver Management");
            System.out.println("3. Delivery Management");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    vehicleMenu();
                    break;
                case "2":
                    driverMenu();
                    break;
                case "3":
                    deliveryMenu();
                    break;
                case "4":
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void vehicleMenu() {
        System.out.println("\n--- Vehicle Management ---");
        System.out.println("1. Add New Vehicle");
        System.out.println("2. View All Vehicles");
        System.out.println("3. Search for Vehicle by Registration (Binary Search)");
        System.out.println("4. View Vehicles Sorted by Mileage");
        System.out.print("Select an option: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                // Logic to add a vehicle
                System.out.print("Enter Registration Number: ");
                String reg = scanner.nextLine();
                System.out.print("Enter Type (Truck/Van): ");
                String type = scanner.nextLine();
                System.out.print("Enter Mileage: ");
                double mileage = Double.parseDouble(scanner.nextLine());
                System.out.print("Enter Fuel Usage (L/100km): ");
                double fuel = Double.parseDouble(scanner.nextLine());
                vehicleDB.addVehicle(new Vehicle(reg, type, mileage, fuel));
                System.out.println("Vehicle added successfully!");
                break;
            case "2":
                System.out.println("\n--- All Vehicles ---");
                vehicleDB.getAllVehicles().forEach(System.out::println);
                break;
            case "3":
                System.out.print("Enter Registration Number to search: ");
                String searchReg = scanner.nextLine();
                Vehicle found = vehicleDB.binarySearchByReg(searchReg);
                if (found != null) {
                    System.out.println("Vehicle Found: " + found);
                } else {
                    System.out.println("Vehicle not found.");
                }
                break;
            case "4":
                System.out.println("\n--- Vehicles Sorted by Mileage ---");
                List<Vehicle> sortedVehicles = vehicleDB.sortVehiclesByMileage();
                sortedVehicles.forEach(System.out::println);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void driverMenu() {
        System.out.println("\n--- Driver Management ---");
        driverManager.printAvailableDrivers();
    }

    private static void deliveryMenu() {
        System.out.println("\n--- Delivery Management ---");
        System.out.println("1. Add New Delivery to Queue");
        System.out.println("2. Dispatch Next Delivery");
        System.out.println("3. View Pending Deliveries");
        System.out.print("Select an option: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                System.out.print("Enter Package ID: ");
                String pkgId = scanner.nextLine();
                System.out.print("Enter Origin: ");
                String origin = scanner.nextLine();
                System.out.print("Enter Destination: ");
                String dest = scanner.nextLine();
                System.out.print("Enter ETA: ");
                String eta = scanner.nextLine();
                deliveryTracker.addDelivery(new Delivery(pkgId, origin, dest, eta));
                break;
            case "2":
                dispatchDelivery();
                break;
            case "3":
                deliveryTracker.printPendingDeliveries();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void dispatchDelivery() {
        if (!deliveryTracker.hasPendingDeliveries()) {
            System.out.println("No deliveries to dispatch.");
            return;
        }
        if (!driverManager.hasAvailableDrivers()) {
            System.out.println("No available drivers to assign.");
            return;
        }

        // Simple assignment: get next delivery, an available driver, and an unassigned vehicle
        Delivery delivery = deliveryTracker.dispatchNextDelivery();
        Driver driver = driverManager.assignDriver();
        
        Vehicle assignedVehicle = null;
        for (Vehicle v : vehicleDB.getAllVehicles()) {
            if (v.getDriverId().equals("Unassigned")) {
                assignedVehicle = v;
                break;
            }
        }
        
        if (assignedVehicle == null) {
            System.out.println("No unassigned vehicles available.");
            // Return driver and delivery to their queues
            driverManager.makeDriverAvailable(driver);
            deliveryTracker.addDelivery(delivery); // This adds to the back, which might not be desired
            return;
        }

        // Assign
        assignedVehicle.setDriverId(driver.getDriverId());
        delivery.assign(assignedVehicle.getRegistrationNumber(), driver.getDriverId());
        driver.addActivity("Dispatched for delivery " + delivery.getPackageId());

        System.out.println("\n--- DISPATCH CONFIRMATION ---");
        System.out.println("Delivery: " + delivery.getPackageId());
        System.out.println("Assigned Vehicle: " + assignedVehicle.getRegistrationNumber());
        System.out.println("Assigned Driver: " + driver.getName());
        System.out.println("----------------------------");
    }
}