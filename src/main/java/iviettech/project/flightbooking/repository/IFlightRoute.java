/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.repository;

import iviettech.project.flightbooking.entity.FlightRoute;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface IFlightRoute extends CrudRepository<FlightRoute, Long>{
    @Query(value = "select * from flight_route fr "
            + "where departure like ?1% and "
            + "arrival like ?2%", nativeQuery = true)
    List<FlightRoute> findByStation(String departure, String arrival);
    
    List<FlightRoute> findAllByOrderByDepartureAsc();
}
