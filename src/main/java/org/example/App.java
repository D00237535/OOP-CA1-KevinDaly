package org.example;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This Vehicle Bookings Management Systems manages the booking of Vehicles
 * by Passengers.
 * <p>
 * This program reads from 3 text files:
 * "vehicles.txt", "passengers.txt", and "next-id-store.txt"
 * You should be able to see them in the project pane.
 * You will create "bookings.txt" at a later stage, to store booking records.
 * <p>
 * "next-id-store.txt" contains one number ("201"), which will be the
 * next auto-generated id to be used to when new vehicles, passengers, or
 * bookings are created.  The value in the file will be updated when new objects
 * are created - but not when objects are recreated from records in
 * the files - as they already have IDs.  Dont change it - it will be updated by
 * the IdGenerator class.
 */


public class App {
    private static PassengerStore passengerStore;
    private static VehicleManager vehicleManager;
    private static BookingManager BookingManager;

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public void start() {
        // create PassengerStore and load all passenger records from text file
        passengerStore = new PassengerStore("passengers.txt");

        // create VehicleManager, and load all vehicles from text file
        vehicleManager = new VehicleManager("vehicles.txt");

        BookingManager = new BookingManager("Booking.txt");

        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }


        vehicleManager.displayAllVehicles();

        // Create BookingManager and load all bookings from file
        // bookingManager = new BookingManager("bookings.txt");

        //pMgr.saveToFile();

        System.out.println("Program ending, Goodbye");
    }

    private void displayMainMenu() throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Passengers\n"
                + "2. Vehicles\n"
                + "3. Bookings\n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int PASSENGERS = 1;
        final int VEHICLES = 2;
        final int BOOKINGS = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {

                    case PASSENGERS:
                        System.out.println("Passengers option chosen");
                        displayPassengerMenu();
                        break;

                    case VEHICLES:
                        System.out.println("Vehicles option chosen");
                        displayVehicleMenu();
                        break;

                    case BOOKINGS:
                        System.out.println("Bookings option chosen");
                        displayBookingMenu();
                        break;

                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }

    // Sub-Menu for Passenger operations
    //
    private void displayPassengerMenu() {
        final String MENU_ITEMS = "\n*** PASSENGER MENU ***\n"
                + "1. Show all Passengers\n"
                + "2. Find Passenger by Name\n"
                + "3. Add a Passenger\n"
                + "4. Delete a Passenger\n"
                + "5. Exit\n"
                + "Enter Option [1,5]";

        final int SHOW_ALL = 1;
        final int FIND_BY_NAME = 2;
        final int ADD_PASSENGER = 3;
        final int DELETE_PASSENGER = 4;
        final int EXIT = 5;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {

                    case SHOW_ALL:
                        System.out.println("Display ALL Passengers");
                        passengerStore.displayAllPassengers();
                        break;

                    case FIND_BY_NAME:
                        System.out.println("Find Passenger by Name");
                        System.out.println("Enter passenger name: ");
                        String name = keyboard.nextLine();
                        Passenger p = passengerStore.findPassengerByName(name);
                        if (p == null)
                            System.out.println("No passenger matching the name \"" + name + "\"");
                        else
                            System.out.println("Found passenger: \n" + p);
                        break;

                    case ADD_PASSENGER:
                        System.out.println("Add New Passenger\n");
                        addNewPassenger();
                        break;

                    case DELETE_PASSENGER:
                        System.out.println("Delete a Passenger\n");
                        System.out.println("Enter the passenger ID you want to delete: ");
                        int passengerId = Integer.parseInt(keyboard.nextLine());
                        p = passengerStore.deletePassenger(passengerId);
                        if (p == null)
                            System.out.println("No passenger matching the ID \"" + passengerId + "\"");
                        else
                            System.out.println("Passenger Deleted: \n" + p);
                        break;

                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }

    private void addNewPassenger() {
        Pattern phoneRegex = Pattern.compile("^\\+?(\\d+-?)+$");
        Pattern doubleRegex = Pattern.compile("^-?(\\d+)(?:\\.\\d+)?$");
        Pattern emailRegex = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);
        Scanner keyboard = new Scanner(System.in);
        String name = "", email, phone, latitude, longitude;
        boolean allowed = true;
        do {
            System.out.print("Please enter your name: ");
            name = keyboard.nextLine();
            allowed = name.length() > 1;
            if (!allowed)
                System.out.println("\nInvalid input!\n");
        } while (!allowed);
        do {
            System.out.print("Please enter your email: ");
            email = keyboard.nextLine();
            allowed = emailRegex.matcher(email).find();
            if (!allowed)
                System.out.println("\nInvalid input!\n" + email + name);
        } while (!allowed);
        do {
            System.out.print("Please enter your phone number: ");
            phone = keyboard.nextLine();
            allowed = phoneRegex.matcher(phone).find();
            if (!allowed)
                System.out.println("\nInvalid input!\n");
        } while (!allowed);
        do {
            System.out.print("v latitude [-90 - 90]: ");
            latitude = keyboard.nextLine();
            allowed = doubleRegex.matcher(latitude).find() && (Double.parseDouble(latitude) >= -90 && Double.parseDouble(latitude) <= 90);
            if (!allowed)
                System.out.println("\nInvalid input!\n");
        } while (!allowed);
        do {
            System.out.print("Please enter your longitude [-180 - 180]: ");
            longitude = keyboard.nextLine();
            allowed = doubleRegex.matcher(longitude).find() && (Double.parseDouble(longitude) >= -180 && Double.parseDouble(longitude) <= 180);
            if (!allowed)
                System.out.println("\nInvalid input!\n");
        } while (!allowed);

        System.out.println(passengerStore.addNewPassenger(name, email, phone, Double.parseDouble(latitude), Double.parseDouble(longitude)));
    }

    private void displayVehicleMenu() {
        final String MENU_ITEMS = "\n*** VEHICLES MENU ***\n"
                + "1. Show all Vehicles\n"
                + "2. Find Vehicle by Make\n"
                + "3. Find Vehicle by Model\n"
                + "4. Find Vehicle By Reg No.\n"
                + "5. Find Vehicle By Type\n"
                + "6. Exit\n"
                + "Enter Option [1,6]";

        final int SHOW_ALL = 1;
        final int FIND_BY_MAKE = 2;
        final int FIND_BY_MODEL = 3;
        final int FIND_BY_REG_NO = 4;
        final int FIND_BY_TYPE = 5;
        final int EXIT = 6;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {

                    case SHOW_ALL:
                        System.out.println("Display ALL Vehicles");
                        vehicleManager.displayAllVehicles();
                        break;

                    case FIND_BY_MAKE:
                        System.out.println("Find Vehicle by Make");
                        System.out.println("Enter Vehicle Make: ");
                        String make = keyboard.nextLine();
                        Vehicle v = vehicleManager.FindVehicleByMake(make);
                        if (v == null)
                            System.out.println("No Vehicle matching the make \"" + make + "\"");
                        else
                            System.out.println("Found Vehicle: \n" + v);
                        break;

                    case FIND_BY_MODEL:
                        System.out.println("Find Vehicle by Model");
                        System.out.println("Enter Vehicle Model: ");
                        String model = keyboard.nextLine();
                        v = vehicleManager.FindVehicleByModel(model);
                        if (v == null)
                            System.out.println("No Vehicle matching the model \"" + model + "\"");
                        else
                            System.out.println("Found Vehicle: \n" + v);
                        break;

                    case FIND_BY_REG_NO:
                        System.out.println("Find Vehicle by Registration");
                        System.out.println("Enter Vehicle Registration: ");
                        String reg = keyboard.nextLine();
                        v = vehicleManager.FindVehicleByRegNumber(reg);
                        if (v == null)
                            System.out.println("No Vehicle matching the reg \"" + reg + "\"");
                        else
                            System.out.println("Found Vehicle: \n" + v);
                        break;

                    case FIND_BY_TYPE:
                        System.out.println("Find Vehicle by Type");
                        System.out.println("What Type of Vehicle would you like?: ");
                        String type = keyboard.nextLine();
                        v = vehicleManager.FindVehicleByType(type);
                        if (v == null)
                            System.out.println("No Vehicle matching the Type \"" + type + "\"");
                        else
                            System.out.println("Found Vehicle: \n" + v);
                        break;

                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }

    private void displayBookingMenu() {
        final String MENU_ITEMS = "\n*** BOOKING MENU ***\n"
                + "1. Show all Bookings\n"
                + "2. Add a Booking\n"
                + "3. Find Booking by Booking ID\n"
                + "4. Find Booking by Passenger ID\n"
                + "5. Find Booking by Vehicle ID\n"
                + "6. Delete Booking\n"
                + "7. Exit\n"

                + "Enter Option [1,7]";

        final int SHOW_ALL = 1;
        final int ADD_BOOKING = 2;
        final int FIND_BY_BOOKING_ID = 3;
        final int FIND_BY_PASSENGER_ID = 4;
        final int FIND_BY_VEHICLE_ID = 5;
        final int DELETE_BOOKING = 6;
        final int EXIT = 7;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SHOW_ALL:
                        System.out.println("Display ALL Bookings");
                        BookingManager.displayAllBookings();
                        break;

                    case ADD_BOOKING:
                        System.out.println("Add New Booking\n");
                        addNewBooking();
                        break;

                    case FIND_BY_BOOKING_ID:
                        System.out.println("Find Booking by ID");
                        System.out.println("Enter Booking ID: ");
                        int bookingId = Integer.parseInt(keyboard.nextLine());
                        Booking b = (Booking) BookingManager.FindBookingByID(bookingId);
                        if (b == null)
                            System.out.println("No Booking matching the chosen ID\"" + bookingId + "\"");
                        else
                            System.out.println("Found Booking: \n" + b);
                        break;

                    case FIND_BY_PASSENGER_ID:
                        System.out.println("Find Booking by Passenger ID");
                        System.out.println("Enter the Passenger ID for the booking: ");
                        int passengerId = Integer.parseInt(keyboard.nextLine());
                        b = (Booking) BookingManager.FindBookingByPassengerID(passengerId);
                        if (b == null)
                            System.out.println("No Booking matching the chosen ID\"" + passengerId + "\"");
                        else
                            System.out.println("Found Booking: \n" + b);
                        break;

                    case FIND_BY_VEHICLE_ID:
                        System.out.println("Find Booking by Vehicle ID");
                        System.out.println("Enter the Vehicle ID for the booking: ");
                        int vehicleId = Integer.parseInt(keyboard.nextLine());
                        b = (Booking) BookingManager.FindBookingByVehicleID(vehicleId);
                        if (b == null)
                            System.out.println("No Booking matching the chosen ID\"" + vehicleId + "\"");
                        else
                            System.out.println("Found Booking: \n" + b);
                        break;

                    case DELETE_BOOKING:
                        System.out.println("Delete a Booking");
                        System.out.println("Please enter the Booking ID for the booking you would like to Delete");
                        bookingId = Integer.parseInt(keyboard.nextLine());
                        b = BookingManager.deleteBooking(bookingId);
                        if (b == null)
                            System.out.println("No Booking matching the ID \"" + bookingId + "\"");
                        else
                            System.out.println("Booking Deleted: \n" + b);

                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

    }

    private void addNewBooking() {
        Pattern phoneRegex = Pattern.compile("^\\+?(\\d+-?)+$");
        Pattern doubleRegex = Pattern.compile("^-?(\\d+)(?:\\.\\d+)?$");
        Pattern emailRegex = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);
        Scanner keyboard = new Scanner(System.in);
        String name = "", email, phone, latitude, longitude;
        boolean allowed = true;
        do {
            System.out.print("Please enter your name: ");
            name = keyboard.nextLine();
            allowed = name.length() > 1;
            if (!allowed)
                System.out.println("\nInvalid input!\n");
        } while (!allowed);
        do {
            System.out.print("Please enter your email: ");
            email = keyboard.nextLine();
            allowed = emailRegex.matcher(email).find();
            if (!allowed)
                System.out.println("\nInvalid input!\n" + email + name);
        } while (!allowed);
        do {
            System.out.print("Please enter your phone number: ");
            phone = keyboard.nextLine();
            allowed = phoneRegex.matcher(phone).find();
            if (!allowed)
                System.out.println("\nInvalid input!\n");
        } while (!allowed);
        do {
            System.out.print("v latitude [-90 - 90]: ");
            latitude = keyboard.nextLine();
            allowed = doubleRegex.matcher(latitude).find() && (Double.parseDouble(latitude) >= -90 && Double.parseDouble(latitude) <= 90);
            if (!allowed)
                System.out.println("\nInvalid input!\n");
        } while (!allowed);
        do {
            System.out.print("Please enter your longitude [-180 - 180]: ");
            longitude = keyboard.nextLine();
            allowed = doubleRegex.matcher(longitude).find() && (Double.parseDouble(longitude) >= -180 && Double.parseDouble(longitude) <= 180);
            if (!allowed)
                System.out.println("\nInvalid input!\n");
        } while (!allowed);

        System.out.println(passengerStore.addNewPassenger(name, email, phone, Double.parseDouble(latitude), Double.parseDouble(longitude)));
    }
}