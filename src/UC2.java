/**
 * UC2_BasicRoomAvailability
 *
 * Demonstrates basic room types with static availability in the Book My Stay App.
 * Introduces abstraction, inheritance, and polymorphism.
 *
 * Author: YourName
 * Version: 1.0
 */
public class UC2_BasicRoomAvailability {

    // Abstract Room class defining common attributes
    abstract static class Room {
        protected String type;
        protected int beds;
        protected double price;

        // Constructor
        public Room(String type, int beds, double price) {
            this.type = type;
            this.beds = beds;
            this.price = price;
        }

        // Abstract method to display room details
        public abstract void displayDetails();
    }

    // Single Room
    static class SingleRoom extends Room {
        public SingleRoom() {
            super("Single Room", 1, 50.0);
        }

        @Override
        public void displayDetails() {
            System.out.println(type + " | Beds: " + beds + " | Price: $" + price);
        }
    }

    // Double Room
    static class DoubleRoom extends Room {
        public DoubleRoom() {
            super("Double Room", 2, 90.0);
        }

        @Override
        public void displayDetails() {
            System.out.println(type + " | Beds: " + beds + " | Price: $" + price);
        }
    }

    // Suite Room
    static class SuiteRoom extends Room {
        public SuiteRoom() {
            super("Suite Room", 3, 150.0);
        }

        @Override
        public void displayDetails() {
            System.out.println(type + " | Beds: " + beds + " | Price: $" + price);
        }
    }

    // Main method - entry point for UC2
    public static void main(String[] args) {

        // Static availability variables
        int availableSingle = 5;
        int availableDouble = 3;
        int availableSuite = 2;

        // Create room objects
        Room single = new SingleRoom();
        Room doubleR = new DoubleRoom();
        Room suite = new SuiteRoom();

        System.out.println("=== Book My Stay App - Room Types & Availability ===\n");

        // Display room details and availability
        single.displayDetails();
        System.out.println("Available: " + availableSingle + "\n");

        doubleR.displayDetails();
        System.out.println("Available: " + availableDouble + "\n");

        suite.displayDetails();
        System.out.println("Available: " + availableSuite + "\n");

        System.out.println("==============================================");
    }
}