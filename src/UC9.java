/**
 * UC9: Error Handling & Validation
 * Introduces input validation and custom exceptions to prevent invalid bookings.
 */
public class UC9 {

    // Custom exception for invalid booking attempts
    public static class InvalidBookingException extends Exception {
        public InvalidBookingException(String message) {
            super(message);
        }
    }

    // Simple method to validate room type and quantity
    public static void validateBooking(String roomType, int quantity, int availableRooms) throws InvalidBookingException {
        if (roomType == null || roomType.isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty.");
        }
        if (quantity <= 0) {
            throw new InvalidBookingException("Booking quantity must be at least 1.");
        }
        if (quantity > availableRooms) {
            throw new InvalidBookingException("Booking quantity exceeds available rooms.");
        }
        System.out.println("Booking validation passed for " + quantity + " " + roomType + " room(s).");
    }

    public static void main(String[] args) {
        String roomType = "Suite";
        int requestedRooms = 3;
        int availableRooms = 2;

        try {
            validateBooking(roomType, requestedRooms, availableRooms);
            System.out.println("Proceed with booking.");
        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }

        // Example of a valid booking
        try {
            validateBooking("Double", 1, 5);
            System.out.println("Proceed with booking.");
        } catch (InvalidBookingException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
}