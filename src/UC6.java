import java.util.*;

/**
 * UC6: Reservation Confirmation & Room Allocation
 * Processes queued booking requests and assigns rooms safely.
 */
public class UC6 {

    // Queue from UC5 representing booking requests
    private Queue<UC5.Reservation> bookingQueue;

    // Inventory: room type -> available count
    private Map<String, Integer> inventory;

    // Allocated rooms: room type -> set of assigned room IDs
    private Map<String, Set<String>> allocatedRooms;

    public UC6(Queue<UC5.Reservation> bookingQueue, Map<String, Integer> initialInventory) {
        this.bookingQueue = bookingQueue;
        this.inventory = initialInventory;
        this.allocatedRooms = new HashMap<>();
    }

    // Confirm reservations in FIFO order
    public void processReservations() {
        while (!bookingQueue.isEmpty()) {
            UC5.Reservation res = bookingQueue.poll();
            String roomType = res.getRoomType();

            int available = inventory.getOrDefault(roomType, 0);
            if (available > 0) {
                // Generate a unique room ID
                String roomID = generateRoomID(roomType);

                // Assign room
                allocatedRooms.computeIfAbsent(roomType, k -> new HashSet<>()).add(roomID);

                // Decrement inventory
                inventory.put(roomType, available - 1);

                System.out.println("Reservation confirmed: " + res.getGuestName() +
                        " -> Room Type: " + roomType + ", Room ID: " + roomID);
            } else {
                System.out.println("No availability for " + res.getGuestName() +
                        " requesting " + roomType);
            }
        }
    }

    // Generate a unique room ID based on room type and allocated count
    private String generateRoomID(String roomType) {
        Set<String> assigned = allocatedRooms.getOrDefault(roomType, new HashSet<>());
        int nextID = assigned.size() + 1;
        String roomID;
        do {
            roomID = roomType.substring(0, 3).toUpperCase() + String.format("%03d", nextID);
            nextID++;
        } while (assigned.contains(roomID));
        return roomID;
    }

    // Display current inventory
    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> Available: " + entry.getValue());
        }
    }

    // Display allocated rooms
    public void displayAllocatedRooms() {
        System.out.println("\nAllocated Rooms:");
        for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Main demo
    public static void main(String[] args) {
        // Simulate booking queue (from UC5)
        Queue<UC5.Reservation> queue = new LinkedList<>();
        queue.add(new UC5.Reservation("Alice", "Single"));
        queue.add(new UC5.Reservation("Bob", "Double"));
        queue.add(new UC5.Reservation("Charlie", "Suite"));
        queue.add(new UC5.Reservation("Diana", "Single"));

        // Initialize inventory
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);

        // Process reservations
        UC6 bookingSystem = new UC6(queue, inventory);
        bookingSystem.processReservations();

        // Display final inventory and allocations
        bookingSystem.displayInventory();
        bookingSystem.displayAllocatedRooms();
    }
}