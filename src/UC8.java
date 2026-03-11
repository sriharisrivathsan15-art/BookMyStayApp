import java.util.*;

/**
 * UC8: Booking History & Reporting
 * Tracks confirmed reservations and provides reporting functionality.
 */
public class UC8 {

    // List to maintain confirmed reservations in order
    private List<Reservation> bookingHistory;

    public UC8() {
        bookingHistory = new ArrayList<>();
    }

    // Add a confirmed reservation to history
    public void addReservation(Reservation reservation) {
        bookingHistory.add(reservation);
        System.out.println("Added reservation " + reservation.getReservationID() + " to booking history.");
    }

    // Display all reservations
    public void displayAllReservations() {
        System.out.println("\nBooking History:");
        for (Reservation r : bookingHistory) {
            System.out.println(r);
        }
    }

    // Generate a simple summary report
    public void generateReport() {
        System.out.println("\nBooking Report Summary:");
        System.out.println("Total confirmed reservations: " + bookingHistory.size());
        Map<String, Integer> roomTypeCount = new HashMap<>();
        for (Reservation r : bookingHistory) {
            roomTypeCount.put(r.getRoomType(), roomTypeCount.getOrDefault(r.getRoomType(), 0) + 1);
        }
        System.out.println("Reservations by Room Type:");
        for (Map.Entry<String, Integer> entry : roomTypeCount.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    // Simple Reservation class
    public static class Reservation {
        private String reservationID;
        private String guestName;
        private String roomType;

        public Reservation(String reservationID, String guestName, String roomType) {
            this.reservationID = reservationID;
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getReservationID() { return reservationID; }
        public String getGuestName() { return guestName; }
        public String getRoomType() { return roomType; }

        @Override
        public String toString() {
            return "ReservationID: " + reservationID + ", Guest: " + guestName + ", RoomType: " + roomType;
        }
    }

    // Demo
    public static void main(String[] args) {
        UC8 bookingHistoryManager = new UC8();

        Reservation res1 = new Reservation("SNG001", "Alice", "Single");
        Reservation res2 = new Reservation("DBL001", "Bob", "Double");
        Reservation res3 = new Reservation("STE001", "Charlie", "Suite");

        bookingHistoryManager.addReservation(res1);
        bookingHistoryManager.addReservation(res2);
        bookingHistoryManager.addReservation(res3);

        bookingHistoryManager.displayAllReservations();
        bookingHistoryManager.generateReport();
    }
}