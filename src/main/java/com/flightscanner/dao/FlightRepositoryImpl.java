package com.flightscanner.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.flightscanner.entities.Flight;

import org.springframework.stereotype.Repository;

@Repository
public class FlightRepositoryImpl {

    private EntityManager em;

    public FlightRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    public List<Flight> findBooksByAuthorNameAndTitle(String source, String destination, Long startTime, Long endTime, Integer pageSize) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Flight> cq = cb.createQuery(Flight.class);

        Root<Flight> flight = cq.from(Flight.class);
        List<Predicate> predicates = new ArrayList<>();

        if (source != null) {
            predicates.add(cb.equal(flight.get("source"), source.toLowerCase()));
        }
        if (destination != null) {
            predicates.add(cb.equal(flight.get("destination"), destination.toLowerCase()));
		}

		if (startTime != null || endTime != null) {
			if (startTime != null && endTime != null) {
				predicates.add(cb.between(flight.get("departure"), startTime, endTime));
			} else if (startTime != null) {
				predicates.add(cb.greaterThanOrEqualTo(flight.get("departure"), startTime));
			} else {
				predicates.add(cb.lessThanOrEqualTo(flight.get("departure"), endTime));
			}
		}

		cq.where(predicates.toArray(new Predicate[0]));
		cq.orderBy(cb.asc(flight.get("departure")));

        TypedQuery<Flight> query = em.createQuery(cq);

        if (pageSize != null) {
            // remote api are returning mix results, 
            // no garuntee we always get timestamp greater that
            // all old responses... so page entinties are changing
            // in such scenario, startTime and pageSize combinamtion is useful
            query.setFirstResult(0);
            query.setMaxResults(pageSize);
        }

        return query.getResultList();
    }

}