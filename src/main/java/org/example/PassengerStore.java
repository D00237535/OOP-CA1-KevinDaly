package org.example;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PassengerStore {

    private final ArrayList<Passenger> passengerList;
    private VehicleManager vehicleManager;
    private BookingManager bookingManager;

    public PassengerStore(String fileName) {
        this.passengerList = new ArrayList<>();
        this.vehicleManager = vehicleManager;
        this.bookingManager = bookingManager;
        loadPassengerDataFromFile(fileName);
    }

    public List<Passenger> getAllPassengers() {
        return this.passengerList;
    }


    /**
     * Read Passenger records from a text file and create and add Passenger
     * objects to the PassengerStore.
     */
    private void loadPassengerDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int id = sc.nextInt();
                String name = sc.next();
                String email = sc.next();
                String phone = sc.next();
                double latitude = sc.nextDouble();
                double longitude = sc.nextDouble();

                // construct a Passenger object and add it to the passenger list
                passengerList.add(new Passenger(id, name, email, phone, latitude, longitude));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    // TODO - see functional spec for details of code to add

    public void displayPassengerMenu() {
        final String MENU_ITEMS =
                "\n*** PASSENGER MENU ***\n"
                        + "1. Show all Passengers\n"
                        + "2. Find Passenger By ID\n"
                        + "3. Find Passenger By Name\n"
                        + "4. Delete Passenger\n"
                        + "5. Edit Passenger\n"
                        + "6. Add Passenger\n"
                        + "7. Exit\n"
                        + "Enter Option [1,7]";

        final int SHOW_ALL = 1;
        final int FIND_BY_ID = 2;
        final int FIND_BY_NAME = 3;
        final int DELETE_PASSENGER = 4;
        final int EDIT_PASSENGER = 5;
        final int ADD_PASSENGER = 6;
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
                        System.out.println("Display ALL Passengers");
                        displayAllPassengers();
                        break;

                    case FIND_BY_ID:
                        System.out.println("Find Booking by Passenger ID");
                        System.out.println("Enter Passenger ID: ");
                        int findPId = Integer.parseInt(keyboard.nextLine());
                        if (findPassengerById(findPId) == null)
                            System.out.println("No Booking matching the chosen ID\"" + findPId + "\"");
                        else
                            System.out.println("Found Booking: \n" + findPassengerById(findPId));

                    case FIND_BY_NAME:
                        System.out.println("Find Booking by Passenger ID");
                        System.out.println("Enter Passenger ID: ");
                        String Name = (keyboard.nextLine());
                        if (findPassengerByName(Name) == null)
                            System.out.println("No Booking matching the chosen ID\"" + Name + "\"");
                        else
                            System.out.println("Found Booking: \n" + findPassengerByName(Name));
                        break;

                    case DELETE_PASSENGER:
                        System.out.println("Delete Passenger Chosen");
                        System.out.println("Enter Passenger Name: ");
                        String delName = keyboard.nextLine();
                        System.out.println("Enter Passenger Email");
                        String delEmail = keyboard.nextLine();
                        if (delName != null && delEmail != null) {
                            deletePassenger(delName, delEmail);
                        }
                        break;

                    case EDIT_PASSENGER:
                        System.out.println("Edit Passenger");
                        editPassengerMenu();
                        break;

                    case ADD_PASSENGER:
                        System.out.println("Add Passenger Chosen");
                        addPassengerMenu();
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


    public void displayAllPassengers() {
        for (Passenger p : passengerList) {
            System.out.println(p.toString());
        }
    }

    public void displayAllPassengerId() {
        for (Passenger p : passengerList) {
            System.out.println(p.getId());
        }
    }

    public void deletePassenger(String name, String email) {
        for (Passenger p : passengerList) {
            if (p.getName().equalsIgnoreCase(name) && p.getEmail().equalsIgnoreCase(email)) {
                passengerList.remove(p);
                break;
            }

        }

    }

    public void editPassenger(String name, String email, String phone, double latitude, double longitude) {
        boolean found = false;
        for (Passenger p : passengerList) {
            if (p.getName().equalsIgnoreCase(name) && p.getEmail().equalsIgnoreCase(email)) {
                found = true;
                p.setName(name);
                p.setEmail(email);
                p.setPhone(phone);
                p.setLocation(latitude, longitude);
                break;
            }
        }
        if (!found) {
            System.out.println("not found");
        }
    }

    public void editPassengerMenu() {
        Scanner kb = new Scanner(System.in);
        System.out.println("Passenger Ids");
        displayAllPassengerId();
        System.out.println("Enter Booking ID to change");
        int PassengerId = kb.nextInt();
        Passenger p = findPassengerById(PassengerId);
        if (p != null) {
            String MENU_ITEMS = "\n*** Edit Booking MENU ***\n"
                    + "1. Edit Name\n"
                    + "2. Edit Email\n"
                    + "3. Edit Phone\n"
                    + "4. Edit Latitude\n"
                    + "5. Edit Longitude\n"
                    + "6. Exit\n";

            final int EDIT_NAME = 1;
            final int EDIT_EMAIL = 2;
            final int EDIT_PHONE = 3;
            final int EDIT_LATITUDE = 4;
            final int EDIT_LONGITUDE = 5;
            final int EXIT = 6;

            int option = 0;
            do {
                System.out.println("\n" + MENU_ITEMS);
                try {
                    option = kb.nextInt();
                    switch (option) {

                        case EDIT_NAME:
                            System.out.println("Edit Passenger Name");
                            System.out.println("Enter new Passenger Name:");
                            String newPassengerName = kb.nextLine();
                            p.setPassengerName(newPassengerName);
                            System.out.println("Name Updated");
                            break;

                        case EDIT_EMAIL:
                            System.out.println("Edit Passenger Email");
                            System.out.println("Enter new Passenger Email:");
                            String newPassengerEmail = kb.nextLine();
                            p.setPassengerEmail(newPassengerEmail);
                            System.out.println("Email Updated");
                            break;

                        case EDIT_PHONE:
                            System.out.println("Edit Passenger Phone");
                            System.out.println("Enter new Passenger Phone:");
                            String newPassengerPhone = kb.nextLine();
                            p.setPassengerPhone(newPassengerPhone);
                            System.out.println("Phone Updated");
                            break;

                        case EDIT_LONGITUDE:
                            System.out.println("Edit Passenger Longitude");
                            System.out.println("Enter new starting Longitude:");
                            double newLongitude = kb.nextDouble();
                            p.setLocation(newLongitude, p.getLocation().getLatitude());
                            System.out.println("Longitude Updated");
                            break;

                        case EDIT_LATITUDE:
                            System.out.println("Edit Passenger Latitude");
                            System.out.println("Enter new starting Latitude:");
                            double newLatitude = kb.nextDouble();
                            p.setLocation(p.getLocation().getLongitude(), newLatitude);
                            System.out.println("Latitude Updated");
                            break;

                        case EXIT:
                            System.out.println("Exit Menu option chosen");
                            break;
                    }
                } catch (InputMismatchException | NumberFormatException e) {
                    System.out.print("Invalid option - please enter number in range");
                }
            }
            while (option != EXIT);
        }

    }


    public Passenger findPassengerByName(String Name) {
        for (Passenger p : passengerList)
            if (p.getName().equalsIgnoreCase(Name)) {
                return p;
            }
        return null;
    }

    public Passenger findPassengerById(int findPId) {
        for (Passenger p : passengerList)
            if (p.getId() == findPId) {
                return p;
            }
        return null;
    }
    // Sub-Menu for Passenger operations
    //


    public boolean addPassenger(String name, String email, String phone,
                                double latitude, double longitude) {
        Passenger P = new Passenger(name, email, phone, latitude, longitude);
        boolean found = false;
        for (Passenger p : passengerList) {
            if (p.equals(P)) {
                found = true;
                break;
            }

        }
        if (!found) {
            passengerList.add(P);
        }
        return found;
    }

    private void addPassengerMenu() {

        Scanner keyboard = new Scanner(System.in);

        try {
            System.out.println("Enter Name:");
            String name = keyboard.nextLine();
            System.out.println("Enter Email:");
            String email = keyboard.nextLine();
            System.out.println("Enter Phone Number:");
            String phone = keyboard.nextLine();
            System.out.println("Enter Latitude");
            double latitude = keyboard.nextDouble();
            System.out.println("Enter longitude");
            double longitude = keyboard.nextDouble();
            if (name != null && email != null) {
                boolean found = addPassenger(name, email, phone, latitude, longitude);

                if (!found) {
                    System.out.println("Passenger was added");
                } else {
                    System.out.println("Passenger already exists");
                }
            } else {
                System.out.println("Name and Email is required, Please Try Again");
            }

        } catch (InputMismatchException | NumberFormatException e) {
            System.out.print("Invalid option - please enter valid details");
        }
    }

} // end class
