package com.flightscanner.utils;

import java.util.List;

import com.flightscanner.entities.Flight;

public interface DataCollector {
    public List<Flight> collect();
}