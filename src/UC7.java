import java.util.*;

/**
 * UC7: Add-On Service Selection
 * Associates optional services with confirmed reservations.
 */
public class UC7 {

    // Map reservation ID -> list of selected services
    private Map<String, List<Service>> reservationServices;

    public UC7() {
        reservationServices = new HashMap<>();
    }

    // Add service(s) to a reservation
    public void addService(String reservationID, Service service) {
        reservationServices.computeIfAbsent(reservationID, k -> new ArrayList<>()).add(service);
        System.out.println("Added service " + service.getName() + " to reservation " + reservationID);
    }

    // Calculate total add-on cost for a reservation
    public double calculateTotalCost(String reservationID) {
        List<Service> services = reservationServices.getOrDefault(reservationID, Collections.emptyList());
        double total = 0.0;
        for (Service s : services) {
            total += s.getPrice();
        }
        return total;
    }

    // Display services for all reservations
    public void displayAllServices() {
        System.out.println("\nAdd-On Services for Reservations:");
        for (Map.Entry<String, List<Service>> entry : reservationServices.entrySet()) {
            System.out.print("Reservation " + entry.getKey() + " -> ");
            List<Service> services = entry.getValue();
            for (Service s : services) {
                System.out.print(s.getName() + " ($" + s.getPrice() + "), ");
            }
            System.out.println();
        }
    }

    // Simple Service class
    public static class Service {
        private String name;
        private double price;

        public Service(String name, double price) {
            this.name = name;
            this.price = price;
        }
        public String getName() { return name; }
        public double getPrice() { return price; }
    }

    // Demo
    public static void main(String[] args) {
        UC7 addOnManager = new UC7();

        // Example reservation IDs from UC6
        String res1 = "SNG001";
        String res2 = "DBL001";

        // Add services
        addOnManager.addService(res1, new Service("Breakfast", 15.0));
        addOnManager.addService(res1, new Service("Airport Pickup", 25.0));
        addOnManager.addService(res2, new Service("Spa Package", 50.0));

        // Display services
        addOnManager.displayAllServices();

        // Calculate total cost for a reservation
        System.out.println("\nTotal add-on cost for " + res1 + ": $" + addOnManager.calculateTotalCost(res1));
    }
}