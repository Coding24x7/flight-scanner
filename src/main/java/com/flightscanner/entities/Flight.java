package com.flightscanner.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flights")
public class Flight {

	@Id
	private String id;
	
	@Column(nullable = false)
    private String source;

	@Column(nullable = false)
	private String destination;

	@Column(nullable = false)
	private long departure;

	@Column(nullable = false)
	private long arrival;

	public Flight(){
		
	}

	public Flight(String id, String source, String destination, long departure, long arrival) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.departure = departure;
		this.arrival = arrival;
	}

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
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the departure
	 */
	public long getDeparture() {
		return departure;
	}

	/**
	 * @param departure the departure to set
	 */
	public void setDeparture(long departure) {
		this.departure = departure;
	}

	/**
	 * @return the arrival
	 */
	public long getArrival() {
		return arrival;
	}

	/**
	 * @param arrival the arrival to set
	 */
	public void setArrival(long arrival) {
		this.arrival = arrival;
	}

	
}
