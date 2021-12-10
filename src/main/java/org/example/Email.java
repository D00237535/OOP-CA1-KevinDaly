package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Email{

    private String message;

    public String sendReminderBookingMessage(int passengerId, int vehicleId, int year, int month, int day, int hour, int minute,
                                             double startLatitude, double startLongitude,
                                             double endLatitude, double endLongitude, double cost) {
        message ="=====================================================\n"+
                "To Passenger Id:"+passengerId+"\n"
                +"Subject:Booked Vehicle Successful!"+"\n" +
                "You have booked Vehicle Id "+vehicleId+
                " on:" + year +"/"+ month+"/" +day + hour+":" +minute+":00"+"\n"+
                "Start Location: StartLatitude = "+startLatitude + " StartLongitude = "+startLongitude+"\n"+
                "End Location: EndLatitude = "+endLatitude + " EndLongitude = "+endLongitude+"\n"+
                "Cost:"+ cost +"euro\n"
                +"=====================================================";

        return message;
    }
}