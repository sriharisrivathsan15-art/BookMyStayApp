import java.util.HashMap;
import java.util.Map;

/**
 * UC3: Centralized Room Inventory Management
 * This class demonstrates how to manage hotel room availability using a centralized HashMap.
 * Provides methods to add room types, check availability, and update room counts.
 */
public class UC3 {

    // Centralized inventory: maps room type names to available room counts
    private Map<String, Integer> roomInventory;

    // Constructor: initializes inventory
    public UC3() {
        roomInventory = new HashMap<>();
    }

    // Add a new room type with initial availability
    public void addRoomType(String roomType, int count) {
        roomInventory.put(roomType, count);
    }

    // Get current availability of a room type
    public int getAvailability(String roomType) {
        return roomInventory.getOrDefault(roomType, 0);
    }

    // Book a room of a given type, returns true if successful
    public boolean bookRoom(String roomType) {
        int available = roomInventory.getOrDefault(roomType, 0);
        if (available > 0) {
            roomInventory.put(roomType, available - 1);
            return true;
        }
        return false; // no rooms available
    }

    // Cancel a booking for a given room type
    public void cancelBooking(String roomType) {
        int available = roomInventory.getOrDefault(roomType, 0);
        roomInventory.put(roomType, available + 1);
    }

    // Display current inventory state
    public void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (String roomType : roomInventory.keySet()) {
            System.out.println(roomType + ": " + roomInventory.get(roomType) + " available");
        }
    }

    // Main method to demonstrate usage
    public static void main(String[] args) {
        UC3 inventory = new UC3();

        // Initialize room types
        inventory.addRoomType("Single", 10);
        inventory.addRoomType("Double", 5);
        inventory.addRoomType("Suite", 2);

        // Display inventory
        inventory.displayInventory();

        // Book some rooms
        System.out.println("\nBooking a Double room...");
        if (inventory.bookRoom("Double")) {
            System.out.println("Booking successful!");
        } else {
            System.out.println("No rooms available.");
        }

        // Display updated inventory
        inventory.displayInventory();
    }
}
    }
}