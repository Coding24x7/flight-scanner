package com.flightscanner.models;

import com.flightscanner.entities.Flight;

public interface RemoteData {
    public boolean isValid();
    public Flight toFlight();
}