package com.flightscanner.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.flightscanner.entities.Flight;

public class BusinessData implements RemoteData {
    CollectorID collectorID = CollectorID.business;

    String uuid;
    String flight;
    String departure;
    String arrival;

    SimpleDateFormat format1;
    SimpleDateFormat format2;

    public BusinessData() {
        format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        format1.setTimeZone(TimeZone.getTimeZone("GMT"));

        format2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        format2.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    /**
     * @return the collectorID
     */
    public CollectorID getCollectorID() {
        return collectorID;
    }

    /**
     * @param collectorID the collectorID to set
     */
    public void setCollectorID(CollectorID collectorID) {
        this.collectorID = collectorID;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the flight
     */
    public String getFlight() {
        return flight;
    }

    /**
     * @param flight the flight to set
     */
    public void setFlight(String flight) {
        this.flight = flight;
    }

    /**
     * @return the departure
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * @param departure the departure to set
     */
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    /**
     * @return the arrival
     */
    public String getArrival() {
        return arrival;
    }

    /**
     * @param arrival the arrival to set
     */
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    @Override
    public Flight toFlight() {

        if (isValid()) {
            String[] cities = flight.split("->");
            if (cities.length != 2) {
                System.out.println(cities);
                return null;
            }

            Date departureTime;
            Date arrivalTime;
            try {
                departureTime = format1.parse(departure);
            } catch (Exception e) {
                try {
                    departureTime = format2.parse(departure);
                } catch (ParseException e1) {
                    return null;
                }
            }

            try {
                arrivalTime = format1.parse(arrival);
            } catch (Exception e) {
                try {
                    arrivalTime = format2.parse(arrival);
                } catch (ParseException e1) {
                    return null;
                }
            }

            return new Flight(collectorID + "#" + uuid, cities[0].trim().toLowerCase(), cities[1].trim().toLowerCase(), departureTime.getTime(), arrivalTime.getTime());
        }

        return null;
    }

    @Override
    public boolean isValid() {
        return uuid != null && departure != null && arrival != null && flight != null;
    }

    @Override
    public String toString() {
        return "BusinessData [uuid=" + uuid + ", flight=" + flight + ", departure=" + departure + ", arrival=" + arrival + "]";
    }
}