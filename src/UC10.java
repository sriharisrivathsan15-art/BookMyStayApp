/**
 * UC10: Booking Cancellation & Inventory Rollback
 * Demonstrates safe cancellation of confirmed bookings with inventory restoration.
 */
import java.util.*;

public class UC10 {

    // Simulate inventory counts for room types
    private static Map<String, Integer> inventory = new HashMap<>();
    // Track allocated room IDs for each room type
    private static Map<String, Stack<String>> allocatedRooms = new HashMap<>();
    // Track confirmed reservations
    private static List<String> bookingHistory = new ArrayList<>();

    // Initialize inventory and allocated structures
    static {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);

        allocatedRooms.put("Single", new Stack<>());
        allocatedRooms.put("Double", new Stack<>());
        allocatedRooms.put("Suite", new Stack<>());
    }

    // Simulate booking a room
    public static String bookRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);
        if (available <= 0) {
            System.out.println("No " + roomType + " rooms available for booking.");
            return null;
        }

        // Generate a simple room ID
        String roomId = roomType.substring(0, 1) + (available);
        allocatedRooms.get(roomType).push(roomId);
        inventory.put(roomType, available - 1);
        bookingHistory.add(roomId);

        System.out.println("Booked " + roomType + " room. Room ID: " + roomId);
        return roomId;
    }

    // Cancel a booking
    public static void cancelBooking(String roomId) {
        if (!bookingHistory.contains(roomId)) {
            System.out.println("Cancellation failed: Reservation does not exist.");
            return;
        }

        // Find room type from room ID prefix
        String roomType = switch (roomId.charAt(0)) {
            case 'S' -> "Single";
            case 'D' -> "Double";
            case 'U' -> "Suite";
            default -> null;
        };

        if (roomType == null || allocatedRooms.get(roomType).isEmpty()) {
            System.out.println("Cancellation failed: Invalid room ID or state.");
            return;
        }

        // LIFO rollback using Stack
        String lastAllocated = allocatedRooms.get(roomType).pop();
        if (!lastAllocated.equals(roomId)) {
            System.out.println("Warning: Room ID mismatch during rollback!");
        }

        inventory.put(roomType, inventory.get(roomType) + 1);
        bookingHistory.remove(roomId);

        System.out.println("Cancelled booking for " + roomType + " room. Room ID: " + roomId);
        System.out.println("Updated inventory: " + inventory);
    }

    public static void main(String[] args) {
        // Book some rooms
        String r1 = bookRoom("Single");
        String r2 = bookRoom("Suite");
        String r3 = bookRoom("Double");

        System.out.println("\nAttempting cancellations...\n");

        // Cancel bookings
        cancelBooking(r2); // Cancel Suite
        cancelBooking("FakeID"); // Attempt invalid cancellation
        cancelBooking(r1); // Cancel Single
    }
}