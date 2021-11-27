//package org.example;
//
//import java.io.IOException;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class Menu {
//    // Components (objects) used in this App
//    PassengerStore passengerStore;  // encapsulates access to list of Passengers
//    VehicleManager vehicleManager;  // encapsulates access to list of Vehicles
//    BookingManager bookingManager;  // deals with all bookings
//
//    public static void main(String[] args) {
//        App app = new App();
//        app.start();
//    }
//
//    public void start() {
//        // create PassengerStore and load all passenger records from text file
//        passengerStore = new PassengerStore("passengers.txt");
//
//        // create VehicleManager, and load all vehicles from text file
//        vehicleManager = new VehicleManager("vehicles.txt");
//
//        try {
//            displayMainMenu();        // User Interface - Menu
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        vehicleManager.displayAllVehicles();
//
//
//        String registration = "172LH234106";
//        Vehicle vehicle = vehicleManager.FindValueByRegNumber(registration);
//        if (vehicle == null)
//            System.out.println("No vehicle with registration " + registration + " was found.");
//        else
//            System.out.println("Found Vehicle: " + vehicle.toString());
//
//        // Create BookingManager and load all bookings from file
//        // bookingManager = new BookingManager("bookings.txt");
//
//        //pMgr.saveToFile();
//
//        System.out.println("Program ending, Goodbye");
//    }
//
//    private void displayMainMenu() throws IOException {
//
//        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
//                + "1. Passengers\n"
//                + "2. Vehicles\n"
//                + "3. Bookings\n"
//                + "4. Exit\n"
//                + "Enter Option [1,4]";
//
//        final int PASSENGERS = 1;
//        final int VEHICLES = 2;
//        final int BOOKINGS = 3;
//        final int EXIT = 4;
//
//        Scanner keyboard = new Scanner(System.in);
//        int option = 0;
//        do {
//            System.out.println("\n" + MENU_ITEMS);
//            try {
//                String usersInput = keyboard.nextLine();
//                option = Integer.parseInt(usersInput);
//                switch (option) {
//                    case PASSENGERS:
//                        System.out.println("Passengers option chosen");
//                        displayPassengerMenu();
//                        break;
//                    case VEHICLES:
//                        System.out.println("Vehicles option chosen");
//                        break;
//                    case BOOKINGS:
//                        System.out.println("Bookings option chosen");
//                        break;
//                    case EXIT:
//                        System.out.println("Exit Menu option chosen");
//                        break;
//                    default:
//                        System.out.print("Invalid option - please enter number in range");
//                        break;
//                }
//
//            } catch (InputMismatchException | NumberFormatException e) {
//                System.out.print("Invalid option - please enter number in range");
//            }
//        } while (option != EXIT);
//
//        System.out.println("\nExiting Main Menu, goodbye.");
//
//    }
//
//    // Sub-Menu for Passenger operations
//    //
//    private void displayPassengerMenu() {
//        final String MENU_ITEMS = "\n*** PASSENGER MENU ***\n"
//                + "1. Show all Passengers\n"
//                + "2. Find Passenger by Name\n"
//                + "3. Exit\n"
//                + "Enter Option [1,3]";
//
//        final int SHOW_ALL = 1;
//        final int FIND_BY_NAME = 2;
//        final int EXIT = 3;
//
//        Scanner keyboard = new Scanner(System.in);
//        int option = 0;
//        do {
//            System.out.println("\n" + MENU_ITEMS);
//            try {
//                String usersInput = keyboard.nextLine();
//                option = Integer.parseInt(usersInput);
//                switch (option) {
//                    case SHOW_ALL:
//                        System.out.println("Display ALL Passengers");
//                        passengerStore.displayAllPassengers();
//                        break;
//                    case FIND_BY_NAME:
//                        System.out.println("Find Passenger by Name");
//                        System.out.println("Enter passenger name: ");
//                        String name = keyboard.nextLine();
//                        Passenger p = passengerStore.findPassengerByName(name);
//                        if (p == null)
//                            System.out.println("No passenger matching the name \"" + name + "\"");
//                        else
//                            System.out.println("Found passenger: \n" + p.toString());
//                        break;
//                    case EXIT:
//                        System.out.println("Exit Menu option chosen");
//                        break;
//                    default:
//                        System.out.print("Invalid option - please enter number in range");
//                        break;
//                }
//
//            } catch (InputMismatchException | NumberFormatException e) {
//                System.out.print("Invalid option - please enter number in range");
//            }
//        } while (option != EXIT);
//
//    }
//}