/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.repository;

import iviettech.project.flightbooking.entity.PassengerType;
import iviettech.project.flightbooking.enumeration.PassengerTypeEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface IPassengerType extends CrudRepository<PassengerType, Integer>{
//    @Query("select PassengerType pt where pt.state = ?2 where b.bookingId = ?1")
    PassengerType findByType(PassengerTypeEnum passengerType);
}
