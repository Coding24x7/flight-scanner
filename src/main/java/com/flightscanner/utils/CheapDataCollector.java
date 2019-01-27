package com.flightscanner.utils;

import java.util.ArrayList;
import java.util.List;

import com.flightscanner.entities.Flight;

import org.springframework.stereotype.Component;

@Component
public class CheapDataCollector implements DataCollector {

	@Override
	public List<Flight> collect() {
		List<Flight> flights = new ArrayList<>();

		flights.add(new Flight("adfdf", "pune", "mumbai", 123, 2222));
		flights.add(new Flight("xyz", "bangalore", "chennai", 10, 99));
		flights.add(new Flight("hhh", "pune", "delhi", 212, 2222));
		flights.add(new Flight("xyz1", "bangalore", "delhi", 10, 99));
		flights.add(new Flight("hhh1", "pune", "kolkata", 212, 2222));
		flights.add(new Flight("xyz2", "bangalore", "chennai", 101, 99));
		flights.add(new Flight("hhh2", "pune", "sikkim", 22, 2222));
		flights.add(new Flight("xyz3", "bangalore", "chennai", 100, 99));
		flights.add(new Flight("hhh3", "pune", "delhi", 2120, 2222));
		return flights;
	}
}
