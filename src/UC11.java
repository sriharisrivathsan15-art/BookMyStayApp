/**
 * UC11: Concurrent Booking Simulation (Thread Safety)
 * Demonstrates safe room allocation under multiple concurrent booking requests.
 */
import java.util.*;

public class UC11 {

    // Shared inventory for all threads
    private static Map<String, Integer> inventory = new HashMap<>();
    private static Map<String, Set<String>> allocatedRooms = new HashMap<>();

    static {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);

        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());
    }

    // Synchronized booking method for thread safety
    public static synchronized String bookRoom(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);
        if (available <= 0) {
            System.out.println(Thread.currentThread().getName() + " tried booking " + roomType + ": No rooms available.");
            return null;
        }

        String roomId = roomType.substring(0, 1) + (available);
        allocatedRooms.get(roomType).add(roomId);
        inventory.put(roomType, available - 1);

        System.out.println(Thread.currentThread().getName() + " booked " + roomType + " room. Room ID: " + roomId);
        return roomId;
    }

    public static void main(String[] args) {
        // Runnable task simulating a guest booking
        Runnable guestTask = () -> {
            bookRoom("Single");
            bookRoom("Double");
            bookRoom("Suite");
        };

        // Simulate multiple concurrent guests
        Thread guest1 = new Thread(guestTask, "Guest-1");
        Thread guest2 = new Thread(guestTask, "Guest-2");
        Thread guest3 = new Thread(guestTask, "Guest-3");

        // Start threads
        guest1.start();
        guest2.start();
        guest3.start();

        try {
            guest1.join();
            guest2.join();
            guest3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nFinal Inventory State: " + inventory);
        System.out.println("Allocated Rooms: " + allocatedRooms);
    }
}