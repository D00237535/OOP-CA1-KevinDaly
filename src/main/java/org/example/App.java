package org.example;
//Nathan Field

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class App {
    // Components (objects) used in this App
    PassengerStore passengerStore;  // encapsulates access to list of Passengers
    VehicleManager vehicleManager;  // encapsulates access to list of Vehicles
    BookingManager bookingManager;  // deals with all bookings

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    public void start() {

        // create PassengerStore and load all passenger records from text file
        passengerStore = new PassengerStore("passengers.txt");
        vehicleManager = new VehicleManager("vehicles.txt");
        bookingManager = new BookingManager("bookings.txt", passengerStore, vehicleManager);


        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }

        //ProgName.saveToFile();
        vehicleManager.saveVehiclesToFile("vehicles.txt");
        bookingManager.saveBookingsToFile("bookings.txt");
        System.out.println("Program ending, Goodbye");
    }

    private void displayMainMenu() throws IOException {
        //Main Menu of the project

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

                    case PASSENGERS://Runs the Menu in the PassengerStore Class
                        System.out.println("Passengers option chosen");
                        passengerStore.displayPassengerMenu();
                        break;

                    case VEHICLES://Runs the Menu in the VehicleManager Class
                        System.out.println("Vehicles option chosen");
                        vehicleManager.displayVehicleMenu();
                        break;

                    case BOOKINGS://Runs the Menu in the BookingManager Class
                        System.out.println("Bookings option chosen");
                        bookingManager.displayBookingMenu();
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
}