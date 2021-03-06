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
    Email email = new Email();
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
                + "6. Delete Booking\n"
                + "7. Add Booking\n"
                + "8. Exit\n"

                + "Enter Option [1,8]";

        final int SHOW_ALL = 1;
        final int FIND_BY_BOOKING_ID = 2;
        final int FIND_BOOKING_BY_PASSENGER_ID = 3;
        final int FIND_BOOKING_BY_VEHICLE_ID = 4;
        final int DELETE_BOOKING = 5;
        final int ADD_NEW_BOOKING= 6;
        final int EDIT_BOOKING= 7;
        final int EXIT = 8;

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

                    case DELETE_BOOKING:
                        System.out.println("Delete a Booking");
                        System.out.println("Please enter the Booking ID for the booking you would like to Delete");
                        int bookingId = Integer.parseInt(keyboard.nextLine());
                        Booking b = deleteBooking(bookingId);
                        if (b == null)
                            System.out.println("No Booking matching the ID \"" + bookingId + "\"");
                        else
                            System.out.println("Booking Deleted: \n" + b);

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
                int hour = sc.nextInt();
                int minute = sc.nextInt();
                double startLatitude = sc.nextDouble();
                double startLongitude = sc.nextDouble();
                double endLatitude = sc.nextDouble();
                double endLongitude = sc.nextDouble();
                double cost = sc.nextDouble();

                // construct a Booking object and add it to the booking list
                bookingList.add(new Booking(bookingId, passengerId, vehicleId, year, month, day, hour, minute,
                        startLatitude, startLongitude, endLatitude, endLongitude, cost));
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
                                + b.getBookingDateTime().getYear() + ","
                                + b.getBookingDateTime().getMonthValue() + ","
                                + b.getBookingDateTime().getDayOfMonth() + ","
                                + b.getBookingDateTime().getHour() + ","
                                + b.getBookingDateTime().getMinute() + ","
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

    public ArrayList<Booking> findBookingByVehicleId(int id) {
        ArrayList<Booking> bookings = new ArrayList<>();
        System.out.println("Bookings with passenger id " + id + ":");
        for (Booking b : bookingList) {
            if (b.getVehicleId() == id) {
                bookings.add(b);
            }
        }
        ComparatorBookingDateTime comp = new ComparatorBookingDateTime();
        Collections.sort(bookings, comp);
        return bookings;
    }

        public Booking deleteBooking(int bId){
            Booking book = null;
            for (Booking b : bookingList) {
                if (b.getBookingId() == bId) {
                    book=b;
                }
            }
            bookingList.remove(book);
            return book;
        }

    public boolean addBooking(int passengerId, int vehicleId, int year, int month, int day, int hour, int minute,
                              double startLatitude, double startLongitude,
                              double endLatitude, double endLongitude, double cost) {


        if (passengerStore.findPassengerById(passengerId) != null &&
                vehicleManager.findVehicleById(vehicleId) != null) {

            bookingList.add(new Booking(passengerId, vehicleId, year, month, day, hour, minute,
                    startLatitude, startLongitude,
                    endLatitude, endLongitude, cost));

            System.out.println(email.sendReminderBookingMessage(passengerId, vehicleId, year, month, day, hour, minute,
                    startLatitude, startLongitude,
                    endLatitude, endLongitude, cost));

        } else {
            System.out.println("Cannot find passenger or vehicle on record!");
        }
        return false;
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

            System.out.println("Enter Booking Hour");
            int hour = kb.nextInt();

            System.out.println("Enter Booking Minute");
            int minute = kb.nextInt();

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
                b.setBookingDateTime(LocalDateTime.from(LocalDate.of(year, month, day)));
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
        Booking b = findBookingById(bookingId);
        if (b != null) {
            String MENU_ITEMS = "\n*** Edit Booking MENU ***\n"
                    + "1. Edit Passenger\n"
                    + "2. Edit Vehicle\n"
                    + "3. Edit Year\n"
                    + "4. Edit Month\n"
                    + "5. Edit Day\n"
                    + "6. Edit Hour\n"
                    + "7. Edit Minute\n"
                    + "8. Edit Start Longitude\n"
                    + "9. Edit Start Latitude\n"
                    + "10. Edit End Longitude\n"
                    + "11. Edit End Latitude\n"
                    + "12. Exit\n"
                    + "Enter Option [1,12]";

            final int EDIT_PASSENGER = 1;
            final int EDIT_VEHICLE = 2;
            final int EDIT_YEAR = 3;
            final int EDIT_MONTH = 4;
            final int EDIT_DAY = 5;
            final int EDIT_HOUR = 6;
            final int EDIT_MINUTE = 7;
            final int EDIT_START_LONGITUDE = 8;
            final int EDIT_START_LATITUDE = 9;
            final int EDIT_END_LONGITUDE = 10;
            final int EDIT_END_LATITUDE = 11;
            final int EXIT = 12;

            int option = 0;
            do {
                System.out.println("\n" + MENU_ITEMS);
                try {
                    option = kb.nextInt();
                    switch (option) {

                        case EDIT_PASSENGER:
                            System.out.println("Edit Passenger");
                            System.out.println("Enter new Passenger ID:");
                            int newPassengerId = kb.nextInt();
                            b.setPassengerId(newPassengerId);
                            System.out.println("Passenger Updated");
                            break;

                        case EDIT_VEHICLE:
                            System.out.println("Edit Vehicle");
                            System.out.println("Enter new Vehicle ID:");
                            int newVehicleId = kb.nextInt();
                            b.setVehicleId(newVehicleId);
                            System.out.println("Vehicle Updated");
                            break;

                        case EDIT_YEAR:
                            System.out.println("Edit Year");
                            System.out.println("Enter new Year:");
                            int newYear = kb.nextInt();
                            b.setBookingDateTime(LocalDateTime.of(newYear, b.getBookingDateTime().getMonth(),
                                    b.getBookingDateTime().getDayOfMonth(), b.getBookingDateTime().getHour(), b.getBookingDateTime().getMinute()));
                            System.out.println("Year Updated");
                            break;

                        case EDIT_MONTH:
                            System.out.println("Edit Month");
                            System.out.println("Enter new Month:");
                            int newMonth = kb.nextInt();
                            b.setBookingDateTime(LocalDateTime.of(b.getBookingDateTime().getYear(), newMonth,
                                    b.getBookingDateTime().getDayOfMonth(), b.getBookingDateTime().getHour(), b.getBookingDateTime().getMinute()));
                            System.out.println("Month Updated");
                            break;

                        case EDIT_DAY:
                            System.out.println("Edit Day");
                            System.out.println("Enter new Day:");
                            int newDay = kb.nextInt();
                            b.setBookingDateTime(LocalDateTime.of(b.getBookingDateTime().getYear(), b.getBookingDateTime().getMonth(),
                                    newDay, b.getBookingDateTime().getHour(), b.getBookingDateTime().getMinute()));
                            System.out.println("Day Updated");
                            break;

                        case EDIT_HOUR:
                            System.out.println("Edit Hour");
                            System.out.println("Enter new Hour:");
                            int newHour = kb.nextInt();
                            b.setBookingDateTime(LocalDateTime.of(b.getBookingDateTime().getYear(), b.getBookingDateTime().getMonth(),
                                    b.getBookingDateTime().getDayOfMonth(), newHour, b.getBookingDateTime().getMinute()));
                            System.out.println("Day Updated");
                            break;

                        case EDIT_MINUTE:
                            System.out.println("Edit Minute");
                            System.out.println("Enter new Minute:");
                            int newMinute = kb.nextInt();
                            b.setBookingDateTime(LocalDateTime.of(b.getBookingDateTime().getYear(), b.getBookingDateTime().getMonth(),
                                    b.getBookingDateTime().getDayOfMonth(), b.getBookingDateTime().getHour(), newMinute));
                            System.out.println("Day Updated");
                            break;

                        case EDIT_START_LONGITUDE:
                            System.out.println("Edit starting Longitude");
                            System.out.println("Enter new starting Longitude:");
                            double newStartLongitude = kb.nextDouble();
                            b.setStartLocation(newStartLongitude, b.getStartLocation().getLatitude());
                            System.out.println("Longitude Updated");
                            break;

                        case EDIT_START_LATITUDE:
                            System.out.println("Edit starting Latitude");
                            System.out.println("Enter new starting Latitude:");
                            double newStartLatitude = kb.nextDouble();
                            b.setStartLocation(b.getStartLocation().getLongitude(), newStartLatitude);
                            System.out.println("Latitude Updated");
                            break;

                        case EDIT_END_LONGITUDE:
                            System.out.println("Edit ending Longitude");
                            System.out.println("Enter new ending Latitude:");
                            double newEndLongitude = kb.nextDouble();
                            b.setStartLocation(b.getEndLocation().getLongitude(), newEndLongitude);
                            System.out.println("Longitude Updated");
                            break;

                        case EDIT_END_LATITUDE:
                            System.out.println("Edit ending Latitude");
                            System.out.println("Enter new ending Latitude:");
                            double newEndLatitude = kb.nextDouble();
                            b.setStartLocation(b.getEndLocation().getLongitude(), newEndLatitude);
                            System.out.println("Latitude Updated");
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
