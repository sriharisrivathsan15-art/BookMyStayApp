import java.util.LinkedList;
import java.util.Queue;

/**
 * UC5: Booking Request (First-Come-First-Served)
 * Demonstrates fair request intake using a queue.
 */
public class UC5 {

    // Queue to hold booking requests in FIFO order
    private Queue<Reservation> bookingQueue;

    public UC5() {
        bookingQueue = new LinkedList<>();
    }

    // Accept a booking request from a guest
    public void submitBookingRequest(Reservation reservation) {
        bookingQueue.add(reservation);
        System.out.println("Booking request submitted: " + reservation.getGuestName() +
                " for room type " + reservation.getRoomType());
    }

    // Display all queued booking requests
    public void displayQueuedRequests() {
        System.out.println("\nCurrent Booking Queue:");
        for (Reservation r : bookingQueue) {
            System.out.println(r.getGuestName() + " - " + r.getRoomType());
        }
    }

    // Reservation class representing a guest booking request
    public static class Reservation {
        private String guestName;
        private String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }
    }

    // Main method to demonstrate UC5
    public static void main(String[] args) {
        UC5 bookingSystem = new UC5();

        // Simulate incoming booking requests
        bookingSystem.submitBookingRequest(new Reservation("Alice", "Single"));
        bookingSystem.submitBookingRequest(new Reservation("Bob", "Double"));
        bookingSystem.submitBookingRequest(new Reservation("Charlie", "Suite"));

        // Display queued requests
        bookingSystem.displayQueuedRequests();
    }
}