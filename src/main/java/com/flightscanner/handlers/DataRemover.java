package com.flightscanner.handlers;

import com.flightscanner.dao.FlightDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class DataRemover {
	Logger logger = LoggerFactory.getLogger(DataRemover.class);

	@Autowired
	private FlightDao flightDao;

	/*
	 * Scheduled process to remove old data from DB.
	 * 
	 */
	@Scheduled(fixedRate = 10000)
	public void run() {
		long time = System.currentTimeMillis();
		logger.info("Removing data older than " + time);

		flightDao.deleteByDepartureLessThan(time);
	}
}
