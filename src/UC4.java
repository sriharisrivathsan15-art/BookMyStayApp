import java.util.HashMap;
import java.util.Map;

/**
 * UC4: Room Search & Availability Check
 * Demonstrates read-only search of available rooms from centralized inventory.
 */
public class UC4 {

    // Centralized inventory: room type -> available count
    private Map<String, Integer> roomInventory;

    // Room details: room type -> price per night
    private Map<String, String> roomDetails;

    public UC4() {
        // Initialize inventory and details
        roomInventory = new HashMap<>();
        roomDetails = new HashMap<>();
    }

    // Add room type with availability and details
    public void addRoomType(String roomType, int availability, String details) {
        roomInventory.put(roomType, availability);
        roomDetails.put(roomType, details);
    }

    // Read-only search: display available rooms
    public void searchAvailableRooms() {
        System.out.println("Available Rooms:");
        for (String roomType : roomInventory.keySet()) {
            int available = roomInventory.get(roomType);
            if (available > 0) { // only show rooms with availability
                System.out.println(roomType + " - " + available + " available - " + roomDetails.get(roomType));
            }
        }
    }

    // Main method to demonstrate UC4
    public static void main(String[] args) {
        UC4 searchService = new UC4();

        // Initialize rooms
        searchService.addRoomType("Single", 10, "1 Bed, Free WiFi, $50/night");
        searchService.addRoomType("Double", 0, "2 Beds, Free WiFi, $80/night");
        searchService.addRoomType("Suite", 2, "King Bed, Sea View, $150/night");

        // Search available rooms
        searchService.searchAvailableRooms();
    }
}