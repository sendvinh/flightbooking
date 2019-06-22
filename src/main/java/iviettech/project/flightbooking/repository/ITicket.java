/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iviettech.project.flightbooking.repository;

import iviettech.project.flightbooking.entity.Ticket;
import iviettech.project.flightbooking.enumeration.SeatState;
import iviettech.project.flightbooking.enumeration.SeatTypeEnum;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Administrator
 */
@Repository
public interface ITicket extends CrudRepository<Ticket, Long>{
    @Query("select t from Ticket t where t.flight.flightId = ?1 order by t.seatCode")
    List<Ticket> getAllTicketOfFlight(long flightId);
    
    @Query("select t from Ticket t where t.flight.flightId = ?1 and "
            + "t.seatType.type = ?2 and "
            + "t.status = ?3")
    List<Ticket> getTicketOfFlight(long flightId, SeatTypeEnum seatType, SeatState status);
    
    @Query("select t from Ticket t where t.flight.flightId = ?1 and "
            + "t.status = ?2")
    List<Ticket> getTicketOfFlight(long flightId, SeatState status);
    
    @Query("select t from Ticket t where t.flight.flightId = ?1 and "
            + "t.seatType.id = ?2 and "
            + "t.status = ?3")
    List<Ticket> getTicketOfFlight(long flightId, int seatTypeId, SeatState status);
    
    @Query("select t from Ticket t where t.flight.flightId = ?1 and "
            + "t.seatType.type = ?2")
    List<Ticket> getTicketWithTypeOfSeat (long flightId, SeatTypeEnum seatType);
    
    @Query("select count(t) from Ticket t where t.flight.flightId = ?1 and "
            + "t.seatType.type = ?2 and "
            + "t.status = ?3")
    Long getNumberOfTicketOfFlight(long flightId, SeatTypeEnum seatType, SeatState status);
    
    @Query("select count(t) from Ticket t where t.flight.flightId = ?1 and "
            + "t.status = ?2")
    Long getNumberOfTicketOfFlight(long flightId, SeatState status);
    
    @Query("select case when COUNT(t) > 0 "
            + "then true else false end from Ticket t "
            + "where t.ticketId = ?1 and t.status = ?2")
    boolean isTicketStillInState(long ticketId, SeatState status);
    
    @Transactional
    @Modifying
    @Query(value =  "update ticket set STATUS = 'TEMPORARY', EXPIRE_TIME = DATE_ADD(NOW(), INTERVAL 596 SECOND) "
            + "where TICKET_ID = ?1 and STATUS = 'AVAILABLE'", nativeQuery = true)
    int updateTicketAfterChooseSeat(long ticketId);
    
    @Transactional
    @Modifying
    @Query(value =  "update ticket set STATUS = 'AVAILABLE', EXPIRE_TIME = null "
            + "where TICKET_ID = ?1 and STATUS = 'TEMPORARY'", nativeQuery = true)
    int updateTicketAfterReleaseSeat(long ticketId);
    
    @Transactional
    @Modifying
    @Query(value =  "update ticket set STATUS = 'AVAILABLE', EXPIRE_TIME = null " 
            + "where STATUS='TEMPORARY' AND EXPIRE_TIME < now()", nativeQuery = true)
    int updateTicketsWhenHoldingTimeOut();
    
    @Transactional
    @Modifying
    @Query(value =  "update ticket JOIN booking ON ticket.BOOKING_ID = booking.BOOKING_ID "
            + "set ticket.STATUS = 'AVAILABLE', ticket.EXPIRE_TIME = null, "
            + "ticket.DATE_OF_BIRTH = null, ticket.ATTACHED_INFANT = 0, "
            + "ticket.FIRST_NAME = null, ticket.LAST_NAME = null, " 
            + "ticket.GENDER = null, ticket.INFANT_NAME = null, "
            + "ticket.PRICE = null, ticket.BOOKING_ID = null, "
            + "ticket.PASSENGER_TYPE_ID = null "
            + "where booking.STATE ='CANCEL'", nativeQuery = true)
    int updateTicketStatusWhenExpire();
    
    
    @Query(value =  "SELECT TIMESTAMPDIFF(second,now(),"
            + "(Select EXPIRE_TIME from ticket where TICKET_ID=?1 AND STATUS='TEMPORARY'))", nativeQuery = true)
    BigInteger getTimeOutInSeconds(long ticketId);
}
