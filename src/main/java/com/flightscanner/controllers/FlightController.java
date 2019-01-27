package com.flightscanner.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flightscanner.entities.Flight;
import com.flightscanner.dao.FlightDao;
import com.flightscanner.dao.FlightRepositoryImpl;

@Controller
@RequestMapping(path="/flight")
public class FlightController {
	Logger logger = LoggerFactory.getLogger(FlightController.class);

	@Autowired
	private FlightRepositoryImpl flightImpl;

	public FlightController() {
	}

	/*
	 * Returns game status.
	 * 
	 * @param  gameId 		game id
	 * @return      		game status
 	 */
	@GetMapping(path="/search")
	public @ResponseBody List<Flight> searchFlights (@RequestParam(required = false) String source, @RequestParam(required = false) String destination, @RequestParam(required = false) Long startTime, @RequestParam(required = false) Long endTime) {
		logger.info("Got flight search request");
		
		return flightImpl.findBooksByAuthorNameAndTitle(source, destination, startTime, endTime);
	}

	
}
