package com.flightscanner.handlers;

import java.util.ArrayList;
import java.util.List;

import com.flightscanner.dao.FlightDao;
import com.flightscanner.entities.Flight;
import com.flightscanner.models.CheapData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;


@Component
@Transactional
public class CheapDataCollector {
	Logger logger = LoggerFactory.getLogger(CheapDataCollector.class);

	@Autowired
	private FlightDao flightDao;

	private String URL = "https://obscure-caverns-79008.herokuapp.com/cheap";
	RestTemplate restTemplate;

	public CheapDataCollector() {
		restTemplate = new RestTemplate();
	}

	/*
	 * Scheduled process to collect data from cheap url and write to DB.
	 * 
	 */
	@Scheduled(fixedRate = 5000)
	public void run() {
		
		List<Flight> flights = collect();
		if (flights != null) {
			flightDao.saveAll(flights);
		}
		
	}

	/*
	 * Collect data from cheap url.
	 * 
	 * @return			 List of Flight
 	 */
	private List<Flight> collect() {
		try {
			logger.info("Connecting to " + URL);

			ResponseEntity<List<CheapData>> response = restTemplate.exchange(
				URL,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<CheapData>>(){});

			if (response != null) {
				return toFlights(response.getBody());
			}
	
		} catch (Exception e) {
			throw e;
		}

		return null;
	}

	/*
	 * Convert data to common form.
	 * 
	 * @param  dataList  List of cheap data objects
	 * @return			 List of Flight
 	 */
	private List<Flight> toFlights(List<CheapData> dataList) {
		if (dataList == null || dataList.size() == 0) {
			return null;
		}

		List<Flight> flights = new ArrayList<>();
		for(CheapData cd : dataList) {
			Flight flight = cd.toFlight();
			if (flight != null) {
				flights.add(flight);
			} else {
				logger.info("ignoring: " + cd);
			}			
		}

		return flights;
	}
}
