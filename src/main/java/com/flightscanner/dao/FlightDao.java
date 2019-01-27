package com.flightscanner.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.flightscanner.entities.Flight;

public interface FlightDao extends CrudRepository<Flight, String> {
	@Transactional
	public void deleteByDepartureLessThan(long departure);
}