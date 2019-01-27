package com.flightscanner.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.flightscanner.entities.Flight;

public interface FlightDao extends CrudRepository<Flight, String> {
	public List<Flight> findAllByOrderByDepartureAsc();
	
	// public List<Flight> findBySourceByOrderByDepartureAsc(String source);
	// public List<Flight> findByDestinationByOrderByDepartureAsc(String destination);

	// public List<Flight> findByDepartureBetweenOrderByDepartureAsc(long start, long end);
	
	// public List<Flight> findBySourceAndDepartureBetweenOrderByDepartureAsc(String source, long start, long end);
}