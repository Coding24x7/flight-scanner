package com.flightscanner.models;

import com.flightscanner.entities.Flight;

public class CheapData implements RemoteData {
    CollectorID collectorID = CollectorID.cheap;

    String id;
    String departure;
    String arrival;
    Long departureTime;
    Long arrivalTime;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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

    /**
     * @return the departureTime
     */
    public Long getDepartureTime() {
        return departureTime;
    }

    /**
     * @param departureTime the departureTime to set
     */
    public void setDepartureTime(Long departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * @return the arrivalTime
     */
    public Long getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @param arrivalTime the arrivalTime to set
     */
    public void setArrivalTime(Long arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public Flight toFlight() {

        if (isValid()) {
            return new Flight(collectorID + "#" + id, departure.toLowerCase(), arrival.toLowerCase(), departureTime, arrivalTime);
        }

        return null;
    }

    @Override
    public boolean isValid() {
        return id != null && departure != null && arrival != null && departureTime != null && arrivalTime != null;
    }

    @Override
    public String toString() {
        return "CheapData [id=" + id + ", departure=" + departure + ", arrival=" + arrival + ", departureTime="
                + departureTime + ", arrivalTime=" + arrivalTime + "]";
    }
}