package com.flightscanner.utils;

import com.flightscanner.dao.FlightDao;
import com.flightscanner.entities.Flight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataHandler implements ApplicationRunner {
	Logger logger = LoggerFactory.getLogger(DataHandler.class);

	@Autowired
	private FlightDao flightDao;

	// data writer
	private class DataWriter {
		private DataCollector collector;

		DataWriter(DataCollector collector) {
			this.collector = collector;
		}

		// waits for notifyHandler
		public void write() {
			while (true) {
				
				flightDao.saveAll(collector.collect());

				logger.debug("New flight data added to database");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
        }
	}

	// data remover
	private class DataRemover {

		public void delete()
        {
            
        }
	}

	@Override
	public void run(ApplicationArguments args) {
		try {
			final DataWriter writer = new DataWriter(new CheapDataCollector());
			new Thread(writer::write).start();
			logger.info("Created new data writer");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			final DataRemover remover = new DataRemover();
			new Thread(remover::delete).start();
			logger.info("Created new data remover");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
