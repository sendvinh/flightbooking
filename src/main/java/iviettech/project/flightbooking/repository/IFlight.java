/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.repository;

import iviettech.project.flightbooking.entity.Flight;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface IFlight extends CrudRepository<Flight, Long> {
    @Query(value = "select * from flight f "
            + "inner join flight_route fr "
            + "on f.flight_route_id = fr.flight_route_id "
            + "where date(f.depart_time) = ?1 and f.depart_time > date_add(now(), interval 6 hour) and "
            + "fr.departure = ?2 and fr.arrival = ?3", nativeQuery = true)
    List<Flight> searchFlight(String date, String departure, String arrival);
       
    @Query(value = "select * from flight f "
            + "inner join ticket t "
            + "on f.flight_id = t.flight_id "
            + "where t.ticket_id = ?1", nativeQuery = true)
    Flight getFlightOfTicket (long ticketId);
}
