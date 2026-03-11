import java.io.*;
import java.util.*;

/**
 * UC12: Data Persistence & System Recovery
 * Demonstrates saving and restoring booking and inventory state using file-based persistence.
 */
public class UC12 {

    private static Map<String, Integer> inventory = new HashMap<>();
    private static List<String> bookingHistory = new ArrayList<>();

    private static final String INVENTORY_FILE = "inventory.dat";
    private static final String HISTORY_FILE = "bookingHistory.dat";

    // Initialize default inventory if no persisted data exists
    static {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    // Save inventory and booking history to files
    public static void saveState() {
        try (ObjectOutputStream invOut = new ObjectOutputStream(new FileOutputStream(INVENTORY_FILE));
             ObjectOutputStream histOut = new ObjectOutputStream(new FileOutputStream(HISTORY_FILE))) {

            invOut.writeObject(inventory);
            histOut.writeObject(bookingHistory);
            System.out.println("System state saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving system state: " + e.getMessage());
        }
    }

    // Load inventory and booking history from files
    @SuppressWarnings("unchecked")
    public static void loadState() {
        try (ObjectInputStream invIn = new ObjectInputStream(new FileInputStream(INVENTORY_FILE));
             ObjectInputStream histIn = new ObjectInputStream(new FileInputStream(HISTORY_FILE))) {

            inventory = (Map<String, Integer>) invIn.readObject();
            bookingHistory = (List<String>) histIn.readObject();
            System.out.println("System state loaded successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("Persistence files not found. Using default state.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading system state: " + e.getMessage());
        }
    }

    // Simulate booking a room
    public static void bookRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);
        if (available <= 0) {
            System.out.println("No " + roomType + " rooms available.");
            return;
        }
        inventory.put(roomType, available - 1);
        String bookingId = roomType.substring(0, 1) + (available);
        bookingHistory.add(bookingId);
        System.out.println("Booked " + roomType + " room. Booking ID: " + bookingId);
    }

    public static void main(String[] args) {
        // Load persisted state on startup
        loadState();

        // Simulate some bookings
        bookRoom("Single");
        bookRoom("Double");

        // Display current state
        System.out.println("Current Inventory: " + inventory);
        System.out.println("Booking History: " + bookingHistory);

        // Save state before shutdown
        saveState();
    }
}