package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class BookingManager {
    private final ArrayList<Booking> bookingList;
    private PassengerStore passengerStore;
    private VehicleManager vehicleManager;
    private IdGenerator idGenerator;

    // Constructor
    public BookingManager(String fileName, PassengerStore passengerStore, VehicleManager vehicleManager) {
        this.bookingList = new ArrayList<>();
        this.passengerStore = passengerStore;
        this.vehicleManager = vehicleManager;
        loadBookingsDataFromFile(fileName);
    }

    //TODO implement functionality as per specification

    public void displayBookingMenu() {
        final String MENU_ITEMS = "\n*** BOOKING MENU ***\n"
                + "1. Show all Bookings\n"
                + "2. Find Booking By ID\n"
                + "3. Find Booking By Passenger ID\n"
                + "4. Find Booking By Vehicle ID\n"
                + "5. Edit Booking\n"
                + "5. Delete Booking"
                + "6. Add Booking\n"
                + "7. Exit\n"

                + "Enter Option [1,7]";

        final int SHOW_ALL = 1;
        final int FIND_BY_BOOKING_ID = 2;
        final int FIND_BOOKING_BY_PASSENGER_ID = 3;
        final int FIND_BOOKING_BY_VEHICLE_ID = 4;
        final int ADD_NEW_BOOKING= 5;
        final int EDIT_BOOKING= 6;
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
                        displayAllBookings();
                        break;

                    case FIND_BY_BOOKING_ID:
                        System.out.println("Find Booking by ID");
                        System.out.println("Enter Booking ID: ");
                        int findId = Integer.parseInt(keyboard.nextLine());
                        if (findBookingById(findId) == null)
                            System.out.println("No Booking matching the chosen ID\"" + findId + "\"");
                        else
                            System.out.println("Found Booking: \n" + findBookingById(findId));
                        break;

                    case FIND_BOOKING_BY_PASSENGER_ID:
                        System.out.println("Find Booking by Passenger ID");
                        System.out.println("Enter Passenger ID: ");
                        int findPId = Integer.parseInt(keyboard.nextLine());
                        if (findBookingByPassengerId(findPId) == null)
                            System.out.println("No Booking matching the chosen ID\"" + findPId + "\"");
                        else
                            System.out.println("Found Booking: \n" + findBookingByPassengerId(findPId));
                        break;

                    case FIND_BOOKING_BY_VEHICLE_ID:
                        System.out.println("Find Booking by Vehicle ID");
                        System.out.println("Enter Vehicle ID: ");
                        int findVId = Integer.parseInt(keyboard.nextLine());
                        if (findBookingByVehicleId(findVId) == null)
                            System.out.println("No Booking matching the chosen ID\"" + findVId + "\"");
                        else
                            System.out.println("Found Booking: \n" + findBookingByVehicleId(findVId));
                        break;

                    case ADD_NEW_BOOKING:
                        System.out.println("Add Bookings");
                        addNewBooking();
                        break;

                    case EDIT_BOOKING:
                        System.out.println("Edit Bookings");
                        editBookingMenu();
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

    private void loadBookingsDataFromFile(String filename) {

        try {
            Scanner sc = new Scanner(new File(filename));
//           Delimiter: set the delimiter to be a comma character ","
//                    or a carriage-return '\r', or a newline '\n'
            sc.useDelimiter("[,\r\n]+");

            while (sc.hasNext()) {
                int bookingId = sc.nextInt();
                int passengerId = sc.nextInt();
                int vehicleId = sc.nextInt();
                int year = sc.nextInt();
                int month = sc.nextInt();
                int day = sc.nextInt();
                double latStart = sc.nextDouble();
                double longStart = sc.nextDouble();
                double latEnd = sc.nextDouble();
                double longEnd = sc.nextDouble();
                double cost = sc.nextDouble();

                // construct a Booking object and add it to the booking list
                bookingList.add(new Booking(bookingId, passengerId, vehicleId, year, month, day,
                        latStart, longStart, latEnd, longEnd, cost));
            }
            sc.close();

        } catch (IOException e) {
            System.out.println("Exception thrown. " + e);
        }
    }

    public void saveBookingsToFile(String fileName) {
        try {
            FileWriter bookingWriter = new FileWriter(fileName);
            for (Booking b : bookingList) {


                bookingWriter.write(
                        b.getBookingId() + ","
                                + b.getPassengerId() + ","
                                + b.getVehicleId() + ","
                                + b.getBookingDate().getYear() + ","
                                + b.getBookingDate().getDayOfMonth() + ","
                                + b.getBookingDate().getDayOfMonth() + ","
                                + b.getStartLocation().getLatitude() + ","
                                + b.getStartLocation().getLongitude() + ","
                                + b.getEndLocation().getLatitude() + ","
                                + b.getEndLocation().getLongitude() + ","
                                + b.getCost() + "\n"
                );


            }
            System.out.println("The new booking has been made, thank you.");
            bookingWriter.close();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void displayAllBookings() {
        for (Booking b : bookingList) {
            System.out.println(b.toString());
        }
    }

    public void displayAllBookingId() {
        for (Booking b : bookingList) {
            System.out.println(b.getBookingId());
        }
    }

    public Booking findBookingById(int findId) {
        for (Booking b : bookingList)
            if (b.getBookingId() == findId) {
                return b;
            }
        return null;
    }

    public ArrayList<Booking> findBookingByPassengerId(int id) {
        ArrayList<Booking> bookings = new ArrayList<>();
        System.out.println("Bookings with passenger id " + id + ":");
        for (Booking b : bookingList) {
            if (b.getPassengerId() == id) {
                bookings.add(b);
            }
        }
        ComparatorBookingDateTime comp = new ComparatorBookingDateTime();
        Collections.sort(bookings, comp);
        return bookings;
    }

    public Vehicle findBookingByVehicleId(int findVId) {
        List<Vehicle> list = vehicleManager.getAllVehicle();
        for (Vehicle v : vehicleList)
            if (v.getVId() == findVId) {
                return v;
            }
        return null;
    }

    public void deleteBooking(int bId) {
        for (Booking b : bookingList) {
            if (b.getBookingId() == bId) {
                bookingList.remove(b);
                break;
            }
        }
    }

    public boolean addBooking(int passengerId, int vehicleId, int year, int month, int day, int hour, int minute,
                              double startLatitude, double startLongitude, double endLatitude, double endLongitude, double cost) {

        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute);
        LocationGPS locationStart = new LocationGPS(startLatitude,startLongitude);
        LocationGPS locationEnd = new LocationGPS(endLatitude,endLongitude);
        Booking b1 = new Booking(passengerId, vehicleId, year, month, day, startLatitude, startLongitude, endLatitude, endLongitude, cost);
        boolean found = false;
        for (Booking b : bookingList) {
            if (b.equals(b1)) {
                found = true;
                break;
            }
        }
        if (!found) {
            bookingList.add(b1);
        }
        return found;
    }

    public void addNewBooking() {

        Scanner kb = new Scanner(System.in);
        System.out.println("Vehicle Ids");
        vehicleManager.displayAllVehicleId();
        System.out.println("Passenger Ids");
        passengerStore.displayAllPassengerId();
        try {
            System.out.println("Enter Passenger ID");
            int passengerId = kb.nextInt();

            System.out.println("Enter Vehicle ID");
            int vehicleId = kb.nextInt();

            System.out.println("Enter Booking Year");
            int year = kb.nextInt();

            System.out.println("Enter Booking Month");
            int month = kb.nextInt();

            System.out.println("Enter Booking Day");
            int day = kb.nextInt();

            System.out.println("Enter Start Latitude");
            double latStart = kb.nextDouble();

            System.out.println("Enter Start Longitude");
            double longStart = kb.nextDouble();

            System.out.println("Enter End Latitude");
            double latEnd = kb.nextDouble();

            System.out.println("Enter End Longitude");
            double longEnd = kb.nextDouble();

            System.out.println("Enter Cost");
            double cost = kb.nextDouble();

            if (passengerStore.findPassengerById(passengerId) == null) {
                System.out.println("Passenger " + passengerId + " was not found");
            }

            else if (vehicleManager.findVehicleById(vehicleId) == null) {
                System.out.println("Vehicle " + vehicleId + " was not found");
            }

            else {
                boolean found = addBooking(passengerId, vehicleId, year, month, day, hour, minute, latStart, longStart, latEnd, longEnd, cost);
                if (!found) {
                    System.out.println("Booking was added");
                } else {
                    System.out.println("Booking already exists");
                }
            }

        } catch (InputMismatchException | NumberFormatException e) {
            System.out.print("Invalid option - please enter valid details");
        }
    }

    public void editBooking(int bookingId, int passengerId, int vehicleId, int year, int month, int day, int hour, int minute,
                            double latStart, double longStart, double latEnd, double longEnd, double cost) {
        boolean found = false;
        for (Booking b : bookingList) {
            if (b.getBookingId() == bookingId) {
                found = true;
                b.setPassengerId(passengerId);
                b.setVehicleId(vehicleId);
                b.setBookingDate(LocalDate.of(year, month, day));
                b.setStartLocation(new LocationGPS(latStart, longStart));
                b.setEndLocation(new LocationGPS(latEnd, longEnd));
                break;
            }
        }
        if (!found) {
            System.out.println("not found");
        }
    }

    public void editBookingMenu() {

        Scanner kb = new Scanner(System.in);
        System.out.println("Booking Ids");
        displayAllBookingId();
        System.out.println("Vehicle Ids");
        vehicleManager.displayAllVehicleId();
        System.out.println("Passenger Ids");
        passengerStore.displayAllPassengerId();
        System.out.println("Enter Booking ID to change");
        int bookingId = kb.nextInt();
        if (findBookingById(bookingId) != null) {
            String MENU_ITEMS = "\n*** Edit Booking MENU ***\n"
                    + "1. Edit Passenger\n"
                    + "2. Edit Vehicle\n"
                    + "3. Edit Year\n"
                    + "4. Edit Month\n"
                    + "5. Edit Day\n"
                    + "6. Edit Hour\n"
                    + "7. Edit Minute\n"
                    + "8. Edit Seconds\n"
                    + "9. Edit Start Longitude\n"
                    + "10. Edit Start Latitude\n"
                    + "11. Edit End Longitude\n"
                    + "12. Edit End Latitude\n"
                    + "13. Exit\n"
                    + "Enter Option [1,13]";

            final int EDIT_PASSENGER = 1;
            final int EDIT_VEHICLE = 2;
            final int EDIT_YEAR = 3;
            final int EDIT_MONTH = 4;
            final int EDIT_DAY = 5;
            final int EDIT_HOUR = 6;
            final int EDIT_MINUTE = 7;
            final int EDIT_SECOND = 8;
            final int EDIT_START_LONGITUDE = 9;
            final int EDIT_START_LATITUDE = 10;
            final int EDIT_END_LONGITUDE = 11;
            final int EDIT_END_LATITUDE = 12;
            final int EXIT = 13;

            int option = 0;
            do {
                Booking b = findBookingById(bookingId);
                System.out.println("\n" + MENU_ITEMS);
                try {
                    option = kb.nextInt();
                    switch (option) {

                        case EDIT_PASSENGER:
                            System.out.println("Edit Passenger");
                            int newPassengerId = kb.nextInt();
                            b.setPassengerId(newPassengerId);
                            System.out.println("Passenger Updated");
                            break;

                        case EDIT_VEHICLE:
                            System.out.println("Edit Vehicle");
                            int newVehicleId = kb.nextInt();
                            b.setVehicleId(newVehicleId);
                            System.out.println("Vehicle Updated");
                            break;

                        case EDIT_YEAR:
                            System.out.println("Edit Year");
                            int newYear = kb.nextInt();
                            b.setBookingDateTime(new LocalDateTime.of(newYear, ));
                            System.out.println("Year Updated");
                            break;

                        case EXIT:
                            System.out.println("Exit Menu option chosen");
                            break;
                    }
                }
                catch (InputMismatchException | NumberFormatException e) {
                    System.out.print("Invalid option - please enter number in range");
                }
            }
            while (option != EXIT);
        }
    }
}
